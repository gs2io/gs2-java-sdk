/*
 * Copyright 2016 Game Server Services, Inc. or its affiliates. All Rights
 * Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package io.gs2.exchange.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.exchange.Gs2ExchangeRestClient;
import io.gs2.exchange.domain.iterator.*;
import io.gs2.exchange.model.*;
import io.gs2.exchange.request.*;
import io.gs2.exchange.result.*;

import java.util.List;


public class AwaitAccessTokenDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2ExchangeRestClient client;
    private final String namespaceName;
    private final AccessToken accessToken;
    private final String awaitName;
    private final String rateName;

    private final String parentKey;
    Long unlockAt;
    public Long getUnlockAt() {
        return this.unlockAt;
    }
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return accessToken.getUserId();
    }
    public String getAwaitName() {
        return awaitName;
    }
    public String getRateName() {
        return rateName;
    }

    public AwaitAccessTokenDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        AccessToken accessToken,
        String awaitName,
        String rateName
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2ExchangeRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.accessToken = accessToken;
        this.awaitName = awaitName;
        this.rateName = rateName;
        this.parentKey = io.gs2.exchange.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.accessToken != null && this.accessToken.getUserId() != null ? this.accessToken.getUserId().toString() : null,
            "Await"
        );
    }

    private Await get(
        GetAwaitRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withAwaitName(this.awaitName)
            .withRateName(this.rateName);
        GetAwaitResult result = this.client.getAwait(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.exchange.domain.model.AwaitDomain.createCacheKey(
                    request.getAwaitName() != null ? request.getAwaitName().toString() : null,
                    request.getRateName() != null ? request.getRateName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public io.gs2.core.domain.StampSheetDomain acquire(
        AcquireRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withAwaitName(this.awaitName)
            .withRateName(this.rateName);
        AcquireResult result = this.client.acquire(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.exchange.domain.model.AwaitDomain.createCacheKey(
                    request.getAwaitName() != null ? request.getAwaitName().toString() : null,
                    request.getRateName() != null ? request.getRateName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        io.gs2.core.domain.StampSheetDomain stampSheet = new io.gs2.core.domain.StampSheetDomain(
                cache,
                jobQueueDomain,
                session,
                result.getStampSheet(),
                result.getStampSheetEncryptionKeyId(),
                stampSheetConfiguration.namespaceName,
                stampSheetConfiguration.stampTaskEventHandler,
                stampSheetConfiguration.stampSheetEventHandler
        );
        try {
            stampSheet.run();
        } catch (io.gs2.core.exception.Gs2Exception e) {
            throw new io.gs2.core.exception.TransactionException(stampSheet, e);
        }
        return stampSheet;
    }

    public io.gs2.core.domain.StampSheetDomain skip(
        SkipRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withAwaitName(this.awaitName)
            .withRateName(this.rateName);
        SkipResult result = this.client.skip(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.exchange.domain.model.AwaitDomain.createCacheKey(
                    request.getAwaitName() != null ? request.getAwaitName().toString() : null,
                    request.getRateName() != null ? request.getRateName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        io.gs2.core.domain.StampSheetDomain stampSheet = new io.gs2.core.domain.StampSheetDomain(
                cache,
                jobQueueDomain,
                session,
                result.getStampSheet(),
                result.getStampSheetEncryptionKeyId(),
                stampSheetConfiguration.namespaceName,
                stampSheetConfiguration.stampTaskEventHandler,
                stampSheetConfiguration.stampSheetEventHandler
        );
        try {
            stampSheet.run();
        } catch (io.gs2.core.exception.Gs2Exception e) {
            throw new io.gs2.core.exception.TransactionException(stampSheet, e);
        }
        return stampSheet;
    }

    public AwaitAccessTokenDomain delete(
        DeleteAwaitRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withAwaitName(this.awaitName)
            .withRateName(this.rateName);
        DeleteAwaitResult result = null;
        try {
            result = this.client.deleteAwait(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.exchange.domain.model.AwaitDomain.createCacheKey(
                request.getAwaitName() != null ? request.getAwaitName().toString() : null,
                request.getRateName() != null ? request.getRateName().toString() : null
            ),
            Await.class
        );
        AwaitAccessTokenDomain domain = this;

        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String userId,
        String awaitName,
        String rateName,
        String childType
    )
    {
        return String.join(
            ":",
            "exchange",
            namespaceName,
            userId,
            awaitName,
            rateName,
            childType
        );
    }

    public static String createCacheKey(
        String awaitName,
        String rateName
    )
    {
        return String.join(
            ":",
            awaitName,
            rateName
        );
    }

    public Await model() {
        Await value = cache.get(
            parentKey,
            io.gs2.exchange.domain.model.AwaitDomain.createCacheKey(
                this.getAwaitName() != null ? this.getAwaitName().toString() : null,
                this.getRateName() != null ? this.getRateName().toString() : null
            ),
            Await.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetAwaitRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.exchange.domain.model.AwaitDomain.createCacheKey(
                        this.getAwaitName() != null ? this.getAwaitName().toString() : null,
                        this.getRateName() != null ? this.getRateName().toString() : null
                    ),
                    Await.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.exchange.domain.model.AwaitDomain.createCacheKey(
                this.getAwaitName() != null ? this.getAwaitName().toString() : null,
                this.getRateName() != null ? this.getRateName().toString() : null
            ),
            Await.class
        );
        }
        return value;
    }

}

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


public class AwaitDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2ExchangeRestClient client;
    private final String namespaceName;
    private final String userId;
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
        return userId;
    }
    public String getAwaitName() {
        return awaitName;
    }
    public String getRateName() {
        return rateName;
    }

    public AwaitDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String userId,
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
        this.userId = userId;
        this.awaitName = awaitName;
        this.rateName = rateName;
        this.parentKey = io.gs2.exchange.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "Await"
        );
    }

    private Await get(
        GetAwaitByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withAwaitName(this.awaitName)
            .withRateName(this.rateName);
        GetAwaitByUserIdResult result = this.client.getAwaitByUserId(
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
        AcquireByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withAwaitName(this.awaitName)
            .withRateName(this.rateName);
        AcquireByUserIdResult result = this.client.acquireByUserId(
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

    public io.gs2.core.domain.StampSheetDomain acquireForce(
        AcquireForceByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withAwaitName(this.awaitName)
            .withRateName(this.rateName);
        AcquireForceByUserIdResult result = this.client.acquireForceByUserId(
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
        SkipByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withAwaitName(this.awaitName)
            .withRateName(this.rateName);
        SkipByUserIdResult result = this.client.skipByUserId(
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

    public AwaitDomain delete(
        DeleteAwaitByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withAwaitName(this.awaitName)
            .withRateName(this.rateName);
        DeleteAwaitByUserIdResult result = null;
        try {
            result = this.client.deleteAwaitByUserId(
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
        AwaitDomain domain = this;

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
                    new GetAwaitByUserIdRequest()
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

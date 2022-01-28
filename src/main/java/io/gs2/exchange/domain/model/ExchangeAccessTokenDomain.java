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


public class ExchangeAccessTokenDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2ExchangeRestClient client;
    private final String namespaceName;
    private final AccessToken accessToken;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return accessToken.getUserId();
    }

    public ExchangeAccessTokenDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        AccessToken accessToken
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
        this.parentKey = io.gs2.exchange.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.accessToken != null && this.accessToken.getUserId() != null ? this.accessToken.getUserId().toString() : null,
            "Exchange"
        );
    }

    public io.gs2.core.domain.StampSheetDomain exchange(
        ExchangeRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null);
        ExchangeResult result = this.client.exchange(
            request
        );
        String parentKey = io.gs2.exchange.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "RateModel"
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.exchange.domain.model.RateModelDomain.createCacheKey(
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

    public static String createCacheParentKey(
        String namespaceName,
        String userId,
        String childType
    )
    {
        return String.join(
            ":",
            "exchange",
            namespaceName,
            userId,
            childType
        );
    }

    public static String createCacheKey(
    )
    {
        return "Singleton";
    }

}

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


public class UserDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2ExchangeRestClient client;
    private final String namespaceName;
    private final String userId;

    private final String parentKey;
    Long unlockAt;
    public Long getUnlockAt() {
        return this.unlockAt;
    }
    String nextPageToken;
    public String getNextPageToken() {
        return this.nextPageToken;
    }
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return userId;
    }

    public UserDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String userId
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
        this.parentKey = io.gs2.exchange.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "User"
        );
    }

    public AwaitDomain createAwait(
        CreateAwaitByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId);
        CreateAwaitByUserIdResult result = this.client.createAwaitByUserId(
            request
        );
        String parentKey = io.gs2.exchange.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "Await"
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.exchange.domain.model.AwaitDomain.createCacheKey(
                    result.getItem().getName() != null ? result.getItem().getName().toString() : null,
                    request.getRateName() != null ? request.getRateName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        AwaitDomain domain = new AwaitDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            request.getNamespaceName(),
            result.getItem().getUserId(),
            result.getItem().getName(),
            result.getItem().getRateName()
        );
        domain.unlockAt = result.getUnlockAt();

        return domain;
    }

    public ExchangeDomain exchange(
    ) {
        return new ExchangeDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.userId
        );
    }

    public DescribeAwaitsByUserIdIterator awaits(
        String rateName
    )
    {
        return new DescribeAwaitsByUserIdIterator(
            this.cache,
            this.client,
            this.namespaceName,
            this.userId,
            rateName
        );
    }

    public AwaitDomain await(
        String awaitName,
        String rateName
    ) {
        return new AwaitDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.userId,
            awaitName,
            rateName
        );
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
        String userId
    )
    {
        return String.join(
            ":",
            userId
        );
    }

}

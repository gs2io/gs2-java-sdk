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
package io.gs2.ranking.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.ranking.Gs2RankingRestClient;
import io.gs2.ranking.domain.iterator.*;
import io.gs2.ranking.model.*;
import io.gs2.ranking.request.*;
import io.gs2.ranking.result.*;

import java.util.List;


public class SubscribeUserDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2RankingRestClient client;
    private final String namespaceName;
    private final String userId;
    private final String categoryName;
    private final String targetUserId;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return userId;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public String getTargetUserId() {
        return targetUserId;
    }

    public SubscribeUserDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String userId,
        String categoryName,
        String targetUserId
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2RankingRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.userId = userId;
        this.categoryName = categoryName;
        this.targetUserId = targetUserId;
        this.parentKey = io.gs2.ranking.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "SubscribeUser"
        );
    }

    private SubscribeUser get(
        GetSubscribeByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withCategoryName(this.categoryName)
            .withTargetUserId(this.targetUserId);
        GetSubscribeByUserIdResult result = this.client.getSubscribeByUserId(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.ranking.domain.model.SubscribeUserDomain.createCacheKey(
                    request.getCategoryName() != null ? request.getCategoryName().toString() : null,
                    request.getTargetUserId() != null ? request.getTargetUserId().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public SubscribeUserDomain unsubscribe(
        UnsubscribeByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withCategoryName(this.categoryName)
            .withTargetUserId(this.targetUserId);
        UnsubscribeByUserIdResult result = this.client.unsubscribeByUserId(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.ranking.domain.model.SubscribeUserDomain.createCacheKey(
                    request.getCategoryName() != null ? request.getCategoryName().toString() : null,
                    request.getTargetUserId() != null ? request.getTargetUserId().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        SubscribeUserDomain domain = this;

        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String userId,
        String categoryName,
        String targetUserId,
        String childType
    )
    {
        return String.join(
            ":",
            "ranking",
            namespaceName,
            userId,
            categoryName,
            targetUserId,
            childType
        );
    }

    public static String createCacheKey(
        String categoryName,
        String targetUserId
    )
    {
        return String.join(
            ":",
            categoryName,
            targetUserId
        );
    }

    public SubscribeUser model() {
        SubscribeUser value = cache.get(
            parentKey,
            io.gs2.ranking.domain.model.SubscribeUserDomain.createCacheKey(
                this.getCategoryName() != null ? this.getCategoryName().toString() : null,
                this.getTargetUserId() != null ? this.getTargetUserId().toString() : null
            ),
            SubscribeUser.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetSubscribeByUserIdRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.ranking.domain.model.SubscribeUserDomain.createCacheKey(
                        this.getCategoryName() != null ? this.getCategoryName().toString() : null,
                        this.getTargetUserId() != null ? this.getTargetUserId().toString() : null
                    ),
                    SubscribeUser.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.ranking.domain.model.SubscribeUserDomain.createCacheKey(
                this.getCategoryName() != null ? this.getCategoryName().toString() : null,
                this.getTargetUserId() != null ? this.getTargetUserId().toString() : null
            ),
            SubscribeUser.class
        );
        }
        return value;
    }

}

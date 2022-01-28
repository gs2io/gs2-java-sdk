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
package io.gs2.lottery.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.lottery.Gs2LotteryRestClient;
import io.gs2.lottery.domain.iterator.*;
import io.gs2.lottery.model.*;
import io.gs2.lottery.request.*;
import io.gs2.lottery.result.*;

import java.util.List;


public class BoxDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2LotteryRestClient client;
    private final String namespaceName;
    private final String userId;
    private final String prizeTableName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return userId;
    }
    public String getPrizeTableName() {
        return prizeTableName;
    }

    public BoxDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String userId,
        String prizeTableName
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2LotteryRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.userId = userId;
        this.prizeTableName = prizeTableName;
        this.parentKey = io.gs2.lottery.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "Box"
        );
    }

    private BoxItems get(
        GetBoxByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withPrizeTableName(this.prizeTableName);
        GetBoxByUserIdResult result = this.client.getBoxByUserId(
            request
        );
        String parentKey = io.gs2.lottery.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "BoxItems"
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.lottery.domain.model.BoxItemsDomain.createCacheKey(
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public BoxDomain getRaw(
        GetRawBoxByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withPrizeTableName(this.prizeTableName);
        GetRawBoxByUserIdResult result = this.client.getRawBoxByUserId(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.lottery.domain.model.BoxDomain.createCacheKey(
                    request.getPrizeTableName() != null ? request.getPrizeTableName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        BoxDomain domain = this;

        return domain;
    }

    public BoxDomain reset(
        ResetBoxByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withPrizeTableName(this.prizeTableName);
        ResetBoxByUserIdResult result = this.client.resetBoxByUserId(
            request
        );
        String parentKey = io.gs2.lottery.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "Box"
        );
        for (Box item : cache.list(
                parentKey,
                Box.class
        )) {
            cache.delete(
                parentKey,
                io.gs2.lottery.domain.model.BoxDomain.createCacheKey(
                    request.getPrizeTableName() != null ? request.getPrizeTableName().toString() : null
                ),
                Box.class
            );
        }
        BoxDomain domain = this;
        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String userId,
        String prizeTableName,
        String childType
    )
    {
        return String.join(
            ":",
            "lottery",
            namespaceName,
            userId,
            prizeTableName,
            childType
        );
    }

    public static String createCacheKey(
        String prizeTableName
    )
    {
        return String.join(
            ":",
            prizeTableName
        );
    }

    public BoxItems model() {
        String parentKey = io.gs2.lottery.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "BoxItems"
        );
        BoxItems value = cache.get(
            parentKey,
            io.gs2.lottery.domain.model.BoxItemsDomain.createCacheKey(
            ),
            BoxItems.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetBoxByUserIdRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.lottery.domain.model.BoxItemsDomain.createCacheKey(
                    ),
                    BoxItems.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.lottery.domain.model.BoxItemsDomain.createCacheKey(
            ),
            BoxItems.class
        );
        }
        return value;
    }

}

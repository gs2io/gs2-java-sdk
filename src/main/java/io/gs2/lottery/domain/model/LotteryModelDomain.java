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


public class LotteryModelDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2LotteryRestClient client;
    private final String namespaceName;
    private final String lotteryName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getLotteryName() {
        return lotteryName;
    }

    public LotteryModelDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String lotteryName
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2LotteryRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.lotteryName = lotteryName;
        this.parentKey = io.gs2.lottery.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "LotteryModel"
        );
    }

    private LotteryModel get(
        GetLotteryModelRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withLotteryName(this.lotteryName);
        GetLotteryModelResult result = this.client.getLotteryModel(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.lottery.domain.model.LotteryModelDomain.createCacheKey(
                    request.getLotteryName() != null ? request.getLotteryName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public static String createCacheParentKey(
        String namespaceName,
        String lotteryName,
        String childType
    )
    {
        return String.join(
            ":",
            "lottery",
            namespaceName,
            lotteryName,
            childType
        );
    }

    public static String createCacheKey(
        String lotteryName
    )
    {
        return String.join(
            ":",
            lotteryName
        );
    }

    public LotteryModel model() {
        LotteryModel value = cache.get(
            parentKey,
            io.gs2.lottery.domain.model.LotteryModelDomain.createCacheKey(
                this.getLotteryName() != null ? this.getLotteryName().toString() : null
            ),
            LotteryModel.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetLotteryModelRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.lottery.domain.model.LotteryModelDomain.createCacheKey(
                        this.getLotteryName() != null ? this.getLotteryName().toString() : null
                    ),
                    LotteryModel.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.lottery.domain.model.LotteryModelDomain.createCacheKey(
                this.getLotteryName() != null ? this.getLotteryName().toString() : null
            ),
            LotteryModel.class
        );
        }
        return value;
    }

}

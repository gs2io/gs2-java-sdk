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


public class PrizeTableDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2LotteryRestClient client;
    private final String namespaceName;
    private final String prizeTableName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getPrizeTableName() {
        return prizeTableName;
    }

    public PrizeTableDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
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
        this.prizeTableName = prizeTableName;
        this.parentKey = io.gs2.lottery.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "PrizeTable"
        );
    }

    private PrizeTable get(
        GetPrizeTableRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withPrizeTableName(this.prizeTableName);
        GetPrizeTableResult result = this.client.getPrizeTable(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.lottery.domain.model.PrizeTableDomain.createCacheKey(
                    request.getPrizeTableName() != null ? request.getPrizeTableName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public static String createCacheParentKey(
        String namespaceName,
        String prizeTableName,
        String childType
    )
    {
        return String.join(
            ":",
            "lottery",
            namespaceName,
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

    public PrizeTable model() {
        PrizeTable value = cache.get(
            parentKey,
            io.gs2.lottery.domain.model.PrizeTableDomain.createCacheKey(
                this.getPrizeTableName() != null ? this.getPrizeTableName().toString() : null
            ),
            PrizeTable.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetPrizeTableRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.lottery.domain.model.PrizeTableDomain.createCacheKey(
                        this.getPrizeTableName() != null ? this.getPrizeTableName().toString() : null
                    ),
                    PrizeTable.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.lottery.domain.model.PrizeTableDomain.createCacheKey(
                this.getPrizeTableName() != null ? this.getPrizeTableName().toString() : null
            ),
            PrizeTable.class
        );
        }
        return value;
    }

}

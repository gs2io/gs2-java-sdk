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


public class PrizeTableMasterDomain {
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

    public PrizeTableMasterDomain(
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
            "PrizeTableMaster"
        );
    }

    private PrizeTableMaster get(
        GetPrizeTableMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withPrizeTableName(this.prizeTableName);
        GetPrizeTableMasterResult result = this.client.getPrizeTableMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.lottery.domain.model.PrizeTableMasterDomain.createCacheKey(
                    request.getPrizeTableName() != null ? request.getPrizeTableName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public PrizeTableMasterDomain update(
        UpdatePrizeTableMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withPrizeTableName(this.prizeTableName);
        UpdatePrizeTableMasterResult result = this.client.updatePrizeTableMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.lottery.domain.model.PrizeTableMasterDomain.createCacheKey(
                    request.getPrizeTableName() != null ? request.getPrizeTableName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        PrizeTableMasterDomain domain = this;

        return domain;
    }

    public PrizeTableMasterDomain delete(
        DeletePrizeTableMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withPrizeTableName(this.prizeTableName);
        DeletePrizeTableMasterResult result = null;
        try {
            result = this.client.deletePrizeTableMaster(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.lottery.domain.model.PrizeTableMasterDomain.createCacheKey(
                request.getPrizeTableName() != null ? request.getPrizeTableName().toString() : null
            ),
            PrizeTableMaster.class
        );
        PrizeTableMasterDomain domain = this;

        return domain;
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

    public PrizeTableMaster model() {
        PrizeTableMaster value = cache.get(
            parentKey,
            io.gs2.lottery.domain.model.PrizeTableMasterDomain.createCacheKey(
                this.getPrizeTableName() != null ? this.getPrizeTableName().toString() : null
            ),
            PrizeTableMaster.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetPrizeTableMasterRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.lottery.domain.model.PrizeTableMasterDomain.createCacheKey(
                        this.getPrizeTableName() != null ? this.getPrizeTableName().toString() : null
                    ),
                    PrizeTableMaster.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.lottery.domain.model.PrizeTableMasterDomain.createCacheKey(
                this.getPrizeTableName() != null ? this.getPrizeTableName().toString() : null
            ),
            PrizeTableMaster.class
        );
        }
        return value;
    }

}

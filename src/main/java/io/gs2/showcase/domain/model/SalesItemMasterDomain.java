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
package io.gs2.showcase.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.showcase.Gs2ShowcaseRestClient;
import io.gs2.showcase.domain.iterator.*;
import io.gs2.showcase.model.*;
import io.gs2.showcase.request.*;
import io.gs2.showcase.result.*;

import java.util.List;


public class SalesItemMasterDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2ShowcaseRestClient client;
    private final String namespaceName;
    private final String salesItemName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getSalesItemName() {
        return salesItemName;
    }

    public SalesItemMasterDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String salesItemName
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2ShowcaseRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.salesItemName = salesItemName;
        this.parentKey = io.gs2.showcase.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "SalesItemMaster"
        );
    }

    private SalesItemMaster get(
        GetSalesItemMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withSalesItemName(this.salesItemName);
        GetSalesItemMasterResult result = this.client.getSalesItemMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.showcase.domain.model.SalesItemMasterDomain.createCacheKey(
                    request.getSalesItemName() != null ? request.getSalesItemName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public SalesItemMasterDomain update(
        UpdateSalesItemMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withSalesItemName(this.salesItemName);
        UpdateSalesItemMasterResult result = this.client.updateSalesItemMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.showcase.domain.model.SalesItemMasterDomain.createCacheKey(
                    request.getSalesItemName() != null ? request.getSalesItemName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        SalesItemMasterDomain domain = this;

        return domain;
    }

    public SalesItemMasterDomain delete(
        DeleteSalesItemMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withSalesItemName(this.salesItemName);
        DeleteSalesItemMasterResult result = null;
        try {
            result = this.client.deleteSalesItemMaster(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.showcase.domain.model.SalesItemMasterDomain.createCacheKey(
                request.getSalesItemName() != null ? request.getSalesItemName().toString() : null
            ),
            SalesItemMaster.class
        );
        SalesItemMasterDomain domain = this;

        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String salesItemName,
        String childType
    )
    {
        return String.join(
            ":",
            "showcase",
            namespaceName,
            salesItemName,
            childType
        );
    }

    public static String createCacheKey(
        String salesItemName
    )
    {
        return String.join(
            ":",
            salesItemName
        );
    }

    public SalesItemMaster model() {
        SalesItemMaster value = cache.get(
            parentKey,
            io.gs2.showcase.domain.model.SalesItemMasterDomain.createCacheKey(
                this.getSalesItemName() != null ? this.getSalesItemName().toString() : null
            ),
            SalesItemMaster.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetSalesItemMasterRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.showcase.domain.model.SalesItemMasterDomain.createCacheKey(
                        this.getSalesItemName() != null ? this.getSalesItemName().toString() : null
                    ),
                    SalesItemMaster.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.showcase.domain.model.SalesItemMasterDomain.createCacheKey(
                this.getSalesItemName() != null ? this.getSalesItemName().toString() : null
            ),
            SalesItemMaster.class
        );
        }
        return value;
    }

}

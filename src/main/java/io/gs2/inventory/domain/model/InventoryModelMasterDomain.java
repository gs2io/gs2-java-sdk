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
package io.gs2.inventory.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.inventory.Gs2InventoryRestClient;
import io.gs2.inventory.domain.iterator.*;
import io.gs2.inventory.model.*;
import io.gs2.inventory.request.*;
import io.gs2.inventory.result.*;

import java.util.List;


public class InventoryModelMasterDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2InventoryRestClient client;
    private final String namespaceName;
    private final String inventoryName;

    private final String parentKey;
    String nextPageToken;
    public String getNextPageToken() {
        return this.nextPageToken;
    }
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getInventoryName() {
        return inventoryName;
    }

    public InventoryModelMasterDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String inventoryName
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2InventoryRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.inventoryName = inventoryName;
        this.parentKey = io.gs2.inventory.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "InventoryModelMaster"
        );
    }

    private InventoryModelMaster get(
        GetInventoryModelMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withInventoryName(this.inventoryName);
        GetInventoryModelMasterResult result = this.client.getInventoryModelMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.inventory.domain.model.InventoryModelMasterDomain.createCacheKey(
                    request.getInventoryName() != null ? request.getInventoryName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public InventoryModelMasterDomain update(
        UpdateInventoryModelMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withInventoryName(this.inventoryName);
        UpdateInventoryModelMasterResult result = this.client.updateInventoryModelMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.inventory.domain.model.InventoryModelMasterDomain.createCacheKey(
                    request.getInventoryName() != null ? request.getInventoryName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        InventoryModelMasterDomain domain = this;

        return domain;
    }

    public InventoryModelMasterDomain delete(
        DeleteInventoryModelMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withInventoryName(this.inventoryName);
        DeleteInventoryModelMasterResult result = null;
        try {
            result = this.client.deleteInventoryModelMaster(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.inventory.domain.model.InventoryModelMasterDomain.createCacheKey(
                request.getInventoryName() != null ? request.getInventoryName().toString() : null
            ),
            InventoryModelMaster.class
        );
        InventoryModelMasterDomain domain = this;

        return domain;
    }

    public ItemModelMasterDomain createItemModelMaster(
        CreateItemModelMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withInventoryName(this.inventoryName);
        CreateItemModelMasterResult result = this.client.createItemModelMaster(
            request
        );
        String parentKey = io.gs2.inventory.domain.model.InventoryModelMasterDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.inventoryName != null ? this.inventoryName.toString() : null,
            "ItemModelMaster"
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.inventory.domain.model.ItemModelMasterDomain.createCacheKey(
                    result.getItem().getName() != null ? result.getItem().getName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        ItemModelMasterDomain domain = new ItemModelMasterDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            request.getNamespaceName(),
            result.getItem().getInventoryName(),
            result.getItem().getName()
        );

        return domain;
    }

    public DescribeItemModelMastersIterator itemModelMasters(
    )
    {
        return new DescribeItemModelMastersIterator(
            this.cache,
            this.client,
            this.namespaceName,
            this.inventoryName
        );
    }

    public ItemModelMasterDomain itemModelMaster(
        String itemName
    ) {
        return new ItemModelMasterDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.inventoryName,
            itemName
        );
    }

    public static String createCacheParentKey(
        String namespaceName,
        String inventoryName,
        String childType
    )
    {
        return String.join(
            ":",
            "inventory",
            namespaceName,
            inventoryName,
            childType
        );
    }

    public static String createCacheKey(
        String inventoryName
    )
    {
        return String.join(
            ":",
            inventoryName
        );
    }

    public InventoryModelMaster model() {
        InventoryModelMaster value = cache.get(
            parentKey,
            io.gs2.inventory.domain.model.InventoryModelMasterDomain.createCacheKey(
                this.getInventoryName() != null ? this.getInventoryName().toString() : null
            ),
            InventoryModelMaster.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetInventoryModelMasterRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.inventory.domain.model.InventoryModelMasterDomain.createCacheKey(
                        this.getInventoryName() != null ? this.getInventoryName().toString() : null
                    ),
                    InventoryModelMaster.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.inventory.domain.model.InventoryModelMasterDomain.createCacheKey(
                this.getInventoryName() != null ? this.getInventoryName().toString() : null
            ),
            InventoryModelMaster.class
        );
        }
        return value;
    }

}

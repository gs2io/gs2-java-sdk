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


public class ItemModelMasterDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2InventoryRestClient client;
    private final String namespaceName;
    private final String inventoryName;
    private final String itemName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getInventoryName() {
        return inventoryName;
    }
    public String getItemName() {
        return itemName;
    }

    public ItemModelMasterDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String inventoryName,
        String itemName
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
        this.itemName = itemName;
        this.parentKey = io.gs2.inventory.domain.model.InventoryModelMasterDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.inventoryName != null ? this.inventoryName.toString() : null,
            "ItemModelMaster"
        );
    }

    private ItemModelMaster get(
        GetItemModelMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withInventoryName(this.inventoryName)
            .withItemName(this.itemName);
        GetItemModelMasterResult result = this.client.getItemModelMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.inventory.domain.model.ItemModelMasterDomain.createCacheKey(
                    request.getItemName() != null ? request.getItemName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public ItemModelMasterDomain update(
        UpdateItemModelMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withInventoryName(this.inventoryName)
            .withItemName(this.itemName);
        UpdateItemModelMasterResult result = this.client.updateItemModelMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.inventory.domain.model.ItemModelMasterDomain.createCacheKey(
                    request.getItemName() != null ? request.getItemName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        ItemModelMasterDomain domain = this;

        return domain;
    }

    public ItemModelMasterDomain delete(
        DeleteItemModelMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withInventoryName(this.inventoryName)
            .withItemName(this.itemName);
        DeleteItemModelMasterResult result = null;
        try {
            result = this.client.deleteItemModelMaster(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.inventory.domain.model.ItemModelMasterDomain.createCacheKey(
                request.getItemName() != null ? request.getItemName().toString() : null
            ),
            ItemModelMaster.class
        );
        ItemModelMasterDomain domain = this;

        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String inventoryName,
        String itemName,
        String childType
    )
    {
        return String.join(
            ":",
            "inventory",
            namespaceName,
            inventoryName,
            itemName,
            childType
        );
    }

    public static String createCacheKey(
        String itemName
    )
    {
        return String.join(
            ":",
            itemName
        );
    }

    public ItemModelMaster model() {
        ItemModelMaster value = cache.get(
            parentKey,
            io.gs2.inventory.domain.model.ItemModelMasterDomain.createCacheKey(
                this.getItemName() != null ? this.getItemName().toString() : null
            ),
            ItemModelMaster.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetItemModelMasterRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.inventory.domain.model.ItemModelMasterDomain.createCacheKey(
                        this.getItemName() != null ? this.getItemName().toString() : null
                    ),
                    ItemModelMaster.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.inventory.domain.model.ItemModelMasterDomain.createCacheKey(
                this.getItemName() != null ? this.getItemName().toString() : null
            ),
            ItemModelMaster.class
        );
        }
        return value;
    }

}

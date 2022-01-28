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


public class ReferenceOfDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2InventoryRestClient client;
    private final String namespaceName;
    private final String userId;
    private final String inventoryName;
    private final String itemName;
    private final String itemSetName;
    private final String referenceOf;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return userId;
    }
    public String getInventoryName() {
        return inventoryName;
    }
    public String getItemName() {
        return itemName;
    }
    public String getItemSetName() {
        return itemSetName;
    }
    public String getReferenceOf() {
        return referenceOf;
    }

    public ReferenceOfDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String userId,
        String inventoryName,
        String itemName,
        String itemSetName,
        String referenceOf
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2InventoryRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.userId = userId;
        this.inventoryName = inventoryName;
        this.itemName = itemName;
        this.itemSetName = itemSetName;
        this.referenceOf = referenceOf;
        this.parentKey = io.gs2.inventory.domain.model.ItemSetDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            this.inventoryName != null ? this.inventoryName.toString() : null,
            this.itemName != null ? this.itemName.toString() : null,
            this.itemSetName != null ? this.itemSetName.toString() : null,
            "ReferenceOf"
        );
    }

    private List<String> get(
        GetReferenceOfByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withInventoryName(this.inventoryName)
            .withItemName(this.itemName)
            .withItemSetName(this.itemSetName)
            .withReferenceOf(this.referenceOf);
        GetReferenceOfByUserIdResult result = this.client.getReferenceOfByUserId(
            request
        );
        String parentKey = "inventory:String";
        for (String item : result.getItem()) {
                
            if (item != null) {
                cache.put(
                    parentKey,
                    item,
                    item,
                    System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                );
            }
        }
                
        if (result.getItemSet() != null) {
            cache.put(
                parentKey,
                io.gs2.inventory.domain.model.ItemSetDomain.createCacheKey(
                    request.getItemName() != null ? request.getItemName().toString() : null,
                    result.getItemSet().getName() != null ? result.getItemSet().getName().toString() : null
                ),
                result.getItemSet(),
                result.getItemSet().getExpiresAt() == null ? System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes : result.getItemSet().getExpiresAt()
            );
        }
                
        if (result.getItemModel() != null) {
            cache.put(
                parentKey,
                io.gs2.inventory.domain.model.ItemModelDomain.createCacheKey(
                    request.getItemName() != null ? request.getItemName().toString() : null
                ),
                result.getItemModel(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
                
        if (result.getInventory() != null) {
            cache.put(
                parentKey,
                io.gs2.inventory.domain.model.InventoryDomain.createCacheKey(
                    request.getInventoryName() != null ? request.getInventoryName().toString() : null
                ),
                result.getInventory(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public ReferenceOfDomain[] verify(
        VerifyReferenceOfByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withInventoryName(this.inventoryName)
            .withItemName(this.itemName)
            .withItemSetName(this.itemSetName)
            .withReferenceOf(this.referenceOf);
        VerifyReferenceOfByUserIdResult result = this.client.verifyReferenceOfByUserId(
            request
        );
        String parentKey = "inventory:String";
        for (String item : result.getItem()) {
                
            if (item != null) {
                cache.put(
                    parentKey,
                    item,
                    item,
                    System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                );
            }
        }
                
        if (result.getItemSet() != null) {
            cache.put(
                parentKey,
                io.gs2.inventory.domain.model.ItemSetDomain.createCacheKey(
                    request.getItemName() != null ? request.getItemName().toString() : null,
                    result.getItemSet().getName() != null ? result.getItemSet().getName().toString() : null
                ),
                result.getItemSet(),
                result.getItemSet().getExpiresAt() == null ? System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes : result.getItemSet().getExpiresAt()
            );
        }
                
        if (result.getItemModel() != null) {
            cache.put(
                parentKey,
                io.gs2.inventory.domain.model.ItemModelDomain.createCacheKey(
                    request.getItemName() != null ? request.getItemName().toString() : null
                ),
                result.getItemModel(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
                
        if (result.getInventory() != null) {
            cache.put(
                parentKey,
                io.gs2.inventory.domain.model.InventoryDomain.createCacheKey(
                    request.getInventoryName() != null ? request.getInventoryName().toString() : null
                ),
                result.getInventory(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        ReferenceOfDomain[] domain = new ReferenceOfDomain[result.getItem().size()];
        for (int i=0; i<result.getItem().size(); i++)
        {
            domain[i] = new ReferenceOfDomain(
                this.cache,
                this.jobQueueDomain,
                this.stampSheetConfiguration,
                this.session,
                request.getNamespaceName(),
                request.getUserId(),
                request.getInventoryName(),
                request.getItemName(),
                request.getItemSetName(),
                request.getReferenceOf()
            );
        }
        return domain;
    }

    public ReferenceOfDomain[] delete(
        DeleteReferenceOfByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withInventoryName(this.inventoryName)
            .withItemName(this.itemName)
            .withItemSetName(this.itemSetName)
            .withReferenceOf(this.referenceOf);
        DeleteReferenceOfByUserIdResult result = null;
        try {
            result = this.client.deleteReferenceOfByUserId(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        String parentKey = "inventory:String";
        ReferenceOfDomain[] domain = new ReferenceOfDomain[result.getItem().size()];
        for (int i=0; i<result.getItem().size(); i++)
        {
            domain[i] = new ReferenceOfDomain(
                this.cache,
                this.jobQueueDomain,
                this.stampSheetConfiguration,
                this.session,
                request.getNamespaceName(),
                request.getUserId(),
                request.getInventoryName(),
                request.getItemName(),
                request.getItemSetName(),
                request.getReferenceOf()
            );
        }
        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String userId,
        String inventoryName,
        String itemName,
        String itemSetName,
        String referenceOf,
        String childType
    )
    {
        return String.join(
            ":",
            "inventory",
            namespaceName,
            userId,
            inventoryName,
            itemName,
            itemSetName,
            referenceOf,
            childType
        );
    }

    public static String createCacheKey(
        String referenceOf
    )
    {
        return String.join(
            ":",
            referenceOf
        );
    }

    public String model() {
        return cache.get(
            parentKey,
            io.gs2.inventory.domain.model.ReferenceOfDomain.createCacheKey(
                this.getReferenceOf() != null ? this.getReferenceOf().toString() : null
            ),
            String.class
        );
    }

}

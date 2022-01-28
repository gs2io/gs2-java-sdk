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


public class ReferenceOfAccessTokenDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2InventoryRestClient client;
    private final String namespaceName;
    private final AccessToken accessToken;
    private final String inventoryName;
    private final String itemName;
    private final String itemSetName;
    private final String referenceOf;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return accessToken.getUserId();
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

    public ReferenceOfAccessTokenDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        AccessToken accessToken,
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
        this.accessToken = accessToken;
        this.inventoryName = inventoryName;
        this.itemName = itemName;
        this.itemSetName = itemSetName;
        this.referenceOf = referenceOf;
        this.parentKey = io.gs2.inventory.domain.model.ItemSetDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.accessToken != null && this.accessToken.getUserId() != null ? this.accessToken.getUserId().toString() : null,
            this.inventoryName != null ? this.inventoryName.toString() : null,
            this.itemName != null ? this.itemName.toString() : null,
            this.itemSetName != null ? this.itemSetName.toString() : null,
            "ReferenceOf"
        );
    }

    private List<String> get(
        GetReferenceOfRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withInventoryName(this.inventoryName)
            .withItemName(this.itemName)
            .withItemSetName(this.itemSetName)
            .withReferenceOf(this.referenceOf);
        GetReferenceOfResult result = this.client.getReferenceOf(
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

    public ReferenceOfAccessTokenDomain[] verify(
        VerifyReferenceOfRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withInventoryName(this.inventoryName)
            .withItemName(this.itemName)
            .withItemSetName(this.itemSetName)
            .withReferenceOf(this.referenceOf);
        VerifyReferenceOfResult result = this.client.verifyReferenceOf(
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
        ReferenceOfAccessTokenDomain[] domain = new ReferenceOfAccessTokenDomain[result.getItem().size()];
        for (int i=0; i<result.getItem().size(); i++)
        {
            domain[i] = new ReferenceOfAccessTokenDomain(
                this.cache,
                this.jobQueueDomain,
                this.stampSheetConfiguration,
                this.session,
                request.getNamespaceName(),
                this.accessToken,
                request.getInventoryName(),
                request.getItemName(),
                request.getItemSetName(),
                request.getReferenceOf()
            );
        }
        return domain;
    }

    public ReferenceOfAccessTokenDomain[] delete(
        DeleteReferenceOfRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withInventoryName(this.inventoryName)
            .withItemName(this.itemName)
            .withItemSetName(this.itemSetName)
            .withReferenceOf(this.referenceOf);
        DeleteReferenceOfResult result = null;
        try {
            result = this.client.deleteReferenceOf(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        String parentKey = "inventory:String";
        ReferenceOfAccessTokenDomain[] domain = new ReferenceOfAccessTokenDomain[result.getItem().size()];
        for (int i=0; i<result.getItem().size(); i++)
        {
            domain[i] = new ReferenceOfAccessTokenDomain(
                this.cache,
                this.jobQueueDomain,
                this.stampSheetConfiguration,
                this.session,
                request.getNamespaceName(),
                this.accessToken,
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

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


public class ItemSetAccessTokenDomain {
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

    private final String parentKey;
    String body;
    public String getBody() {
        return this.body;
    }
    String signature;
    public String getSignature() {
        return this.signature;
    }
    Long overflowCount;
    public Long getOverflowCount() {
        return this.overflowCount;
    }
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

    public ItemSetAccessTokenDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        AccessToken accessToken,
        String inventoryName,
        String itemName,
        String itemSetName
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
        this.parentKey = io.gs2.inventory.domain.model.InventoryDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.accessToken != null && this.accessToken.getUserId() != null ? this.accessToken.getUserId().toString() : null,
            this.inventoryName != null ? this.inventoryName.toString() : null,
            "ItemSet"
        );
    }

    private List<ItemSet> get(
        GetItemSetRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withInventoryName(this.inventoryName)
            .withItemName(this.itemName)
            .withItemSetName(this.itemSetName);
        GetItemSetResult result = this.client.getItemSet(
            request
        );
        String parentKey = io.gs2.inventory.domain.model.InventoryDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.accessToken != null && this.accessToken.getUserId() != null ? this.accessToken.getUserId().toString() : null,
            this.inventoryName != null ? this.inventoryName.toString() : null,
            "ItemSet"
        );
        for (ItemSet item : result.getItems()) {
                
            if (item != null) {
                cache.put(
                    parentKey,
                    io.gs2.inventory.domain.model.ItemSetDomain.createCacheKey(
                        request.getItemName() != null ? request.getItemName().toString() : null,
                        item.getName() != null ? item.getName().toString() : null
                    ),
                    item,
                    item.getExpiresAt() == null ? System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes : item.getExpiresAt()
                );cache.put(
                    parentKey,
                    io.gs2.inventory.domain.model.ItemSetDomain.createCacheKey(
                        request.getItemName() != null ? request.getItemName().toString() : null,
                        "null"
                    ),
                    item,
                    item.getExpiresAt() == null ? System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes : item.getExpiresAt()
                );
            }
        }
                
        if (result.getItemModel() != null) {
            cache.put(
                parentKey,
                io.gs2.inventory.domain.model.ItemModelDomain.createCacheKey(
                    request.getItemName() != null ? request.getItemName().toString() : null
                ),
                result.getItemModel(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );cache.put(
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
            );cache.put(
                parentKey,
                io.gs2.inventory.domain.model.InventoryDomain.createCacheKey(
                    request.getInventoryName() != null ? request.getInventoryName().toString() : null
                ),
                result.getInventory(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItems();
    }

    public ItemSetAccessTokenDomain[] getItemWithSignature(
        GetItemWithSignatureRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withInventoryName(this.inventoryName)
            .withItemName(this.itemName)
            .withItemSetName(this.itemSetName);
        GetItemWithSignatureResult result = this.client.getItemWithSignature(
            request
        );
        String parentKey = io.gs2.inventory.domain.model.InventoryDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.accessToken != null && this.accessToken.getUserId() != null ? this.accessToken.getUserId().toString() : null,
            this.inventoryName != null ? this.inventoryName.toString() : null,
            "ItemSet"
        );
        for (ItemSet item : result.getItems()) {
                
            if (item != null) {
                cache.put(
                    parentKey,
                    io.gs2.inventory.domain.model.ItemSetDomain.createCacheKey(
                        request.getItemName() != null ? request.getItemName().toString() : null,
                        item.getName() != null ? item.getName().toString() : null
                    ),
                    item,
                    item.getExpiresAt() == null ? System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes : item.getExpiresAt()
                );cache.put(
                    parentKey,
                    io.gs2.inventory.domain.model.ItemSetDomain.createCacheKey(
                        request.getItemName() != null ? request.getItemName().toString() : null,
                        "null"
                    ),
                    item,
                    item.getExpiresAt() == null ? System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes : item.getExpiresAt()
                );
            }
        }
                
        if (result.getItemModel() != null) {
            cache.put(
                parentKey,
                io.gs2.inventory.domain.model.ItemModelDomain.createCacheKey(
                    request.getItemName() != null ? request.getItemName().toString() : null
                ),
                result.getItemModel(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );cache.put(
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
            );cache.put(
                parentKey,
                io.gs2.inventory.domain.model.InventoryDomain.createCacheKey(
                    request.getInventoryName() != null ? request.getInventoryName().toString() : null
                ),
                result.getInventory(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        ItemSetAccessTokenDomain[] domain = new ItemSetAccessTokenDomain[result.getItems().size()];
        for (int i=0; i<result.getItems().size(); i++)
        {
            domain[i] = new ItemSetAccessTokenDomain(
                this.cache,
                this.jobQueueDomain,
                this.stampSheetConfiguration,
                this.session,
                request.getNamespaceName(),
                this.accessToken,
                result.getItems().get(i).getInventoryName(),
                result.getItems().get(i).getItemName(),
                result.getItems().get(i).getName()
            );
        }
        this.body = result.getBody();
        this.signature = result.getSignature();
        return domain;
    }

    public ItemSetAccessTokenDomain[] consume(
        ConsumeItemSetRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withInventoryName(this.inventoryName)
            .withItemName(this.itemName)
            .withItemSetName(this.itemSetName);
        ConsumeItemSetResult result = this.client.consumeItemSet(
            request
        );
        String parentKey = io.gs2.inventory.domain.model.InventoryDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.accessToken != null && this.accessToken.getUserId() != null ? this.accessToken.getUserId().toString() : null,
            this.inventoryName != null ? this.inventoryName.toString() : null,
            "ItemSet"
        );
        for (ItemSet item : result.getItems()) {
                
            if (item != null) {
                cache.put(
                    parentKey,
                    io.gs2.inventory.domain.model.ItemSetDomain.createCacheKey(
                        request.getItemName() != null ? request.getItemName().toString() : null,
                        item.getName() != null ? item.getName().toString() : null
                    ),
                    item,
                    item.getExpiresAt() == null ? System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes : item.getExpiresAt()
                );cache.put(
                    parentKey,
                    io.gs2.inventory.domain.model.ItemSetDomain.createCacheKey(
                        request.getItemName() != null ? request.getItemName().toString() : null,
                        "null"
                    ),
                    item,
                    item.getExpiresAt() == null ? System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes : item.getExpiresAt()
                );
            }
        }
                
        if (result.getItemModel() != null) {
            cache.put(
                parentKey,
                io.gs2.inventory.domain.model.ItemModelDomain.createCacheKey(
                    request.getItemName() != null ? request.getItemName().toString() : null
                ),
                result.getItemModel(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );cache.put(
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
            );cache.put(
                parentKey,
                io.gs2.inventory.domain.model.InventoryDomain.createCacheKey(
                    request.getInventoryName() != null ? request.getInventoryName().toString() : null
                ),
                result.getInventory(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        ItemSetAccessTokenDomain[] domain = new ItemSetAccessTokenDomain[result.getItems().size()];
        for (int i=0; i<result.getItems().size(); i++)
        {
            domain[i] = new ItemSetAccessTokenDomain(
                this.cache,
                this.jobQueueDomain,
                this.stampSheetConfiguration,
                this.session,
                request.getNamespaceName(),
                this.accessToken,
                result.getItems().get(i).getInventoryName(),
                result.getItems().get(i).getItemName(),
                result.getItems().get(i).getName()
            );
        }
        return domain;
    }

    public ReferenceOfAccessTokenDomain[] addReferenceOf(
        AddReferenceOfRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withInventoryName(this.inventoryName)
            .withItemName(this.itemName)
            .withItemSetName(this.itemSetName);
        AddReferenceOfResult result = this.client.addReferenceOf(
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
                );cache.put(
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
            );cache.put(
                parentKey,
                io.gs2.inventory.domain.model.ItemSetDomain.createCacheKey(
                    request.getItemName() != null ? request.getItemName().toString() : null,
                    "null"
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
            );cache.put(
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
            );cache.put(
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

    public DescribeReferenceOfIterator referenceOves(
    )
    {
        return new DescribeReferenceOfIterator(
            this.cache,
            this.client,
            this.namespaceName,
            this.inventoryName,
            this.accessToken,
            this.itemName,
            this.itemSetName
        );
    }

    public ReferenceOfAccessTokenDomain referenceOf(
        String referenceOf
    ) {
        return new ReferenceOfAccessTokenDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.accessToken,
            this.inventoryName,
            this.itemName,
            this.itemSetName,
            referenceOf
        );
    }

    public static String createCacheParentKey(
        String namespaceName,
        String userId,
        String inventoryName,
        String itemName,
        String itemSetName,
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
            childType
        );
    }

    public static String createCacheKey(
        String itemName,
        String itemSetName
    )
    {
        return String.join(
            ":",
            itemName,
            itemSetName
        );
    }

    public ItemSet model() {
        ItemSet value = cache.get(
            parentKey,
            io.gs2.inventory.domain.model.ItemSetDomain.createCacheKey(
                this.getItemName() != null ? this.getItemName().toString() : null,
                this.getItemSetName() != null ? this.getItemSetName().toString() : null
            ),
            ItemSet.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetItemSetRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.inventory.domain.model.ItemSetDomain.createCacheKey(
                        this.getItemName() != null ? this.getItemName().toString() : null,
                        this.getItemSetName() != null ? this.getItemSetName().toString() : null
                    ),
                    ItemSet.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.inventory.domain.model.ItemSetDomain.createCacheKey(
                this.getItemName() != null ? this.getItemName().toString() : null,
                this.getItemSetName() != null ? this.getItemSetName().toString() : null
            ),
            ItemSet.class
        );
        }
        return value;
    }

}

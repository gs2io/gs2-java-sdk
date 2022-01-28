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


public class InventoryDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2InventoryRestClient client;
    private final String namespaceName;
    private final String userId;
    private final String inventoryName;

    private final String parentKey;
    Long overflowCount;
    public Long getOverflowCount() {
        return this.overflowCount;
    }
    String nextPageToken;
    public String getNextPageToken() {
        return this.nextPageToken;
    }
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return userId;
    }
    public String getInventoryName() {
        return inventoryName;
    }

    public InventoryDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String userId,
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
        this.userId = userId;
        this.inventoryName = inventoryName;
        this.parentKey = io.gs2.inventory.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "Inventory"
        );
    }

    private Inventory get(
        GetInventoryByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withInventoryName(this.inventoryName);
        GetInventoryByUserIdResult result = this.client.getInventoryByUserId(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.inventory.domain.model.InventoryDomain.createCacheKey(
                    request.getInventoryName() != null ? request.getInventoryName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public InventoryDomain addCapacity(
        AddCapacityByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withInventoryName(this.inventoryName);
        AddCapacityByUserIdResult result = this.client.addCapacityByUserId(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.inventory.domain.model.InventoryDomain.createCacheKey(
                    request.getInventoryName() != null ? request.getInventoryName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        InventoryDomain domain = this;

        return domain;
    }

    public InventoryDomain setCapacity(
        SetCapacityByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withInventoryName(this.inventoryName);
        SetCapacityByUserIdResult result = this.client.setCapacityByUserId(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.inventory.domain.model.InventoryDomain.createCacheKey(
                    request.getInventoryName() != null ? request.getInventoryName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        InventoryDomain domain = this;

        return domain;
    }

    public InventoryDomain delete(
        DeleteInventoryByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withInventoryName(this.inventoryName);
        DeleteInventoryByUserIdResult result = null;
        try {
            result = this.client.deleteInventoryByUserId(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.inventory.domain.model.InventoryDomain.createCacheKey(
                request.getInventoryName() != null ? request.getInventoryName().toString() : null
            ),
            Inventory.class
        );
        InventoryDomain domain = this;

        return domain;
    }

    public DescribeItemSetsByUserIdIterator itemSets(
    )
    {
        return new DescribeItemSetsByUserIdIterator(
            this.cache,
            this.client,
            this.namespaceName,
            this.inventoryName,
            this.userId
        );
    }

    public ItemSetDomain itemSet(
        String itemName,
        String itemSetName
    ) {
        return new ItemSetDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.userId,
            this.inventoryName,
            itemName,
            itemSetName
        );
    }

    public static String createCacheParentKey(
        String namespaceName,
        String userId,
        String inventoryName,
        String childType
    )
    {
        return String.join(
            ":",
            "inventory",
            namespaceName,
            userId,
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

    public Inventory model() {
        Inventory value = cache.get(
            parentKey,
            io.gs2.inventory.domain.model.InventoryDomain.createCacheKey(
                this.getInventoryName() != null ? this.getInventoryName().toString() : null
            ),
            Inventory.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetInventoryByUserIdRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.inventory.domain.model.InventoryDomain.createCacheKey(
                        this.getInventoryName() != null ? this.getInventoryName().toString() : null
                    ),
                    Inventory.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.inventory.domain.model.InventoryDomain.createCacheKey(
                this.getInventoryName() != null ? this.getInventoryName().toString() : null
            ),
            Inventory.class
        );
        }
        return value;
    }

}

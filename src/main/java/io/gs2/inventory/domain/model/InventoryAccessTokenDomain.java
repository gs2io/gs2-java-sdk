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


public class InventoryAccessTokenDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2InventoryRestClient client;
    private final String namespaceName;
    private final AccessToken accessToken;
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
        return accessToken.getUserId();
    }
    public String getInventoryName() {
        return inventoryName;
    }

    public InventoryAccessTokenDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        AccessToken accessToken,
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
        this.accessToken = accessToken;
        this.inventoryName = inventoryName;
        this.parentKey = io.gs2.inventory.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.accessToken != null && this.accessToken.getUserId() != null ? this.accessToken.getUserId().toString() : null,
            "Inventory"
        );
    }

    private Inventory get(
        GetInventoryRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withInventoryName(this.inventoryName);
        GetInventoryResult result = this.client.getInventory(
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

    public DescribeItemSetsIterator itemSets(
    )
    {
        return new DescribeItemSetsIterator(
            this.cache,
            this.client,
            this.namespaceName,
            this.inventoryName,
            this.accessToken
        );
    }

    public ItemSetAccessTokenDomain itemSet(
        String itemName,
        String itemSetName
    ) {
        return new ItemSetAccessTokenDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.accessToken,
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
                    new GetInventoryRequest()
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


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
package io.gs2.inventory.domain.iterator;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.inventory.Gs2InventoryRestClient;
import io.gs2.inventory.model.*;
import io.gs2.inventory.request.*;
import io.gs2.inventory.result.*;

import java.util.*;
import java.util.stream.Collectors;

public class DescribeItemModelsIterator implements Iterator<ItemModel>, Iterable<ItemModel> {
    CacheDatabase cache;
    Gs2InventoryRestClient client;
    String namespaceName;
    String inventoryName;
    boolean last;
    List<ItemModel> result;

    Integer fetchSize;

    public DescribeItemModelsIterator(
        CacheDatabase cache,
        Gs2InventoryRestClient client,
        String namespaceName,
        String inventoryName
    ) {
        this.cache = cache;
        this.client = client;
        this.namespaceName = namespaceName;
        this.inventoryName = inventoryName;
        this.last = false;
        this.result = new ArrayList<>();

        this.fetchSize = null;
        this.load();
    }

    private void load() {
        String parentKey = io.gs2.inventory.domain.model.InventoryModelDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.inventoryName != null ? this.inventoryName.toString() : null,
            "ItemModel"
        );
        String listParentKey = parentKey;
        if (this.cache.isListCached(
                listParentKey,
                ItemModel.class
        )) {
            this.result = this.cache.list(
                    parentKey,
                    ItemModel.class
            ).stream()
                .collect(Collectors.toList());
            this.last = true;
        } else {

            DescribeItemModelsResult r = this.client.describeItemModels(
                new DescribeItemModelsRequest()
                    .withNamespaceName(this.namespaceName)
                    .withInventoryName(this.inventoryName)
                );
            this.result = r.getItems();
            this.last = true;
            for (ItemModel item : this.result) {
                this.cache.put(
                        parentKey,
                        io.gs2.inventory.domain.model.ItemModelDomain.createCacheKey(
                                item.getName() != null ? item.getName().toString() : null
                        ),
                        item,
                        System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                );
            }

            if (this.last) {
                this.cache.listCached(
                        listParentKey,
                        ItemModel.class
                );
            }
        }
    }

    @Override
    public boolean hasNext(

    ) {
        return this.result.size() != 0 || !this.last;
    }

    @Override
    public ItemModel next(

    ) {
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        if (this.result.size() == 0) {
            return null;
        }
        ItemModel ret = this.result.get(0);
        this.result = this.result.subList(1, this.result.size());
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        return ret;
    }

    @Override
    public Iterator<ItemModel> iterator() {
        return this;
    }
}
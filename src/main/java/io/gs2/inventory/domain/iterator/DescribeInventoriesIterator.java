
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

public class DescribeInventoriesIterator implements Iterator<Inventory>, Iterable<Inventory> {
    CacheDatabase cache;
    Gs2InventoryRestClient client;
    String namespaceName;
    AccessToken accessToken;
    String pageToken;
    boolean last;
    List<Inventory> result;

    Integer fetchSize;

    public DescribeInventoriesIterator(
        CacheDatabase cache,
        Gs2InventoryRestClient client,
        String namespaceName,
        AccessToken accessToken
    ) {
        this.cache = cache;
        this.client = client;
        this.namespaceName = namespaceName;
        this.accessToken = accessToken;
        this.pageToken = null;
        this.last = false;
        this.result = new ArrayList<>();

        this.fetchSize = null;
        this.load();
    }

    private void load() {
        String parentKey = io.gs2.inventory.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.accessToken != null && this.accessToken.getUserId() != null ? this.accessToken.getUserId().toString() : null,
            "Inventory"
        );
        String listParentKey = parentKey;
        if (this.cache.isListCached(
                listParentKey,
                Inventory.class
        )) {
            this.result = this.cache.list(
                    parentKey,
                    Inventory.class
            ).stream()
                .collect(Collectors.toList());
            this.pageToken = null;
            this.last = true;
        } else {

            DescribeInventoriesResult r = this.client.describeInventories(
                new DescribeInventoriesRequest()
                    .withNamespaceName(this.namespaceName)
                    .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
                    .withPageToken(this.pageToken)
                    .withLimit(this.fetchSize)
                );
            this.result = r.getItems();
            this.pageToken = r.getNextPageToken();
            this.last = this.pageToken == null;
            for (Inventory item : this.result) {
                this.cache.put(
                        parentKey,
                        io.gs2.inventory.domain.model.InventoryDomain.createCacheKey(
                                item.getInventoryName() != null ? item.getInventoryName().toString() : null
                        ),
                        item,
                        System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                );
            }

            if (this.last) {
                this.cache.listCached(
                        listParentKey,
                        Inventory.class
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
    public Inventory next(

    ) {
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        if (this.result.size() == 0) {
            return null;
        }
        Inventory ret = this.result.get(0);
        this.result = this.result.subList(1, this.result.size());
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        return ret;
    }

    @Override
    public Iterator<Inventory> iterator() {
        return this;
    }
}

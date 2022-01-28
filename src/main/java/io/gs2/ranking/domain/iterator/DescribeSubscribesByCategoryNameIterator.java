
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
package io.gs2.ranking.domain.iterator;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.ranking.Gs2RankingRestClient;
import io.gs2.ranking.model.*;
import io.gs2.ranking.request.*;
import io.gs2.ranking.result.*;

import java.util.*;
import java.util.stream.Collectors;

public class DescribeSubscribesByCategoryNameIterator implements Iterator<SubscribeUser>, Iterable<SubscribeUser> {
    CacheDatabase cache;
    Gs2RankingRestClient client;
    String namespaceName;
    String categoryName;
    AccessToken accessToken;
    boolean last;
    List<SubscribeUser> result;

    Integer fetchSize;

    public DescribeSubscribesByCategoryNameIterator(
        CacheDatabase cache,
        Gs2RankingRestClient client,
        String namespaceName,
        String categoryName,
        AccessToken accessToken
    ) {
        this.cache = cache;
        this.client = client;
        this.namespaceName = namespaceName;
        this.categoryName = categoryName;
        this.accessToken = accessToken;
        this.last = false;
        this.result = new ArrayList<>();

        this.fetchSize = null;
        this.load();
    }

    private void load() {
        String parentKey = io.gs2.ranking.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.accessToken != null && this.accessToken.getUserId() != null ? this.accessToken.getUserId().toString() : null,
            "SubscribeUser"
        );
        String listParentKey = parentKey;
        if (this.cache.isListCached(
                listParentKey,
                SubscribeUser.class
        )) {
            this.result = this.cache.list(
                    parentKey,
                    SubscribeUser.class
            ).stream()
                .filter(item -> this.categoryName == null || item.getCategoryName().equals(this.categoryName))
                .collect(Collectors.toList());
            this.last = true;
        } else {

            DescribeSubscribesByCategoryNameResult r = this.client.describeSubscribesByCategoryName(
                new DescribeSubscribesByCategoryNameRequest()
                    .withNamespaceName(this.namespaceName)
                    .withCategoryName(this.categoryName)
                    .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
                );
            this.result = r.getItems();
            this.last = true;
            for (SubscribeUser item : this.result) {
                this.cache.put(
                        parentKey,
                        io.gs2.ranking.domain.model.SubscribeUserDomain.createCacheKey(
                                item.getCategoryName() != null ? item.getCategoryName().toString() : null,
                                item.getTargetUserId() != null ? item.getTargetUserId().toString() : null
                        ),
                        item,
                        System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                );
            }

            if (this.last) {
                this.cache.listCached(
                        listParentKey,
                        SubscribeUser.class
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
    public SubscribeUser next(

    ) {
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        if (this.result.size() == 0) {
            return null;
        }
        SubscribeUser ret = this.result.get(0);
        this.result = this.result.subList(1, this.result.size());
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        return ret;
    }

    @Override
    public Iterator<SubscribeUser> iterator() {
        return this;
    }
}

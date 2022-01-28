
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
package io.gs2.showcase.domain.iterator;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.showcase.Gs2ShowcaseRestClient;
import io.gs2.showcase.model.*;
import io.gs2.showcase.request.*;
import io.gs2.showcase.result.*;

import java.util.*;
import java.util.stream.Collectors;

public class DescribeShowcasesByUserIdIterator implements Iterator<Showcase>, Iterable<Showcase> {
    CacheDatabase cache;
    Gs2ShowcaseRestClient client;
    String namespaceName;
    String userId;
    boolean last;
    List<Showcase> result;

    Integer fetchSize;

    public DescribeShowcasesByUserIdIterator(
        CacheDatabase cache,
        Gs2ShowcaseRestClient client,
        String namespaceName,
        String userId
    ) {
        this.cache = cache;
        this.client = client;
        this.namespaceName = namespaceName;
        this.userId = userId;
        this.last = false;
        this.result = new ArrayList<>();

        this.fetchSize = null;
        this.load();
    }

    private void load() {
        String parentKey = io.gs2.showcase.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "Showcase"
        );
        String listParentKey = parentKey;
        if (this.cache.isListCached(
                listParentKey,
                Showcase.class
        )) {
            this.result = this.cache.list(
                    parentKey,
                    Showcase.class
            ).stream()
                .collect(Collectors.toList());
            this.last = true;
        } else {

            DescribeShowcasesByUserIdResult r = this.client.describeShowcasesByUserId(
                new DescribeShowcasesByUserIdRequest()
                    .withNamespaceName(this.namespaceName)
                    .withUserId(this.userId)
                );
            this.result = r.getItems();
            this.last = true;
            for (Showcase item : this.result) {
                this.cache.put(
                        parentKey,
                        io.gs2.showcase.domain.model.ShowcaseDomain.createCacheKey(
                                item.getName() != null ? item.getName().toString() : null
                        ),
                        item,
                        System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                );
            }

            if (this.last) {
                this.cache.listCached(
                        listParentKey,
                        Showcase.class
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
    public Showcase next(

    ) {
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        if (this.result.size() == 0) {
            return null;
        }
        Showcase ret = this.result.get(0);
        this.result = this.result.subList(1, this.result.size());
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        return ret;
    }

    @Override
    public Iterator<Showcase> iterator() {
        return this;
    }
}

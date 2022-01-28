
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
package io.gs2.formation.domain.iterator;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.formation.Gs2FormationRestClient;
import io.gs2.formation.model.*;
import io.gs2.formation.request.*;
import io.gs2.formation.result.*;

import java.util.*;
import java.util.stream.Collectors;

public class DescribeMoldsByUserIdIterator implements Iterator<Mold>, Iterable<Mold> {
    CacheDatabase cache;
    Gs2FormationRestClient client;
    String namespaceName;
    String userId;
    String pageToken;
    boolean last;
    List<Mold> result;

    Integer fetchSize;

    public DescribeMoldsByUserIdIterator(
        CacheDatabase cache,
        Gs2FormationRestClient client,
        String namespaceName,
        String userId
    ) {
        this.cache = cache;
        this.client = client;
        this.namespaceName = namespaceName;
        this.userId = userId;
        this.pageToken = null;
        this.last = false;
        this.result = new ArrayList<>();

        this.fetchSize = null;
        this.load();
    }

    private void load() {
        String parentKey = io.gs2.formation.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "Mold"
        );
        String listParentKey = parentKey;
        if (this.cache.isListCached(
                listParentKey,
                Mold.class
        )) {
            this.result = this.cache.list(
                    parentKey,
                    Mold.class
            ).stream()
                .collect(Collectors.toList());
            this.pageToken = null;
            this.last = true;
        } else {

            DescribeMoldsByUserIdResult r = this.client.describeMoldsByUserId(
                new DescribeMoldsByUserIdRequest()
                    .withNamespaceName(this.namespaceName)
                    .withUserId(this.userId)
                    .withPageToken(this.pageToken)
                    .withLimit(this.fetchSize)
                );
            this.result = r.getItems();
            this.pageToken = r.getNextPageToken();
            this.last = this.pageToken == null;
            for (Mold item : this.result) {
                this.cache.put(
                        parentKey,
                        io.gs2.formation.domain.model.MoldDomain.createCacheKey(
                                item.getName() != null ? item.getName().toString() : null
                        ),
                        item,
                        System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                );
            }

            if (this.last) {
                this.cache.listCached(
                        listParentKey,
                        Mold.class
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
    public Mold next(

    ) {
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        if (this.result.size() == 0) {
            return null;
        }
        Mold ret = this.result.get(0);
        this.result = this.result.subList(1, this.result.size());
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        return ret;
    }

    @Override
    public Iterator<Mold> iterator() {
        return this;
    }
}

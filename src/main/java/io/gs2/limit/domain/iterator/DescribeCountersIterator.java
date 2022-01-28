
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
package io.gs2.limit.domain.iterator;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.limit.Gs2LimitRestClient;
import io.gs2.limit.model.*;
import io.gs2.limit.request.*;
import io.gs2.limit.result.*;

import java.util.*;
import java.util.stream.Collectors;

public class DescribeCountersIterator implements Iterator<Counter>, Iterable<Counter> {
    CacheDatabase cache;
    Gs2LimitRestClient client;
    String namespaceName;
    AccessToken accessToken;
    String limitName;
    String pageToken;
    boolean last;
    List<Counter> result;

    Integer fetchSize;

    public DescribeCountersIterator(
        CacheDatabase cache,
        Gs2LimitRestClient client,
        String namespaceName,
        AccessToken accessToken,
        String limitName
    ) {
        this.cache = cache;
        this.client = client;
        this.namespaceName = namespaceName;
        this.accessToken = accessToken;
        this.limitName = limitName;
        this.pageToken = null;
        this.last = false;
        this.result = new ArrayList<>();

        this.fetchSize = null;
        this.load();
    }

    private void load() {
        String parentKey = io.gs2.limit.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.accessToken != null && this.accessToken.getUserId() != null ? this.accessToken.getUserId().toString() : null,
            "Counter"
        );
        String listParentKey = parentKey;
        if (this.cache.isListCached(
                listParentKey,
                Counter.class
        )) {
            this.result = this.cache.list(
                    parentKey,
                    Counter.class
            ).stream()
                .filter(item -> this.limitName == null || item.getLimitName().equals(this.limitName))
                .collect(Collectors.toList());
            this.pageToken = null;
            this.last = true;
        } else {

            DescribeCountersResult r = this.client.describeCounters(
                new DescribeCountersRequest()
                    .withNamespaceName(this.namespaceName)
                    .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
                    .withLimitName(this.limitName)
                    .withPageToken(this.pageToken)
                    .withLimit(this.fetchSize)
                );
            this.result = r.getItems();
            this.pageToken = r.getNextPageToken();
            this.last = this.pageToken == null;
            for (Counter item : this.result) {
                this.cache.put(
                        parentKey,
                        io.gs2.limit.domain.model.CounterDomain.createCacheKey(
                                item.getLimitName() != null ? item.getLimitName().toString() : null,
                                item.getName() != null ? item.getName().toString() : null
                        ),
                        item,
                        System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                );
            }

            if (this.last) {
                this.cache.listCached(
                        listParentKey,
                        Counter.class
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
    public Counter next(

    ) {
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        if (this.result.size() == 0) {
            return null;
        }
        Counter ret = this.result.get(0);
        this.result = this.result.subList(1, this.result.size());
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        return ret;
    }

    @Override
    public Iterator<Counter> iterator() {
        return this;
    }
}

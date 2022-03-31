
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
package io.gs2.identifier.domain.iterator;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.identifier.Gs2IdentifierRestClient;
import io.gs2.identifier.model.*;
import io.gs2.identifier.request.*;
import io.gs2.identifier.result.*;

import java.util.*;
import java.util.stream.Collectors;

public class DescribeIdentifiersIterator implements Iterator<Identifier>, Iterable<Identifier> {
    CacheDatabase cache;
    Gs2IdentifierRestClient client;
    String userName;
    String pageToken;
    boolean last;
    List<Identifier> result;

    Integer fetchSize;

    public DescribeIdentifiersIterator(
        CacheDatabase cache,
        Gs2IdentifierRestClient client,
        String userName
    ) {
        this.cache = cache;
        this.client = client;
        this.userName = userName;
        this.pageToken = null;
        this.last = false;
        this.result = new ArrayList<>();

        this.fetchSize = null;
        this.load();
    }

    private void load() {
        String parentKey = io.gs2.identifier.domain.model.UserDomain.createCacheParentKey(
            this.userName != null ? this.userName.toString() : null,
            "Identifier"
        );
        String listParentKey = parentKey;
        if (this.cache.isListCached(
                listParentKey,
                Identifier.class
        )) {
            this.result = this.cache.list(
                    parentKey,
                    Identifier.class
            ).stream()
                .collect(Collectors.toList());
            this.pageToken = null;
            this.last = true;
        } else {

            DescribeIdentifiersResult r = this.client.describeIdentifiers(
                new DescribeIdentifiersRequest()
                    .withUserName(this.userName)
                    .withPageToken(this.pageToken)
                    .withLimit(this.fetchSize)
                );
            this.result = r.getItems();
            this.pageToken = r.getNextPageToken();
            this.last = this.pageToken == null;
            for (Identifier item : this.result) {
                this.cache.put(
                        parentKey,
                        io.gs2.identifier.domain.model.IdentifierDomain.createCacheKey(
                                item.getUserName() != null ? item.getUserName().toString() : null,
                                item.getClientId() != null ? item.getClientId().toString() : null
                        ),
                        item,
                        System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                );
            }

            if (this.last) {
                this.cache.listCached(
                        listParentKey,
                        Identifier.class
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
    public Identifier next(

    ) {
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        if (this.result.size() == 0) {
            return null;
        }
        Identifier ret = this.result.get(0);
        this.result = this.result.subList(1, this.result.size());
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        return ret;
    }

    @Override
    public Iterator<Identifier> iterator() {
        return this;
    }
}
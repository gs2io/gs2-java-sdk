
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

public class DescribePasswordsIterator implements Iterator<Password>, Iterable<Password> {
    CacheDatabase cache;
    Gs2IdentifierRestClient client;
    String userName;
    String pageToken;
    boolean last;
    List<Password> result;

    Integer fetchSize;

    public DescribePasswordsIterator(
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
            "Password"
        );
        String listParentKey = parentKey;
        if (this.cache.isListCached(
                listParentKey,
                Password.class
        )) {
            this.result = this.cache.list(
                    parentKey,
                    Password.class
            ).stream()
                .collect(Collectors.toList());
            this.pageToken = null;
            this.last = true;
        } else {

            DescribePasswordsResult r = this.client.describePasswords(
                new DescribePasswordsRequest()
                    .withUserName(this.userName)
                    .withPageToken(this.pageToken)
                    .withLimit(this.fetchSize)
                );
            this.result = r.getItems();
            this.pageToken = r.getNextPageToken();
            this.last = this.pageToken == null;
            for (Password item : this.result) {
                this.cache.put(
                        parentKey,
                        io.gs2.identifier.domain.model.PasswordDomain.createCacheKey(
                                item.getUserName() != null ? item.getUserName().toString() : null
                        ),
                        item,
                        System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                );
            }

            if (this.last) {
                this.cache.listCached(
                        listParentKey,
                        Password.class
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
    public Password next(

    ) {
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        if (this.result.size() == 0) {
            return null;
        }
        Password ret = this.result.get(0);
        this.result = this.result.subList(1, this.result.size());
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        return ret;
    }

    @Override
    public Iterator<Password> iterator() {
        return this;
    }
}

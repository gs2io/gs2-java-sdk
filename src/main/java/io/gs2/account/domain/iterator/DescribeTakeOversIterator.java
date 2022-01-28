
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
package io.gs2.account.domain.iterator;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.account.Gs2AccountRestClient;
import io.gs2.account.model.*;
import io.gs2.account.request.*;
import io.gs2.account.result.*;

import java.util.*;
import java.util.stream.Collectors;

public class DescribeTakeOversIterator implements Iterator<TakeOver>, Iterable<TakeOver> {
    CacheDatabase cache;
    Gs2AccountRestClient client;
    String namespaceName;
    AccessToken accessToken;
    String pageToken;
    boolean last;
    List<TakeOver> result;

    Integer fetchSize;

    public DescribeTakeOversIterator(
        CacheDatabase cache,
        Gs2AccountRestClient client,
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
        String parentKey = io.gs2.account.domain.model.AccountDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.accessToken != null && this.accessToken.getUserId() != null ? this.accessToken.getUserId().toString() : null,
            "TakeOver"
        );
        String listParentKey = parentKey;
        if (this.cache.isListCached(
                listParentKey,
                TakeOver.class
        )) {
            this.result = this.cache.list(
                    parentKey,
                    TakeOver.class
            ).stream()
                .collect(Collectors.toList());
            this.pageToken = null;
            this.last = true;
        } else {

            DescribeTakeOversResult r = this.client.describeTakeOvers(
                new DescribeTakeOversRequest()
                    .withNamespaceName(this.namespaceName)
                    .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
                    .withPageToken(this.pageToken)
                    .withLimit(this.fetchSize)
                );
            this.result = r.getItems();
            this.pageToken = r.getNextPageToken();
            this.last = this.pageToken == null;
            for (TakeOver item : this.result) {
                this.cache.put(
                        parentKey,
                        io.gs2.account.domain.model.TakeOverDomain.createCacheKey(
                                item.getType() != null ? item.getType().toString() : null
                        ),
                        item,
                        System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                );
            }

            if (this.last) {
                this.cache.listCached(
                        listParentKey,
                        TakeOver.class
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
    public TakeOver next(

    ) {
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        if (this.result.size() == 0) {
            return null;
        }
        TakeOver ret = this.result.get(0);
        this.result = this.result.subList(1, this.result.size());
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        return ret;
    }

    @Override
    public Iterator<TakeOver> iterator() {
        return this;
    }
}

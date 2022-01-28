
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
package io.gs2.dictionary.domain.iterator;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.dictionary.Gs2DictionaryRestClient;
import io.gs2.dictionary.model.*;
import io.gs2.dictionary.request.*;
import io.gs2.dictionary.result.*;

import java.util.*;
import java.util.stream.Collectors;

public class DescribeEntryModelsIterator implements Iterator<EntryModel>, Iterable<EntryModel> {
    CacheDatabase cache;
    Gs2DictionaryRestClient client;
    String namespaceName;
    boolean last;
    List<EntryModel> result;

    Integer fetchSize;

    public DescribeEntryModelsIterator(
        CacheDatabase cache,
        Gs2DictionaryRestClient client,
        String namespaceName
    ) {
        this.cache = cache;
        this.client = client;
        this.namespaceName = namespaceName;
        this.last = false;
        this.result = new ArrayList<>();

        this.fetchSize = null;
        this.load();
    }

    private void load() {
        String parentKey = io.gs2.dictionary.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "EntryModel"
        );
        String listParentKey = parentKey;
        if (this.cache.isListCached(
                listParentKey,
                EntryModel.class
        )) {
            this.result = this.cache.list(
                    parentKey,
                    EntryModel.class
            ).stream()
                .collect(Collectors.toList());
            this.last = true;
        } else {

            DescribeEntryModelsResult r = this.client.describeEntryModels(
                new DescribeEntryModelsRequest()
                    .withNamespaceName(this.namespaceName)
                );
            this.result = r.getItems();
            this.last = true;
            for (EntryModel item : this.result) {
                this.cache.put(
                        parentKey,
                        io.gs2.dictionary.domain.model.EntryModelDomain.createCacheKey(
                                item.getName() != null ? item.getName().toString() : null
                        ),
                        item,
                        System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                );
            }

            if (this.last) {
                this.cache.listCached(
                        listParentKey,
                        EntryModel.class
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
    public EntryModel next(

    ) {
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        if (this.result.size() == 0) {
            return null;
        }
        EntryModel ret = this.result.get(0);
        this.result = this.result.subList(1, this.result.size());
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        return ret;
    }

    @Override
    public Iterator<EntryModel> iterator() {
        return this;
    }
}


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
package io.gs2.script.domain.iterator;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.script.Gs2ScriptRestClient;
import io.gs2.script.model.*;
import io.gs2.script.request.*;
import io.gs2.script.result.*;

import java.util.*;
import java.util.stream.Collectors;

public class DescribeScriptsIterator implements Iterator<Script>, Iterable<Script> {
    CacheDatabase cache;
    Gs2ScriptRestClient client;
    String namespaceName;
    String pageToken;
    boolean last;
    List<Script> result;

    Integer fetchSize;

    public DescribeScriptsIterator(
        CacheDatabase cache,
        Gs2ScriptRestClient client,
        String namespaceName
    ) {
        this.cache = cache;
        this.client = client;
        this.namespaceName = namespaceName;
        this.pageToken = null;
        this.last = false;
        this.result = new ArrayList<>();

        this.fetchSize = null;
        this.load();
    }

    private void load() {
        String parentKey = io.gs2.script.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "Script"
        );
        String listParentKey = parentKey;
        if (this.cache.isListCached(
                listParentKey,
                Script.class
        )) {
            this.result = this.cache.list(
                    parentKey,
                    Script.class
            ).stream()
                .collect(Collectors.toList());
            this.pageToken = null;
            this.last = true;
        } else {

            DescribeScriptsResult r = this.client.describeScripts(
                new DescribeScriptsRequest()
                    .withNamespaceName(this.namespaceName)
                    .withPageToken(this.pageToken)
                    .withLimit(this.fetchSize)
                );
            this.result = r.getItems();
            this.pageToken = r.getNextPageToken();
            this.last = this.pageToken == null;
            for (Script item : this.result) {
                this.cache.put(
                        parentKey,
                        io.gs2.script.domain.model.ScriptDomain.createCacheKey(
                                item.getName() != null ? item.getName().toString() : null
                        ),
                        item,
                        System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                );
            }

            if (this.last) {
                this.cache.listCached(
                        listParentKey,
                        Script.class
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
    public Script next(

    ) {
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        if (this.result.size() == 0) {
            return null;
        }
        Script ret = this.result.get(0);
        this.result = this.result.subList(1, this.result.size());
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        return ret;
    }

    @Override
    public Iterator<Script> iterator() {
        return this;
    }
}
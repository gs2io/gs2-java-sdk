
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

public class DescribeFormsIterator implements Iterator<Form>, Iterable<Form> {
    CacheDatabase cache;
    Gs2FormationRestClient client;
    String namespaceName;
    String moldName;
    AccessToken accessToken;
    String pageToken;
    boolean last;
    List<Form> result;

    Integer fetchSize;

    public DescribeFormsIterator(
        CacheDatabase cache,
        Gs2FormationRestClient client,
        String namespaceName,
        String moldName,
        AccessToken accessToken
    ) {
        this.cache = cache;
        this.client = client;
        this.namespaceName = namespaceName;
        this.moldName = moldName;
        this.accessToken = accessToken;
        this.pageToken = null;
        this.last = false;
        this.result = new ArrayList<>();

        this.fetchSize = null;
        this.load();
    }

    private void load() {
        String parentKey = io.gs2.formation.domain.model.MoldDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.accessToken != null && this.accessToken.getUserId() != null ? this.accessToken.getUserId().toString() : null,
            this.moldName != null ? this.moldName.toString() : null,
            "Form"
        );
        String listParentKey = parentKey;
        if (this.cache.isListCached(
                listParentKey,
                Form.class
        )) {
            this.result = this.cache.list(
                    parentKey,
                    Form.class
            ).stream()
                .collect(Collectors.toList());
            this.pageToken = null;
            this.last = true;
        } else {

            DescribeFormsResult r = this.client.describeForms(
                new DescribeFormsRequest()
                    .withNamespaceName(this.namespaceName)
                    .withMoldName(this.moldName)
                    .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
                    .withPageToken(this.pageToken)
                    .withLimit(this.fetchSize)
                );
            this.result = r.getItems();
            this.pageToken = r.getNextPageToken();
            this.last = this.pageToken == null;
            for (Form item : this.result) {
                this.cache.put(
                        parentKey,
                        io.gs2.formation.domain.model.FormDomain.createCacheKey(
                                item.getIndex() != null ? item.getIndex().toString() : null
                        ),
                        item,
                        System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                );
            }

            if (this.last) {
                this.cache.listCached(
                        listParentKey,
                        Form.class
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
    public Form next(

    ) {
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        if (this.result.size() == 0) {
            return null;
        }
        Form ret = this.result.get(0);
        this.result = this.result.subList(1, this.result.size());
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        return ret;
    }

    @Override
    public Iterator<Form> iterator() {
        return this;
    }
}

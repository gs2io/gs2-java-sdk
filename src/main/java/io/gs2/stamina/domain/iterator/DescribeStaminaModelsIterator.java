
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
package io.gs2.stamina.domain.iterator;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.stamina.Gs2StaminaRestClient;
import io.gs2.stamina.model.*;
import io.gs2.stamina.request.*;
import io.gs2.stamina.result.*;

import java.util.*;
import java.util.stream.Collectors;

public class DescribeStaminaModelsIterator implements Iterator<StaminaModel>, Iterable<StaminaModel> {
    CacheDatabase cache;
    Gs2StaminaRestClient client;
    String namespaceName;
    boolean last;
    List<StaminaModel> result;

    Integer fetchSize;

    public DescribeStaminaModelsIterator(
        CacheDatabase cache,
        Gs2StaminaRestClient client,
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
        String parentKey = io.gs2.stamina.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "StaminaModel"
        );
        String listParentKey = parentKey;
        if (this.cache.isListCached(
                listParentKey,
                StaminaModel.class
        )) {
            this.result = this.cache.list(
                    parentKey,
                    StaminaModel.class
            ).stream()
                .collect(Collectors.toList());
            this.last = true;
        } else {

            DescribeStaminaModelsResult r = this.client.describeStaminaModels(
                new DescribeStaminaModelsRequest()
                    .withNamespaceName(this.namespaceName)
                );
            this.result = r.getItems();
            this.last = true;
            for (StaminaModel item : this.result) {
                this.cache.put(
                        parentKey,
                        io.gs2.stamina.domain.model.StaminaModelDomain.createCacheKey(
                                item.getName() != null ? item.getName().toString() : null
                        ),
                        item,
                        System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                );
            }

            if (this.last) {
                this.cache.listCached(
                        listParentKey,
                        StaminaModel.class
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
    public StaminaModel next(

    ) {
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        if (this.result.size() == 0) {
            return null;
        }
        StaminaModel ret = this.result.get(0);
        this.result = this.result.subList(1, this.result.size());
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        return ret;
    }

    @Override
    public Iterator<StaminaModel> iterator() {
        return this;
    }
}

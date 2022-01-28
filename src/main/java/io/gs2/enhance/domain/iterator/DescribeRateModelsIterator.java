
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
package io.gs2.enhance.domain.iterator;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.enhance.Gs2EnhanceRestClient;
import io.gs2.enhance.model.*;
import io.gs2.enhance.request.*;
import io.gs2.enhance.result.*;

import java.util.*;
import java.util.stream.Collectors;

public class DescribeRateModelsIterator implements Iterator<RateModel>, Iterable<RateModel> {
    CacheDatabase cache;
    Gs2EnhanceRestClient client;
    String namespaceName;
    boolean last;
    List<RateModel> result;

    Integer fetchSize;

    public DescribeRateModelsIterator(
        CacheDatabase cache,
        Gs2EnhanceRestClient client,
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
        String parentKey = io.gs2.enhance.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "RateModel"
        );
        String listParentKey = parentKey;
        if (this.cache.isListCached(
                listParentKey,
                RateModel.class
        )) {
            this.result = this.cache.list(
                    parentKey,
                    RateModel.class
            ).stream()
                .collect(Collectors.toList());
            this.last = true;
        } else {

            DescribeRateModelsResult r = this.client.describeRateModels(
                new DescribeRateModelsRequest()
                    .withNamespaceName(this.namespaceName)
                );
            this.result = r.getItems();
            this.last = true;
            for (RateModel item : this.result) {
                this.cache.put(
                        parentKey,
                        io.gs2.enhance.domain.model.RateModelDomain.createCacheKey(
                                item.getName() != null ? item.getName().toString() : null
                        ),
                        item,
                        System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                );
            }

            if (this.last) {
                this.cache.listCached(
                        listParentKey,
                        RateModel.class
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
    public RateModel next(

    ) {
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        if (this.result.size() == 0) {
            return null;
        }
        RateModel ret = this.result.get(0);
        this.result = this.result.subList(1, this.result.size());
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        return ret;
    }

    @Override
    public Iterator<RateModel> iterator() {
        return this;
    }
}

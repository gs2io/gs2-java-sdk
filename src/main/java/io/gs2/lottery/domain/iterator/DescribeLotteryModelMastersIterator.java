
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
package io.gs2.lottery.domain.iterator;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.lottery.Gs2LotteryRestClient;
import io.gs2.lottery.model.*;
import io.gs2.lottery.request.*;
import io.gs2.lottery.result.*;

import java.util.*;
import java.util.stream.Collectors;

public class DescribeLotteryModelMastersIterator implements Iterator<LotteryModelMaster>, Iterable<LotteryModelMaster> {
    CacheDatabase cache;
    Gs2LotteryRestClient client;
    String namespaceName;
    String pageToken;
    boolean last;
    List<LotteryModelMaster> result;

    Integer fetchSize;

    public DescribeLotteryModelMastersIterator(
        CacheDatabase cache,
        Gs2LotteryRestClient client,
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
        String parentKey = io.gs2.lottery.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "LotteryModelMaster"
        );
        String listParentKey = parentKey;
        if (this.cache.isListCached(
                listParentKey,
                LotteryModelMaster.class
        )) {
            this.result = this.cache.list(
                    parentKey,
                    LotteryModelMaster.class
            ).stream()
                .collect(Collectors.toList());
            this.pageToken = null;
            this.last = true;
        } else {

            DescribeLotteryModelMastersResult r = this.client.describeLotteryModelMasters(
                new DescribeLotteryModelMastersRequest()
                    .withNamespaceName(this.namespaceName)
                    .withPageToken(this.pageToken)
                    .withLimit(this.fetchSize)
                );
            this.result = r.getItems();
            this.pageToken = r.getNextPageToken();
            this.last = this.pageToken == null;
            for (LotteryModelMaster item : this.result) {
                this.cache.put(
                        parentKey,
                        io.gs2.lottery.domain.model.LotteryModelMasterDomain.createCacheKey(
                                item.getName() != null ? item.getName().toString() : null
                        ),
                        item,
                        System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                );
            }

            if (this.last) {
                this.cache.listCached(
                        listParentKey,
                        LotteryModelMaster.class
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
    public LotteryModelMaster next(

    ) {
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        if (this.result.size() == 0) {
            return null;
        }
        LotteryModelMaster ret = this.result.get(0);
        this.result = this.result.subList(1, this.result.size());
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        return ret;
    }

    @Override
    public Iterator<LotteryModelMaster> iterator() {
        return this;
    }
}


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

public class DescribeProbabilitiesByUserIdIterator implements Iterator<Probability>, Iterable<Probability> {
    CacheDatabase cache;
    Gs2LotteryRestClient client;
    String namespaceName;
    String lotteryName;
    String userId;
    boolean last;
    List<Probability> result;

    Integer fetchSize;

    public DescribeProbabilitiesByUserIdIterator(
        CacheDatabase cache,
        Gs2LotteryRestClient client,
        String namespaceName,
        String lotteryName,
        String userId
    ) {
        this.cache = cache;
        this.client = client;
        this.namespaceName = namespaceName;
        this.lotteryName = lotteryName;
        this.userId = userId;
        this.last = false;
        this.result = new ArrayList<>();

        this.fetchSize = null;
        this.load();
    }

    private void load() {
        String parentKey = io.gs2.lottery.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "Probability"
        );
        String listParentKey = parentKey;
        if (this.cache.isListCached(
                listParentKey,
                Probability.class
        )) {
            this.result = this.cache.list(
                    listParentKey,
                    Probability.class
            ).stream()
                .collect(Collectors.toList());
            this.last = true;
        } else {

            DescribeProbabilitiesByUserIdResult r = this.client.describeProbabilitiesByUserId(
                new DescribeProbabilitiesByUserIdRequest()
                    .withNamespaceName(this.namespaceName)
                    .withLotteryName(this.lotteryName)
                    .withUserId(this.userId)
                );
            this.result = r.getItems();
            this.last = true;
            for (Probability item : this.result) {
                this.cache.put(
                        listParentKey,
                        io.gs2.lottery.domain.model.ProbabilityDomain.createCacheKey(
                        ),
                        item,
                        System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                );
            }

            if (this.last) {
                this.cache.listCached(
                        listParentKey,
                        Probability.class
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
    public Probability next(

    ) {
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        if (this.result.size() == 0) {
            return null;
        }
        Probability ret = this.result.get(0);
        this.result = this.result.subList(1, this.result.size());
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        return ret;
    }

    @Override
    public Iterator<Probability> iterator() {
        return this;
    }
}
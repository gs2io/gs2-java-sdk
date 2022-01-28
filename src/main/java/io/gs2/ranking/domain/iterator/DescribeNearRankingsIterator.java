
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
package io.gs2.ranking.domain.iterator;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.ranking.Gs2RankingRestClient;
import io.gs2.ranking.model.*;
import io.gs2.ranking.request.*;
import io.gs2.ranking.result.*;

import java.util.*;
import java.util.stream.Collectors;

public class DescribeNearRankingsIterator implements Iterator<Ranking>, Iterable<Ranking> {
    CacheDatabase cache;
    Gs2RankingRestClient client;
    String namespaceName;
    String categoryName;
    Long score;
    boolean last;
    List<Ranking> result;

    Integer fetchSize;

    public DescribeNearRankingsIterator(
        CacheDatabase cache,
        Gs2RankingRestClient client,
        String namespaceName,
        String categoryName,
        Long score
    ) {
        this.cache = cache;
        this.client = client;
        this.namespaceName = namespaceName;
        this.categoryName = categoryName;
        this.score = score;
        this.last = false;
        this.result = new ArrayList<>();

        this.fetchSize = null;
        this.load();
    }

    private void load() {
        String parentKey = io.gs2.ranking.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "Singleton",
            "Ranking"
        );
        String listParentKey = parentKey;
        if (this.cache.isListCached(
                listParentKey,
                Ranking.class
        )) {
            this.result = this.cache.list(
                    listParentKey,
                    Ranking.class
            ).stream()
                .collect(Collectors.toList());
            this.last = true;
        } else {

            DescribeNearRankingsResult r = this.client.describeNearRankings(
                new DescribeNearRankingsRequest()
                    .withNamespaceName(this.namespaceName)
                    .withCategoryName(this.categoryName)
                    .withScore(this.score)
                );
            this.result = r.getItems();
            this.last = true;
            for (Ranking item : this.result) {
                this.cache.put(
                        listParentKey,
                        io.gs2.ranking.domain.model.RankingDomain.createCacheKey(
                                this.categoryName != null ? this.categoryName.toString() : null
                        ),
                        item,
                        System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                );
            }

            if (this.last) {
                this.cache.listCached(
                        listParentKey,
                        Ranking.class
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
    public Ranking next(

    ) {
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        if (this.result.size() == 0) {
            return null;
        }
        Ranking ret = this.result.get(0);
        this.result = this.result.subList(1, this.result.size());
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        return ret;
    }

    @Override
    public Iterator<Ranking> iterator() {
        return this;
    }
}

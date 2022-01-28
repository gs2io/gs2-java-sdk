
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

public class DescribeScoresByUserIdIterator implements Iterator<Score>, Iterable<Score> {
    CacheDatabase cache;
    Gs2RankingRestClient client;
    String namespaceName;
    String categoryName;
    String userId;
    String scorerUserId;
    String pageToken;
    boolean last;
    List<Score> result;

    Integer fetchSize;

    public DescribeScoresByUserIdIterator(
        CacheDatabase cache,
        Gs2RankingRestClient client,
        String namespaceName,
        String categoryName,
        String userId,
        String scorerUserId
    ) {
        this.cache = cache;
        this.client = client;
        this.namespaceName = namespaceName;
        this.categoryName = categoryName;
        this.userId = userId;
        this.scorerUserId = scorerUserId;
        this.pageToken = null;
        this.last = false;
        this.result = new ArrayList<>();

        this.fetchSize = null;
        this.load();
    }

    private void load() {
        String parentKey = io.gs2.ranking.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "Score"
        );
        String listParentKey = parentKey;
        if (this.cache.isListCached(
                listParentKey,
                Score.class
        )) {
            this.result = this.cache.list(
                    parentKey,
                    Score.class
            ).stream()
                .filter(item -> this.categoryName == null || item.getCategoryName().equals(this.categoryName))
                .filter(item -> this.scorerUserId == null || item.getScorerUserId().equals(this.scorerUserId))
                .collect(Collectors.toList());
            this.pageToken = null;
            this.last = true;
        } else {

            DescribeScoresByUserIdResult r = this.client.describeScoresByUserId(
                new DescribeScoresByUserIdRequest()
                    .withNamespaceName(this.namespaceName)
                    .withCategoryName(this.categoryName)
                    .withUserId(this.userId)
                    .withScorerUserId(this.scorerUserId)
                    .withPageToken(this.pageToken)
                    .withLimit(this.fetchSize)
                );
            this.result = r.getItems();
            this.pageToken = r.getNextPageToken();
            this.last = this.pageToken == null;
            for (Score item : this.result) {
                this.cache.put(
                        parentKey,
                        io.gs2.ranking.domain.model.ScoreDomain.createCacheKey(
                                item.getCategoryName() != null ? item.getCategoryName().toString() : null,
                                item.getScorerUserId() != null ? item.getScorerUserId().toString() : null,
                                item.getUniqueId() != null ? item.getUniqueId().toString() : null
                        ),
                        item,
                        System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                );
            }

            if (this.last) {
                this.cache.listCached(
                        listParentKey,
                        Score.class
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
    public Score next(

    ) {
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        if (this.result.size() == 0) {
            return null;
        }
        Score ret = this.result.get(0);
        this.result = this.result.subList(1, this.result.size());
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        return ret;
    }

    @Override
    public Iterator<Score> iterator() {
        return this;
    }
}

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
package io.gs2.ranking.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.ranking.Gs2RankingRestClient;
import io.gs2.ranking.domain.iterator.*;
import io.gs2.ranking.model.*;
import io.gs2.ranking.request.*;
import io.gs2.ranking.result.*;

import java.util.List;


public class RankingAccessTokenDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2RankingRestClient client;
    private final String namespaceName;
    private final AccessToken accessToken;
    private final String categoryName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return accessToken.getUserId();
    }
    public String getCategoryName() {
        return categoryName;
    }

    public RankingAccessTokenDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        AccessToken accessToken,
        String categoryName
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2RankingRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.accessToken = accessToken;
        this.categoryName = categoryName;
        this.parentKey = io.gs2.ranking.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "Singleton",
            "Ranking"
        );
    }

    private Ranking get(
        GetRankingRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withCategoryName(this.categoryName);
        GetRankingResult result = this.client.getRanking(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.ranking.domain.model.RankingDomain.createCacheKey(
                    request.getCategoryName() != null ? request.getCategoryName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public ScoreAccessTokenDomain putScore(
        PutScoreRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withCategoryName(this.categoryName);
        PutScoreResult result = this.client.putScore(
            request
        );
        String parentKey = io.gs2.ranking.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.accessToken != null && this.accessToken.getUserId() != null ? this.accessToken.getUserId().toString() : null,
            "Score"
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.ranking.domain.model.ScoreDomain.createCacheKey(
                    request.getCategoryName() != null ? request.getCategoryName().toString() : null,
                    result.getItem().getScorerUserId() != null ? result.getItem().getScorerUserId().toString() : null,
                    result.getItem().getUniqueId() != null ? result.getItem().getUniqueId().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        ScoreAccessTokenDomain domain = new ScoreAccessTokenDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            request.getNamespaceName(),
            this.accessToken,
            result.getItem().getCategoryName(),
            result.getItem().getScorerUserId(),
            result.getItem().getUniqueId()
        );

        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String userId,
        String categoryName,
        String childType
    )
    {
        return String.join(
            ":",
            "ranking",
            namespaceName,
            userId,
            categoryName,
            childType
        );
    }

    public static String createCacheKey(
        String categoryName
    )
    {
        return String.join(
            ":",
            categoryName
        );
    }

    public Ranking model() {
        Ranking value = cache.get(
            parentKey,
            io.gs2.ranking.domain.model.RankingDomain.createCacheKey(
                this.getCategoryName() != null ? this.getCategoryName().toString() : null
            ),
            Ranking.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetRankingRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.ranking.domain.model.RankingDomain.createCacheKey(
                        this.getCategoryName() != null ? this.getCategoryName().toString() : null
                    ),
                    Ranking.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.ranking.domain.model.RankingDomain.createCacheKey(
                this.getCategoryName() != null ? this.getCategoryName().toString() : null
            ),
            Ranking.class
        );
        }
        return value;
    }

}

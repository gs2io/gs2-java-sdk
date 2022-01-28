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


public class UserDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2RankingRestClient client;
    private final String namespaceName;
    private final String userId;

    private final String parentKey;
    String nextPageToken;
    public String getNextPageToken() {
        return this.nextPageToken;
    }
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return userId;
    }

    public UserDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String userId
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2RankingRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.userId = userId;
        this.parentKey = io.gs2.ranking.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "User"
        );
    }

    public DescribeSubscribesByCategoryNameAndUserIdIterator subscribesByCategoryName(
        String categoryName
    )
    {
        return new DescribeSubscribesByCategoryNameAndUserIdIterator(
            this.cache,
            this.client,
            this.namespaceName,
            categoryName,
            this.userId
        );
    }

    public SubscribeDomain subscribe(
        String categoryName
    ) {
        return new SubscribeDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.userId,
            categoryName
        );
    }

    public SubscribeUserDomain subscribeUser(
        String categoryName,
        String targetUserId
    ) {
        return new SubscribeUserDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.userId,
            categoryName,
            targetUserId
        );
    }

    public DescribeRankingsByUserIdIterator rankings(
        String categoryName
    )
    {
        return new DescribeRankingsByUserIdIterator(
            this.cache,
            this.client,
            this.namespaceName,
            categoryName,
            this.userId
        );
    }

    public DescribeNearRankingsIterator nearRankings(
        String categoryName,
        Long score
    )
    {
        return new DescribeNearRankingsIterator(
            this.cache,
            this.client,
            this.namespaceName,
            categoryName,
            score
        );
    }

    public RankingDomain ranking(
        String categoryName
    ) {
        return new RankingDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.userId,
            categoryName
        );
    }

    public DescribeScoresByUserIdIterator scores(
        String categoryName,
        String scorerUserId
    )
    {
        return new DescribeScoresByUserIdIterator(
            this.cache,
            this.client,
            this.namespaceName,
            categoryName,
            this.userId,
            scorerUserId
        );
    }

    public ScoreDomain score(
        String categoryName,
        String scorerUserId,
        String uniqueId
    ) {
        return new ScoreDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.userId,
            categoryName,
            scorerUserId,
            uniqueId
        );
    }

    public static String createCacheParentKey(
        String namespaceName,
        String userId,
        String childType
    )
    {
        return String.join(
            ":",
            "ranking",
            namespaceName,
            userId,
            childType
        );
    }

    public static String createCacheKey(
        String userId
    )
    {
        return String.join(
            ":",
            userId
        );
    }

}

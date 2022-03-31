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
package io.gs2.matchmaking.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.matchmaking.Gs2MatchmakingRestClient;
import io.gs2.matchmaking.domain.iterator.*;
import io.gs2.matchmaking.model.*;
import io.gs2.matchmaking.request.*;
import io.gs2.matchmaking.result.*;

import java.util.List;


public class UserAccessTokenDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2MatchmakingRestClient client;
    private final String namespaceName;
    private final AccessToken accessToken;

    private final String parentKey;
    String nextPageToken;
    public String getNextPageToken() {
        return this.nextPageToken;
    }
    String matchmakingContextToken;
    public String getMatchmakingContextToken() {
        return this.matchmakingContextToken;
    }
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return accessToken.getUserId();
    }

    public UserAccessTokenDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        AccessToken accessToken
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2MatchmakingRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.accessToken = accessToken;
        this.parentKey = io.gs2.matchmaking.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "User"
        );
    }

    public GatheringAccessTokenDomain createGathering(
        CreateGatheringRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null);
        CreateGatheringResult result = this.client.createGathering(
            request
        );
        String parentKey = io.gs2.matchmaking.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "Singleton",
            "Gathering"
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.matchmaking.domain.model.GatheringDomain.createCacheKey(
                    result.getItem().getName() != null ? result.getItem().getName().toString() : null
                ),
                result.getItem(),
                result.getItem().getExpiresAt() == null ? System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes : result.getItem().getExpiresAt()
            );
        }
        GatheringAccessTokenDomain domain = new GatheringAccessTokenDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            request.getNamespaceName(),
            this.accessToken,
            result.getItem().getName()
        );

        return domain;
    }

    public BallotAccessTokenDomain ballot(
        String ratingName,
        String gatheringName,
        Integer numberOfPlayer,
        String keyId
    ) {
        return new BallotAccessTokenDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.accessToken,
            ratingName,
            gatheringName,
            numberOfPlayer,
            keyId
        );
    }

    public DoMatchmakingIterator doMatchmaking(
        Player player
    )
    {
        return new DoMatchmakingIterator(
            this.cache,
            this.client,
            this.namespaceName,
            this.accessToken,
            player
        );
    }

    public GatheringAccessTokenDomain gathering(
        String gatheringName
    ) {
        return new GatheringAccessTokenDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.accessToken,
            gatheringName
        );
    }

    public DescribeRatingsIterator ratings(
    )
    {
        return new DescribeRatingsIterator(
            this.cache,
            this.client,
            this.namespaceName,
            this.accessToken
        );
    }

    public RatingAccessTokenDomain rating(
        String ratingName
    ) {
        return new RatingAccessTokenDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.accessToken,
            ratingName
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
            "matchmaking",
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
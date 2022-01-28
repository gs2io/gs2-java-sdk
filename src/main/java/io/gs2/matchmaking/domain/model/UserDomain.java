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


public class UserDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2MatchmakingRestClient client;
    private final String namespaceName;
    private final String userId;

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
        this.client = new Gs2MatchmakingRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.userId = userId;
        this.parentKey = io.gs2.matchmaking.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "User"
        );
    }

    public GatheringDomain createGathering(
        CreateGatheringByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId);
        CreateGatheringByUserIdResult result = this.client.createGatheringByUserId(
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
        GatheringDomain domain = new GatheringDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            request.getNamespaceName(),
            request.getUserId(),
            result.getItem().getName()
        );

        return domain;
    }

    public GatheringDomain deleteGathering(
        DeleteGatheringRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName);
        DeleteGatheringResult result = null;
        try {
            result = this.client.deleteGathering(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        String parentKey = io.gs2.matchmaking.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "Singleton",
            "Gathering"
        );
        cache.delete(
            parentKey,
            io.gs2.matchmaking.domain.model.GatheringDomain.createCacheKey(
                request.getGatheringName() != null ? request.getGatheringName().toString() : null
            ),
            Gathering.class
        );
        GatheringDomain domain = new GatheringDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            request.getNamespaceName(),
            this.userId,
            result.getItem().getName()
        );

        return domain;
    }

    public RatingDomain[] putResult(
        PutResultRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName);
        PutResultResult result = this.client.putResult(
            request
        );
        String parentKey = io.gs2.matchmaking.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "Rating"
        );
        for (Rating item : result.getItems()) {
            cache.delete(
                io.gs2.matchmaking.domain.model.UserDomain.createCacheParentKey(
                    this.namespaceName != null ? this.namespaceName.toString() : null,
                    item.getUserId(),
                    "Rating"
                ),
                io.gs2.matchmaking.domain.model.RatingDomain.createCacheKey(
                    request.getRatingName() != null ? request.getRatingName().toString() : null
                ),
                Rating.class
            );
        }
        RatingDomain[] domain = new RatingDomain[result.getItems().size()];
        for (int i=0; i<result.getItems().size(); i++)
        {
            domain[i] = new RatingDomain(
                this.cache,
                this.jobQueueDomain,
                this.stampSheetConfiguration,
                this.session,
                request.getNamespaceName(),
                result.getItems().get(i).getUserId(),
                result.getItems().get(i).getName()
            );
        }
        return domain;
    }

    public BallotDomain ballot(
        String ratingName,
        String gatheringName,
        Integer numberOfPlayer,
        String keyId
    ) {
        return new BallotDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.userId,
            ratingName,
            gatheringName,
            numberOfPlayer,
            keyId
        );
    }

    public DescribeGatheringsIterator gatherings(
    )
    {
        return new DescribeGatheringsIterator(
            this.cache,
            this.client,
            this.namespaceName
        );
    }

    public DoMatchmakingByPlayerIterator doMatchmakingByPlayer(
        Player player
    )
    {
        return new DoMatchmakingByPlayerIterator(
            this.cache,
            this.client,
            this.namespaceName,
            player
        );
    }

    public DoMatchmakingByUserIdIterator doMatchmaking(
        Player player
    )
    {
        return new DoMatchmakingByUserIdIterator(
            this.cache,
            this.client,
            this.namespaceName,
            this.userId,
            player
        );
    }

    public GatheringDomain gathering(
        String gatheringName
    ) {
        return new GatheringDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.userId,
            gatheringName
        );
    }

    public DescribeRatingsByUserIdIterator ratings(
    )
    {
        return new DescribeRatingsByUserIdIterator(
            this.cache,
            this.client,
            this.namespaceName,
            this.userId
        );
    }

    public RatingDomain rating(
        String ratingName
    ) {
        return new RatingDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.userId,
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

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


public class BallotAccessTokenDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2MatchmakingRestClient client;
    private final String namespaceName;
    private final AccessToken accessToken;
    private final String ratingName;
    private final String gatheringName;
    private final Integer numberOfPlayer;
    private final String keyId;

    private final String parentKey;
    String body;
    public String getBody() {
        return this.body;
    }
    String signature;
    public String getSignature() {
        return this.signature;
    }
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return accessToken.getUserId();
    }
    public String getRatingName() {
        return ratingName;
    }
    public String getGatheringName() {
        return gatheringName;
    }
    public Integer getNumberOfPlayer() {
        return numberOfPlayer;
    }
    public String getKeyId() {
        return keyId;
    }

    public BallotAccessTokenDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        AccessToken accessToken,
        String ratingName,
        String gatheringName,
        Integer numberOfPlayer,
        String keyId
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
        this.ratingName = ratingName;
        this.gatheringName = gatheringName;
        this.numberOfPlayer = numberOfPlayer;
        this.keyId = keyId;
        this.parentKey = io.gs2.matchmaking.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.accessToken != null && this.accessToken.getUserId() != null ? this.accessToken.getUserId().toString() : null,
            "Ballot"
        );
    }

    private BallotAccessTokenDomain get(
        GetBallotRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withRatingName(this.ratingName)
            .withGatheringName(this.gatheringName)
            .withNumberOfPlayer(this.numberOfPlayer)
            .withKeyId(this.keyId);
        GetBallotResult result = this.client.getBallot(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.matchmaking.domain.model.BallotDomain.createCacheKey(
                    request.getRatingName() != null ? request.getRatingName().toString() : null,
                    request.getGatheringName() != null ? request.getGatheringName().toString() : null,
                    request.getNumberOfPlayer() != null ? request.getNumberOfPlayer().toString() : null,
                    request.getKeyId() != null ? request.getKeyId().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        BallotAccessTokenDomain domain = this;
        domain.body = result.getBody();
        domain.signature = result.getSignature();

        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String userId,
        String ratingName,
        String gatheringName,
        String numberOfPlayer,
        String keyId,
        String childType
    )
    {
        return String.join(
            ":",
            "matchmaking",
            namespaceName,
            userId,
            ratingName,
            gatheringName,
            numberOfPlayer,
            keyId,
            childType
        );
    }

    public static String createCacheKey(
        String ratingName,
        String gatheringName,
        String numberOfPlayer,
        String keyId
    )
    {
        return String.join(
            ":",
            ratingName,
            gatheringName,
            numberOfPlayer,
            keyId
        );
    }

    public Ballot model() {
        Ballot value = cache.get(
            parentKey,
            io.gs2.matchmaking.domain.model.BallotDomain.createCacheKey(
                this.getRatingName() != null ? this.getRatingName().toString() : null,
                this.getGatheringName() != null ? this.getGatheringName().toString() : null,
                this.getNumberOfPlayer() != null ? this.getNumberOfPlayer().toString() : null,
                this.getKeyId() != null ? this.getKeyId().toString() : null
            ),
            Ballot.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetBallotRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.matchmaking.domain.model.BallotDomain.createCacheKey(
                        this.getRatingName() != null ? this.getRatingName().toString() : null,
                        this.getGatheringName() != null ? this.getGatheringName().toString() : null,
                        this.getNumberOfPlayer() != null ? this.getNumberOfPlayer().toString() : null,
                        this.getKeyId() != null ? this.getKeyId().toString() : null
                    ),
                    Ballot.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.matchmaking.domain.model.BallotDomain.createCacheKey(
                this.getRatingName() != null ? this.getRatingName().toString() : null,
                this.getGatheringName() != null ? this.getGatheringName().toString() : null,
                this.getNumberOfPlayer() != null ? this.getNumberOfPlayer().toString() : null,
                this.getKeyId() != null ? this.getKeyId().toString() : null
            ),
            Ballot.class
        );
        }
        return value;
    }

}

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


public class VoteDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2MatchmakingRestClient client;
    private final String namespaceName;
    private final String ratingName;
    private final String gatheringName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getRatingName() {
        return ratingName;
    }
    public String getGatheringName() {
        return gatheringName;
    }

    public VoteDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String ratingName,
        String gatheringName
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2MatchmakingRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.ratingName = ratingName;
        this.gatheringName = gatheringName;
        this.parentKey = io.gs2.matchmaking.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "Vote"
        );
    }

    public VoteDomain commit(
        CommitVoteRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withRatingName(this.ratingName)
            .withGatheringName(this.gatheringName);
        CommitVoteResult result = this.client.commitVote(
            request
        );
        VoteDomain domain = this;
        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String ratingName,
        String gatheringName,
        String childType
    )
    {
        return String.join(
            ":",
            "matchmaking",
            namespaceName,
            ratingName,
            gatheringName,
            childType
        );
    }

    public static String createCacheKey(
        String ratingName,
        String gatheringName
    )
    {
        return String.join(
            ":",
            ratingName,
            gatheringName
        );
    }

    public Vote model() {
        Vote value = cache.get(
            parentKey,
            io.gs2.matchmaking.domain.model.VoteDomain.createCacheKey(
                this.getRatingName() != null ? this.getRatingName().toString() : null,
                this.getGatheringName() != null ? this.getGatheringName().toString() : null
            ),
            Vote.class
        );
        return value;
    }

}

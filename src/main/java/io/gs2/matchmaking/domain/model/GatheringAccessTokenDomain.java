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


public class GatheringAccessTokenDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2MatchmakingRestClient client;
    private final String namespaceName;
    private final AccessToken accessToken;
    private final String gatheringName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return accessToken.getUserId();
    }
    public String getGatheringName() {
        return gatheringName;
    }

    public GatheringAccessTokenDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        AccessToken accessToken,
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
        this.accessToken = accessToken;
        this.gatheringName = gatheringName;
        this.parentKey = io.gs2.matchmaking.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "Singleton",
            "Gathering"
        );
    }

    public GatheringAccessTokenDomain update(
        UpdateGatheringRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withGatheringName(this.gatheringName);
        UpdateGatheringResult result = this.client.updateGathering(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.matchmaking.domain.model.GatheringDomain.createCacheKey(
                    request.getGatheringName() != null ? request.getGatheringName().toString() : null
                ),
                result.getItem(),
                result.getItem().getExpiresAt() == null ? System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes : result.getItem().getExpiresAt()
            );
        }
        GatheringAccessTokenDomain domain = this;

        return domain;
    }

    public GatheringAccessTokenDomain cancelMatchmaking(
        CancelMatchmakingRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withGatheringName(this.gatheringName);
        CancelMatchmakingResult result = null;
        try {
            result = this.client.cancelMatchmaking(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.matchmaking.domain.model.GatheringDomain.createCacheKey(
                request.getGatheringName() != null ? request.getGatheringName().toString() : null
            ),
            Gathering.class
        );
        GatheringAccessTokenDomain domain = this;

        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String userId,
        String gatheringName,
        String childType
    )
    {
        return String.join(
            ":",
            "matchmaking",
            namespaceName,
            userId,
            gatheringName,
            childType
        );
    }

    public static String createCacheKey(
        String gatheringName
    )
    {
        return String.join(
            ":",
            gatheringName
        );
    }

    public Gathering model() {
        Gathering value = cache.get(
            parentKey,
            io.gs2.matchmaking.domain.model.GatheringDomain.createCacheKey(
                this.getGatheringName() != null ? this.getGatheringName().toString() : null
            ),
            Gathering.class
        );
        return value;
    }

}

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


public class RatingDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2MatchmakingRestClient client;
    private final String namespaceName;
    private final String userId;
    private final String ratingName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return userId;
    }
    public String getRatingName() {
        return ratingName;
    }

    public RatingDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String userId,
        String ratingName
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
        this.ratingName = ratingName;
        this.parentKey = io.gs2.matchmaking.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "Rating"
        );
    }

    private Rating get(
        GetRatingByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withRatingName(this.ratingName);
        GetRatingByUserIdResult result = this.client.getRatingByUserId(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.matchmaking.domain.model.RatingDomain.createCacheKey(
                    request.getRatingName() != null ? request.getRatingName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public RatingDomain delete(
        DeleteRatingRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withRatingName(this.ratingName);
        DeleteRatingResult result = null;
        try {
            result = this.client.deleteRating(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.matchmaking.domain.model.RatingDomain.createCacheKey(
                request.getRatingName() != null ? request.getRatingName().toString() : null
            ),
            Rating.class
        );
        RatingDomain domain = this;

        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String userId,
        String ratingName,
        String childType
    )
    {
        return String.join(
            ":",
            "matchmaking",
            namespaceName,
            userId,
            ratingName,
            childType
        );
    }

    public static String createCacheKey(
        String ratingName
    )
    {
        return String.join(
            ":",
            ratingName
        );
    }

    public Rating model() {
        Rating value = cache.get(
            parentKey,
            io.gs2.matchmaking.domain.model.RatingDomain.createCacheKey(
                this.getRatingName() != null ? this.getRatingName().toString() : null
            ),
            Rating.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetRatingByUserIdRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.matchmaking.domain.model.RatingDomain.createCacheKey(
                        this.getRatingName() != null ? this.getRatingName().toString() : null
                    ),
                    Rating.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.matchmaking.domain.model.RatingDomain.createCacheKey(
                this.getRatingName() != null ? this.getRatingName().toString() : null
            ),
            Rating.class
        );
        }
        return value;
    }

}
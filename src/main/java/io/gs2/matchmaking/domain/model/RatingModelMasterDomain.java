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


public class RatingModelMasterDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2MatchmakingRestClient client;
    private final String namespaceName;
    private final String ratingName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getRatingName() {
        return ratingName;
    }

    public RatingModelMasterDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
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
        this.ratingName = ratingName;
        this.parentKey = io.gs2.matchmaking.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "RatingModelMaster"
        );
    }

    private RatingModelMaster get(
        GetRatingModelMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withRatingName(this.ratingName);
        GetRatingModelMasterResult result = this.client.getRatingModelMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.matchmaking.domain.model.RatingModelMasterDomain.createCacheKey(
                    request.getRatingName() != null ? request.getRatingName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public RatingModelMasterDomain update(
        UpdateRatingModelMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withRatingName(this.ratingName);
        UpdateRatingModelMasterResult result = this.client.updateRatingModelMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.matchmaking.domain.model.RatingModelMasterDomain.createCacheKey(
                    request.getRatingName() != null ? request.getRatingName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        RatingModelMasterDomain domain = this;

        return domain;
    }

    public RatingModelMasterDomain delete(
        DeleteRatingModelMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withRatingName(this.ratingName);
        DeleteRatingModelMasterResult result = null;
        try {
            result = this.client.deleteRatingModelMaster(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.matchmaking.domain.model.RatingModelMasterDomain.createCacheKey(
                request.getRatingName() != null ? request.getRatingName().toString() : null
            ),
            RatingModelMaster.class
        );
        RatingModelMasterDomain domain = this;

        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String ratingName,
        String childType
    )
    {
        return String.join(
            ":",
            "matchmaking",
            namespaceName,
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

    public RatingModelMaster model() {
        RatingModelMaster value = cache.get(
            parentKey,
            io.gs2.matchmaking.domain.model.RatingModelMasterDomain.createCacheKey(
                this.getRatingName() != null ? this.getRatingName().toString() : null
            ),
            RatingModelMaster.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetRatingModelMasterRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.matchmaking.domain.model.RatingModelMasterDomain.createCacheKey(
                        this.getRatingName() != null ? this.getRatingName().toString() : null
                    ),
                    RatingModelMaster.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.matchmaking.domain.model.RatingModelMasterDomain.createCacheKey(
                this.getRatingName() != null ? this.getRatingName().toString() : null
            ),
            RatingModelMaster.class
        );
        }
        return value;
    }

}

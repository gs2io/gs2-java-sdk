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
package io.gs2.friend.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.friend.Gs2FriendRestClient;
import io.gs2.friend.domain.iterator.*;
import io.gs2.friend.model.*;
import io.gs2.friend.request.*;
import io.gs2.friend.result.*;

import java.util.List;


public class FollowDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2FriendRestClient client;
    private final String namespaceName;
    private final String userId;
    private final Boolean withProfile;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return userId;
    }
    public Boolean getWithProfile() {
        return withProfile;
    }

    public FollowDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String userId,
        Boolean withProfile
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2FriendRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.userId = userId;
        this.withProfile = withProfile;
        this.parentKey = io.gs2.friend.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "Follow"
        );
    }

    public FollowUserDomain follow(
        FollowByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId);
        FollowByUserIdResult result = this.client.followByUserId(
            request
        );
        String parentKey = io.gs2.friend.domain.model.FollowDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            this.withProfile != null ? this.withProfile.toString() : "false",
            "FollowUser"
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.friend.domain.model.FollowUserDomain.createCacheKey(
                    request.getTargetUserId() != null ? request.getTargetUserId().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        FollowUserDomain domain = new FollowUserDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            request.getNamespaceName(),
            result.getItem().getUserId(),
            this.withProfile,
            request.getTargetUserId()
        );

        return domain;
    }

    public FollowUserDomain followUser(
        String targetUserId
    ) {
        return new FollowUserDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.userId,
            this.withProfile,
            targetUserId
        );
    }

    public static String createCacheParentKey(
        String namespaceName,
        String userId,
        String withProfile,
        String childType
    )
    {
        return String.join(
            ":",
            "friend",
            namespaceName,
            userId,
            withProfile,
            childType
        );
    }

    public static String createCacheKey(
        String withProfile
    )
    {
        return String.join(
            ":",
            withProfile
        );
    }

    public Follow model() {
        Follow value = cache.get(
            parentKey,
            io.gs2.friend.domain.model.FollowDomain.createCacheKey(
                this.getWithProfile() != null ? this.getWithProfile().toString() : null
            ),
            Follow.class
        );
        return value;
    }

}

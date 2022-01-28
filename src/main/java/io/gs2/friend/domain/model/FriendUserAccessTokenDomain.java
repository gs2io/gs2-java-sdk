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


public class FriendUserAccessTokenDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2FriendRestClient client;
    private final String namespaceName;
    private final AccessToken accessToken;
    private final Boolean withProfile;
    private final String targetUserId;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return accessToken.getUserId();
    }
    public Boolean getWithProfile() {
        return withProfile;
    }
    public String getTargetUserId() {
        return targetUserId;
    }

    public FriendUserAccessTokenDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        AccessToken accessToken,
        Boolean withProfile,
        String targetUserId
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2FriendRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.accessToken = accessToken;
        this.withProfile = withProfile;
        this.targetUserId = targetUserId;
        this.parentKey = io.gs2.friend.domain.model.FriendDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.accessToken != null && this.accessToken.getUserId() != null ? this.accessToken.getUserId().toString() : null,
            this.withProfile != null ? this.withProfile.toString() : "false",
            "FriendUser"
        );
    }

    private FriendUser get(
        GetFriendRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withWithProfile(this.withProfile)
            .withTargetUserId(this.targetUserId);
        GetFriendResult result = this.client.getFriend(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.friend.domain.model.FriendUserDomain.createCacheKey(
                    request.getTargetUserId() != null ? request.getTargetUserId().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public FriendUserAccessTokenDomain delete(
        DeleteFriendRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withTargetUserId(this.targetUserId);
        DeleteFriendResult result = null;
        try {
            result = this.client.deleteFriend(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.friend.domain.model.FriendUserDomain.createCacheKey(
                request.getTargetUserId() != null ? request.getTargetUserId().toString() : null
            ),
            FriendUser.class
        );
        cache.delete(
            io.gs2.friend.domain.model.FriendDomain.createCacheParentKey(
                this.namespaceName != null ? this.namespaceName.toString() : null,
                this.getTargetUserId() != null ? this.getTargetUserId().toString() : null,
                this.withProfile != null ? this.withProfile.toString() : "false",
                "FriendUser"
            ),
            io.gs2.friend.domain.model.FriendUserDomain.createCacheKey(
                this.getUserId() != null ? this.getUserId().toString() : null
            ),
            FriendUser.class
        );
        FriendUserAccessTokenDomain domain = this;

        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String userId,
        String withProfile,
        String targetUserId,
        String childType
    )
    {
        return String.join(
            ":",
            "friend",
            namespaceName,
            userId,
            withProfile,
            targetUserId,
            childType
        );
    }

    public static String createCacheKey(
        String targetUserId
    )
    {
        return String.join(
            ":",
            targetUserId
        );
    }

    public FriendUser model() {
        FriendUser value = cache.get(
            parentKey,
            io.gs2.friend.domain.model.FriendUserDomain.createCacheKey(
                this.getTargetUserId() != null ? this.getTargetUserId().toString() : null
            ),
            FriendUser.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetFriendRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.friend.domain.model.FriendUserDomain.createCacheKey(
                        this.getTargetUserId() != null ? this.getTargetUserId().toString() : null
                    ),
                    FriendUser.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.friend.domain.model.FriendUserDomain.createCacheKey(
                this.getTargetUserId() != null ? this.getTargetUserId().toString() : null
            ),
            FriendUser.class
        );
        }
        return value;
    }

}

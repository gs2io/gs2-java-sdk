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


public class ProfileDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2FriendRestClient client;
    private final String namespaceName;
    private final String userId;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return userId;
    }

    public ProfileDomain(
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
        this.client = new Gs2FriendRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.userId = userId;
        this.parentKey = io.gs2.friend.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "Profile"
        );
    }

    private Profile get(
        GetProfileByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId);
        GetProfileByUserIdResult result = this.client.getProfileByUserId(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.friend.domain.model.ProfileDomain.createCacheKey(
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public ProfileDomain update(
        UpdateProfileByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId);
        UpdateProfileByUserIdResult result = this.client.updateProfileByUserId(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.friend.domain.model.ProfileDomain.createCacheKey(
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        ProfileDomain domain = this;

        return domain;
    }

    public ProfileDomain delete(
        DeleteProfileByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId);
        DeleteProfileByUserIdResult result = null;
        try {
            result = this.client.deleteProfileByUserId(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        ProfileDomain domain = this;
        return domain;
    }

    public PublicProfileDomain getPublic(
        GetPublicProfileRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId);
        GetPublicProfileResult result = this.client.getPublicProfile(
            request
        );
        String parentKey = io.gs2.friend.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "PublicProfile"
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.friend.domain.model.PublicProfileDomain.createCacheKey(
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        PublicProfileDomain domain = new PublicProfileDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            request.getNamespaceName(),
            result.getItem().getUserId()
        );

        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String userId,
        String childType
    )
    {
        return String.join(
            ":",
            "friend",
            namespaceName,
            userId,
            childType
        );
    }

    public static String createCacheKey(
    )
    {
        return "Singleton";
    }

    public Profile model() {
        Profile value = cache.get(
            parentKey,
            io.gs2.friend.domain.model.ProfileDomain.createCacheKey(
            ),
            Profile.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetProfileByUserIdRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.friend.domain.model.ProfileDomain.createCacheKey(
                    ),
                    Profile.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.friend.domain.model.ProfileDomain.createCacheKey(
            ),
            Profile.class
        );
        }
        return value;
    }

}

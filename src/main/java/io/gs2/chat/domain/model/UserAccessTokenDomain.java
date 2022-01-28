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
package io.gs2.chat.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.chat.Gs2ChatRestClient;
import io.gs2.chat.domain.iterator.*;
import io.gs2.chat.model.*;
import io.gs2.chat.request.*;
import io.gs2.chat.result.*;

import java.util.List;


public class UserAccessTokenDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2ChatRestClient client;
    private final String namespaceName;
    private final AccessToken accessToken;

    private final String parentKey;
    String nextPageToken;
    public String getNextPageToken() {
        return this.nextPageToken;
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
        this.client = new Gs2ChatRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.accessToken = accessToken;
        this.parentKey = io.gs2.chat.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "User"
        );
    }

    public RoomAccessTokenDomain createRoom(
        CreateRoomRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null);
        CreateRoomResult result = this.client.createRoom(
            request
        );
        String parentKey = io.gs2.chat.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "Singleton",
            "Room"
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.chat.domain.model.RoomDomain.createCacheKey(
                    result.getItem().getName() != null ? result.getItem().getName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        RoomAccessTokenDomain domain = new RoomAccessTokenDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            request.getNamespaceName(),
            this.accessToken,
            result.getItem().getName(),
            result.getItem().getPassword()
        );

        return domain;
    }

    public RoomAccessTokenDomain deleteRoom(
        DeleteRoomRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null);
        DeleteRoomResult result = null;
        try {
            result = this.client.deleteRoom(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        String parentKey = io.gs2.chat.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "Singleton",
            "Room"
        );
        cache.delete(
            parentKey,
            io.gs2.chat.domain.model.RoomDomain.createCacheKey(
                request.getRoomName() != null ? request.getRoomName().toString() : null
            ),
            Room.class
        );
        RoomAccessTokenDomain domain = new RoomAccessTokenDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            request.getNamespaceName(),
            this.accessToken,
            result.getItem().getName(),
            result.getItem().getPassword()
        );

        return domain;
    }

    public RoomAccessTokenDomain room(
        String roomName,
        String password
    ) {
        return new RoomAccessTokenDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.accessToken,
            roomName,
            password
        );
    }

    public DescribeSubscribesIterator subscribes(
    )
    {
        return new DescribeSubscribesIterator(
            this.cache,
            this.client,
            this.namespaceName,
            this.accessToken
        );
    }

    public SubscribeAccessTokenDomain subscribe(
        String roomName
    ) {
        return new SubscribeAccessTokenDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.accessToken,
            roomName
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
            "chat",
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

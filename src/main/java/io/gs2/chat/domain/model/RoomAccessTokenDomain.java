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


public class RoomAccessTokenDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2ChatRestClient client;
    private final String namespaceName;
    private final AccessToken accessToken;
    private final String roomName;
    private final String password;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return accessToken.getUserId();
    }
    public String getRoomName() {
        return roomName;
    }
    public String getPassword() {
        return password;
    }

    public RoomAccessTokenDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        AccessToken accessToken,
        String roomName,
        String password
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
        this.roomName = roomName;
        this.password = password;
        this.parentKey = io.gs2.chat.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "Singleton",
            "Room"
        );
    }

    public RoomAccessTokenDomain update(
        UpdateRoomRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withRoomName(this.roomName)
            .withPassword(this.password);
        UpdateRoomResult result = this.client.updateRoom(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.chat.domain.model.RoomDomain.createCacheKey(
                    request.getRoomName() != null ? request.getRoomName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        RoomAccessTokenDomain domain = this;

        return domain;
    }

    public MessageAccessTokenDomain post(
        PostRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withRoomName(this.roomName)
            .withPassword(this.password);
        PostResult result = this.client.post(
            request
        );
        String parentKey = io.gs2.chat.domain.model.RoomDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "Singleton",
            this.roomName != null ? this.roomName.toString() : null,
            "Message"
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.chat.domain.model.MessageDomain.createCacheKey(
                    result.getItem().getName() != null ? result.getItem().getName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        MessageAccessTokenDomain domain = new MessageAccessTokenDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            request.getNamespaceName(),
            this.accessToken,
            result.getItem().getRoomName(),
            request.getPassword(),
            result.getItem().getName()
        );

        return domain;
    }

    public DescribeMessagesIterator messages(
    )
    {
        return new DescribeMessagesIterator(
            this.cache,
            this.client,
            this.namespaceName,
            this.roomName,
            this.password,
            this.accessToken
        );
    }

    public MessageAccessTokenDomain message(
        String messageName
    ) {
        return new MessageAccessTokenDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.accessToken,
            this.roomName,
            this.password,
            messageName
        );
    }

    public static String createCacheParentKey(
        String namespaceName,
        String userId,
        String roomName,
        String childType
    )
    {
        return String.join(
            ":",
            "chat",
            namespaceName,
            userId,
            roomName,
            childType
        );
    }

    public static String createCacheKey(
        String roomName
    )
    {
        return String.join(
            ":",
            roomName
        );
    }

    public Room model() {
        Room value = cache.get(
            parentKey,
            io.gs2.chat.domain.model.RoomDomain.createCacheKey(
                this.getRoomName() != null ? this.getRoomName().toString() : null
            ),
            Room.class
        );
        return value;
    }

}

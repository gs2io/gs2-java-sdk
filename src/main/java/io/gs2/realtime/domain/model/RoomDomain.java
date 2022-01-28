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
package io.gs2.realtime.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.realtime.Gs2RealtimeRestClient;
import io.gs2.realtime.domain.iterator.*;
import io.gs2.realtime.model.*;
import io.gs2.realtime.request.*;
import io.gs2.realtime.result.*;

import java.util.List;


public class RoomDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2RealtimeRestClient client;
    private final String namespaceName;
    private final String roomName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getRoomName() {
        return roomName;
    }

    public RoomDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String roomName
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2RealtimeRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.roomName = roomName;
        this.parentKey = io.gs2.realtime.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "Room"
        );
    }

    private Room get(
        GetRoomRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withRoomName(this.roomName);
        GetRoomResult result = this.client.getRoom(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.realtime.domain.model.RoomDomain.createCacheKey(
                    request.getRoomName() != null ? request.getRoomName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public RoomDomain delete(
        DeleteRoomRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withRoomName(this.roomName);
        DeleteRoomResult result = null;
        try {
            result = this.client.deleteRoom(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.realtime.domain.model.RoomDomain.createCacheKey(
                request.getRoomName() != null ? request.getRoomName().toString() : null
            ),
            Room.class
        );
        RoomDomain domain = this;

        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String roomName,
        String childType
    )
    {
        return String.join(
            ":",
            "realtime",
            namespaceName,
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
            io.gs2.realtime.domain.model.RoomDomain.createCacheKey(
                this.getRoomName() != null ? this.getRoomName().toString() : null
            ),
            Room.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetRoomRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.realtime.domain.model.RoomDomain.createCacheKey(
                        this.getRoomName() != null ? this.getRoomName().toString() : null
                    ),
                    Room.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.realtime.domain.model.RoomDomain.createCacheKey(
                this.getRoomName() != null ? this.getRoomName().toString() : null
            ),
            Room.class
        );
        }
        return value;
    }

}

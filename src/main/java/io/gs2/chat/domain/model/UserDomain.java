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


public class UserDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2ChatRestClient client;
    private final String namespaceName;
    private final String userId;

    private final String parentKey;
    String nextPageToken;
    public String getNextPageToken() {
        return this.nextPageToken;
    }
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return userId;
    }

    public UserDomain(
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
        this.client = new Gs2ChatRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.userId = userId;
        this.parentKey = io.gs2.chat.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "User"
        );
    }

    public DescribeRoomsIterator rooms(
    )
    {
        return new DescribeRoomsIterator(
            this.cache,
            this.client,
            this.namespaceName
        );
    }

    public RoomDomain room(
        String roomName,
        String password
    ) {
        return new RoomDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.userId,
            roomName,
            password
        );
    }

    public DescribeSubscribesByUserIdIterator subscribes(
    )
    {
        return new DescribeSubscribesByUserIdIterator(
            this.cache,
            this.client,
            this.namespaceName,
            this.userId
        );
    }

    public DescribeSubscribesByRoomNameIterator subscribesByRoomName(
        String roomName
    )
    {
        return new DescribeSubscribesByRoomNameIterator(
            this.cache,
            this.client,
            this.namespaceName,
            roomName
        );
    }

    public SubscribeDomain subscribe(
        String roomName
    ) {
        return new SubscribeDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.userId,
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
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


public class SubscribeDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2ChatRestClient client;
    private final String namespaceName;
    private final String userId;
    private final String roomName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return userId;
    }
    public String getRoomName() {
        return roomName;
    }

    public SubscribeDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String userId,
        String roomName
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
        this.roomName = roomName;
        this.parentKey = io.gs2.chat.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "Subscribe"
        );
    }

    public SubscribeDomain subscribe(
        SubscribeByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withRoomName(this.roomName);
        SubscribeByUserIdResult result = this.client.subscribeByUserId(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.chat.domain.model.SubscribeDomain.createCacheKey(
                    request.getRoomName() != null ? request.getRoomName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        SubscribeDomain domain = this;

        return domain;
    }

    private Subscribe get(
        GetSubscribeByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withRoomName(this.roomName);
        GetSubscribeByUserIdResult result = this.client.getSubscribeByUserId(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.chat.domain.model.SubscribeDomain.createCacheKey(
                    request.getRoomName() != null ? request.getRoomName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public SubscribeDomain updateNotificationType(
        UpdateNotificationTypeByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withRoomName(this.roomName);
        UpdateNotificationTypeByUserIdResult result = this.client.updateNotificationTypeByUserId(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.chat.domain.model.SubscribeDomain.createCacheKey(
                    request.getRoomName() != null ? request.getRoomName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        SubscribeDomain domain = this;

        return domain;
    }

    public SubscribeDomain unsubscribe(
        UnsubscribeByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withRoomName(this.roomName);
        UnsubscribeByUserIdResult result = this.client.unsubscribeByUserId(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.chat.domain.model.SubscribeDomain.createCacheKey(
                    request.getRoomName() != null ? request.getRoomName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        SubscribeDomain domain = this;

        return domain;
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

    public Subscribe model() {
        Subscribe value = cache.get(
            parentKey,
            io.gs2.chat.domain.model.SubscribeDomain.createCacheKey(
                this.getRoomName() != null ? this.getRoomName().toString() : null
            ),
            Subscribe.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetSubscribeByUserIdRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.chat.domain.model.SubscribeDomain.createCacheKey(
                        this.getRoomName() != null ? this.getRoomName().toString() : null
                    ),
                    Subscribe.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.chat.domain.model.SubscribeDomain.createCacheKey(
                this.getRoomName() != null ? this.getRoomName().toString() : null
            ),
            Subscribe.class
        );
        }
        return value;
    }

}

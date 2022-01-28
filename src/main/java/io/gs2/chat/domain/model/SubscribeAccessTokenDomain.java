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


public class SubscribeAccessTokenDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2ChatRestClient client;
    private final String namespaceName;
    private final AccessToken accessToken;
    private final String roomName;

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

    public SubscribeAccessTokenDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        AccessToken accessToken,
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
        this.accessToken = accessToken;
        this.roomName = roomName;
        this.parentKey = io.gs2.chat.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.accessToken != null && this.accessToken.getUserId() != null ? this.accessToken.getUserId().toString() : null,
            "Subscribe"
        );
    }

    public SubscribeAccessTokenDomain subscribe(
        SubscribeRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withRoomName(this.roomName);
        SubscribeResult result = this.client.subscribe(
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
        SubscribeAccessTokenDomain domain = this;

        return domain;
    }

    private Subscribe get(
        GetSubscribeRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withRoomName(this.roomName);
        GetSubscribeResult result = this.client.getSubscribe(
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

    public SubscribeAccessTokenDomain updateNotificationType(
        UpdateNotificationTypeRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withRoomName(this.roomName);
        UpdateNotificationTypeResult result = this.client.updateNotificationType(
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
        SubscribeAccessTokenDomain domain = this;

        return domain;
    }

    public SubscribeAccessTokenDomain unsubscribe(
        UnsubscribeRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withRoomName(this.roomName);
        UnsubscribeResult result = null;
        try {
            result = this.client.unsubscribe(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.chat.domain.model.SubscribeDomain.createCacheKey(
                request.getRoomName() != null ? request.getRoomName().toString() : null
            ),
            Subscribe.class
        );
        SubscribeAccessTokenDomain domain = this;

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
                    new GetSubscribeRequest()
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

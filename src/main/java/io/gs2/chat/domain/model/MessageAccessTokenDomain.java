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


public class MessageAccessTokenDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2ChatRestClient client;
    private final String namespaceName;
    private final AccessToken accessToken;
    private final String roomName;
    private final String password;
    private final String messageName;

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
    public String getMessageName() {
        return messageName;
    }

    public MessageAccessTokenDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        AccessToken accessToken,
        String roomName,
        String password,
        String messageName
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
        this.messageName = messageName;
        this.parentKey = io.gs2.chat.domain.model.RoomDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "Singleton",
            this.roomName != null ? this.roomName.toString() : null,
            "Message"
        );
    }

    private Message get(
        GetMessageRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withRoomName(this.roomName)
            .withPassword(this.password)
            .withMessageName(this.messageName);
        GetMessageResult result = this.client.getMessage(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.chat.domain.model.MessageDomain.createCacheKey(
                    request.getMessageName() != null ? request.getMessageName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public static String createCacheParentKey(
        String namespaceName,
        String userId,
        String roomName,
        String messageName,
        String childType
    )
    {
        return String.join(
            ":",
            "chat",
            namespaceName,
            userId,
            roomName,
            messageName,
            childType
        );
    }

    public static String createCacheKey(
        String messageName
    )
    {
        return String.join(
            ":",
            messageName
        );
    }

    public Message model() {
        Message value = cache.get(
            parentKey,
            io.gs2.chat.domain.model.MessageDomain.createCacheKey(
                this.getMessageName() != null ? this.getMessageName().toString() : null
            ),
            Message.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetMessageRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.chat.domain.model.MessageDomain.createCacheKey(
                        this.getMessageName() != null ? this.getMessageName().toString() : null
                    ),
                    Message.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.chat.domain.model.MessageDomain.createCacheKey(
                this.getMessageName() != null ? this.getMessageName().toString() : null
            ),
            Message.class
        );
        }
        return value;
    }

}

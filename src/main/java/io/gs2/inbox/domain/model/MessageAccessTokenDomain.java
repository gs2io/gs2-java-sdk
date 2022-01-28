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
package io.gs2.inbox.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.inbox.Gs2InboxRestClient;
import io.gs2.inbox.domain.iterator.*;
import io.gs2.inbox.model.*;
import io.gs2.inbox.request.*;
import io.gs2.inbox.result.*;

import java.util.List;


public class MessageAccessTokenDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2InboxRestClient client;
    private final String namespaceName;
    private final AccessToken accessToken;
    private final String messageName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return accessToken.getUserId();
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
        String messageName
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2InboxRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.accessToken = accessToken;
        this.messageName = messageName;
        this.parentKey = io.gs2.inbox.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.accessToken != null && this.accessToken.getUserId() != null ? this.accessToken.getUserId().toString() : null,
            "Message"
        );
    }

    private Message get(
        GetMessageRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withMessageName(this.messageName);
        GetMessageResult result = this.client.getMessage(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.inbox.domain.model.MessageDomain.createCacheKey(
                    request.getMessageName() != null ? request.getMessageName().toString() : null
                ),
                result.getItem(),
                result.getItem().getExpiresAt() == null ? System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes : result.getItem().getExpiresAt()
            );
        }
        return result.getItem();
    }

    public MessageAccessTokenDomain open(
        OpenMessageRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withMessageName(this.messageName);
        OpenMessageResult result = this.client.openMessage(
            request
        );
        cache.listCacheClear(
            parentKey.replace("Message", "Message"),
            Message.class
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.inbox.domain.model.MessageDomain.createCacheKey(
                    request.getMessageName() != null ? request.getMessageName().toString() : null
                ),
                result.getItem(),
                result.getItem().getExpiresAt() == null ? System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes : result.getItem().getExpiresAt()
            );
        }
        MessageAccessTokenDomain domain = this;

        return domain;
    }

    public io.gs2.core.domain.StampSheetDomain read(
        ReadMessageRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withMessageName(this.messageName);
        ReadMessageResult result = this.client.readMessage(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.inbox.domain.model.MessageDomain.createCacheKey(
                    request.getMessageName() != null ? request.getMessageName().toString() : null
                ),
                result.getItem(),
                result.getItem().getExpiresAt() == null ? System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes : result.getItem().getExpiresAt()
            );
        }
        io.gs2.core.domain.StampSheetDomain stampSheet = new io.gs2.core.domain.StampSheetDomain(
                cache,
                jobQueueDomain,
                session,
                result.getStampSheet(),
                result.getStampSheetEncryptionKeyId(),
                stampSheetConfiguration.namespaceName,
                stampSheetConfiguration.stampTaskEventHandler,
                stampSheetConfiguration.stampSheetEventHandler
        );
        try {
            stampSheet.run();
        } catch (io.gs2.core.exception.Gs2Exception e) {
            throw new io.gs2.core.exception.TransactionException(stampSheet, e);
        }
        return stampSheet;
    }

    public MessageAccessTokenDomain delete(
        DeleteMessageRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withMessageName(this.messageName);
        DeleteMessageResult result = null;
        try {
            result = this.client.deleteMessage(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.inbox.domain.model.MessageDomain.createCacheKey(
                request.getMessageName() != null ? request.getMessageName().toString() : null
            ),
            Message.class
        );
        MessageAccessTokenDomain domain = this;

        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String userId,
        String messageName,
        String childType
    )
    {
        return String.join(
            ":",
            "inbox",
            namespaceName,
            userId,
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
            io.gs2.inbox.domain.model.MessageDomain.createCacheKey(
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
                    io.gs2.inbox.domain.model.MessageDomain.createCacheKey(
                        this.getMessageName() != null ? this.getMessageName().toString() : null
                    ),
                    Message.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.inbox.domain.model.MessageDomain.createCacheKey(
                this.getMessageName() != null ? this.getMessageName().toString() : null
            ),
            Message.class
        );
        }
        return value;
    }

}

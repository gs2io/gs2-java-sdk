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


public class ReceiveFriendRequestDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2FriendRestClient client;
    private final String namespaceName;
    private final String userId;
    private final String fromUserId;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return userId;
    }
    public String getFromUserId() {
        return fromUserId;
    }

    public ReceiveFriendRequestDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String userId,
        String fromUserId
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
        this.fromUserId = fromUserId;
        this.parentKey = io.gs2.friend.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "ReceiveFriendRequest"
        );
    }

    private FriendRequest get(
        GetReceiveRequestByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withFromUserId(this.fromUserId);
        GetReceiveRequestByUserIdResult result = this.client.getReceiveRequestByUserId(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.friend.domain.model.FriendRequestDomain.createCacheKey(
                    result.getItem().getTargetUserId() != null ? result.getItem().getTargetUserId().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public FriendRequestDomain accept(
        AcceptRequestByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withFromUserId(this.fromUserId);
        AcceptRequestByUserIdResult result = null;
        try {
            result = this.client.acceptRequestByUserId(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.friend.domain.model.FriendRequestDomain.createCacheKey(
                result.getItem().getTargetUserId() != null ? result.getItem().getTargetUserId().toString() : null
            ),
            FriendRequest.class
        );
        cache.listCacheClear(
            io.gs2.friend.domain.model.UserDomain.createCacheParentKey(
                this.getNamespaceName() != null ? this.getNamespaceName().toString() : null,
                this.fromUserId != null ? this.fromUserId.toString() : null,
                "SendFriendRequest"
            ),
            FriendRequest.class
        );
        cache.listCacheClear(
            io.gs2.friend.domain.model.UserDomain.createCacheParentKey(
                this.getNamespaceName() != null ? this.getNamespaceName().toString() : null,
                this.getUserId() != null ? this.getUserId().toString() : null,
                "ReceiveFriendRequest"
            ),
            FriendRequest.class
        );
        FriendRequestDomain domain = new FriendRequestDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            request.getNamespaceName(),
            result.getItem().getUserId(),
            this.fromUserId
        );

        return domain;
    }

    public FriendRequestDomain reject(
        RejectRequestByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withFromUserId(this.fromUserId);
        RejectRequestByUserIdResult result = null;
        try {
            result = this.client.rejectRequestByUserId(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.friend.domain.model.FriendRequestDomain.createCacheKey(
                result.getItem().getTargetUserId() != null ? result.getItem().getTargetUserId().toString() : null
            ),
            FriendRequest.class
        );
        cache.listCacheClear(
            io.gs2.friend.domain.model.UserDomain.createCacheParentKey(
                this.getNamespaceName() != null ? this.getNamespaceName().toString() : null,
                this.fromUserId != null ? this.fromUserId.toString() : null,
                "SendFriendRequest"
            ),
            FriendRequest.class
        );
        cache.listCacheClear(
            io.gs2.friend.domain.model.UserDomain.createCacheParentKey(
                this.getNamespaceName() != null ? this.getNamespaceName().toString() : null,
                this.getUserId() != null ? this.getUserId().toString() : null,
                "ReceiveFriendRequest"
            ),
            FriendRequest.class
        );
        FriendRequestDomain domain = new FriendRequestDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            request.getNamespaceName(),
            result.getItem().getUserId(),
            this.fromUserId
        );

        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String userId,
        String fromUserId,
        String childType
    )
    {
        return String.join(
            ":",
            "friend",
            namespaceName,
            userId,
            fromUserId,
            childType
        );
    }

    public static String createCacheKey(
        String fromUserId
    )
    {
        return String.join(
            ":",
            fromUserId
        );
    }

    public FriendRequest model() {
        FriendRequest value = cache.get(
            parentKey,
            io.gs2.friend.domain.model.ReceiveFriendRequestDomain.createCacheKey(
                this.getFromUserId() != null ? this.getFromUserId().toString() : null
            ),
            FriendRequest.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetReceiveRequestByUserIdRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.friend.domain.model.ReceiveFriendRequestDomain.createCacheKey(
                        this.getFromUserId() != null ? this.getFromUserId().toString() : null
                    ),
                    ReceiveFriendRequest.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.friend.domain.model.ReceiveFriendRequestDomain.createCacheKey(
                this.getFromUserId() != null ? this.getFromUserId().toString() : null
            ),
            FriendRequest.class
        );
        }
        return value;
    }

}

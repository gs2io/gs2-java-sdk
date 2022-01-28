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


public class ReceivedDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2InboxRestClient client;
    private final String namespaceName;
    private final String userId;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return userId;
    }

    public ReceivedDomain(
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
        this.client = new Gs2InboxRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.userId = userId;
        this.parentKey = io.gs2.inbox.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "Received"
        );
    }

    private Received get(
        GetReceivedByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId);
        GetReceivedByUserIdResult result = this.client.getReceivedByUserId(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.inbox.domain.model.ReceivedDomain.createCacheKey(
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public ReceivedDomain update(
        UpdateReceivedByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId);
        UpdateReceivedByUserIdResult result = this.client.updateReceivedByUserId(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.inbox.domain.model.ReceivedDomain.createCacheKey(
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        ReceivedDomain domain = this;

        return domain;
    }

    public ReceivedDomain delete(
        DeleteReceivedByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId);
        DeleteReceivedByUserIdResult result = null;
        try {
            result = this.client.deleteReceivedByUserId(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.inbox.domain.model.ReceivedDomain.createCacheKey(
            ),
            Received.class
        );
        ReceivedDomain domain = this;

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
            "inbox",
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

    public Received model() {
        Received value = cache.get(
            parentKey,
            io.gs2.inbox.domain.model.ReceivedDomain.createCacheKey(
            ),
            Received.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetReceivedByUserIdRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.inbox.domain.model.ReceivedDomain.createCacheKey(
                    ),
                    Received.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.inbox.domain.model.ReceivedDomain.createCacheKey(
            ),
            Received.class
        );
        }
        return value;
    }

}

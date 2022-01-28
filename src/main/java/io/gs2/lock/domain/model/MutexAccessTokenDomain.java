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
package io.gs2.lock.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.lock.Gs2LockRestClient;
import io.gs2.lock.domain.iterator.*;
import io.gs2.lock.model.*;
import io.gs2.lock.request.*;
import io.gs2.lock.result.*;

import java.util.List;


public class MutexAccessTokenDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2LockRestClient client;
    private final String namespaceName;
    private final AccessToken accessToken;
    private final String propertyId;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return accessToken.getUserId();
    }
    public String getPropertyId() {
        return propertyId;
    }

    public MutexAccessTokenDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        AccessToken accessToken,
        String propertyId
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2LockRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.accessToken = accessToken;
        this.propertyId = propertyId;
        this.parentKey = io.gs2.lock.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.accessToken != null && this.accessToken.getUserId() != null ? this.accessToken.getUserId().toString() : null,
            "Mutex"
        );
    }

    public MutexAccessTokenDomain lock(
        LockRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withPropertyId(this.propertyId);
        LockResult result = this.client.lock(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.lock.domain.model.MutexDomain.createCacheKey(
                    request.getPropertyId() != null ? request.getPropertyId().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        MutexAccessTokenDomain domain = this;

        return domain;
    }

    public MutexAccessTokenDomain unlock(
        UnlockRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withPropertyId(this.propertyId);
        UnlockResult result = null;
        try {
            result = this.client.unlock(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.lock.domain.model.MutexDomain.createCacheKey(
                request.getPropertyId() != null ? request.getPropertyId().toString() : null
            ),
            Mutex.class
        );
        MutexAccessTokenDomain domain = this;

        return domain;
    }

    private Mutex get(
        GetMutexRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withPropertyId(this.propertyId);
        GetMutexResult result = this.client.getMutex(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.lock.domain.model.MutexDomain.createCacheKey(
                    request.getPropertyId() != null ? request.getPropertyId().toString() : null
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
        String propertyId,
        String childType
    )
    {
        return String.join(
            ":",
            "lock",
            namespaceName,
            userId,
            propertyId,
            childType
        );
    }

    public static String createCacheKey(
        String propertyId
    )
    {
        return String.join(
            ":",
            propertyId
        );
    }

    public Mutex model() {
        Mutex value = cache.get(
            parentKey,
            io.gs2.lock.domain.model.MutexDomain.createCacheKey(
                this.getPropertyId() != null ? this.getPropertyId().toString() : null
            ),
            Mutex.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetMutexRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.lock.domain.model.MutexDomain.createCacheKey(
                        this.getPropertyId() != null ? this.getPropertyId().toString() : null
                    ),
                    Mutex.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.lock.domain.model.MutexDomain.createCacheKey(
                this.getPropertyId() != null ? this.getPropertyId().toString() : null
            ),
            Mutex.class
        );
        }
        return value;
    }

}

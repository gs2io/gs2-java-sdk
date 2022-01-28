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


public class MutexDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2LockRestClient client;
    private final String namespaceName;
    private final String userId;
    private final String propertyId;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return userId;
    }
    public String getPropertyId() {
        return propertyId;
    }

    public MutexDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String userId,
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
        this.userId = userId;
        this.propertyId = propertyId;
        this.parentKey = io.gs2.lock.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "Mutex"
        );
    }

    public MutexDomain lock(
        LockByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withPropertyId(this.propertyId);
        LockByUserIdResult result = this.client.lockByUserId(
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
        MutexDomain domain = this;

        return domain;
    }

    public MutexDomain unlock(
        UnlockByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withPropertyId(this.propertyId);
        UnlockByUserIdResult result = this.client.unlockByUserId(
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
        MutexDomain domain = this;

        return domain;
    }

    private Mutex get(
        GetMutexByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withPropertyId(this.propertyId);
        GetMutexByUserIdResult result = this.client.getMutexByUserId(
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

    public MutexDomain delete(
        DeleteMutexByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withPropertyId(this.propertyId);
        DeleteMutexByUserIdResult result = null;
        try {
            result = this.client.deleteMutexByUserId(
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
        MutexDomain domain = this;

        return domain;
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
                    new GetMutexByUserIdRequest()
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

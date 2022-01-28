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
package io.gs2.limit.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.limit.Gs2LimitRestClient;
import io.gs2.limit.domain.iterator.*;
import io.gs2.limit.model.*;
import io.gs2.limit.request.*;
import io.gs2.limit.result.*;

import java.util.List;


public class CounterDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2LimitRestClient client;
    private final String namespaceName;
    private final String userId;
    private final String limitName;
    private final String counterName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return userId;
    }
    public String getLimitName() {
        return limitName;
    }
    public String getCounterName() {
        return counterName;
    }

    public CounterDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String userId,
        String limitName,
        String counterName
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2LimitRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.userId = userId;
        this.limitName = limitName;
        this.counterName = counterName;
        this.parentKey = io.gs2.limit.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "Counter"
        );
    }

    private Counter get(
        GetCounterByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withLimitName(this.limitName)
            .withCounterName(this.counterName);
        GetCounterByUserIdResult result = this.client.getCounterByUserId(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.limit.domain.model.CounterDomain.createCacheKey(
                    request.getLimitName() != null ? request.getLimitName().toString() : null,
                    request.getCounterName() != null ? request.getCounterName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public CounterDomain countUp(
        CountUpByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withLimitName(this.limitName)
            .withCounterName(this.counterName);
        CountUpByUserIdResult result = this.client.countUpByUserId(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.limit.domain.model.CounterDomain.createCacheKey(
                    request.getLimitName() != null ? request.getLimitName().toString() : null,
                    request.getCounterName() != null ? request.getCounterName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        CounterDomain domain = this;

        return domain;
    }

    public CounterDomain delete(
        DeleteCounterByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withLimitName(this.limitName)
            .withCounterName(this.counterName);
        DeleteCounterByUserIdResult result = null;
        try {
            result = this.client.deleteCounterByUserId(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.limit.domain.model.CounterDomain.createCacheKey(
                request.getLimitName() != null ? request.getLimitName().toString() : null,
                request.getCounterName() != null ? request.getCounterName().toString() : null
            ),
            Counter.class
        );
        CounterDomain domain = this;

        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String userId,
        String limitName,
        String counterName,
        String childType
    )
    {
        return String.join(
            ":",
            "limit",
            namespaceName,
            userId,
            limitName,
            counterName,
            childType
        );
    }

    public static String createCacheKey(
        String limitName,
        String counterName
    )
    {
        return String.join(
            ":",
            limitName,
            counterName
        );
    }

    public Counter model() {
        Counter value = cache.get(
            parentKey,
            io.gs2.limit.domain.model.CounterDomain.createCacheKey(
                this.getLimitName() != null ? this.getLimitName().toString() : null,
                this.getCounterName() != null ? this.getCounterName().toString() : null
            ),
            Counter.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetCounterByUserIdRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.limit.domain.model.CounterDomain.createCacheKey(
                        this.getLimitName() != null ? this.getLimitName().toString() : null,
                        this.getCounterName() != null ? this.getCounterName().toString() : null
                    ),
                    Counter.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.limit.domain.model.CounterDomain.createCacheKey(
                this.getLimitName() != null ? this.getLimitName().toString() : null,
                this.getCounterName() != null ? this.getCounterName().toString() : null
            ),
            Counter.class
        );
        }
        return value;
    }

}

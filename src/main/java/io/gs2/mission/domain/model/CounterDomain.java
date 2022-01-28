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
package io.gs2.mission.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.mission.Gs2MissionRestClient;
import io.gs2.mission.domain.iterator.*;
import io.gs2.mission.model.*;
import io.gs2.mission.request.*;
import io.gs2.mission.result.*;

import java.util.List;


public class CounterDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2MissionRestClient client;
    private final String namespaceName;
    private final String userId;
    private final String counterName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return userId;
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
        String counterName
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2MissionRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.userId = userId;
        this.counterName = counterName;
        this.parentKey = io.gs2.mission.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "Counter"
        );
    }

    public CounterDomain increase(
        IncreaseCounterByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withCounterName(this.counterName);
        IncreaseCounterByUserIdResult result = this.client.increaseCounterByUserId(
            request
        );
        cache.listCacheClear(
            parentKey.replace("Counter", "Complete"),
            Complete.class
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.mission.domain.model.CounterDomain.createCacheKey(
                    request.getCounterName() != null ? request.getCounterName().toString() : null
                ),
                result.getItem(),
                result.getItem().getValues().stream().map(ScopedValue::getNextResetAt).map(i -> i == null ? System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes : i).min((i, j) -> (int) (i - j)).orElse(
                        System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                )
            );
        }
        CounterDomain domain = this;

        return domain;
    }

    private Counter get(
        GetCounterByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withCounterName(this.counterName);
        GetCounterByUserIdResult result = this.client.getCounterByUserId(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.mission.domain.model.CounterDomain.createCacheKey(
                    request.getCounterName() != null ? request.getCounterName().toString() : null
                ),
                result.getItem(),
                result.getItem().getValues().stream().map(ScopedValue::getNextResetAt).map(i -> i == null ? System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes : i).min((i, j) -> (int) (i - j)).orElse(
                        System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                )
            );
        }
        return result.getItem();
    }

    public CounterDomain delete(
        DeleteCounterByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withCounterName(this.counterName);
        DeleteCounterByUserIdResult result = null;
        try {
            result = this.client.deleteCounterByUserId(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.mission.domain.model.CounterDomain.createCacheKey(
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
        String counterName,
        String childType
    )
    {
        return String.join(
            ":",
            "mission",
            namespaceName,
            userId,
            counterName,
            childType
        );
    }

    public static String createCacheKey(
        String counterName
    )
    {
        return String.join(
            ":",
            counterName
        );
    }

    public Counter model() {
        Counter value = cache.get(
            parentKey,
            io.gs2.mission.domain.model.CounterDomain.createCacheKey(
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
                    io.gs2.mission.domain.model.CounterDomain.createCacheKey(
                        this.getCounterName() != null ? this.getCounterName().toString() : null
                    ),
                    Counter.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.mission.domain.model.CounterDomain.createCacheKey(
                this.getCounterName() != null ? this.getCounterName().toString() : null
            ),
            Counter.class
        );
        }
        return value;
    }

}

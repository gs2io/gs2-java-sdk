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
package io.gs2.schedule.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.schedule.Gs2ScheduleRestClient;
import io.gs2.schedule.domain.iterator.*;
import io.gs2.schedule.model.*;
import io.gs2.schedule.request.*;
import io.gs2.schedule.result.*;

import java.util.List;


public class TriggerAccessTokenDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2ScheduleRestClient client;
    private final String namespaceName;
    private final AccessToken accessToken;
    private final String triggerName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return accessToken.getUserId();
    }
    public String getTriggerName() {
        return triggerName;
    }

    public TriggerAccessTokenDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        AccessToken accessToken,
        String triggerName
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2ScheduleRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.accessToken = accessToken;
        this.triggerName = triggerName;
        this.parentKey = io.gs2.schedule.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.accessToken != null && this.accessToken.getUserId() != null ? this.accessToken.getUserId().toString() : null,
            "Trigger"
        );
    }

    private Trigger get(
        GetTriggerRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withTriggerName(this.triggerName);
        GetTriggerResult result = this.client.getTrigger(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.schedule.domain.model.TriggerDomain.createCacheKey(
                    request.getTriggerName() != null ? request.getTriggerName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public TriggerAccessTokenDomain delete(
        DeleteTriggerRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withTriggerName(this.triggerName);
        DeleteTriggerResult result = null;
        try {
            result = this.client.deleteTrigger(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.schedule.domain.model.TriggerDomain.createCacheKey(
                request.getTriggerName() != null ? request.getTriggerName().toString() : null
            ),
            Trigger.class
        );
        TriggerAccessTokenDomain domain = this;

        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String userId,
        String triggerName,
        String childType
    )
    {
        return String.join(
            ":",
            "schedule",
            namespaceName,
            userId,
            triggerName,
            childType
        );
    }

    public static String createCacheKey(
        String triggerName
    )
    {
        return String.join(
            ":",
            triggerName
        );
    }

    public Trigger model() {
        Trigger value = cache.get(
            parentKey,
            io.gs2.schedule.domain.model.TriggerDomain.createCacheKey(
                this.getTriggerName() != null ? this.getTriggerName().toString() : null
            ),
            Trigger.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetTriggerRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.schedule.domain.model.TriggerDomain.createCacheKey(
                        this.getTriggerName() != null ? this.getTriggerName().toString() : null
                    ),
                    Trigger.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.schedule.domain.model.TriggerDomain.createCacheKey(
                this.getTriggerName() != null ? this.getTriggerName().toString() : null
            ),
            Trigger.class
        );
        }
        return value;
    }

}
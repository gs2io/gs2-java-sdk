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


public class EventDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2ScheduleRestClient client;
    private final String namespaceName;
    private final String userId;
    private final String eventName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return userId;
    }
    public String getEventName() {
        return eventName;
    }

    public EventDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String userId,
        String eventName
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2ScheduleRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.userId = userId;
        this.eventName = eventName;
        this.parentKey = io.gs2.schedule.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "Event"
        );
    }

    private Event get(
        GetEventByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withEventName(this.eventName);
        GetEventByUserIdResult result = this.client.getEventByUserId(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.schedule.domain.model.EventDomain.createCacheKey(
                    request.getEventName() != null ? request.getEventName().toString() : null
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
        String eventName,
        String childType
    )
    {
        return String.join(
            ":",
            "schedule",
            namespaceName,
            userId,
            eventName,
            childType
        );
    }

    public static String createCacheKey(
        String eventName
    )
    {
        return String.join(
            ":",
            eventName
        );
    }

    public Event model() {
        Event value = cache.get(
            parentKey,
            io.gs2.schedule.domain.model.EventDomain.createCacheKey(
                this.getEventName() != null ? this.getEventName().toString() : null
            ),
            Event.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetEventByUserIdRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.schedule.domain.model.EventDomain.createCacheKey(
                        this.getEventName() != null ? this.getEventName().toString() : null
                    ),
                    Event.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.schedule.domain.model.EventDomain.createCacheKey(
                this.getEventName() != null ? this.getEventName().toString() : null
            ),
            Event.class
        );
        }
        return value;
    }

}

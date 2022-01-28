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


public class CurrentEventMasterDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2ScheduleRestClient client;
    private final String namespaceName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }

    public CurrentEventMasterDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2ScheduleRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.parentKey = io.gs2.schedule.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "CurrentEventMaster"
        );
    }

    public CurrentEventMasterDomain exportMaster(
        ExportMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName);
        ExportMasterResult result = this.client.exportMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.schedule.domain.model.CurrentEventMasterDomain.createCacheKey(
                    request.getNamespaceName() != null ? request.getNamespaceName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        CurrentEventMasterDomain domain = this;

        return domain;
    }

    private CurrentEventMaster get(
        GetCurrentEventMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName);
        GetCurrentEventMasterResult result = this.client.getCurrentEventMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.schedule.domain.model.CurrentEventMasterDomain.createCacheKey(
                    request.getNamespaceName() != null ? request.getNamespaceName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public CurrentEventMasterDomain update(
        UpdateCurrentEventMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName);
        UpdateCurrentEventMasterResult result = this.client.updateCurrentEventMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.schedule.domain.model.CurrentEventMasterDomain.createCacheKey(
                    request.getNamespaceName() != null ? request.getNamespaceName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        CurrentEventMasterDomain domain = this;

        return domain;
    }

    public CurrentEventMasterDomain updateFromGitHub(
        UpdateCurrentEventMasterFromGitHubRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName);
        UpdateCurrentEventMasterFromGitHubResult result = this.client.updateCurrentEventMasterFromGitHub(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.schedule.domain.model.CurrentEventMasterDomain.createCacheKey(
                    request.getNamespaceName() != null ? request.getNamespaceName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        CurrentEventMasterDomain domain = this;

        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String childType
    )
    {
        return String.join(
            ":",
            "schedule",
            namespaceName,
            childType
        );
    }

    public static String createCacheKey(
        String namespaceName
    )
    {
        return String.join(
            ":",
            namespaceName
        );
    }

    public CurrentEventMaster model() {
        CurrentEventMaster value = cache.get(
            parentKey,
            io.gs2.schedule.domain.model.CurrentEventMasterDomain.createCacheKey(
                this.getNamespaceName() != null ? this.getNamespaceName().toString() : null
            ),
            CurrentEventMaster.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetCurrentEventMasterRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.schedule.domain.model.CurrentEventMasterDomain.createCacheKey(
                        this.getNamespaceName() != null ? this.getNamespaceName().toString() : null
                    ),
                    CurrentEventMaster.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.schedule.domain.model.CurrentEventMasterDomain.createCacheKey(
                this.getNamespaceName() != null ? this.getNamespaceName().toString() : null
            ),
            CurrentEventMaster.class
        );
        }
        return value;
    }

}

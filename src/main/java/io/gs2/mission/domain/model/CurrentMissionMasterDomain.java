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


public class CurrentMissionMasterDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2MissionRestClient client;
    private final String namespaceName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }

    public CurrentMissionMasterDomain(
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
        this.client = new Gs2MissionRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.parentKey = io.gs2.mission.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "CurrentMissionMaster"
        );
    }

    public CurrentMissionMasterDomain exportMaster(
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
                io.gs2.mission.domain.model.CurrentMissionMasterDomain.createCacheKey(
                    request.getNamespaceName() != null ? request.getNamespaceName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        CurrentMissionMasterDomain domain = this;

        return domain;
    }

    private CurrentMissionMaster get(
        GetCurrentMissionMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName);
        GetCurrentMissionMasterResult result = this.client.getCurrentMissionMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.mission.domain.model.CurrentMissionMasterDomain.createCacheKey(
                    request.getNamespaceName() != null ? request.getNamespaceName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public CurrentMissionMasterDomain update(
        UpdateCurrentMissionMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName);
        UpdateCurrentMissionMasterResult result = this.client.updateCurrentMissionMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.mission.domain.model.CurrentMissionMasterDomain.createCacheKey(
                    request.getNamespaceName() != null ? request.getNamespaceName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        CurrentMissionMasterDomain domain = this;

        return domain;
    }

    public CurrentMissionMasterDomain updateFromGitHub(
        UpdateCurrentMissionMasterFromGitHubRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName);
        UpdateCurrentMissionMasterFromGitHubResult result = this.client.updateCurrentMissionMasterFromGitHub(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.mission.domain.model.CurrentMissionMasterDomain.createCacheKey(
                    request.getNamespaceName() != null ? request.getNamespaceName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        CurrentMissionMasterDomain domain = this;

        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String childType
    )
    {
        return String.join(
            ":",
            "mission",
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

    public CurrentMissionMaster model() {
        CurrentMissionMaster value = cache.get(
            parentKey,
            io.gs2.mission.domain.model.CurrentMissionMasterDomain.createCacheKey(
                this.getNamespaceName() != null ? this.getNamespaceName().toString() : null
            ),
            CurrentMissionMaster.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetCurrentMissionMasterRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.mission.domain.model.CurrentMissionMasterDomain.createCacheKey(
                        this.getNamespaceName() != null ? this.getNamespaceName().toString() : null
                    ),
                    CurrentMissionMaster.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.mission.domain.model.CurrentMissionMasterDomain.createCacheKey(
                this.getNamespaceName() != null ? this.getNamespaceName().toString() : null
            ),
            CurrentMissionMaster.class
        );
        }
        return value;
    }

}

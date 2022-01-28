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


public class MissionTaskModelMasterDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2MissionRestClient client;
    private final String namespaceName;
    private final String missionGroupName;
    private final String missionTaskName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getMissionGroupName() {
        return missionGroupName;
    }
    public String getMissionTaskName() {
        return missionTaskName;
    }

    public MissionTaskModelMasterDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String missionGroupName,
        String missionTaskName
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2MissionRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.missionGroupName = missionGroupName;
        this.missionTaskName = missionTaskName;
        this.parentKey = io.gs2.mission.domain.model.MissionGroupModelMasterDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.missionGroupName != null ? this.missionGroupName.toString() : null,
            "MissionTaskModelMaster"
        );
    }

    private MissionTaskModelMaster get(
        GetMissionTaskModelMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withMissionGroupName(this.missionGroupName)
            .withMissionTaskName(this.missionTaskName);
        GetMissionTaskModelMasterResult result = this.client.getMissionTaskModelMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.mission.domain.model.MissionTaskModelMasterDomain.createCacheKey(
                    request.getMissionTaskName() != null ? request.getMissionTaskName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public MissionTaskModelMasterDomain update(
        UpdateMissionTaskModelMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withMissionGroupName(this.missionGroupName)
            .withMissionTaskName(this.missionTaskName);
        UpdateMissionTaskModelMasterResult result = this.client.updateMissionTaskModelMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.mission.domain.model.MissionTaskModelMasterDomain.createCacheKey(
                    request.getMissionTaskName() != null ? request.getMissionTaskName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        MissionTaskModelMasterDomain domain = this;

        return domain;
    }

    public MissionTaskModelMasterDomain delete(
        DeleteMissionTaskModelMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withMissionGroupName(this.missionGroupName)
            .withMissionTaskName(this.missionTaskName);
        DeleteMissionTaskModelMasterResult result = null;
        try {
            result = this.client.deleteMissionTaskModelMaster(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.mission.domain.model.MissionTaskModelMasterDomain.createCacheKey(
                request.getMissionTaskName() != null ? request.getMissionTaskName().toString() : null
            ),
            MissionTaskModelMaster.class
        );
        MissionTaskModelMasterDomain domain = this;

        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String missionGroupName,
        String missionTaskName,
        String childType
    )
    {
        return String.join(
            ":",
            "mission",
            namespaceName,
            missionGroupName,
            missionTaskName,
            childType
        );
    }

    public static String createCacheKey(
        String missionTaskName
    )
    {
        return String.join(
            ":",
            missionTaskName
        );
    }

    public MissionTaskModelMaster model() {
        MissionTaskModelMaster value = cache.get(
            parentKey,
            io.gs2.mission.domain.model.MissionTaskModelMasterDomain.createCacheKey(
                this.getMissionTaskName() != null ? this.getMissionTaskName().toString() : null
            ),
            MissionTaskModelMaster.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetMissionTaskModelMasterRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.mission.domain.model.MissionTaskModelMasterDomain.createCacheKey(
                        this.getMissionTaskName() != null ? this.getMissionTaskName().toString() : null
                    ),
                    MissionTaskModelMaster.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.mission.domain.model.MissionTaskModelMasterDomain.createCacheKey(
                this.getMissionTaskName() != null ? this.getMissionTaskName().toString() : null
            ),
            MissionTaskModelMaster.class
        );
        }
        return value;
    }

}

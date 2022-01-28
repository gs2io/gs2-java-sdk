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


public class MissionGroupModelMasterDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2MissionRestClient client;
    private final String namespaceName;
    private final String missionGroupName;

    private final String parentKey;
    String nextPageToken;
    public String getNextPageToken() {
        return this.nextPageToken;
    }
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getMissionGroupName() {
        return missionGroupName;
    }

    public MissionGroupModelMasterDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String missionGroupName
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
        this.parentKey = io.gs2.mission.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "MissionGroupModelMaster"
        );
    }

    private MissionGroupModelMaster get(
        GetMissionGroupModelMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withMissionGroupName(this.missionGroupName);
        GetMissionGroupModelMasterResult result = this.client.getMissionGroupModelMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.mission.domain.model.MissionGroupModelMasterDomain.createCacheKey(
                    request.getMissionGroupName() != null ? request.getMissionGroupName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public MissionGroupModelMasterDomain update(
        UpdateMissionGroupModelMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withMissionGroupName(this.missionGroupName);
        UpdateMissionGroupModelMasterResult result = this.client.updateMissionGroupModelMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.mission.domain.model.MissionGroupModelMasterDomain.createCacheKey(
                    request.getMissionGroupName() != null ? request.getMissionGroupName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        MissionGroupModelMasterDomain domain = this;

        return domain;
    }

    public MissionGroupModelMasterDomain delete(
        DeleteMissionGroupModelMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withMissionGroupName(this.missionGroupName);
        DeleteMissionGroupModelMasterResult result = null;
        try {
            result = this.client.deleteMissionGroupModelMaster(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.mission.domain.model.MissionGroupModelMasterDomain.createCacheKey(
                request.getMissionGroupName() != null ? request.getMissionGroupName().toString() : null
            ),
            MissionGroupModelMaster.class
        );
        MissionGroupModelMasterDomain domain = this;

        return domain;
    }

    public MissionTaskModelMasterDomain createMissionTaskModelMaster(
        CreateMissionTaskModelMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withMissionGroupName(this.missionGroupName);
        CreateMissionTaskModelMasterResult result = this.client.createMissionTaskModelMaster(
            request
        );
        String parentKey = io.gs2.mission.domain.model.MissionGroupModelMasterDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.missionGroupName != null ? this.missionGroupName.toString() : null,
            "MissionTaskModelMaster"
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.mission.domain.model.MissionTaskModelMasterDomain.createCacheKey(
                    result.getItem().getName() != null ? result.getItem().getName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        MissionTaskModelMasterDomain domain = new MissionTaskModelMasterDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            request.getNamespaceName(),
            request.getMissionGroupName(),
            result.getItem().getName()
        );

        return domain;
    }

    public DescribeMissionTaskModelMastersIterator missionTaskModelMasters(
    )
    {
        return new DescribeMissionTaskModelMastersIterator(
            this.cache,
            this.client,
            this.namespaceName,
            this.missionGroupName
        );
    }

    public MissionTaskModelMasterDomain missionTaskModelMaster(
        String missionTaskName
    ) {
        return new MissionTaskModelMasterDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.missionGroupName,
            missionTaskName
        );
    }

    public static String createCacheParentKey(
        String namespaceName,
        String missionGroupName,
        String childType
    )
    {
        return String.join(
            ":",
            "mission",
            namespaceName,
            missionGroupName,
            childType
        );
    }

    public static String createCacheKey(
        String missionGroupName
    )
    {
        return String.join(
            ":",
            missionGroupName
        );
    }

    public MissionGroupModelMaster model() {
        MissionGroupModelMaster value = cache.get(
            parentKey,
            io.gs2.mission.domain.model.MissionGroupModelMasterDomain.createCacheKey(
                this.getMissionGroupName() != null ? this.getMissionGroupName().toString() : null
            ),
            MissionGroupModelMaster.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetMissionGroupModelMasterRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.mission.domain.model.MissionGroupModelMasterDomain.createCacheKey(
                        this.getMissionGroupName() != null ? this.getMissionGroupName().toString() : null
                    ),
                    MissionGroupModelMaster.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.mission.domain.model.MissionGroupModelMasterDomain.createCacheKey(
                this.getMissionGroupName() != null ? this.getMissionGroupName().toString() : null
            ),
            MissionGroupModelMaster.class
        );
        }
        return value;
    }

}

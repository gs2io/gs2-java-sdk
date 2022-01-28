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


public class CompleteDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2MissionRestClient client;
    private final String namespaceName;
    private final String userId;
    private final String missionGroupName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return userId;
    }
    public String getMissionGroupName() {
        return missionGroupName;
    }

    public CompleteDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String userId,
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
        this.userId = userId;
        this.missionGroupName = missionGroupName;
        this.parentKey = io.gs2.mission.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "Complete"
        );
    }

    public io.gs2.core.domain.StampSheetDomain complete(
        CompleteByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withMissionGroupName(this.missionGroupName);
        CompleteByUserIdResult result = this.client.completeByUserId(
            request
        );
        io.gs2.core.domain.StampSheetDomain stampSheet = new io.gs2.core.domain.StampSheetDomain(
                cache,
                jobQueueDomain,
                session,
                result.getStampSheet(),
                result.getStampSheetEncryptionKeyId(),
                stampSheetConfiguration.namespaceName,
                stampSheetConfiguration.stampTaskEventHandler,
                stampSheetConfiguration.stampSheetEventHandler
        );
        try {
            stampSheet.run();
        } catch (io.gs2.core.exception.Gs2Exception e) {
            throw new io.gs2.core.exception.TransactionException(stampSheet, e);
        }
        return stampSheet;
    }

    public CompleteDomain receive(
        ReceiveByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withMissionGroupName(this.missionGroupName);
        ReceiveByUserIdResult result = this.client.receiveByUserId(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.mission.domain.model.CompleteDomain.createCacheKey(
                    request.getMissionGroupName() != null ? request.getMissionGroupName().toString() : null
                ),
                result.getItem(),
                result.getItem().getNextResetAt() == null ? System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes : result.getItem().getNextResetAt()
            );
        }
        CompleteDomain domain = this;

        return domain;
    }

    private Complete get(
        GetCompleteByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withMissionGroupName(this.missionGroupName);
        GetCompleteByUserIdResult result = this.client.getCompleteByUserId(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.mission.domain.model.CompleteDomain.createCacheKey(
                    request.getMissionGroupName() != null ? request.getMissionGroupName().toString() : null
                ),
                result.getItem(),
                result.getItem().getNextResetAt() == null ? System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes : result.getItem().getNextResetAt()
            );
        }
        return result.getItem();
    }

    public CompleteDomain delete(
        DeleteCompleteByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withMissionGroupName(this.missionGroupName);
        DeleteCompleteByUserIdResult result = null;
        try {
            result = this.client.deleteCompleteByUserId(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.mission.domain.model.CompleteDomain.createCacheKey(
                request.getMissionGroupName() != null ? request.getMissionGroupName().toString() : null
            ),
            Complete.class
        );
        CompleteDomain domain = this;

        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String userId,
        String missionGroupName,
        String childType
    )
    {
        return String.join(
            ":",
            "mission",
            namespaceName,
            userId,
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

    public Complete model() {
        Complete value = cache.get(
            parentKey,
            io.gs2.mission.domain.model.CompleteDomain.createCacheKey(
                this.getMissionGroupName() != null ? this.getMissionGroupName().toString() : null
            ),
            Complete.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetCompleteByUserIdRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.mission.domain.model.CompleteDomain.createCacheKey(
                        this.getMissionGroupName() != null ? this.getMissionGroupName().toString() : null
                    ),
                    Complete.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.mission.domain.model.CompleteDomain.createCacheKey(
                this.getMissionGroupName() != null ? this.getMissionGroupName().toString() : null
            ),
            Complete.class
        );
        }
        return value;
    }

}

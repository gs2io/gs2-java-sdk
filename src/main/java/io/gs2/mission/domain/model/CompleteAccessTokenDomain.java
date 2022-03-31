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


public class CompleteAccessTokenDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2MissionRestClient client;
    private final String namespaceName;
    private final AccessToken accessToken;
    private final String missionGroupName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return accessToken.getUserId();
    }
    public String getMissionGroupName() {
        return missionGroupName;
    }

    public CompleteAccessTokenDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        AccessToken accessToken,
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
        this.accessToken = accessToken;
        this.missionGroupName = missionGroupName;
        this.parentKey = io.gs2.mission.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.accessToken != null && this.accessToken.getUserId() != null ? this.accessToken.getUserId().toString() : null,
            "Complete"
        );
    }

    public io.gs2.core.domain.StampSheetDomain complete(
        CompleteRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withMissionGroupName(this.missionGroupName);
        CompleteResult result = this.client.complete(
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

    private Complete get(
        GetCompleteRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withMissionGroupName(this.missionGroupName);
        GetCompleteResult result = this.client.getComplete(
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
                    new GetCompleteRequest()
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
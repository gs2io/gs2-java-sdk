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
package io.gs2.experience.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.experience.Gs2ExperienceRestClient;
import io.gs2.experience.domain.iterator.*;
import io.gs2.experience.model.*;
import io.gs2.experience.request.*;
import io.gs2.experience.result.*;

import java.util.List;


public class ThresholdMasterDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2ExperienceRestClient client;
    private final String namespaceName;
    private final String thresholdName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getThresholdName() {
        return thresholdName;
    }

    public ThresholdMasterDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String thresholdName
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2ExperienceRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.thresholdName = thresholdName;
        this.parentKey = io.gs2.experience.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "ThresholdMaster"
        );
    }

    private ThresholdMaster get(
        GetThresholdMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withThresholdName(this.thresholdName);
        GetThresholdMasterResult result = this.client.getThresholdMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.experience.domain.model.ThresholdMasterDomain.createCacheKey(
                    request.getThresholdName() != null ? request.getThresholdName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public ThresholdMasterDomain update(
        UpdateThresholdMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withThresholdName(this.thresholdName);
        UpdateThresholdMasterResult result = this.client.updateThresholdMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.experience.domain.model.ThresholdMasterDomain.createCacheKey(
                    request.getThresholdName() != null ? request.getThresholdName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        ThresholdMasterDomain domain = this;

        return domain;
    }

    public ThresholdMasterDomain delete(
        DeleteThresholdMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withThresholdName(this.thresholdName);
        DeleteThresholdMasterResult result = null;
        try {
            result = this.client.deleteThresholdMaster(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.experience.domain.model.ThresholdMasterDomain.createCacheKey(
                request.getThresholdName() != null ? request.getThresholdName().toString() : null
            ),
            ThresholdMaster.class
        );
        ThresholdMasterDomain domain = this;

        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String thresholdName,
        String childType
    )
    {
        return String.join(
            ":",
            "experience",
            namespaceName,
            thresholdName,
            childType
        );
    }

    public static String createCacheKey(
        String thresholdName
    )
    {
        return String.join(
            ":",
            thresholdName
        );
    }

    public ThresholdMaster model() {
        ThresholdMaster value = cache.get(
            parentKey,
            io.gs2.experience.domain.model.ThresholdMasterDomain.createCacheKey(
                this.getThresholdName() != null ? this.getThresholdName().toString() : null
            ),
            ThresholdMaster.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetThresholdMasterRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.experience.domain.model.ThresholdMasterDomain.createCacheKey(
                        this.getThresholdName() != null ? this.getThresholdName().toString() : null
                    ),
                    ThresholdMaster.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.experience.domain.model.ThresholdMasterDomain.createCacheKey(
                this.getThresholdName() != null ? this.getThresholdName().toString() : null
            ),
            ThresholdMaster.class
        );
        }
        return value;
    }

}

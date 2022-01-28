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


public class ExperienceModelMasterDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2ExperienceRestClient client;
    private final String namespaceName;
    private final String experienceName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getExperienceName() {
        return experienceName;
    }

    public ExperienceModelMasterDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String experienceName
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2ExperienceRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.experienceName = experienceName;
        this.parentKey = io.gs2.experience.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "ExperienceModelMaster"
        );
    }

    private ExperienceModelMaster get(
        GetExperienceModelMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withExperienceName(this.experienceName);
        GetExperienceModelMasterResult result = this.client.getExperienceModelMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.experience.domain.model.ExperienceModelMasterDomain.createCacheKey(
                    request.getExperienceName() != null ? request.getExperienceName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public ExperienceModelMasterDomain update(
        UpdateExperienceModelMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withExperienceName(this.experienceName);
        UpdateExperienceModelMasterResult result = this.client.updateExperienceModelMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.experience.domain.model.ExperienceModelMasterDomain.createCacheKey(
                    request.getExperienceName() != null ? request.getExperienceName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        ExperienceModelMasterDomain domain = this;

        return domain;
    }

    public ExperienceModelMasterDomain delete(
        DeleteExperienceModelMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withExperienceName(this.experienceName);
        DeleteExperienceModelMasterResult result = null;
        try {
            result = this.client.deleteExperienceModelMaster(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.experience.domain.model.ExperienceModelMasterDomain.createCacheKey(
                request.getExperienceName() != null ? request.getExperienceName().toString() : null
            ),
            ExperienceModelMaster.class
        );
        ExperienceModelMasterDomain domain = this;

        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String experienceName,
        String childType
    )
    {
        return String.join(
            ":",
            "experience",
            namespaceName,
            experienceName,
            childType
        );
    }

    public static String createCacheKey(
        String experienceName
    )
    {
        return String.join(
            ":",
            experienceName
        );
    }

    public ExperienceModelMaster model() {
        ExperienceModelMaster value = cache.get(
            parentKey,
            io.gs2.experience.domain.model.ExperienceModelMasterDomain.createCacheKey(
                this.getExperienceName() != null ? this.getExperienceName().toString() : null
            ),
            ExperienceModelMaster.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetExperienceModelMasterRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.experience.domain.model.ExperienceModelMasterDomain.createCacheKey(
                        this.getExperienceName() != null ? this.getExperienceName().toString() : null
                    ),
                    ExperienceModelMaster.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.experience.domain.model.ExperienceModelMasterDomain.createCacheKey(
                this.getExperienceName() != null ? this.getExperienceName().toString() : null
            ),
            ExperienceModelMaster.class
        );
        }
        return value;
    }

}

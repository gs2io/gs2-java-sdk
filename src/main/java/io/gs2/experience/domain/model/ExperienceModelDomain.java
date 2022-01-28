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


public class ExperienceModelDomain {
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

    public ExperienceModelDomain(
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
            "ExperienceModel"
        );
    }

    private ExperienceModel get(
        GetExperienceModelRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withExperienceName(this.experienceName);
        GetExperienceModelResult result = this.client.getExperienceModel(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.experience.domain.model.ExperienceModelDomain.createCacheKey(
                    request.getExperienceName() != null ? request.getExperienceName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
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

    public ExperienceModel model() {
        ExperienceModel value = cache.get(
            parentKey,
            io.gs2.experience.domain.model.ExperienceModelDomain.createCacheKey(
                this.getExperienceName() != null ? this.getExperienceName().toString() : null
            ),
            ExperienceModel.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetExperienceModelRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.experience.domain.model.ExperienceModelDomain.createCacheKey(
                        this.getExperienceName() != null ? this.getExperienceName().toString() : null
                    ),
                    ExperienceModel.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.experience.domain.model.ExperienceModelDomain.createCacheKey(
                this.getExperienceName() != null ? this.getExperienceName().toString() : null
            ),
            ExperienceModel.class
        );
        }
        return value;
    }

}

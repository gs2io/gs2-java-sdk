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


public class StatusDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2ExperienceRestClient client;
    private final String namespaceName;
    private final String userId;
    private final String experienceName;
    private final String propertyId;

    private final String parentKey;
    String body;
    public String getBody() {
        return this.body;
    }
    String signature;
    public String getSignature() {
        return this.signature;
    }
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return userId;
    }
    public String getExperienceName() {
        return experienceName;
    }
    public String getPropertyId() {
        return propertyId;
    }

    public StatusDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String userId,
        String experienceName,
        String propertyId
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2ExperienceRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.userId = userId;
        this.experienceName = experienceName;
        this.propertyId = propertyId;
        this.parentKey = io.gs2.experience.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "Status"
        );
    }

    private Status get(
        GetStatusByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withExperienceName(this.experienceName)
            .withPropertyId(this.propertyId);
        GetStatusByUserIdResult result = this.client.getStatusByUserId(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.experience.domain.model.StatusDomain.createCacheKey(
                    request.getExperienceName() != null ? request.getExperienceName().toString() : null,
                    request.getPropertyId() != null ? request.getPropertyId().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public StatusDomain getWithSignature(
        GetStatusWithSignatureByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withExperienceName(this.experienceName)
            .withPropertyId(this.propertyId);
        GetStatusWithSignatureByUserIdResult result = this.client.getStatusWithSignatureByUserId(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.experience.domain.model.StatusDomain.createCacheKey(
                    request.getExperienceName() != null ? request.getExperienceName().toString() : null,
                    request.getPropertyId() != null ? request.getPropertyId().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        StatusDomain domain = this;
        domain.body = result.getBody();
        domain.signature = result.getSignature();

        return domain;
    }

    public StatusDomain addExperience(
        AddExperienceByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withExperienceName(this.experienceName)
            .withPropertyId(this.propertyId);
        AddExperienceByUserIdResult result = this.client.addExperienceByUserId(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.experience.domain.model.StatusDomain.createCacheKey(
                    request.getExperienceName() != null ? request.getExperienceName().toString() : null,
                    request.getPropertyId() != null ? request.getPropertyId().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        StatusDomain domain = this;

        return domain;
    }

    public StatusDomain setExperience(
        SetExperienceByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withExperienceName(this.experienceName)
            .withPropertyId(this.propertyId);
        SetExperienceByUserIdResult result = this.client.setExperienceByUserId(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.experience.domain.model.StatusDomain.createCacheKey(
                    request.getExperienceName() != null ? request.getExperienceName().toString() : null,
                    request.getPropertyId() != null ? request.getPropertyId().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        StatusDomain domain = this;

        return domain;
    }

    public StatusDomain addRankCap(
        AddRankCapByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withExperienceName(this.experienceName)
            .withPropertyId(this.propertyId);
        AddRankCapByUserIdResult result = this.client.addRankCapByUserId(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.experience.domain.model.StatusDomain.createCacheKey(
                    request.getExperienceName() != null ? request.getExperienceName().toString() : null,
                    request.getPropertyId() != null ? request.getPropertyId().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        StatusDomain domain = this;

        return domain;
    }

    public StatusDomain setRankCap(
        SetRankCapByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withExperienceName(this.experienceName)
            .withPropertyId(this.propertyId);
        SetRankCapByUserIdResult result = this.client.setRankCapByUserId(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.experience.domain.model.StatusDomain.createCacheKey(
                    request.getExperienceName() != null ? request.getExperienceName().toString() : null,
                    request.getPropertyId() != null ? request.getPropertyId().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        StatusDomain domain = this;

        return domain;
    }

    public StatusDomain delete(
        DeleteStatusByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withExperienceName(this.experienceName)
            .withPropertyId(this.propertyId);
        DeleteStatusByUserIdResult result = null;
        try {
            result = this.client.deleteStatusByUserId(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.experience.domain.model.StatusDomain.createCacheKey(
                request.getExperienceName() != null ? request.getExperienceName().toString() : null,
                request.getPropertyId() != null ? request.getPropertyId().toString() : null
            ),
            Status.class
        );
        StatusDomain domain = this;

        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String userId,
        String experienceName,
        String propertyId,
        String childType
    )
    {
        return String.join(
            ":",
            "experience",
            namespaceName,
            userId,
            experienceName,
            propertyId,
            childType
        );
    }

    public static String createCacheKey(
        String experienceName,
        String propertyId
    )
    {
        return String.join(
            ":",
            experienceName,
            propertyId
        );
    }

    public Status model() {
        Status value = cache.get(
            parentKey,
            io.gs2.experience.domain.model.StatusDomain.createCacheKey(
                this.getExperienceName() != null ? this.getExperienceName().toString() : null,
                this.getPropertyId() != null ? this.getPropertyId().toString() : null
            ),
            Status.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetStatusByUserIdRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.experience.domain.model.StatusDomain.createCacheKey(
                        this.getExperienceName() != null ? this.getExperienceName().toString() : null,
                        this.getPropertyId() != null ? this.getPropertyId().toString() : null
                    ),
                    Status.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.experience.domain.model.StatusDomain.createCacheKey(
                this.getExperienceName() != null ? this.getExperienceName().toString() : null,
                this.getPropertyId() != null ? this.getPropertyId().toString() : null
            ),
            Status.class
        );
        }
        return value;
    }

}

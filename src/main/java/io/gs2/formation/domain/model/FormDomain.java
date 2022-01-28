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
package io.gs2.formation.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.formation.Gs2FormationRestClient;
import io.gs2.formation.domain.iterator.*;
import io.gs2.formation.model.*;
import io.gs2.formation.request.*;
import io.gs2.formation.result.*;

import java.util.List;


public class FormDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2FormationRestClient client;
    private final String namespaceName;
    private final String userId;
    private final String moldName;
    private final Integer index;

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
    public String getMoldName() {
        return moldName;
    }
    public Integer getIndex() {
        return index;
    }

    public FormDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String userId,
        String moldName,
        Integer index
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2FormationRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.userId = userId;
        this.moldName = moldName;
        this.index = index;
        this.parentKey = io.gs2.formation.domain.model.MoldDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            this.moldName != null ? this.moldName.toString() : null,
            "Form"
        );
    }

    private Form get(
        GetFormByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withMoldName(this.moldName)
            .withIndex(this.index);
        GetFormByUserIdResult result = this.client.getFormByUserId(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.formation.domain.model.FormDomain.createCacheKey(
                    request.getIndex() != null ? request.getIndex().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
                
        if (result.getMold() != null) {
            cache.put(
                parentKey,
                io.gs2.formation.domain.model.MoldDomain.createCacheKey(
                    request.getMoldName() != null ? request.getMoldName().toString() : null
                ),
                result.getMold(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
                
        if (result.getMoldModel() != null) {
            cache.put(
                parentKey,
                io.gs2.formation.domain.model.MoldModelDomain.createCacheKey(
                    request.getMoldName() != null ? request.getMoldName().toString() : null
                ),
                result.getMoldModel(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
                
        if (result.getFormModel() != null) {
            cache.put(
                parentKey,
                io.gs2.formation.domain.model.FormModelDomain.createCacheKey(
                    result.getFormModel().getName() != null ? result.getFormModel().getName().toString() : null
                ),
                result.getFormModel(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public FormDomain getWithSignature(
        GetFormWithSignatureByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withMoldName(this.moldName)
            .withIndex(this.index);
        GetFormWithSignatureByUserIdResult result = this.client.getFormWithSignatureByUserId(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.formation.domain.model.FormDomain.createCacheKey(
                    request.getIndex() != null ? request.getIndex().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
                
        if (result.getMold() != null) {
            cache.put(
                parentKey,
                io.gs2.formation.domain.model.MoldDomain.createCacheKey(
                    request.getMoldName() != null ? request.getMoldName().toString() : null
                ),
                result.getMold(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
                
        if (result.getMoldModel() != null) {
            cache.put(
                parentKey,
                io.gs2.formation.domain.model.MoldModelDomain.createCacheKey(
                    request.getMoldName() != null ? request.getMoldName().toString() : null
                ),
                result.getMoldModel(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
                
        if (result.getFormModel() != null) {
            cache.put(
                parentKey,
                io.gs2.formation.domain.model.FormModelDomain.createCacheKey(
                    result.getFormModel().getName() != null ? result.getFormModel().getName().toString() : null
                ),
                result.getFormModel(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        FormDomain domain = this;
        domain.body = result.getBody();
        domain.signature = result.getSignature();

        return domain;
    }

    public FormDomain set(
        SetFormByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withMoldName(this.moldName)
            .withIndex(this.index);
        SetFormByUserIdResult result = this.client.setFormByUserId(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.formation.domain.model.FormDomain.createCacheKey(
                    request.getIndex() != null ? request.getIndex().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
                
        if (result.getMold() != null) {
            cache.put(
                parentKey,
                io.gs2.formation.domain.model.MoldDomain.createCacheKey(
                    request.getMoldName() != null ? request.getMoldName().toString() : null
                ),
                result.getMold(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
                
        if (result.getMoldModel() != null) {
            cache.put(
                parentKey,
                io.gs2.formation.domain.model.MoldModelDomain.createCacheKey(
                    request.getMoldName() != null ? request.getMoldName().toString() : null
                ),
                result.getMoldModel(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
                
        if (result.getFormModel() != null) {
            cache.put(
                parentKey,
                io.gs2.formation.domain.model.FormModelDomain.createCacheKey(
                    result.getFormModel().getName() != null ? result.getFormModel().getName().toString() : null
                ),
                result.getFormModel(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        FormDomain domain = this;

        return domain;
    }

    public io.gs2.core.domain.StampSheetDomain acquireActionsToProperties(
        AcquireActionsToFormPropertiesRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withMoldName(this.moldName)
            .withIndex(this.index);
        AcquireActionsToFormPropertiesResult result = this.client.acquireActionsToFormProperties(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.formation.domain.model.FormDomain.createCacheKey(
                    request.getIndex() != null ? request.getIndex().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
                
        if (result.getMold() != null) {
            cache.put(
                parentKey,
                io.gs2.formation.domain.model.MoldDomain.createCacheKey(
                    request.getMoldName() != null ? request.getMoldName().toString() : null
                ),
                result.getMold(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
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

    public FormDomain delete(
        DeleteFormByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withMoldName(this.moldName)
            .withIndex(this.index);
        DeleteFormByUserIdResult result = null;
        try {
            result = this.client.deleteFormByUserId(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.formation.domain.model.FormDomain.createCacheKey(
                request.getIndex() != null ? request.getIndex().toString() : null
            ),
            Form.class
        );
        FormDomain domain = this;

        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String userId,
        String moldName,
        String index,
        String childType
    )
    {
        return String.join(
            ":",
            "formation",
            namespaceName,
            userId,
            moldName,
            index,
            childType
        );
    }

    public static String createCacheKey(
        String index
    )
    {
        return String.join(
            ":",
            index
        );
    }

    public Form model() {
        Form value = cache.get(
            parentKey,
            io.gs2.formation.domain.model.FormDomain.createCacheKey(
                this.getIndex() != null ? this.getIndex().toString() : null
            ),
            Form.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetFormByUserIdRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.formation.domain.model.FormDomain.createCacheKey(
                        this.getIndex() != null ? this.getIndex().toString() : null
                    ),
                    Form.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.formation.domain.model.FormDomain.createCacheKey(
                this.getIndex() != null ? this.getIndex().toString() : null
            ),
            Form.class
        );
        }
        return value;
    }

}

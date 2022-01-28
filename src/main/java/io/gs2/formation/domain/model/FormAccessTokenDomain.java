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


public class FormAccessTokenDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2FormationRestClient client;
    private final String namespaceName;
    private final AccessToken accessToken;
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
        return accessToken.getUserId();
    }
    public String getMoldName() {
        return moldName;
    }
    public Integer getIndex() {
        return index;
    }

    public FormAccessTokenDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        AccessToken accessToken,
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
        this.accessToken = accessToken;
        this.moldName = moldName;
        this.index = index;
        this.parentKey = io.gs2.formation.domain.model.MoldDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.accessToken != null && this.accessToken.getUserId() != null ? this.accessToken.getUserId().toString() : null,
            this.moldName != null ? this.moldName.toString() : null,
            "Form"
        );
    }

    private Form get(
        GetFormRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withMoldName(this.moldName)
            .withIndex(this.index);
        GetFormResult result = this.client.getForm(
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

    public FormAccessTokenDomain getWithSignature(
        GetFormWithSignatureRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withMoldName(this.moldName)
            .withIndex(this.index);
        GetFormWithSignatureResult result = this.client.getFormWithSignature(
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
        FormAccessTokenDomain domain = this;
        domain.body = result.getBody();
        domain.signature = result.getSignature();

        return domain;
    }

    public FormAccessTokenDomain setWithSignature(
        SetFormWithSignatureRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withMoldName(this.moldName)
            .withIndex(this.index);
        SetFormWithSignatureResult result = this.client.setFormWithSignature(
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
        FormAccessTokenDomain domain = this;

        return domain;
    }

    public FormAccessTokenDomain delete(
        DeleteFormRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withMoldName(this.moldName)
            .withIndex(this.index);
        DeleteFormResult result = null;
        try {
            result = this.client.deleteForm(
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
        FormAccessTokenDomain domain = this;

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
                    new GetFormRequest()
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

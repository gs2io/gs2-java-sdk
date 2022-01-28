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


public class FormModelMasterDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2FormationRestClient client;
    private final String namespaceName;
    private final String formModelName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getFormModelName() {
        return formModelName;
    }

    public FormModelMasterDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String formModelName
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2FormationRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.formModelName = formModelName;
        this.parentKey = io.gs2.formation.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "FormModelMaster"
        );
    }

    private FormModelMaster get(
        GetFormModelMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withFormModelName(this.formModelName);
        GetFormModelMasterResult result = this.client.getFormModelMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.formation.domain.model.FormModelMasterDomain.createCacheKey(
                    request.getFormModelName() != null ? request.getFormModelName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public FormModelMasterDomain update(
        UpdateFormModelMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withFormModelName(this.formModelName);
        UpdateFormModelMasterResult result = this.client.updateFormModelMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.formation.domain.model.FormModelMasterDomain.createCacheKey(
                    request.getFormModelName() != null ? request.getFormModelName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        FormModelMasterDomain domain = this;

        return domain;
    }

    public FormModelMasterDomain delete(
        DeleteFormModelMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withFormModelName(this.formModelName);
        DeleteFormModelMasterResult result = null;
        try {
            result = this.client.deleteFormModelMaster(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.formation.domain.model.FormModelMasterDomain.createCacheKey(
                request.getFormModelName() != null ? request.getFormModelName().toString() : null
            ),
            FormModelMaster.class
        );
        FormModelMasterDomain domain = this;

        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String formModelName,
        String childType
    )
    {
        return String.join(
            ":",
            "formation",
            namespaceName,
            formModelName,
            childType
        );
    }

    public static String createCacheKey(
        String formModelName
    )
    {
        return String.join(
            ":",
            formModelName
        );
    }

    public FormModelMaster model() {
        FormModelMaster value = cache.get(
            parentKey,
            io.gs2.formation.domain.model.FormModelMasterDomain.createCacheKey(
                this.getFormModelName() != null ? this.getFormModelName().toString() : null
            ),
            FormModelMaster.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetFormModelMasterRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.formation.domain.model.FormModelMasterDomain.createCacheKey(
                        this.getFormModelName() != null ? this.getFormModelName().toString() : null
                    ),
                    FormModelMaster.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.formation.domain.model.FormModelMasterDomain.createCacheKey(
                this.getFormModelName() != null ? this.getFormModelName().toString() : null
            ),
            FormModelMaster.class
        );
        }
        return value;
    }

}

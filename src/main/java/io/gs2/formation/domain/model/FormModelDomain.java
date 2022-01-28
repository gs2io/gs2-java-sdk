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


public class FormModelDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2FormationRestClient client;
    private final String namespaceName;
    private final String moldName;
    private final String formModelName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getMoldName() {
        return moldName;
    }
    public String getFormModelName() {
        return formModelName;
    }

    public FormModelDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String moldName,
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
        this.moldName = moldName;
        this.formModelName = formModelName;
        this.parentKey = io.gs2.formation.domain.model.MoldModelDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.moldName != null ? this.moldName.toString() : null,
            "FormModel"
        );
    }

    public static String createCacheParentKey(
        String namespaceName,
        String moldName,
        String formModelName,
        String childType
    )
    {
        return String.join(
            ":",
            "formation",
            namespaceName,
            moldName,
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

    public FormModel model() {
        FormModel value = cache.get(
            parentKey,
            io.gs2.formation.domain.model.FormModelDomain.createCacheKey(
                this.getFormModelName() != null ? this.getFormModelName().toString() : null
            ),
            FormModel.class
        );
        return value;
    }

}

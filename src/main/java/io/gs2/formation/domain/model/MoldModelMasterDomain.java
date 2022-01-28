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


public class MoldModelMasterDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2FormationRestClient client;
    private final String namespaceName;
    private final String moldName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getMoldName() {
        return moldName;
    }

    public MoldModelMasterDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String moldName
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
        this.parentKey = io.gs2.formation.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "MoldModelMaster"
        );
    }

    private MoldModelMaster get(
        GetMoldModelMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withMoldName(this.moldName);
        GetMoldModelMasterResult result = this.client.getMoldModelMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.formation.domain.model.MoldModelMasterDomain.createCacheKey(
                    request.getMoldName() != null ? request.getMoldName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public MoldModelMasterDomain update(
        UpdateMoldModelMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withMoldName(this.moldName);
        UpdateMoldModelMasterResult result = this.client.updateMoldModelMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.formation.domain.model.MoldModelMasterDomain.createCacheKey(
                    request.getMoldName() != null ? request.getMoldName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        MoldModelMasterDomain domain = this;

        return domain;
    }

    public MoldModelMasterDomain delete(
        DeleteMoldModelMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withMoldName(this.moldName);
        DeleteMoldModelMasterResult result = null;
        try {
            result = this.client.deleteMoldModelMaster(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.formation.domain.model.MoldModelMasterDomain.createCacheKey(
                request.getMoldName() != null ? request.getMoldName().toString() : null
            ),
            MoldModelMaster.class
        );
        MoldModelMasterDomain domain = this;

        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String moldName,
        String childType
    )
    {
        return String.join(
            ":",
            "formation",
            namespaceName,
            moldName,
            childType
        );
    }

    public static String createCacheKey(
        String moldName
    )
    {
        return String.join(
            ":",
            moldName
        );
    }

    public MoldModelMaster model() {
        MoldModelMaster value = cache.get(
            parentKey,
            io.gs2.formation.domain.model.MoldModelMasterDomain.createCacheKey(
                this.getMoldName() != null ? this.getMoldName().toString() : null
            ),
            MoldModelMaster.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetMoldModelMasterRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.formation.domain.model.MoldModelMasterDomain.createCacheKey(
                        this.getMoldName() != null ? this.getMoldName().toString() : null
                    ),
                    MoldModelMaster.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.formation.domain.model.MoldModelMasterDomain.createCacheKey(
                this.getMoldName() != null ? this.getMoldName().toString() : null
            ),
            MoldModelMaster.class
        );
        }
        return value;
    }

}

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
package io.gs2.stamina.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.stamina.Gs2StaminaRestClient;
import io.gs2.stamina.domain.iterator.*;
import io.gs2.stamina.model.*;
import io.gs2.stamina.request.*;
import io.gs2.stamina.result.*;

import java.util.List;


public class RecoverValueTableMasterDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2StaminaRestClient client;
    private final String namespaceName;
    private final String recoverValueTableName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getRecoverValueTableName() {
        return recoverValueTableName;
    }

    public RecoverValueTableMasterDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String recoverValueTableName
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2StaminaRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.recoverValueTableName = recoverValueTableName;
        this.parentKey = io.gs2.stamina.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "RecoverValueTableMaster"
        );
    }

    private RecoverValueTableMaster get(
        GetRecoverValueTableMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withRecoverValueTableName(this.recoverValueTableName);
        GetRecoverValueTableMasterResult result = this.client.getRecoverValueTableMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.stamina.domain.model.RecoverValueTableMasterDomain.createCacheKey(
                    request.getRecoverValueTableName() != null ? request.getRecoverValueTableName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public RecoverValueTableMasterDomain update(
        UpdateRecoverValueTableMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withRecoverValueTableName(this.recoverValueTableName);
        UpdateRecoverValueTableMasterResult result = this.client.updateRecoverValueTableMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.stamina.domain.model.RecoverValueTableMasterDomain.createCacheKey(
                    request.getRecoverValueTableName() != null ? request.getRecoverValueTableName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        RecoverValueTableMasterDomain domain = this;

        return domain;
    }

    public RecoverValueTableMasterDomain delete(
        DeleteRecoverValueTableMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withRecoverValueTableName(this.recoverValueTableName);
        DeleteRecoverValueTableMasterResult result = null;
        try {
            result = this.client.deleteRecoverValueTableMaster(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.stamina.domain.model.RecoverValueTableMasterDomain.createCacheKey(
                request.getRecoverValueTableName() != null ? request.getRecoverValueTableName().toString() : null
            ),
            RecoverValueTableMaster.class
        );
        RecoverValueTableMasterDomain domain = this;

        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String recoverValueTableName,
        String childType
    )
    {
        return String.join(
            ":",
            "stamina",
            namespaceName,
            recoverValueTableName,
            childType
        );
    }

    public static String createCacheKey(
        String recoverValueTableName
    )
    {
        return String.join(
            ":",
            recoverValueTableName
        );
    }

    public RecoverValueTableMaster model() {
        RecoverValueTableMaster value = cache.get(
            parentKey,
            io.gs2.stamina.domain.model.RecoverValueTableMasterDomain.createCacheKey(
                this.getRecoverValueTableName() != null ? this.getRecoverValueTableName().toString() : null
            ),
            RecoverValueTableMaster.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetRecoverValueTableMasterRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.stamina.domain.model.RecoverValueTableMasterDomain.createCacheKey(
                        this.getRecoverValueTableName() != null ? this.getRecoverValueTableName().toString() : null
                    ),
                    RecoverValueTableMaster.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.stamina.domain.model.RecoverValueTableMasterDomain.createCacheKey(
                this.getRecoverValueTableName() != null ? this.getRecoverValueTableName().toString() : null
            ),
            RecoverValueTableMaster.class
        );
        }
        return value;
    }

}

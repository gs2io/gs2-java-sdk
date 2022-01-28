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


public class StaminaModelDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2StaminaRestClient client;
    private final String namespaceName;
    private final String staminaName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getStaminaName() {
        return staminaName;
    }

    public StaminaModelDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String staminaName
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2StaminaRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.staminaName = staminaName;
        this.parentKey = io.gs2.stamina.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "StaminaModel"
        );
    }

    private StaminaModel get(
        GetStaminaModelRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withStaminaName(this.staminaName);
        GetStaminaModelResult result = this.client.getStaminaModel(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.stamina.domain.model.StaminaModelDomain.createCacheKey(
                    request.getStaminaName() != null ? request.getStaminaName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public static String createCacheParentKey(
        String namespaceName,
        String staminaName,
        String childType
    )
    {
        return String.join(
            ":",
            "stamina",
            namespaceName,
            staminaName,
            childType
        );
    }

    public static String createCacheKey(
        String staminaName
    )
    {
        return String.join(
            ":",
            staminaName
        );
    }

    public StaminaModel model() {
        StaminaModel value = cache.get(
            parentKey,
            io.gs2.stamina.domain.model.StaminaModelDomain.createCacheKey(
                this.getStaminaName() != null ? this.getStaminaName().toString() : null
            ),
            StaminaModel.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetStaminaModelRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.stamina.domain.model.StaminaModelDomain.createCacheKey(
                        this.getStaminaName() != null ? this.getStaminaName().toString() : null
                    ),
                    StaminaModel.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.stamina.domain.model.StaminaModelDomain.createCacheKey(
                this.getStaminaName() != null ? this.getStaminaName().toString() : null
            ),
            StaminaModel.class
        );
        }
        return value;
    }

}

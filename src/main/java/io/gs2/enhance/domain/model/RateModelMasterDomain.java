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
package io.gs2.enhance.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.enhance.Gs2EnhanceRestClient;
import io.gs2.enhance.domain.iterator.*;
import io.gs2.enhance.model.*;
import io.gs2.enhance.request.*;
import io.gs2.enhance.result.*;

import java.util.List;


public class RateModelMasterDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2EnhanceRestClient client;
    private final String namespaceName;
    private final String rateName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getRateName() {
        return rateName;
    }

    public RateModelMasterDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String rateName
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2EnhanceRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.rateName = rateName;
        this.parentKey = io.gs2.enhance.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "RateModelMaster"
        );
    }

    private RateModelMaster get(
        GetRateModelMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withRateName(this.rateName);
        GetRateModelMasterResult result = this.client.getRateModelMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.enhance.domain.model.RateModelMasterDomain.createCacheKey(
                    request.getRateName() != null ? request.getRateName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public RateModelMasterDomain update(
        UpdateRateModelMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withRateName(this.rateName);
        UpdateRateModelMasterResult result = this.client.updateRateModelMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.enhance.domain.model.RateModelMasterDomain.createCacheKey(
                    request.getRateName() != null ? request.getRateName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        RateModelMasterDomain domain = this;

        return domain;
    }

    public RateModelMasterDomain delete(
        DeleteRateModelMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withRateName(this.rateName);
        DeleteRateModelMasterResult result = null;
        try {
            result = this.client.deleteRateModelMaster(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.enhance.domain.model.RateModelMasterDomain.createCacheKey(
                request.getRateName() != null ? request.getRateName().toString() : null
            ),
            RateModelMaster.class
        );
        RateModelMasterDomain domain = this;

        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String rateName,
        String childType
    )
    {
        return String.join(
            ":",
            "enhance",
            namespaceName,
            rateName,
            childType
        );
    }

    public static String createCacheKey(
        String rateName
    )
    {
        return String.join(
            ":",
            rateName
        );
    }

    public RateModelMaster model() {
        RateModelMaster value = cache.get(
            parentKey,
            io.gs2.enhance.domain.model.RateModelMasterDomain.createCacheKey(
                this.getRateName() != null ? this.getRateName().toString() : null
            ),
            RateModelMaster.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetRateModelMasterRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.enhance.domain.model.RateModelMasterDomain.createCacheKey(
                        this.getRateName() != null ? this.getRateName().toString() : null
                    ),
                    RateModelMaster.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.enhance.domain.model.RateModelMasterDomain.createCacheKey(
                this.getRateName() != null ? this.getRateName().toString() : null
            ),
            RateModelMaster.class
        );
        }
        return value;
    }

}

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


public class RateModelDomain {
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

    public RateModelDomain(
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
            "RateModel"
        );
    }

    private RateModel get(
        GetRateModelRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withRateName(this.rateName);
        GetRateModelResult result = this.client.getRateModel(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.enhance.domain.model.RateModelDomain.createCacheKey(
                    request.getRateName() != null ? request.getRateName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
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

    public RateModel model() {
        RateModel value = cache.get(
            parentKey,
            io.gs2.enhance.domain.model.RateModelDomain.createCacheKey(
                this.getRateName() != null ? this.getRateName().toString() : null
            ),
            RateModel.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetRateModelRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.enhance.domain.model.RateModelDomain.createCacheKey(
                        this.getRateName() != null ? this.getRateName().toString() : null
                    ),
                    RateModel.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.enhance.domain.model.RateModelDomain.createCacheKey(
                this.getRateName() != null ? this.getRateName().toString() : null
            ),
            RateModel.class
        );
        }
        return value;
    }

}

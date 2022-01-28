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
package io.gs2.limit.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.limit.Gs2LimitRestClient;
import io.gs2.limit.domain.iterator.*;
import io.gs2.limit.model.*;
import io.gs2.limit.request.*;
import io.gs2.limit.result.*;

import java.util.List;


public class LimitModelDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2LimitRestClient client;
    private final String namespaceName;
    private final String limitName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getLimitName() {
        return limitName;
    }

    public LimitModelDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String limitName
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2LimitRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.limitName = limitName;
        this.parentKey = io.gs2.limit.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "LimitModel"
        );
    }

    private LimitModel get(
        GetLimitModelRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withLimitName(this.limitName);
        GetLimitModelResult result = this.client.getLimitModel(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.limit.domain.model.LimitModelDomain.createCacheKey(
                    request.getLimitName() != null ? request.getLimitName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public static String createCacheParentKey(
        String namespaceName,
        String limitName,
        String childType
    )
    {
        return String.join(
            ":",
            "limit",
            namespaceName,
            limitName,
            childType
        );
    }

    public static String createCacheKey(
        String limitName
    )
    {
        return String.join(
            ":",
            limitName
        );
    }

    public LimitModel model() {
        LimitModel value = cache.get(
            parentKey,
            io.gs2.limit.domain.model.LimitModelDomain.createCacheKey(
                this.getLimitName() != null ? this.getLimitName().toString() : null
            ),
            LimitModel.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetLimitModelRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.limit.domain.model.LimitModelDomain.createCacheKey(
                        this.getLimitName() != null ? this.getLimitName().toString() : null
                    ),
                    LimitModel.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.limit.domain.model.LimitModelDomain.createCacheKey(
                this.getLimitName() != null ? this.getLimitName().toString() : null
            ),
            LimitModel.class
        );
        }
        return value;
    }

}

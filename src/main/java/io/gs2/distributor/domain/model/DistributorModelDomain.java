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
package io.gs2.distributor.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.distributor.Gs2DistributorRestClient;
import io.gs2.distributor.domain.iterator.*;
import io.gs2.distributor.model.*;
import io.gs2.distributor.request.*;
import io.gs2.distributor.result.*;

import java.util.List;


public class DistributorModelDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2DistributorRestClient client;
    private final String namespaceName;
    private final String distributorName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getDistributorName() {
        return distributorName;
    }

    public DistributorModelDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String distributorName
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2DistributorRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.distributorName = distributorName;
        this.parentKey = io.gs2.distributor.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "DistributorModel"
        );
    }

    private DistributorModel get(
        GetDistributorModelRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withDistributorName(this.distributorName);
        GetDistributorModelResult result = this.client.getDistributorModel(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.distributor.domain.model.DistributorModelDomain.createCacheKey(
                    request.getDistributorName() != null ? request.getDistributorName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public static String createCacheParentKey(
        String namespaceName,
        String distributorName,
        String childType
    )
    {
        return String.join(
            ":",
            "distributor",
            namespaceName,
            distributorName,
            childType
        );
    }

    public static String createCacheKey(
        String distributorName
    )
    {
        return String.join(
            ":",
            distributorName
        );
    }

    public DistributorModel model() {
        DistributorModel value = cache.get(
            parentKey,
            io.gs2.distributor.domain.model.DistributorModelDomain.createCacheKey(
                this.getDistributorName() != null ? this.getDistributorName().toString() : null
            ),
            DistributorModel.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetDistributorModelRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.distributor.domain.model.DistributorModelDomain.createCacheKey(
                        this.getDistributorName() != null ? this.getDistributorName().toString() : null
                    ),
                    DistributorModel.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.distributor.domain.model.DistributorModelDomain.createCacheKey(
                this.getDistributorName() != null ? this.getDistributorName().toString() : null
            ),
            DistributorModel.class
        );
        }
        return value;
    }

}

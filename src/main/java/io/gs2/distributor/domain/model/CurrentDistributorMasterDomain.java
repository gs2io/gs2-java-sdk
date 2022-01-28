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


public class CurrentDistributorMasterDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2DistributorRestClient client;
    private final String namespaceName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }

    public CurrentDistributorMasterDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2DistributorRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.parentKey = io.gs2.distributor.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "CurrentDistributorMaster"
        );
    }

    public CurrentDistributorMasterDomain exportMaster(
        ExportMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName);
        ExportMasterResult result = this.client.exportMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.distributor.domain.model.CurrentDistributorMasterDomain.createCacheKey(
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        CurrentDistributorMasterDomain domain = this;

        return domain;
    }

    private CurrentDistributorMaster get(
        GetCurrentDistributorMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName);
        GetCurrentDistributorMasterResult result = this.client.getCurrentDistributorMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.distributor.domain.model.CurrentDistributorMasterDomain.createCacheKey(
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public CurrentDistributorMasterDomain update(
        UpdateCurrentDistributorMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName);
        UpdateCurrentDistributorMasterResult result = this.client.updateCurrentDistributorMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.distributor.domain.model.CurrentDistributorMasterDomain.createCacheKey(
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        CurrentDistributorMasterDomain domain = this;

        return domain;
    }

    public CurrentDistributorMasterDomain updateFromGitHub(
        UpdateCurrentDistributorMasterFromGitHubRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName);
        UpdateCurrentDistributorMasterFromGitHubResult result = this.client.updateCurrentDistributorMasterFromGitHub(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.distributor.domain.model.CurrentDistributorMasterDomain.createCacheKey(
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        CurrentDistributorMasterDomain domain = this;

        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String childType
    )
    {
        return String.join(
            ":",
            "distributor",
            namespaceName,
            childType
        );
    }

    public static String createCacheKey(
    )
    {
        return "Singleton";
    }

    public CurrentDistributorMaster model() {
        CurrentDistributorMaster value = cache.get(
            parentKey,
            io.gs2.distributor.domain.model.CurrentDistributorMasterDomain.createCacheKey(
            ),
            CurrentDistributorMaster.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetCurrentDistributorMasterRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.distributor.domain.model.CurrentDistributorMasterDomain.createCacheKey(
                    ),
                    CurrentDistributorMaster.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.distributor.domain.model.CurrentDistributorMasterDomain.createCacheKey(
            ),
            CurrentDistributorMaster.class
        );
        }
        return value;
    }

}

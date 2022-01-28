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
package io.gs2.inbox.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.inbox.Gs2InboxRestClient;
import io.gs2.inbox.domain.iterator.*;
import io.gs2.inbox.model.*;
import io.gs2.inbox.request.*;
import io.gs2.inbox.result.*;

import java.util.List;


public class GlobalMessageMasterDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2InboxRestClient client;
    private final String namespaceName;
    private final String globalMessageName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getGlobalMessageName() {
        return globalMessageName;
    }

    public GlobalMessageMasterDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String globalMessageName
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2InboxRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.globalMessageName = globalMessageName;
        this.parentKey = io.gs2.inbox.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "GlobalMessageMaster"
        );
    }

    private GlobalMessageMaster get(
        GetGlobalMessageMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withGlobalMessageName(this.globalMessageName);
        GetGlobalMessageMasterResult result = this.client.getGlobalMessageMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.inbox.domain.model.GlobalMessageMasterDomain.createCacheKey(
                    request.getGlobalMessageName() != null ? request.getGlobalMessageName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public GlobalMessageMasterDomain update(
        UpdateGlobalMessageMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withGlobalMessageName(this.globalMessageName);
        UpdateGlobalMessageMasterResult result = this.client.updateGlobalMessageMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.inbox.domain.model.GlobalMessageMasterDomain.createCacheKey(
                    request.getGlobalMessageName() != null ? request.getGlobalMessageName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        GlobalMessageMasterDomain domain = this;

        return domain;
    }

    public GlobalMessageMasterDomain delete(
        DeleteGlobalMessageMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withGlobalMessageName(this.globalMessageName);
        DeleteGlobalMessageMasterResult result = null;
        try {
            result = this.client.deleteGlobalMessageMaster(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.inbox.domain.model.GlobalMessageMasterDomain.createCacheKey(
                request.getGlobalMessageName() != null ? request.getGlobalMessageName().toString() : null
            ),
            GlobalMessageMaster.class
        );
        GlobalMessageMasterDomain domain = this;

        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String globalMessageName,
        String childType
    )
    {
        return String.join(
            ":",
            "inbox",
            namespaceName,
            globalMessageName,
            childType
        );
    }

    public static String createCacheKey(
        String globalMessageName
    )
    {
        return String.join(
            ":",
            globalMessageName
        );
    }

    public GlobalMessageMaster model() {
        GlobalMessageMaster value = cache.get(
            parentKey,
            io.gs2.inbox.domain.model.GlobalMessageMasterDomain.createCacheKey(
                this.getGlobalMessageName() != null ? this.getGlobalMessageName().toString() : null
            ),
            GlobalMessageMaster.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetGlobalMessageMasterRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.inbox.domain.model.GlobalMessageMasterDomain.createCacheKey(
                        this.getGlobalMessageName() != null ? this.getGlobalMessageName().toString() : null
                    ),
                    GlobalMessageMaster.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.inbox.domain.model.GlobalMessageMasterDomain.createCacheKey(
                this.getGlobalMessageName() != null ? this.getGlobalMessageName().toString() : null
            ),
            GlobalMessageMaster.class
        );
        }
        return value;
    }

}

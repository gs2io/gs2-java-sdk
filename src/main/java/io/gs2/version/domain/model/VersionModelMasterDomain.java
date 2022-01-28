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
package io.gs2.version.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.version.Gs2VersionRestClient;
import io.gs2.version.domain.iterator.*;
import io.gs2.version.model.*;
import io.gs2.version.request.*;
import io.gs2.version.result.*;

import java.util.List;


public class VersionModelMasterDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2VersionRestClient client;
    private final String namespaceName;
    private final String versionName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getVersionName() {
        return versionName;
    }

    public VersionModelMasterDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String versionName
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2VersionRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.versionName = versionName;
        this.parentKey = io.gs2.version.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "VersionModelMaster"
        );
    }

    private VersionModelMaster get(
        GetVersionModelMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withVersionName(this.versionName);
        GetVersionModelMasterResult result = this.client.getVersionModelMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.version.domain.model.VersionModelMasterDomain.createCacheKey(
                    request.getVersionName() != null ? request.getVersionName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public VersionModelMasterDomain update(
        UpdateVersionModelMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withVersionName(this.versionName);
        UpdateVersionModelMasterResult result = this.client.updateVersionModelMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.version.domain.model.VersionModelMasterDomain.createCacheKey(
                    request.getVersionName() != null ? request.getVersionName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        VersionModelMasterDomain domain = this;

        return domain;
    }

    public VersionModelMasterDomain delete(
        DeleteVersionModelMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withVersionName(this.versionName);
        DeleteVersionModelMasterResult result = null;
        try {
            result = this.client.deleteVersionModelMaster(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.version.domain.model.VersionModelMasterDomain.createCacheKey(
                request.getVersionName() != null ? request.getVersionName().toString() : null
            ),
            VersionModelMaster.class
        );
        VersionModelMasterDomain domain = this;

        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String versionName,
        String childType
    )
    {
        return String.join(
            ":",
            "version",
            namespaceName,
            versionName,
            childType
        );
    }

    public static String createCacheKey(
        String versionName
    )
    {
        return String.join(
            ":",
            versionName
        );
    }

    public VersionModelMaster model() {
        VersionModelMaster value = cache.get(
            parentKey,
            io.gs2.version.domain.model.VersionModelMasterDomain.createCacheKey(
                this.getVersionName() != null ? this.getVersionName().toString() : null
            ),
            VersionModelMaster.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetVersionModelMasterRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.version.domain.model.VersionModelMasterDomain.createCacheKey(
                        this.getVersionName() != null ? this.getVersionName().toString() : null
                    ),
                    VersionModelMaster.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.version.domain.model.VersionModelMasterDomain.createCacheKey(
                this.getVersionName() != null ? this.getVersionName().toString() : null
            ),
            VersionModelMaster.class
        );
        }
        return value;
    }

}

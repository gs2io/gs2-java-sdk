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


public class AcceptVersionAccessTokenDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2VersionRestClient client;
    private final String namespaceName;
    private final AccessToken accessToken;
    private final String versionName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return accessToken.getUserId();
    }
    public String getVersionName() {
        return versionName;
    }

    public AcceptVersionAccessTokenDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        AccessToken accessToken,
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
        this.accessToken = accessToken;
        this.versionName = versionName;
        this.parentKey = io.gs2.version.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.accessToken != null && this.accessToken.getUserId() != null ? this.accessToken.getUserId().toString() : null,
            "AcceptVersion"
        );
    }

    public AcceptVersionAccessTokenDomain accept(
        AcceptRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withVersionName(this.versionName);
        AcceptResult result = this.client.accept(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.version.domain.model.AcceptVersionDomain.createCacheKey(
                    request.getVersionName() != null ? request.getVersionName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        AcceptVersionAccessTokenDomain domain = this;

        return domain;
    }

    private AcceptVersion get(
        GetAcceptVersionRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withVersionName(this.versionName);
        GetAcceptVersionResult result = this.client.getAcceptVersion(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.version.domain.model.AcceptVersionDomain.createCacheKey(
                    request.getVersionName() != null ? request.getVersionName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public AcceptVersionAccessTokenDomain delete(
        DeleteAcceptVersionRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withVersionName(this.versionName);
        DeleteAcceptVersionResult result = null;
        try {
            result = this.client.deleteAcceptVersion(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.version.domain.model.AcceptVersionDomain.createCacheKey(
                request.getVersionName() != null ? request.getVersionName().toString() : null
            ),
            AcceptVersion.class
        );
        AcceptVersionAccessTokenDomain domain = this;

        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String userId,
        String versionName,
        String childType
    )
    {
        return String.join(
            ":",
            "version",
            namespaceName,
            userId,
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

    public AcceptVersion model() {
        AcceptVersion value = cache.get(
            parentKey,
            io.gs2.version.domain.model.AcceptVersionDomain.createCacheKey(
                this.getVersionName() != null ? this.getVersionName().toString() : null
            ),
            AcceptVersion.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetAcceptVersionRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.version.domain.model.AcceptVersionDomain.createCacheKey(
                        this.getVersionName() != null ? this.getVersionName().toString() : null
                    ),
                    AcceptVersion.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.version.domain.model.AcceptVersionDomain.createCacheKey(
                this.getVersionName() != null ? this.getVersionName().toString() : null
            ),
            AcceptVersion.class
        );
        }
        return value;
    }

}

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


public class CheckerDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2VersionRestClient client;
    private final String namespaceName;
    private final String userId;

    private final String parentKey;
    String projectToken;
    public String getProjectToken() {
        return this.projectToken;
    }
    List<Status> warnings;
    public List<Status> getWarnings() {
        return this.warnings;
    }
    List<Status> errors;
    public List<Status> getErrors() {
        return this.errors;
    }
    String body;
    public String getBody() {
        return this.body;
    }
    String signature;
    public String getSignature() {
        return this.signature;
    }
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return userId;
    }

    public CheckerDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String userId
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2VersionRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.userId = userId;
        this.parentKey = io.gs2.version.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "Checker"
        );
    }

    public CheckerDomain checkVersion(
        CheckVersionByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId);
        CheckVersionByUserIdResult result = this.client.checkVersionByUserId(
            request
        );
        CheckerDomain domain = this;
        this.projectToken = result.getProjectToken();
        this.warnings = result.getWarnings();
        this.errors = result.getErrors();
        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String userId,
        String childType
    )
    {
        return String.join(
            ":",
            "version",
            namespaceName,
            userId,
            childType
        );
    }

    public static String createCacheKey(
    )
    {
        return "Singleton";
    }

}

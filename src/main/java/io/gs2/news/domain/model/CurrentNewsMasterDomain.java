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
package io.gs2.news.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.news.Gs2NewsRestClient;
import io.gs2.news.domain.iterator.*;
import io.gs2.news.model.*;
import io.gs2.news.request.*;
import io.gs2.news.result.*;

import java.util.List;


public class CurrentNewsMasterDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2NewsRestClient client;
    private final String namespaceName;

    private final String parentKey;
    String uploadToken;
    public String getUploadToken() {
        return this.uploadToken;
    }
    String templateUploadUrl;
    public String getTemplateUploadUrl() {
        return this.templateUploadUrl;
    }
    public String getNamespaceName() {
        return namespaceName;
    }

    public CurrentNewsMasterDomain(
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
        this.client = new Gs2NewsRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.parentKey = io.gs2.news.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "CurrentNewsMaster"
        );
    }

    public CurrentNewsMasterDomain prepareUpdate(
        PrepareUpdateCurrentNewsMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName);
        PrepareUpdateCurrentNewsMasterResult result = this.client.prepareUpdateCurrentNewsMaster(
            request
        );
        CurrentNewsMasterDomain domain = this;
        this.uploadToken = result.getUploadToken();
        this.templateUploadUrl = result.getTemplateUploadUrl();
        return domain;
    }

    public CurrentNewsMasterDomain update(
        UpdateCurrentNewsMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName);
        UpdateCurrentNewsMasterResult result = this.client.updateCurrentNewsMaster(
            request
        );
        CurrentNewsMasterDomain domain = this;
        return domain;
    }

    public CurrentNewsMasterDomain prepareUpdateFromGitHub(
        PrepareUpdateCurrentNewsMasterFromGitHubRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName);
        PrepareUpdateCurrentNewsMasterFromGitHubResult result = this.client.prepareUpdateCurrentNewsMasterFromGitHub(
            request
        );
        CurrentNewsMasterDomain domain = this;
        this.uploadToken = result.getUploadToken();
        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String childType
    )
    {
        return String.join(
            ":",
            "news",
            namespaceName,
            childType
        );
    }

    public static String createCacheKey(
        String namespaceName
    )
    {
        return String.join(
            ":",
            namespaceName
        );
    }

}

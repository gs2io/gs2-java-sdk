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
package io.gs2.key.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.key.Gs2KeyRestClient;
import io.gs2.key.domain.iterator.*;
import io.gs2.key.model.*;
import io.gs2.key.request.*;
import io.gs2.key.result.*;

import java.util.List;


public class GitHubApiKeyDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2KeyRestClient client;
    private final String namespaceName;
    private final String apiKeyName;

    private final String parentKey;
    String apiKey;
    public String getApiKey() {
        return this.apiKey;
    }
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getApiKeyName() {
        return apiKeyName;
    }

    public GitHubApiKeyDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String apiKeyName
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2KeyRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.apiKeyName = apiKeyName;
        this.parentKey = io.gs2.key.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "GitHubApiKey"
        );
    }

    public GitHubApiKeyDomain update(
        UpdateGitHubApiKeyRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withApiKeyName(this.apiKeyName);
        UpdateGitHubApiKeyResult result = this.client.updateGitHubApiKey(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.key.domain.model.GitHubApiKeyDomain.createCacheKey(
                    request.getApiKeyName() != null ? request.getApiKeyName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        GitHubApiKeyDomain domain = this;

        return domain;
    }

    private GitHubApiKey get(
        GetGitHubApiKeyRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withApiKeyName(this.apiKeyName);
        GetGitHubApiKeyResult result = this.client.getGitHubApiKey(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.key.domain.model.GitHubApiKeyDomain.createCacheKey(
                    request.getApiKeyName() != null ? request.getApiKeyName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public GitHubApiKeyDomain delete(
        DeleteGitHubApiKeyRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withApiKeyName(this.apiKeyName);
        DeleteGitHubApiKeyResult result = null;
        try {
            result = this.client.deleteGitHubApiKey(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.key.domain.model.GitHubApiKeyDomain.createCacheKey(
                request.getApiKeyName() != null ? request.getApiKeyName().toString() : null
            ),
            GitHubApiKey.class
        );
        GitHubApiKeyDomain domain = this;

        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String apiKeyName,
        String childType
    )
    {
        return String.join(
            ":",
            "key",
            namespaceName,
            apiKeyName,
            childType
        );
    }

    public static String createCacheKey(
        String apiKeyName
    )
    {
        return String.join(
            ":",
            apiKeyName
        );
    }

    public GitHubApiKey model() {
        GitHubApiKey value = cache.get(
            parentKey,
            io.gs2.key.domain.model.GitHubApiKeyDomain.createCacheKey(
                this.getApiKeyName() != null ? this.getApiKeyName().toString() : null
            ),
            GitHubApiKey.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetGitHubApiKeyRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.key.domain.model.GitHubApiKeyDomain.createCacheKey(
                        this.getApiKeyName() != null ? this.getApiKeyName().toString() : null
                    ),
                    GitHubApiKey.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.key.domain.model.GitHubApiKeyDomain.createCacheKey(
                this.getApiKeyName() != null ? this.getApiKeyName().toString() : null
            ),
            GitHubApiKey.class
        );
        }
        return value;
    }

}

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
package io.gs2.identifier.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.identifier.Gs2IdentifierRestClient;
import io.gs2.identifier.domain.iterator.*;
import io.gs2.identifier.model.*;
import io.gs2.identifier.request.*;
import io.gs2.identifier.result.*;

import java.util.List;


public class ProjectTokenDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2IdentifierRestClient client;

    private final String parentKey;
    String accessToken;
    public String getAccessToken() {
        return this.accessToken;
    }
    String tokenType;
    public String getTokenType() {
        return this.tokenType;
    }
    Integer expiresIn;
    public Integer getExpiresIn() {
        return this.expiresIn;
    }

    public ProjectTokenDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2IdentifierRestClient(
            session
        );
        this.parentKey = "identifier:ProjectToken";
    }

    public ProjectTokenDomain login(
        LoginRequest request
    ) {
        LoginResult result = this.client.login(
            request
        );
        ProjectTokenDomain domain = this;
        this.accessToken = result.getAccessToken();
        this.tokenType = result.getTokenType();
        this.expiresIn = result.getExpiresIn();
        return domain;
    }

    public ProjectTokenDomain loginByUser(
        LoginByUserRequest request
    ) {
        LoginByUserResult result = this.client.loginByUser(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.identifier.domain.model.ProjectTokenDomain.createCacheKey(
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        ProjectTokenDomain domain = this;

        return domain;
    }

    public static String createCacheParentKey(
        String childType
    )
    {
        return String.join(
            ":",
            "identifier",
            childType
        );
    }

    public static String createCacheKey(
    )
    {
        return "Singleton";
    }

    public ProjectToken model() {
        ProjectToken value = cache.get(
            parentKey,
            io.gs2.identifier.domain.model.ProjectTokenDomain.createCacheKey(
            ),
            ProjectToken.class
        );
        return value;
    }

}

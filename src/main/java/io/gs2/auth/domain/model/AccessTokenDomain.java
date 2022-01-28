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
package io.gs2.auth.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.auth.Gs2AuthRestClient;
import io.gs2.auth.model.*;
import io.gs2.auth.request.*;
import io.gs2.auth.result.*;

import java.util.List;


public class AccessTokenDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2AuthRestClient client;

    private final String parentKey;
    String token;
    public String getToken() {
        return this.token;
    }
    String userId;
    public String getUserId() {
        return this.userId;
    }
    Long expire;
    public Long getExpire() {
        return this.expire;
    }

    public AccessTokenDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2AuthRestClient(
            session
        );
        this.parentKey = "auth:AccessToken";
    }

    public AccessTokenDomain login(
        LoginRequest request
    ) {
        LoginResult result = this.client.login(
            request
        );
        AccessTokenDomain domain = this;
        this.cache.put(
            this.parentKey,
            AccessTokenDomain.createCacheKey(),
            new AccessToken()
                    .withToken(result.getToken())
                    .withUserId(result.getUserId())
                    .withExpire(result.getExpire()),
            System.currentTimeMillis() + 1000 * 60 * 15
        );
        this.token = result.getToken();
        this.userId = result.getUserId();
        this.expire = result.getExpire();
        return domain;
    }

    public AccessTokenDomain loginBySignature(
        LoginBySignatureRequest request
    ) {
        LoginBySignatureResult result = this.client.loginBySignature(
            request
        );
        AccessTokenDomain domain = this;
        this.cache.put(
            this.parentKey,
            AccessTokenDomain.createCacheKey(),
            new AccessToken()
                    .withToken(result.getToken())
                    .withUserId(result.getUserId())
                    .withExpire(result.getExpire()),
            System.currentTimeMillis() + 1000 * 60 * 15
        );
        this.token = result.getToken();
        this.userId = result.getUserId();
        this.expire = result.getExpire();
        return domain;
    }

    public static String createCacheParentKey(
        String childType
    )
    {
        return String.join(
            ":",
            "auth",
            childType
        );
    }

    public static String createCacheKey(
    )
    {
        return "Singleton";
    }

    public AccessToken model() {
        AccessToken value = cache.get(
            parentKey,
            io.gs2.auth.domain.model.AccessTokenDomain.createCacheKey(
            ),
            AccessToken.class
        );
        return value;
    }

}

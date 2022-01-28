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
package io.gs2.gateway.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.core.net.Gs2WebSocketSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.gateway.Gs2GatewayRestClient;
import io.gs2.gateway.Gs2GatewayWebSocketClient;
import io.gs2.gateway.domain.iterator.*;
import io.gs2.gateway.model.*;
import io.gs2.gateway.request.*;
import io.gs2.gateway.result.*;

import java.util.List;


public class FirebaseTokenAccessTokenDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2GatewayRestClient client;
    private final Gs2WebSocketSession wssession;
    private final Gs2GatewayWebSocketClient wsclient;
    private final String namespaceName;
    private final AccessToken accessToken;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return accessToken.getUserId();
    }

    public FirebaseTokenAccessTokenDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        Gs2WebSocketSession wssession,
        String namespaceName,
        AccessToken accessToken
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2GatewayRestClient(
            session
        );
        this.wssession = wssession;
        this.wsclient = new Gs2GatewayWebSocketClient(
            wssession
        );
        this.namespaceName = namespaceName;
        this.accessToken = accessToken;
        this.parentKey = io.gs2.gateway.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.accessToken != null && this.accessToken.getUserId() != null ? this.accessToken.getUserId().toString() : null,
            "FirebaseToken"
        );
    }

    public FirebaseTokenAccessTokenDomain set(
        SetFirebaseTokenRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null);
        SetFirebaseTokenResult result = this.client.setFirebaseToken(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.gateway.domain.model.FirebaseTokenDomain.createCacheKey(
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        FirebaseTokenAccessTokenDomain domain = this;

        return domain;
    }

    private FirebaseToken get(
        GetFirebaseTokenRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null);
        GetFirebaseTokenResult result = this.client.getFirebaseToken(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.gateway.domain.model.FirebaseTokenDomain.createCacheKey(
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public FirebaseTokenAccessTokenDomain delete(
        DeleteFirebaseTokenRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null);
        DeleteFirebaseTokenResult result = null;
        try {
            result = this.client.deleteFirebaseToken(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.gateway.domain.model.FirebaseTokenDomain.createCacheKey(
            ),
            FirebaseToken.class
        );
        FirebaseTokenAccessTokenDomain domain = this;

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
            "gateway",
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

    public FirebaseToken model() {
        FirebaseToken value = cache.get(
            parentKey,
            io.gs2.gateway.domain.model.FirebaseTokenDomain.createCacheKey(
            ),
            FirebaseToken.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetFirebaseTokenRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.gateway.domain.model.FirebaseTokenDomain.createCacheKey(
                    ),
                    FirebaseToken.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.gateway.domain.model.FirebaseTokenDomain.createCacheKey(
            ),
            FirebaseToken.class
        );
        }
        return value;
    }

}

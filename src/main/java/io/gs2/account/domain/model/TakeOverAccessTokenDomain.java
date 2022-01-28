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
package io.gs2.account.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.account.Gs2AccountRestClient;
import io.gs2.account.domain.iterator.*;
import io.gs2.account.model.*;
import io.gs2.account.request.*;
import io.gs2.account.result.*;

import java.util.List;


public class TakeOverAccessTokenDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2AccountRestClient client;
    private final String namespaceName;
    private final AccessToken accessToken;
    private final Integer type;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return accessToken.getUserId();
    }
    public Integer getType() {
        return type;
    }

    public TakeOverAccessTokenDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        AccessToken accessToken,
        Integer type
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2AccountRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.accessToken = accessToken;
        this.type = type;
        this.parentKey = io.gs2.account.domain.model.AccountDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.accessToken != null && this.accessToken.getUserId() != null ? this.accessToken.getUserId().toString() : null,
            "TakeOver"
        );
    }

    public TakeOverAccessTokenDomain create(
        CreateTakeOverRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withType(this.type);
        CreateTakeOverResult result = this.client.createTakeOver(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.account.domain.model.TakeOverDomain.createCacheKey(
                    request.getType() != null ? request.getType().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        TakeOverAccessTokenDomain domain = this;

        return domain;
    }

    private TakeOver get(
        GetTakeOverRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withType(this.type);
        GetTakeOverResult result = this.client.getTakeOver(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.account.domain.model.TakeOverDomain.createCacheKey(
                    request.getType() != null ? request.getType().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public TakeOverAccessTokenDomain update(
        UpdateTakeOverRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withType(this.type);
        UpdateTakeOverResult result = this.client.updateTakeOver(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.account.domain.model.TakeOverDomain.createCacheKey(
                    request.getType() != null ? request.getType().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        TakeOverAccessTokenDomain domain = this;

        return domain;
    }

    public TakeOverAccessTokenDomain delete(
        DeleteTakeOverRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withType(this.type);
        DeleteTakeOverResult result = null;
        try {
            result = this.client.deleteTakeOver(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.account.domain.model.TakeOverDomain.createCacheKey(
                request.getType() != null ? request.getType().toString() : null
            ),
            TakeOver.class
        );
        TakeOverAccessTokenDomain domain = this;

        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String userId,
        String type,
        String childType
    )
    {
        return String.join(
            ":",
            "account",
            namespaceName,
            userId,
            type,
            childType
        );
    }

    public static String createCacheKey(
        String type
    )
    {
        return String.join(
            ":",
            type
        );
    }

    public TakeOver model() {
        TakeOver value = cache.get(
            parentKey,
            io.gs2.account.domain.model.TakeOverDomain.createCacheKey(
                this.getType() != null ? this.getType().toString() : null
            ),
            TakeOver.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetTakeOverRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.account.domain.model.TakeOverDomain.createCacheKey(
                        this.getType() != null ? this.getType().toString() : null
                    ),
                    TakeOver.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.account.domain.model.TakeOverDomain.createCacheKey(
                this.getType() != null ? this.getType().toString() : null
            ),
            TakeOver.class
        );
        }
        return value;
    }

}

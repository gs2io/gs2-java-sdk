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


public class IdentifierDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2IdentifierRestClient client;
    private final String userName;
    private final String clientId;

    private final String parentKey;
    String clientSecret;
    public String getClientSecret() {
        return this.clientSecret;
    }
    public String getUserName() {
        return userName;
    }
    public String getClientId() {
        return clientId;
    }

    public IdentifierDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String userName,
        String clientId
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2IdentifierRestClient(
            session
        );
        this.userName = userName;
        this.clientId = clientId;
        this.parentKey = io.gs2.identifier.domain.model.UserDomain.createCacheParentKey(
            this.userName != null ? this.userName.toString() : null,
            "Identifier"
        );
    }

    private Identifier get(
        GetIdentifierRequest request
    ) {
        request
            .withUserName(this.userName)
            .withClientId(this.clientId);
        GetIdentifierResult result = this.client.getIdentifier(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.identifier.domain.model.IdentifierDomain.createCacheKey(
                    request.getUserName() != null ? request.getUserName().toString() : null,
                    request.getClientId() != null ? request.getClientId().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public IdentifierDomain delete(
        DeleteIdentifierRequest request
    ) {
        request
            .withUserName(this.userName)
            .withClientId(this.clientId);
        DeleteIdentifierResult result = null;
        try {
            result = this.client.deleteIdentifier(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.identifier.domain.model.IdentifierDomain.createCacheKey(
                request.getUserName() != null ? request.getUserName().toString() : null,
                request.getClientId() != null ? request.getClientId().toString() : null
            ),
            Identifier.class
        );
        IdentifierDomain domain = this;

        return domain;
    }

    public static String createCacheParentKey(
        String userName,
        String clientId,
        String childType
    )
    {
        return String.join(
            ":",
            "identifier",
            userName,
            clientId,
            childType
        );
    }

    public static String createCacheKey(
        String userName,
        String clientId
    )
    {
        return String.join(
            ":",
            userName,
            clientId
        );
    }

    public Identifier model() {
        Identifier value = cache.get(
            parentKey,
            io.gs2.identifier.domain.model.IdentifierDomain.createCacheKey(
                this.getUserName() != null ? this.getUserName().toString() : null,
                this.getClientId() != null ? this.getClientId().toString() : null
            ),
            Identifier.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetIdentifierRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.identifier.domain.model.IdentifierDomain.createCacheKey(
                        this.getUserName() != null ? this.getUserName().toString() : null,
                        this.getClientId() != null ? this.getClientId().toString() : null
                    ),
                    Identifier.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.identifier.domain.model.IdentifierDomain.createCacheKey(
                this.getUserName() != null ? this.getUserName().toString() : null,
                this.getClientId() != null ? this.getClientId().toString() : null
            ),
            Identifier.class
        );
        }
        return value;
    }

}

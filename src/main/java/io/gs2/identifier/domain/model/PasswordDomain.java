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


public class PasswordDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2IdentifierRestClient client;
    private final String userName;

    private final String parentKey;
    public String getUserName() {
        return userName;
    }

    public PasswordDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String userName
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2IdentifierRestClient(
            session
        );
        this.userName = userName;
        this.parentKey = io.gs2.identifier.domain.model.UserDomain.createCacheParentKey(
            this.userName != null ? this.userName.toString() : null,
            "Password"
        );
    }

    public PasswordDomain create(
        CreatePasswordRequest request
    ) {
        request
            .withUserName(this.userName);
        CreatePasswordResult result = this.client.createPassword(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.identifier.domain.model.PasswordDomain.createCacheKey(
                    request.getUserName() != null ? request.getUserName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        PasswordDomain domain = this;

        return domain;
    }

    private Password get(
        GetPasswordRequest request
    ) {
        request
            .withUserName(this.userName);
        GetPasswordResult result = this.client.getPassword(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.identifier.domain.model.PasswordDomain.createCacheKey(
                    request.getUserName() != null ? request.getUserName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public PasswordDomain delete(
        DeletePasswordRequest request
    ) {
        request
            .withUserName(this.userName);
        DeletePasswordResult result = null;
        try {
            result = this.client.deletePassword(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.identifier.domain.model.PasswordDomain.createCacheKey(
                request.getUserName() != null ? request.getUserName().toString() : null
            ),
            Password.class
        );
        PasswordDomain domain = this;

        return domain;
    }

    public static String createCacheParentKey(
        String userName,
        String childType
    )
    {
        return String.join(
            ":",
            "identifier",
            userName,
            childType
        );
    }

    public static String createCacheKey(
        String userName
    )
    {
        return String.join(
            ":",
            userName
        );
    }

    public Password model() {
        Password value = cache.get(
            parentKey,
            io.gs2.identifier.domain.model.PasswordDomain.createCacheKey(
                this.getUserName() != null ? this.getUserName().toString() : null
            ),
            Password.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetPasswordRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.identifier.domain.model.PasswordDomain.createCacheKey(
                        this.getUserName() != null ? this.getUserName().toString() : null
                    ),
                    Password.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.identifier.domain.model.PasswordDomain.createCacheKey(
                this.getUserName() != null ? this.getUserName().toString() : null
            ),
            Password.class
        );
        }
        return value;
    }

}

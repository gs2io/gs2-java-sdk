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


public class UserDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2IdentifierRestClient client;
    private final String userName;

    private final String parentKey;
    String clientSecret;
    public String getClientSecret() {
        return this.clientSecret;
    }
    String nextPageToken;
    public String getNextPageToken() {
        return this.nextPageToken;
    }
    public String getUserName() {
        return userName;
    }

    public UserDomain(
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
        this.parentKey = "identifier:User";
    }

    public UserDomain update(
        UpdateUserRequest request
    ) {
        request
            .withUserName(this.userName);
        UpdateUserResult result = this.client.updateUser(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.identifier.domain.model.UserDomain.createCacheKey(
                    request.getUserName() != null ? request.getUserName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        UserDomain domain = this;

        return domain;
    }

    private User get(
        GetUserRequest request
    ) {
        request
            .withUserName(this.userName);
        GetUserResult result = this.client.getUser(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.identifier.domain.model.UserDomain.createCacheKey(
                    request.getUserName() != null ? request.getUserName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public UserDomain delete(
        DeleteUserRequest request
    ) {
        request
            .withUserName(this.userName);
        DeleteUserResult result = null;
        try {
            result = this.client.deleteUser(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.identifier.domain.model.UserDomain.createCacheKey(
                request.getUserName() != null ? request.getUserName().toString() : null
            ),
            User.class
        );
        UserDomain domain = this;

        return domain;
    }

    public IdentifierDomain createIdentifier(
        CreateIdentifierRequest request
    ) {
        request
            .withUserName(this.userName);
        CreateIdentifierResult result = this.client.createIdentifier(
            request
        );
        String parentKey = io.gs2.identifier.domain.model.UserDomain.createCacheParentKey(
            this.userName != null ? this.userName.toString() : null,
            "Identifier"
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.identifier.domain.model.IdentifierDomain.createCacheKey(
                    request.getUserName() != null ? request.getUserName().toString() : null,
                    result.getItem().getClientId() != null ? result.getItem().getClientId().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        IdentifierDomain domain = new IdentifierDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            result.getItem().getUserName(),
            result.getItem().getClientId()
        );
        domain.clientSecret = result.getClientSecret();

        return domain;
    }

    public DescribeIdentifiersIterator identifiers(
    )
    {
        return new DescribeIdentifiersIterator(
            this.cache,
            this.client,
            this.userName
        );
    }

    public IdentifierDomain identifier(
        String clientId
    ) {
        return new IdentifierDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.userName,
            clientId
        );
    }

    public DescribePasswordsIterator passwords(
    )
    {
        return new DescribePasswordsIterator(
            this.cache,
            this.client,
            this.userName
        );
    }

    public PasswordDomain password(
    ) {
        return new PasswordDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.userName
        );
    }

    public AttachSecurityPolicyDomain attachSecurityPolicy(
    ) {
        return new AttachSecurityPolicyDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.userName
        );
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

    public User model() {
        User value = cache.get(
            parentKey,
            io.gs2.identifier.domain.model.UserDomain.createCacheKey(
                this.getUserName() != null ? this.getUserName().toString() : null
            ),
            User.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetUserRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.identifier.domain.model.UserDomain.createCacheKey(
                        this.getUserName() != null ? this.getUserName().toString() : null
                    ),
                    User.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.identifier.domain.model.UserDomain.createCacheKey(
                this.getUserName() != null ? this.getUserName().toString() : null
            ),
            User.class
        );
        }
        return value;
    }

}

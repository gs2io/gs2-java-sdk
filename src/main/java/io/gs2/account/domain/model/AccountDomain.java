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


public class AccountDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2AccountRestClient client;
    private final String namespaceName;
    private final String userId;

    private final String parentKey;
    String body;
    public String getBody() {
        return this.body;
    }
    String signature;
    public String getSignature() {
        return this.signature;
    }
    String nextPageToken;
    public String getNextPageToken() {
        return this.nextPageToken;
    }
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return userId;
    }

    public AccountDomain(
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
        this.client = new Gs2AccountRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.userId = userId;
        this.parentKey = io.gs2.account.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "Account"
        );
    }

    public AccountDomain updateTimeOffset(
        UpdateTimeOffsetRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId);
        UpdateTimeOffsetResult result = this.client.updateTimeOffset(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.account.domain.model.AccountDomain.createCacheKey(
                    request.getUserId() != null ? request.getUserId().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        AccountDomain domain = this;

        return domain;
    }

    private Account get(
        GetAccountRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId);
        GetAccountResult result = this.client.getAccount(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.account.domain.model.AccountDomain.createCacheKey(
                    request.getUserId() != null ? request.getUserId().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public AccountDomain delete(
        DeleteAccountRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId);
        DeleteAccountResult result = null;
        try {
            result = this.client.deleteAccount(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.account.domain.model.AccountDomain.createCacheKey(
                request.getUserId() != null ? request.getUserId().toString() : null
            ),
            Account.class
        );
        AccountDomain domain = this;

        return domain;
    }

    public AccountDomain authentication(
        AuthenticationRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId);
        AuthenticationResult result = this.client.authentication(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.account.domain.model.AccountDomain.createCacheKey(
                    request.getUserId() != null ? request.getUserId().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        AccountDomain domain = this;
        domain.body = result.getBody();
        domain.signature = result.getSignature();

        return domain;
    }

    public DescribeTakeOversByUserIdIterator takeOvers(
    )
    {
        return new DescribeTakeOversByUserIdIterator(
            this.cache,
            this.client,
            this.namespaceName,
            this.userId
        );
    }

    public TakeOverDomain takeOver(
        Integer type
    ) {
        return new TakeOverDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.userId,
            type
        );
    }

    public static String createCacheParentKey(
        String namespaceName,
        String userId,
        String childType
    )
    {
        return String.join(
            ":",
            "account",
            namespaceName,
            userId,
            childType
        );
    }

    public static String createCacheKey(
        String userId
    )
    {
        return String.join(
            ":",
            userId
        );
    }

    public Account model() {
        Account value = cache.get(
            parentKey,
            io.gs2.account.domain.model.AccountDomain.createCacheKey(
                this.getUserId() != null ? this.getUserId().toString() : null
            ),
            Account.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetAccountRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.account.domain.model.AccountDomain.createCacheKey(
                        this.getUserId() != null ? this.getUserId().toString() : null
                    ),
                    Account.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.account.domain.model.AccountDomain.createCacheKey(
                this.getUserId() != null ? this.getUserId().toString() : null
            ),
            Account.class
        );
        }
        return value;
    }

}

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
package io.gs2.lock.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.lock.Gs2LockRestClient;
import io.gs2.lock.domain.iterator.*;
import io.gs2.lock.model.*;
import io.gs2.lock.request.*;
import io.gs2.lock.result.*;

import java.util.List;


public class UserDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2LockRestClient client;
    private final String namespaceName;
    private final String userId;

    private final String parentKey;
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

    public UserDomain(
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
        this.client = new Gs2LockRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.userId = userId;
        this.parentKey = io.gs2.lock.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "User"
        );
    }

    public DescribeMutexesByUserIdIterator mutexes(
    )
    {
        return new DescribeMutexesByUserIdIterator(
            this.cache,
            this.client,
            this.namespaceName,
            this.userId
        );
    }

    public MutexDomain mutex(
        String propertyId
    ) {
        return new MutexDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.userId,
            propertyId
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
            "lock",
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

}

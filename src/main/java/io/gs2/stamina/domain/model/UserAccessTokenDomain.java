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
package io.gs2.stamina.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.stamina.Gs2StaminaRestClient;
import io.gs2.stamina.domain.iterator.*;
import io.gs2.stamina.model.*;
import io.gs2.stamina.request.*;
import io.gs2.stamina.result.*;

import java.util.List;


public class UserAccessTokenDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2StaminaRestClient client;
    private final String namespaceName;
    private final AccessToken accessToken;

    private final String parentKey;
    Long overflowValue;
    public Long getOverflowValue() {
        return this.overflowValue;
    }
    String nextPageToken;
    public String getNextPageToken() {
        return this.nextPageToken;
    }
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return accessToken.getUserId();
    }

    public UserAccessTokenDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        AccessToken accessToken
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2StaminaRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.accessToken = accessToken;
        this.parentKey = io.gs2.stamina.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "User"
        );
    }

    public DescribeStaminasIterator staminas(
    )
    {
        return new DescribeStaminasIterator(
            this.cache,
            this.client,
            this.namespaceName,
            this.accessToken
        );
    }

    public StaminaAccessTokenDomain stamina(
        String staminaName
    ) {
        return new StaminaAccessTokenDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.accessToken,
            staminaName
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
            "stamina",
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
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
package io.gs2.news.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.news.Gs2NewsRestClient;
import io.gs2.news.domain.iterator.*;
import io.gs2.news.model.*;
import io.gs2.news.request.*;
import io.gs2.news.result.*;

import java.util.List;


public class SetCookieRequestEntryAccessTokenDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2NewsRestClient client;
    private final String namespaceName;
    private final AccessToken accessToken;
    private final String key;
    private final String value;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return accessToken.getUserId();
    }
    public String getKey() {
        return key;
    }
    public String getValue() {
        return value;
    }

    public SetCookieRequestEntryAccessTokenDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        AccessToken accessToken,
        String key,
        String value
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2NewsRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.accessToken = accessToken;
        this.key = key;
        this.value = value;
        this.parentKey = io.gs2.news.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.accessToken != null && this.accessToken.getUserId() != null ? this.accessToken.getUserId().toString() : null,
            "SetCookieRequestEntry"
        );
    }

    public static String createCacheParentKey(
        String namespaceName,
        String userId,
        String key,
        String value,
        String childType
    )
    {
        return String.join(
            ":",
            "news",
            namespaceName,
            userId,
            key,
            value,
            childType
        );
    }

    public static String createCacheKey(
        String key,
        String value
    )
    {
        return String.join(
            ":",
            key,
            value
        );
    }

    public SetCookieRequestEntry model() {
        SetCookieRequestEntry value = cache.get(
            parentKey,
            io.gs2.news.domain.model.SetCookieRequestEntryDomain.createCacheKey(
                this.getKey() != null ? this.getKey().toString() : null,
                this.getValue() != null ? this.getValue().toString() : null
            ),
            SetCookieRequestEntry.class
        );
        return value;
    }

}

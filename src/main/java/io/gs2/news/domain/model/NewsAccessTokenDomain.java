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


public class NewsAccessTokenDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2NewsRestClient client;
    private final String namespaceName;
    private final AccessToken accessToken;

    private final String parentKey;
    String browserUrl;
    public String getBrowserUrl() {
        return this.browserUrl;
    }
    String zipUrl;
    public String getZipUrl() {
        return this.zipUrl;
    }
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return accessToken.getUserId();
    }

    public NewsAccessTokenDomain(
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
        this.client = new Gs2NewsRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.accessToken = accessToken;
        this.parentKey = io.gs2.news.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.accessToken != null && this.accessToken.getUserId() != null ? this.accessToken.getUserId().toString() : null,
            "News"
        );
    }

    public SetCookieRequestEntryAccessTokenDomain[] wantGrant(
        WantGrantRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null);
        WantGrantResult result = this.client.wantGrant(
            request
        );
        String parentKey = io.gs2.news.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.accessToken != null && this.accessToken.getUserId() != null ? this.accessToken.getUserId().toString() : null,
            "SetCookieRequestEntry"
        );
        for (SetCookieRequestEntry item : result.getItems()) {
                
            if (item != null) {
                cache.put(
                    parentKey,
                    io.gs2.news.domain.model.SetCookieRequestEntryDomain.createCacheKey(
                        item.getKey() != null ? item.getKey().toString() : null,
                        item.getValue() != null ? item.getValue().toString() : null
                    ),
                    item,
                    System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                );
            }
        }
        SetCookieRequestEntryAccessTokenDomain[] domain = new SetCookieRequestEntryAccessTokenDomain[result.getItems().size()];
        for (int i=0; i<result.getItems().size(); i++)
        {
            domain[i] = new SetCookieRequestEntryAccessTokenDomain(
                this.cache,
                this.jobQueueDomain,
                this.stampSheetConfiguration,
                this.session,
                request.getNamespaceName(),
                this.accessToken,
                result.getItems().get(i).getKey(),
                result.getItems().get(i).getValue()
            );
        }
        this.browserUrl = result.getBrowserUrl();
        this.zipUrl = result.getZipUrl();
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
            "news",
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

    public News model() {
        News value = cache.get(
            parentKey,
            io.gs2.news.domain.model.NewsDomain.createCacheKey(
            ),
            News.class
        );
        return value;
    }

}

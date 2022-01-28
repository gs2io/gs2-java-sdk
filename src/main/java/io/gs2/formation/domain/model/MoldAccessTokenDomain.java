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
package io.gs2.formation.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.formation.Gs2FormationRestClient;
import io.gs2.formation.domain.iterator.*;
import io.gs2.formation.model.*;
import io.gs2.formation.request.*;
import io.gs2.formation.result.*;

import java.util.List;


public class MoldAccessTokenDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2FormationRestClient client;
    private final String namespaceName;
    private final AccessToken accessToken;
    private final String moldName;

    private final String parentKey;
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
    public String getMoldName() {
        return moldName;
    }

    public MoldAccessTokenDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        AccessToken accessToken,
        String moldName
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2FormationRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.accessToken = accessToken;
        this.moldName = moldName;
        this.parentKey = io.gs2.formation.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.accessToken != null && this.accessToken.getUserId() != null ? this.accessToken.getUserId().toString() : null,
            "Mold"
        );
    }

    private Mold get(
        GetMoldRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withMoldName(this.moldName);
        GetMoldResult result = this.client.getMold(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.formation.domain.model.MoldDomain.createCacheKey(
                    request.getMoldName() != null ? request.getMoldName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
                
        if (result.getMoldModel() != null) {
            cache.put(
                parentKey,
                io.gs2.formation.domain.model.MoldModelDomain.createCacheKey(
                    request.getMoldName() != null ? request.getMoldName().toString() : null
                ),
                result.getMoldModel(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public MoldAccessTokenDomain delete(
        DeleteMoldRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withMoldName(this.moldName);
        DeleteMoldResult result = null;
        try {
            result = this.client.deleteMold(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.formation.domain.model.MoldDomain.createCacheKey(
                request.getMoldName() != null ? request.getMoldName().toString() : null
            ),
            Mold.class
        );
        MoldAccessTokenDomain domain = this;

        return domain;
    }

    public DescribeFormsIterator forms(
    )
    {
        return new DescribeFormsIterator(
            this.cache,
            this.client,
            this.namespaceName,
            this.moldName,
            this.accessToken
        );
    }

    public FormAccessTokenDomain form(
        Integer index
    ) {
        return new FormAccessTokenDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.accessToken,
            this.moldName,
            index
        );
    }

    public static String createCacheParentKey(
        String namespaceName,
        String userId,
        String moldName,
        String childType
    )
    {
        return String.join(
            ":",
            "formation",
            namespaceName,
            userId,
            moldName,
            childType
        );
    }

    public static String createCacheKey(
        String moldName
    )
    {
        return String.join(
            ":",
            moldName
        );
    }

    public Mold model() {
        Mold value = cache.get(
            parentKey,
            io.gs2.formation.domain.model.MoldDomain.createCacheKey(
                this.getMoldName() != null ? this.getMoldName().toString() : null
            ),
            Mold.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetMoldRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.formation.domain.model.MoldDomain.createCacheKey(
                        this.getMoldName() != null ? this.getMoldName().toString() : null
                    ),
                    Mold.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.formation.domain.model.MoldDomain.createCacheKey(
                this.getMoldName() != null ? this.getMoldName().toString() : null
            ),
            Mold.class
        );
        }
        return value;
    }

}

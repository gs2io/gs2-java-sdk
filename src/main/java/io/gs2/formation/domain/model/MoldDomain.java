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


public class MoldDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2FormationRestClient client;
    private final String namespaceName;
    private final String userId;
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
        return userId;
    }
    public String getMoldName() {
        return moldName;
    }

    public MoldDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String userId,
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
        this.userId = userId;
        this.moldName = moldName;
        this.parentKey = io.gs2.formation.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "Mold"
        );
    }

    private Mold get(
        GetMoldByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withMoldName(this.moldName);
        GetMoldByUserIdResult result = this.client.getMoldByUserId(
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

    public MoldDomain setCapacity(
        SetMoldCapacityByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withMoldName(this.moldName);
        SetMoldCapacityByUserIdResult result = this.client.setMoldCapacityByUserId(
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
        MoldDomain domain = this;

        return domain;
    }

    public MoldDomain addCapacity(
        AddMoldCapacityByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withMoldName(this.moldName);
        AddMoldCapacityByUserIdResult result = this.client.addMoldCapacityByUserId(
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
        MoldDomain domain = this;

        return domain;
    }

    public MoldDomain delete(
        DeleteMoldByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withMoldName(this.moldName);
        DeleteMoldByUserIdResult result = null;
        try {
            result = this.client.deleteMoldByUserId(
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
        MoldDomain domain = this;

        return domain;
    }

    public DescribeFormsByUserIdIterator forms(
    )
    {
        return new DescribeFormsByUserIdIterator(
            this.cache,
            this.client,
            this.namespaceName,
            this.moldName,
            this.userId
        );
    }

    public FormDomain form(
        Integer index
    ) {
        return new FormDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.userId,
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
                    new GetMoldByUserIdRequest()
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

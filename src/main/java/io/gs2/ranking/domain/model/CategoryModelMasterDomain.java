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
package io.gs2.ranking.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.ranking.Gs2RankingRestClient;
import io.gs2.ranking.domain.iterator.*;
import io.gs2.ranking.model.*;
import io.gs2.ranking.request.*;
import io.gs2.ranking.result.*;

import java.util.List;


public class CategoryModelMasterDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2RankingRestClient client;
    private final String namespaceName;
    private final String categoryName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getCategoryName() {
        return categoryName;
    }

    public CategoryModelMasterDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String categoryName
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2RankingRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.categoryName = categoryName;
        this.parentKey = io.gs2.ranking.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "CategoryModelMaster"
        );
    }

    private CategoryModelMaster get(
        GetCategoryModelMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withCategoryName(this.categoryName);
        GetCategoryModelMasterResult result = this.client.getCategoryModelMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.ranking.domain.model.CategoryModelMasterDomain.createCacheKey(
                    request.getCategoryName() != null ? request.getCategoryName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public CategoryModelMasterDomain update(
        UpdateCategoryModelMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withCategoryName(this.categoryName);
        UpdateCategoryModelMasterResult result = this.client.updateCategoryModelMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.ranking.domain.model.CategoryModelMasterDomain.createCacheKey(
                    request.getCategoryName() != null ? request.getCategoryName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        CategoryModelMasterDomain domain = this;

        return domain;
    }

    public CategoryModelMasterDomain delete(
        DeleteCategoryModelMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withCategoryName(this.categoryName);
        DeleteCategoryModelMasterResult result = null;
        try {
            result = this.client.deleteCategoryModelMaster(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.ranking.domain.model.CategoryModelMasterDomain.createCacheKey(
                request.getCategoryName() != null ? request.getCategoryName().toString() : null
            ),
            CategoryModelMaster.class
        );
        CategoryModelMasterDomain domain = this;

        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String categoryName,
        String childType
    )
    {
        return String.join(
            ":",
            "ranking",
            namespaceName,
            categoryName,
            childType
        );
    }

    public static String createCacheKey(
        String categoryName
    )
    {
        return String.join(
            ":",
            categoryName
        );
    }

    public CategoryModelMaster model() {
        CategoryModelMaster value = cache.get(
            parentKey,
            io.gs2.ranking.domain.model.CategoryModelMasterDomain.createCacheKey(
                this.getCategoryName() != null ? this.getCategoryName().toString() : null
            ),
            CategoryModelMaster.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetCategoryModelMasterRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.ranking.domain.model.CategoryModelMasterDomain.createCacheKey(
                        this.getCategoryName() != null ? this.getCategoryName().toString() : null
                    ),
                    CategoryModelMaster.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.ranking.domain.model.CategoryModelMasterDomain.createCacheKey(
                this.getCategoryName() != null ? this.getCategoryName().toString() : null
            ),
            CategoryModelMaster.class
        );
        }
        return value;
    }

}

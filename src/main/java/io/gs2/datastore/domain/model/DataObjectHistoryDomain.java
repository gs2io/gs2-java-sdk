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
package io.gs2.datastore.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.datastore.Gs2DatastoreRestClient;
import io.gs2.datastore.domain.iterator.*;
import io.gs2.datastore.model.*;
import io.gs2.datastore.request.*;
import io.gs2.datastore.result.*;

import java.util.List;


public class DataObjectHistoryDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2DatastoreRestClient client;
    private final String namespaceName;
    private final String userId;
    private final String dataObjectName;
    private final String generation;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return userId;
    }
    public String getDataObjectName() {
        return dataObjectName;
    }
    public String getGeneration() {
        return generation;
    }

    public DataObjectHistoryDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String userId,
        String dataObjectName,
        String generation
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2DatastoreRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.userId = userId;
        this.dataObjectName = dataObjectName;
        this.generation = generation;
        this.parentKey = io.gs2.datastore.domain.model.DataObjectDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            this.dataObjectName != null ? this.dataObjectName.toString() : null,
            "DataObjectHistory"
        );
    }

    private DataObjectHistory get(
        GetDataObjectHistoryByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withDataObjectName(this.dataObjectName)
            .withGeneration(this.generation);
        GetDataObjectHistoryByUserIdResult result = this.client.getDataObjectHistoryByUserId(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.datastore.domain.model.DataObjectHistoryDomain.createCacheKey(
                    request.getGeneration() != null ? request.getGeneration().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public static String createCacheParentKey(
        String namespaceName,
        String userId,
        String dataObjectName,
        String generation,
        String childType
    )
    {
        return String.join(
            ":",
            "datastore",
            namespaceName,
            userId,
            dataObjectName,
            generation,
            childType
        );
    }

    public static String createCacheKey(
        String generation
    )
    {
        return String.join(
            ":",
            generation
        );
    }

    public DataObjectHistory model() {
        DataObjectHistory value = cache.get(
            parentKey,
            io.gs2.datastore.domain.model.DataObjectHistoryDomain.createCacheKey(
                this.getGeneration() != null ? this.getGeneration().toString() : null
            ),
            DataObjectHistory.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetDataObjectHistoryByUserIdRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.datastore.domain.model.DataObjectHistoryDomain.createCacheKey(
                        this.getGeneration() != null ? this.getGeneration().toString() : null
                    ),
                    DataObjectHistory.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.datastore.domain.model.DataObjectHistoryDomain.createCacheKey(
                this.getGeneration() != null ? this.getGeneration().toString() : null
            ),
            DataObjectHistory.class
        );
        }
        return value;
    }

}

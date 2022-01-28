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


public class DataObjectDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2DatastoreRestClient client;
    private final String namespaceName;
    private final String userId;
    private final String dataObjectName;

    private final String parentKey;
    String uploadUrl;
    public String getUploadUrl() {
        return this.uploadUrl;
    }
    String fileUrl;
    public String getFileUrl() {
        return this.fileUrl;
    }
    Long contentLength;
    public Long getContentLength() {
        return this.contentLength;
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
    public String getDataObjectName() {
        return dataObjectName;
    }

    public DataObjectDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String userId,
        String dataObjectName
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
        this.parentKey = io.gs2.datastore.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "DataObject"
        );
    }

    public DataObjectDomain update(
        UpdateDataObjectByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withDataObjectName(this.dataObjectName);
        UpdateDataObjectByUserIdResult result = this.client.updateDataObjectByUserId(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.datastore.domain.model.DataObjectDomain.createCacheKey(
                    request.getDataObjectName() != null ? request.getDataObjectName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        DataObjectDomain domain = this;

        return domain;
    }

    public DataObjectDomain prepareReUpload(
        PrepareReUploadByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withDataObjectName(this.dataObjectName);
        PrepareReUploadByUserIdResult result = this.client.prepareReUploadByUserId(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.datastore.domain.model.DataObjectDomain.createCacheKey(
                    request.getDataObjectName() != null ? request.getDataObjectName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        DataObjectDomain domain = this;
        domain.uploadUrl = result.getUploadUrl();

        return domain;
    }

    public DataObjectDomain doneUpload(
        DoneUploadByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withDataObjectName(this.dataObjectName);
        DoneUploadByUserIdResult result = this.client.doneUploadByUserId(
            request
        );
        cache.listCacheClear(
            io.gs2.datastore.domain.model.DataObjectDomain.createCacheParentKey(
                this.getNamespaceName() != null ? this.getNamespaceName().toString() : null,
                this.getUserId() != null ? this.getUserId().toString() : null,
                this.getDataObjectName() != null ? this.getDataObjectName().toString() : null,
                "DataObjectHistory"
            ),
            DataObjectHistory.class
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.datastore.domain.model.DataObjectDomain.createCacheKey(
                    request.getDataObjectName() != null ? request.getDataObjectName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        DataObjectDomain domain = this;

        return domain;
    }

    public DataObjectDomain delete(
        DeleteDataObjectByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withDataObjectName(this.dataObjectName);
        DeleteDataObjectByUserIdResult result = null;
        try {
            result = this.client.deleteDataObjectByUserId(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.datastore.domain.model.DataObjectDomain.createCacheKey(
                request.getDataObjectName() != null ? request.getDataObjectName().toString() : null
            ),
            DataObject.class
        );
        DataObjectDomain domain = this;

        return domain;
    }

    public DataObjectDomain prepareDownloadByUserIdAndName(
        PrepareDownloadByUserIdAndDataObjectNameRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withDataObjectName(this.dataObjectName);
        PrepareDownloadByUserIdAndDataObjectNameResult result = this.client.prepareDownloadByUserIdAndDataObjectName(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.datastore.domain.model.DataObjectDomain.createCacheKey(
                    request.getDataObjectName() != null ? request.getDataObjectName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        DataObjectDomain domain = this;
        domain.fileUrl = result.getFileUrl();
        domain.contentLength = result.getContentLength();

        return domain;
    }

    public DataObjectDomain prepareDownloadByUserIdAndNameAndGeneration(
        PrepareDownloadByUserIdAndDataObjectNameAndGenerationRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withDataObjectName(this.dataObjectName);
        PrepareDownloadByUserIdAndDataObjectNameAndGenerationResult result = this.client.prepareDownloadByUserIdAndDataObjectNameAndGeneration(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.datastore.domain.model.DataObjectDomain.createCacheKey(
                    request.getDataObjectName() != null ? request.getDataObjectName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        DataObjectDomain domain = this;
        domain.fileUrl = result.getFileUrl();
        domain.contentLength = result.getContentLength();

        return domain;
    }

    public DescribeDataObjectHistoriesByUserIdIterator dataObjectHistories(
    )
    {
        return new DescribeDataObjectHistoriesByUserIdIterator(
            this.cache,
            this.client,
            this.namespaceName,
            this.userId,
            this.dataObjectName
        );
    }

    public DataObjectHistoryDomain dataObjectHistory(
        String generation
    ) {
        return new DataObjectHistoryDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.userId,
            this.dataObjectName,
            generation
        );
    }

    public static String createCacheParentKey(
        String namespaceName,
        String userId,
        String dataObjectName,
        String childType
    )
    {
        return String.join(
            ":",
            "datastore",
            namespaceName,
            userId,
            dataObjectName,
            childType
        );
    }

    public static String createCacheKey(
        String dataObjectName
    )
    {
        return String.join(
            ":",
            dataObjectName
        );
    }

    public DataObject model() {
        DataObject value = cache.get(
            parentKey,
            io.gs2.datastore.domain.model.DataObjectDomain.createCacheKey(
                this.getDataObjectName() != null ? this.getDataObjectName().toString() : null
            ),
            DataObject.class
        );
        return value;
    }

}

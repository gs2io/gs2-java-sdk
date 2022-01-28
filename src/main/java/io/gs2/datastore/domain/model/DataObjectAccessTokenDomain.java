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


public class DataObjectAccessTokenDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2DatastoreRestClient client;
    private final String namespaceName;
    private final AccessToken accessToken;
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
        return accessToken.getUserId();
    }
    public String getDataObjectName() {
        return dataObjectName;
    }

    public DataObjectAccessTokenDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        AccessToken accessToken,
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
        this.accessToken = accessToken;
        this.dataObjectName = dataObjectName;
        this.parentKey = io.gs2.datastore.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.accessToken != null && this.accessToken.getUserId() != null ? this.accessToken.getUserId().toString() : null,
            "DataObject"
        );
    }

    public DataObjectAccessTokenDomain update(
        UpdateDataObjectRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withDataObjectName(this.dataObjectName);
        UpdateDataObjectResult result = this.client.updateDataObject(
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
        DataObjectAccessTokenDomain domain = this;

        return domain;
    }

    public DataObjectAccessTokenDomain prepareReUpload(
        PrepareReUploadRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withDataObjectName(this.dataObjectName);
        PrepareReUploadResult result = this.client.prepareReUpload(
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
        DataObjectAccessTokenDomain domain = this;
        domain.uploadUrl = result.getUploadUrl();

        return domain;
    }

    public DataObjectAccessTokenDomain doneUpload(
        DoneUploadRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withDataObjectName(this.dataObjectName);
        DoneUploadResult result = this.client.doneUpload(
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
        DataObjectAccessTokenDomain domain = this;

        return domain;
    }

    public DataObjectAccessTokenDomain delete(
        DeleteDataObjectRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withDataObjectName(this.dataObjectName);
        DeleteDataObjectResult result = this.client.deleteDataObject(
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
        DataObjectAccessTokenDomain domain = this;

        return domain;
    }

    public DataObjectAccessTokenDomain prepareDownloadOwnData(
        PrepareDownloadOwnDataRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withDataObjectName(this.dataObjectName);
        PrepareDownloadOwnDataResult result = this.client.prepareDownloadOwnData(
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
        DataObjectAccessTokenDomain domain = this;
        domain.fileUrl = result.getFileUrl();
        domain.contentLength = result.getContentLength();

        return domain;
    }

    public DataObjectAccessTokenDomain prepareDownloadOwnDataByGeneration(
        PrepareDownloadOwnDataByGenerationRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withDataObjectName(this.dataObjectName);
        PrepareDownloadOwnDataByGenerationResult result = this.client.prepareDownloadOwnDataByGeneration(
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
        DataObjectAccessTokenDomain domain = this;
        domain.fileUrl = result.getFileUrl();
        domain.contentLength = result.getContentLength();

        return domain;
    }

    public DescribeDataObjectHistoriesIterator dataObjectHistories(
    )
    {
        return new DescribeDataObjectHistoriesIterator(
            this.cache,
            this.client,
            this.namespaceName,
            this.accessToken,
            this.dataObjectName
        );
    }

    public DataObjectHistoryAccessTokenDomain dataObjectHistory(
        String generation
    ) {
        return new DataObjectHistoryAccessTokenDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.accessToken,
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

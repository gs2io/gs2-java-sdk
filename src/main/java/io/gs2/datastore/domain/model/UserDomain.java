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


public class UserDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2DatastoreRestClient client;
    private final String namespaceName;
    private final String userId;

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
        this.client = new Gs2DatastoreRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.userId = userId;
        this.parentKey = io.gs2.datastore.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "User"
        );
    }

    public DataObjectDomain prepareUpload(
        PrepareUploadByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId);
        PrepareUploadByUserIdResult result = this.client.prepareUploadByUserId(
            request
        );
        String parentKey = io.gs2.datastore.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "DataObject"
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.datastore.domain.model.DataObjectDomain.createCacheKey(
                    result.getItem().getName() != null ? result.getItem().getName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        DataObjectDomain domain = new DataObjectDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            request.getNamespaceName(),
            result.getItem().getUserId(),
            result.getItem().getName()
        );
        domain.uploadUrl = result.getUploadUrl();

        return domain;
    }

    public DataObjectDomain prepareDownload(
        PrepareDownloadByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId);
        PrepareDownloadByUserIdResult result = this.client.prepareDownloadByUserId(
            request
        );
        String parentKey = io.gs2.datastore.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "DataObject"
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.datastore.domain.model.DataObjectDomain.createCacheKey(
                    result.getItem().getName() != null ? result.getItem().getName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        DataObjectDomain domain = new DataObjectDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            request.getNamespaceName(),
            result.getItem().getUserId(),
            result.getItem().getName()
        );
        domain.fileUrl = result.getFileUrl();
        domain.contentLength = result.getContentLength();

        return domain;
    }

    public DataObjectDomain prepareDownloadByGeneration(
        PrepareDownloadByGenerationAndUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId);
        PrepareDownloadByGenerationAndUserIdResult result = this.client.prepareDownloadByGenerationAndUserId(
            request
        );
        String parentKey = io.gs2.datastore.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "DataObject"
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.datastore.domain.model.DataObjectDomain.createCacheKey(
                    result.getItem().getName() != null ? result.getItem().getName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        DataObjectDomain domain = new DataObjectDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            request.getNamespaceName(),
            result.getItem().getUserId(),
            result.getItem().getName()
        );
        domain.fileUrl = result.getFileUrl();
        domain.contentLength = result.getContentLength();

        return domain;
    }

    public DescribeDataObjectsByUserIdIterator dataObjects(
        String status
    )
    {
        return new DescribeDataObjectsByUserIdIterator(
            this.cache,
            this.client,
            this.namespaceName,
            this.userId,
            status
        );
    }

    public DataObjectDomain dataObject(
        String dataObjectName
    ) {
        return new DataObjectDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.userId,
            dataObjectName
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
            "datastore",
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

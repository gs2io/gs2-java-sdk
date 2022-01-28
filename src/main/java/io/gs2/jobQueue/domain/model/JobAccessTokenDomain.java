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
package io.gs2.jobQueue.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.jobQueue.Gs2JobQueueRestClient;
import io.gs2.jobQueue.domain.iterator.*;
import io.gs2.jobQueue.model.*;
import io.gs2.jobQueue.request.*;
import io.gs2.jobQueue.result.*;

import java.util.List;


public class JobAccessTokenDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2JobQueueRestClient client;
    private final String namespaceName;
    private final AccessToken accessToken;
    private final String jobName;

    private final String parentKey;
    Boolean isLastJob;
    public Boolean getIsLastJob() {
        return this.isLastJob;
    }
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return accessToken.getUserId();
    }
    public String getJobName() {
        return jobName;
    }

    public JobAccessTokenDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        AccessToken accessToken,
        String jobName
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2JobQueueRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.accessToken = accessToken;
        this.jobName = jobName;
        this.parentKey = io.gs2.jobQueue.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.accessToken != null && this.accessToken.getUserId() != null ? this.accessToken.getUserId().toString() : null,
            "Job"
        );
    }

    public JobResultAccessTokenDomain jobResult(
        String tryNumber
    ) {
        return new JobResultAccessTokenDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.accessToken,
            this.jobName,
            tryNumber
        );
    }

    public static String createCacheParentKey(
        String namespaceName,
        String userId,
        String jobName,
        String childType
    )
    {
        return String.join(
            ":",
            "jobQueue",
            namespaceName,
            userId,
            jobName,
            childType
        );
    }

    public static String createCacheKey(
        String jobName
    )
    {
        return String.join(
            ":",
            jobName
        );
    }

    public Job model() {
        Job value = cache.get(
            parentKey,
            io.gs2.jobQueue.domain.model.JobDomain.createCacheKey(
                this.getJobName() != null ? this.getJobName().toString() : null
            ),
            Job.class
        );
        return value;
    }

}

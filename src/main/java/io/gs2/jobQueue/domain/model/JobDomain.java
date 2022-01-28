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


public class JobDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2JobQueueRestClient client;
    private final String namespaceName;
    private final String userId;
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
        return userId;
    }
    public String getJobName() {
        return jobName;
    }

    public JobDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String userId,
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
        this.userId = userId;
        this.jobName = jobName;
        this.parentKey = io.gs2.jobQueue.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "Job"
        );
    }

    private Job get(
        GetJobByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withJobName(this.jobName);
        GetJobByUserIdResult result = this.client.getJobByUserId(
            request
        );
        return result.getItem();
    }

    public JobDomain delete(
        DeleteJobByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withJobName(this.jobName);
        DeleteJobByUserIdResult result = null;
        try {
            result = this.client.deleteJobByUserId(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.jobQueue.domain.model.JobDomain.createCacheKey(
                request.getJobName() != null ? request.getJobName().toString() : null
            ),
            Job.class
        );
        JobDomain domain = this;

        return domain;
    }

    public JobResultDomain jobResult(
        String tryNumber
    ) {
        return new JobResultDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.userId,
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
        if (value == null) {
            try {
                this.get(
                    new GetJobByUserIdRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.jobQueue.domain.model.JobDomain.createCacheKey(
                        this.getJobName() != null ? this.getJobName().toString() : null
                    ),
                    Job.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.jobQueue.domain.model.JobDomain.createCacheKey(
                this.getJobName() != null ? this.getJobName().toString() : null
            ),
            Job.class
        );
        }
        return value;
    }

}

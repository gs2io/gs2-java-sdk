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


public class UserDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2JobQueueRestClient client;
    private final String namespaceName;
    private final String userId;

    private final String parentKey;
    Boolean isLastJob;
    public Boolean getIsLastJob() {
        return this.isLastJob;
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
        this.client = new Gs2JobQueueRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.userId = userId;
        this.parentKey = io.gs2.jobQueue.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "User"
        );
    }

    public JobDomain[] push(
        PushByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId);
        PushByUserIdResult result = this.client.pushByUserId(
            request
        );
        String parentKey = io.gs2.jobQueue.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "Job"
        );
        for (Job item : result.getItems()) {
                
            if (item != null) {
                cache.put(
                    parentKey,
                    io.gs2.jobQueue.domain.model.JobDomain.createCacheKey(
                        item.getName() != null ? item.getName().toString() : null
                    ),
                    item,
                    System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                );
            }
        }
        JobDomain[] domain = new JobDomain[result.getItems().size()];
        for (int i=0; i<result.getItems().size(); i++)
        {
            domain[i] = new JobDomain(
                this.cache,
                this.jobQueueDomain,
                this.stampSheetConfiguration,
                this.session,
                request.getNamespaceName(),
                result.getItems().get(i).getUserId(),
                result.getItems().get(i).getName()
            );
        }
        this.jobQueueDomain.push(
                this.namespaceName
        );
        return domain;
    }

    public JobDomain run(
        RunByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId);
        RunByUserIdResult result = null;
        try {
            result = this.client.runByUserId(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        String parentKey = io.gs2.jobQueue.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "Job"
        );
        cache.delete(
            parentKey,
            io.gs2.jobQueue.domain.model.JobDomain.createCacheKey(
                result.getItem().getName() != null ? result.getItem().getName().toString() : null
            ),
            Job.class
        );
        if (result.getItem() != null) {
            io.gs2.core.domain.Gs2.updateCacheFromJobResult(
                    cache,
                    result.getItem(),
                    result.getResult()
            );
        }
        JobDomain domain = new JobDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            request.getNamespaceName(),
            result.getItem().getUserId(),
            result.getItem().getName()
        );
        domain.isLastJob = result.getIsLastJob();

        return domain;
    }

    public DescribeJobsByUserIdIterator jobs(
    )
    {
        return new DescribeJobsByUserIdIterator(
            this.cache,
            this.client,
            this.namespaceName,
            this.userId
        );
    }

    public JobDomain job(
        String jobName
    ) {
        return new JobDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.userId,
            jobName
        );
    }

    public DescribeDeadLetterJobsByUserIdIterator deadLetterJobs(
    )
    {
        return new DescribeDeadLetterJobsByUserIdIterator(
            this.cache,
            this.client,
            this.namespaceName,
            this.userId
        );
    }

    public DeadLetterJobDomain deadLetterJob(
        String deadLetterJobName
    ) {
        return new DeadLetterJobDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.userId,
            deadLetterJobName
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
            "jobQueue",
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

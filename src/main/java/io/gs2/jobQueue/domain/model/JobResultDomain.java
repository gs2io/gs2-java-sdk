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


public class JobResultDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2JobQueueRestClient client;
    private final String namespaceName;
    private final String userId;
    private final String jobName;
    private final String tryNumber;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return userId;
    }
    public String getJobName() {
        return jobName;
    }
    public String getTryNumber() {
        return tryNumber;
    }

    public JobResultDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String userId,
        String jobName,
        String tryNumber
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
        this.tryNumber = tryNumber;
        this.parentKey = io.gs2.jobQueue.domain.model.JobDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            this.jobName != null ? this.jobName.toString() : null,
            "JobResult"
        );
    }

    public static String createCacheParentKey(
        String namespaceName,
        String userId,
        String jobName,
        String tryNumber,
        String childType
    )
    {
        return String.join(
            ":",
            "jobQueue",
            namespaceName,
            userId,
            jobName,
            tryNumber,
            childType
        );
    }

    public static String createCacheKey(
        String tryNumber
    )
    {
        return String.join(
            ":",
            tryNumber
        );
    }

    public JobResult model() {
        JobResult value = cache.get(
            parentKey,
            io.gs2.jobQueue.domain.model.JobResultDomain.createCacheKey(
                this.getTryNumber() != null ? this.getTryNumber().toString() : null
            ),
            JobResult.class
        );
        return value;
    }

}

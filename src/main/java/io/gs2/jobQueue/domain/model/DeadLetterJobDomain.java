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


public class DeadLetterJobDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2JobQueueRestClient client;
    private final String namespaceName;
    private final String userId;
    private final String deadLetterJobName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return userId;
    }
    public String getDeadLetterJobName() {
        return deadLetterJobName;
    }

    public DeadLetterJobDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String userId,
        String deadLetterJobName
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
        this.deadLetterJobName = deadLetterJobName;
        this.parentKey = io.gs2.jobQueue.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "DeadLetterJob"
        );
    }

    private DeadLetterJob get(
        GetDeadLetterJobByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withDeadLetterJobName(this.deadLetterJobName);
        GetDeadLetterJobByUserIdResult result = this.client.getDeadLetterJobByUserId(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.jobQueue.domain.model.DeadLetterJobDomain.createCacheKey(
                    request.getDeadLetterJobName() != null ? request.getDeadLetterJobName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public DeadLetterJobDomain delete(
        DeleteDeadLetterJobByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withDeadLetterJobName(this.deadLetterJobName);
        DeleteDeadLetterJobByUserIdResult result = null;
        try {
            result = this.client.deleteDeadLetterJobByUserId(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.jobQueue.domain.model.DeadLetterJobDomain.createCacheKey(
                request.getDeadLetterJobName() != null ? request.getDeadLetterJobName().toString() : null
            ),
            DeadLetterJob.class
        );
        DeadLetterJobDomain domain = this;

        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String userId,
        String deadLetterJobName,
        String childType
    )
    {
        return String.join(
            ":",
            "jobQueue",
            namespaceName,
            userId,
            deadLetterJobName,
            childType
        );
    }

    public static String createCacheKey(
        String deadLetterJobName
    )
    {
        return String.join(
            ":",
            deadLetterJobName
        );
    }

    public DeadLetterJob model() {
        DeadLetterJob value = cache.get(
            parentKey,
            io.gs2.jobQueue.domain.model.DeadLetterJobDomain.createCacheKey(
                this.getDeadLetterJobName() != null ? this.getDeadLetterJobName().toString() : null
            ),
            DeadLetterJob.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetDeadLetterJobByUserIdRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.jobQueue.domain.model.DeadLetterJobDomain.createCacheKey(
                        this.getDeadLetterJobName() != null ? this.getDeadLetterJobName().toString() : null
                    ),
                    DeadLetterJob.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.jobQueue.domain.model.DeadLetterJobDomain.createCacheKey(
                this.getDeadLetterJobName() != null ? this.getDeadLetterJobName().toString() : null
            ),
            DeadLetterJob.class
        );
        }
        return value;
    }

}

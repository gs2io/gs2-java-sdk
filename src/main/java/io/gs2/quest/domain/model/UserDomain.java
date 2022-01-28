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
package io.gs2.quest.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.quest.Gs2QuestRestClient;
import io.gs2.quest.domain.iterator.*;
import io.gs2.quest.model.*;
import io.gs2.quest.request.*;
import io.gs2.quest.result.*;

import java.util.List;


public class UserDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2QuestRestClient client;
    private final String namespaceName;
    private final String userId;

    private final String parentKey;
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
        this.client = new Gs2QuestRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.userId = userId;
        this.parentKey = io.gs2.quest.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "User"
        );
    }

    public ProgressDomain createProgress(
        CreateProgressByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId);
        CreateProgressByUserIdResult result = this.client.createProgressByUserId(
            request
        );
        String parentKey = io.gs2.quest.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "Progress"
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.quest.domain.model.ProgressDomain.createCacheKey(
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        ProgressDomain domain = new ProgressDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            request.getNamespaceName(),
            result.getItem().getUserId(),
            result.getItem().getTransactionId()
        );

        return domain;
    }

    public io.gs2.core.domain.StampSheetDomain start(
        StartByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId);
        StartByUserIdResult result = this.client.startByUserId(
            request
        );
        io.gs2.core.domain.StampSheetDomain stampSheet = new io.gs2.core.domain.StampSheetDomain(
                cache,
                jobQueueDomain,
                session,
                result.getStampSheet(),
                result.getStampSheetEncryptionKeyId(),
                stampSheetConfiguration.namespaceName,
                stampSheetConfiguration.stampTaskEventHandler,
                stampSheetConfiguration.stampSheetEventHandler
        );
        try {
            stampSheet.run();
        } catch (io.gs2.core.exception.Gs2Exception e) {
            throw new io.gs2.core.exception.TransactionException(stampSheet, e);
        }
        return stampSheet;
    }

    public ProgressDomain deleteProgress(
        DeleteProgressByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId);
        DeleteProgressByUserIdResult result = null;
        try {
            result = this.client.deleteProgressByUserId(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        String parentKey = io.gs2.quest.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "Progress"
        );
        cache.delete(
            parentKey,
            io.gs2.quest.domain.model.ProgressDomain.createCacheKey(
            ),
            Progress.class
        );
        ProgressDomain domain = new ProgressDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            request.getNamespaceName(),
            result.getItem().getUserId(),
            result.getItem().getTransactionId()
        );

        return domain;
    }

    public DescribeProgressesByUserIdIterator progresses(
    )
    {
        return new DescribeProgressesByUserIdIterator(
            this.cache,
            this.client,
            this.namespaceName,
            this.userId
        );
    }

    public ProgressDomain progress(
        String transactionId
    ) {
        return new ProgressDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.userId,
            transactionId
        );
    }

    public DescribeCompletedQuestListsByUserIdIterator completedQuestLists(
    )
    {
        return new DescribeCompletedQuestListsByUserIdIterator(
            this.cache,
            this.client,
            this.namespaceName,
            this.userId
        );
    }

    public CompletedQuestListDomain completedQuestList(
        String questGroupName
    ) {
        return new CompletedQuestListDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.userId,
            questGroupName
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
            "quest",
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

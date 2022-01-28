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


public class ProgressDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2QuestRestClient client;
    private final String namespaceName;
    private final String userId;
    private final String transactionId;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return userId;
    }
    public String getTransactionId() {
        return transactionId;
    }

    public ProgressDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String userId,
        String transactionId
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
        this.transactionId = transactionId;
        this.parentKey = io.gs2.quest.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "Progress"
        );
    }

    private Progress get(
        GetProgressByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId);
        GetProgressByUserIdResult result = this.client.getProgressByUserId(
            request
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
                
        if (result.getQuestGroup() != null) {
            cache.put(
                parentKey,
                io.gs2.quest.domain.model.QuestGroupModelDomain.createCacheKey(
                    result.getQuestGroup().getName() != null ? result.getQuestGroup().getName().toString() : null
                ),
                result.getQuestGroup(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
                
        if (result.getQuest() != null) {
            cache.put(
                parentKey,
                io.gs2.quest.domain.model.QuestModelDomain.createCacheKey(
                    result.getQuest().getName() != null ? result.getQuest().getName().toString() : null
                ),
                result.getQuest(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public io.gs2.core.domain.StampSheetDomain end(
        EndByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withTransactionId(this.transactionId);
        EndByUserIdResult result = this.client.endByUserId(
            request
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
    )
    {
        return "Singleton";
    }

    public Progress model() {
        Progress value = cache.get(
            parentKey,
            io.gs2.quest.domain.model.ProgressDomain.createCacheKey(
            ),
            Progress.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetProgressByUserIdRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.quest.domain.model.ProgressDomain.createCacheKey(
                    ),
                    Progress.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.quest.domain.model.ProgressDomain.createCacheKey(
            ),
            Progress.class
        );
        }
        return value;
    }

}

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


public class CompletedQuestListAccessTokenDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2QuestRestClient client;
    private final String namespaceName;
    private final AccessToken accessToken;
    private final String questGroupName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return accessToken.getUserId();
    }
    public String getQuestGroupName() {
        return questGroupName;
    }

    public CompletedQuestListAccessTokenDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        AccessToken accessToken,
        String questGroupName
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2QuestRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.accessToken = accessToken;
        this.questGroupName = questGroupName;
        this.parentKey = io.gs2.quest.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.accessToken != null && this.accessToken.getUserId() != null ? this.accessToken.getUserId().toString() : null,
            "CompletedQuestList"
        );
    }

    private CompletedQuestList get(
        GetCompletedQuestListRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
            .withQuestGroupName(this.questGroupName);
        GetCompletedQuestListResult result = this.client.getCompletedQuestList(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.quest.domain.model.CompletedQuestListDomain.createCacheKey(
                    request.getQuestGroupName() != null ? request.getQuestGroupName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public static String createCacheParentKey(
        String namespaceName,
        String userId,
        String questGroupName,
        String childType
    )
    {
        return String.join(
            ":",
            "quest",
            namespaceName,
            userId,
            questGroupName,
            childType
        );
    }

    public static String createCacheKey(
        String questGroupName
    )
    {
        return String.join(
            ":",
            questGroupName
        );
    }

    public CompletedQuestList model() {
        CompletedQuestList value = cache.get(
            parentKey,
            io.gs2.quest.domain.model.CompletedQuestListDomain.createCacheKey(
                this.getQuestGroupName() != null ? this.getQuestGroupName().toString() : null
            ),
            CompletedQuestList.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetCompletedQuestListRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.quest.domain.model.CompletedQuestListDomain.createCacheKey(
                        this.getQuestGroupName() != null ? this.getQuestGroupName().toString() : null
                    ),
                    CompletedQuestList.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.quest.domain.model.CompletedQuestListDomain.createCacheKey(
                this.getQuestGroupName() != null ? this.getQuestGroupName().toString() : null
            ),
            CompletedQuestList.class
        );
        }
        return value;
    }

}

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


public class QuestGroupModelDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2QuestRestClient client;
    private final String namespaceName;
    private final String questGroupName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getQuestGroupName() {
        return questGroupName;
    }

    public QuestGroupModelDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
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
        this.questGroupName = questGroupName;
        this.parentKey = io.gs2.quest.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "QuestGroupModel"
        );
    }

    private QuestGroupModel get(
        GetQuestGroupModelRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withQuestGroupName(this.questGroupName);
        GetQuestGroupModelResult result = this.client.getQuestGroupModel(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.quest.domain.model.QuestGroupModelDomain.createCacheKey(
                    request.getQuestGroupName() != null ? request.getQuestGroupName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public DescribeQuestModelsIterator questModels(
    )
    {
        return new DescribeQuestModelsIterator(
            this.cache,
            this.client,
            this.namespaceName,
            this.questGroupName
        );
    }

    public QuestModelDomain questModel(
        String questName
    ) {
        return new QuestModelDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.questGroupName,
            questName
        );
    }

    public static String createCacheParentKey(
        String namespaceName,
        String questGroupName,
        String childType
    )
    {
        return String.join(
            ":",
            "quest",
            namespaceName,
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

    public QuestGroupModel model() {
        QuestGroupModel value = cache.get(
            parentKey,
            io.gs2.quest.domain.model.QuestGroupModelDomain.createCacheKey(
                this.getQuestGroupName() != null ? this.getQuestGroupName().toString() : null
            ),
            QuestGroupModel.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetQuestGroupModelRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.quest.domain.model.QuestGroupModelDomain.createCacheKey(
                        this.getQuestGroupName() != null ? this.getQuestGroupName().toString() : null
                    ),
                    QuestGroupModel.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.quest.domain.model.QuestGroupModelDomain.createCacheKey(
                this.getQuestGroupName() != null ? this.getQuestGroupName().toString() : null
            ),
            QuestGroupModel.class
        );
        }
        return value;
    }

}

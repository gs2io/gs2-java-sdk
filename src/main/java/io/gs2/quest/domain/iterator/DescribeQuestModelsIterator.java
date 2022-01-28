
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
package io.gs2.quest.domain.iterator;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.quest.Gs2QuestRestClient;
import io.gs2.quest.model.*;
import io.gs2.quest.request.*;
import io.gs2.quest.result.*;

import java.util.*;
import java.util.stream.Collectors;

public class DescribeQuestModelsIterator implements Iterator<QuestModel>, Iterable<QuestModel> {
    CacheDatabase cache;
    Gs2QuestRestClient client;
    String namespaceName;
    String questGroupName;
    boolean last;
    List<QuestModel> result;

    Integer fetchSize;

    public DescribeQuestModelsIterator(
        CacheDatabase cache,
        Gs2QuestRestClient client,
        String namespaceName,
        String questGroupName
    ) {
        this.cache = cache;
        this.client = client;
        this.namespaceName = namespaceName;
        this.questGroupName = questGroupName;
        this.last = false;
        this.result = new ArrayList<>();

        this.fetchSize = null;
        this.load();
    }

    private void load() {
        String parentKey = io.gs2.quest.domain.model.QuestGroupModelDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.questGroupName != null ? this.questGroupName.toString() : null,
            "QuestModel"
        );
        String listParentKey = parentKey;
        if (this.cache.isListCached(
                listParentKey,
                QuestModel.class
        )) {
            this.result = this.cache.list(
                    parentKey,
                    QuestModel.class
            ).stream()
                .collect(Collectors.toList());
            this.last = true;
        } else {

            DescribeQuestModelsResult r = this.client.describeQuestModels(
                new DescribeQuestModelsRequest()
                    .withNamespaceName(this.namespaceName)
                    .withQuestGroupName(this.questGroupName)
                );
            this.result = r.getItems();
            this.last = true;
            for (QuestModel item : this.result) {
                this.cache.put(
                        parentKey,
                        io.gs2.quest.domain.model.QuestModelDomain.createCacheKey(
                                item.getName() != null ? item.getName().toString() : null
                        ),
                        item,
                        System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                );
            }

            if (this.last) {
                this.cache.listCached(
                        listParentKey,
                        QuestModel.class
                );
            }
        }
    }

    @Override
    public boolean hasNext(

    ) {
        return this.result.size() != 0 || !this.last;
    }

    @Override
    public QuestModel next(

    ) {
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        if (this.result.size() == 0) {
            return null;
        }
        QuestModel ret = this.result.get(0);
        this.result = this.result.subList(1, this.result.size());
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        return ret;
    }

    @Override
    public Iterator<QuestModel> iterator() {
        return this;
    }
}

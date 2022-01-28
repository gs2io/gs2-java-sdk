
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
package io.gs2.mission.domain.iterator;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.mission.Gs2MissionRestClient;
import io.gs2.mission.model.*;
import io.gs2.mission.request.*;
import io.gs2.mission.result.*;

import java.util.*;
import java.util.stream.Collectors;

public class DescribeMissionTaskModelMastersIterator implements Iterator<MissionTaskModelMaster>, Iterable<MissionTaskModelMaster> {
    CacheDatabase cache;
    Gs2MissionRestClient client;
    String namespaceName;
    String missionGroupName;
    String pageToken;
    boolean last;
    List<MissionTaskModelMaster> result;

    Integer fetchSize;

    public DescribeMissionTaskModelMastersIterator(
        CacheDatabase cache,
        Gs2MissionRestClient client,
        String namespaceName,
        String missionGroupName
    ) {
        this.cache = cache;
        this.client = client;
        this.namespaceName = namespaceName;
        this.missionGroupName = missionGroupName;
        this.pageToken = null;
        this.last = false;
        this.result = new ArrayList<>();

        this.fetchSize = null;
        this.load();
    }

    private void load() {
        String parentKey = io.gs2.mission.domain.model.MissionGroupModelMasterDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.missionGroupName != null ? this.missionGroupName.toString() : null,
            "MissionTaskModelMaster"
        );
        String listParentKey = parentKey;
        if (this.cache.isListCached(
                listParentKey,
                MissionTaskModelMaster.class
        )) {
            this.result = this.cache.list(
                    parentKey,
                    MissionTaskModelMaster.class
            ).stream()
                .collect(Collectors.toList());
            this.pageToken = null;
            this.last = true;
        } else {

            DescribeMissionTaskModelMastersResult r = this.client.describeMissionTaskModelMasters(
                new DescribeMissionTaskModelMastersRequest()
                    .withNamespaceName(this.namespaceName)
                    .withMissionGroupName(this.missionGroupName)
                    .withPageToken(this.pageToken)
                    .withLimit(this.fetchSize)
                );
            this.result = r.getItems();
            this.pageToken = r.getNextPageToken();
            this.last = this.pageToken == null;
            for (MissionTaskModelMaster item : this.result) {
                this.cache.put(
                        parentKey,
                        io.gs2.mission.domain.model.MissionTaskModelMasterDomain.createCacheKey(
                                item.getName() != null ? item.getName().toString() : null
                        ),
                        item,
                        System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                );
            }

            if (this.last) {
                this.cache.listCached(
                        listParentKey,
                        MissionTaskModelMaster.class
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
    public MissionTaskModelMaster next(

    ) {
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        if (this.result.size() == 0) {
            return null;
        }
        MissionTaskModelMaster ret = this.result.get(0);
        this.result = this.result.subList(1, this.result.size());
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        return ret;
    }

    @Override
    public Iterator<MissionTaskModelMaster> iterator() {
        return this;
    }
}

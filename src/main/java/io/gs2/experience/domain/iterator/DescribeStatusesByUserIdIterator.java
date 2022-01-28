
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
package io.gs2.experience.domain.iterator;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.experience.Gs2ExperienceRestClient;
import io.gs2.experience.model.*;
import io.gs2.experience.request.*;
import io.gs2.experience.result.*;

import java.util.*;
import java.util.stream.Collectors;

public class DescribeStatusesByUserIdIterator implements Iterator<Status>, Iterable<Status> {
    CacheDatabase cache;
    Gs2ExperienceRestClient client;
    String namespaceName;
    String experienceName;
    String userId;
    String pageToken;
    boolean last;
    List<Status> result;

    Integer fetchSize;

    public DescribeStatusesByUserIdIterator(
        CacheDatabase cache,
        Gs2ExperienceRestClient client,
        String namespaceName,
        String experienceName,
        String userId
    ) {
        this.cache = cache;
        this.client = client;
        this.namespaceName = namespaceName;
        this.experienceName = experienceName;
        this.userId = userId;
        this.pageToken = null;
        this.last = false;
        this.result = new ArrayList<>();

        this.fetchSize = null;
        this.load();
    }

    private void load() {
        String parentKey = io.gs2.experience.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "Status"
        );
        String listParentKey = parentKey;
        if (this.cache.isListCached(
                listParentKey,
                Status.class
        )) {
            this.result = this.cache.list(
                    parentKey,
                    Status.class
            ).stream()
                .filter(item -> this.experienceName == null || item.getExperienceName().equals(this.experienceName))
                .collect(Collectors.toList());
            this.pageToken = null;
            this.last = true;
        } else {

            DescribeStatusesByUserIdResult r = this.client.describeStatusesByUserId(
                new DescribeStatusesByUserIdRequest()
                    .withNamespaceName(this.namespaceName)
                    .withExperienceName(this.experienceName)
                    .withUserId(this.userId)
                    .withPageToken(this.pageToken)
                    .withLimit(this.fetchSize)
                );
            this.result = r.getItems();
            this.pageToken = r.getNextPageToken();
            this.last = this.pageToken == null;
            for (Status item : this.result) {
                this.cache.put(
                        parentKey,
                        io.gs2.experience.domain.model.StatusDomain.createCacheKey(
                                item.getExperienceName() != null ? item.getExperienceName().toString() : null,
                                item.getPropertyId() != null ? item.getPropertyId().toString() : null
                        ),
                        item,
                        System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                );
            }

            if (this.last) {
                this.cache.listCached(
                        listParentKey,
                        Status.class
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
    public Status next(

    ) {
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        if (this.result.size() == 0) {
            return null;
        }
        Status ret = this.result.get(0);
        this.result = this.result.subList(1, this.result.size());
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        return ret;
    }

    @Override
    public Iterator<Status> iterator() {
        return this;
    }
}


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
package io.gs2.schedule.domain.iterator;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.schedule.Gs2ScheduleRestClient;
import io.gs2.schedule.model.*;
import io.gs2.schedule.request.*;
import io.gs2.schedule.result.*;

import java.util.*;
import java.util.stream.Collectors;

public class DescribeEventsByUserIdIterator implements Iterator<Event>, Iterable<Event> {
    CacheDatabase cache;
    Gs2ScheduleRestClient client;
    String namespaceName;
    String userId;
    boolean last;
    List<Event> result;

    Integer fetchSize;

    public DescribeEventsByUserIdIterator(
        CacheDatabase cache,
        Gs2ScheduleRestClient client,
        String namespaceName,
        String userId
    ) {
        this.cache = cache;
        this.client = client;
        this.namespaceName = namespaceName;
        this.userId = userId;
        this.last = false;
        this.result = new ArrayList<>();

        this.fetchSize = null;
        this.load();
    }

    private void load() {
        String parentKey = io.gs2.schedule.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "Event"
        );
        String listParentKey = parentKey;
        if (this.cache.isListCached(
                listParentKey,
                Event.class
        )) {
            this.result = this.cache.list(
                    parentKey,
                    Event.class
            ).stream()
                .collect(Collectors.toList());
            this.last = true;
        } else {

            DescribeEventsByUserIdResult r = this.client.describeEventsByUserId(
                new DescribeEventsByUserIdRequest()
                    .withNamespaceName(this.namespaceName)
                    .withUserId(this.userId)
                );
            this.result = r.getItems();
            this.last = true;
            for (Event item : this.result) {
                this.cache.put(
                        parentKey,
                        io.gs2.schedule.domain.model.EventDomain.createCacheKey(
                                item.getName() != null ? item.getName().toString() : null
                        ),
                        item,
                        System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                );
            }

            if (this.last) {
                this.cache.listCached(
                        listParentKey,
                        Event.class
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
    public Event next(

    ) {
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        if (this.result.size() == 0) {
            return null;
        }
        Event ret = this.result.get(0);
        this.result = this.result.subList(1, this.result.size());
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        return ret;
    }

    @Override
    public Iterator<Event> iterator() {
        return this;
    }
}

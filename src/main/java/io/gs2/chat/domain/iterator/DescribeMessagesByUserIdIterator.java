
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
package io.gs2.chat.domain.iterator;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.chat.Gs2ChatRestClient;
import io.gs2.chat.model.*;
import io.gs2.chat.request.*;
import io.gs2.chat.result.*;

import java.util.*;
import java.util.stream.Collectors;

public class DescribeMessagesByUserIdIterator implements Iterator<Message>, Iterable<Message> {
    CacheDatabase cache;
    Gs2ChatRestClient client;
    String namespaceName;
    String roomName;
    String password;
    String userId;
    Long startAt;
    boolean last;
    List<Message> result;

    Integer fetchSize;

    public DescribeMessagesByUserIdIterator(
        CacheDatabase cache,
        Gs2ChatRestClient client,
        String namespaceName,
        String roomName,
        String password,
        String userId
    ) {
        this.cache = cache;
        this.client = client;
        this.namespaceName = namespaceName;
        this.roomName = roomName;
        this.password = password;
        this.userId = userId;
        this.startAt = null;
        this.last = false;
        this.result = new ArrayList<>();

        this.fetchSize = null;
        this.load();
    }

    private void load() {
        String parentKey = io.gs2.chat.domain.model.RoomDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "Singleton",
            this.roomName != null ? this.roomName.toString() : null,
            "Message"
        );
        String listParentKey = parentKey;
        if (this.cache.isListCached(
                listParentKey,
                Message.class
        )) {
            this.result = this.cache.list(
                    parentKey,
                    Message.class
            ).stream()
                .collect(Collectors.toList());
            this.last = true;
        } else {

            DescribeMessagesByUserIdResult r = this.client.describeMessagesByUserId(
                new DescribeMessagesByUserIdRequest()
                    .withNamespaceName(this.namespaceName)
                    .withRoomName(this.roomName)
                    .withPassword(this.password)
                    .withUserId(this.userId)
                    .withStartAt(this.startAt)
                    .withLimit(this.fetchSize)
                );
            this.result = r.getItems();
            if (this.result.size() > 0) {
                this.startAt = this.result.get(this.result.size()-1).getCreatedAt() + 1;
            }
            for (Message item : this.result) {
                this.cache.put(
                        parentKey,
                        io.gs2.chat.domain.model.MessageDomain.createCacheKey(
                                item.getName() != null ? item.getName().toString() : null
                        ),
                        item,
                        System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                );
            }

            if (this.last) {
                this.cache.listCached(
                        listParentKey,
                        Message.class
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
    public Message next(

    ) {
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        if (this.result.size() == 0) {
            return null;
        }
        Message ret = this.result.get(0);
        this.result = this.result.subList(1, this.result.size());
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        return ret;
    }

    @Override
    public Iterator<Message> iterator() {
        return this;
    }
}

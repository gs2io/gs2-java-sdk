
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

public class DescribeSubscribesByRoomNameIterator implements Iterator<Subscribe>, Iterable<Subscribe> {
    CacheDatabase cache;
    Gs2ChatRestClient client;
    String namespaceName;
    String roomName;
    String pageToken;
    boolean last;
    List<Subscribe> result;

    Integer fetchSize;

    public DescribeSubscribesByRoomNameIterator(
        CacheDatabase cache,
        Gs2ChatRestClient client,
        String namespaceName,
        String roomName
    ) {
        this.cache = cache;
        this.client = client;
        this.namespaceName = namespaceName;
        this.roomName = roomName;
        this.pageToken = null;
        this.last = false;
        this.result = new ArrayList<>();

        this.fetchSize = null;
        this.load();
    }

    private void load() {

        DescribeSubscribesByRoomNameResult r = this.client.describeSubscribesByRoomName(
            new DescribeSubscribesByRoomNameRequest()
                .withNamespaceName(this.namespaceName)
                .withRoomName(this.roomName)
                .withPageToken(this.pageToken)
                .withLimit(this.fetchSize)
            );
        this.result = r.getItems();
        this.pageToken = r.getNextPageToken();
        this.last = this.pageToken == null;
    }

    @Override
    public boolean hasNext(

    ) {
        return this.result.size() != 0 || !this.last;
    }

    @Override
    public Subscribe next(

    ) {
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        if (this.result.size() == 0) {
            return null;
        }
        Subscribe ret = this.result.get(0);
        this.result = this.result.subList(1, this.result.size());
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        return ret;
    }

    @Override
    public Iterator<Subscribe> iterator() {
        return this;
    }
}

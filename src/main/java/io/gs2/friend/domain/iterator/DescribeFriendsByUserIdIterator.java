
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
package io.gs2.friend.domain.iterator;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.friend.Gs2FriendRestClient;
import io.gs2.friend.model.*;
import io.gs2.friend.request.*;
import io.gs2.friend.result.*;

import java.util.*;
import java.util.stream.Collectors;

public class DescribeFriendsByUserIdIterator implements Iterator<FriendUser>, Iterable<FriendUser> {
    CacheDatabase cache;
    Gs2FriendRestClient client;
    String namespaceName;
    String userId;
    Boolean withProfile;
    String pageToken;
    boolean last;
    List<FriendUser> result;

    Integer fetchSize;

    public DescribeFriendsByUserIdIterator(
        CacheDatabase cache,
        Gs2FriendRestClient client,
        String namespaceName,
        String userId,
        Boolean withProfile
    ) {
        this.cache = cache;
        this.client = client;
        this.namespaceName = namespaceName;
        this.userId = userId;
        this.withProfile = withProfile;
        this.pageToken = null;
        this.last = false;
        this.result = new ArrayList<>();

        this.fetchSize = null;
        this.load();
    }

    private void load() {
        String parentKey = io.gs2.friend.domain.model.FriendDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            this.withProfile != null ? this.withProfile.toString() : "false",
            "FriendUser"
        );
        String listParentKey = parentKey;
        if (this.cache.isListCached(
                listParentKey,
                FriendUser.class
        )) {
            this.result = this.cache.list(
                    parentKey,
                    FriendUser.class
            ).stream()
                .collect(Collectors.toList());
            this.pageToken = null;
            this.last = true;
        } else {

            DescribeFriendsByUserIdResult r = this.client.describeFriendsByUserId(
                new DescribeFriendsByUserIdRequest()
                    .withNamespaceName(this.namespaceName)
                    .withUserId(this.userId)
                    .withWithProfile(this.withProfile)
                    .withPageToken(this.pageToken)
                    .withLimit(this.fetchSize)
                );
            this.result = r.getItems();
            this.pageToken = r.getNextPageToken();
            this.last = this.pageToken == null;
            for (FriendUser item : this.result) {
                this.cache.put(
                        parentKey,
                        io.gs2.friend.domain.model.FriendUserDomain.createCacheKey(
                                item.getUserId() != null ? item.getUserId().toString() : null
                        ),
                        item,
                        System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                );
            }

            if (this.last) {
                this.cache.listCached(
                        listParentKey,
                        FriendUser.class
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
    public FriendUser next(

    ) {
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        if (this.result.size() == 0) {
            return null;
        }
        FriendUser ret = this.result.get(0);
        this.result = this.result.subList(1, this.result.size());
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        return ret;
    }

    @Override
    public Iterator<FriendUser> iterator() {
        return this;
    }
}

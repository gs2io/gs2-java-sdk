
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

public class DescribeFollowsIterator implements Iterator<FollowUser>, Iterable<FollowUser> {
    CacheDatabase cache;
    Gs2FriendRestClient client;
    String namespaceName;
    AccessToken accessToken;
    Boolean withProfile;
    String pageToken;
    boolean last;
    List<FollowUser> result;

    Integer fetchSize;

    public DescribeFollowsIterator(
        CacheDatabase cache,
        Gs2FriendRestClient client,
        String namespaceName,
        AccessToken accessToken,
        Boolean withProfile
    ) {
        this.cache = cache;
        this.client = client;
        this.namespaceName = namespaceName;
        this.accessToken = accessToken;
        this.withProfile = withProfile;
        this.pageToken = null;
        this.last = false;
        this.result = new ArrayList<>();

        this.fetchSize = null;
        this.load();
    }

    private void load() {
        String parentKey = io.gs2.friend.domain.model.FollowDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.accessToken != null && this.accessToken.getUserId() != null ? this.accessToken.getUserId().toString() : null,
            this.withProfile != null ? this.withProfile.toString() : "false",
            "FollowUser"
        );
        String listParentKey = parentKey;
        if (this.cache.isListCached(
                listParentKey,
                FollowUser.class
        )) {
            this.result = this.cache.list(
                    parentKey,
                    FollowUser.class
            ).stream()
                .collect(Collectors.toList());
            this.pageToken = null;
            this.last = true;
        } else {

            DescribeFollowsResult r = this.client.describeFollows(
                new DescribeFollowsRequest()
                    .withNamespaceName(this.namespaceName)
                    .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null)
                    .withWithProfile(this.withProfile)
                    .withPageToken(this.pageToken)
                    .withLimit(this.fetchSize)
                );
            this.result = r.getItems();
            this.pageToken = r.getNextPageToken();
            this.last = this.pageToken == null;
            for (FollowUser item : this.result) {
                this.cache.put(
                        parentKey,
                        io.gs2.friend.domain.model.FollowUserDomain.createCacheKey(
                                item.getUserId() != null ? item.getUserId().toString() : null
                        ),
                        item,
                        System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                );
            }

            if (this.last) {
                this.cache.listCached(
                        listParentKey,
                        FollowUser.class
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
    public FollowUser next(

    ) {
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        if (this.result.size() == 0) {
            return null;
        }
        FollowUser ret = this.result.get(0);
        this.result = this.result.subList(1, this.result.size());
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        return ret;
    }

    @Override
    public Iterator<FollowUser> iterator() {
        return this;
    }
}

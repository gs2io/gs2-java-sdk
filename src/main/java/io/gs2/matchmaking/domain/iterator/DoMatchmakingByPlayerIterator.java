
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
package io.gs2.matchmaking.domain.iterator;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.matchmaking.Gs2MatchmakingRestClient;
import io.gs2.matchmaking.model.*;
import io.gs2.matchmaking.request.*;
import io.gs2.matchmaking.result.*;

import java.util.*;
import java.util.stream.Collectors;

public class DoMatchmakingByPlayerIterator implements Iterator<Gathering>, Iterable<Gathering> {
    CacheDatabase cache;
    Gs2MatchmakingRestClient client;
    String namespaceName;
    Player player;
    String matchmakingContextToken;
    boolean last;
    List<Gathering> result;

    Integer fetchSize;

    public DoMatchmakingByPlayerIterator(
        CacheDatabase cache,
        Gs2MatchmakingRestClient client,
        String namespaceName,
        Player player
    ) {
        this.cache = cache;
        this.client = client;
        this.namespaceName = namespaceName;
        this.player = player;
        this.matchmakingContextToken = null;
        this.last = false;
        this.result = new ArrayList<>();

        this.fetchSize = null;
        this.load();
    }

    private void load() {

        DoMatchmakingByPlayerResult r = this.client.doMatchmakingByPlayer(
            new DoMatchmakingByPlayerRequest()
                .withNamespaceName(this.namespaceName)
                .withPlayer(this.player)
                .withMatchmakingContextToken(this.matchmakingContextToken)
            );
        this.result = new ArrayList<>();
        this.result.add(r.getItem());
        this.matchmakingContextToken = r.getMatchmakingContextToken();
        this.last = this.matchmakingContextToken == null;
        this.cache.listCacheClear(
            io.gs2.matchmaking.domain.model.UserDomain.createCacheParentKey(
                this.namespaceName != null ? this.namespaceName.toString() : null,
                "Singleton",
                "Gathering"
            ),
            Gathering.class
        );
    }

    @Override
    public boolean hasNext(

    ) {
        return this.result.size() != 0 || !this.last;
    }

    @Override
    public Gathering next(

    ) {
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        if (this.result.size() == 0) {
            return null;
        }
        Gathering ret = this.result.get(0);
        this.result = this.result.subList(1, this.result.size());
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        return ret;
    }

    @Override
    public Iterator<Gathering> iterator() {
        return this;
    }
}

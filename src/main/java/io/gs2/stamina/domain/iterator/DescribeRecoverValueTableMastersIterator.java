
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
package io.gs2.stamina.domain.iterator;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.stamina.Gs2StaminaRestClient;
import io.gs2.stamina.model.*;
import io.gs2.stamina.request.*;
import io.gs2.stamina.result.*;

import java.util.*;
import java.util.stream.Collectors;

public class DescribeRecoverValueTableMastersIterator implements Iterator<RecoverValueTableMaster>, Iterable<RecoverValueTableMaster> {
    CacheDatabase cache;
    Gs2StaminaRestClient client;
    String namespaceName;
    String pageToken;
    boolean last;
    List<RecoverValueTableMaster> result;

    Integer fetchSize;

    public DescribeRecoverValueTableMastersIterator(
        CacheDatabase cache,
        Gs2StaminaRestClient client,
        String namespaceName
    ) {
        this.cache = cache;
        this.client = client;
        this.namespaceName = namespaceName;
        this.pageToken = null;
        this.last = false;
        this.result = new ArrayList<>();

        this.fetchSize = null;
        this.load();
    }

    private void load() {
        String parentKey = io.gs2.stamina.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "RecoverValueTableMaster"
        );
        String listParentKey = parentKey;
        if (this.cache.isListCached(
                listParentKey,
                RecoverValueTableMaster.class
        )) {
            this.result = this.cache.list(
                    parentKey,
                    RecoverValueTableMaster.class
            ).stream()
                .collect(Collectors.toList());
            this.pageToken = null;
            this.last = true;
        } else {

            DescribeRecoverValueTableMastersResult r = this.client.describeRecoverValueTableMasters(
                new DescribeRecoverValueTableMastersRequest()
                    .withNamespaceName(this.namespaceName)
                    .withPageToken(this.pageToken)
                    .withLimit(this.fetchSize)
                );
            this.result = r.getItems();
            this.pageToken = r.getNextPageToken();
            this.last = this.pageToken == null;
            for (RecoverValueTableMaster item : this.result) {
                this.cache.put(
                        parentKey,
                        io.gs2.stamina.domain.model.RecoverValueTableMasterDomain.createCacheKey(
                                item.getName() != null ? item.getName().toString() : null
                        ),
                        item,
                        System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                );
            }

            if (this.last) {
                this.cache.listCached(
                        listParentKey,
                        RecoverValueTableMaster.class
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
    public RecoverValueTableMaster next(

    ) {
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        if (this.result.size() == 0) {
            return null;
        }
        RecoverValueTableMaster ret = this.result.get(0);
        this.result = this.result.subList(1, this.result.size());
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        return ret;
    }

    @Override
    public Iterator<RecoverValueTableMaster> iterator() {
        return this;
    }
}


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
package io.gs2.money.domain.iterator;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.money.Gs2MoneyRestClient;
import io.gs2.money.model.*;
import io.gs2.money.request.*;
import io.gs2.money.result.*;

import java.util.*;
import java.util.stream.Collectors;

public class DescribeReceiptsIterator implements Iterator<Receipt>, Iterable<Receipt> {
    CacheDatabase cache;
    Gs2MoneyRestClient client;
    String namespaceName;
    String userId;
    Integer slot;
    Long begin;
    Long end;
    String pageToken;
    boolean last;
    List<Receipt> result;

    Integer fetchSize;

    public DescribeReceiptsIterator(
        CacheDatabase cache,
        Gs2MoneyRestClient client,
        String namespaceName,
        String userId,
        Integer slot,
        Long begin,
        Long end
    ) {
        this.cache = cache;
        this.client = client;
        this.namespaceName = namespaceName;
        this.userId = userId;
        this.slot = slot;
        this.begin = begin;
        this.end = end;
        this.pageToken = null;
        this.last = false;
        this.result = new ArrayList<>();

        this.fetchSize = null;
        this.load();
    }

    private void load() {
        String parentKey = io.gs2.money.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "Receipt"
        );
        String listParentKey = parentKey;
        if (this.cache.isListCached(
                listParentKey,
                Receipt.class
        )) {
            this.result = this.cache.list(
                    parentKey,
                    Receipt.class
            ).stream()
                .filter(item -> this.slot == null || item.getSlot().equals(this.slot))
                .filter(item -> this.begin == null || item.getCreatedAt() >= this.begin)
                .filter(item -> this.end == null || item.getCreatedAt() <= this.end)
                .collect(Collectors.toList());
            this.pageToken = null;
            this.last = true;
        } else {

            DescribeReceiptsResult r = this.client.describeReceipts(
                new DescribeReceiptsRequest()
                    .withNamespaceName(this.namespaceName)
                    .withUserId(this.userId)
                    .withSlot(this.slot)
                    .withBegin(this.begin)
                    .withEnd(this.end)
                    .withPageToken(this.pageToken)
                    .withLimit(this.fetchSize)
                );
            this.result = r.getItems();
            this.pageToken = r.getNextPageToken();
            this.last = this.pageToken == null;
            for (Receipt item : this.result) {
                this.cache.put(
                        parentKey,
                        io.gs2.money.domain.model.ReceiptDomain.createCacheKey(
                                item.getTransactionId() != null ? item.getTransactionId().toString() : null
                        ),
                        item,
                        System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                );
            }

            if (this.last) {
                this.cache.listCached(
                        listParentKey,
                        Receipt.class
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
    public Receipt next(

    ) {
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        if (this.result.size() == 0) {
            return null;
        }
        Receipt ret = this.result.get(0);
        this.result = this.result.subList(1, this.result.size());
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        return ret;
    }

    @Override
    public Iterator<Receipt> iterator() {
        return this;
    }
}

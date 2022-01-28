
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
package io.gs2.log.domain.iterator;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.log.Gs2LogRestClient;
import io.gs2.log.model.*;
import io.gs2.log.request.*;
import io.gs2.log.result.*;

import java.util.*;
import java.util.stream.Collectors;

public class QueryIssueStampSheetLogIterator implements Iterator<IssueStampSheetLog>, Iterable<IssueStampSheetLog> {
    CacheDatabase cache;
    Gs2LogRestClient client;
    String namespaceName;
    String service;
    String method;
    String userId;
    String action;
    Long begin;
    Long end;
    Boolean longTerm;
    String pageToken;
    boolean last;
    List<IssueStampSheetLog> result;

    Integer fetchSize;

    public QueryIssueStampSheetLogIterator(
        CacheDatabase cache,
        Gs2LogRestClient client,
        String namespaceName,
        String service,
        String method,
        String userId,
        String action,
        Long begin,
        Long end,
        Boolean longTerm
    ) {
        this.cache = cache;
        this.client = client;
        this.namespaceName = namespaceName;
        this.service = service;
        this.method = method;
        this.userId = userId;
        this.action = action;
        this.begin = begin;
        this.end = end;
        this.longTerm = longTerm;
        this.pageToken = null;
        this.last = false;
        this.result = new ArrayList<>();

        this.fetchSize = null;
        this.load();
    }

    private void load() {
        String parentKey = io.gs2.log.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "IssueStampSheetLog"
        );
        String listParentKey = parentKey;
        if (this.cache.isListCached(
                listParentKey,
                IssueStampSheetLog.class
        )) {
            this.result = this.cache.list(
                    parentKey,
                    IssueStampSheetLog.class
            ).stream()
                .filter(item -> this.service == null || item.getService().equals(this.service))
                .filter(item -> this.method == null || item.getMethod().equals(this.method))
                .filter(item -> this.userId == null || item.getUserId().equals(this.userId))
                .filter(item -> this.action == null || item.getAction().equals(this.action))
                .filter(item -> this.begin == null || item.getTimestamp() >= this.begin)
                .filter(item -> this.end == null || item.getTimestamp() <= this.end)
                .collect(Collectors.toList());
            this.pageToken = null;
            this.last = true;
        } else {

            QueryIssueStampSheetLogResult r = this.client.queryIssueStampSheetLog(
                new QueryIssueStampSheetLogRequest()
                    .withNamespaceName(this.namespaceName)
                    .withService(this.service)
                    .withMethod(this.method)
                    .withUserId(this.userId)
                    .withAction(this.action)
                    .withBegin(this.begin)
                    .withEnd(this.end)
                    .withLongTerm(this.longTerm)
                    .withPageToken(this.pageToken)
                    .withLimit(this.fetchSize)
                );
            this.result = r.getItems();
            this.pageToken = r.getNextPageToken();
            this.last = this.pageToken == null;
            for (IssueStampSheetLog item : this.result) {
                this.cache.put(
                        parentKey,
                        io.gs2.log.domain.model.IssueStampSheetLogDomain.createCacheKey(
                        ),
                        item,
                        System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                );
            }

            if (this.last) {
                this.cache.listCached(
                        listParentKey,
                        IssueStampSheetLog.class
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
    public IssueStampSheetLog next(

    ) {
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        if (this.result.size() == 0) {
            return null;
        }
        IssueStampSheetLog ret = this.result.get(0);
        this.result = this.result.subList(1, this.result.size());
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        return ret;
    }

    @Override
    public Iterator<IssueStampSheetLog> iterator() {
        return this;
    }
}

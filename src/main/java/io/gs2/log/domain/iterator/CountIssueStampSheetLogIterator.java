
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

public class CountIssueStampSheetLogIterator implements Iterator<IssueStampSheetLogCount>, Iterable<IssueStampSheetLogCount> {
    CacheDatabase cache;
    Gs2LogRestClient client;
    String namespaceName;
    Boolean service;
    Boolean method;
    Boolean userId;
    Boolean action;
    Long begin;
    Long end;
    Boolean longTerm;
    String pageToken;
    boolean last;
    List<IssueStampSheetLogCount> result;

    Integer fetchSize;

    public CountIssueStampSheetLogIterator(
        CacheDatabase cache,
        Gs2LogRestClient client,
        String namespaceName,
        Boolean service,
        Boolean method,
        Boolean userId,
        Boolean action,
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

        CountIssueStampSheetLogResult r = this.client.countIssueStampSheetLog(
            new CountIssueStampSheetLogRequest()
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
    }

    @Override
    public boolean hasNext(

    ) {
        return this.result.size() != 0 || !this.last;
    }

    @Override
    public IssueStampSheetLogCount next(

    ) {
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        if (this.result.size() == 0) {
            return null;
        }
        IssueStampSheetLogCount ret = this.result.get(0);
        this.result = this.result.subList(1, this.result.size());
        if (this.result.size() == 0 && !this.last) {
            this.load();
        }
        return ret;
    }

    @Override
    public Iterator<IssueStampSheetLogCount> iterator() {
        return this;
    }
}

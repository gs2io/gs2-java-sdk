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
package io.gs2.log.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.log.Gs2LogRestClient;
import io.gs2.log.domain.iterator.*;
import io.gs2.log.model.*;
import io.gs2.log.request.*;
import io.gs2.log.result.*;

import java.util.List;


public class NamespaceDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2LogRestClient client;
    private final String namespaceName;

    private final String parentKey;
    String status;
    public String getStatus() {
        return this.status;
    }
    String nextPageToken;
    public String getNextPageToken() {
        return this.nextPageToken;
    }
    Long totalCount;
    public Long getTotalCount() {
        return this.totalCount;
    }
    Long scanSize;
    public Long getScanSize() {
        return this.scanSize;
    }
    public String getNamespaceName() {
        return namespaceName;
    }

    public NamespaceDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2LogRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.parentKey = "log:Namespace";
    }

    public NamespaceDomain getStatus(
        GetNamespaceStatusRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName);
        GetNamespaceStatusResult result = this.client.getNamespaceStatus(
            request
        );
        NamespaceDomain domain = this;
        this.status = result.getStatus();
        return domain;
    }

    private Namespace get(
        GetNamespaceRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName);
        GetNamespaceResult result = this.client.getNamespace(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.log.domain.model.NamespaceDomain.createCacheKey(
                    request.getNamespaceName() != null ? request.getNamespaceName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public NamespaceDomain update(
        UpdateNamespaceRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName);
        UpdateNamespaceResult result = this.client.updateNamespace(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.log.domain.model.NamespaceDomain.createCacheKey(
                    request.getNamespaceName() != null ? request.getNamespaceName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        NamespaceDomain domain = this;

        return domain;
    }

    public NamespaceDomain delete(
        DeleteNamespaceRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName);
        DeleteNamespaceResult result = null;
        try {
            result = this.client.deleteNamespace(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.log.domain.model.NamespaceDomain.createCacheKey(
                request.getNamespaceName() != null ? request.getNamespaceName().toString() : null
            ),
            Namespace.class
        );
        NamespaceDomain domain = this;

        return domain;
    }

    public NamespaceDomain putLog(
        PutLogRequest request
    ) {
        PutLogResult result = this.client.putLog(
            request
        );
        NamespaceDomain domain = this;
        return domain;
    }

    public LogDomain log(
    ) {
        return new LogDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName
        );
    }

    public QueryAccessLogIterator accessLog(
        String service,
        String method,
        String userId,
        Long begin,
        Long end,
        Boolean longTerm
    )
    {
        return new QueryAccessLogIterator(
            this.cache,
            this.client,
            this.namespaceName,
            service,
            method,
            userId,
            begin,
            end,
            longTerm
        );
    }

    public CountAccessLogIterator countAccessLog(
        Boolean service,
        Boolean method,
        Boolean userId,
        Long begin,
        Long end,
        Boolean longTerm
    )
    {
        return new CountAccessLogIterator(
            this.cache,
            this.client,
            this.namespaceName,
            service,
            method,
            userId,
            begin,
            end,
            longTerm
        );
    }

    public AccessLogDomain accessLog(
    ) {
        return new AccessLogDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName
        );
    }

    public AccessLogCountDomain accessLogCount(
    ) {
        return new AccessLogCountDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName
        );
    }

    public QueryExecuteStampSheetLogIterator executeStampSheetLog(
        String service,
        String method,
        String userId,
        String action,
        Long begin,
        Long end,
        Boolean longTerm
    )
    {
        return new QueryExecuteStampSheetLogIterator(
            this.cache,
            this.client,
            this.namespaceName,
            service,
            method,
            userId,
            action,
            begin,
            end,
            longTerm
        );
    }

    public CountExecuteStampSheetLogIterator countExecuteStampSheetLog(
        Boolean service,
        Boolean method,
        Boolean userId,
        Boolean action,
        Long begin,
        Long end,
        Boolean longTerm
    )
    {
        return new CountExecuteStampSheetLogIterator(
            this.cache,
            this.client,
            this.namespaceName,
            service,
            method,
            userId,
            action,
            begin,
            end,
            longTerm
        );
    }

    public ExecuteStampSheetLogDomain executeStampSheetLog(
    ) {
        return new ExecuteStampSheetLogDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName
        );
    }

    public ExecuteStampSheetLogCountDomain executeStampSheetLogCount(
    ) {
        return new ExecuteStampSheetLogCountDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName
        );
    }

    public QueryExecuteStampTaskLogIterator executeStampTaskLog(
        String service,
        String method,
        String userId,
        String action,
        Long begin,
        Long end,
        Boolean longTerm
    )
    {
        return new QueryExecuteStampTaskLogIterator(
            this.cache,
            this.client,
            this.namespaceName,
            service,
            method,
            userId,
            action,
            begin,
            end,
            longTerm
        );
    }

    public CountExecuteStampTaskLogIterator countExecuteStampTaskLog(
        Boolean service,
        Boolean method,
        Boolean userId,
        Boolean action,
        Long begin,
        Long end,
        Boolean longTerm
    )
    {
        return new CountExecuteStampTaskLogIterator(
            this.cache,
            this.client,
            this.namespaceName,
            service,
            method,
            userId,
            action,
            begin,
            end,
            longTerm
        );
    }

    public ExecuteStampTaskLogDomain executeStampTaskLog(
    ) {
        return new ExecuteStampTaskLogDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName
        );
    }

    public ExecuteStampTaskLogCountDomain executeStampTaskLogCount(
    ) {
        return new ExecuteStampTaskLogCountDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName
        );
    }

    public QueryIssueStampSheetLogIterator issueStampSheetLog(
        String service,
        String method,
        String userId,
        String action,
        Long begin,
        Long end,
        Boolean longTerm
    )
    {
        return new QueryIssueStampSheetLogIterator(
            this.cache,
            this.client,
            this.namespaceName,
            service,
            method,
            userId,
            action,
            begin,
            end,
            longTerm
        );
    }

    public CountIssueStampSheetLogIterator countIssueStampSheetLog(
        Boolean service,
        Boolean method,
        Boolean userId,
        Boolean action,
        Long begin,
        Long end,
        Boolean longTerm
    )
    {
        return new CountIssueStampSheetLogIterator(
            this.cache,
            this.client,
            this.namespaceName,
            service,
            method,
            userId,
            action,
            begin,
            end,
            longTerm
        );
    }

    public IssueStampSheetLogDomain issueStampSheetLog(
    ) {
        return new IssueStampSheetLogDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName
        );
    }

    public IssueStampSheetLogCountDomain issueStampSheetLogCount(
    ) {
        return new IssueStampSheetLogCountDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName
        );
    }

    public static String createCacheParentKey(
        String namespaceName,
        String childType
    )
    {
        return String.join(
            ":",
            "log",
            namespaceName,
            childType
        );
    }

    public static String createCacheKey(
        String namespaceName
    )
    {
        return String.join(
            ":",
            namespaceName
        );
    }

    public Namespace model() {
        Namespace value = cache.get(
            parentKey,
            io.gs2.log.domain.model.NamespaceDomain.createCacheKey(
                this.getNamespaceName() != null ? this.getNamespaceName().toString() : null
            ),
            Namespace.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetNamespaceRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.log.domain.model.NamespaceDomain.createCacheKey(
                        this.getNamespaceName() != null ? this.getNamespaceName().toString() : null
                    ),
                    Namespace.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.log.domain.model.NamespaceDomain.createCacheKey(
                this.getNamespaceName() != null ? this.getNamespaceName().toString() : null
            ),
            Namespace.class
        );
        }
        return value;
    }

}

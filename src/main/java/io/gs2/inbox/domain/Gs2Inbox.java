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

package io.gs2.inbox.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.inbox.Gs2InboxRestClient;
import io.gs2.inbox.domain.iterator.*;
import io.gs2.inbox.domain.model.*;
import io.gs2.inbox.model.*;
import io.gs2.inbox.request.*;
import io.gs2.inbox.result.*;

import java.util.List;

public class Gs2Inbox {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2InboxRestClient client;

    private final String parentKey;

    public Gs2Inbox(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2InboxRestClient(
            session
        );
        this.parentKey = "inbox";
    }

    public NamespaceDomain createNamespace(
        CreateNamespaceRequest request
    ) {
        CreateNamespaceResult result = this.client.createNamespace(
            request
        );
        String parentKey = "inbox:Namespace";
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.inbox.domain.model.NamespaceDomain.createCacheKey(
                    result.getItem().getName() != null ? result.getItem().getName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        NamespaceDomain domain = new NamespaceDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            result.getItem().getName()
        );
        return domain;
    }

    public DescribeNamespacesIterator namespaces(
    )
    {
        return new DescribeNamespacesIterator(
            this.cache,
            this.client
        );
    }

    public NamespaceDomain namespace(
        String namespaceName
    ) {
        return new NamespaceDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            namespaceName
        );
    }

    public static void updateCacheFromStampSheet(
            CacheDatabase cache,
            String method,
            String request,
            String result
    ) {
        try {
            switch (method) {
                case "SendMessageByUserId": {
                    SendMessageByUserIdRequest requestModel = SendMessageByUserIdRequest.fromJson(new ObjectMapper().readTree(request));
                    SendMessageByUserIdResult resultModel = SendMessageByUserIdResult.fromJson(new ObjectMapper().readTree(result));
                    String parentKey = UserDomain.createCacheParentKey(
                        requestModel.getNamespaceName().toString(),
                        resultModel.getItem().getUserId().toString(),
                        "Message"
                    );
                    String key = MessageDomain.createCacheKey(
                        resultModel.getItem().getName().toString()
                    );
                    cache.put(
                        parentKey,
                        key,
                        resultModel.getItem(),
                        resultModel.getItem().getExpiresAt() == null ? System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes : resultModel.getItem().getExpiresAt()
                    );
                    break;
                }
            }
        } catch (JsonProcessingException e) {
        }
    }

    public static void updateCacheFromStampTask(
            CacheDatabase cache,
            String method,
            String request,
            String result
    ) {
        try {
            switch (method) {
                case "OpenMessageByUserId": {
                    OpenMessageByUserIdRequest requestModel = OpenMessageByUserIdRequest.fromJson(new ObjectMapper().readTree(request));
                    OpenMessageByUserIdResult resultModel = OpenMessageByUserIdResult.fromJson(new ObjectMapper().readTree(result));
                    String parentKey = UserDomain.createCacheParentKey(
                        requestModel.getNamespaceName().toString(),
                        resultModel.getItem().getUserId().toString(),
                        "Message"
                    );
                    String key = MessageDomain.createCacheKey(
                        resultModel.getItem().getName().toString()
                    );
                    cache.put(
                        parentKey,
                        key,
                        resultModel.getItem(),
                        resultModel.getItem().getExpiresAt() == null ? System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes : resultModel.getItem().getExpiresAt()
                    );
                    break;
                }
            }
        } catch (JsonProcessingException e) {
        }
    }

    public static void updateCacheFromJobResult(
            CacheDatabase cache,
            String method,
            io.gs2.jobQueue.model.Job job,
            io.gs2.jobQueue.model.JobResultBody result
    ) {
        try {
            switch (method) {
                case "send_message_by_user_id": {
                    SendMessageByUserIdRequest requestModel = SendMessageByUserIdRequest.fromJson(new ObjectMapper().readTree(job.getArgs()));
                    SendMessageByUserIdResult resultModel = SendMessageByUserIdResult.fromJson(new ObjectMapper().readTree(result.getResult()));
                    String parentKey = UserDomain.createCacheParentKey(
                        requestModel.getNamespaceName().toString(),
                        resultModel.getItem().getUserId().toString(),
                        "Message"
                    );
                    String key = MessageDomain.createCacheKey(
                        resultModel.getItem().getName().toString()
                    );
                    cache.put(
                        parentKey,
                        key,
                        resultModel.getItem(),
                          resultModel.getItem().getExpiresAt() == null ? System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes : resultModel.getItem().getExpiresAt()
                    );
                    break;
                }
            }
        } catch (JsonProcessingException e) {
        }
    }
}


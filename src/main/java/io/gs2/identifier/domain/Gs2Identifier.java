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

package io.gs2.identifier.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.identifier.Gs2IdentifierRestClient;
import io.gs2.identifier.domain.iterator.*;
import io.gs2.identifier.domain.model.*;
import io.gs2.identifier.model.*;
import io.gs2.identifier.request.*;
import io.gs2.identifier.result.*;

import java.util.List;

public class Gs2Identifier {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2IdentifierRestClient client;

    private final String parentKey;

    public Gs2Identifier(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2IdentifierRestClient(
            session
        );
        this.parentKey = "identifier";
    }

    public UserDomain createUser(
        CreateUserRequest request
    ) {
        CreateUserResult result = this.client.createUser(
            request
        );
        String parentKey = "identifier:User";
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.identifier.domain.model.UserDomain.createCacheKey(
                    result.getItem().getName() != null ? result.getItem().getName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        UserDomain domain = new UserDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            result.getItem().getName()
        );
        return domain;
    }

    public SecurityPolicyDomain createSecurityPolicy(
        CreateSecurityPolicyRequest request
    ) {
        CreateSecurityPolicyResult result = this.client.createSecurityPolicy(
            request
        );
        String parentKey = "identifier:SecurityPolicy";
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.identifier.domain.model.SecurityPolicyDomain.createCacheKey(
                    result.getItem().getName() != null ? result.getItem().getName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        SecurityPolicyDomain domain = new SecurityPolicyDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            result.getItem().getName()
        );
        return domain;
    }

    public DescribeUsersIterator users(
    )
    {
        return new DescribeUsersIterator(
            this.cache,
            this.client
        );
    }

    public UserDomain user(
        String userName
    ) {
        return new UserDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            userName
        );
    }

    public DescribeSecurityPoliciesIterator securityPolicies(
    )
    {
        return new DescribeSecurityPoliciesIterator(
            this.cache,
            this.client
        );
    }

    public DescribeCommonSecurityPoliciesIterator commonSecurityPolicies(
    )
    {
        return new DescribeCommonSecurityPoliciesIterator(
            this.cache,
            this.client
        );
    }

    public SecurityPolicyDomain securityPolicy(
        String securityPolicyName
    ) {
        return new SecurityPolicyDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            securityPolicyName
        );
    }

    public static void updateCacheFromStampSheet(
            CacheDatabase cache,
            String method,
            String request,
            String result
    ) {
    }

    public static void updateCacheFromStampTask(
            CacheDatabase cache,
            String method,
            String request,
            String result
    ) {
    }

    public static void updateCacheFromJobResult(
            CacheDatabase cache,
            String method,
            io.gs2.jobQueue.model.Job job,
            io.gs2.jobQueue.model.JobResultBody result
    ) {
    }
}


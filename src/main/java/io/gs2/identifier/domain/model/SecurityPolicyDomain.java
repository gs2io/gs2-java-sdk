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
package io.gs2.identifier.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.identifier.Gs2IdentifierRestClient;
import io.gs2.identifier.domain.iterator.*;
import io.gs2.identifier.model.*;
import io.gs2.identifier.request.*;
import io.gs2.identifier.result.*;

import java.util.List;


public class SecurityPolicyDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2IdentifierRestClient client;
    private final String securityPolicyName;

    private final String parentKey;
    public String getSecurityPolicyName() {
        return securityPolicyName;
    }

    public SecurityPolicyDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String securityPolicyName
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2IdentifierRestClient(
            session
        );
        this.securityPolicyName = securityPolicyName;
        this.parentKey = "identifier:SecurityPolicy";
    }

    public SecurityPolicyDomain update(
        UpdateSecurityPolicyRequest request
    ) {
        request
            .withSecurityPolicyName(this.securityPolicyName);
        UpdateSecurityPolicyResult result = this.client.updateSecurityPolicy(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.identifier.domain.model.SecurityPolicyDomain.createCacheKey(
                    request.getSecurityPolicyName() != null ? request.getSecurityPolicyName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        SecurityPolicyDomain domain = this;

        return domain;
    }

    private SecurityPolicy get(
        GetSecurityPolicyRequest request
    ) {
        request
            .withSecurityPolicyName(this.securityPolicyName);
        GetSecurityPolicyResult result = this.client.getSecurityPolicy(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.identifier.domain.model.SecurityPolicyDomain.createCacheKey(
                    request.getSecurityPolicyName() != null ? request.getSecurityPolicyName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public SecurityPolicyDomain delete(
        DeleteSecurityPolicyRequest request
    ) {
        request
            .withSecurityPolicyName(this.securityPolicyName);
        DeleteSecurityPolicyResult result = null;
        try {
            result = this.client.deleteSecurityPolicy(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.identifier.domain.model.SecurityPolicyDomain.createCacheKey(
                request.getSecurityPolicyName() != null ? request.getSecurityPolicyName().toString() : null
            ),
            SecurityPolicy.class
        );
        SecurityPolicyDomain domain = this;

        return domain;
    }

    public static String createCacheParentKey(
        String securityPolicyName,
        String childType
    )
    {
        return String.join(
            ":",
            "identifier",
            securityPolicyName,
            childType
        );
    }

    public static String createCacheKey(
        String securityPolicyName
    )
    {
        return String.join(
            ":",
            securityPolicyName
        );
    }

    public SecurityPolicy model() {
        SecurityPolicy value = cache.get(
            parentKey,
            io.gs2.identifier.domain.model.SecurityPolicyDomain.createCacheKey(
                this.getSecurityPolicyName() != null ? this.getSecurityPolicyName().toString() : null
            ),
            SecurityPolicy.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetSecurityPolicyRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.identifier.domain.model.SecurityPolicyDomain.createCacheKey(
                        this.getSecurityPolicyName() != null ? this.getSecurityPolicyName().toString() : null
                    ),
                    SecurityPolicy.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.identifier.domain.model.SecurityPolicyDomain.createCacheKey(
                this.getSecurityPolicyName() != null ? this.getSecurityPolicyName().toString() : null
            ),
            SecurityPolicy.class
        );
        }
        return value;
    }

}

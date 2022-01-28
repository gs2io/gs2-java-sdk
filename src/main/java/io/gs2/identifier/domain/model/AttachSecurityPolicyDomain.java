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


public class AttachSecurityPolicyDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2IdentifierRestClient client;
    private final String userName;

    private final String parentKey;
    public String getUserName() {
        return userName;
    }

    public AttachSecurityPolicyDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String userName
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2IdentifierRestClient(
            session
        );
        this.userName = userName;
        this.parentKey = io.gs2.identifier.domain.model.UserDomain.createCacheParentKey(
            this.userName != null ? this.userName.toString() : null,
            "AttachSecurityPolicy"
        );
    }

    public SecurityPolicyDomain[] getHasSecurityPolicy(
        GetHasSecurityPolicyRequest request
    ) {
        request
            .withUserName(this.userName);
        GetHasSecurityPolicyResult result = this.client.getHasSecurityPolicy(
            request
        );
        String parentKey = "identifier:SecurityPolicy";
        for (SecurityPolicy item : result.getItems()) {
                
            if (item != null) {
                cache.put(
                    parentKey,
                    io.gs2.identifier.domain.model.SecurityPolicyDomain.createCacheKey(
                        item.getName() != null ? item.getName().toString() : null
                    ),
                    item,
                    System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                );
            }
        }
        SecurityPolicyDomain[] domain = new SecurityPolicyDomain[result.getItems().size()];
        for (int i=0; i<result.getItems().size(); i++)
        {
            domain[i] = new SecurityPolicyDomain(
                this.cache,
                this.jobQueueDomain,
                this.stampSheetConfiguration,
                this.session,
                result.getItems().get(i).getName()
            );
        }
        return domain;
    }

    public SecurityPolicyDomain[] attachSecurityPolicy(
        AttachSecurityPolicyRequest request
    ) {
        request
            .withUserName(this.userName);
        AttachSecurityPolicyResult result = this.client.attachSecurityPolicy(
            request
        );
        String parentKey = "identifier:SecurityPolicy";
        for (SecurityPolicy item : result.getItems()) {
                
            if (item != null) {
                cache.put(
                    parentKey,
                    io.gs2.identifier.domain.model.SecurityPolicyDomain.createCacheKey(
                        item.getName() != null ? item.getName().toString() : null
                    ),
                    item,
                    System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                );
            }
        }
        SecurityPolicyDomain[] domain = new SecurityPolicyDomain[result.getItems().size()];
        for (int i=0; i<result.getItems().size(); i++)
        {
            domain[i] = new SecurityPolicyDomain(
                this.cache,
                this.jobQueueDomain,
                this.stampSheetConfiguration,
                this.session,
                result.getItems().get(i).getName()
            );
        }
        return domain;
    }

    public SecurityPolicyDomain[] detachSecurityPolicy(
        DetachSecurityPolicyRequest request
    ) {
        request
            .withUserName(this.userName);
        DetachSecurityPolicyResult result = this.client.detachSecurityPolicy(
            request
        );
        String parentKey = "identifier:SecurityPolicy";
        for (SecurityPolicy item : result.getItems()) {
                
            if (item != null) {
                cache.put(
                    parentKey,
                    io.gs2.identifier.domain.model.SecurityPolicyDomain.createCacheKey(
                        item.getName() != null ? item.getName().toString() : null
                    ),
                    item,
                    System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                );
            }
        }
        SecurityPolicyDomain[] domain = new SecurityPolicyDomain[result.getItems().size()];
        for (int i=0; i<result.getItems().size(); i++)
        {
            domain[i] = new SecurityPolicyDomain(
                this.cache,
                this.jobQueueDomain,
                this.stampSheetConfiguration,
                this.session,
                result.getItems().get(i).getName()
            );
        }
        return domain;
    }

    public static String createCacheParentKey(
        String userName,
        String childType
    )
    {
        return String.join(
            ":",
            "identifier",
            userName,
            childType
        );
    }

    public static String createCacheKey(
        String userName
    )
    {
        return String.join(
            ":",
            userName
        );
    }

    public AttachSecurityPolicy model() {
        AttachSecurityPolicy value = cache.get(
            parentKey,
            io.gs2.identifier.domain.model.AttachSecurityPolicyDomain.createCacheKey(
                this.getUserName() != null ? this.getUserName().toString() : null
            ),
            AttachSecurityPolicy.class
        );
        return value;
    }

}

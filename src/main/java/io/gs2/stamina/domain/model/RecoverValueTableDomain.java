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
package io.gs2.stamina.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.stamina.Gs2StaminaRestClient;
import io.gs2.stamina.domain.iterator.*;
import io.gs2.stamina.model.*;
import io.gs2.stamina.request.*;
import io.gs2.stamina.result.*;

import java.util.List;


public class RecoverValueTableDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2StaminaRestClient client;
    private final String namespaceName;
    private final String recoverValueTableName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getRecoverValueTableName() {
        return recoverValueTableName;
    }

    public RecoverValueTableDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String recoverValueTableName
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2StaminaRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.recoverValueTableName = recoverValueTableName;
        this.parentKey = io.gs2.stamina.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "RecoverValueTable"
        );
    }

    public static String createCacheParentKey(
        String namespaceName,
        String recoverValueTableName,
        String childType
    )
    {
        return String.join(
            ":",
            "stamina",
            namespaceName,
            recoverValueTableName,
            childType
        );
    }

    public static String createCacheKey(
        String recoverValueTableName
    )
    {
        return String.join(
            ":",
            recoverValueTableName
        );
    }

    public RecoverValueTable model() {
        RecoverValueTable value = cache.get(
            parentKey,
            io.gs2.stamina.domain.model.RecoverValueTableDomain.createCacheKey(
                this.getRecoverValueTableName() != null ? this.getRecoverValueTableName().toString() : null
            ),
            RecoverValueTable.class
        );
        return value;
    }

}

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
package io.gs2.money.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.money.Gs2MoneyRestClient;
import io.gs2.money.domain.iterator.*;
import io.gs2.money.model.*;
import io.gs2.money.request.*;
import io.gs2.money.result.*;

import java.util.List;


public class ReceiptAccessTokenDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2MoneyRestClient client;
    private final String namespaceName;
    private final AccessToken accessToken;
    private final String transactionId;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return accessToken.getUserId();
    }
    public String getTransactionId() {
        return transactionId;
    }

    public ReceiptAccessTokenDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        AccessToken accessToken,
        String transactionId
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2MoneyRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.accessToken = accessToken;
        this.transactionId = transactionId;
        this.parentKey = io.gs2.money.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.accessToken != null && this.accessToken.getUserId() != null ? this.accessToken.getUserId().toString() : null,
            "Receipt"
        );
    }

    public static String createCacheParentKey(
        String namespaceName,
        String userId,
        String transactionId,
        String childType
    )
    {
        return String.join(
            ":",
            "money",
            namespaceName,
            userId,
            transactionId,
            childType
        );
    }

    public static String createCacheKey(
        String transactionId
    )
    {
        return String.join(
            ":",
            transactionId
        );
    }

    public Receipt model() {
        Receipt value = cache.get(
            parentKey,
            io.gs2.money.domain.model.ReceiptDomain.createCacheKey(
                this.getTransactionId() != null ? this.getTransactionId().toString() : null
            ),
            Receipt.class
        );
        return value;
    }

}

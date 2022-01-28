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
package io.gs2.showcase.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.showcase.Gs2ShowcaseRestClient;
import io.gs2.showcase.domain.iterator.*;
import io.gs2.showcase.model.*;
import io.gs2.showcase.request.*;
import io.gs2.showcase.result.*;

import java.util.List;


public class ShowcaseDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2ShowcaseRestClient client;
    private final String namespaceName;
    private final String userId;
    private final String showcaseName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return userId;
    }
    public String getShowcaseName() {
        return showcaseName;
    }

    public ShowcaseDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String userId,
        String showcaseName
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2ShowcaseRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.userId = userId;
        this.showcaseName = showcaseName;
        this.parentKey = io.gs2.showcase.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "Showcase"
        );
    }

    private Showcase get(
        GetShowcaseByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withShowcaseName(this.showcaseName);
        GetShowcaseByUserIdResult result = this.client.getShowcaseByUserId(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.showcase.domain.model.ShowcaseDomain.createCacheKey(
                    request.getShowcaseName() != null ? request.getShowcaseName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public io.gs2.core.domain.StampSheetDomain buy(
        BuyByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withShowcaseName(this.showcaseName);
        BuyByUserIdResult result = this.client.buyByUserId(
            request
        );
        String parentKey = io.gs2.showcase.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "SalesItem"
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.showcase.domain.model.SalesItemDomain.createCacheKey(
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        io.gs2.core.domain.StampSheetDomain stampSheet = new io.gs2.core.domain.StampSheetDomain(
                cache,
                jobQueueDomain,
                session,
                result.getStampSheet(),
                result.getStampSheetEncryptionKeyId(),
                stampSheetConfiguration.namespaceName,
                stampSheetConfiguration.stampTaskEventHandler,
                stampSheetConfiguration.stampSheetEventHandler
        );
        try {
            stampSheet.run();
        } catch (io.gs2.core.exception.Gs2Exception e) {
            throw new io.gs2.core.exception.TransactionException(stampSheet, e);
        }
        return stampSheet;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String userId,
        String showcaseName,
        String childType
    )
    {
        return String.join(
            ":",
            "showcase",
            namespaceName,
            userId,
            showcaseName,
            childType
        );
    }

    public static String createCacheKey(
        String showcaseName
    )
    {
        return String.join(
            ":",
            showcaseName
        );
    }

    public Showcase model() {
        Showcase value = cache.get(
            parentKey,
            io.gs2.showcase.domain.model.ShowcaseDomain.createCacheKey(
                this.getShowcaseName() != null ? this.getShowcaseName().toString() : null
            ),
            Showcase.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetShowcaseByUserIdRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.showcase.domain.model.ShowcaseDomain.createCacheKey(
                        this.getShowcaseName() != null ? this.getShowcaseName().toString() : null
                    ),
                    Showcase.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.showcase.domain.model.ShowcaseDomain.createCacheKey(
                this.getShowcaseName() != null ? this.getShowcaseName().toString() : null
            ),
            Showcase.class
        );
        }
        return value;
    }

}

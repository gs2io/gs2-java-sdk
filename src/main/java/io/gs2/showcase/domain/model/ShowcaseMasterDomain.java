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


public class ShowcaseMasterDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2ShowcaseRestClient client;
    private final String namespaceName;
    private final String showcaseName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getShowcaseName() {
        return showcaseName;
    }

    public ShowcaseMasterDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
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
        this.showcaseName = showcaseName;
        this.parentKey = io.gs2.showcase.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "ShowcaseMaster"
        );
    }

    private ShowcaseMaster get(
        GetShowcaseMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withShowcaseName(this.showcaseName);
        GetShowcaseMasterResult result = this.client.getShowcaseMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.showcase.domain.model.ShowcaseMasterDomain.createCacheKey(
                    request.getShowcaseName() != null ? request.getShowcaseName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public ShowcaseMasterDomain update(
        UpdateShowcaseMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withShowcaseName(this.showcaseName);
        UpdateShowcaseMasterResult result = this.client.updateShowcaseMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.showcase.domain.model.ShowcaseMasterDomain.createCacheKey(
                    request.getShowcaseName() != null ? request.getShowcaseName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        ShowcaseMasterDomain domain = this;

        return domain;
    }

    public ShowcaseMasterDomain delete(
        DeleteShowcaseMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withShowcaseName(this.showcaseName);
        DeleteShowcaseMasterResult result = null;
        try {
            result = this.client.deleteShowcaseMaster(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.showcase.domain.model.ShowcaseMasterDomain.createCacheKey(
                request.getShowcaseName() != null ? request.getShowcaseName().toString() : null
            ),
            ShowcaseMaster.class
        );
        ShowcaseMasterDomain domain = this;

        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String showcaseName,
        String childType
    )
    {
        return String.join(
            ":",
            "showcase",
            namespaceName,
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

    public ShowcaseMaster model() {
        ShowcaseMaster value = cache.get(
            parentKey,
            io.gs2.showcase.domain.model.ShowcaseMasterDomain.createCacheKey(
                this.getShowcaseName() != null ? this.getShowcaseName().toString() : null
            ),
            ShowcaseMaster.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetShowcaseMasterRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.showcase.domain.model.ShowcaseMasterDomain.createCacheKey(
                        this.getShowcaseName() != null ? this.getShowcaseName().toString() : null
                    ),
                    ShowcaseMaster.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.showcase.domain.model.ShowcaseMasterDomain.createCacheKey(
                this.getShowcaseName() != null ? this.getShowcaseName().toString() : null
            ),
            ShowcaseMaster.class
        );
        }
        return value;
    }

}

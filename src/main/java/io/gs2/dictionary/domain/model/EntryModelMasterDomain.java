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
package io.gs2.dictionary.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.dictionary.Gs2DictionaryRestClient;
import io.gs2.dictionary.domain.iterator.*;
import io.gs2.dictionary.model.*;
import io.gs2.dictionary.request.*;
import io.gs2.dictionary.result.*;

import java.util.List;


public class EntryModelMasterDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2DictionaryRestClient client;
    private final String namespaceName;
    private final String entryName;

    private final String parentKey;
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getEntryName() {
        return entryName;
    }

    public EntryModelMasterDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String entryName
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2DictionaryRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.entryName = entryName;
        this.parentKey = io.gs2.dictionary.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "EntryModelMaster"
        );
    }

    private EntryModelMaster get(
        GetEntryModelMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withEntryName(this.entryName);
        GetEntryModelMasterResult result = this.client.getEntryModelMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.dictionary.domain.model.EntryModelMasterDomain.createCacheKey(
                    request.getEntryName() != null ? request.getEntryName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public EntryModelMasterDomain update(
        UpdateEntryModelMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withEntryName(this.entryName);
        UpdateEntryModelMasterResult result = this.client.updateEntryModelMaster(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.dictionary.domain.model.EntryModelMasterDomain.createCacheKey(
                    request.getEntryName() != null ? request.getEntryName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        EntryModelMasterDomain domain = this;

        return domain;
    }

    public EntryModelMasterDomain delete(
        DeleteEntryModelMasterRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withEntryName(this.entryName);
        DeleteEntryModelMasterResult result = null;
        try {
            result = this.client.deleteEntryModelMaster(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.dictionary.domain.model.EntryModelMasterDomain.createCacheKey(
                request.getEntryName() != null ? request.getEntryName().toString() : null
            ),
            EntryModelMaster.class
        );
        EntryModelMasterDomain domain = this;

        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String entryName,
        String childType
    )
    {
        return String.join(
            ":",
            "dictionary",
            namespaceName,
            entryName,
            childType
        );
    }

    public static String createCacheKey(
        String entryName
    )
    {
        return String.join(
            ":",
            entryName
        );
    }

    public EntryModelMaster model() {
        EntryModelMaster value = cache.get(
            parentKey,
            io.gs2.dictionary.domain.model.EntryModelMasterDomain.createCacheKey(
                this.getEntryName() != null ? this.getEntryName().toString() : null
            ),
            EntryModelMaster.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetEntryModelMasterRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.dictionary.domain.model.EntryModelMasterDomain.createCacheKey(
                        this.getEntryName() != null ? this.getEntryName().toString() : null
                    ),
                    EntryModelMaster.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.dictionary.domain.model.EntryModelMasterDomain.createCacheKey(
                this.getEntryName() != null ? this.getEntryName().toString() : null
            ),
            EntryModelMaster.class
        );
        }
        return value;
    }

}

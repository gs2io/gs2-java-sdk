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


public class EntryDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2DictionaryRestClient client;
    private final String namespaceName;
    private final String userId;
    private final String entryModelName;

    private final String parentKey;
    String body;
    public String getBody() {
        return this.body;
    }
    String signature;
    public String getSignature() {
        return this.signature;
    }
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return userId;
    }
    public String getEntryModelName() {
        return entryModelName;
    }

    public EntryDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String userId,
        String entryModelName
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2DictionaryRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.userId = userId;
        this.entryModelName = entryModelName;
        this.parentKey = io.gs2.dictionary.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "Entry"
        );
    }

    private Entry get(
        GetEntryByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withEntryModelName(this.entryModelName);
        GetEntryByUserIdResult result = this.client.getEntryByUserId(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.dictionary.domain.model.EntryDomain.createCacheKey(
                    request.getEntryModelName() != null ? request.getEntryModelName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public EntryDomain getWithSignature(
        GetEntryWithSignatureByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withEntryModelName(this.entryModelName);
        GetEntryWithSignatureByUserIdResult result = this.client.getEntryWithSignatureByUserId(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.dictionary.domain.model.EntryDomain.createCacheKey(
                    request.getEntryModelName() != null ? request.getEntryModelName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        EntryDomain domain = this;
        domain.body = result.getBody();
        domain.signature = result.getSignature();

        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String userId,
        String entryModelName,
        String childType
    )
    {
        return String.join(
            ":",
            "dictionary",
            namespaceName,
            userId,
            entryModelName,
            childType
        );
    }

    public static String createCacheKey(
        String entryModelName
    )
    {
        return String.join(
            ":",
            entryModelName
        );
    }

    public Entry model() {
        Entry value = cache.get(
            parentKey,
            io.gs2.dictionary.domain.model.EntryDomain.createCacheKey(
                this.getEntryModelName() != null ? this.getEntryModelName().toString() : null
            ),
            Entry.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetEntryByUserIdRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.dictionary.domain.model.EntryDomain.createCacheKey(
                        this.getEntryModelName() != null ? this.getEntryModelName().toString() : null
                    ),
                    Entry.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.dictionary.domain.model.EntryDomain.createCacheKey(
                this.getEntryModelName() != null ? this.getEntryModelName().toString() : null
            ),
            Entry.class
        );
        }
        return value;
    }

}

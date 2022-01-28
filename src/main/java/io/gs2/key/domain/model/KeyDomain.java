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
package io.gs2.key.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.key.Gs2KeyRestClient;
import io.gs2.key.domain.iterator.*;
import io.gs2.key.model.*;
import io.gs2.key.request.*;
import io.gs2.key.result.*;

import java.util.List;


public class KeyDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2KeyRestClient client;
    private final String namespaceName;
    private final String keyName;

    private final String parentKey;
    String data;
    public String getData() {
        return this.data;
    }
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getKeyName() {
        return keyName;
    }

    public KeyDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String keyName
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2KeyRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.keyName = keyName;
        this.parentKey = io.gs2.key.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "Key"
        );
    }

    public KeyDomain update(
        UpdateKeyRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withKeyName(this.keyName);
        UpdateKeyResult result = this.client.updateKey(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.key.domain.model.KeyDomain.createCacheKey(
                    request.getKeyName() != null ? request.getKeyName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        KeyDomain domain = this;

        return domain;
    }

    private Key get(
        GetKeyRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withKeyName(this.keyName);
        GetKeyResult result = this.client.getKey(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.key.domain.model.KeyDomain.createCacheKey(
                    request.getKeyName() != null ? request.getKeyName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public KeyDomain delete(
        DeleteKeyRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withKeyName(this.keyName);
        DeleteKeyResult result = null;
        try {
            result = this.client.deleteKey(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.key.domain.model.KeyDomain.createCacheKey(
                request.getKeyName() != null ? request.getKeyName().toString() : null
            ),
            Key.class
        );
        KeyDomain domain = this;

        return domain;
    }

    public KeyDomain encrypt(
        EncryptRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withKeyName(this.keyName);
        EncryptResult result = this.client.encrypt(
            request
        );
        KeyDomain domain = this;
        this.data = result.getData();
        return domain;
    }

    public KeyDomain decrypt(
        DecryptRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withKeyName(this.keyName);
        DecryptResult result = this.client.decrypt(
            request
        );
        KeyDomain domain = this;
        this.data = result.getData();
        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String keyName,
        String childType
    )
    {
        return String.join(
            ":",
            "key",
            namespaceName,
            keyName,
            childType
        );
    }

    public static String createCacheKey(
        String keyName
    )
    {
        return String.join(
            ":",
            keyName
        );
    }

    public Key model() {
        Key value = cache.get(
            parentKey,
            io.gs2.key.domain.model.KeyDomain.createCacheKey(
                this.getKeyName() != null ? this.getKeyName().toString() : null
            ),
            Key.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetKeyRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.key.domain.model.KeyDomain.createCacheKey(
                        this.getKeyName() != null ? this.getKeyName().toString() : null
                    ),
                    Key.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.key.domain.model.KeyDomain.createCacheKey(
                this.getKeyName() != null ? this.getKeyName().toString() : null
            ),
            Key.class
        );
        }
        return value;
    }

}

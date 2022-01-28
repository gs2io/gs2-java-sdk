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
package io.gs2.realtime.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.realtime.Gs2RealtimeRestClient;
import io.gs2.realtime.domain.iterator.*;
import io.gs2.realtime.model.*;
import io.gs2.realtime.request.*;
import io.gs2.realtime.result.*;

import java.util.List;


public class NamespaceDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2RealtimeRestClient client;
    private final String namespaceName;

    private final String parentKey;
    String status;
    public String getStatus() {
        return this.status;
    }
    String nextPageToken;
    public String getNextPageToken() {
        return this.nextPageToken;
    }
    public String getNamespaceName() {
        return namespaceName;
    }

    public NamespaceDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2RealtimeRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.parentKey = "realtime:Namespace";
    }

    public NamespaceDomain getStatus(
        GetNamespaceStatusRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName);
        GetNamespaceStatusResult result = this.client.getNamespaceStatus(
            request
        );
        NamespaceDomain domain = this;
        this.status = result.getStatus();
        return domain;
    }

    private Namespace get(
        GetNamespaceRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName);
        GetNamespaceResult result = this.client.getNamespace(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.realtime.domain.model.NamespaceDomain.createCacheKey(
                    request.getNamespaceName() != null ? request.getNamespaceName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public NamespaceDomain update(
        UpdateNamespaceRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName);
        UpdateNamespaceResult result = this.client.updateNamespace(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.realtime.domain.model.NamespaceDomain.createCacheKey(
                    request.getNamespaceName() != null ? request.getNamespaceName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        NamespaceDomain domain = this;

        return domain;
    }

    public NamespaceDomain delete(
        DeleteNamespaceRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName);
        DeleteNamespaceResult result = null;
        try {
            result = this.client.deleteNamespace(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.realtime.domain.model.NamespaceDomain.createCacheKey(
                request.getNamespaceName() != null ? request.getNamespaceName().toString() : null
            ),
            Namespace.class
        );
        NamespaceDomain domain = this;

        return domain;
    }

    public RoomDomain wantRoom(
        WantRoomRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName);
        WantRoomResult result = this.client.wantRoom(
            request
        );
        String parentKey = io.gs2.realtime.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "Room"
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.realtime.domain.model.RoomDomain.createCacheKey(
                    result.getItem().getName() != null ? result.getItem().getName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        RoomDomain domain = new RoomDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            request.getNamespaceName(),
            result.getItem().getName()
        );

        return domain;
    }

    public DescribeRoomsIterator rooms(
    )
    {
        return new DescribeRoomsIterator(
            this.cache,
            this.client,
            this.namespaceName
        );
    }

    public RoomDomain room(
        String roomName
    ) {
        return new RoomDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            roomName
        );
    }

    public static String createCacheParentKey(
        String namespaceName,
        String childType
    )
    {
        return String.join(
            ":",
            "realtime",
            namespaceName,
            childType
        );
    }

    public static String createCacheKey(
        String namespaceName
    )
    {
        return String.join(
            ":",
            namespaceName
        );
    }

    public Namespace model() {
        Namespace value = cache.get(
            parentKey,
            io.gs2.realtime.domain.model.NamespaceDomain.createCacheKey(
                this.getNamespaceName() != null ? this.getNamespaceName().toString() : null
            ),
            Namespace.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetNamespaceRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.realtime.domain.model.NamespaceDomain.createCacheKey(
                        this.getNamespaceName() != null ? this.getNamespaceName().toString() : null
                    ),
                    Namespace.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.realtime.domain.model.NamespaceDomain.createCacheKey(
                this.getNamespaceName() != null ? this.getNamespaceName().toString() : null
            ),
            Namespace.class
        );
        }
        return value;
    }

}

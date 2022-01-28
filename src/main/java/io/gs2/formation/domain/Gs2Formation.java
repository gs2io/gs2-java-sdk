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

package io.gs2.formation.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.formation.Gs2FormationRestClient;
import io.gs2.formation.domain.iterator.*;
import io.gs2.formation.domain.model.*;
import io.gs2.formation.model.*;
import io.gs2.formation.request.*;
import io.gs2.formation.result.*;

import java.util.List;

public class Gs2Formation {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2FormationRestClient client;

    private final String parentKey;

    public Gs2Formation(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2FormationRestClient(
            session
        );
        this.parentKey = "formation";
    }

    public NamespaceDomain createNamespace(
        CreateNamespaceRequest request
    ) {
        CreateNamespaceResult result = this.client.createNamespace(
            request
        );
        String parentKey = "formation:Namespace";
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.formation.domain.model.NamespaceDomain.createCacheKey(
                    result.getItem().getName() != null ? result.getItem().getName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        NamespaceDomain domain = new NamespaceDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            result.getItem().getName()
        );
        return domain;
    }

    public DescribeNamespacesIterator namespaces(
    )
    {
        return new DescribeNamespacesIterator(
            this.cache,
            this.client
        );
    }

    public NamespaceDomain namespace(
        String namespaceName
    ) {
        return new NamespaceDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            namespaceName
        );
    }

    public static void updateCacheFromStampSheet(
            CacheDatabase cache,
            String method,
            String request,
            String result
    ) {
        try {
            switch (method) {
                case "AddMoldCapacityByUserId": {
                    AddMoldCapacityByUserIdRequest requestModel = AddMoldCapacityByUserIdRequest.fromJson(new ObjectMapper().readTree(request));
                    AddMoldCapacityByUserIdResult resultModel = AddMoldCapacityByUserIdResult.fromJson(new ObjectMapper().readTree(result));
                    String parentKey = UserDomain.createCacheParentKey(
                        requestModel.getNamespaceName().toString(),
                        resultModel.getItem().getUserId().toString(),
                        "Mold"
                    );
                    String key = MoldDomain.createCacheKey(
                        resultModel.getItem().getName().toString()
                    );
                    cache.put(
                        parentKey,
                        key,
                        resultModel.getItem(),
                        System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                    );
                    break;
                }
                case "SetMoldCapacityByUserId": {
                    SetMoldCapacityByUserIdRequest requestModel = SetMoldCapacityByUserIdRequest.fromJson(new ObjectMapper().readTree(request));
                    SetMoldCapacityByUserIdResult resultModel = SetMoldCapacityByUserIdResult.fromJson(new ObjectMapper().readTree(result));
                    String parentKey = UserDomain.createCacheParentKey(
                        requestModel.getNamespaceName().toString(),
                        resultModel.getItem().getUserId().toString(),
                        "Mold"
                    );
                    String key = MoldDomain.createCacheKey(
                        resultModel.getItem().getName().toString()
                    );
                    cache.put(
                        parentKey,
                        key,
                        resultModel.getItem(),
                        System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                    );
                    break;
                }
                case "AcquireActionsToFormProperties": {
                    AcquireActionsToFormPropertiesRequest requestModel = AcquireActionsToFormPropertiesRequest.fromJson(new ObjectMapper().readTree(request));
                    AcquireActionsToFormPropertiesResult resultModel = AcquireActionsToFormPropertiesResult.fromJson(new ObjectMapper().readTree(result));
                    String parentKey = MoldDomain.createCacheParentKey(
                        requestModel.getNamespaceName().toString(),
                        requestModel.getUserId().toString(),
                        requestModel.getMoldName().toString(),
                        "Form"
                    );
                    String key = FormDomain.createCacheKey(
                        resultModel.getItem().getIndex().toString()
                    );
                    cache.put(
                        parentKey,
                        key,
                        resultModel.getItem(),
                        System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                    );
                    break;
                }
            }
        } catch (JsonProcessingException e) {
        }
    }

    public static void updateCacheFromStampTask(
            CacheDatabase cache,
            String method,
            String request,
            String result
    ) {
    }

    public static void updateCacheFromJobResult(
            CacheDatabase cache,
            String method,
            io.gs2.jobQueue.model.Job job,
            io.gs2.jobQueue.model.JobResultBody result
    ) {
        try {
            switch (method) {
                case "add_mold_capacity_by_user_id": {
                    AddMoldCapacityByUserIdRequest requestModel = AddMoldCapacityByUserIdRequest.fromJson(new ObjectMapper().readTree(job.getArgs()));
                    AddMoldCapacityByUserIdResult resultModel = AddMoldCapacityByUserIdResult.fromJson(new ObjectMapper().readTree(result.getResult()));
                    String parentKey = UserDomain.createCacheParentKey(
                        requestModel.getNamespaceName().toString(),
                        resultModel.getItem().getUserId().toString(),
                        "Mold"
                    );
                    String key = MoldDomain.createCacheKey(
                        resultModel.getItem().getName().toString()
                    );
                    cache.put(
                        parentKey,
                        key,
                        resultModel.getItem(),
                          System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                    );
                    break;
                }
                case "set_mold_capacity_by_user_id": {
                    SetMoldCapacityByUserIdRequest requestModel = SetMoldCapacityByUserIdRequest.fromJson(new ObjectMapper().readTree(job.getArgs()));
                    SetMoldCapacityByUserIdResult resultModel = SetMoldCapacityByUserIdResult.fromJson(new ObjectMapper().readTree(result.getResult()));
                    String parentKey = UserDomain.createCacheParentKey(
                        requestModel.getNamespaceName().toString(),
                        resultModel.getItem().getUserId().toString(),
                        "Mold"
                    );
                    String key = MoldDomain.createCacheKey(
                        resultModel.getItem().getName().toString()
                    );
                    cache.put(
                        parentKey,
                        key,
                        resultModel.getItem(),
                          System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                    );
                    break;
                }
                case "acquire_actions_to_form_properties": {
                    AcquireActionsToFormPropertiesRequest requestModel = AcquireActionsToFormPropertiesRequest.fromJson(new ObjectMapper().readTree(job.getArgs()));
                    AcquireActionsToFormPropertiesResult resultModel = AcquireActionsToFormPropertiesResult.fromJson(new ObjectMapper().readTree(result.getResult()));
                    String parentKey = MoldDomain.createCacheParentKey(
                        requestModel.getNamespaceName().toString(),
                        requestModel.getUserId().toString(),
                        requestModel.getMoldName().toString(),
                        "Form"
                    );
                    String key = FormDomain.createCacheKey(
                        resultModel.getItem().getIndex().toString()
                    );
                    cache.put(
                        parentKey,
                        key,
                        resultModel.getItem(),
                          System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                    );
                    break;
                }
            }
        } catch (JsonProcessingException e) {
        }
    }
}


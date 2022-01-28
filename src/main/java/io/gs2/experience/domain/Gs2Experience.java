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

package io.gs2.experience.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.experience.Gs2ExperienceRestClient;
import io.gs2.experience.domain.iterator.*;
import io.gs2.experience.domain.model.*;
import io.gs2.experience.model.*;
import io.gs2.experience.request.*;
import io.gs2.experience.result.*;

import java.util.List;

public class Gs2Experience {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2ExperienceRestClient client;

    private final String parentKey;

    public Gs2Experience(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2ExperienceRestClient(
            session
        );
        this.parentKey = "experience";
    }

    public NamespaceDomain createNamespace(
        CreateNamespaceRequest request
    ) {
        CreateNamespaceResult result = this.client.createNamespace(
            request
        );
        String parentKey = "experience:Namespace";
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.experience.domain.model.NamespaceDomain.createCacheKey(
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
                case "AddExperienceByUserId": {
                    AddExperienceByUserIdRequest requestModel = AddExperienceByUserIdRequest.fromJson(new ObjectMapper().readTree(request));
                    AddExperienceByUserIdResult resultModel = AddExperienceByUserIdResult.fromJson(new ObjectMapper().readTree(result));
                    String parentKey = UserDomain.createCacheParentKey(
                        requestModel.getNamespaceName().toString(),
                        resultModel.getItem().getUserId().toString(),
                        "Status"
                    );
                    String key = StatusDomain.createCacheKey(
                        resultModel.getItem().getExperienceName().toString(),
                        resultModel.getItem().getPropertyId().toString()
                    );
                    cache.put(
                        parentKey,
                        key,
                        resultModel.getItem(),
                        System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                    );
                    break;
                }
                case "AddRankCapByUserId": {
                    AddRankCapByUserIdRequest requestModel = AddRankCapByUserIdRequest.fromJson(new ObjectMapper().readTree(request));
                    AddRankCapByUserIdResult resultModel = AddRankCapByUserIdResult.fromJson(new ObjectMapper().readTree(result));
                    String parentKey = UserDomain.createCacheParentKey(
                        requestModel.getNamespaceName().toString(),
                        resultModel.getItem().getUserId().toString(),
                        "Status"
                    );
                    String key = StatusDomain.createCacheKey(
                        resultModel.getItem().getExperienceName().toString(),
                        resultModel.getItem().getPropertyId().toString()
                    );
                    cache.put(
                        parentKey,
                        key,
                        resultModel.getItem(),
                        System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                    );
                    break;
                }
                case "SetRankCapByUserId": {
                    SetRankCapByUserIdRequest requestModel = SetRankCapByUserIdRequest.fromJson(new ObjectMapper().readTree(request));
                    SetRankCapByUserIdResult resultModel = SetRankCapByUserIdResult.fromJson(new ObjectMapper().readTree(result));
                    String parentKey = UserDomain.createCacheParentKey(
                        requestModel.getNamespaceName().toString(),
                        resultModel.getItem().getUserId().toString(),
                        "Status"
                    );
                    String key = StatusDomain.createCacheKey(
                        resultModel.getItem().getExperienceName().toString(),
                        resultModel.getItem().getPropertyId().toString()
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
                case "add_experience_by_user_id": {
                    AddExperienceByUserIdRequest requestModel = AddExperienceByUserIdRequest.fromJson(new ObjectMapper().readTree(job.getArgs()));
                    AddExperienceByUserIdResult resultModel = AddExperienceByUserIdResult.fromJson(new ObjectMapper().readTree(result.getResult()));
                    String parentKey = UserDomain.createCacheParentKey(
                        requestModel.getNamespaceName().toString(),
                        resultModel.getItem().getUserId().toString(),
                        "Status"
                    );
                    String key = StatusDomain.createCacheKey(
                        resultModel.getItem().getExperienceName().toString(),
                        resultModel.getItem().getPropertyId().toString()
                    );
                    cache.put(
                        parentKey,
                        key,
                        resultModel.getItem(),
                          System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                    );
                    break;
                }
                case "add_rank_cap_by_user_id": {
                    AddRankCapByUserIdRequest requestModel = AddRankCapByUserIdRequest.fromJson(new ObjectMapper().readTree(job.getArgs()));
                    AddRankCapByUserIdResult resultModel = AddRankCapByUserIdResult.fromJson(new ObjectMapper().readTree(result.getResult()));
                    String parentKey = UserDomain.createCacheParentKey(
                        requestModel.getNamespaceName().toString(),
                        resultModel.getItem().getUserId().toString(),
                        "Status"
                    );
                    String key = StatusDomain.createCacheKey(
                        resultModel.getItem().getExperienceName().toString(),
                        resultModel.getItem().getPropertyId().toString()
                    );
                    cache.put(
                        parentKey,
                        key,
                        resultModel.getItem(),
                          System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                    );
                    break;
                }
                case "set_rank_cap_by_user_id": {
                    SetRankCapByUserIdRequest requestModel = SetRankCapByUserIdRequest.fromJson(new ObjectMapper().readTree(job.getArgs()));
                    SetRankCapByUserIdResult resultModel = SetRankCapByUserIdResult.fromJson(new ObjectMapper().readTree(result.getResult()));
                    String parentKey = UserDomain.createCacheParentKey(
                        requestModel.getNamespaceName().toString(),
                        resultModel.getItem().getUserId().toString(),
                        "Status"
                    );
                    String key = StatusDomain.createCacheKey(
                        resultModel.getItem().getExperienceName().toString(),
                        resultModel.getItem().getPropertyId().toString()
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


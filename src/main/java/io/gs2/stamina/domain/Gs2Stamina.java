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

package io.gs2.stamina.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.stamina.Gs2StaminaRestClient;
import io.gs2.stamina.domain.iterator.*;
import io.gs2.stamina.domain.model.*;
import io.gs2.stamina.model.*;
import io.gs2.stamina.request.*;
import io.gs2.stamina.result.*;

import java.util.List;

public class Gs2Stamina {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2StaminaRestClient client;

    private final String parentKey;

    public Gs2Stamina(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2StaminaRestClient(
            session
        );
        this.parentKey = "stamina";
    }

    public NamespaceDomain createNamespace(
        CreateNamespaceRequest request
    ) {
        CreateNamespaceResult result = this.client.createNamespace(
            request
        );
        String parentKey = "stamina:Namespace";
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.stamina.domain.model.NamespaceDomain.createCacheKey(
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
                case "RecoverStaminaByUserId": {
                    RecoverStaminaByUserIdRequest requestModel = RecoverStaminaByUserIdRequest.fromJson(new ObjectMapper().readTree(request));
                    RecoverStaminaByUserIdResult resultModel = RecoverStaminaByUserIdResult.fromJson(new ObjectMapper().readTree(result));
                    String parentKey = UserDomain.createCacheParentKey(
                        requestModel.getNamespaceName().toString(),
                        resultModel.getItem().getUserId().toString(),
                        "Stamina"
                    );
                    String key = StaminaDomain.createCacheKey(
                        resultModel.getItem().getStaminaName().toString()
                    );
                    cache.put(
                        parentKey,
                        key,
                        resultModel.getItem(),
                        System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                    );
                    break;
                }
                case "RaiseMaxValueByUserId": {
                    RaiseMaxValueByUserIdRequest requestModel = RaiseMaxValueByUserIdRequest.fromJson(new ObjectMapper().readTree(request));
                    RaiseMaxValueByUserIdResult resultModel = RaiseMaxValueByUserIdResult.fromJson(new ObjectMapper().readTree(result));
                    String parentKey = UserDomain.createCacheParentKey(
                        requestModel.getNamespaceName().toString(),
                        resultModel.getItem().getUserId().toString(),
                        "Stamina"
                    );
                    String key = StaminaDomain.createCacheKey(
                        resultModel.getItem().getStaminaName().toString()
                    );
                    cache.put(
                        parentKey,
                        key,
                        resultModel.getItem(),
                        System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                    );
                    break;
                }
                case "SetMaxValueByUserId": {
                    SetMaxValueByUserIdRequest requestModel = SetMaxValueByUserIdRequest.fromJson(new ObjectMapper().readTree(request));
                    SetMaxValueByUserIdResult resultModel = SetMaxValueByUserIdResult.fromJson(new ObjectMapper().readTree(result));
                    String parentKey = UserDomain.createCacheParentKey(
                        requestModel.getNamespaceName().toString(),
                        resultModel.getItem().getUserId().toString(),
                        "Stamina"
                    );
                    String key = StaminaDomain.createCacheKey(
                        resultModel.getItem().getStaminaName().toString()
                    );
                    cache.put(
                        parentKey,
                        key,
                        resultModel.getItem(),
                        System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                    );
                    break;
                }
                case "SetRecoverIntervalByUserId": {
                    SetRecoverIntervalByUserIdRequest requestModel = SetRecoverIntervalByUserIdRequest.fromJson(new ObjectMapper().readTree(request));
                    SetRecoverIntervalByUserIdResult resultModel = SetRecoverIntervalByUserIdResult.fromJson(new ObjectMapper().readTree(result));
                    String parentKey = UserDomain.createCacheParentKey(
                        requestModel.getNamespaceName().toString(),
                        resultModel.getItem().getUserId().toString(),
                        "Stamina"
                    );
                    String key = StaminaDomain.createCacheKey(
                        resultModel.getItem().getStaminaName().toString()
                    );
                    cache.put(
                        parentKey,
                        key,
                        resultModel.getItem(),
                        System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                    );
                    break;
                }
                case "SetRecoverValueByUserId": {
                    SetRecoverValueByUserIdRequest requestModel = SetRecoverValueByUserIdRequest.fromJson(new ObjectMapper().readTree(request));
                    SetRecoverValueByUserIdResult resultModel = SetRecoverValueByUserIdResult.fromJson(new ObjectMapper().readTree(result));
                    String parentKey = UserDomain.createCacheParentKey(
                        requestModel.getNamespaceName().toString(),
                        resultModel.getItem().getUserId().toString(),
                        "Stamina"
                    );
                    String key = StaminaDomain.createCacheKey(
                        resultModel.getItem().getStaminaName().toString()
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
        try {
            switch (method) {
                case "ConsumeStaminaByUserId": {
                    ConsumeStaminaByUserIdRequest requestModel = ConsumeStaminaByUserIdRequest.fromJson(new ObjectMapper().readTree(request));
                    ConsumeStaminaByUserIdResult resultModel = ConsumeStaminaByUserIdResult.fromJson(new ObjectMapper().readTree(result));
                    String parentKey = UserDomain.createCacheParentKey(
                        requestModel.getNamespaceName().toString(),
                        resultModel.getItem().getUserId().toString(),
                        "Stamina"
                    );
                    String key = StaminaDomain.createCacheKey(
                        resultModel.getItem().getStaminaName().toString()
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

    public static void updateCacheFromJobResult(
            CacheDatabase cache,
            String method,
            io.gs2.jobQueue.model.Job job,
            io.gs2.jobQueue.model.JobResultBody result
    ) {
        try {
            switch (method) {
                case "recover_stamina_by_user_id": {
                    RecoverStaminaByUserIdRequest requestModel = RecoverStaminaByUserIdRequest.fromJson(new ObjectMapper().readTree(job.getArgs()));
                    RecoverStaminaByUserIdResult resultModel = RecoverStaminaByUserIdResult.fromJson(new ObjectMapper().readTree(result.getResult()));
                    String parentKey = UserDomain.createCacheParentKey(
                        requestModel.getNamespaceName().toString(),
                        resultModel.getItem().getUserId().toString(),
                        "Stamina"
                    );
                    String key = StaminaDomain.createCacheKey(
                        resultModel.getItem().getStaminaName().toString()
                    );
                    cache.put(
                        parentKey,
                        key,
                        resultModel.getItem(),
                          System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                    );
                    break;
                }
                case "raise_max_value_by_user_id": {
                    RaiseMaxValueByUserIdRequest requestModel = RaiseMaxValueByUserIdRequest.fromJson(new ObjectMapper().readTree(job.getArgs()));
                    RaiseMaxValueByUserIdResult resultModel = RaiseMaxValueByUserIdResult.fromJson(new ObjectMapper().readTree(result.getResult()));
                    String parentKey = UserDomain.createCacheParentKey(
                        requestModel.getNamespaceName().toString(),
                        resultModel.getItem().getUserId().toString(),
                        "Stamina"
                    );
                    String key = StaminaDomain.createCacheKey(
                        resultModel.getItem().getStaminaName().toString()
                    );
                    cache.put(
                        parentKey,
                        key,
                        resultModel.getItem(),
                          System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                    );
                    break;
                }
                case "set_max_value_by_user_id": {
                    SetMaxValueByUserIdRequest requestModel = SetMaxValueByUserIdRequest.fromJson(new ObjectMapper().readTree(job.getArgs()));
                    SetMaxValueByUserIdResult resultModel = SetMaxValueByUserIdResult.fromJson(new ObjectMapper().readTree(result.getResult()));
                    String parentKey = UserDomain.createCacheParentKey(
                        requestModel.getNamespaceName().toString(),
                        resultModel.getItem().getUserId().toString(),
                        "Stamina"
                    );
                    String key = StaminaDomain.createCacheKey(
                        resultModel.getItem().getStaminaName().toString()
                    );
                    cache.put(
                        parentKey,
                        key,
                        resultModel.getItem(),
                          System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                    );
                    break;
                }
                case "set_recover_interval_by_user_id": {
                    SetRecoverIntervalByUserIdRequest requestModel = SetRecoverIntervalByUserIdRequest.fromJson(new ObjectMapper().readTree(job.getArgs()));
                    SetRecoverIntervalByUserIdResult resultModel = SetRecoverIntervalByUserIdResult.fromJson(new ObjectMapper().readTree(result.getResult()));
                    String parentKey = UserDomain.createCacheParentKey(
                        requestModel.getNamespaceName().toString(),
                        resultModel.getItem().getUserId().toString(),
                        "Stamina"
                    );
                    String key = StaminaDomain.createCacheKey(
                        resultModel.getItem().getStaminaName().toString()
                    );
                    cache.put(
                        parentKey,
                        key,
                        resultModel.getItem(),
                          System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                    );
                    break;
                }
                case "set_recover_value_by_user_id": {
                    SetRecoverValueByUserIdRequest requestModel = SetRecoverValueByUserIdRequest.fromJson(new ObjectMapper().readTree(job.getArgs()));
                    SetRecoverValueByUserIdResult resultModel = SetRecoverValueByUserIdResult.fromJson(new ObjectMapper().readTree(result.getResult()));
                    String parentKey = UserDomain.createCacheParentKey(
                        requestModel.getNamespaceName().toString(),
                        resultModel.getItem().getUserId().toString(),
                        "Stamina"
                    );
                    String key = StaminaDomain.createCacheKey(
                        resultModel.getItem().getStaminaName().toString()
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


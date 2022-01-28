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

package io.gs2.inventory.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.inventory.Gs2InventoryRestClient;
import io.gs2.inventory.domain.iterator.*;
import io.gs2.inventory.domain.model.*;
import io.gs2.inventory.model.*;
import io.gs2.inventory.request.*;
import io.gs2.inventory.result.*;

import java.util.List;

public class Gs2Inventory {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2InventoryRestClient client;

    private final String parentKey;

    public Gs2Inventory(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2InventoryRestClient(
            session
        );
        this.parentKey = "inventory";
    }

    public NamespaceDomain createNamespace(
        CreateNamespaceRequest request
    ) {
        CreateNamespaceResult result = this.client.createNamespace(
            request
        );
        String parentKey = "inventory:Namespace";
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.inventory.domain.model.NamespaceDomain.createCacheKey(
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
                case "AddCapacityByUserId": {
                    AddCapacityByUserIdRequest requestModel = AddCapacityByUserIdRequest.fromJson(new ObjectMapper().readTree(request));
                    AddCapacityByUserIdResult resultModel = AddCapacityByUserIdResult.fromJson(new ObjectMapper().readTree(result));
                    String parentKey = UserDomain.createCacheParentKey(
                        requestModel.getNamespaceName().toString(),
                        resultModel.getItem().getUserId().toString(),
                        "Inventory"
                    );
                    String key = InventoryDomain.createCacheKey(
                        resultModel.getItem().getInventoryName().toString()
                    );
                    cache.put(
                        parentKey,
                        key,
                        resultModel.getItem(),
                        System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                    );
                    break;
                }
                case "SetCapacityByUserId": {
                    SetCapacityByUserIdRequest requestModel = SetCapacityByUserIdRequest.fromJson(new ObjectMapper().readTree(request));
                    SetCapacityByUserIdResult resultModel = SetCapacityByUserIdResult.fromJson(new ObjectMapper().readTree(result));
                    String parentKey = UserDomain.createCacheParentKey(
                        requestModel.getNamespaceName().toString(),
                        resultModel.getItem().getUserId().toString(),
                        "Inventory"
                    );
                    String key = InventoryDomain.createCacheKey(
                        resultModel.getItem().getInventoryName().toString()
                    );
                    cache.put(
                        parentKey,
                        key,
                        resultModel.getItem(),
                        System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                    );
                    break;
                }
                case "AcquireItemSetByUserId": {
                    AcquireItemSetByUserIdRequest requestModel = AcquireItemSetByUserIdRequest.fromJson(new ObjectMapper().readTree(request));
                    AcquireItemSetByUserIdResult resultModel = AcquireItemSetByUserIdResult.fromJson(new ObjectMapper().readTree(result));
                    for (ItemSet item : resultModel.getItems()) {
                        String parentKey = InventoryDomain.createCacheParentKey(
                            requestModel.getNamespaceName().toString(),
                            item.getUserId().toString(),
                            item.getInventoryName().toString(),
                            "ItemSet"
                        );
                        String key = ItemSetDomain.createCacheKey(
                            item.getItemName().toString(),
                            item.getName().toString()
                        );
                        cache.put(
                            parentKey,
                            key,
                            item,
                            System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                        );
                        key = ItemSetDomain.createCacheKey(
                            item.getItemName().toString(),
                            "null"
                        );
                        cache.put(
                            parentKey,
                            key,
                            item,
                            System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                        );
                    }
                    break;
                }
                case "AddReferenceOfByUserId": {
                    AddReferenceOfByUserIdRequest requestModel = AddReferenceOfByUserIdRequest.fromJson(new ObjectMapper().readTree(request));
                    AddReferenceOfByUserIdResult resultModel = AddReferenceOfByUserIdResult.fromJson(new ObjectMapper().readTree(result));
                    for (String item : resultModel.getItem()) {
                        String parentKey = ItemSetDomain.createCacheParentKey(
                            requestModel.getNamespaceName().toString(),
                            requestModel.getUserId().toString(),
                            requestModel.getInventoryName().toString(),
                            requestModel.getItemName().toString(),
                            requestModel.getItemSetName().toString(),
                            "ReferenceOf"
                        );
                        String key = ReferenceOfDomain.createCacheKey(
                            item.toString()
                        );
                        cache.put(
                            parentKey,
                            key,
                            item,
                            System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                        );
                    }
                    break;
                }
                case "DeleteReferenceOfByUserId": {
                    DeleteReferenceOfByUserIdRequest requestModel = DeleteReferenceOfByUserIdRequest.fromJson(new ObjectMapper().readTree(request));
                    DeleteReferenceOfByUserIdResult resultModel = DeleteReferenceOfByUserIdResult.fromJson(new ObjectMapper().readTree(result));
                    for (String item : resultModel.getItem()) {
                        String parentKey = ItemSetDomain.createCacheParentKey(
                            requestModel.getNamespaceName().toString(),
                            requestModel.getUserId().toString(),
                            requestModel.getInventoryName().toString(),
                            requestModel.getItemName().toString(),
                            requestModel.getItemSetName().toString(),
                            "ReferenceOf"
                        );
                        String key = ReferenceOfDomain.createCacheKey(
                            item.toString()
                        );
                        cache.put(
                            parentKey,
                            key,
                            item,
                            System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                        );
                    }
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
                case "ConsumeItemSetByUserId": {
                    ConsumeItemSetByUserIdRequest requestModel = ConsumeItemSetByUserIdRequest.fromJson(new ObjectMapper().readTree(request));
                    ConsumeItemSetByUserIdResult resultModel = ConsumeItemSetByUserIdResult.fromJson(new ObjectMapper().readTree(result));
                    for (ItemSet item : resultModel.getItems()) {
                        String parentKey = InventoryDomain.createCacheParentKey(
                            requestModel.getNamespaceName().toString(),
                            item.getUserId().toString(),
                            item.getInventoryName().toString(),
                            "ItemSet"
                        );
                        String key = ItemSetDomain.createCacheKey(
                            item.getItemName().toString(),
                            item.getName().toString()
                        );
                        cache.put(
                            parentKey,
                            key,
                            item,
                            System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                        );
                        String key2 = ItemSetDomain.createCacheKey(
                            item.getItemName().toString(),
                            "null"
                        );
                        cache.put(
                            parentKey,
                            key2,
                            item,
                            System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                        );
                    }
                    break;
                }
                case "VerifyReferenceOfByUserId": {
                    VerifyReferenceOfByUserIdRequest requestModel = VerifyReferenceOfByUserIdRequest.fromJson(new ObjectMapper().readTree(request));
                    VerifyReferenceOfByUserIdResult resultModel = VerifyReferenceOfByUserIdResult.fromJson(new ObjectMapper().readTree(result));
                    for (String item : resultModel.getItem()) {
                        String parentKey = ItemSetDomain.createCacheParentKey(
                            requestModel.getNamespaceName().toString(),
                            requestModel.getUserId().toString(),
                            requestModel.getInventoryName().toString(),
                            requestModel.getItemName().toString(),
                            requestModel.getItemSetName().toString(),
                            "ReferenceOf"
                        );
                        String key = ReferenceOfDomain.createCacheKey(
                            item.toString()
                        );
                        cache.put(
                            parentKey,
                            key,
                            item,
                            System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                        );
                    }
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
                case "add_capacity_by_user_id": {
                    AddCapacityByUserIdRequest requestModel = AddCapacityByUserIdRequest.fromJson(new ObjectMapper().readTree(job.getArgs()));
                    AddCapacityByUserIdResult resultModel = AddCapacityByUserIdResult.fromJson(new ObjectMapper().readTree(result.getResult()));
                    String parentKey = UserDomain.createCacheParentKey(
                        requestModel.getNamespaceName().toString(),
                        resultModel.getItem().getUserId().toString(),
                        "Inventory"
                    );
                    String key = InventoryDomain.createCacheKey(
                        resultModel.getItem().getInventoryName().toString()
                    );
                    cache.put(
                        parentKey,
                        key,
                        resultModel.getItem(),
                          System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                    );
                    break;
                }
                case "set_capacity_by_user_id": {
                    SetCapacityByUserIdRequest requestModel = SetCapacityByUserIdRequest.fromJson(new ObjectMapper().readTree(job.getArgs()));
                    SetCapacityByUserIdResult resultModel = SetCapacityByUserIdResult.fromJson(new ObjectMapper().readTree(result.getResult()));
                    String parentKey = UserDomain.createCacheParentKey(
                        requestModel.getNamespaceName().toString(),
                        resultModel.getItem().getUserId().toString(),
                        "Inventory"
                    );
                    String key = InventoryDomain.createCacheKey(
                        resultModel.getItem().getInventoryName().toString()
                    );
                    cache.put(
                        parentKey,
                        key,
                        resultModel.getItem(),
                          System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                    );
                    break;
                }
                case "acquire_item_set_by_user_id": {
                    AcquireItemSetByUserIdRequest requestModel = AcquireItemSetByUserIdRequest.fromJson(new ObjectMapper().readTree(job.getArgs()));
                    AcquireItemSetByUserIdResult resultModel = AcquireItemSetByUserIdResult.fromJson(new ObjectMapper().readTree(result.getResult()));
                    for (ItemSet item : resultModel.getItems()) {
                        String parentKey = InventoryDomain.createCacheParentKey(
                            requestModel.getNamespaceName().toString(),
                            item.getUserId().toString(),
                            item.getInventoryName().toString(),
                            "ItemSet"
                        );
                        String key = ItemSetDomain.createCacheKey(
                            item.getItemName().toString(),
                            item.getName().toString()
                        );
                        cache.put(
                            parentKey,
                            key,
                            item,
                            System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                        );
                    }
                    break;
                }
                case "add_reference_of_by_user_id": {
                    AddReferenceOfByUserIdRequest requestModel = AddReferenceOfByUserIdRequest.fromJson(new ObjectMapper().readTree(job.getArgs()));
                    AddReferenceOfByUserIdResult resultModel = AddReferenceOfByUserIdResult.fromJson(new ObjectMapper().readTree(result.getResult()));
                    for (String item : resultModel.getItem()) {
                        String parentKey = ItemSetDomain.createCacheParentKey(
                            requestModel.getNamespaceName().toString(),
                            requestModel.getUserId().toString(),
                            requestModel.getInventoryName().toString(),
                            requestModel.getItemName().toString(),
                            requestModel.getItemSetName().toString(),
                            "ReferenceOf"
                        );
                        String key = ReferenceOfDomain.createCacheKey(
                            item.toString()
                        );
                        cache.put(
                            parentKey,
                            key,
                            item,
                            System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                        );
                    }
                    break;
                }
                case "delete_reference_of_by_user_id": {
                    DeleteReferenceOfByUserIdRequest requestModel = DeleteReferenceOfByUserIdRequest.fromJson(new ObjectMapper().readTree(job.getArgs()));
                    DeleteReferenceOfByUserIdResult resultModel = DeleteReferenceOfByUserIdResult.fromJson(new ObjectMapper().readTree(result.getResult()));
                    for (String item : resultModel.getItem()) {
                        String parentKey = ItemSetDomain.createCacheParentKey(
                            requestModel.getNamespaceName().toString(),
                            requestModel.getUserId().toString(),
                            requestModel.getInventoryName().toString(),
                            requestModel.getItemName().toString(),
                            requestModel.getItemSetName().toString(),
                            "ReferenceOf"
                        );
                        String key = ReferenceOfDomain.createCacheKey(
                            item.toString()
                        );
                        cache.put(
                            parentKey,
                            key,
                            item,
                            System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                        );
                    }
                    break;
                }
            }
        } catch (JsonProcessingException e) {
        }
    }
}


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
package io.gs2.money.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.money.Gs2MoneyRestClient;
import io.gs2.money.domain.iterator.*;
import io.gs2.money.model.*;
import io.gs2.money.request.*;
import io.gs2.money.result.*;

import java.util.List;


public class WalletDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2MoneyRestClient client;
    private final String namespaceName;
    private final String userId;
    private final Integer slot;

    private final String parentKey;
    Float price;
    public Float getPrice() {
        return this.price;
    }
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return userId;
    }
    public Integer getSlot() {
        return slot;
    }

    public WalletDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String userId,
        Integer slot
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2MoneyRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.userId = userId;
        this.slot = slot;
        this.parentKey = io.gs2.money.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.userId != null ? this.userId.toString() : null,
            "Wallet"
        );
    }

    private Wallet get(
        GetWalletByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withSlot(this.slot);
        GetWalletByUserIdResult result = this.client.getWalletByUserId(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.money.domain.model.WalletDomain.createCacheKey(
                    request.getSlot() != null ? request.getSlot().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public WalletDomain deposit(
        DepositByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withSlot(this.slot);
        DepositByUserIdResult result = this.client.depositByUserId(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.money.domain.model.WalletDomain.createCacheKey(
                    request.getSlot() != null ? request.getSlot().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        WalletDomain domain = this;

        return domain;
    }

    public WalletDomain withdraw(
        WithdrawByUserIdRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withUserId(this.userId)
            .withSlot(this.slot);
        WithdrawByUserIdResult result = this.client.withdrawByUserId(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.money.domain.model.WalletDomain.createCacheKey(
                    request.getSlot() != null ? request.getSlot().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        WalletDomain domain = this;
        domain.price = result.getPrice();

        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String userId,
        String slot,
        String childType
    )
    {
        return String.join(
            ":",
            "money",
            namespaceName,
            userId,
            slot,
            childType
        );
    }

    public static String createCacheKey(
        String slot
    )
    {
        return String.join(
            ":",
            slot
        );
    }

    public Wallet model() {
        Wallet value = cache.get(
            parentKey,
            io.gs2.money.domain.model.WalletDomain.createCacheKey(
                this.getSlot() != null ? this.getSlot().toString() : null
            ),
            Wallet.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetWalletByUserIdRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.money.domain.model.WalletDomain.createCacheKey(
                        this.getSlot() != null ? this.getSlot().toString() : null
                    ),
                    Wallet.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.money.domain.model.WalletDomain.createCacheKey(
                this.getSlot() != null ? this.getSlot().toString() : null
            ),
            Wallet.class
        );
        }
        return value;
    }

}

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

package io.gs2.project.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.project.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * 支払い方法を新規作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateBillingMethodRequest extends Gs2BasicRequest<CreateBillingMethodRequest> {

    /** GS2アカウントトークン */
    private String accountToken;

    /**
     * GS2アカウントトークンを取得
     *
     * @return 支払い方法を新規作成
     */
    public String getAccountToken() {
        return accountToken;
    }

    /**
     * GS2アカウントトークンを設定
     *
     * @param accountToken 支払い方法を新規作成
     */
    public void setAccountToken(String accountToken) {
        this.accountToken = accountToken;
    }

    /**
     * GS2アカウントトークンを設定
     *
     * @param accountToken 支払い方法を新規作成
     * @return this
     */
    public CreateBillingMethodRequest withAccountToken(String accountToken) {
        setAccountToken(accountToken);
        return this;
    }

    /** 名前 */
    private String description;

    /**
     * 名前を取得
     *
     * @return 支払い方法を新規作成
     */
    public String getDescription() {
        return description;
    }

    /**
     * 名前を設定
     *
     * @param description 支払い方法を新規作成
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 名前を設定
     *
     * @param description 支払い方法を新規作成
     * @return this
     */
    public CreateBillingMethodRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** 支払い方法 */
    private String methodType;

    /**
     * 支払い方法を取得
     *
     * @return 支払い方法を新規作成
     */
    public String getMethodType() {
        return methodType;
    }

    /**
     * 支払い方法を設定
     *
     * @param methodType 支払い方法を新規作成
     */
    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    /**
     * 支払い方法を設定
     *
     * @param methodType 支払い方法を新規作成
     * @return this
     */
    public CreateBillingMethodRequest withMethodType(String methodType) {
        setMethodType(methodType);
        return this;
    }

    /** クレジットカードカスタマーID */
    private String cardCustomerId;

    /**
     * クレジットカードカスタマーIDを取得
     *
     * @return 支払い方法を新規作成
     */
    public String getCardCustomerId() {
        return cardCustomerId;
    }

    /**
     * クレジットカードカスタマーIDを設定
     *
     * @param cardCustomerId 支払い方法を新規作成
     */
    public void setCardCustomerId(String cardCustomerId) {
        this.cardCustomerId = cardCustomerId;
    }

    /**
     * クレジットカードカスタマーIDを設定
     *
     * @param cardCustomerId 支払い方法を新規作成
     * @return this
     */
    public CreateBillingMethodRequest withCardCustomerId(String cardCustomerId) {
        setCardCustomerId(cardCustomerId);
        return this;
    }

    /** パートナーID */
    private String partnerId;

    /**
     * パートナーIDを取得
     *
     * @return 支払い方法を新規作成
     */
    public String getPartnerId() {
        return partnerId;
    }

    /**
     * パートナーIDを設定
     *
     * @param partnerId 支払い方法を新規作成
     */
    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    /**
     * パートナーIDを設定
     *
     * @param partnerId 支払い方法を新規作成
     * @return this
     */
    public CreateBillingMethodRequest withPartnerId(String partnerId) {
        setPartnerId(partnerId);
        return this;
    }

}
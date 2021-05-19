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
 * 支払い方法を更新 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateBillingMethodRequest extends Gs2BasicRequest<UpdateBillingMethodRequest> {

    /** GS2アカウントトークン */
    private String accountToken;

    /**
     * GS2アカウントトークンを取得
     *
     * @return 支払い方法を更新
     */
    public String getAccountToken() {
        return accountToken;
    }

    /**
     * GS2アカウントトークンを設定
     *
     * @param accountToken 支払い方法を更新
     */
    public void setAccountToken(String accountToken) {
        this.accountToken = accountToken;
    }

    /**
     * GS2アカウントトークンを設定
     *
     * @param accountToken 支払い方法を更新
     * @return this
     */
    public UpdateBillingMethodRequest withAccountToken(String accountToken) {
        setAccountToken(accountToken);
        return this;
    }

    /** 名前 */
    private String billingMethodName;

    /**
     * 名前を取得
     *
     * @return 支払い方法を更新
     */
    public String getBillingMethodName() {
        return billingMethodName;
    }

    /**
     * 名前を設定
     *
     * @param billingMethodName 支払い方法を更新
     */
    public void setBillingMethodName(String billingMethodName) {
        this.billingMethodName = billingMethodName;
    }

    /**
     * 名前を設定
     *
     * @param billingMethodName 支払い方法を更新
     * @return this
     */
    public UpdateBillingMethodRequest withBillingMethodName(String billingMethodName) {
        setBillingMethodName(billingMethodName);
        return this;
    }

    /** 名前 */
    private String description;

    /**
     * 名前を取得
     *
     * @return 支払い方法を更新
     */
    public String getDescription() {
        return description;
    }

    /**
     * 名前を設定
     *
     * @param description 支払い方法を更新
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 名前を設定
     *
     * @param description 支払い方法を更新
     * @return this
     */
    public UpdateBillingMethodRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

}
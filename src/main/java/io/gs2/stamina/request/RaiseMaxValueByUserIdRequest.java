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

package io.gs2.stamina.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.stamina.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * ユーザIDを指定してスタミナの最大値を加算 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class RaiseMaxValueByUserIdRequest extends Gs2BasicRequest<RaiseMaxValueByUserIdRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ユーザIDを指定してスタミナの最大値を加算
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザIDを指定してスタミナの最大値を加算
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザIDを指定してスタミナの最大値を加算
     * @return this
     */
    public RaiseMaxValueByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** スタミナの種類名 */
    private String staminaName;

    /**
     * スタミナの種類名を取得
     *
     * @return ユーザIDを指定してスタミナの最大値を加算
     */
    public String getStaminaName() {
        return staminaName;
    }

    /**
     * スタミナの種類名を設定
     *
     * @param staminaName ユーザIDを指定してスタミナの最大値を加算
     */
    public void setStaminaName(String staminaName) {
        this.staminaName = staminaName;
    }

    /**
     * スタミナの種類名を設定
     *
     * @param staminaName ユーザIDを指定してスタミナの最大値を加算
     * @return this
     */
    public RaiseMaxValueByUserIdRequest withStaminaName(String staminaName) {
        setStaminaName(staminaName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return ユーザIDを指定してスタミナの最大値を加算
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザIDを指定してスタミナの最大値を加算
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザIDを指定してスタミナの最大値を加算
     * @return this
     */
    public RaiseMaxValueByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** 上昇する最大スタミナ量 */
    private Integer raiseValue;

    /**
     * 上昇する最大スタミナ量を取得
     *
     * @return ユーザIDを指定してスタミナの最大値を加算
     */
    public Integer getRaiseValue() {
        return raiseValue;
    }

    /**
     * 上昇する最大スタミナ量を設定
     *
     * @param raiseValue ユーザIDを指定してスタミナの最大値を加算
     */
    public void setRaiseValue(Integer raiseValue) {
        this.raiseValue = raiseValue;
    }

    /**
     * 上昇する最大スタミナ量を設定
     *
     * @param raiseValue ユーザIDを指定してスタミナの最大値を加算
     * @return this
     */
    public RaiseMaxValueByUserIdRequest withRaiseValue(Integer raiseValue) {
        setRaiseValue(raiseValue);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return ユーザIDを指定してスタミナの最大値を加算
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザIDを指定してスタミナの最大値を加算
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザIDを指定してスタミナの最大値を加算
     * @return this
     */
    public RaiseMaxValueByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}
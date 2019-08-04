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
import io.gs2.control.Gs2BasicRequest;

/**
 * ユーザIDを指定してスタミナを回復 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class RecoverStaminaByUserIdRequest extends Gs2BasicRequest<RecoverStaminaByUserIdRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ユーザIDを指定してスタミナを回復
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザIDを指定してスタミナを回復
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザIDを指定してスタミナを回復
     * @return this
     */
    public RecoverStaminaByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** スタミナの種類名 */
    private String staminaName;

    /**
     * スタミナの種類名を取得
     *
     * @return ユーザIDを指定してスタミナを回復
     */
    public String getStaminaName() {
        return staminaName;
    }

    /**
     * スタミナの種類名を設定
     *
     * @param staminaName ユーザIDを指定してスタミナを回復
     */
    public void setStaminaName(String staminaName) {
        this.staminaName = staminaName;
    }

    /**
     * スタミナの種類名を設定
     *
     * @param staminaName ユーザIDを指定してスタミナを回復
     * @return this
     */
    public RecoverStaminaByUserIdRequest withStaminaName(String staminaName) {
        setStaminaName(staminaName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return ユーザIDを指定してスタミナを回復
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザIDを指定してスタミナを回復
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザIDを指定してスタミナを回復
     * @return this
     */
    public RecoverStaminaByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** 回復するスタミナ量 */
    private Integer recoverValue;

    /**
     * 回復するスタミナ量を取得
     *
     * @return ユーザIDを指定してスタミナを回復
     */
    public Integer getRecoverValue() {
        return recoverValue;
    }

    /**
     * 回復するスタミナ量を設定
     *
     * @param recoverValue ユーザIDを指定してスタミナを回復
     */
    public void setRecoverValue(Integer recoverValue) {
        this.recoverValue = recoverValue;
    }

    /**
     * 回復するスタミナ量を設定
     *
     * @param recoverValue ユーザIDを指定してスタミナを回復
     * @return this
     */
    public RecoverStaminaByUserIdRequest withRecoverValue(Integer recoverValue) {
        setRecoverValue(recoverValue);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return ユーザIDを指定してスタミナを回復
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザIDを指定してスタミナを回復
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザIDを指定してスタミナを回復
     * @return this
     */
    public RecoverStaminaByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}
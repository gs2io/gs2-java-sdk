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
 * ユーザIDを指定してスタミナの回復間隔(分)を更新 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class SetRecoverIntervalByUserIdRequest extends Gs2BasicRequest<SetRecoverIntervalByUserIdRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ユーザIDを指定してスタミナの回復間隔(分)を更新
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザIDを指定してスタミナの回復間隔(分)を更新
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザIDを指定してスタミナの回復間隔(分)を更新
     * @return this
     */
    public SetRecoverIntervalByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** スタミナの種類名 */
    private String staminaName;

    /**
     * スタミナの種類名を取得
     *
     * @return ユーザIDを指定してスタミナの回復間隔(分)を更新
     */
    public String getStaminaName() {
        return staminaName;
    }

    /**
     * スタミナの種類名を設定
     *
     * @param staminaName ユーザIDを指定してスタミナの回復間隔(分)を更新
     */
    public void setStaminaName(String staminaName) {
        this.staminaName = staminaName;
    }

    /**
     * スタミナの種類名を設定
     *
     * @param staminaName ユーザIDを指定してスタミナの回復間隔(分)を更新
     * @return this
     */
    public SetRecoverIntervalByUserIdRequest withStaminaName(String staminaName) {
        setStaminaName(staminaName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return ユーザIDを指定してスタミナの回復間隔(分)を更新
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザIDを指定してスタミナの回復間隔(分)を更新
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザIDを指定してスタミナの回復間隔(分)を更新
     * @return this
     */
    public SetRecoverIntervalByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** スタミナの回復間隔(分) */
    private Integer recoverIntervalMinutes;

    /**
     * スタミナの回復間隔(分)を取得
     *
     * @return ユーザIDを指定してスタミナの回復間隔(分)を更新
     */
    public Integer getRecoverIntervalMinutes() {
        return recoverIntervalMinutes;
    }

    /**
     * スタミナの回復間隔(分)を設定
     *
     * @param recoverIntervalMinutes ユーザIDを指定してスタミナの回復間隔(分)を更新
     */
    public void setRecoverIntervalMinutes(Integer recoverIntervalMinutes) {
        this.recoverIntervalMinutes = recoverIntervalMinutes;
    }

    /**
     * スタミナの回復間隔(分)を設定
     *
     * @param recoverIntervalMinutes ユーザIDを指定してスタミナの回復間隔(分)を更新
     * @return this
     */
    public SetRecoverIntervalByUserIdRequest withRecoverIntervalMinutes(Integer recoverIntervalMinutes) {
        setRecoverIntervalMinutes(recoverIntervalMinutes);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return ユーザIDを指定してスタミナの回復間隔(分)を更新
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザIDを指定してスタミナの回復間隔(分)を更新
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザIDを指定してスタミナの回復間隔(分)を更新
     * @return this
     */
    public SetRecoverIntervalByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}
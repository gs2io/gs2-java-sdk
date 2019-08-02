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

package io.gs2.account.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.account.model.*;
import io.gs2.control.Gs2BasicRequest;

/**
 * 引き継ぎ設定を更新 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateTakeOverByUserIdRequest extends Gs2BasicRequest<UpdateTakeOverByUserIdRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return 引き継ぎ設定を更新
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 引き継ぎ設定を更新
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 引き継ぎ設定を更新
     * @return this
     */
    public UpdateTakeOverByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return 引き継ぎ設定を更新
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId 引き継ぎ設定を更新
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId 引き継ぎ設定を更新
     * @return this
     */
    public UpdateTakeOverByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** スロット番号 */
    private Integer type;

    /**
     * スロット番号を取得
     *
     * @return 引き継ぎ設定を更新
     */
    public Integer getType() {
        return type;
    }

    /**
     * スロット番号を設定
     *
     * @param type 引き継ぎ設定を更新
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * スロット番号を設定
     *
     * @param type 引き継ぎ設定を更新
     * @return this
     */
    public UpdateTakeOverByUserIdRequest withType(Integer type) {
        setType(type);
        return this;
    }

    /** 古いパスワード */
    private String oldPassword;

    /**
     * 古いパスワードを取得
     *
     * @return 引き継ぎ設定を更新
     */
    public String getOldPassword() {
        return oldPassword;
    }

    /**
     * 古いパスワードを設定
     *
     * @param oldPassword 引き継ぎ設定を更新
     */
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    /**
     * 古いパスワードを設定
     *
     * @param oldPassword 引き継ぎ設定を更新
     * @return this
     */
    public UpdateTakeOverByUserIdRequest withOldPassword(String oldPassword) {
        setOldPassword(oldPassword);
        return this;
    }

    /** 新しいパスワード */
    private String password;

    /**
     * 新しいパスワードを取得
     *
     * @return 引き継ぎ設定を更新
     */
    public String getPassword() {
        return password;
    }

    /**
     * 新しいパスワードを設定
     *
     * @param password 引き継ぎ設定を更新
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 新しいパスワードを設定
     *
     * @param password 引き継ぎ設定を更新
     * @return this
     */
    public UpdateTakeOverByUserIdRequest withPassword(String password) {
        setPassword(password);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return 引き継ぎ設定を更新
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider 引き継ぎ設定を更新
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider 引き継ぎ設定を更新
     * @return this
     */
    public UpdateTakeOverByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}
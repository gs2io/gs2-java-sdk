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
import io.gs2.core.control.Gs2BasicRequest;

/**
 * 引き継ぎ設定を更新 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DoTakeOverRequest extends Gs2BasicRequest<DoTakeOverRequest> {

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
    public DoTakeOverRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
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
    public DoTakeOverRequest withType(Integer type) {
        setType(type);
        return this;
    }

    /** 引き継ぎ用ユーザーID */
    private String userIdentifier;

    /**
     * 引き継ぎ用ユーザーIDを取得
     *
     * @return 引き継ぎ設定を更新
     */
    public String getUserIdentifier() {
        return userIdentifier;
    }

    /**
     * 引き継ぎ用ユーザーIDを設定
     *
     * @param userIdentifier 引き継ぎ設定を更新
     */
    public void setUserIdentifier(String userIdentifier) {
        this.userIdentifier = userIdentifier;
    }

    /**
     * 引き継ぎ用ユーザーIDを設定
     *
     * @param userIdentifier 引き継ぎ設定を更新
     * @return this
     */
    public DoTakeOverRequest withUserIdentifier(String userIdentifier) {
        setUserIdentifier(userIdentifier);
        return this;
    }

    /** パスワード */
    private String password;

    /**
     * パスワードを取得
     *
     * @return 引き継ぎ設定を更新
     */
    public String getPassword() {
        return password;
    }

    /**
     * パスワードを設定
     *
     * @param password 引き継ぎ設定を更新
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * パスワードを設定
     *
     * @param password 引き継ぎ設定を更新
     * @return this
     */
    public DoTakeOverRequest withPassword(String password) {
        setPassword(password);
        return this;
    }

}
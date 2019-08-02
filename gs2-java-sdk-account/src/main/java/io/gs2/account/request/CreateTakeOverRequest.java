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
 * 引き継ぎ設定を新規作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateTakeOverRequest extends Gs2BasicRequest<CreateTakeOverRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return 引き継ぎ設定を新規作成
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 引き継ぎ設定を新規作成
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 引き継ぎ設定を新規作成
     * @return this
     */
    public CreateTakeOverRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** スロット番号 */
    private Integer type;

    /**
     * スロット番号を取得
     *
     * @return 引き継ぎ設定を新規作成
     */
    public Integer getType() {
        return type;
    }

    /**
     * スロット番号を設定
     *
     * @param type 引き継ぎ設定を新規作成
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * スロット番号を設定
     *
     * @param type 引き継ぎ設定を新規作成
     * @return this
     */
    public CreateTakeOverRequest withType(Integer type) {
        setType(type);
        return this;
    }

    /** 引き継ぎ用ユーザーID */
    private String userIdentifier;

    /**
     * 引き継ぎ用ユーザーIDを取得
     *
     * @return 引き継ぎ設定を新規作成
     */
    public String getUserIdentifier() {
        return userIdentifier;
    }

    /**
     * 引き継ぎ用ユーザーIDを設定
     *
     * @param userIdentifier 引き継ぎ設定を新規作成
     */
    public void setUserIdentifier(String userIdentifier) {
        this.userIdentifier = userIdentifier;
    }

    /**
     * 引き継ぎ用ユーザーIDを設定
     *
     * @param userIdentifier 引き継ぎ設定を新規作成
     * @return this
     */
    public CreateTakeOverRequest withUserIdentifier(String userIdentifier) {
        setUserIdentifier(userIdentifier);
        return this;
    }

    /** パスワード */
    private String password;

    /**
     * パスワードを取得
     *
     * @return 引き継ぎ設定を新規作成
     */
    public String getPassword() {
        return password;
    }

    /**
     * パスワードを設定
     *
     * @param password 引き継ぎ設定を新規作成
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * パスワードを設定
     *
     * @param password 引き継ぎ設定を新規作成
     * @return this
     */
    public CreateTakeOverRequest withPassword(String password) {
        setPassword(password);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return 引き継ぎ設定を新規作成
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider 引き継ぎ設定を新規作成
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider 引き継ぎ設定を新規作成
     * @return this
     */
    public CreateTakeOverRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

    /** アクセストークン */
    private String accessToken;

    /**
     * アクセストークンを取得
     *
     * @return アクセストークン
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * アクセストークンを設定
     *
     * @param accessToken アクセストークン
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * アクセストークンを設定
     *
     * @param accessToken アクセストークン
     * @return this
     */
    public CreateTakeOverRequest withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

}
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

package io.gs2.gateway.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.gateway.model.*;
import io.gs2.control.Gs2BasicRequest;

/**
 * デバイストークンを設定 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class SetFirebaseTokenRequest extends Gs2BasicRequest<SetFirebaseTokenRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return デバイストークンを設定
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName デバイストークンを設定
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName デバイストークンを設定
     * @return this
     */
    public SetFirebaseTokenRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** Firebase Cloud Messaging のデバイストークン */
    private String token;

    /**
     * Firebase Cloud Messaging のデバイストークンを取得
     *
     * @return デバイストークンを設定
     */
    public String getToken() {
        return token;
    }

    /**
     * Firebase Cloud Messaging のデバイストークンを設定
     *
     * @param token デバイストークンを設定
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Firebase Cloud Messaging のデバイストークンを設定
     *
     * @param token デバイストークンを設定
     * @return this
     */
    public SetFirebaseTokenRequest withToken(String token) {
        setToken(token);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return デバイストークンを設定
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider デバイストークンを設定
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider デバイストークンを設定
     * @return this
     */
    public SetFirebaseTokenRequest withDuplicationAvoider(String duplicationAvoider) {
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
    public SetFirebaseTokenRequest withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

}
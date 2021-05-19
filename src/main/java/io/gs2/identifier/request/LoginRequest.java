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

package io.gs2.identifier.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.identifier.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * プロジェクトトークン を取得します のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class LoginRequest extends Gs2BasicRequest<LoginRequest> {

    /** クライアントID */
    private String client_id;

    /**
     * クライアントIDを取得
     *
     * @return プロジェクトトークン を取得します
     */
    public String getClientId() {
        return client_id;
    }

    /**
     * クライアントIDを設定
     *
     * @param clientId プロジェクトトークン を取得します
     */
    public void setClientId(String clientId) {
        this.client_id = clientId;
    }

    /**
     * クライアントIDを設定
     *
     * @param clientId プロジェクトトークン を取得します
     * @return this
     */
    public LoginRequest withClientId(String clientId) {
        setClientId(clientId);
        return this;
    }

    /** クライアントシークレット */
    private String client_secret;

    /**
     * クライアントシークレットを取得
     *
     * @return プロジェクトトークン を取得します
     */
    public String getClientSecret() {
        return client_secret;
    }

    /**
     * クライアントシークレットを設定
     *
     * @param clientSecret プロジェクトトークン を取得します
     */
    public void setClientSecret(String clientSecret) {
        this.client_secret = clientSecret;
    }

    /**
     * クライアントシークレットを設定
     *
     * @param clientSecret プロジェクトトークン を取得します
     * @return this
     */
    public LoginRequest withClientSecret(String clientSecret) {
        setClientSecret(clientSecret);
        return this;
    }

}
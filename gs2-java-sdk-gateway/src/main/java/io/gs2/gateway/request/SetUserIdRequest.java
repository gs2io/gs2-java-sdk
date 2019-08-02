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
 * WebsocketセッションにユーザIDを設定 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class SetUserIdRequest extends Gs2BasicRequest<SetUserIdRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return WebsocketセッションにユーザIDを設定
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName WebsocketセッションにユーザIDを設定
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName WebsocketセッションにユーザIDを設定
     * @return this
     */
    public SetUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** コネクションID */
    private String connectionId;

    /**
     * コネクションIDを取得
     *
     * @return WebsocketセッションにユーザIDを設定
     */
    public String getConnectionId() {
        return connectionId;
    }

    /**
     * コネクションIDを設定
     *
     * @param connectionId WebsocketセッションにユーザIDを設定
     */
    public void setConnectionId(String connectionId) {
        this.connectionId = connectionId;
    }

    /**
     * コネクションIDを設定
     *
     * @param connectionId WebsocketセッションにユーザIDを設定
     * @return this
     */
    public SetUserIdRequest withConnectionId(String connectionId) {
        setConnectionId(connectionId);
        return this;
    }

    /** 同時に異なるクライアントからの接続を許容するか */
    private Boolean allowConcurrentAccess;

    /**
     * 同時に異なるクライアントからの接続を許容するかを取得
     *
     * @return WebsocketセッションにユーザIDを設定
     */
    public Boolean getAllowConcurrentAccess() {
        return allowConcurrentAccess;
    }

    /**
     * 同時に異なるクライアントからの接続を許容するかを設定
     *
     * @param allowConcurrentAccess WebsocketセッションにユーザIDを設定
     */
    public void setAllowConcurrentAccess(Boolean allowConcurrentAccess) {
        this.allowConcurrentAccess = allowConcurrentAccess;
    }

    /**
     * 同時に異なるクライアントからの接続を許容するかを設定
     *
     * @param allowConcurrentAccess WebsocketセッションにユーザIDを設定
     * @return this
     */
    public SetUserIdRequest withAllowConcurrentAccess(Boolean allowConcurrentAccess) {
        setAllowConcurrentAccess(allowConcurrentAccess);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return WebsocketセッションにユーザIDを設定
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider WebsocketセッションにユーザIDを設定
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider WebsocketセッションにユーザIDを設定
     * @return this
     */
    public SetUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
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
    public SetUserIdRequest withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

}
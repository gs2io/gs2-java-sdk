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

package io.gs2.inbox.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.inbox.model.*;
import io.gs2.control.Gs2BasicRequest;

/**
 * メッセージを取得 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class GetMessageRequest extends Gs2BasicRequest<GetMessageRequest> {

    /** プレゼントボックス名 */
    private String namespaceName;

    /**
     * プレゼントボックス名を取得
     *
     * @return メッセージを取得
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * プレゼントボックス名を設定
     *
     * @param namespaceName メッセージを取得
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * プレゼントボックス名を設定
     *
     * @param namespaceName メッセージを取得
     * @return this
     */
    public GetMessageRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** メッセージID */
    private String messageName;

    /**
     * メッセージIDを取得
     *
     * @return メッセージを取得
     */
    public String getMessageName() {
        return messageName;
    }

    /**
     * メッセージIDを設定
     *
     * @param messageName メッセージを取得
     */
    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }

    /**
     * メッセージIDを設定
     *
     * @param messageName メッセージを取得
     * @return this
     */
    public GetMessageRequest withMessageName(String messageName) {
        setMessageName(messageName);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return メッセージを取得
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider メッセージを取得
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider メッセージを取得
     * @return this
     */
    public GetMessageRequest withDuplicationAvoider(String duplicationAvoider) {
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
    public GetMessageRequest withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

}
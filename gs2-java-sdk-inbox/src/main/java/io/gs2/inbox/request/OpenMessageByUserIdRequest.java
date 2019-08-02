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
 * ユーザーIDを指定してメッセージを開封 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class OpenMessageByUserIdRequest extends Gs2BasicRequest<OpenMessageByUserIdRequest> {

    /** プレゼントボックス名 */
    private String namespaceName;

    /**
     * プレゼントボックス名を取得
     *
     * @return ユーザーIDを指定してメッセージを開封
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * プレゼントボックス名を設定
     *
     * @param namespaceName ユーザーIDを指定してメッセージを開封
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * プレゼントボックス名を設定
     *
     * @param namespaceName ユーザーIDを指定してメッセージを開封
     * @return this
     */
    public OpenMessageByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return ユーザーIDを指定してメッセージを開封
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザーIDを指定してメッセージを開封
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザーIDを指定してメッセージを開封
     * @return this
     */
    public OpenMessageByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** メッセージID */
    private String messageName;

    /**
     * メッセージIDを取得
     *
     * @return ユーザーIDを指定してメッセージを開封
     */
    public String getMessageName() {
        return messageName;
    }

    /**
     * メッセージIDを設定
     *
     * @param messageName ユーザーIDを指定してメッセージを開封
     */
    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }

    /**
     * メッセージIDを設定
     *
     * @param messageName ユーザーIDを指定してメッセージを開封
     * @return this
     */
    public OpenMessageByUserIdRequest withMessageName(String messageName) {
        setMessageName(messageName);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return ユーザーIDを指定してメッセージを開封
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザーIDを指定してメッセージを開封
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザーIDを指定してメッセージを開封
     * @return this
     */
    public OpenMessageByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}
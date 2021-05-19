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

package io.gs2.friend.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.friend.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * ユーザーIDを指定してフレンドリクエストを承諾 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class AcceptRequestByUserIdRequest extends Gs2BasicRequest<AcceptRequestByUserIdRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ユーザーIDを指定してフレンドリクエストを承諾
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザーIDを指定してフレンドリクエストを承諾
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザーIDを指定してフレンドリクエストを承諾
     * @return this
     */
    public AcceptRequestByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** フレンドリクエストを受け取ったユーザID */
    private String userId;

    /**
     * フレンドリクエストを受け取ったユーザIDを取得
     *
     * @return ユーザーIDを指定してフレンドリクエストを承諾
     */
    public String getUserId() {
        return userId;
    }

    /**
     * フレンドリクエストを受け取ったユーザIDを設定
     *
     * @param userId ユーザーIDを指定してフレンドリクエストを承諾
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * フレンドリクエストを受け取ったユーザIDを設定
     *
     * @param userId ユーザーIDを指定してフレンドリクエストを承諾
     * @return this
     */
    public AcceptRequestByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** フレンドリクエストを送信したユーザID */
    private String fromUserId;

    /**
     * フレンドリクエストを送信したユーザIDを取得
     *
     * @return ユーザーIDを指定してフレンドリクエストを承諾
     */
    public String getFromUserId() {
        return fromUserId;
    }

    /**
     * フレンドリクエストを送信したユーザIDを設定
     *
     * @param fromUserId ユーザーIDを指定してフレンドリクエストを承諾
     */
    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    /**
     * フレンドリクエストを送信したユーザIDを設定
     *
     * @param fromUserId ユーザーIDを指定してフレンドリクエストを承諾
     * @return this
     */
    public AcceptRequestByUserIdRequest withFromUserId(String fromUserId) {
        setFromUserId(fromUserId);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return ユーザーIDを指定してフレンドリクエストを承諾
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザーIDを指定してフレンドリクエストを承諾
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザーIDを指定してフレンドリクエストを承諾
     * @return this
     */
    public AcceptRequestByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}
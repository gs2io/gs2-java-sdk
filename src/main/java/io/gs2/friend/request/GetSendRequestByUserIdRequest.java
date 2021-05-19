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
 * ユーザーIDを指定して送信したフレンドリクエストを取得 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class GetSendRequestByUserIdRequest extends Gs2BasicRequest<GetSendRequestByUserIdRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ユーザーIDを指定して送信したフレンドリクエストを取得
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザーIDを指定して送信したフレンドリクエストを取得
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザーIDを指定して送信したフレンドリクエストを取得
     * @return this
     */
    public GetSendRequestByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return ユーザーIDを指定して送信したフレンドリクエストを取得
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザーIDを指定して送信したフレンドリクエストを取得
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザーIDを指定して送信したフレンドリクエストを取得
     * @return this
     */
    public GetSendRequestByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** フレンドリクエストの宛先ユーザーID */
    private String targetUserId;

    /**
     * フレンドリクエストの宛先ユーザーIDを取得
     *
     * @return ユーザーIDを指定して送信したフレンドリクエストを取得
     */
    public String getTargetUserId() {
        return targetUserId;
    }

    /**
     * フレンドリクエストの宛先ユーザーIDを設定
     *
     * @param targetUserId ユーザーIDを指定して送信したフレンドリクエストを取得
     */
    public void setTargetUserId(String targetUserId) {
        this.targetUserId = targetUserId;
    }

    /**
     * フレンドリクエストの宛先ユーザーIDを設定
     *
     * @param targetUserId ユーザーIDを指定して送信したフレンドリクエストを取得
     * @return this
     */
    public GetSendRequestByUserIdRequest withTargetUserId(String targetUserId) {
        setTargetUserId(targetUserId);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return ユーザーIDを指定して送信したフレンドリクエストを取得
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザーIDを指定して送信したフレンドリクエストを取得
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザーIDを指定して送信したフレンドリクエストを取得
     * @return this
     */
    public GetSendRequestByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}
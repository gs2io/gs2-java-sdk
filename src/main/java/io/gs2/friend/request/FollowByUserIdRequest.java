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
 * ユーザーIDを指定してフォロー のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class FollowByUserIdRequest extends Gs2BasicRequest<FollowByUserIdRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ユーザーIDを指定してフォロー
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザーIDを指定してフォロー
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザーIDを指定してフォロー
     * @return this
     */
    public FollowByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** フォローするユーザID */
    private String userId;

    /**
     * フォローするユーザIDを取得
     *
     * @return ユーザーIDを指定してフォロー
     */
    public String getUserId() {
        return userId;
    }

    /**
     * フォローするユーザIDを設定
     *
     * @param userId ユーザーIDを指定してフォロー
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * フォローするユーザIDを設定
     *
     * @param userId ユーザーIDを指定してフォロー
     * @return this
     */
    public FollowByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** フォローされるユーザID */
    private String targetUserId;

    /**
     * フォローされるユーザIDを取得
     *
     * @return ユーザーIDを指定してフォロー
     */
    public String getTargetUserId() {
        return targetUserId;
    }

    /**
     * フォローされるユーザIDを設定
     *
     * @param targetUserId ユーザーIDを指定してフォロー
     */
    public void setTargetUserId(String targetUserId) {
        this.targetUserId = targetUserId;
    }

    /**
     * フォローされるユーザIDを設定
     *
     * @param targetUserId ユーザーIDを指定してフォロー
     * @return this
     */
    public FollowByUserIdRequest withTargetUserId(String targetUserId) {
        setTargetUserId(targetUserId);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return ユーザーIDを指定してフォロー
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザーIDを指定してフォロー
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザーIDを指定してフォロー
     * @return this
     */
    public FollowByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}
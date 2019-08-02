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
 * 引き継ぎ設定を削除 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DeleteTakeOverByUserIdentifierRequest extends Gs2BasicRequest<DeleteTakeOverByUserIdentifierRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return 引き継ぎ設定を削除
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 引き継ぎ設定を削除
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 引き継ぎ設定を削除
     * @return this
     */
    public DeleteTakeOverByUserIdentifierRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return 引き継ぎ設定を削除
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId 引き継ぎ設定を削除
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId 引き継ぎ設定を削除
     * @return this
     */
    public DeleteTakeOverByUserIdentifierRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** スロット番号 */
    private Integer type;

    /**
     * スロット番号を取得
     *
     * @return 引き継ぎ設定を削除
     */
    public Integer getType() {
        return type;
    }

    /**
     * スロット番号を設定
     *
     * @param type 引き継ぎ設定を削除
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * スロット番号を設定
     *
     * @param type 引き継ぎ設定を削除
     * @return this
     */
    public DeleteTakeOverByUserIdentifierRequest withType(Integer type) {
        setType(type);
        return this;
    }

    /** 引き継ぎ用ユーザーID */
    private String userIdentifier;

    /**
     * 引き継ぎ用ユーザーIDを取得
     *
     * @return 引き継ぎ設定を削除
     */
    public String getUserIdentifier() {
        return userIdentifier;
    }

    /**
     * 引き継ぎ用ユーザーIDを設定
     *
     * @param userIdentifier 引き継ぎ設定を削除
     */
    public void setUserIdentifier(String userIdentifier) {
        this.userIdentifier = userIdentifier;
    }

    /**
     * 引き継ぎ用ユーザーIDを設定
     *
     * @param userIdentifier 引き継ぎ設定を削除
     * @return this
     */
    public DeleteTakeOverByUserIdentifierRequest withUserIdentifier(String userIdentifier) {
        setUserIdentifier(userIdentifier);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return 引き継ぎ設定を削除
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider 引き継ぎ設定を削除
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider 引き継ぎ設定を削除
     * @return this
     */
    public DeleteTakeOverByUserIdentifierRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}
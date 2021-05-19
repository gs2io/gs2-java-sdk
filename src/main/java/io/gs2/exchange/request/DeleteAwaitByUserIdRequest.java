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

package io.gs2.exchange.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.exchange.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * 交換待機を削除 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DeleteAwaitByUserIdRequest extends Gs2BasicRequest<DeleteAwaitByUserIdRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return 交換待機を削除
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 交換待機を削除
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 交換待機を削除
     * @return this
     */
    public DeleteAwaitByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return 交換待機を削除
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId 交換待機を削除
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId 交換待機を削除
     * @return this
     */
    public DeleteAwaitByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** 交換レート名 */
    private String rateName;

    /**
     * 交換レート名を取得
     *
     * @return 交換待機を削除
     */
    public String getRateName() {
        return rateName;
    }

    /**
     * 交換レート名を設定
     *
     * @param rateName 交換待機を削除
     */
    public void setRateName(String rateName) {
        this.rateName = rateName;
    }

    /**
     * 交換レート名を設定
     *
     * @param rateName 交換待機を削除
     * @return this
     */
    public DeleteAwaitByUserIdRequest withRateName(String rateName) {
        setRateName(rateName);
        return this;
    }

    /** 交換待機の名前 */
    private String awaitName;

    /**
     * 交換待機の名前を取得
     *
     * @return 交換待機を削除
     */
    public String getAwaitName() {
        return awaitName;
    }

    /**
     * 交換待機の名前を設定
     *
     * @param awaitName 交換待機を削除
     */
    public void setAwaitName(String awaitName) {
        this.awaitName = awaitName;
    }

    /**
     * 交換待機の名前を設定
     *
     * @param awaitName 交換待機を削除
     * @return this
     */
    public DeleteAwaitByUserIdRequest withAwaitName(String awaitName) {
        setAwaitName(awaitName);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return 交換待機を削除
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider 交換待機を削除
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider 交換待機を削除
     * @return this
     */
    public DeleteAwaitByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}
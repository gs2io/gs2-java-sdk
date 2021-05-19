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

package io.gs2.showcase.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.showcase.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * ユーザIDを指定して陳列棚を取得 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class BuyByUserIdRequest extends Gs2BasicRequest<BuyByUserIdRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ユーザIDを指定して陳列棚を取得
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザIDを指定して陳列棚を取得
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザIDを指定して陳列棚を取得
     * @return this
     */
    public BuyByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** 商品名 */
    private String showcaseName;

    /**
     * 商品名を取得
     *
     * @return ユーザIDを指定して陳列棚を取得
     */
    public String getShowcaseName() {
        return showcaseName;
    }

    /**
     * 商品名を設定
     *
     * @param showcaseName ユーザIDを指定して陳列棚を取得
     */
    public void setShowcaseName(String showcaseName) {
        this.showcaseName = showcaseName;
    }

    /**
     * 商品名を設定
     *
     * @param showcaseName ユーザIDを指定して陳列棚を取得
     * @return this
     */
    public BuyByUserIdRequest withShowcaseName(String showcaseName) {
        setShowcaseName(showcaseName);
        return this;
    }

    /** 陳列商品ID */
    private String displayItemId;

    /**
     * 陳列商品IDを取得
     *
     * @return ユーザIDを指定して陳列棚を取得
     */
    public String getDisplayItemId() {
        return displayItemId;
    }

    /**
     * 陳列商品IDを設定
     *
     * @param displayItemId ユーザIDを指定して陳列棚を取得
     */
    public void setDisplayItemId(String displayItemId) {
        this.displayItemId = displayItemId;
    }

    /**
     * 陳列商品IDを設定
     *
     * @param displayItemId ユーザIDを指定して陳列棚を取得
     * @return this
     */
    public BuyByUserIdRequest withDisplayItemId(String displayItemId) {
        setDisplayItemId(displayItemId);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return ユーザIDを指定して陳列棚を取得
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザIDを指定して陳列棚を取得
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザIDを指定して陳列棚を取得
     * @return this
     */
    public BuyByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** 設定値 */
    private List<Config> config;

    /**
     * 設定値を取得
     *
     * @return ユーザIDを指定して陳列棚を取得
     */
    public List<Config> getConfig() {
        return config;
    }

    /**
     * 設定値を設定
     *
     * @param config ユーザIDを指定して陳列棚を取得
     */
    public void setConfig(List<Config> config) {
        this.config = config;
    }

    /**
     * 設定値を設定
     *
     * @param config ユーザIDを指定して陳列棚を取得
     * @return this
     */
    public BuyByUserIdRequest withConfig(List<Config> config) {
        setConfig(config);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return ユーザIDを指定して陳列棚を取得
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザIDを指定して陳列棚を取得
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザIDを指定して陳列棚を取得
     * @return this
     */
    public BuyByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}
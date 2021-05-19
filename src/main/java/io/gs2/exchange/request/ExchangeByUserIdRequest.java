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
 * ユーザIDを指定して交換を実行 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class ExchangeByUserIdRequest extends Gs2BasicRequest<ExchangeByUserIdRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ユーザIDを指定して交換を実行
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザIDを指定して交換を実行
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザIDを指定して交換を実行
     * @return this
     */
    public ExchangeByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** 交換レートの種類名 */
    private String rateName;

    /**
     * 交換レートの種類名を取得
     *
     * @return ユーザIDを指定して交換を実行
     */
    public String getRateName() {
        return rateName;
    }

    /**
     * 交換レートの種類名を設定
     *
     * @param rateName ユーザIDを指定して交換を実行
     */
    public void setRateName(String rateName) {
        this.rateName = rateName;
    }

    /**
     * 交換レートの種類名を設定
     *
     * @param rateName ユーザIDを指定して交換を実行
     * @return this
     */
    public ExchangeByUserIdRequest withRateName(String rateName) {
        setRateName(rateName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return ユーザIDを指定して交換を実行
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザIDを指定して交換を実行
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザIDを指定して交換を実行
     * @return this
     */
    public ExchangeByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** 交換するロット数 */
    private Integer count;

    /**
     * 交換するロット数を取得
     *
     * @return ユーザIDを指定して交換を実行
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 交換するロット数を設定
     *
     * @param count ユーザIDを指定して交換を実行
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 交換するロット数を設定
     *
     * @param count ユーザIDを指定して交換を実行
     * @return this
     */
    public ExchangeByUserIdRequest withCount(Integer count) {
        setCount(count);
        return this;
    }

    /** 設定値 */
    private List<Config> config;

    /**
     * 設定値を取得
     *
     * @return ユーザIDを指定して交換を実行
     */
    public List<Config> getConfig() {
        return config;
    }

    /**
     * 設定値を設定
     *
     * @param config ユーザIDを指定して交換を実行
     */
    public void setConfig(List<Config> config) {
        this.config = config;
    }

    /**
     * 設定値を設定
     *
     * @param config ユーザIDを指定して交換を実行
     * @return this
     */
    public ExchangeByUserIdRequest withConfig(List<Config> config) {
        setConfig(config);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return ユーザIDを指定して交換を実行
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザIDを指定して交換を実行
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザIDを指定して交換を実行
     * @return this
     */
    public ExchangeByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}
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

package io.gs2.quest.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.quest.model.*;
import io.gs2.control.Gs2BasicRequest;

/**
 * クエストを完了 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class EndRequest extends Gs2BasicRequest<EndRequest> {

    /** カテゴリ名 */
    private String namespaceName;

    /**
     * カテゴリ名を取得
     *
     * @return クエストを完了
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * カテゴリ名を設定
     *
     * @param namespaceName クエストを完了
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * カテゴリ名を設定
     *
     * @param namespaceName クエストを完了
     * @return this
     */
    public EndRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** トランザクションID */
    private String transactionId;

    /**
     * トランザクションIDを取得
     *
     * @return クエストを完了
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * トランザクションIDを設定
     *
     * @param transactionId クエストを完了
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * トランザクションIDを設定
     *
     * @param transactionId クエストを完了
     * @return this
     */
    public EndRequest withTransactionId(String transactionId) {
        setTransactionId(transactionId);
        return this;
    }

    /** 実際にクエストで得た報酬 */
    private List<Reward> rewards;

    /**
     * 実際にクエストで得た報酬を取得
     *
     * @return クエストを完了
     */
    public List<Reward> getRewards() {
        return rewards;
    }

    /**
     * 実際にクエストで得た報酬を設定
     *
     * @param rewards クエストを完了
     */
    public void setRewards(List<Reward> rewards) {
        this.rewards = rewards;
    }

    /**
     * 実際にクエストで得た報酬を設定
     *
     * @param rewards クエストを完了
     * @return this
     */
    public EndRequest withRewards(List<Reward> rewards) {
        setRewards(rewards);
        return this;
    }

    /** クエストをクリアしたか */
    private Boolean isComplete;

    /**
     * クエストをクリアしたかを取得
     *
     * @return クエストを完了
     */
    public Boolean getIsComplete() {
        return isComplete;
    }

    /**
     * クエストをクリアしたかを設定
     *
     * @param isComplete クエストを完了
     */
    public void setIsComplete(Boolean isComplete) {
        this.isComplete = isComplete;
    }

    /**
     * クエストをクリアしたかを設定
     *
     * @param isComplete クエストを完了
     * @return this
     */
    public EndRequest withIsComplete(Boolean isComplete) {
        setIsComplete(isComplete);
        return this;
    }

    /** スタンプシートの変数に適用する設定値 */
    private List<Config> config;

    /**
     * スタンプシートの変数に適用する設定値を取得
     *
     * @return クエストを完了
     */
    public List<Config> getConfig() {
        return config;
    }

    /**
     * スタンプシートの変数に適用する設定値を設定
     *
     * @param config クエストを完了
     */
    public void setConfig(List<Config> config) {
        this.config = config;
    }

    /**
     * スタンプシートの変数に適用する設定値を設定
     *
     * @param config クエストを完了
     * @return this
     */
    public EndRequest withConfig(List<Config> config) {
        setConfig(config);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return クエストを完了
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider クエストを完了
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider クエストを完了
     * @return this
     */
    public EndRequest withDuplicationAvoider(String duplicationAvoider) {
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
    public EndRequest withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

}
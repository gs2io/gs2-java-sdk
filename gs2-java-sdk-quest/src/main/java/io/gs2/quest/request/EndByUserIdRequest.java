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
 * ユーザIDを指定してクエストを完了 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class EndByUserIdRequest extends Gs2BasicRequest<EndByUserIdRequest> {

    /** カテゴリ名 */
    private String namespaceName;

    /**
     * カテゴリ名を取得
     *
     * @return ユーザIDを指定してクエストを完了
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * カテゴリ名を設定
     *
     * @param namespaceName ユーザIDを指定してクエストを完了
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * カテゴリ名を設定
     *
     * @param namespaceName ユーザIDを指定してクエストを完了
     * @return this
     */
    public EndByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return ユーザIDを指定してクエストを完了
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザIDを指定してクエストを完了
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザIDを指定してクエストを完了
     * @return this
     */
    public EndByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** トランザクションID */
    private String transactionId;

    /**
     * トランザクションIDを取得
     *
     * @return ユーザIDを指定してクエストを完了
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * トランザクションIDを設定
     *
     * @param transactionId ユーザIDを指定してクエストを完了
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * トランザクションIDを設定
     *
     * @param transactionId ユーザIDを指定してクエストを完了
     * @return this
     */
    public EndByUserIdRequest withTransactionId(String transactionId) {
        setTransactionId(transactionId);
        return this;
    }

    /** 実際にクエストで得た報酬 */
    private List<Reward> rewards;

    /**
     * 実際にクエストで得た報酬を取得
     *
     * @return ユーザIDを指定してクエストを完了
     */
    public List<Reward> getRewards() {
        return rewards;
    }

    /**
     * 実際にクエストで得た報酬を設定
     *
     * @param rewards ユーザIDを指定してクエストを完了
     */
    public void setRewards(List<Reward> rewards) {
        this.rewards = rewards;
    }

    /**
     * 実際にクエストで得た報酬を設定
     *
     * @param rewards ユーザIDを指定してクエストを完了
     * @return this
     */
    public EndByUserIdRequest withRewards(List<Reward> rewards) {
        setRewards(rewards);
        return this;
    }

    /** クエストをクリアしたか */
    private Boolean isComplete;

    /**
     * クエストをクリアしたかを取得
     *
     * @return ユーザIDを指定してクエストを完了
     */
    public Boolean getIsComplete() {
        return isComplete;
    }

    /**
     * クエストをクリアしたかを設定
     *
     * @param isComplete ユーザIDを指定してクエストを完了
     */
    public void setIsComplete(Boolean isComplete) {
        this.isComplete = isComplete;
    }

    /**
     * クエストをクリアしたかを設定
     *
     * @param isComplete ユーザIDを指定してクエストを完了
     * @return this
     */
    public EndByUserIdRequest withIsComplete(Boolean isComplete) {
        setIsComplete(isComplete);
        return this;
    }

    /** スタンプシートの変数に適用する設定値 */
    private List<Config> config;

    /**
     * スタンプシートの変数に適用する設定値を取得
     *
     * @return ユーザIDを指定してクエストを完了
     */
    public List<Config> getConfig() {
        return config;
    }

    /**
     * スタンプシートの変数に適用する設定値を設定
     *
     * @param config ユーザIDを指定してクエストを完了
     */
    public void setConfig(List<Config> config) {
        this.config = config;
    }

    /**
     * スタンプシートの変数に適用する設定値を設定
     *
     * @param config ユーザIDを指定してクエストを完了
     * @return this
     */
    public EndByUserIdRequest withConfig(List<Config> config) {
        setConfig(config);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return ユーザIDを指定してクエストを完了
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザIDを指定してクエストを完了
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザIDを指定してクエストを完了
     * @return this
     */
    public EndByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}
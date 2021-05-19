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
import io.gs2.core.control.Gs2BasicRequest;

/**
 * ユーザIDを指定してクエスト挑戦を作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateProgressByUserIdRequest extends Gs2BasicRequest<CreateProgressByUserIdRequest> {

    /** カテゴリ名 */
    private String namespaceName;

    /**
     * カテゴリ名を取得
     *
     * @return ユーザIDを指定してクエスト挑戦を作成
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * カテゴリ名を設定
     *
     * @param namespaceName ユーザIDを指定してクエスト挑戦を作成
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * カテゴリ名を設定
     *
     * @param namespaceName ユーザIDを指定してクエスト挑戦を作成
     * @return this
     */
    public CreateProgressByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return ユーザIDを指定してクエスト挑戦を作成
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザIDを指定してクエスト挑戦を作成
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザIDを指定してクエスト挑戦を作成
     * @return this
     */
    public CreateProgressByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** クエストモデル */
    private String questModelId;

    /**
     * クエストモデルを取得
     *
     * @return ユーザIDを指定してクエスト挑戦を作成
     */
    public String getQuestModelId() {
        return questModelId;
    }

    /**
     * クエストモデルを設定
     *
     * @param questModelId ユーザIDを指定してクエスト挑戦を作成
     */
    public void setQuestModelId(String questModelId) {
        this.questModelId = questModelId;
    }

    /**
     * クエストモデルを設定
     *
     * @param questModelId ユーザIDを指定してクエスト挑戦を作成
     * @return this
     */
    public CreateProgressByUserIdRequest withQuestModelId(String questModelId) {
        setQuestModelId(questModelId);
        return this;
    }

    /** すでに開始しているクエストがある場合にそれを破棄して開始するか */
    private Boolean force;

    /**
     * すでに開始しているクエストがある場合にそれを破棄して開始するかを取得
     *
     * @return ユーザIDを指定してクエスト挑戦を作成
     */
    public Boolean getForce() {
        return force;
    }

    /**
     * すでに開始しているクエストがある場合にそれを破棄して開始するかを設定
     *
     * @param force ユーザIDを指定してクエスト挑戦を作成
     */
    public void setForce(Boolean force) {
        this.force = force;
    }

    /**
     * すでに開始しているクエストがある場合にそれを破棄して開始するかを設定
     *
     * @param force ユーザIDを指定してクエスト挑戦を作成
     * @return this
     */
    public CreateProgressByUserIdRequest withForce(Boolean force) {
        setForce(force);
        return this;
    }

    /** スタンプシートの変数に適用する設定値 */
    private List<Config> config;

    /**
     * スタンプシートの変数に適用する設定値を取得
     *
     * @return ユーザIDを指定してクエスト挑戦を作成
     */
    public List<Config> getConfig() {
        return config;
    }

    /**
     * スタンプシートの変数に適用する設定値を設定
     *
     * @param config ユーザIDを指定してクエスト挑戦を作成
     */
    public void setConfig(List<Config> config) {
        this.config = config;
    }

    /**
     * スタンプシートの変数に適用する設定値を設定
     *
     * @param config ユーザIDを指定してクエスト挑戦を作成
     * @return this
     */
    public CreateProgressByUserIdRequest withConfig(List<Config> config) {
        setConfig(config);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return ユーザIDを指定してクエスト挑戦を作成
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザIDを指定してクエスト挑戦を作成
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザIDを指定してクエスト挑戦を作成
     * @return this
     */
    public CreateProgressByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}
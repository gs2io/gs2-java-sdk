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
 * クエストを開始 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class StartRequest extends Gs2BasicRequest<StartRequest> {

    /** カテゴリ名 */
    private String namespaceName;

    /**
     * カテゴリ名を取得
     *
     * @return クエストを開始
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * カテゴリ名を設定
     *
     * @param namespaceName クエストを開始
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * カテゴリ名を設定
     *
     * @param namespaceName クエストを開始
     * @return this
     */
    public StartRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** クエストグループ名 */
    private String questGroupName;

    /**
     * クエストグループ名を取得
     *
     * @return クエストを開始
     */
    public String getQuestGroupName() {
        return questGroupName;
    }

    /**
     * クエストグループ名を設定
     *
     * @param questGroupName クエストを開始
     */
    public void setQuestGroupName(String questGroupName) {
        this.questGroupName = questGroupName;
    }

    /**
     * クエストグループ名を設定
     *
     * @param questGroupName クエストを開始
     * @return this
     */
    public StartRequest withQuestGroupName(String questGroupName) {
        setQuestGroupName(questGroupName);
        return this;
    }

    /** クエストモデル名 */
    private String questName;

    /**
     * クエストモデル名を取得
     *
     * @return クエストを開始
     */
    public String getQuestName() {
        return questName;
    }

    /**
     * クエストモデル名を設定
     *
     * @param questName クエストを開始
     */
    public void setQuestName(String questName) {
        this.questName = questName;
    }

    /**
     * クエストモデル名を設定
     *
     * @param questName クエストを開始
     * @return this
     */
    public StartRequest withQuestName(String questName) {
        setQuestName(questName);
        return this;
    }

    /** すでに開始しているクエストがある場合にそれを破棄して開始するか */
    private Boolean force;

    /**
     * すでに開始しているクエストがある場合にそれを破棄して開始するかを取得
     *
     * @return クエストを開始
     */
    public Boolean getForce() {
        return force;
    }

    /**
     * すでに開始しているクエストがある場合にそれを破棄して開始するかを設定
     *
     * @param force クエストを開始
     */
    public void setForce(Boolean force) {
        this.force = force;
    }

    /**
     * すでに開始しているクエストがある場合にそれを破棄して開始するかを設定
     *
     * @param force クエストを開始
     * @return this
     */
    public StartRequest withForce(Boolean force) {
        setForce(force);
        return this;
    }

    /** スタンプシートの変数に適用する設定値 */
    private List<Config> config;

    /**
     * スタンプシートの変数に適用する設定値を取得
     *
     * @return クエストを開始
     */
    public List<Config> getConfig() {
        return config;
    }

    /**
     * スタンプシートの変数に適用する設定値を設定
     *
     * @param config クエストを開始
     */
    public void setConfig(List<Config> config) {
        this.config = config;
    }

    /**
     * スタンプシートの変数に適用する設定値を設定
     *
     * @param config クエストを開始
     * @return this
     */
    public StartRequest withConfig(List<Config> config) {
        setConfig(config);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return クエストを開始
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider クエストを開始
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider クエストを開始
     * @return this
     */
    public StartRequest withDuplicationAvoider(String duplicationAvoider) {
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
    public StartRequest withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

}
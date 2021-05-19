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
 * クエストを分類するカテゴリーを新規作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateNamespaceRequest extends Gs2BasicRequest<CreateNamespaceRequest> {

    /** カテゴリ名 */
    private String name;

    /**
     * カテゴリ名を取得
     *
     * @return クエストを分類するカテゴリーを新規作成
     */
    public String getName() {
        return name;
    }

    /**
     * カテゴリ名を設定
     *
     * @param name クエストを分類するカテゴリーを新規作成
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * カテゴリ名を設定
     *
     * @param name クエストを分類するカテゴリーを新規作成
     * @return this
     */
    public CreateNamespaceRequest withName(String name) {
        setName(name);
        return this;
    }

    /** ネームスペースの説明 */
    private String description;

    /**
     * ネームスペースの説明を取得
     *
     * @return クエストを分類するカテゴリーを新規作成
     */
    public String getDescription() {
        return description;
    }

    /**
     * ネームスペースの説明を設定
     *
     * @param description クエストを分類するカテゴリーを新規作成
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * ネームスペースの説明を設定
     *
     * @param description クエストを分類するカテゴリーを新規作成
     * @return this
     */
    public CreateNamespaceRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** クエスト開始したときに実行するスクリプト */
    private ScriptSetting startQuestScript;

    /**
     * クエスト開始したときに実行するスクリプトを取得
     *
     * @return クエストを分類するカテゴリーを新規作成
     */
    public ScriptSetting getStartQuestScript() {
        return startQuestScript;
    }

    /**
     * クエスト開始したときに実行するスクリプトを設定
     *
     * @param startQuestScript クエストを分類するカテゴリーを新規作成
     */
    public void setStartQuestScript(ScriptSetting startQuestScript) {
        this.startQuestScript = startQuestScript;
    }

    /**
     * クエスト開始したときに実行するスクリプトを設定
     *
     * @param startQuestScript クエストを分類するカテゴリーを新規作成
     * @return this
     */
    public CreateNamespaceRequest withStartQuestScript(ScriptSetting startQuestScript) {
        setStartQuestScript(startQuestScript);
        return this;
    }

    /** クエストクリアしたときに実行するスクリプト */
    private ScriptSetting completeQuestScript;

    /**
     * クエストクリアしたときに実行するスクリプトを取得
     *
     * @return クエストを分類するカテゴリーを新規作成
     */
    public ScriptSetting getCompleteQuestScript() {
        return completeQuestScript;
    }

    /**
     * クエストクリアしたときに実行するスクリプトを設定
     *
     * @param completeQuestScript クエストを分類するカテゴリーを新規作成
     */
    public void setCompleteQuestScript(ScriptSetting completeQuestScript) {
        this.completeQuestScript = completeQuestScript;
    }

    /**
     * クエストクリアしたときに実行するスクリプトを設定
     *
     * @param completeQuestScript クエストを分類するカテゴリーを新規作成
     * @return this
     */
    public CreateNamespaceRequest withCompleteQuestScript(ScriptSetting completeQuestScript) {
        setCompleteQuestScript(completeQuestScript);
        return this;
    }

    /** クエスト失敗したときに実行するスクリプト */
    private ScriptSetting failedQuestScript;

    /**
     * クエスト失敗したときに実行するスクリプトを取得
     *
     * @return クエストを分類するカテゴリーを新規作成
     */
    public ScriptSetting getFailedQuestScript() {
        return failedQuestScript;
    }

    /**
     * クエスト失敗したときに実行するスクリプトを設定
     *
     * @param failedQuestScript クエストを分類するカテゴリーを新規作成
     */
    public void setFailedQuestScript(ScriptSetting failedQuestScript) {
        this.failedQuestScript = failedQuestScript;
    }

    /**
     * クエスト失敗したときに実行するスクリプトを設定
     *
     * @param failedQuestScript クエストを分類するカテゴリーを新規作成
     * @return this
     */
    public CreateNamespaceRequest withFailedQuestScript(ScriptSetting failedQuestScript) {
        setFailedQuestScript(failedQuestScript);
        return this;
    }

    /** 報酬付与処理をジョブとして追加するキューのネームスペース のGRN */
    private String queueNamespaceId;

    /**
     * 報酬付与処理をジョブとして追加するキューのネームスペース のGRNを取得
     *
     * @return クエストを分類するカテゴリーを新規作成
     */
    public String getQueueNamespaceId() {
        return queueNamespaceId;
    }

    /**
     * 報酬付与処理をジョブとして追加するキューのネームスペース のGRNを設定
     *
     * @param queueNamespaceId クエストを分類するカテゴリーを新規作成
     */
    public void setQueueNamespaceId(String queueNamespaceId) {
        this.queueNamespaceId = queueNamespaceId;
    }

    /**
     * 報酬付与処理をジョブとして追加するキューのネームスペース のGRNを設定
     *
     * @param queueNamespaceId クエストを分類するカテゴリーを新規作成
     * @return this
     */
    public CreateNamespaceRequest withQueueNamespaceId(String queueNamespaceId) {
        setQueueNamespaceId(queueNamespaceId);
        return this;
    }

    /** 報酬付与処理のスタンプシートで使用する暗号鍵GRN */
    private String keyId;

    /**
     * 報酬付与処理のスタンプシートで使用する暗号鍵GRNを取得
     *
     * @return クエストを分類するカテゴリーを新規作成
     */
    public String getKeyId() {
        return keyId;
    }

    /**
     * 報酬付与処理のスタンプシートで使用する暗号鍵GRNを設定
     *
     * @param keyId クエストを分類するカテゴリーを新規作成
     */
    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    /**
     * 報酬付与処理のスタンプシートで使用する暗号鍵GRNを設定
     *
     * @param keyId クエストを分類するカテゴリーを新規作成
     * @return this
     */
    public CreateNamespaceRequest withKeyId(String keyId) {
        setKeyId(keyId);
        return this;
    }

    /** ログの出力設定 */
    private LogSetting logSetting;

    /**
     * ログの出力設定を取得
     *
     * @return クエストを分類するカテゴリーを新規作成
     */
    public LogSetting getLogSetting() {
        return logSetting;
    }

    /**
     * ログの出力設定を設定
     *
     * @param logSetting クエストを分類するカテゴリーを新規作成
     */
    public void setLogSetting(LogSetting logSetting) {
        this.logSetting = logSetting;
    }

    /**
     * ログの出力設定を設定
     *
     * @param logSetting クエストを分類するカテゴリーを新規作成
     * @return this
     */
    public CreateNamespaceRequest withLogSetting(LogSetting logSetting) {
        setLogSetting(logSetting);
        return this;
    }

}
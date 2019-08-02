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
 * クエストを分類するカテゴリーを更新 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateNamespaceRequest extends Gs2BasicRequest<UpdateNamespaceRequest> {

    /** カテゴリ名 */
    private String namespaceName;

    /**
     * カテゴリ名を取得
     *
     * @return クエストを分類するカテゴリーを更新
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * カテゴリ名を設定
     *
     * @param namespaceName クエストを分類するカテゴリーを更新
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * カテゴリ名を設定
     *
     * @param namespaceName クエストを分類するカテゴリーを更新
     * @return this
     */
    public UpdateNamespaceRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ネームスペースの説明 */
    private String description;

    /**
     * ネームスペースの説明を取得
     *
     * @return クエストを分類するカテゴリーを更新
     */
    public String getDescription() {
        return description;
    }

    /**
     * ネームスペースの説明を設定
     *
     * @param description クエストを分類するカテゴリーを更新
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * ネームスペースの説明を設定
     *
     * @param description クエストを分類するカテゴリーを更新
     * @return this
     */
    public UpdateNamespaceRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** クエスト開始時 に実行されるスクリプト のGRN */
    private String startQuestTriggerScriptId;

    /**
     * クエスト開始時 に実行されるスクリプト のGRNを取得
     *
     * @return クエストを分類するカテゴリーを更新
     */
    public String getStartQuestTriggerScriptId() {
        return startQuestTriggerScriptId;
    }

    /**
     * クエスト開始時 に実行されるスクリプト のGRNを設定
     *
     * @param startQuestTriggerScriptId クエストを分類するカテゴリーを更新
     */
    public void setStartQuestTriggerScriptId(String startQuestTriggerScriptId) {
        this.startQuestTriggerScriptId = startQuestTriggerScriptId;
    }

    /**
     * クエスト開始時 に実行されるスクリプト のGRNを設定
     *
     * @param startQuestTriggerScriptId クエストを分類するカテゴリーを更新
     * @return this
     */
    public UpdateNamespaceRequest withStartQuestTriggerScriptId(String startQuestTriggerScriptId) {
        setStartQuestTriggerScriptId(startQuestTriggerScriptId);
        return this;
    }

    /** クエスト開始完了時 に実行されるスクリプト のGRN */
    private String startQuestDoneTriggerScriptId;

    /**
     * クエスト開始完了時 に実行されるスクリプト のGRNを取得
     *
     * @return クエストを分類するカテゴリーを更新
     */
    public String getStartQuestDoneTriggerScriptId() {
        return startQuestDoneTriggerScriptId;
    }

    /**
     * クエスト開始完了時 に実行されるスクリプト のGRNを設定
     *
     * @param startQuestDoneTriggerScriptId クエストを分類するカテゴリーを更新
     */
    public void setStartQuestDoneTriggerScriptId(String startQuestDoneTriggerScriptId) {
        this.startQuestDoneTriggerScriptId = startQuestDoneTriggerScriptId;
    }

    /**
     * クエスト開始完了時 に実行されるスクリプト のGRNを設定
     *
     * @param startQuestDoneTriggerScriptId クエストを分類するカテゴリーを更新
     * @return this
     */
    public UpdateNamespaceRequest withStartQuestDoneTriggerScriptId(String startQuestDoneTriggerScriptId) {
        setStartQuestDoneTriggerScriptId(startQuestDoneTriggerScriptId);
        return this;
    }

    /** クエスト開始完了時 に通知するジョブを追加するキューのネームスペース のGRN */
    private String startQuestDoneTriggerQueueNamespaceId;

    /**
     * クエスト開始完了時 に通知するジョブを追加するキューのネームスペース のGRNを取得
     *
     * @return クエストを分類するカテゴリーを更新
     */
    public String getStartQuestDoneTriggerQueueNamespaceId() {
        return startQuestDoneTriggerQueueNamespaceId;
    }

    /**
     * クエスト開始完了時 に通知するジョブを追加するキューのネームスペース のGRNを設定
     *
     * @param startQuestDoneTriggerQueueNamespaceId クエストを分類するカテゴリーを更新
     */
    public void setStartQuestDoneTriggerQueueNamespaceId(String startQuestDoneTriggerQueueNamespaceId) {
        this.startQuestDoneTriggerQueueNamespaceId = startQuestDoneTriggerQueueNamespaceId;
    }

    /**
     * クエスト開始完了時 に通知するジョブを追加するキューのネームスペース のGRNを設定
     *
     * @param startQuestDoneTriggerQueueNamespaceId クエストを分類するカテゴリーを更新
     * @return this
     */
    public UpdateNamespaceRequest withStartQuestDoneTriggerQueueNamespaceId(String startQuestDoneTriggerQueueNamespaceId) {
        setStartQuestDoneTriggerQueueNamespaceId(startQuestDoneTriggerQueueNamespaceId);
        return this;
    }

    /** クエストクリア時 に実行されるスクリプト のGRN */
    private String completeQuestTriggerScriptId;

    /**
     * クエストクリア時 に実行されるスクリプト のGRNを取得
     *
     * @return クエストを分類するカテゴリーを更新
     */
    public String getCompleteQuestTriggerScriptId() {
        return completeQuestTriggerScriptId;
    }

    /**
     * クエストクリア時 に実行されるスクリプト のGRNを設定
     *
     * @param completeQuestTriggerScriptId クエストを分類するカテゴリーを更新
     */
    public void setCompleteQuestTriggerScriptId(String completeQuestTriggerScriptId) {
        this.completeQuestTriggerScriptId = completeQuestTriggerScriptId;
    }

    /**
     * クエストクリア時 に実行されるスクリプト のGRNを設定
     *
     * @param completeQuestTriggerScriptId クエストを分類するカテゴリーを更新
     * @return this
     */
    public UpdateNamespaceRequest withCompleteQuestTriggerScriptId(String completeQuestTriggerScriptId) {
        setCompleteQuestTriggerScriptId(completeQuestTriggerScriptId);
        return this;
    }

    /** クエストクリア完了時 に実行されるスクリプト のGRN */
    private String completeQuestDoneTriggerScriptId;

    /**
     * クエストクリア完了時 に実行されるスクリプト のGRNを取得
     *
     * @return クエストを分類するカテゴリーを更新
     */
    public String getCompleteQuestDoneTriggerScriptId() {
        return completeQuestDoneTriggerScriptId;
    }

    /**
     * クエストクリア完了時 に実行されるスクリプト のGRNを設定
     *
     * @param completeQuestDoneTriggerScriptId クエストを分類するカテゴリーを更新
     */
    public void setCompleteQuestDoneTriggerScriptId(String completeQuestDoneTriggerScriptId) {
        this.completeQuestDoneTriggerScriptId = completeQuestDoneTriggerScriptId;
    }

    /**
     * クエストクリア完了時 に実行されるスクリプト のGRNを設定
     *
     * @param completeQuestDoneTriggerScriptId クエストを分類するカテゴリーを更新
     * @return this
     */
    public UpdateNamespaceRequest withCompleteQuestDoneTriggerScriptId(String completeQuestDoneTriggerScriptId) {
        setCompleteQuestDoneTriggerScriptId(completeQuestDoneTriggerScriptId);
        return this;
    }

    /** クエストクリア完了時 に通知するジョブを追加するキューのネームスペース のGRN */
    private String completeQuestDoneTriggerQueueNamespaceId;

    /**
     * クエストクリア完了時 に通知するジョブを追加するキューのネームスペース のGRNを取得
     *
     * @return クエストを分類するカテゴリーを更新
     */
    public String getCompleteQuestDoneTriggerQueueNamespaceId() {
        return completeQuestDoneTriggerQueueNamespaceId;
    }

    /**
     * クエストクリア完了時 に通知するジョブを追加するキューのネームスペース のGRNを設定
     *
     * @param completeQuestDoneTriggerQueueNamespaceId クエストを分類するカテゴリーを更新
     */
    public void setCompleteQuestDoneTriggerQueueNamespaceId(String completeQuestDoneTriggerQueueNamespaceId) {
        this.completeQuestDoneTriggerQueueNamespaceId = completeQuestDoneTriggerQueueNamespaceId;
    }

    /**
     * クエストクリア完了時 に通知するジョブを追加するキューのネームスペース のGRNを設定
     *
     * @param completeQuestDoneTriggerQueueNamespaceId クエストを分類するカテゴリーを更新
     * @return this
     */
    public UpdateNamespaceRequest withCompleteQuestDoneTriggerQueueNamespaceId(String completeQuestDoneTriggerQueueNamespaceId) {
        setCompleteQuestDoneTriggerQueueNamespaceId(completeQuestDoneTriggerQueueNamespaceId);
        return this;
    }

    /** クエスト失敗時 に実行されるスクリプト のGRN */
    private String failedQuestTriggerScriptId;

    /**
     * クエスト失敗時 に実行されるスクリプト のGRNを取得
     *
     * @return クエストを分類するカテゴリーを更新
     */
    public String getFailedQuestTriggerScriptId() {
        return failedQuestTriggerScriptId;
    }

    /**
     * クエスト失敗時 に実行されるスクリプト のGRNを設定
     *
     * @param failedQuestTriggerScriptId クエストを分類するカテゴリーを更新
     */
    public void setFailedQuestTriggerScriptId(String failedQuestTriggerScriptId) {
        this.failedQuestTriggerScriptId = failedQuestTriggerScriptId;
    }

    /**
     * クエスト失敗時 に実行されるスクリプト のGRNを設定
     *
     * @param failedQuestTriggerScriptId クエストを分類するカテゴリーを更新
     * @return this
     */
    public UpdateNamespaceRequest withFailedQuestTriggerScriptId(String failedQuestTriggerScriptId) {
        setFailedQuestTriggerScriptId(failedQuestTriggerScriptId);
        return this;
    }

    /** クエスト失敗完了時 に実行されるスクリプト のGRN */
    private String failedQuestDoneTriggerScriptId;

    /**
     * クエスト失敗完了時 に実行されるスクリプト のGRNを取得
     *
     * @return クエストを分類するカテゴリーを更新
     */
    public String getFailedQuestDoneTriggerScriptId() {
        return failedQuestDoneTriggerScriptId;
    }

    /**
     * クエスト失敗完了時 に実行されるスクリプト のGRNを設定
     *
     * @param failedQuestDoneTriggerScriptId クエストを分類するカテゴリーを更新
     */
    public void setFailedQuestDoneTriggerScriptId(String failedQuestDoneTriggerScriptId) {
        this.failedQuestDoneTriggerScriptId = failedQuestDoneTriggerScriptId;
    }

    /**
     * クエスト失敗完了時 に実行されるスクリプト のGRNを設定
     *
     * @param failedQuestDoneTriggerScriptId クエストを分類するカテゴリーを更新
     * @return this
     */
    public UpdateNamespaceRequest withFailedQuestDoneTriggerScriptId(String failedQuestDoneTriggerScriptId) {
        setFailedQuestDoneTriggerScriptId(failedQuestDoneTriggerScriptId);
        return this;
    }

    /** クエスト失敗完了時 に通知するジョブを追加するキューのネームスペース のGRN */
    private String failedQuestDoneTriggerQueueNamespaceId;

    /**
     * クエスト失敗完了時 に通知するジョブを追加するキューのネームスペース のGRNを取得
     *
     * @return クエストを分類するカテゴリーを更新
     */
    public String getFailedQuestDoneTriggerQueueNamespaceId() {
        return failedQuestDoneTriggerQueueNamespaceId;
    }

    /**
     * クエスト失敗完了時 に通知するジョブを追加するキューのネームスペース のGRNを設定
     *
     * @param failedQuestDoneTriggerQueueNamespaceId クエストを分類するカテゴリーを更新
     */
    public void setFailedQuestDoneTriggerQueueNamespaceId(String failedQuestDoneTriggerQueueNamespaceId) {
        this.failedQuestDoneTriggerQueueNamespaceId = failedQuestDoneTriggerQueueNamespaceId;
    }

    /**
     * クエスト失敗完了時 に通知するジョブを追加するキューのネームスペース のGRNを設定
     *
     * @param failedQuestDoneTriggerQueueNamespaceId クエストを分類するカテゴリーを更新
     * @return this
     */
    public UpdateNamespaceRequest withFailedQuestDoneTriggerQueueNamespaceId(String failedQuestDoneTriggerQueueNamespaceId) {
        setFailedQuestDoneTriggerQueueNamespaceId(failedQuestDoneTriggerQueueNamespaceId);
        return this;
    }

    /** 報酬付与処理をジョブとして追加するキューのネームスペース のGRN */
    private String queueNamespaceId;

    /**
     * 報酬付与処理をジョブとして追加するキューのネームスペース のGRNを取得
     *
     * @return クエストを分類するカテゴリーを更新
     */
    public String getQueueNamespaceId() {
        return queueNamespaceId;
    }

    /**
     * 報酬付与処理をジョブとして追加するキューのネームスペース のGRNを設定
     *
     * @param queueNamespaceId クエストを分類するカテゴリーを更新
     */
    public void setQueueNamespaceId(String queueNamespaceId) {
        this.queueNamespaceId = queueNamespaceId;
    }

    /**
     * 報酬付与処理をジョブとして追加するキューのネームスペース のGRNを設定
     *
     * @param queueNamespaceId クエストを分類するカテゴリーを更新
     * @return this
     */
    public UpdateNamespaceRequest withQueueNamespaceId(String queueNamespaceId) {
        setQueueNamespaceId(queueNamespaceId);
        return this;
    }

    /** 報酬付与処理のスタンプシートで使用する暗号鍵GRN */
    private String keyId;

    /**
     * 報酬付与処理のスタンプシートで使用する暗号鍵GRNを取得
     *
     * @return クエストを分類するカテゴリーを更新
     */
    public String getKeyId() {
        return keyId;
    }

    /**
     * 報酬付与処理のスタンプシートで使用する暗号鍵GRNを設定
     *
     * @param keyId クエストを分類するカテゴリーを更新
     */
    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    /**
     * 報酬付与処理のスタンプシートで使用する暗号鍵GRNを設定
     *
     * @param keyId クエストを分類するカテゴリーを更新
     * @return this
     */
    public UpdateNamespaceRequest withKeyId(String keyId) {
        setKeyId(keyId);
        return this;
    }

}
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

package io.gs2.quest.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.model.IModel;

/**
 * クエストを分類するカテゴリー
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Namespace implements IModel, Serializable, Comparable<Namespace> {
	/** クエストを分類するカテゴリー */
	protected String namespaceId;

	/**
	 * クエストを分類するカテゴリーを取得
	 *
	 * @return クエストを分類するカテゴリー
	 */
	public String getNamespaceId() {
		return namespaceId;
	}

	/**
	 * クエストを分類するカテゴリーを設定
	 *
	 * @param namespaceId クエストを分類するカテゴリー
	 */
	public void setNamespaceId(String namespaceId) {
		this.namespaceId = namespaceId;
	}

	/**
	 * クエストを分類するカテゴリーを設定
	 *
	 * @param namespaceId クエストを分類するカテゴリー
	 * @return this
	 */
	public Namespace withNamespaceId(String namespaceId) {
		this.namespaceId = namespaceId;
		return this;
	}
	/** オーナーID */
	protected String ownerId;

	/**
	 * オーナーIDを取得
	 *
	 * @return オーナーID
	 */
	public String getOwnerId() {
		return ownerId;
	}

	/**
	 * オーナーIDを設定
	 *
	 * @param ownerId オーナーID
	 */
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * オーナーIDを設定
	 *
	 * @param ownerId オーナーID
	 * @return this
	 */
	public Namespace withOwnerId(String ownerId) {
		this.ownerId = ownerId;
		return this;
	}
	/** カテゴリ名 */
	protected String name;

	/**
	 * カテゴリ名を取得
	 *
	 * @return カテゴリ名
	 */
	public String getName() {
		return name;
	}

	/**
	 * カテゴリ名を設定
	 *
	 * @param name カテゴリ名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * カテゴリ名を設定
	 *
	 * @param name カテゴリ名
	 * @return this
	 */
	public Namespace withName(String name) {
		this.name = name;
		return this;
	}
	/** ネームスペースの説明 */
	protected String description;

	/**
	 * ネームスペースの説明を取得
	 *
	 * @return ネームスペースの説明
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * ネームスペースの説明を設定
	 *
	 * @param description ネームスペースの説明
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * ネームスペースの説明を設定
	 *
	 * @param description ネームスペースの説明
	 * @return this
	 */
	public Namespace withDescription(String description) {
		this.description = description;
		return this;
	}
	/** クエスト開始時 に実行されるスクリプト のGRN */
	protected String startQuestTriggerScriptId;

	/**
	 * クエスト開始時 に実行されるスクリプト のGRNを取得
	 *
	 * @return クエスト開始時 に実行されるスクリプト のGRN
	 */
	public String getStartQuestTriggerScriptId() {
		return startQuestTriggerScriptId;
	}

	/**
	 * クエスト開始時 に実行されるスクリプト のGRNを設定
	 *
	 * @param startQuestTriggerScriptId クエスト開始時 に実行されるスクリプト のGRN
	 */
	public void setStartQuestTriggerScriptId(String startQuestTriggerScriptId) {
		this.startQuestTriggerScriptId = startQuestTriggerScriptId;
	}

	/**
	 * クエスト開始時 に実行されるスクリプト のGRNを設定
	 *
	 * @param startQuestTriggerScriptId クエスト開始時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withStartQuestTriggerScriptId(String startQuestTriggerScriptId) {
		this.startQuestTriggerScriptId = startQuestTriggerScriptId;
		return this;
	}
	/** クエスト開始完了時 に実行されるスクリプト のGRN */
	protected String startQuestDoneTriggerScriptId;

	/**
	 * クエスト開始完了時 に実行されるスクリプト のGRNを取得
	 *
	 * @return クエスト開始完了時 に実行されるスクリプト のGRN
	 */
	public String getStartQuestDoneTriggerScriptId() {
		return startQuestDoneTriggerScriptId;
	}

	/**
	 * クエスト開始完了時 に実行されるスクリプト のGRNを設定
	 *
	 * @param startQuestDoneTriggerScriptId クエスト開始完了時 に実行されるスクリプト のGRN
	 */
	public void setStartQuestDoneTriggerScriptId(String startQuestDoneTriggerScriptId) {
		this.startQuestDoneTriggerScriptId = startQuestDoneTriggerScriptId;
	}

	/**
	 * クエスト開始完了時 に実行されるスクリプト のGRNを設定
	 *
	 * @param startQuestDoneTriggerScriptId クエスト開始完了時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withStartQuestDoneTriggerScriptId(String startQuestDoneTriggerScriptId) {
		this.startQuestDoneTriggerScriptId = startQuestDoneTriggerScriptId;
		return this;
	}
	/** クエスト開始完了時 に通知するジョブを追加するキューのネームスペース のGRN */
	protected String startQuestDoneTriggerQueueNamespaceId;

	/**
	 * クエスト開始完了時 に通知するジョブを追加するキューのネームスペース のGRNを取得
	 *
	 * @return クエスト開始完了時 に通知するジョブを追加するキューのネームスペース のGRN
	 */
	public String getStartQuestDoneTriggerQueueNamespaceId() {
		return startQuestDoneTriggerQueueNamespaceId;
	}

	/**
	 * クエスト開始完了時 に通知するジョブを追加するキューのネームスペース のGRNを設定
	 *
	 * @param startQuestDoneTriggerQueueNamespaceId クエスト開始完了時 に通知するジョブを追加するキューのネームスペース のGRN
	 */
	public void setStartQuestDoneTriggerQueueNamespaceId(String startQuestDoneTriggerQueueNamespaceId) {
		this.startQuestDoneTriggerQueueNamespaceId = startQuestDoneTriggerQueueNamespaceId;
	}

	/**
	 * クエスト開始完了時 に通知するジョブを追加するキューのネームスペース のGRNを設定
	 *
	 * @param startQuestDoneTriggerQueueNamespaceId クエスト開始完了時 に通知するジョブを追加するキューのネームスペース のGRN
	 * @return this
	 */
	public Namespace withStartQuestDoneTriggerQueueNamespaceId(String startQuestDoneTriggerQueueNamespaceId) {
		this.startQuestDoneTriggerQueueNamespaceId = startQuestDoneTriggerQueueNamespaceId;
		return this;
	}
	/** クエストクリア時 に実行されるスクリプト のGRN */
	protected String completeQuestTriggerScriptId;

	/**
	 * クエストクリア時 に実行されるスクリプト のGRNを取得
	 *
	 * @return クエストクリア時 に実行されるスクリプト のGRN
	 */
	public String getCompleteQuestTriggerScriptId() {
		return completeQuestTriggerScriptId;
	}

	/**
	 * クエストクリア時 に実行されるスクリプト のGRNを設定
	 *
	 * @param completeQuestTriggerScriptId クエストクリア時 に実行されるスクリプト のGRN
	 */
	public void setCompleteQuestTriggerScriptId(String completeQuestTriggerScriptId) {
		this.completeQuestTriggerScriptId = completeQuestTriggerScriptId;
	}

	/**
	 * クエストクリア時 に実行されるスクリプト のGRNを設定
	 *
	 * @param completeQuestTriggerScriptId クエストクリア時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withCompleteQuestTriggerScriptId(String completeQuestTriggerScriptId) {
		this.completeQuestTriggerScriptId = completeQuestTriggerScriptId;
		return this;
	}
	/** クエストクリア完了時 に実行されるスクリプト のGRN */
	protected String completeQuestDoneTriggerScriptId;

	/**
	 * クエストクリア完了時 に実行されるスクリプト のGRNを取得
	 *
	 * @return クエストクリア完了時 に実行されるスクリプト のGRN
	 */
	public String getCompleteQuestDoneTriggerScriptId() {
		return completeQuestDoneTriggerScriptId;
	}

	/**
	 * クエストクリア完了時 に実行されるスクリプト のGRNを設定
	 *
	 * @param completeQuestDoneTriggerScriptId クエストクリア完了時 に実行されるスクリプト のGRN
	 */
	public void setCompleteQuestDoneTriggerScriptId(String completeQuestDoneTriggerScriptId) {
		this.completeQuestDoneTriggerScriptId = completeQuestDoneTriggerScriptId;
	}

	/**
	 * クエストクリア完了時 に実行されるスクリプト のGRNを設定
	 *
	 * @param completeQuestDoneTriggerScriptId クエストクリア完了時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withCompleteQuestDoneTriggerScriptId(String completeQuestDoneTriggerScriptId) {
		this.completeQuestDoneTriggerScriptId = completeQuestDoneTriggerScriptId;
		return this;
	}
	/** クエストクリア完了時 に通知するジョブを追加するキューのネームスペース のGRN */
	protected String completeQuestDoneTriggerQueueNamespaceId;

	/**
	 * クエストクリア完了時 に通知するジョブを追加するキューのネームスペース のGRNを取得
	 *
	 * @return クエストクリア完了時 に通知するジョブを追加するキューのネームスペース のGRN
	 */
	public String getCompleteQuestDoneTriggerQueueNamespaceId() {
		return completeQuestDoneTriggerQueueNamespaceId;
	}

	/**
	 * クエストクリア完了時 に通知するジョブを追加するキューのネームスペース のGRNを設定
	 *
	 * @param completeQuestDoneTriggerQueueNamespaceId クエストクリア完了時 に通知するジョブを追加するキューのネームスペース のGRN
	 */
	public void setCompleteQuestDoneTriggerQueueNamespaceId(String completeQuestDoneTriggerQueueNamespaceId) {
		this.completeQuestDoneTriggerQueueNamespaceId = completeQuestDoneTriggerQueueNamespaceId;
	}

	/**
	 * クエストクリア完了時 に通知するジョブを追加するキューのネームスペース のGRNを設定
	 *
	 * @param completeQuestDoneTriggerQueueNamespaceId クエストクリア完了時 に通知するジョブを追加するキューのネームスペース のGRN
	 * @return this
	 */
	public Namespace withCompleteQuestDoneTriggerQueueNamespaceId(String completeQuestDoneTriggerQueueNamespaceId) {
		this.completeQuestDoneTriggerQueueNamespaceId = completeQuestDoneTriggerQueueNamespaceId;
		return this;
	}
	/** クエスト失敗時 に実行されるスクリプト のGRN */
	protected String failedQuestTriggerScriptId;

	/**
	 * クエスト失敗時 に実行されるスクリプト のGRNを取得
	 *
	 * @return クエスト失敗時 に実行されるスクリプト のGRN
	 */
	public String getFailedQuestTriggerScriptId() {
		return failedQuestTriggerScriptId;
	}

	/**
	 * クエスト失敗時 に実行されるスクリプト のGRNを設定
	 *
	 * @param failedQuestTriggerScriptId クエスト失敗時 に実行されるスクリプト のGRN
	 */
	public void setFailedQuestTriggerScriptId(String failedQuestTriggerScriptId) {
		this.failedQuestTriggerScriptId = failedQuestTriggerScriptId;
	}

	/**
	 * クエスト失敗時 に実行されるスクリプト のGRNを設定
	 *
	 * @param failedQuestTriggerScriptId クエスト失敗時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withFailedQuestTriggerScriptId(String failedQuestTriggerScriptId) {
		this.failedQuestTriggerScriptId = failedQuestTriggerScriptId;
		return this;
	}
	/** クエスト失敗完了時 に実行されるスクリプト のGRN */
	protected String failedQuestDoneTriggerScriptId;

	/**
	 * クエスト失敗完了時 に実行されるスクリプト のGRNを取得
	 *
	 * @return クエスト失敗完了時 に実行されるスクリプト のGRN
	 */
	public String getFailedQuestDoneTriggerScriptId() {
		return failedQuestDoneTriggerScriptId;
	}

	/**
	 * クエスト失敗完了時 に実行されるスクリプト のGRNを設定
	 *
	 * @param failedQuestDoneTriggerScriptId クエスト失敗完了時 に実行されるスクリプト のGRN
	 */
	public void setFailedQuestDoneTriggerScriptId(String failedQuestDoneTriggerScriptId) {
		this.failedQuestDoneTriggerScriptId = failedQuestDoneTriggerScriptId;
	}

	/**
	 * クエスト失敗完了時 に実行されるスクリプト のGRNを設定
	 *
	 * @param failedQuestDoneTriggerScriptId クエスト失敗完了時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withFailedQuestDoneTriggerScriptId(String failedQuestDoneTriggerScriptId) {
		this.failedQuestDoneTriggerScriptId = failedQuestDoneTriggerScriptId;
		return this;
	}
	/** クエスト失敗完了時 に通知するジョブを追加するキューのネームスペース のGRN */
	protected String failedQuestDoneTriggerQueueNamespaceId;

	/**
	 * クエスト失敗完了時 に通知するジョブを追加するキューのネームスペース のGRNを取得
	 *
	 * @return クエスト失敗完了時 に通知するジョブを追加するキューのネームスペース のGRN
	 */
	public String getFailedQuestDoneTriggerQueueNamespaceId() {
		return failedQuestDoneTriggerQueueNamespaceId;
	}

	/**
	 * クエスト失敗完了時 に通知するジョブを追加するキューのネームスペース のGRNを設定
	 *
	 * @param failedQuestDoneTriggerQueueNamespaceId クエスト失敗完了時 に通知するジョブを追加するキューのネームスペース のGRN
	 */
	public void setFailedQuestDoneTriggerQueueNamespaceId(String failedQuestDoneTriggerQueueNamespaceId) {
		this.failedQuestDoneTriggerQueueNamespaceId = failedQuestDoneTriggerQueueNamespaceId;
	}

	/**
	 * クエスト失敗完了時 に通知するジョブを追加するキューのネームスペース のGRNを設定
	 *
	 * @param failedQuestDoneTriggerQueueNamespaceId クエスト失敗完了時 に通知するジョブを追加するキューのネームスペース のGRN
	 * @return this
	 */
	public Namespace withFailedQuestDoneTriggerQueueNamespaceId(String failedQuestDoneTriggerQueueNamespaceId) {
		this.failedQuestDoneTriggerQueueNamespaceId = failedQuestDoneTriggerQueueNamespaceId;
		return this;
	}
	/** 報酬付与処理をジョブとして追加するキューのネームスペース のGRN */
	protected String queueNamespaceId;

	/**
	 * 報酬付与処理をジョブとして追加するキューのネームスペース のGRNを取得
	 *
	 * @return 報酬付与処理をジョブとして追加するキューのネームスペース のGRN
	 */
	public String getQueueNamespaceId() {
		return queueNamespaceId;
	}

	/**
	 * 報酬付与処理をジョブとして追加するキューのネームスペース のGRNを設定
	 *
	 * @param queueNamespaceId 報酬付与処理をジョブとして追加するキューのネームスペース のGRN
	 */
	public void setQueueNamespaceId(String queueNamespaceId) {
		this.queueNamespaceId = queueNamespaceId;
	}

	/**
	 * 報酬付与処理をジョブとして追加するキューのネームスペース のGRNを設定
	 *
	 * @param queueNamespaceId 報酬付与処理をジョブとして追加するキューのネームスペース のGRN
	 * @return this
	 */
	public Namespace withQueueNamespaceId(String queueNamespaceId) {
		this.queueNamespaceId = queueNamespaceId;
		return this;
	}
	/** 報酬付与処理のスタンプシートで使用する暗号鍵GRN */
	protected String keyId;

	/**
	 * 報酬付与処理のスタンプシートで使用する暗号鍵GRNを取得
	 *
	 * @return 報酬付与処理のスタンプシートで使用する暗号鍵GRN
	 */
	public String getKeyId() {
		return keyId;
	}

	/**
	 * 報酬付与処理のスタンプシートで使用する暗号鍵GRNを設定
	 *
	 * @param keyId 報酬付与処理のスタンプシートで使用する暗号鍵GRN
	 */
	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

	/**
	 * 報酬付与処理のスタンプシートで使用する暗号鍵GRNを設定
	 *
	 * @param keyId 報酬付与処理のスタンプシートで使用する暗号鍵GRN
	 * @return this
	 */
	public Namespace withKeyId(String keyId) {
		this.keyId = keyId;
		return this;
	}
	/** 作成日時 */
	protected Long createdAt;

	/**
	 * 作成日時を取得
	 *
	 * @return 作成日時
	 */
	public Long getCreatedAt() {
		return createdAt;
	}

	/**
	 * 作成日時を設定
	 *
	 * @param createdAt 作成日時
	 */
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * 作成日時を設定
	 *
	 * @param createdAt 作成日時
	 * @return this
	 */
	public Namespace withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	/** 最終更新日時 */
	protected Long updatedAt;

	/**
	 * 最終更新日時を取得
	 *
	 * @return 最終更新日時
	 */
	public Long getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * 最終更新日時を設定
	 *
	 * @param updatedAt 最終更新日時
	 */
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * 最終更新日時を設定
	 *
	 * @param updatedAt 最終更新日時
	 * @return this
	 */
	public Namespace withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("namespaceId", this.getNamespaceId())
            .put("ownerId", this.getOwnerId())
            .put("name", this.getName())
            .put("description", this.getDescription())
            .put("startQuestTriggerScriptId", this.getStartQuestTriggerScriptId())
            .put("startQuestDoneTriggerScriptId", this.getStartQuestDoneTriggerScriptId())
            .put("startQuestDoneTriggerQueueNamespaceId", this.getStartQuestDoneTriggerQueueNamespaceId())
            .put("completeQuestTriggerScriptId", this.getCompleteQuestTriggerScriptId())
            .put("completeQuestDoneTriggerScriptId", this.getCompleteQuestDoneTriggerScriptId())
            .put("completeQuestDoneTriggerQueueNamespaceId", this.getCompleteQuestDoneTriggerQueueNamespaceId())
            .put("failedQuestTriggerScriptId", this.getFailedQuestTriggerScriptId())
            .put("failedQuestDoneTriggerScriptId", this.getFailedQuestDoneTriggerScriptId())
            .put("failedQuestDoneTriggerQueueNamespaceId", this.getFailedQuestDoneTriggerQueueNamespaceId())
            .put("queueNamespaceId", this.getQueueNamespaceId())
            .put("keyId", this.getKeyId())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        return body_;
    }
	@Override
	public int compareTo(Namespace o) {
		return namespaceId.compareTo(o.namespaceId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.namespaceId == null) ? 0 : this.namespaceId.hashCode());
        result = prime * result + ((this.ownerId == null) ? 0 : this.ownerId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.startQuestTriggerScriptId == null) ? 0 : this.startQuestTriggerScriptId.hashCode());
        result = prime * result + ((this.startQuestDoneTriggerScriptId == null) ? 0 : this.startQuestDoneTriggerScriptId.hashCode());
        result = prime * result + ((this.startQuestDoneTriggerQueueNamespaceId == null) ? 0 : this.startQuestDoneTriggerQueueNamespaceId.hashCode());
        result = prime * result + ((this.completeQuestTriggerScriptId == null) ? 0 : this.completeQuestTriggerScriptId.hashCode());
        result = prime * result + ((this.completeQuestDoneTriggerScriptId == null) ? 0 : this.completeQuestDoneTriggerScriptId.hashCode());
        result = prime * result + ((this.completeQuestDoneTriggerQueueNamespaceId == null) ? 0 : this.completeQuestDoneTriggerQueueNamespaceId.hashCode());
        result = prime * result + ((this.failedQuestTriggerScriptId == null) ? 0 : this.failedQuestTriggerScriptId.hashCode());
        result = prime * result + ((this.failedQuestDoneTriggerScriptId == null) ? 0 : this.failedQuestDoneTriggerScriptId.hashCode());
        result = prime * result + ((this.failedQuestDoneTriggerQueueNamespaceId == null) ? 0 : this.failedQuestDoneTriggerQueueNamespaceId.hashCode());
        result = prime * result + ((this.queueNamespaceId == null) ? 0 : this.queueNamespaceId.hashCode());
        result = prime * result + ((this.keyId == null) ? 0 : this.keyId.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		Namespace other = (Namespace) o;
		if (namespaceId == null) {
			return other.namespaceId == null;
		} else if (!namespaceId.equals(other.namespaceId)) {
			return false;
		}
		if (ownerId == null) {
			return other.ownerId == null;
		} else if (!ownerId.equals(other.ownerId)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (description == null) {
			return other.description == null;
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (startQuestTriggerScriptId == null) {
			return other.startQuestTriggerScriptId == null;
		} else if (!startQuestTriggerScriptId.equals(other.startQuestTriggerScriptId)) {
			return false;
		}
		if (startQuestDoneTriggerScriptId == null) {
			return other.startQuestDoneTriggerScriptId == null;
		} else if (!startQuestDoneTriggerScriptId.equals(other.startQuestDoneTriggerScriptId)) {
			return false;
		}
		if (startQuestDoneTriggerQueueNamespaceId == null) {
			return other.startQuestDoneTriggerQueueNamespaceId == null;
		} else if (!startQuestDoneTriggerQueueNamespaceId.equals(other.startQuestDoneTriggerQueueNamespaceId)) {
			return false;
		}
		if (completeQuestTriggerScriptId == null) {
			return other.completeQuestTriggerScriptId == null;
		} else if (!completeQuestTriggerScriptId.equals(other.completeQuestTriggerScriptId)) {
			return false;
		}
		if (completeQuestDoneTriggerScriptId == null) {
			return other.completeQuestDoneTriggerScriptId == null;
		} else if (!completeQuestDoneTriggerScriptId.equals(other.completeQuestDoneTriggerScriptId)) {
			return false;
		}
		if (completeQuestDoneTriggerQueueNamespaceId == null) {
			return other.completeQuestDoneTriggerQueueNamespaceId == null;
		} else if (!completeQuestDoneTriggerQueueNamespaceId.equals(other.completeQuestDoneTriggerQueueNamespaceId)) {
			return false;
		}
		if (failedQuestTriggerScriptId == null) {
			return other.failedQuestTriggerScriptId == null;
		} else if (!failedQuestTriggerScriptId.equals(other.failedQuestTriggerScriptId)) {
			return false;
		}
		if (failedQuestDoneTriggerScriptId == null) {
			return other.failedQuestDoneTriggerScriptId == null;
		} else if (!failedQuestDoneTriggerScriptId.equals(other.failedQuestDoneTriggerScriptId)) {
			return false;
		}
		if (failedQuestDoneTriggerQueueNamespaceId == null) {
			return other.failedQuestDoneTriggerQueueNamespaceId == null;
		} else if (!failedQuestDoneTriggerQueueNamespaceId.equals(other.failedQuestDoneTriggerQueueNamespaceId)) {
			return false;
		}
		if (queueNamespaceId == null) {
			return other.queueNamespaceId == null;
		} else if (!queueNamespaceId.equals(other.queueNamespaceId)) {
			return false;
		}
		if (keyId == null) {
			return other.keyId == null;
		} else if (!keyId.equals(other.keyId)) {
			return false;
		}
		if (createdAt == null) {
			return other.createdAt == null;
		} else if (!createdAt.equals(other.createdAt)) {
			return false;
		}
		if (updatedAt == null) {
			return other.updatedAt == null;
		} else if (!updatedAt.equals(other.updatedAt)) {
			return false;
		}
		return true;
	}
}
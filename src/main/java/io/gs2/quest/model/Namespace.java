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
import io.gs2.core.model.IModel;

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
	/** クエスト開始したときに実行するスクリプト */
	protected ScriptSetting startQuestScript;

	/**
	 * クエスト開始したときに実行するスクリプトを取得
	 *
	 * @return クエスト開始したときに実行するスクリプト
	 */
	public ScriptSetting getStartQuestScript() {
		return startQuestScript;
	}

	/**
	 * クエスト開始したときに実行するスクリプトを設定
	 *
	 * @param startQuestScript クエスト開始したときに実行するスクリプト
	 */
	public void setStartQuestScript(ScriptSetting startQuestScript) {
		this.startQuestScript = startQuestScript;
	}

	/**
	 * クエスト開始したときに実行するスクリプトを設定
	 *
	 * @param startQuestScript クエスト開始したときに実行するスクリプト
	 * @return this
	 */
	public Namespace withStartQuestScript(ScriptSetting startQuestScript) {
		this.startQuestScript = startQuestScript;
		return this;
	}
	/** クエストクリアしたときに実行するスクリプト */
	protected ScriptSetting completeQuestScript;

	/**
	 * クエストクリアしたときに実行するスクリプトを取得
	 *
	 * @return クエストクリアしたときに実行するスクリプト
	 */
	public ScriptSetting getCompleteQuestScript() {
		return completeQuestScript;
	}

	/**
	 * クエストクリアしたときに実行するスクリプトを設定
	 *
	 * @param completeQuestScript クエストクリアしたときに実行するスクリプト
	 */
	public void setCompleteQuestScript(ScriptSetting completeQuestScript) {
		this.completeQuestScript = completeQuestScript;
	}

	/**
	 * クエストクリアしたときに実行するスクリプトを設定
	 *
	 * @param completeQuestScript クエストクリアしたときに実行するスクリプト
	 * @return this
	 */
	public Namespace withCompleteQuestScript(ScriptSetting completeQuestScript) {
		this.completeQuestScript = completeQuestScript;
		return this;
	}
	/** クエスト失敗したときに実行するスクリプト */
	protected ScriptSetting failedQuestScript;

	/**
	 * クエスト失敗したときに実行するスクリプトを取得
	 *
	 * @return クエスト失敗したときに実行するスクリプト
	 */
	public ScriptSetting getFailedQuestScript() {
		return failedQuestScript;
	}

	/**
	 * クエスト失敗したときに実行するスクリプトを設定
	 *
	 * @param failedQuestScript クエスト失敗したときに実行するスクリプト
	 */
	public void setFailedQuestScript(ScriptSetting failedQuestScript) {
		this.failedQuestScript = failedQuestScript;
	}

	/**
	 * クエスト失敗したときに実行するスクリプトを設定
	 *
	 * @param failedQuestScript クエスト失敗したときに実行するスクリプト
	 * @return this
	 */
	public Namespace withFailedQuestScript(ScriptSetting failedQuestScript) {
		this.failedQuestScript = failedQuestScript;
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
	/** ログの出力設定 */
	protected LogSetting logSetting;

	/**
	 * ログの出力設定を取得
	 *
	 * @return ログの出力設定
	 */
	public LogSetting getLogSetting() {
		return logSetting;
	}

	/**
	 * ログの出力設定を設定
	 *
	 * @param logSetting ログの出力設定
	 */
	public void setLogSetting(LogSetting logSetting) {
		this.logSetting = logSetting;
	}

	/**
	 * ログの出力設定を設定
	 *
	 * @param logSetting ログの出力設定
	 * @return this
	 */
	public Namespace withLogSetting(LogSetting logSetting) {
		this.logSetting = logSetting;
		return this;
	}
	/** None */
	protected String status;

	/**
	 * Noneを取得
	 *
	 * @return None
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Noneを設定
	 *
	 * @param status None
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Noneを設定
	 *
	 * @param status None
	 * @return this
	 */
	public Namespace withStatus(String status) {
		this.status = status;
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
        JsonNode startQuestScript = this.getStartQuestScript().toJson();
        JsonNode completeQuestScript = this.getCompleteQuestScript().toJson();
        JsonNode failedQuestScript = this.getFailedQuestScript().toJson();
        JsonNode logSetting = this.getLogSetting().toJson();
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("namespaceId", this.getNamespaceId())
            .put("ownerId", this.getOwnerId())
            .put("name", this.getName())
            .put("description", this.getDescription())
            .put("queueNamespaceId", this.getQueueNamespaceId())
            .put("keyId", this.getKeyId())
            .put("status", this.getStatus())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        body_.set("startQuestScript", startQuestScript);
        body_.set("completeQuestScript", completeQuestScript);
        body_.set("failedQuestScript", failedQuestScript);
        body_.set("logSetting", logSetting);
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
        result = prime * result + ((this.startQuestScript == null) ? 0 : this.startQuestScript.hashCode());
        result = prime * result + ((this.completeQuestScript == null) ? 0 : this.completeQuestScript.hashCode());
        result = prime * result + ((this.failedQuestScript == null) ? 0 : this.failedQuestScript.hashCode());
        result = prime * result + ((this.queueNamespaceId == null) ? 0 : this.queueNamespaceId.hashCode());
        result = prime * result + ((this.keyId == null) ? 0 : this.keyId.hashCode());
        result = prime * result + ((this.logSetting == null) ? 0 : this.logSetting.hashCode());
        result = prime * result + ((this.status == null) ? 0 : this.status.hashCode());
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
		if (startQuestScript == null) {
			return other.startQuestScript == null;
		} else if (!startQuestScript.equals(other.startQuestScript)) {
			return false;
		}
		if (completeQuestScript == null) {
			return other.completeQuestScript == null;
		} else if (!completeQuestScript.equals(other.completeQuestScript)) {
			return false;
		}
		if (failedQuestScript == null) {
			return other.failedQuestScript == null;
		} else if (!failedQuestScript.equals(other.failedQuestScript)) {
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
		if (logSetting == null) {
			return other.logSetting == null;
		} else if (!logSetting.equals(other.logSetting)) {
			return false;
		}
		if (status == null) {
			return other.status == null;
		} else if (!status.equals(other.status)) {
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
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

package io.gs2.inbox.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * ネームスペース
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Namespace implements IModel, Serializable, Comparable<Namespace> {
	/** ネームスペース */
	protected String namespaceId;

	/**
	 * ネームスペースを取得
	 *
	 * @return ネームスペース
	 */
	public String getNamespaceId() {
		return namespaceId;
	}

	/**
	 * ネームスペースを設定
	 *
	 * @param namespaceId ネームスペース
	 */
	public void setNamespaceId(String namespaceId) {
		this.namespaceId = namespaceId;
	}

	/**
	 * ネームスペースを設定
	 *
	 * @param namespaceId ネームスペース
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
	/** ネームスペース名 */
	protected String name;

	/**
	 * ネームスペース名を取得
	 *
	 * @return ネームスペース名
	 */
	public String getName() {
		return name;
	}

	/**
	 * ネームスペース名を設定
	 *
	 * @param name ネームスペース名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ネームスペース名を設定
	 *
	 * @param name ネームスペース名
	 * @return this
	 */
	public Namespace withName(String name) {
		this.name = name;
		return this;
	}
	/** 説明文 */
	protected String description;

	/**
	 * 説明文を取得
	 *
	 * @return 説明文
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 説明文を設定
	 *
	 * @param description 説明文
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 説明文を設定
	 *
	 * @param description 説明文
	 * @return this
	 */
	public Namespace withDescription(String description) {
		this.description = description;
		return this;
	}
	/** 開封したメッセージを自動的に削除するか */
	protected Boolean isAutomaticDeletingEnabled;

	/**
	 * 開封したメッセージを自動的に削除するかを取得
	 *
	 * @return 開封したメッセージを自動的に削除するか
	 */
	public Boolean getIsAutomaticDeletingEnabled() {
		return isAutomaticDeletingEnabled;
	}

	/**
	 * 開封したメッセージを自動的に削除するかを設定
	 *
	 * @param isAutomaticDeletingEnabled 開封したメッセージを自動的に削除するか
	 */
	public void setIsAutomaticDeletingEnabled(Boolean isAutomaticDeletingEnabled) {
		this.isAutomaticDeletingEnabled = isAutomaticDeletingEnabled;
	}

	/**
	 * 開封したメッセージを自動的に削除するかを設定
	 *
	 * @param isAutomaticDeletingEnabled 開封したメッセージを自動的に削除するか
	 * @return this
	 */
	public Namespace withIsAutomaticDeletingEnabled(Boolean isAutomaticDeletingEnabled) {
		this.isAutomaticDeletingEnabled = isAutomaticDeletingEnabled;
		return this;
	}
	/** メッセージ受信したときに実行するスクリプト */
	protected ScriptSetting receiveMessageScript;

	/**
	 * メッセージ受信したときに実行するスクリプトを取得
	 *
	 * @return メッセージ受信したときに実行するスクリプト
	 */
	public ScriptSetting getReceiveMessageScript() {
		return receiveMessageScript;
	}

	/**
	 * メッセージ受信したときに実行するスクリプトを設定
	 *
	 * @param receiveMessageScript メッセージ受信したときに実行するスクリプト
	 */
	public void setReceiveMessageScript(ScriptSetting receiveMessageScript) {
		this.receiveMessageScript = receiveMessageScript;
	}

	/**
	 * メッセージ受信したときに実行するスクリプトを設定
	 *
	 * @param receiveMessageScript メッセージ受信したときに実行するスクリプト
	 * @return this
	 */
	public Namespace withReceiveMessageScript(ScriptSetting receiveMessageScript) {
		this.receiveMessageScript = receiveMessageScript;
		return this;
	}
	/** メッセージ開封したときに実行するスクリプト */
	protected ScriptSetting readMessageScript;

	/**
	 * メッセージ開封したときに実行するスクリプトを取得
	 *
	 * @return メッセージ開封したときに実行するスクリプト
	 */
	public ScriptSetting getReadMessageScript() {
		return readMessageScript;
	}

	/**
	 * メッセージ開封したときに実行するスクリプトを設定
	 *
	 * @param readMessageScript メッセージ開封したときに実行するスクリプト
	 */
	public void setReadMessageScript(ScriptSetting readMessageScript) {
		this.readMessageScript = readMessageScript;
	}

	/**
	 * メッセージ開封したときに実行するスクリプトを設定
	 *
	 * @param readMessageScript メッセージ開封したときに実行するスクリプト
	 * @return this
	 */
	public Namespace withReadMessageScript(ScriptSetting readMessageScript) {
		this.readMessageScript = readMessageScript;
		return this;
	}
	/** メッセージ削除したときに実行するスクリプト */
	protected ScriptSetting deleteMessageScript;

	/**
	 * メッセージ削除したときに実行するスクリプトを取得
	 *
	 * @return メッセージ削除したときに実行するスクリプト
	 */
	public ScriptSetting getDeleteMessageScript() {
		return deleteMessageScript;
	}

	/**
	 * メッセージ削除したときに実行するスクリプトを設定
	 *
	 * @param deleteMessageScript メッセージ削除したときに実行するスクリプト
	 */
	public void setDeleteMessageScript(ScriptSetting deleteMessageScript) {
		this.deleteMessageScript = deleteMessageScript;
	}

	/**
	 * メッセージ削除したときに実行するスクリプトを設定
	 *
	 * @param deleteMessageScript メッセージ削除したときに実行するスクリプト
	 * @return this
	 */
	public Namespace withDeleteMessageScript(ScriptSetting deleteMessageScript) {
		this.deleteMessageScript = deleteMessageScript;
		return this;
	}
	/** 報酬付与処理をジョブとして追加するキューネームスペース のGRN */
	protected String queueNamespaceId;

	/**
	 * 報酬付与処理をジョブとして追加するキューネームスペース のGRNを取得
	 *
	 * @return 報酬付与処理をジョブとして追加するキューネームスペース のGRN
	 */
	public String getQueueNamespaceId() {
		return queueNamespaceId;
	}

	/**
	 * 報酬付与処理をジョブとして追加するキューネームスペース のGRNを設定
	 *
	 * @param queueNamespaceId 報酬付与処理をジョブとして追加するキューネームスペース のGRN
	 */
	public void setQueueNamespaceId(String queueNamespaceId) {
		this.queueNamespaceId = queueNamespaceId;
	}

	/**
	 * 報酬付与処理をジョブとして追加するキューネームスペース のGRNを設定
	 *
	 * @param queueNamespaceId 報酬付与処理をジョブとして追加するキューネームスペース のGRN
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
	/** メッセージを受信したときのプッシュ通知 */
	protected NotificationSetting receiveNotification;

	/**
	 * メッセージを受信したときのプッシュ通知を取得
	 *
	 * @return メッセージを受信したときのプッシュ通知
	 */
	public NotificationSetting getReceiveNotification() {
		return receiveNotification;
	}

	/**
	 * メッセージを受信したときのプッシュ通知を設定
	 *
	 * @param receiveNotification メッセージを受信したときのプッシュ通知
	 */
	public void setReceiveNotification(NotificationSetting receiveNotification) {
		this.receiveNotification = receiveNotification;
	}

	/**
	 * メッセージを受信したときのプッシュ通知を設定
	 *
	 * @param receiveNotification メッセージを受信したときのプッシュ通知
	 * @return this
	 */
	public Namespace withReceiveNotification(NotificationSetting receiveNotification) {
		this.receiveNotification = receiveNotification;
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
        JsonNode receiveMessageScript = this.getReceiveMessageScript().toJson();
        JsonNode readMessageScript = this.getReadMessageScript().toJson();
        JsonNode deleteMessageScript = this.getDeleteMessageScript().toJson();
        JsonNode receiveNotification = this.getReceiveNotification().toJson();
        JsonNode logSetting = this.getLogSetting().toJson();
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("namespaceId", this.getNamespaceId())
            .put("ownerId", this.getOwnerId())
            .put("name", this.getName())
            .put("description", this.getDescription())
            .put("isAutomaticDeletingEnabled", this.getIsAutomaticDeletingEnabled())
            .put("queueNamespaceId", this.getQueueNamespaceId())
            .put("keyId", this.getKeyId())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        body_.set("receiveMessageScript", receiveMessageScript);
        body_.set("readMessageScript", readMessageScript);
        body_.set("deleteMessageScript", deleteMessageScript);
        body_.set("receiveNotification", receiveNotification);
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
        result = prime * result + ((this.isAutomaticDeletingEnabled == null) ? 0 : this.isAutomaticDeletingEnabled.hashCode());
        result = prime * result + ((this.receiveMessageScript == null) ? 0 : this.receiveMessageScript.hashCode());
        result = prime * result + ((this.readMessageScript == null) ? 0 : this.readMessageScript.hashCode());
        result = prime * result + ((this.deleteMessageScript == null) ? 0 : this.deleteMessageScript.hashCode());
        result = prime * result + ((this.queueNamespaceId == null) ? 0 : this.queueNamespaceId.hashCode());
        result = prime * result + ((this.keyId == null) ? 0 : this.keyId.hashCode());
        result = prime * result + ((this.receiveNotification == null) ? 0 : this.receiveNotification.hashCode());
        result = prime * result + ((this.logSetting == null) ? 0 : this.logSetting.hashCode());
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
		if (isAutomaticDeletingEnabled == null) {
			return other.isAutomaticDeletingEnabled == null;
		} else if (!isAutomaticDeletingEnabled.equals(other.isAutomaticDeletingEnabled)) {
			return false;
		}
		if (receiveMessageScript == null) {
			return other.receiveMessageScript == null;
		} else if (!receiveMessageScript.equals(other.receiveMessageScript)) {
			return false;
		}
		if (readMessageScript == null) {
			return other.readMessageScript == null;
		} else if (!readMessageScript.equals(other.readMessageScript)) {
			return false;
		}
		if (deleteMessageScript == null) {
			return other.deleteMessageScript == null;
		} else if (!deleteMessageScript.equals(other.deleteMessageScript)) {
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
		if (receiveNotification == null) {
			return other.receiveNotification == null;
		} else if (!receiveNotification.equals(other.receiveNotification)) {
			return false;
		}
		if (logSetting == null) {
			return other.logSetting == null;
		} else if (!logSetting.equals(other.logSetting)) {
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
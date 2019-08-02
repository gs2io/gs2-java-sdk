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
import io.gs2.model.IModel;

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
	/** プレゼントボックス名 */
	protected String name;

	/**
	 * プレゼントボックス名を取得
	 *
	 * @return プレゼントボックス名
	 */
	public String getName() {
		return name;
	}

	/**
	 * プレゼントボックス名を設定
	 *
	 * @param name プレゼントボックス名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * プレゼントボックス名を設定
	 *
	 * @param name プレゼントボックス名
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
	/** メッセージ受信時 に実行されるスクリプト のGRN */
	protected String receiveMessageTriggerScriptId;

	/**
	 * メッセージ受信時 に実行されるスクリプト のGRNを取得
	 *
	 * @return メッセージ受信時 に実行されるスクリプト のGRN
	 */
	public String getReceiveMessageTriggerScriptId() {
		return receiveMessageTriggerScriptId;
	}

	/**
	 * メッセージ受信時 に実行されるスクリプト のGRNを設定
	 *
	 * @param receiveMessageTriggerScriptId メッセージ受信時 に実行されるスクリプト のGRN
	 */
	public void setReceiveMessageTriggerScriptId(String receiveMessageTriggerScriptId) {
		this.receiveMessageTriggerScriptId = receiveMessageTriggerScriptId;
	}

	/**
	 * メッセージ受信時 に実行されるスクリプト のGRNを設定
	 *
	 * @param receiveMessageTriggerScriptId メッセージ受信時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withReceiveMessageTriggerScriptId(String receiveMessageTriggerScriptId) {
		this.receiveMessageTriggerScriptId = receiveMessageTriggerScriptId;
		return this;
	}
	/** メッセージ受信完了時 に実行されるスクリプト のGRN */
	protected String receiveMessageDoneTriggerScriptId;

	/**
	 * メッセージ受信完了時 に実行されるスクリプト のGRNを取得
	 *
	 * @return メッセージ受信完了時 に実行されるスクリプト のGRN
	 */
	public String getReceiveMessageDoneTriggerScriptId() {
		return receiveMessageDoneTriggerScriptId;
	}

	/**
	 * メッセージ受信完了時 に実行されるスクリプト のGRNを設定
	 *
	 * @param receiveMessageDoneTriggerScriptId メッセージ受信完了時 に実行されるスクリプト のGRN
	 */
	public void setReceiveMessageDoneTriggerScriptId(String receiveMessageDoneTriggerScriptId) {
		this.receiveMessageDoneTriggerScriptId = receiveMessageDoneTriggerScriptId;
	}

	/**
	 * メッセージ受信完了時 に実行されるスクリプト のGRNを設定
	 *
	 * @param receiveMessageDoneTriggerScriptId メッセージ受信完了時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withReceiveMessageDoneTriggerScriptId(String receiveMessageDoneTriggerScriptId) {
		this.receiveMessageDoneTriggerScriptId = receiveMessageDoneTriggerScriptId;
		return this;
	}
	/** メッセージ受信完了時 にジョブが登録されるネームスペース のGRN */
	protected String receiveMessageDoneTriggerNamespaceId;

	/**
	 * メッセージ受信完了時 にジョブが登録されるネームスペース のGRNを取得
	 *
	 * @return メッセージ受信完了時 にジョブが登録されるネームスペース のGRN
	 */
	public String getReceiveMessageDoneTriggerNamespaceId() {
		return receiveMessageDoneTriggerNamespaceId;
	}

	/**
	 * メッセージ受信完了時 にジョブが登録されるネームスペース のGRNを設定
	 *
	 * @param receiveMessageDoneTriggerNamespaceId メッセージ受信完了時 にジョブが登録されるネームスペース のGRN
	 */
	public void setReceiveMessageDoneTriggerNamespaceId(String receiveMessageDoneTriggerNamespaceId) {
		this.receiveMessageDoneTriggerNamespaceId = receiveMessageDoneTriggerNamespaceId;
	}

	/**
	 * メッセージ受信完了時 にジョブが登録されるネームスペース のGRNを設定
	 *
	 * @param receiveMessageDoneTriggerNamespaceId メッセージ受信完了時 にジョブが登録されるネームスペース のGRN
	 * @return this
	 */
	public Namespace withReceiveMessageDoneTriggerNamespaceId(String receiveMessageDoneTriggerNamespaceId) {
		this.receiveMessageDoneTriggerNamespaceId = receiveMessageDoneTriggerNamespaceId;
		return this;
	}
	/** メッセージ開封時 に実行されるスクリプト のGRN */
	protected String readMessageTriggerScriptId;

	/**
	 * メッセージ開封時 に実行されるスクリプト のGRNを取得
	 *
	 * @return メッセージ開封時 に実行されるスクリプト のGRN
	 */
	public String getReadMessageTriggerScriptId() {
		return readMessageTriggerScriptId;
	}

	/**
	 * メッセージ開封時 に実行されるスクリプト のGRNを設定
	 *
	 * @param readMessageTriggerScriptId メッセージ開封時 に実行されるスクリプト のGRN
	 */
	public void setReadMessageTriggerScriptId(String readMessageTriggerScriptId) {
		this.readMessageTriggerScriptId = readMessageTriggerScriptId;
	}

	/**
	 * メッセージ開封時 に実行されるスクリプト のGRNを設定
	 *
	 * @param readMessageTriggerScriptId メッセージ開封時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withReadMessageTriggerScriptId(String readMessageTriggerScriptId) {
		this.readMessageTriggerScriptId = readMessageTriggerScriptId;
		return this;
	}
	/** メッセージ開封完了時 に実行されるスクリプト のGRN */
	protected String readMessageDoneTriggerScriptId;

	/**
	 * メッセージ開封完了時 に実行されるスクリプト のGRNを取得
	 *
	 * @return メッセージ開封完了時 に実行されるスクリプト のGRN
	 */
	public String getReadMessageDoneTriggerScriptId() {
		return readMessageDoneTriggerScriptId;
	}

	/**
	 * メッセージ開封完了時 に実行されるスクリプト のGRNを設定
	 *
	 * @param readMessageDoneTriggerScriptId メッセージ開封完了時 に実行されるスクリプト のGRN
	 */
	public void setReadMessageDoneTriggerScriptId(String readMessageDoneTriggerScriptId) {
		this.readMessageDoneTriggerScriptId = readMessageDoneTriggerScriptId;
	}

	/**
	 * メッセージ開封完了時 に実行されるスクリプト のGRNを設定
	 *
	 * @param readMessageDoneTriggerScriptId メッセージ開封完了時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withReadMessageDoneTriggerScriptId(String readMessageDoneTriggerScriptId) {
		this.readMessageDoneTriggerScriptId = readMessageDoneTriggerScriptId;
		return this;
	}
	/** メッセージ開封完了時 にジョブが登録されるネームスペース のGRN */
	protected String readMessageDoneTriggerNamespaceId;

	/**
	 * メッセージ開封完了時 にジョブが登録されるネームスペース のGRNを取得
	 *
	 * @return メッセージ開封完了時 にジョブが登録されるネームスペース のGRN
	 */
	public String getReadMessageDoneTriggerNamespaceId() {
		return readMessageDoneTriggerNamespaceId;
	}

	/**
	 * メッセージ開封完了時 にジョブが登録されるネームスペース のGRNを設定
	 *
	 * @param readMessageDoneTriggerNamespaceId メッセージ開封完了時 にジョブが登録されるネームスペース のGRN
	 */
	public void setReadMessageDoneTriggerNamespaceId(String readMessageDoneTriggerNamespaceId) {
		this.readMessageDoneTriggerNamespaceId = readMessageDoneTriggerNamespaceId;
	}

	/**
	 * メッセージ開封完了時 にジョブが登録されるネームスペース のGRNを設定
	 *
	 * @param readMessageDoneTriggerNamespaceId メッセージ開封完了時 にジョブが登録されるネームスペース のGRN
	 * @return this
	 */
	public Namespace withReadMessageDoneTriggerNamespaceId(String readMessageDoneTriggerNamespaceId) {
		this.readMessageDoneTriggerNamespaceId = readMessageDoneTriggerNamespaceId;
		return this;
	}
	/** メッセージ削除時 に実行されるスクリプト のGRN */
	protected String deleteMessageTriggerScriptId;

	/**
	 * メッセージ削除時 に実行されるスクリプト のGRNを取得
	 *
	 * @return メッセージ削除時 に実行されるスクリプト のGRN
	 */
	public String getDeleteMessageTriggerScriptId() {
		return deleteMessageTriggerScriptId;
	}

	/**
	 * メッセージ削除時 に実行されるスクリプト のGRNを設定
	 *
	 * @param deleteMessageTriggerScriptId メッセージ削除時 に実行されるスクリプト のGRN
	 */
	public void setDeleteMessageTriggerScriptId(String deleteMessageTriggerScriptId) {
		this.deleteMessageTriggerScriptId = deleteMessageTriggerScriptId;
	}

	/**
	 * メッセージ削除時 に実行されるスクリプト のGRNを設定
	 *
	 * @param deleteMessageTriggerScriptId メッセージ削除時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withDeleteMessageTriggerScriptId(String deleteMessageTriggerScriptId) {
		this.deleteMessageTriggerScriptId = deleteMessageTriggerScriptId;
		return this;
	}
	/** メッセージ削除完了時 に実行されるスクリプト のGRN */
	protected String deleteMessageDoneTriggerScriptId;

	/**
	 * メッセージ削除完了時 に実行されるスクリプト のGRNを取得
	 *
	 * @return メッセージ削除完了時 に実行されるスクリプト のGRN
	 */
	public String getDeleteMessageDoneTriggerScriptId() {
		return deleteMessageDoneTriggerScriptId;
	}

	/**
	 * メッセージ削除完了時 に実行されるスクリプト のGRNを設定
	 *
	 * @param deleteMessageDoneTriggerScriptId メッセージ削除完了時 に実行されるスクリプト のGRN
	 */
	public void setDeleteMessageDoneTriggerScriptId(String deleteMessageDoneTriggerScriptId) {
		this.deleteMessageDoneTriggerScriptId = deleteMessageDoneTriggerScriptId;
	}

	/**
	 * メッセージ削除完了時 に実行されるスクリプト のGRNを設定
	 *
	 * @param deleteMessageDoneTriggerScriptId メッセージ削除完了時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withDeleteMessageDoneTriggerScriptId(String deleteMessageDoneTriggerScriptId) {
		this.deleteMessageDoneTriggerScriptId = deleteMessageDoneTriggerScriptId;
		return this;
	}
	/** メッセージ削除完了時 にジョブが登録されるネームスペース のGRN */
	protected String deleteMessageDoneTriggerNamespaceId;

	/**
	 * メッセージ削除完了時 にジョブが登録されるネームスペース のGRNを取得
	 *
	 * @return メッセージ削除完了時 にジョブが登録されるネームスペース のGRN
	 */
	public String getDeleteMessageDoneTriggerNamespaceId() {
		return deleteMessageDoneTriggerNamespaceId;
	}

	/**
	 * メッセージ削除完了時 にジョブが登録されるネームスペース のGRNを設定
	 *
	 * @param deleteMessageDoneTriggerNamespaceId メッセージ削除完了時 にジョブが登録されるネームスペース のGRN
	 */
	public void setDeleteMessageDoneTriggerNamespaceId(String deleteMessageDoneTriggerNamespaceId) {
		this.deleteMessageDoneTriggerNamespaceId = deleteMessageDoneTriggerNamespaceId;
	}

	/**
	 * メッセージ削除完了時 にジョブが登録されるネームスペース のGRNを設定
	 *
	 * @param deleteMessageDoneTriggerNamespaceId メッセージ削除完了時 にジョブが登録されるネームスペース のGRN
	 * @return this
	 */
	public Namespace withDeleteMessageDoneTriggerNamespaceId(String deleteMessageDoneTriggerNamespaceId) {
		this.deleteMessageDoneTriggerNamespaceId = deleteMessageDoneTriggerNamespaceId;
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
        JsonNode receiveNotification = this.getReceiveNotification().toJson();
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("namespaceId", this.getNamespaceId())
            .put("ownerId", this.getOwnerId())
            .put("name", this.getName())
            .put("description", this.getDescription())
            .put("isAutomaticDeletingEnabled", this.getIsAutomaticDeletingEnabled())
            .put("receiveMessageTriggerScriptId", this.getReceiveMessageTriggerScriptId())
            .put("receiveMessageDoneTriggerScriptId", this.getReceiveMessageDoneTriggerScriptId())
            .put("receiveMessageDoneTriggerNamespaceId", this.getReceiveMessageDoneTriggerNamespaceId())
            .put("readMessageTriggerScriptId", this.getReadMessageTriggerScriptId())
            .put("readMessageDoneTriggerScriptId", this.getReadMessageDoneTriggerScriptId())
            .put("readMessageDoneTriggerNamespaceId", this.getReadMessageDoneTriggerNamespaceId())
            .put("deleteMessageTriggerScriptId", this.getDeleteMessageTriggerScriptId())
            .put("deleteMessageDoneTriggerScriptId", this.getDeleteMessageDoneTriggerScriptId())
            .put("deleteMessageDoneTriggerNamespaceId", this.getDeleteMessageDoneTriggerNamespaceId())
            .put("queueNamespaceId", this.getQueueNamespaceId())
            .put("keyId", this.getKeyId())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        body_.set("receiveNotification", receiveNotification);
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
        result = prime * result + ((this.receiveMessageTriggerScriptId == null) ? 0 : this.receiveMessageTriggerScriptId.hashCode());
        result = prime * result + ((this.receiveMessageDoneTriggerScriptId == null) ? 0 : this.receiveMessageDoneTriggerScriptId.hashCode());
        result = prime * result + ((this.receiveMessageDoneTriggerNamespaceId == null) ? 0 : this.receiveMessageDoneTriggerNamespaceId.hashCode());
        result = prime * result + ((this.readMessageTriggerScriptId == null) ? 0 : this.readMessageTriggerScriptId.hashCode());
        result = prime * result + ((this.readMessageDoneTriggerScriptId == null) ? 0 : this.readMessageDoneTriggerScriptId.hashCode());
        result = prime * result + ((this.readMessageDoneTriggerNamespaceId == null) ? 0 : this.readMessageDoneTriggerNamespaceId.hashCode());
        result = prime * result + ((this.deleteMessageTriggerScriptId == null) ? 0 : this.deleteMessageTriggerScriptId.hashCode());
        result = prime * result + ((this.deleteMessageDoneTriggerScriptId == null) ? 0 : this.deleteMessageDoneTriggerScriptId.hashCode());
        result = prime * result + ((this.deleteMessageDoneTriggerNamespaceId == null) ? 0 : this.deleteMessageDoneTriggerNamespaceId.hashCode());
        result = prime * result + ((this.queueNamespaceId == null) ? 0 : this.queueNamespaceId.hashCode());
        result = prime * result + ((this.keyId == null) ? 0 : this.keyId.hashCode());
        result = prime * result + ((this.receiveNotification == null) ? 0 : this.receiveNotification.hashCode());
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
		if (receiveMessageTriggerScriptId == null) {
			return other.receiveMessageTriggerScriptId == null;
		} else if (!receiveMessageTriggerScriptId.equals(other.receiveMessageTriggerScriptId)) {
			return false;
		}
		if (receiveMessageDoneTriggerScriptId == null) {
			return other.receiveMessageDoneTriggerScriptId == null;
		} else if (!receiveMessageDoneTriggerScriptId.equals(other.receiveMessageDoneTriggerScriptId)) {
			return false;
		}
		if (receiveMessageDoneTriggerNamespaceId == null) {
			return other.receiveMessageDoneTriggerNamespaceId == null;
		} else if (!receiveMessageDoneTriggerNamespaceId.equals(other.receiveMessageDoneTriggerNamespaceId)) {
			return false;
		}
		if (readMessageTriggerScriptId == null) {
			return other.readMessageTriggerScriptId == null;
		} else if (!readMessageTriggerScriptId.equals(other.readMessageTriggerScriptId)) {
			return false;
		}
		if (readMessageDoneTriggerScriptId == null) {
			return other.readMessageDoneTriggerScriptId == null;
		} else if (!readMessageDoneTriggerScriptId.equals(other.readMessageDoneTriggerScriptId)) {
			return false;
		}
		if (readMessageDoneTriggerNamespaceId == null) {
			return other.readMessageDoneTriggerNamespaceId == null;
		} else if (!readMessageDoneTriggerNamespaceId.equals(other.readMessageDoneTriggerNamespaceId)) {
			return false;
		}
		if (deleteMessageTriggerScriptId == null) {
			return other.deleteMessageTriggerScriptId == null;
		} else if (!deleteMessageTriggerScriptId.equals(other.deleteMessageTriggerScriptId)) {
			return false;
		}
		if (deleteMessageDoneTriggerScriptId == null) {
			return other.deleteMessageDoneTriggerScriptId == null;
		} else if (!deleteMessageDoneTriggerScriptId.equals(other.deleteMessageDoneTriggerScriptId)) {
			return false;
		}
		if (deleteMessageDoneTriggerNamespaceId == null) {
			return other.deleteMessageDoneTriggerNamespaceId == null;
		} else if (!deleteMessageDoneTriggerNamespaceId.equals(other.deleteMessageDoneTriggerNamespaceId)) {
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
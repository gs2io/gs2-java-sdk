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

package io.gs2.mission.model;

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
	/** ミッション達成時 に実行されるスクリプト のGRN */
	protected String missionCompleteTriggerScriptId;

	/**
	 * ミッション達成時 に実行されるスクリプト のGRNを取得
	 *
	 * @return ミッション達成時 に実行されるスクリプト のGRN
	 */
	public String getMissionCompleteTriggerScriptId() {
		return missionCompleteTriggerScriptId;
	}

	/**
	 * ミッション達成時 に実行されるスクリプト のGRNを設定
	 *
	 * @param missionCompleteTriggerScriptId ミッション達成時 に実行されるスクリプト のGRN
	 */
	public void setMissionCompleteTriggerScriptId(String missionCompleteTriggerScriptId) {
		this.missionCompleteTriggerScriptId = missionCompleteTriggerScriptId;
	}

	/**
	 * ミッション達成時 に実行されるスクリプト のGRNを設定
	 *
	 * @param missionCompleteTriggerScriptId ミッション達成時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withMissionCompleteTriggerScriptId(String missionCompleteTriggerScriptId) {
		this.missionCompleteTriggerScriptId = missionCompleteTriggerScriptId;
		return this;
	}
	/** ミッション達成完了時 に実行されるスクリプト のGRN */
	protected String missionCompleteDoneTriggerScriptId;

	/**
	 * ミッション達成完了時 に実行されるスクリプト のGRNを取得
	 *
	 * @return ミッション達成完了時 に実行されるスクリプト のGRN
	 */
	public String getMissionCompleteDoneTriggerScriptId() {
		return missionCompleteDoneTriggerScriptId;
	}

	/**
	 * ミッション達成完了時 に実行されるスクリプト のGRNを設定
	 *
	 * @param missionCompleteDoneTriggerScriptId ミッション達成完了時 に実行されるスクリプト のGRN
	 */
	public void setMissionCompleteDoneTriggerScriptId(String missionCompleteDoneTriggerScriptId) {
		this.missionCompleteDoneTriggerScriptId = missionCompleteDoneTriggerScriptId;
	}

	/**
	 * ミッション達成完了時 に実行されるスクリプト のGRNを設定
	 *
	 * @param missionCompleteDoneTriggerScriptId ミッション達成完了時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withMissionCompleteDoneTriggerScriptId(String missionCompleteDoneTriggerScriptId) {
		this.missionCompleteDoneTriggerScriptId = missionCompleteDoneTriggerScriptId;
		return this;
	}
	/** ミッション達成完了時 にジョブが登録されるネームスペース のGRN */
	protected String missionCompleteDoneTriggerQueueNamespaceId;

	/**
	 * ミッション達成完了時 にジョブが登録されるネームスペース のGRNを取得
	 *
	 * @return ミッション達成完了時 にジョブが登録されるネームスペース のGRN
	 */
	public String getMissionCompleteDoneTriggerQueueNamespaceId() {
		return missionCompleteDoneTriggerQueueNamespaceId;
	}

	/**
	 * ミッション達成完了時 にジョブが登録されるネームスペース のGRNを設定
	 *
	 * @param missionCompleteDoneTriggerQueueNamespaceId ミッション達成完了時 にジョブが登録されるネームスペース のGRN
	 */
	public void setMissionCompleteDoneTriggerQueueNamespaceId(String missionCompleteDoneTriggerQueueNamespaceId) {
		this.missionCompleteDoneTriggerQueueNamespaceId = missionCompleteDoneTriggerQueueNamespaceId;
	}

	/**
	 * ミッション達成完了時 にジョブが登録されるネームスペース のGRNを設定
	 *
	 * @param missionCompleteDoneTriggerQueueNamespaceId ミッション達成完了時 にジョブが登録されるネームスペース のGRN
	 * @return this
	 */
	public Namespace withMissionCompleteDoneTriggerQueueNamespaceId(String missionCompleteDoneTriggerQueueNamespaceId) {
		this.missionCompleteDoneTriggerQueueNamespaceId = missionCompleteDoneTriggerQueueNamespaceId;
		return this;
	}
	/** カウンター上昇時 に実行されるスクリプト のGRN */
	protected String counterIncrementTriggerScriptId;

	/**
	 * カウンター上昇時 に実行されるスクリプト のGRNを取得
	 *
	 * @return カウンター上昇時 に実行されるスクリプト のGRN
	 */
	public String getCounterIncrementTriggerScriptId() {
		return counterIncrementTriggerScriptId;
	}

	/**
	 * カウンター上昇時 に実行されるスクリプト のGRNを設定
	 *
	 * @param counterIncrementTriggerScriptId カウンター上昇時 に実行されるスクリプト のGRN
	 */
	public void setCounterIncrementTriggerScriptId(String counterIncrementTriggerScriptId) {
		this.counterIncrementTriggerScriptId = counterIncrementTriggerScriptId;
	}

	/**
	 * カウンター上昇時 に実行されるスクリプト のGRNを設定
	 *
	 * @param counterIncrementTriggerScriptId カウンター上昇時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withCounterIncrementTriggerScriptId(String counterIncrementTriggerScriptId) {
		this.counterIncrementTriggerScriptId = counterIncrementTriggerScriptId;
		return this;
	}
	/** カウンター上昇完了時 に実行されるスクリプト のGRN */
	protected String counterIncrementDoneTriggerScriptId;

	/**
	 * カウンター上昇完了時 に実行されるスクリプト のGRNを取得
	 *
	 * @return カウンター上昇完了時 に実行されるスクリプト のGRN
	 */
	public String getCounterIncrementDoneTriggerScriptId() {
		return counterIncrementDoneTriggerScriptId;
	}

	/**
	 * カウンター上昇完了時 に実行されるスクリプト のGRNを設定
	 *
	 * @param counterIncrementDoneTriggerScriptId カウンター上昇完了時 に実行されるスクリプト のGRN
	 */
	public void setCounterIncrementDoneTriggerScriptId(String counterIncrementDoneTriggerScriptId) {
		this.counterIncrementDoneTriggerScriptId = counterIncrementDoneTriggerScriptId;
	}

	/**
	 * カウンター上昇完了時 に実行されるスクリプト のGRNを設定
	 *
	 * @param counterIncrementDoneTriggerScriptId カウンター上昇完了時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withCounterIncrementDoneTriggerScriptId(String counterIncrementDoneTriggerScriptId) {
		this.counterIncrementDoneTriggerScriptId = counterIncrementDoneTriggerScriptId;
		return this;
	}
	/** カウンター上昇完了時 にジョブが登録されるネームスペース のGRN */
	protected String counterIncrementDoneTriggerQueueNamespaceId;

	/**
	 * カウンター上昇完了時 にジョブが登録されるネームスペース のGRNを取得
	 *
	 * @return カウンター上昇完了時 にジョブが登録されるネームスペース のGRN
	 */
	public String getCounterIncrementDoneTriggerQueueNamespaceId() {
		return counterIncrementDoneTriggerQueueNamespaceId;
	}

	/**
	 * カウンター上昇完了時 にジョブが登録されるネームスペース のGRNを設定
	 *
	 * @param counterIncrementDoneTriggerQueueNamespaceId カウンター上昇完了時 にジョブが登録されるネームスペース のGRN
	 */
	public void setCounterIncrementDoneTriggerQueueNamespaceId(String counterIncrementDoneTriggerQueueNamespaceId) {
		this.counterIncrementDoneTriggerQueueNamespaceId = counterIncrementDoneTriggerQueueNamespaceId;
	}

	/**
	 * カウンター上昇完了時 にジョブが登録されるネームスペース のGRNを設定
	 *
	 * @param counterIncrementDoneTriggerQueueNamespaceId カウンター上昇完了時 にジョブが登録されるネームスペース のGRN
	 * @return this
	 */
	public Namespace withCounterIncrementDoneTriggerQueueNamespaceId(String counterIncrementDoneTriggerQueueNamespaceId) {
		this.counterIncrementDoneTriggerQueueNamespaceId = counterIncrementDoneTriggerQueueNamespaceId;
		return this;
	}
	/** 報酬受け取り時 に実行されるスクリプト のGRN */
	protected String receiveRewardsTriggerScriptId;

	/**
	 * 報酬受け取り時 に実行されるスクリプト のGRNを取得
	 *
	 * @return 報酬受け取り時 に実行されるスクリプト のGRN
	 */
	public String getReceiveRewardsTriggerScriptId() {
		return receiveRewardsTriggerScriptId;
	}

	/**
	 * 報酬受け取り時 に実行されるスクリプト のGRNを設定
	 *
	 * @param receiveRewardsTriggerScriptId 報酬受け取り時 に実行されるスクリプト のGRN
	 */
	public void setReceiveRewardsTriggerScriptId(String receiveRewardsTriggerScriptId) {
		this.receiveRewardsTriggerScriptId = receiveRewardsTriggerScriptId;
	}

	/**
	 * 報酬受け取り時 に実行されるスクリプト のGRNを設定
	 *
	 * @param receiveRewardsTriggerScriptId 報酬受け取り時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withReceiveRewardsTriggerScriptId(String receiveRewardsTriggerScriptId) {
		this.receiveRewardsTriggerScriptId = receiveRewardsTriggerScriptId;
		return this;
	}
	/** 報酬受け取り完了時 に実行されるスクリプト のGRN */
	protected String receiveRewardsDoneTriggerScriptId;

	/**
	 * 報酬受け取り完了時 に実行されるスクリプト のGRNを取得
	 *
	 * @return 報酬受け取り完了時 に実行されるスクリプト のGRN
	 */
	public String getReceiveRewardsDoneTriggerScriptId() {
		return receiveRewardsDoneTriggerScriptId;
	}

	/**
	 * 報酬受け取り完了時 に実行されるスクリプト のGRNを設定
	 *
	 * @param receiveRewardsDoneTriggerScriptId 報酬受け取り完了時 に実行されるスクリプト のGRN
	 */
	public void setReceiveRewardsDoneTriggerScriptId(String receiveRewardsDoneTriggerScriptId) {
		this.receiveRewardsDoneTriggerScriptId = receiveRewardsDoneTriggerScriptId;
	}

	/**
	 * 報酬受け取り完了時 に実行されるスクリプト のGRNを設定
	 *
	 * @param receiveRewardsDoneTriggerScriptId 報酬受け取り完了時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withReceiveRewardsDoneTriggerScriptId(String receiveRewardsDoneTriggerScriptId) {
		this.receiveRewardsDoneTriggerScriptId = receiveRewardsDoneTriggerScriptId;
		return this;
	}
	/** 報酬受け取り完了時 にジョブが登録されるネームスペース のGRN */
	protected String receiveRewardsDoneTriggerQueueNamespaceId;

	/**
	 * 報酬受け取り完了時 にジョブが登録されるネームスペース のGRNを取得
	 *
	 * @return 報酬受け取り完了時 にジョブが登録されるネームスペース のGRN
	 */
	public String getReceiveRewardsDoneTriggerQueueNamespaceId() {
		return receiveRewardsDoneTriggerQueueNamespaceId;
	}

	/**
	 * 報酬受け取り完了時 にジョブが登録されるネームスペース のGRNを設定
	 *
	 * @param receiveRewardsDoneTriggerQueueNamespaceId 報酬受け取り完了時 にジョブが登録されるネームスペース のGRN
	 */
	public void setReceiveRewardsDoneTriggerQueueNamespaceId(String receiveRewardsDoneTriggerQueueNamespaceId) {
		this.receiveRewardsDoneTriggerQueueNamespaceId = receiveRewardsDoneTriggerQueueNamespaceId;
	}

	/**
	 * 報酬受け取り完了時 にジョブが登録されるネームスペース のGRNを設定
	 *
	 * @param receiveRewardsDoneTriggerQueueNamespaceId 報酬受け取り完了時 にジョブが登録されるネームスペース のGRN
	 * @return this
	 */
	public Namespace withReceiveRewardsDoneTriggerQueueNamespaceId(String receiveRewardsDoneTriggerQueueNamespaceId) {
		this.receiveRewardsDoneTriggerQueueNamespaceId = receiveRewardsDoneTriggerQueueNamespaceId;
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
	/** ミッションのタスクを達成したときのプッシュ通知 */
	protected NotificationSetting completeNotification;

	/**
	 * ミッションのタスクを達成したときのプッシュ通知を取得
	 *
	 * @return ミッションのタスクを達成したときのプッシュ通知
	 */
	public NotificationSetting getCompleteNotification() {
		return completeNotification;
	}

	/**
	 * ミッションのタスクを達成したときのプッシュ通知を設定
	 *
	 * @param completeNotification ミッションのタスクを達成したときのプッシュ通知
	 */
	public void setCompleteNotification(NotificationSetting completeNotification) {
		this.completeNotification = completeNotification;
	}

	/**
	 * ミッションのタスクを達成したときのプッシュ通知を設定
	 *
	 * @param completeNotification ミッションのタスクを達成したときのプッシュ通知
	 * @return this
	 */
	public Namespace withCompleteNotification(NotificationSetting completeNotification) {
		this.completeNotification = completeNotification;
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
        JsonNode completeNotification = this.getCompleteNotification().toJson();
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("namespaceId", this.getNamespaceId())
            .put("ownerId", this.getOwnerId())
            .put("name", this.getName())
            .put("description", this.getDescription())
            .put("missionCompleteTriggerScriptId", this.getMissionCompleteTriggerScriptId())
            .put("missionCompleteDoneTriggerScriptId", this.getMissionCompleteDoneTriggerScriptId())
            .put("missionCompleteDoneTriggerQueueNamespaceId", this.getMissionCompleteDoneTriggerQueueNamespaceId())
            .put("counterIncrementTriggerScriptId", this.getCounterIncrementTriggerScriptId())
            .put("counterIncrementDoneTriggerScriptId", this.getCounterIncrementDoneTriggerScriptId())
            .put("counterIncrementDoneTriggerQueueNamespaceId", this.getCounterIncrementDoneTriggerQueueNamespaceId())
            .put("receiveRewardsTriggerScriptId", this.getReceiveRewardsTriggerScriptId())
            .put("receiveRewardsDoneTriggerScriptId", this.getReceiveRewardsDoneTriggerScriptId())
            .put("receiveRewardsDoneTriggerQueueNamespaceId", this.getReceiveRewardsDoneTriggerQueueNamespaceId())
            .put("queueNamespaceId", this.getQueueNamespaceId())
            .put("keyId", this.getKeyId())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        body_.set("completeNotification", completeNotification);
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
        result = prime * result + ((this.missionCompleteTriggerScriptId == null) ? 0 : this.missionCompleteTriggerScriptId.hashCode());
        result = prime * result + ((this.missionCompleteDoneTriggerScriptId == null) ? 0 : this.missionCompleteDoneTriggerScriptId.hashCode());
        result = prime * result + ((this.missionCompleteDoneTriggerQueueNamespaceId == null) ? 0 : this.missionCompleteDoneTriggerQueueNamespaceId.hashCode());
        result = prime * result + ((this.counterIncrementTriggerScriptId == null) ? 0 : this.counterIncrementTriggerScriptId.hashCode());
        result = prime * result + ((this.counterIncrementDoneTriggerScriptId == null) ? 0 : this.counterIncrementDoneTriggerScriptId.hashCode());
        result = prime * result + ((this.counterIncrementDoneTriggerQueueNamespaceId == null) ? 0 : this.counterIncrementDoneTriggerQueueNamespaceId.hashCode());
        result = prime * result + ((this.receiveRewardsTriggerScriptId == null) ? 0 : this.receiveRewardsTriggerScriptId.hashCode());
        result = prime * result + ((this.receiveRewardsDoneTriggerScriptId == null) ? 0 : this.receiveRewardsDoneTriggerScriptId.hashCode());
        result = prime * result + ((this.receiveRewardsDoneTriggerQueueNamespaceId == null) ? 0 : this.receiveRewardsDoneTriggerQueueNamespaceId.hashCode());
        result = prime * result + ((this.queueNamespaceId == null) ? 0 : this.queueNamespaceId.hashCode());
        result = prime * result + ((this.keyId == null) ? 0 : this.keyId.hashCode());
        result = prime * result + ((this.completeNotification == null) ? 0 : this.completeNotification.hashCode());
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
		if (missionCompleteTriggerScriptId == null) {
			return other.missionCompleteTriggerScriptId == null;
		} else if (!missionCompleteTriggerScriptId.equals(other.missionCompleteTriggerScriptId)) {
			return false;
		}
		if (missionCompleteDoneTriggerScriptId == null) {
			return other.missionCompleteDoneTriggerScriptId == null;
		} else if (!missionCompleteDoneTriggerScriptId.equals(other.missionCompleteDoneTriggerScriptId)) {
			return false;
		}
		if (missionCompleteDoneTriggerQueueNamespaceId == null) {
			return other.missionCompleteDoneTriggerQueueNamespaceId == null;
		} else if (!missionCompleteDoneTriggerQueueNamespaceId.equals(other.missionCompleteDoneTriggerQueueNamespaceId)) {
			return false;
		}
		if (counterIncrementTriggerScriptId == null) {
			return other.counterIncrementTriggerScriptId == null;
		} else if (!counterIncrementTriggerScriptId.equals(other.counterIncrementTriggerScriptId)) {
			return false;
		}
		if (counterIncrementDoneTriggerScriptId == null) {
			return other.counterIncrementDoneTriggerScriptId == null;
		} else if (!counterIncrementDoneTriggerScriptId.equals(other.counterIncrementDoneTriggerScriptId)) {
			return false;
		}
		if (counterIncrementDoneTriggerQueueNamespaceId == null) {
			return other.counterIncrementDoneTriggerQueueNamespaceId == null;
		} else if (!counterIncrementDoneTriggerQueueNamespaceId.equals(other.counterIncrementDoneTriggerQueueNamespaceId)) {
			return false;
		}
		if (receiveRewardsTriggerScriptId == null) {
			return other.receiveRewardsTriggerScriptId == null;
		} else if (!receiveRewardsTriggerScriptId.equals(other.receiveRewardsTriggerScriptId)) {
			return false;
		}
		if (receiveRewardsDoneTriggerScriptId == null) {
			return other.receiveRewardsDoneTriggerScriptId == null;
		} else if (!receiveRewardsDoneTriggerScriptId.equals(other.receiveRewardsDoneTriggerScriptId)) {
			return false;
		}
		if (receiveRewardsDoneTriggerQueueNamespaceId == null) {
			return other.receiveRewardsDoneTriggerQueueNamespaceId == null;
		} else if (!receiveRewardsDoneTriggerQueueNamespaceId.equals(other.receiveRewardsDoneTriggerQueueNamespaceId)) {
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
		if (completeNotification == null) {
			return other.completeNotification == null;
		} else if (!completeNotification.equals(other.completeNotification)) {
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
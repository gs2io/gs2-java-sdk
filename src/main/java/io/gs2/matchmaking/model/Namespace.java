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

package io.gs2.matchmaking.model;

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
	/** レーティング計算機能を使用するか */
	protected Boolean enableRating;

	/**
	 * レーティング計算機能を使用するかを取得
	 *
	 * @return レーティング計算機能を使用するか
	 */
	public Boolean getEnableRating() {
		return enableRating;
	}

	/**
	 * レーティング計算機能を使用するかを設定
	 *
	 * @param enableRating レーティング計算機能を使用するか
	 */
	public void setEnableRating(Boolean enableRating) {
		this.enableRating = enableRating;
	}

	/**
	 * レーティング計算機能を使用するかを設定
	 *
	 * @param enableRating レーティング計算機能を使用するか
	 * @return this
	 */
	public Namespace withEnableRating(Boolean enableRating) {
		this.enableRating = enableRating;
		return this;
	}
	/** ギャザリング新規作成時のアクション */
	protected String createGatheringTriggerType;

	/**
	 * ギャザリング新規作成時のアクションを取得
	 *
	 * @return ギャザリング新規作成時のアクション
	 */
	public String getCreateGatheringTriggerType() {
		return createGatheringTriggerType;
	}

	/**
	 * ギャザリング新規作成時のアクションを設定
	 *
	 * @param createGatheringTriggerType ギャザリング新規作成時のアクション
	 */
	public void setCreateGatheringTriggerType(String createGatheringTriggerType) {
		this.createGatheringTriggerType = createGatheringTriggerType;
	}

	/**
	 * ギャザリング新規作成時のアクションを設定
	 *
	 * @param createGatheringTriggerType ギャザリング新規作成時のアクション
	 * @return this
	 */
	public Namespace withCreateGatheringTriggerType(String createGatheringTriggerType) {
		this.createGatheringTriggerType = createGatheringTriggerType;
		return this;
	}
	/** ギャザリング新規作成時 にルームを作成するネームスペース のGRN */
	protected String createGatheringTriggerRealtimeNamespaceId;

	/**
	 * ギャザリング新規作成時 にルームを作成するネームスペース のGRNを取得
	 *
	 * @return ギャザリング新規作成時 にルームを作成するネームスペース のGRN
	 */
	public String getCreateGatheringTriggerRealtimeNamespaceId() {
		return createGatheringTriggerRealtimeNamespaceId;
	}

	/**
	 * ギャザリング新規作成時 にルームを作成するネームスペース のGRNを設定
	 *
	 * @param createGatheringTriggerRealtimeNamespaceId ギャザリング新規作成時 にルームを作成するネームスペース のGRN
	 */
	public void setCreateGatheringTriggerRealtimeNamespaceId(String createGatheringTriggerRealtimeNamespaceId) {
		this.createGatheringTriggerRealtimeNamespaceId = createGatheringTriggerRealtimeNamespaceId;
	}

	/**
	 * ギャザリング新規作成時 にルームを作成するネームスペース のGRNを設定
	 *
	 * @param createGatheringTriggerRealtimeNamespaceId ギャザリング新規作成時 にルームを作成するネームスペース のGRN
	 * @return this
	 */
	public Namespace withCreateGatheringTriggerRealtimeNamespaceId(String createGatheringTriggerRealtimeNamespaceId) {
		this.createGatheringTriggerRealtimeNamespaceId = createGatheringTriggerRealtimeNamespaceId;
		return this;
	}
	/** ギャザリング新規作成時 に実行されるスクリプト のGRN */
	protected String createGatheringTriggerScriptId;

	/**
	 * ギャザリング新規作成時 に実行されるスクリプト のGRNを取得
	 *
	 * @return ギャザリング新規作成時 に実行されるスクリプト のGRN
	 */
	public String getCreateGatheringTriggerScriptId() {
		return createGatheringTriggerScriptId;
	}

	/**
	 * ギャザリング新規作成時 に実行されるスクリプト のGRNを設定
	 *
	 * @param createGatheringTriggerScriptId ギャザリング新規作成時 に実行されるスクリプト のGRN
	 */
	public void setCreateGatheringTriggerScriptId(String createGatheringTriggerScriptId) {
		this.createGatheringTriggerScriptId = createGatheringTriggerScriptId;
	}

	/**
	 * ギャザリング新規作成時 に実行されるスクリプト のGRNを設定
	 *
	 * @param createGatheringTriggerScriptId ギャザリング新規作成時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withCreateGatheringTriggerScriptId(String createGatheringTriggerScriptId) {
		this.createGatheringTriggerScriptId = createGatheringTriggerScriptId;
		return this;
	}
	/** マッチメイキング完了時のアクション */
	protected String completeMatchmakingTriggerType;

	/**
	 * マッチメイキング完了時のアクションを取得
	 *
	 * @return マッチメイキング完了時のアクション
	 */
	public String getCompleteMatchmakingTriggerType() {
		return completeMatchmakingTriggerType;
	}

	/**
	 * マッチメイキング完了時のアクションを設定
	 *
	 * @param completeMatchmakingTriggerType マッチメイキング完了時のアクション
	 */
	public void setCompleteMatchmakingTriggerType(String completeMatchmakingTriggerType) {
		this.completeMatchmakingTriggerType = completeMatchmakingTriggerType;
	}

	/**
	 * マッチメイキング完了時のアクションを設定
	 *
	 * @param completeMatchmakingTriggerType マッチメイキング完了時のアクション
	 * @return this
	 */
	public Namespace withCompleteMatchmakingTriggerType(String completeMatchmakingTriggerType) {
		this.completeMatchmakingTriggerType = completeMatchmakingTriggerType;
		return this;
	}
	/** マッチメイキング完了時 にルームを作成するネームスペース のGRN */
	protected String completeMatchmakingTriggerRealtimeNamespaceId;

	/**
	 * マッチメイキング完了時 にルームを作成するネームスペース のGRNを取得
	 *
	 * @return マッチメイキング完了時 にルームを作成するネームスペース のGRN
	 */
	public String getCompleteMatchmakingTriggerRealtimeNamespaceId() {
		return completeMatchmakingTriggerRealtimeNamespaceId;
	}

	/**
	 * マッチメイキング完了時 にルームを作成するネームスペース のGRNを設定
	 *
	 * @param completeMatchmakingTriggerRealtimeNamespaceId マッチメイキング完了時 にルームを作成するネームスペース のGRN
	 */
	public void setCompleteMatchmakingTriggerRealtimeNamespaceId(String completeMatchmakingTriggerRealtimeNamespaceId) {
		this.completeMatchmakingTriggerRealtimeNamespaceId = completeMatchmakingTriggerRealtimeNamespaceId;
	}

	/**
	 * マッチメイキング完了時 にルームを作成するネームスペース のGRNを設定
	 *
	 * @param completeMatchmakingTriggerRealtimeNamespaceId マッチメイキング完了時 にルームを作成するネームスペース のGRN
	 * @return this
	 */
	public Namespace withCompleteMatchmakingTriggerRealtimeNamespaceId(String completeMatchmakingTriggerRealtimeNamespaceId) {
		this.completeMatchmakingTriggerRealtimeNamespaceId = completeMatchmakingTriggerRealtimeNamespaceId;
		return this;
	}
	/** マッチメイキング完了時 に実行されるスクリプト のGRN */
	protected String completeMatchmakingTriggerScriptId;

	/**
	 * マッチメイキング完了時 に実行されるスクリプト のGRNを取得
	 *
	 * @return マッチメイキング完了時 に実行されるスクリプト のGRN
	 */
	public String getCompleteMatchmakingTriggerScriptId() {
		return completeMatchmakingTriggerScriptId;
	}

	/**
	 * マッチメイキング完了時 に実行されるスクリプト のGRNを設定
	 *
	 * @param completeMatchmakingTriggerScriptId マッチメイキング完了時 に実行されるスクリプト のGRN
	 */
	public void setCompleteMatchmakingTriggerScriptId(String completeMatchmakingTriggerScriptId) {
		this.completeMatchmakingTriggerScriptId = completeMatchmakingTriggerScriptId;
	}

	/**
	 * マッチメイキング完了時 に実行されるスクリプト のGRNを設定
	 *
	 * @param completeMatchmakingTriggerScriptId マッチメイキング完了時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withCompleteMatchmakingTriggerScriptId(String completeMatchmakingTriggerScriptId) {
		this.completeMatchmakingTriggerScriptId = completeMatchmakingTriggerScriptId;
		return this;
	}
	/** ギャザリングに新規プレイヤーが参加したときのプッシュ通知 */
	protected NotificationSetting joinNotification;

	/**
	 * ギャザリングに新規プレイヤーが参加したときのプッシュ通知を取得
	 *
	 * @return ギャザリングに新規プレイヤーが参加したときのプッシュ通知
	 */
	public NotificationSetting getJoinNotification() {
		return joinNotification;
	}

	/**
	 * ギャザリングに新規プレイヤーが参加したときのプッシュ通知を設定
	 *
	 * @param joinNotification ギャザリングに新規プレイヤーが参加したときのプッシュ通知
	 */
	public void setJoinNotification(NotificationSetting joinNotification) {
		this.joinNotification = joinNotification;
	}

	/**
	 * ギャザリングに新規プレイヤーが参加したときのプッシュ通知を設定
	 *
	 * @param joinNotification ギャザリングに新規プレイヤーが参加したときのプッシュ通知
	 * @return this
	 */
	public Namespace withJoinNotification(NotificationSetting joinNotification) {
		this.joinNotification = joinNotification;
		return this;
	}
	/** ギャザリングからプレイヤーが離脱したときのプッシュ通知 */
	protected NotificationSetting leaveNotification;

	/**
	 * ギャザリングからプレイヤーが離脱したときのプッシュ通知を取得
	 *
	 * @return ギャザリングからプレイヤーが離脱したときのプッシュ通知
	 */
	public NotificationSetting getLeaveNotification() {
		return leaveNotification;
	}

	/**
	 * ギャザリングからプレイヤーが離脱したときのプッシュ通知を設定
	 *
	 * @param leaveNotification ギャザリングからプレイヤーが離脱したときのプッシュ通知
	 */
	public void setLeaveNotification(NotificationSetting leaveNotification) {
		this.leaveNotification = leaveNotification;
	}

	/**
	 * ギャザリングからプレイヤーが離脱したときのプッシュ通知を設定
	 *
	 * @param leaveNotification ギャザリングからプレイヤーが離脱したときのプッシュ通知
	 * @return this
	 */
	public Namespace withLeaveNotification(NotificationSetting leaveNotification) {
		this.leaveNotification = leaveNotification;
		return this;
	}
	/** マッチメイキングが完了したときのプッシュ通知 */
	protected NotificationSetting completeNotification;

	/**
	 * マッチメイキングが完了したときのプッシュ通知を取得
	 *
	 * @return マッチメイキングが完了したときのプッシュ通知
	 */
	public NotificationSetting getCompleteNotification() {
		return completeNotification;
	}

	/**
	 * マッチメイキングが完了したときのプッシュ通知を設定
	 *
	 * @param completeNotification マッチメイキングが完了したときのプッシュ通知
	 */
	public void setCompleteNotification(NotificationSetting completeNotification) {
		this.completeNotification = completeNotification;
	}

	/**
	 * マッチメイキングが完了したときのプッシュ通知を設定
	 *
	 * @param completeNotification マッチメイキングが完了したときのプッシュ通知
	 * @return this
	 */
	public Namespace withCompleteNotification(NotificationSetting completeNotification) {
		this.completeNotification = completeNotification;
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
        JsonNode joinNotification = this.getJoinNotification().toJson();
        JsonNode leaveNotification = this.getLeaveNotification().toJson();
        JsonNode completeNotification = this.getCompleteNotification().toJson();
        JsonNode logSetting = this.getLogSetting().toJson();
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("namespaceId", this.getNamespaceId())
            .put("ownerId", this.getOwnerId())
            .put("name", this.getName())
            .put("description", this.getDescription())
            .put("enableRating", this.getEnableRating())
            .put("createGatheringTriggerType", this.getCreateGatheringTriggerType())
            .put("createGatheringTriggerRealtimeNamespaceId", this.getCreateGatheringTriggerRealtimeNamespaceId())
            .put("createGatheringTriggerScriptId", this.getCreateGatheringTriggerScriptId())
            .put("completeMatchmakingTriggerType", this.getCompleteMatchmakingTriggerType())
            .put("completeMatchmakingTriggerRealtimeNamespaceId", this.getCompleteMatchmakingTriggerRealtimeNamespaceId())
            .put("completeMatchmakingTriggerScriptId", this.getCompleteMatchmakingTriggerScriptId())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        body_.set("joinNotification", joinNotification);
        body_.set("leaveNotification", leaveNotification);
        body_.set("completeNotification", completeNotification);
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
        result = prime * result + ((this.enableRating == null) ? 0 : this.enableRating.hashCode());
        result = prime * result + ((this.createGatheringTriggerType == null) ? 0 : this.createGatheringTriggerType.hashCode());
        result = prime * result + ((this.createGatheringTriggerRealtimeNamespaceId == null) ? 0 : this.createGatheringTriggerRealtimeNamespaceId.hashCode());
        result = prime * result + ((this.createGatheringTriggerScriptId == null) ? 0 : this.createGatheringTriggerScriptId.hashCode());
        result = prime * result + ((this.completeMatchmakingTriggerType == null) ? 0 : this.completeMatchmakingTriggerType.hashCode());
        result = prime * result + ((this.completeMatchmakingTriggerRealtimeNamespaceId == null) ? 0 : this.completeMatchmakingTriggerRealtimeNamespaceId.hashCode());
        result = prime * result + ((this.completeMatchmakingTriggerScriptId == null) ? 0 : this.completeMatchmakingTriggerScriptId.hashCode());
        result = prime * result + ((this.joinNotification == null) ? 0 : this.joinNotification.hashCode());
        result = prime * result + ((this.leaveNotification == null) ? 0 : this.leaveNotification.hashCode());
        result = prime * result + ((this.completeNotification == null) ? 0 : this.completeNotification.hashCode());
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
		if (enableRating == null) {
			return other.enableRating == null;
		} else if (!enableRating.equals(other.enableRating)) {
			return false;
		}
		if (createGatheringTriggerType == null) {
			return other.createGatheringTriggerType == null;
		} else if (!createGatheringTriggerType.equals(other.createGatheringTriggerType)) {
			return false;
		}
		if (createGatheringTriggerRealtimeNamespaceId == null) {
			return other.createGatheringTriggerRealtimeNamespaceId == null;
		} else if (!createGatheringTriggerRealtimeNamespaceId.equals(other.createGatheringTriggerRealtimeNamespaceId)) {
			return false;
		}
		if (createGatheringTriggerScriptId == null) {
			return other.createGatheringTriggerScriptId == null;
		} else if (!createGatheringTriggerScriptId.equals(other.createGatheringTriggerScriptId)) {
			return false;
		}
		if (completeMatchmakingTriggerType == null) {
			return other.completeMatchmakingTriggerType == null;
		} else if (!completeMatchmakingTriggerType.equals(other.completeMatchmakingTriggerType)) {
			return false;
		}
		if (completeMatchmakingTriggerRealtimeNamespaceId == null) {
			return other.completeMatchmakingTriggerRealtimeNamespaceId == null;
		} else if (!completeMatchmakingTriggerRealtimeNamespaceId.equals(other.completeMatchmakingTriggerRealtimeNamespaceId)) {
			return false;
		}
		if (completeMatchmakingTriggerScriptId == null) {
			return other.completeMatchmakingTriggerScriptId == null;
		} else if (!completeMatchmakingTriggerScriptId.equals(other.completeMatchmakingTriggerScriptId)) {
			return false;
		}
		if (joinNotification == null) {
			return other.joinNotification == null;
		} else if (!joinNotification.equals(other.joinNotification)) {
			return false;
		}
		if (leaveNotification == null) {
			return other.leaveNotification == null;
		} else if (!leaveNotification.equals(other.leaveNotification)) {
			return false;
		}
		if (completeNotification == null) {
			return other.completeNotification == null;
		} else if (!completeNotification.equals(other.completeNotification)) {
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
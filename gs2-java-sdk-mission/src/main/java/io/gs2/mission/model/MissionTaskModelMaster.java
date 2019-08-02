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
 * ミッションタスクマスター
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class MissionTaskModelMaster implements IModel, Serializable, Comparable<MissionTaskModelMaster> {
	/** ミッションタスクマスター */
	protected String missionTaskId;

	/**
	 * ミッションタスクマスターを取得
	 *
	 * @return ミッションタスクマスター
	 */
	public String getMissionTaskId() {
		return missionTaskId;
	}

	/**
	 * ミッションタスクマスターを設定
	 *
	 * @param missionTaskId ミッションタスクマスター
	 */
	public void setMissionTaskId(String missionTaskId) {
		this.missionTaskId = missionTaskId;
	}

	/**
	 * ミッションタスクマスターを設定
	 *
	 * @param missionTaskId ミッションタスクマスター
	 * @return this
	 */
	public MissionTaskModelMaster withMissionTaskId(String missionTaskId) {
		this.missionTaskId = missionTaskId;
		return this;
	}
	/** タスク名 */
	protected String name;

	/**
	 * タスク名を取得
	 *
	 * @return タスク名
	 */
	public String getName() {
		return name;
	}

	/**
	 * タスク名を設定
	 *
	 * @param name タスク名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * タスク名を設定
	 *
	 * @param name タスク名
	 * @return this
	 */
	public MissionTaskModelMaster withName(String name) {
		this.name = name;
		return this;
	}
	/** メタデータ */
	protected String metadata;

	/**
	 * メタデータを取得
	 *
	 * @return メタデータ
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * メタデータを設定
	 *
	 * @param metadata メタデータ
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	/**
	 * メタデータを設定
	 *
	 * @param metadata メタデータ
	 * @return this
	 */
	public MissionTaskModelMaster withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	/** ミッションタスクの説明 */
	protected String description;

	/**
	 * ミッションタスクの説明を取得
	 *
	 * @return ミッションタスクの説明
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * ミッションタスクの説明を設定
	 *
	 * @param description ミッションタスクの説明
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * ミッションタスクの説明を設定
	 *
	 * @param description ミッションタスクの説明
	 * @return this
	 */
	public MissionTaskModelMaster withDescription(String description) {
		this.description = description;
		return this;
	}
	/** カウンター名 */
	protected String counterName;

	/**
	 * カウンター名を取得
	 *
	 * @return カウンター名
	 */
	public String getCounterName() {
		return counterName;
	}

	/**
	 * カウンター名を設定
	 *
	 * @param counterName カウンター名
	 */
	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}

	/**
	 * カウンター名を設定
	 *
	 * @param counterName カウンター名
	 * @return this
	 */
	public MissionTaskModelMaster withCounterName(String counterName) {
		this.counterName = counterName;
		return this;
	}
	/** リセットタイミング */
	protected String resetType;

	/**
	 * リセットタイミングを取得
	 *
	 * @return リセットタイミング
	 */
	public String getResetType() {
		return resetType;
	}

	/**
	 * リセットタイミングを設定
	 *
	 * @param resetType リセットタイミング
	 */
	public void setResetType(String resetType) {
		this.resetType = resetType;
	}

	/**
	 * リセットタイミングを設定
	 *
	 * @param resetType リセットタイミング
	 * @return this
	 */
	public MissionTaskModelMaster withResetType(String resetType) {
		this.resetType = resetType;
		return this;
	}
	/** 目標値 */
	protected Long targetValue;

	/**
	 * 目標値を取得
	 *
	 * @return 目標値
	 */
	public Long getTargetValue() {
		return targetValue;
	}

	/**
	 * 目標値を設定
	 *
	 * @param targetValue 目標値
	 */
	public void setTargetValue(Long targetValue) {
		this.targetValue = targetValue;
	}

	/**
	 * 目標値を設定
	 *
	 * @param targetValue 目標値
	 * @return this
	 */
	public MissionTaskModelMaster withTargetValue(Long targetValue) {
		this.targetValue = targetValue;
		return this;
	}
	/** ミッション達成時の報酬 */
	protected List<AcquireAction> completeAcquireActions;

	/**
	 * ミッション達成時の報酬を取得
	 *
	 * @return ミッション達成時の報酬
	 */
	public List<AcquireAction> getCompleteAcquireActions() {
		return completeAcquireActions;
	}

	/**
	 * ミッション達成時の報酬を設定
	 *
	 * @param completeAcquireActions ミッション達成時の報酬
	 */
	public void setCompleteAcquireActions(List<AcquireAction> completeAcquireActions) {
		this.completeAcquireActions = completeAcquireActions;
	}

	/**
	 * ミッション達成時の報酬を設定
	 *
	 * @param completeAcquireActions ミッション達成時の報酬
	 * @return this
	 */
	public MissionTaskModelMaster withCompleteAcquireActions(List<AcquireAction> completeAcquireActions) {
		this.completeAcquireActions = completeAcquireActions;
		return this;
	}
	/** 達成報酬の受け取り可能な期間を指定するイベントマスター のGRN */
	protected String challengePeriodEventId;

	/**
	 * 達成報酬の受け取り可能な期間を指定するイベントマスター のGRNを取得
	 *
	 * @return 達成報酬の受け取り可能な期間を指定するイベントマスター のGRN
	 */
	public String getChallengePeriodEventId() {
		return challengePeriodEventId;
	}

	/**
	 * 達成報酬の受け取り可能な期間を指定するイベントマスター のGRNを設定
	 *
	 * @param challengePeriodEventId 達成報酬の受け取り可能な期間を指定するイベントマスター のGRN
	 */
	public void setChallengePeriodEventId(String challengePeriodEventId) {
		this.challengePeriodEventId = challengePeriodEventId;
	}

	/**
	 * 達成報酬の受け取り可能な期間を指定するイベントマスター のGRNを設定
	 *
	 * @param challengePeriodEventId 達成報酬の受け取り可能な期間を指定するイベントマスター のGRN
	 * @return this
	 */
	public MissionTaskModelMaster withChallengePeriodEventId(String challengePeriodEventId) {
		this.challengePeriodEventId = challengePeriodEventId;
		return this;
	}
	/** このタスクに挑戦するために達成しておく必要のあるタスクの名前 */
	protected String premiseMissionTaskName;

	/**
	 * このタスクに挑戦するために達成しておく必要のあるタスクの名前を取得
	 *
	 * @return このタスクに挑戦するために達成しておく必要のあるタスクの名前
	 */
	public String getPremiseMissionTaskName() {
		return premiseMissionTaskName;
	}

	/**
	 * このタスクに挑戦するために達成しておく必要のあるタスクの名前を設定
	 *
	 * @param premiseMissionTaskName このタスクに挑戦するために達成しておく必要のあるタスクの名前
	 */
	public void setPremiseMissionTaskName(String premiseMissionTaskName) {
		this.premiseMissionTaskName = premiseMissionTaskName;
	}

	/**
	 * このタスクに挑戦するために達成しておく必要のあるタスクの名前を設定
	 *
	 * @param premiseMissionTaskName このタスクに挑戦するために達成しておく必要のあるタスクの名前
	 * @return this
	 */
	public MissionTaskModelMaster withPremiseMissionTaskName(String premiseMissionTaskName) {
		this.premiseMissionTaskName = premiseMissionTaskName;
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
	public MissionTaskModelMaster withCreatedAt(Long createdAt) {
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
	public MissionTaskModelMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> completeAcquireActions = new ArrayList<>();
        if(this.completeAcquireActions != null) {
            for(AcquireAction item : this.completeAcquireActions) {
                completeAcquireActions.add(item.toJson());
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("missionTaskId", this.getMissionTaskId())
            .put("name", this.getName())
            .put("metadata", this.getMetadata())
            .put("description", this.getDescription())
            .put("counterName", this.getCounterName())
            .put("resetType", this.getResetType())
            .put("targetValue", this.getTargetValue())
            .put("challengePeriodEventId", this.getChallengePeriodEventId())
            .put("premiseMissionTaskName", this.getPremiseMissionTaskName())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        body_.set("completeAcquireActions", JsonNodeFactory.instance.arrayNode().addAll(completeAcquireActions));
        return body_;
    }
	@Override
	public int compareTo(MissionTaskModelMaster o) {
		return missionTaskId.compareTo(o.missionTaskId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.missionTaskId == null) ? 0 : this.missionTaskId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.counterName == null) ? 0 : this.counterName.hashCode());
        result = prime * result + ((this.resetType == null) ? 0 : this.resetType.hashCode());
        result = prime * result + ((this.targetValue == null) ? 0 : this.targetValue.hashCode());
        result = prime * result + ((this.completeAcquireActions == null) ? 0 : this.completeAcquireActions.hashCode());
        result = prime * result + ((this.challengePeriodEventId == null) ? 0 : this.challengePeriodEventId.hashCode());
        result = prime * result + ((this.premiseMissionTaskName == null) ? 0 : this.premiseMissionTaskName.hashCode());
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
		MissionTaskModelMaster other = (MissionTaskModelMaster) o;
		if (missionTaskId == null) {
			return other.missionTaskId == null;
		} else if (!missionTaskId.equals(other.missionTaskId)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (metadata == null) {
			return other.metadata == null;
		} else if (!metadata.equals(other.metadata)) {
			return false;
		}
		if (description == null) {
			return other.description == null;
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (counterName == null) {
			return other.counterName == null;
		} else if (!counterName.equals(other.counterName)) {
			return false;
		}
		if (resetType == null) {
			return other.resetType == null;
		} else if (!resetType.equals(other.resetType)) {
			return false;
		}
		if (targetValue == null) {
			return other.targetValue == null;
		} else if (!targetValue.equals(other.targetValue)) {
			return false;
		}
		if (completeAcquireActions == null) {
			return other.completeAcquireActions == null;
		} else if (!completeAcquireActions.equals(other.completeAcquireActions)) {
			return false;
		}
		if (challengePeriodEventId == null) {
			return other.challengePeriodEventId == null;
		} else if (!challengePeriodEventId.equals(other.challengePeriodEventId)) {
			return false;
		}
		if (premiseMissionTaskName == null) {
			return other.premiseMissionTaskName == null;
		} else if (!premiseMissionTaskName.equals(other.premiseMissionTaskName)) {
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
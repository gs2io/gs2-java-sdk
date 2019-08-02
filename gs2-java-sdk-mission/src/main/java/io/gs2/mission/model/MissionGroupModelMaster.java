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
 * ミッショングループマスター
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class MissionGroupModelMaster implements IModel, Serializable, Comparable<MissionGroupModelMaster> {
	/** ミッショングループマスター */
	protected String missionGroupId;

	/**
	 * ミッショングループマスターを取得
	 *
	 * @return ミッショングループマスター
	 */
	public String getMissionGroupId() {
		return missionGroupId;
	}

	/**
	 * ミッショングループマスターを設定
	 *
	 * @param missionGroupId ミッショングループマスター
	 */
	public void setMissionGroupId(String missionGroupId) {
		this.missionGroupId = missionGroupId;
	}

	/**
	 * ミッショングループマスターを設定
	 *
	 * @param missionGroupId ミッショングループマスター
	 * @return this
	 */
	public MissionGroupModelMaster withMissionGroupId(String missionGroupId) {
		this.missionGroupId = missionGroupId;
		return this;
	}
	/** ミッショングループ名 */
	protected String name;

	/**
	 * ミッショングループ名を取得
	 *
	 * @return ミッショングループ名
	 */
	public String getName() {
		return name;
	}

	/**
	 * ミッショングループ名を設定
	 *
	 * @param name ミッショングループ名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ミッショングループ名を設定
	 *
	 * @param name ミッショングループ名
	 * @return this
	 */
	public MissionGroupModelMaster withName(String name) {
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
	public MissionGroupModelMaster withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	/** ミッショングループの説明 */
	protected String description;

	/**
	 * ミッショングループの説明を取得
	 *
	 * @return ミッショングループの説明
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * ミッショングループの説明を設定
	 *
	 * @param description ミッショングループの説明
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * ミッショングループの説明を設定
	 *
	 * @param description ミッショングループの説明
	 * @return this
	 */
	public MissionGroupModelMaster withDescription(String description) {
		this.description = description;
		return this;
	}
	/** ミッションを達成したときの通知先ネームスペース のGRN */
	protected String completeNotificationNamespaceId;

	/**
	 * ミッションを達成したときの通知先ネームスペース のGRNを取得
	 *
	 * @return ミッションを達成したときの通知先ネームスペース のGRN
	 */
	public String getCompleteNotificationNamespaceId() {
		return completeNotificationNamespaceId;
	}

	/**
	 * ミッションを達成したときの通知先ネームスペース のGRNを設定
	 *
	 * @param completeNotificationNamespaceId ミッションを達成したときの通知先ネームスペース のGRN
	 */
	public void setCompleteNotificationNamespaceId(String completeNotificationNamespaceId) {
		this.completeNotificationNamespaceId = completeNotificationNamespaceId;
	}

	/**
	 * ミッションを達成したときの通知先ネームスペース のGRNを設定
	 *
	 * @param completeNotificationNamespaceId ミッションを達成したときの通知先ネームスペース のGRN
	 * @return this
	 */
	public MissionGroupModelMaster withCompleteNotificationNamespaceId(String completeNotificationNamespaceId) {
		this.completeNotificationNamespaceId = completeNotificationNamespaceId;
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
	public MissionGroupModelMaster withCreatedAt(Long createdAt) {
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
	public MissionGroupModelMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("missionGroupId", this.getMissionGroupId())
            .put("name", this.getName())
            .put("metadata", this.getMetadata())
            .put("description", this.getDescription())
            .put("completeNotificationNamespaceId", this.getCompleteNotificationNamespaceId())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        return body_;
    }
	@Override
	public int compareTo(MissionGroupModelMaster o) {
		return missionGroupId.compareTo(o.missionGroupId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.missionGroupId == null) ? 0 : this.missionGroupId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.completeNotificationNamespaceId == null) ? 0 : this.completeNotificationNamespaceId.hashCode());
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
		MissionGroupModelMaster other = (MissionGroupModelMaster) o;
		if (missionGroupId == null) {
			return other.missionGroupId == null;
		} else if (!missionGroupId.equals(other.missionGroupId)) {
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
		if (completeNotificationNamespaceId == null) {
			return other.completeNotificationNamespaceId == null;
		} else if (!completeNotificationNamespaceId.equals(other.completeNotificationNamespaceId)) {
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
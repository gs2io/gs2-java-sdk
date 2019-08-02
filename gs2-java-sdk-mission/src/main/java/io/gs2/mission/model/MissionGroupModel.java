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
 * ミッショングループ
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class MissionGroupModel implements IModel, Serializable, Comparable<MissionGroupModel> {
	/** ミッショングループ */
	protected String missionGroupId;

	/**
	 * ミッショングループを取得
	 *
	 * @return ミッショングループ
	 */
	public String getMissionGroupId() {
		return missionGroupId;
	}

	/**
	 * ミッショングループを設定
	 *
	 * @param missionGroupId ミッショングループ
	 */
	public void setMissionGroupId(String missionGroupId) {
		this.missionGroupId = missionGroupId;
	}

	/**
	 * ミッショングループを設定
	 *
	 * @param missionGroupId ミッショングループ
	 * @return this
	 */
	public MissionGroupModel withMissionGroupId(String missionGroupId) {
		this.missionGroupId = missionGroupId;
		return this;
	}
	/** グループ名 */
	protected String name;

	/**
	 * グループ名を取得
	 *
	 * @return グループ名
	 */
	public String getName() {
		return name;
	}

	/**
	 * グループ名を設定
	 *
	 * @param name グループ名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * グループ名を設定
	 *
	 * @param name グループ名
	 * @return this
	 */
	public MissionGroupModel withName(String name) {
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
	public MissionGroupModel withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	/** タスクリスト */
	protected List<MissionTaskModel> tasks;

	/**
	 * タスクリストを取得
	 *
	 * @return タスクリスト
	 */
	public List<MissionTaskModel> getTasks() {
		return tasks;
	}

	/**
	 * タスクリストを設定
	 *
	 * @param tasks タスクリスト
	 */
	public void setTasks(List<MissionTaskModel> tasks) {
		this.tasks = tasks;
	}

	/**
	 * タスクリストを設定
	 *
	 * @param tasks タスクリスト
	 * @return this
	 */
	public MissionGroupModel withTasks(List<MissionTaskModel> tasks) {
		this.tasks = tasks;
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
	public MissionGroupModel withCompleteNotificationNamespaceId(String completeNotificationNamespaceId) {
		this.completeNotificationNamespaceId = completeNotificationNamespaceId;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> tasks = new ArrayList<>();
        if(this.tasks != null) {
            for(MissionTaskModel item : this.tasks) {
                tasks.add(item.toJson());
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("missionGroupId", this.getMissionGroupId())
            .put("name", this.getName())
            .put("metadata", this.getMetadata())
            .put("completeNotificationNamespaceId", this.getCompleteNotificationNamespaceId());
        body_.set("tasks", JsonNodeFactory.instance.arrayNode().addAll(tasks));
        return body_;
    }
	@Override
	public int compareTo(MissionGroupModel o) {
		return missionGroupId.compareTo(o.missionGroupId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.missionGroupId == null) ? 0 : this.missionGroupId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.tasks == null) ? 0 : this.tasks.hashCode());
        result = prime * result + ((this.completeNotificationNamespaceId == null) ? 0 : this.completeNotificationNamespaceId.hashCode());
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
		MissionGroupModel other = (MissionGroupModel) o;
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
		if (tasks == null) {
			return other.tasks == null;
		} else if (!tasks.equals(other.tasks)) {
			return false;
		}
		if (completeNotificationNamespaceId == null) {
			return other.completeNotificationNamespaceId == null;
		} else if (!completeNotificationNamespaceId.equals(other.completeNotificationNamespaceId)) {
			return false;
		}
		return true;
	}
}
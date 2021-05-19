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
import io.gs2.core.model.IModel;

/**
 * 達成状況
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Complete implements IModel, Serializable, Comparable<Complete> {
	/** 達成状況 */
	protected String completeId;

	/**
	 * 達成状況を取得
	 *
	 * @return 達成状況
	 */
	public String getCompleteId() {
		return completeId;
	}

	/**
	 * 達成状況を設定
	 *
	 * @param completeId 達成状況
	 */
	public void setCompleteId(String completeId) {
		this.completeId = completeId;
	}

	/**
	 * 達成状況を設定
	 *
	 * @param completeId 達成状況
	 * @return this
	 */
	public Complete withCompleteId(String completeId) {
		this.completeId = completeId;
		return this;
	}
	/** ユーザーID */
	protected String userId;

	/**
	 * ユーザーIDを取得
	 *
	 * @return ユーザーID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * ユーザーIDを設定
	 *
	 * @param userId ユーザーID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * ユーザーIDを設定
	 *
	 * @param userId ユーザーID
	 * @return this
	 */
	public Complete withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	/** ミッショングループ名 */
	protected String missionGroupName;

	/**
	 * ミッショングループ名を取得
	 *
	 * @return ミッショングループ名
	 */
	public String getMissionGroupName() {
		return missionGroupName;
	}

	/**
	 * ミッショングループ名を設定
	 *
	 * @param missionGroupName ミッショングループ名
	 */
	public void setMissionGroupName(String missionGroupName) {
		this.missionGroupName = missionGroupName;
	}

	/**
	 * ミッショングループ名を設定
	 *
	 * @param missionGroupName ミッショングループ名
	 * @return this
	 */
	public Complete withMissionGroupName(String missionGroupName) {
		this.missionGroupName = missionGroupName;
		return this;
	}
	/** 達成済みのタスク名リスト */
	protected List<String> completedMissionTaskNames;

	/**
	 * 達成済みのタスク名リストを取得
	 *
	 * @return 達成済みのタスク名リスト
	 */
	public List<String> getCompletedMissionTaskNames() {
		return completedMissionTaskNames;
	}

	/**
	 * 達成済みのタスク名リストを設定
	 *
	 * @param completedMissionTaskNames 達成済みのタスク名リスト
	 */
	public void setCompletedMissionTaskNames(List<String> completedMissionTaskNames) {
		this.completedMissionTaskNames = completedMissionTaskNames;
	}

	/**
	 * 達成済みのタスク名リストを設定
	 *
	 * @param completedMissionTaskNames 達成済みのタスク名リスト
	 * @return this
	 */
	public Complete withCompletedMissionTaskNames(List<String> completedMissionTaskNames) {
		this.completedMissionTaskNames = completedMissionTaskNames;
		return this;
	}
	/** 報酬の受け取り済みのタスク名リスト */
	protected List<String> receivedMissionTaskNames;

	/**
	 * 報酬の受け取り済みのタスク名リストを取得
	 *
	 * @return 報酬の受け取り済みのタスク名リスト
	 */
	public List<String> getReceivedMissionTaskNames() {
		return receivedMissionTaskNames;
	}

	/**
	 * 報酬の受け取り済みのタスク名リストを設定
	 *
	 * @param receivedMissionTaskNames 報酬の受け取り済みのタスク名リスト
	 */
	public void setReceivedMissionTaskNames(List<String> receivedMissionTaskNames) {
		this.receivedMissionTaskNames = receivedMissionTaskNames;
	}

	/**
	 * 報酬の受け取り済みのタスク名リストを設定
	 *
	 * @param receivedMissionTaskNames 報酬の受け取り済みのタスク名リスト
	 * @return this
	 */
	public Complete withReceivedMissionTaskNames(List<String> receivedMissionTaskNames) {
		this.receivedMissionTaskNames = receivedMissionTaskNames;
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
	public Complete withCreatedAt(Long createdAt) {
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
	public Complete withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> completedMissionTaskNames = new ArrayList<>();
        if(this.completedMissionTaskNames != null) {
            for(String item : this.completedMissionTaskNames) {
                completedMissionTaskNames.add(JsonNodeFactory.instance.textNode(item));
            }
        }
        List<JsonNode> receivedMissionTaskNames = new ArrayList<>();
        if(this.receivedMissionTaskNames != null) {
            for(String item : this.receivedMissionTaskNames) {
                receivedMissionTaskNames.add(JsonNodeFactory.instance.textNode(item));
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("completeId", this.getCompleteId())
            .put("userId", this.getUserId())
            .put("missionGroupName", this.getMissionGroupName())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        body_.set("completedMissionTaskNames", JsonNodeFactory.instance.arrayNode().addAll(completedMissionTaskNames));
        body_.set("receivedMissionTaskNames", JsonNodeFactory.instance.arrayNode().addAll(receivedMissionTaskNames));
        return body_;
    }
	@Override
	public int compareTo(Complete o) {
		return completeId.compareTo(o.completeId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.completeId == null) ? 0 : this.completeId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.missionGroupName == null) ? 0 : this.missionGroupName.hashCode());
        result = prime * result + ((this.completedMissionTaskNames == null) ? 0 : this.completedMissionTaskNames.hashCode());
        result = prime * result + ((this.receivedMissionTaskNames == null) ? 0 : this.receivedMissionTaskNames.hashCode());
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
		Complete other = (Complete) o;
		if (completeId == null) {
			return other.completeId == null;
		} else if (!completeId.equals(other.completeId)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (missionGroupName == null) {
			return other.missionGroupName == null;
		} else if (!missionGroupName.equals(other.missionGroupName)) {
			return false;
		}
		if (completedMissionTaskNames == null) {
			return other.completedMissionTaskNames == null;
		} else if (!completedMissionTaskNames.equals(other.completedMissionTaskNames)) {
			return false;
		}
		if (receivedMissionTaskNames == null) {
			return other.receivedMissionTaskNames == null;
		} else if (!receivedMissionTaskNames.equals(other.receivedMissionTaskNames)) {
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
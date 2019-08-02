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

package io.gs2.experience.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.model.IModel;

/**
 * ステータス
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Status implements IModel, Serializable, Comparable<Status> {
	/** ステータス */
	protected String statusId;

	/**
	 * ステータスを取得
	 *
	 * @return ステータス
	 */
	public String getStatusId() {
		return statusId;
	}

	/**
	 * ステータスを設定
	 *
	 * @param statusId ステータス
	 */
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	/**
	 * ステータスを設定
	 *
	 * @param statusId ステータス
	 * @return this
	 */
	public Status withStatusId(String statusId) {
		this.statusId = statusId;
		return this;
	}
	/** 経験値の種類の名前 */
	protected String experienceName;

	/**
	 * 経験値の種類の名前を取得
	 *
	 * @return 経験値の種類の名前
	 */
	public String getExperienceName() {
		return experienceName;
	}

	/**
	 * 経験値の種類の名前を設定
	 *
	 * @param experienceName 経験値の種類の名前
	 */
	public void setExperienceName(String experienceName) {
		this.experienceName = experienceName;
	}

	/**
	 * 経験値の種類の名前を設定
	 *
	 * @param experienceName 経験値の種類の名前
	 * @return this
	 */
	public Status withExperienceName(String experienceName) {
		this.experienceName = experienceName;
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
	public Status withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	/** プロパティID */
	protected String propertyId;

	/**
	 * プロパティIDを取得
	 *
	 * @return プロパティID
	 */
	public String getPropertyId() {
		return propertyId;
	}

	/**
	 * プロパティIDを設定
	 *
	 * @param propertyId プロパティID
	 */
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	/**
	 * プロパティIDを設定
	 *
	 * @param propertyId プロパティID
	 * @return this
	 */
	public Status withPropertyId(String propertyId) {
		this.propertyId = propertyId;
		return this;
	}
	/** 累計獲得経験値 */
	protected Long experienceValue;

	/**
	 * 累計獲得経験値を取得
	 *
	 * @return 累計獲得経験値
	 */
	public Long getExperienceValue() {
		return experienceValue;
	}

	/**
	 * 累計獲得経験値を設定
	 *
	 * @param experienceValue 累計獲得経験値
	 */
	public void setExperienceValue(Long experienceValue) {
		this.experienceValue = experienceValue;
	}

	/**
	 * 累計獲得経験値を設定
	 *
	 * @param experienceValue 累計獲得経験値
	 * @return this
	 */
	public Status withExperienceValue(Long experienceValue) {
		this.experienceValue = experienceValue;
		return this;
	}
	/** 現在のランク */
	protected Long rankValue;

	/**
	 * 現在のランクを取得
	 *
	 * @return 現在のランク
	 */
	public Long getRankValue() {
		return rankValue;
	}

	/**
	 * 現在のランクを設定
	 *
	 * @param rankValue 現在のランク
	 */
	public void setRankValue(Long rankValue) {
		this.rankValue = rankValue;
	}

	/**
	 * 現在のランクを設定
	 *
	 * @param rankValue 現在のランク
	 * @return this
	 */
	public Status withRankValue(Long rankValue) {
		this.rankValue = rankValue;
		return this;
	}
	/** 現在のランクキャップ */
	protected Long rankCapValue;

	/**
	 * 現在のランクキャップを取得
	 *
	 * @return 現在のランクキャップ
	 */
	public Long getRankCapValue() {
		return rankCapValue;
	}

	/**
	 * 現在のランクキャップを設定
	 *
	 * @param rankCapValue 現在のランクキャップ
	 */
	public void setRankCapValue(Long rankCapValue) {
		this.rankCapValue = rankCapValue;
	}

	/**
	 * 現在のランクキャップを設定
	 *
	 * @param rankCapValue 現在のランクキャップ
	 * @return this
	 */
	public Status withRankCapValue(Long rankCapValue) {
		this.rankCapValue = rankCapValue;
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
	public Status withCreatedAt(Long createdAt) {
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
	public Status withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("statusId", this.getStatusId())
            .put("experienceName", this.getExperienceName())
            .put("userId", this.getUserId())
            .put("propertyId", this.getPropertyId())
            .put("experienceValue", this.getExperienceValue())
            .put("rankValue", this.getRankValue())
            .put("rankCapValue", this.getRankCapValue())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        return body_;
    }
	@Override
	public int compareTo(Status o) {
		return statusId.compareTo(o.statusId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.statusId == null) ? 0 : this.statusId.hashCode());
        result = prime * result + ((this.experienceName == null) ? 0 : this.experienceName.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.propertyId == null) ? 0 : this.propertyId.hashCode());
        result = prime * result + ((this.experienceValue == null) ? 0 : this.experienceValue.hashCode());
        result = prime * result + ((this.rankValue == null) ? 0 : this.rankValue.hashCode());
        result = prime * result + ((this.rankCapValue == null) ? 0 : this.rankCapValue.hashCode());
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
		Status other = (Status) o;
		if (statusId == null) {
			return other.statusId == null;
		} else if (!statusId.equals(other.statusId)) {
			return false;
		}
		if (experienceName == null) {
			return other.experienceName == null;
		} else if (!experienceName.equals(other.experienceName)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (propertyId == null) {
			return other.propertyId == null;
		} else if (!propertyId.equals(other.propertyId)) {
			return false;
		}
		if (experienceValue == null) {
			return other.experienceValue == null;
		} else if (!experienceValue.equals(other.experienceValue)) {
			return false;
		}
		if (rankValue == null) {
			return other.rankValue == null;
		} else if (!rankValue.equals(other.rankValue)) {
			return false;
		}
		if (rankCapValue == null) {
			return other.rankCapValue == null;
		} else if (!rankCapValue.equals(other.rankCapValue)) {
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
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

package io.gs2.enhance.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * 強化実行
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Progress implements IModel, Serializable, Comparable<Progress> {
	/** 強化実行 */
	protected String progressId;

	/**
	 * 強化実行を取得
	 *
	 * @return 強化実行
	 */
	public String getProgressId() {
		return progressId;
	}

	/**
	 * 強化実行を設定
	 *
	 * @param progressId 強化実行
	 */
	public void setProgressId(String progressId) {
		this.progressId = progressId;
	}

	/**
	 * 強化実行を設定
	 *
	 * @param progressId 強化実行
	 * @return this
	 */
	public Progress withProgressId(String progressId) {
		this.progressId = progressId;
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
	public Progress withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	/** レートモデル名 */
	protected String rateName;

	/**
	 * レートモデル名を取得
	 *
	 * @return レートモデル名
	 */
	public String getRateName() {
		return rateName;
	}

	/**
	 * レートモデル名を設定
	 *
	 * @param rateName レートモデル名
	 */
	public void setRateName(String rateName) {
		this.rateName = rateName;
	}

	/**
	 * レートモデル名を設定
	 *
	 * @param rateName レートモデル名
	 * @return this
	 */
	public Progress withRateName(String rateName) {
		this.rateName = rateName;
		return this;
	}
	/** 強化対象のプロパティID */
	protected String propertyId;

	/**
	 * 強化対象のプロパティIDを取得
	 *
	 * @return 強化対象のプロパティID
	 */
	public String getPropertyId() {
		return propertyId;
	}

	/**
	 * 強化対象のプロパティIDを設定
	 *
	 * @param propertyId 強化対象のプロパティID
	 */
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	/**
	 * 強化対象のプロパティIDを設定
	 *
	 * @param propertyId 強化対象のプロパティID
	 * @return this
	 */
	public Progress withPropertyId(String propertyId) {
		this.propertyId = propertyId;
		return this;
	}
	/** 入手できる経験値 */
	protected Integer experienceValue;

	/**
	 * 入手できる経験値を取得
	 *
	 * @return 入手できる経験値
	 */
	public Integer getExperienceValue() {
		return experienceValue;
	}

	/**
	 * 入手できる経験値を設定
	 *
	 * @param experienceValue 入手できる経験値
	 */
	public void setExperienceValue(Integer experienceValue) {
		this.experienceValue = experienceValue;
	}

	/**
	 * 入手できる経験値を設定
	 *
	 * @param experienceValue 入手できる経験値
	 * @return this
	 */
	public Progress withExperienceValue(Integer experienceValue) {
		this.experienceValue = experienceValue;
		return this;
	}
	/** 経験値倍率 */
	protected Float rate;

	/**
	 * 経験値倍率を取得
	 *
	 * @return 経験値倍率
	 */
	public Float getRate() {
		return rate;
	}

	/**
	 * 経験値倍率を設定
	 *
	 * @param rate 経験値倍率
	 */
	public void setRate(Float rate) {
		this.rate = rate;
	}

	/**
	 * 経験値倍率を設定
	 *
	 * @param rate 経験値倍率
	 * @return this
	 */
	public Progress withRate(Float rate) {
		this.rate = rate;
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
	public Progress withCreatedAt(Long createdAt) {
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
	public Progress withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("progressId", this.getProgressId())
            .put("userId", this.getUserId())
            .put("rateName", this.getRateName())
            .put("propertyId", this.getPropertyId())
            .put("experienceValue", this.getExperienceValue())
            .put("rate", this.getRate())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        return body_;
    }
	@Override
	public int compareTo(Progress o) {
		return progressId.compareTo(o.progressId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.progressId == null) ? 0 : this.progressId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.rateName == null) ? 0 : this.rateName.hashCode());
        result = prime * result + ((this.propertyId == null) ? 0 : this.propertyId.hashCode());
        result = prime * result + ((this.experienceValue == null) ? 0 : this.experienceValue.hashCode());
        result = prime * result + ((this.rate == null) ? 0 : this.rate.hashCode());
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
		Progress other = (Progress) o;
		if (progressId == null) {
			return other.progressId == null;
		} else if (!progressId.equals(other.progressId)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (rateName == null) {
			return other.rateName == null;
		} else if (!rateName.equals(other.rateName)) {
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
		if (rate == null) {
			return other.rate == null;
		} else if (!rate.equals(other.rate)) {
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
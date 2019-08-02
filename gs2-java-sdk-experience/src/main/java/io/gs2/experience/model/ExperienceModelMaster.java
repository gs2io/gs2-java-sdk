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
 * 経験値の種類マスター
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class ExperienceModelMaster implements IModel, Serializable, Comparable<ExperienceModelMaster> {
	/** 経験値の種類マスター */
	protected String experienceModelId;

	/**
	 * 経験値の種類マスターを取得
	 *
	 * @return 経験値の種類マスター
	 */
	public String getExperienceModelId() {
		return experienceModelId;
	}

	/**
	 * 経験値の種類マスターを設定
	 *
	 * @param experienceModelId 経験値の種類マスター
	 */
	public void setExperienceModelId(String experienceModelId) {
		this.experienceModelId = experienceModelId;
	}

	/**
	 * 経験値の種類マスターを設定
	 *
	 * @param experienceModelId 経験値の種類マスター
	 * @return this
	 */
	public ExperienceModelMaster withExperienceModelId(String experienceModelId) {
		this.experienceModelId = experienceModelId;
		return this;
	}
	/** 経験値の種類名 */
	protected String name;

	/**
	 * 経験値の種類名を取得
	 *
	 * @return 経験値の種類名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 経験値の種類名を設定
	 *
	 * @param name 経験値の種類名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 経験値の種類名を設定
	 *
	 * @param name 経験値の種類名
	 * @return this
	 */
	public ExperienceModelMaster withName(String name) {
		this.name = name;
		return this;
	}
	/** 経験値の種類マスターの説明 */
	protected String description;

	/**
	 * 経験値の種類マスターの説明を取得
	 *
	 * @return 経験値の種類マスターの説明
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 経験値の種類マスターの説明を設定
	 *
	 * @param description 経験値の種類マスターの説明
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 経験値の種類マスターの説明を設定
	 *
	 * @param description 経験値の種類マスターの説明
	 * @return this
	 */
	public ExperienceModelMaster withDescription(String description) {
		this.description = description;
		return this;
	}
	/** 経験値の種類のメタデータ */
	protected String metadata;

	/**
	 * 経験値の種類のメタデータを取得
	 *
	 * @return 経験値の種類のメタデータ
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * 経験値の種類のメタデータを設定
	 *
	 * @param metadata 経験値の種類のメタデータ
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	/**
	 * 経験値の種類のメタデータを設定
	 *
	 * @param metadata 経験値の種類のメタデータ
	 * @return this
	 */
	public ExperienceModelMaster withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	/** 経験値の初期値 */
	protected Long defaultExperience;

	/**
	 * 経験値の初期値を取得
	 *
	 * @return 経験値の初期値
	 */
	public Long getDefaultExperience() {
		return defaultExperience;
	}

	/**
	 * 経験値の初期値を設定
	 *
	 * @param defaultExperience 経験値の初期値
	 */
	public void setDefaultExperience(Long defaultExperience) {
		this.defaultExperience = defaultExperience;
	}

	/**
	 * 経験値の初期値を設定
	 *
	 * @param defaultExperience 経験値の初期値
	 * @return this
	 */
	public ExperienceModelMaster withDefaultExperience(Long defaultExperience) {
		this.defaultExperience = defaultExperience;
		return this;
	}
	/** ランクキャップの初期値 */
	protected Long defaultRankCap;

	/**
	 * ランクキャップの初期値を取得
	 *
	 * @return ランクキャップの初期値
	 */
	public Long getDefaultRankCap() {
		return defaultRankCap;
	}

	/**
	 * ランクキャップの初期値を設定
	 *
	 * @param defaultRankCap ランクキャップの初期値
	 */
	public void setDefaultRankCap(Long defaultRankCap) {
		this.defaultRankCap = defaultRankCap;
	}

	/**
	 * ランクキャップの初期値を設定
	 *
	 * @param defaultRankCap ランクキャップの初期値
	 * @return this
	 */
	public ExperienceModelMaster withDefaultRankCap(Long defaultRankCap) {
		this.defaultRankCap = defaultRankCap;
		return this;
	}
	/** ランクキャップの最大値 */
	protected Long maxRankCap;

	/**
	 * ランクキャップの最大値を取得
	 *
	 * @return ランクキャップの最大値
	 */
	public Long getMaxRankCap() {
		return maxRankCap;
	}

	/**
	 * ランクキャップの最大値を設定
	 *
	 * @param maxRankCap ランクキャップの最大値
	 */
	public void setMaxRankCap(Long maxRankCap) {
		this.maxRankCap = maxRankCap;
	}

	/**
	 * ランクキャップの最大値を設定
	 *
	 * @param maxRankCap ランクキャップの最大値
	 * @return this
	 */
	public ExperienceModelMaster withMaxRankCap(Long maxRankCap) {
		this.maxRankCap = maxRankCap;
		return this;
	}
	/** ランク計算に用いる */
	protected String rankThresholdId;

	/**
	 * ランク計算に用いるを取得
	 *
	 * @return ランク計算に用いる
	 */
	public String getRankThresholdId() {
		return rankThresholdId;
	}

	/**
	 * ランク計算に用いるを設定
	 *
	 * @param rankThresholdId ランク計算に用いる
	 */
	public void setRankThresholdId(String rankThresholdId) {
		this.rankThresholdId = rankThresholdId;
	}

	/**
	 * ランク計算に用いるを設定
	 *
	 * @param rankThresholdId ランク計算に用いる
	 * @return this
	 */
	public ExperienceModelMaster withRankThresholdId(String rankThresholdId) {
		this.rankThresholdId = rankThresholdId;
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
	public ExperienceModelMaster withCreatedAt(Long createdAt) {
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
	public ExperienceModelMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("experienceModelId", this.getExperienceModelId())
            .put("name", this.getName())
            .put("description", this.getDescription())
            .put("metadata", this.getMetadata())
            .put("defaultExperience", this.getDefaultExperience())
            .put("defaultRankCap", this.getDefaultRankCap())
            .put("maxRankCap", this.getMaxRankCap())
            .put("rankThresholdId", this.getRankThresholdId())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        return body_;
    }
	@Override
	public int compareTo(ExperienceModelMaster o) {
		return experienceModelId.compareTo(o.experienceModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.experienceModelId == null) ? 0 : this.experienceModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.defaultExperience == null) ? 0 : this.defaultExperience.hashCode());
        result = prime * result + ((this.defaultRankCap == null) ? 0 : this.defaultRankCap.hashCode());
        result = prime * result + ((this.maxRankCap == null) ? 0 : this.maxRankCap.hashCode());
        result = prime * result + ((this.rankThresholdId == null) ? 0 : this.rankThresholdId.hashCode());
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
		ExperienceModelMaster other = (ExperienceModelMaster) o;
		if (experienceModelId == null) {
			return other.experienceModelId == null;
		} else if (!experienceModelId.equals(other.experienceModelId)) {
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
		if (metadata == null) {
			return other.metadata == null;
		} else if (!metadata.equals(other.metadata)) {
			return false;
		}
		if (defaultExperience == null) {
			return other.defaultExperience == null;
		} else if (!defaultExperience.equals(other.defaultExperience)) {
			return false;
		}
		if (defaultRankCap == null) {
			return other.defaultRankCap == null;
		} else if (!defaultRankCap.equals(other.defaultRankCap)) {
			return false;
		}
		if (maxRankCap == null) {
			return other.maxRankCap == null;
		} else if (!maxRankCap.equals(other.maxRankCap)) {
			return false;
		}
		if (rankThresholdId == null) {
			return other.rankThresholdId == null;
		} else if (!rankThresholdId.equals(other.rankThresholdId)) {
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
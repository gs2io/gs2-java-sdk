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
 * 経験値・ランクアップ閾値モデル
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class ExperienceModel implements IModel, Serializable, Comparable<ExperienceModel> {
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
	public ExperienceModel withExperienceModelId(String experienceModelId) {
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
	public ExperienceModel withName(String name) {
		this.name = name;
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
	public ExperienceModel withMetadata(String metadata) {
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
	public ExperienceModel withDefaultExperience(Long defaultExperience) {
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
	public ExperienceModel withDefaultRankCap(Long defaultRankCap) {
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
	public ExperienceModel withMaxRankCap(Long maxRankCap) {
		this.maxRankCap = maxRankCap;
		return this;
	}
	/** ランクアップ閾値 */
	protected Threshold rankThreshold;

	/**
	 * ランクアップ閾値を取得
	 *
	 * @return ランクアップ閾値
	 */
	public Threshold getRankThreshold() {
		return rankThreshold;
	}

	/**
	 * ランクアップ閾値を設定
	 *
	 * @param rankThreshold ランクアップ閾値
	 */
	public void setRankThreshold(Threshold rankThreshold) {
		this.rankThreshold = rankThreshold;
	}

	/**
	 * ランクアップ閾値を設定
	 *
	 * @param rankThreshold ランクアップ閾値
	 * @return this
	 */
	public ExperienceModel withRankThreshold(Threshold rankThreshold) {
		this.rankThreshold = rankThreshold;
		return this;
	}

    public ObjectNode toJson() {
        JsonNode rankThreshold = this.getRankThreshold().toJson();
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("experienceModelId", this.getExperienceModelId())
            .put("name", this.getName())
            .put("metadata", this.getMetadata())
            .put("defaultExperience", this.getDefaultExperience())
            .put("defaultRankCap", this.getDefaultRankCap())
            .put("maxRankCap", this.getMaxRankCap());
        body_.set("rankThreshold", rankThreshold);
        return body_;
    }
	@Override
	public int compareTo(ExperienceModel o) {
		return experienceModelId.compareTo(o.experienceModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.experienceModelId == null) ? 0 : this.experienceModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.defaultExperience == null) ? 0 : this.defaultExperience.hashCode());
        result = prime * result + ((this.defaultRankCap == null) ? 0 : this.defaultRankCap.hashCode());
        result = prime * result + ((this.maxRankCap == null) ? 0 : this.maxRankCap.hashCode());
        result = prime * result + ((this.rankThreshold == null) ? 0 : this.rankThreshold.hashCode());
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
		ExperienceModel other = (ExperienceModel) o;
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
		if (rankThreshold == null) {
			return other.rankThreshold == null;
		} else if (!rankThreshold.equals(other.rankThreshold)) {
			return false;
		}
		return true;
	}
}
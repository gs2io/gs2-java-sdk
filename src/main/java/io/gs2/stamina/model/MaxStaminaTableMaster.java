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

package io.gs2.stamina.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * スタミナの最大値テーブルマスター
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class MaxStaminaTableMaster implements IModel, Serializable, Comparable<MaxStaminaTableMaster> {
	/** スタミナの最大値テーブルマスター */
	protected String maxStaminaTableId;

	/**
	 * スタミナの最大値テーブルマスターを取得
	 *
	 * @return スタミナの最大値テーブルマスター
	 */
	public String getMaxStaminaTableId() {
		return maxStaminaTableId;
	}

	/**
	 * スタミナの最大値テーブルマスターを設定
	 *
	 * @param maxStaminaTableId スタミナの最大値テーブルマスター
	 */
	public void setMaxStaminaTableId(String maxStaminaTableId) {
		this.maxStaminaTableId = maxStaminaTableId;
	}

	/**
	 * スタミナの最大値テーブルマスターを設定
	 *
	 * @param maxStaminaTableId スタミナの最大値テーブルマスター
	 * @return this
	 */
	public MaxStaminaTableMaster withMaxStaminaTableId(String maxStaminaTableId) {
		this.maxStaminaTableId = maxStaminaTableId;
		return this;
	}
	/** 最大スタミナ値テーブル名 */
	protected String name;

	/**
	 * 最大スタミナ値テーブル名を取得
	 *
	 * @return 最大スタミナ値テーブル名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 最大スタミナ値テーブル名を設定
	 *
	 * @param name 最大スタミナ値テーブル名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 最大スタミナ値テーブル名を設定
	 *
	 * @param name 最大スタミナ値テーブル名
	 * @return this
	 */
	public MaxStaminaTableMaster withName(String name) {
		this.name = name;
		return this;
	}
	/** 最大スタミナ値テーブルのメタデータ */
	protected String metadata;

	/**
	 * 最大スタミナ値テーブルのメタデータを取得
	 *
	 * @return 最大スタミナ値テーブルのメタデータ
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * 最大スタミナ値テーブルのメタデータを設定
	 *
	 * @param metadata 最大スタミナ値テーブルのメタデータ
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	/**
	 * 最大スタミナ値テーブルのメタデータを設定
	 *
	 * @param metadata 最大スタミナ値テーブルのメタデータ
	 * @return this
	 */
	public MaxStaminaTableMaster withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	/** スタミナの最大値テーブルマスターの説明 */
	protected String description;

	/**
	 * スタミナの最大値テーブルマスターの説明を取得
	 *
	 * @return スタミナの最大値テーブルマスターの説明
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * スタミナの最大値テーブルマスターの説明を設定
	 *
	 * @param description スタミナの最大値テーブルマスターの説明
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * スタミナの最大値テーブルマスターの説明を設定
	 *
	 * @param description スタミナの最大値テーブルマスターの説明
	 * @return this
	 */
	public MaxStaminaTableMaster withDescription(String description) {
		this.description = description;
		return this;
	}
	/** 経験値の種類マスター のGRN */
	protected String experienceModelId;

	/**
	 * 経験値の種類マスター のGRNを取得
	 *
	 * @return 経験値の種類マスター のGRN
	 */
	public String getExperienceModelId() {
		return experienceModelId;
	}

	/**
	 * 経験値の種類マスター のGRNを設定
	 *
	 * @param experienceModelId 経験値の種類マスター のGRN
	 */
	public void setExperienceModelId(String experienceModelId) {
		this.experienceModelId = experienceModelId;
	}

	/**
	 * 経験値の種類マスター のGRNを設定
	 *
	 * @param experienceModelId 経験値の種類マスター のGRN
	 * @return this
	 */
	public MaxStaminaTableMaster withExperienceModelId(String experienceModelId) {
		this.experienceModelId = experienceModelId;
		return this;
	}
	/** ランク毎のスタミナの最大値テーブル */
	protected List<Integer> values;

	/**
	 * ランク毎のスタミナの最大値テーブルを取得
	 *
	 * @return ランク毎のスタミナの最大値テーブル
	 */
	public List<Integer> getValues() {
		return values;
	}

	/**
	 * ランク毎のスタミナの最大値テーブルを設定
	 *
	 * @param values ランク毎のスタミナの最大値テーブル
	 */
	public void setValues(List<Integer> values) {
		this.values = values;
	}

	/**
	 * ランク毎のスタミナの最大値テーブルを設定
	 *
	 * @param values ランク毎のスタミナの最大値テーブル
	 * @return this
	 */
	public MaxStaminaTableMaster withValues(List<Integer> values) {
		this.values = values;
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
	public MaxStaminaTableMaster withCreatedAt(Long createdAt) {
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
	public MaxStaminaTableMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> values = new ArrayList<>();
        if(this.values != null) {
            for(Integer item : this.values) {
                values.add(JsonNodeFactory.instance.numberNode(item));
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("maxStaminaTableId", this.getMaxStaminaTableId())
            .put("name", this.getName())
            .put("metadata", this.getMetadata())
            .put("description", this.getDescription())
            .put("experienceModelId", this.getExperienceModelId())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        body_.set("values", JsonNodeFactory.instance.arrayNode().addAll(values));
        return body_;
    }
	@Override
	public int compareTo(MaxStaminaTableMaster o) {
		return maxStaminaTableId.compareTo(o.maxStaminaTableId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.maxStaminaTableId == null) ? 0 : this.maxStaminaTableId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.experienceModelId == null) ? 0 : this.experienceModelId.hashCode());
        result = prime * result + ((this.values == null) ? 0 : this.values.hashCode());
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
		MaxStaminaTableMaster other = (MaxStaminaTableMaster) o;
		if (maxStaminaTableId == null) {
			return other.maxStaminaTableId == null;
		} else if (!maxStaminaTableId.equals(other.maxStaminaTableId)) {
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
		if (experienceModelId == null) {
			return other.experienceModelId == null;
		} else if (!experienceModelId.equals(other.experienceModelId)) {
			return false;
		}
		if (values == null) {
			return other.values == null;
		} else if (!values.equals(other.values)) {
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
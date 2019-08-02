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
 * ランクアップ閾値マスター
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class ThresholdMaster implements IModel, Serializable, Comparable<ThresholdMaster> {
	/** ランクアップ閾値マスター */
	protected String thresholdId;

	/**
	 * ランクアップ閾値マスターを取得
	 *
	 * @return ランクアップ閾値マスター
	 */
	public String getThresholdId() {
		return thresholdId;
	}

	/**
	 * ランクアップ閾値マスターを設定
	 *
	 * @param thresholdId ランクアップ閾値マスター
	 */
	public void setThresholdId(String thresholdId) {
		this.thresholdId = thresholdId;
	}

	/**
	 * ランクアップ閾値マスターを設定
	 *
	 * @param thresholdId ランクアップ閾値マスター
	 * @return this
	 */
	public ThresholdMaster withThresholdId(String thresholdId) {
		this.thresholdId = thresholdId;
		return this;
	}
	/** ランクアップ閾値名 */
	protected String name;

	/**
	 * ランクアップ閾値名を取得
	 *
	 * @return ランクアップ閾値名
	 */
	public String getName() {
		return name;
	}

	/**
	 * ランクアップ閾値名を設定
	 *
	 * @param name ランクアップ閾値名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ランクアップ閾値名を設定
	 *
	 * @param name ランクアップ閾値名
	 * @return this
	 */
	public ThresholdMaster withName(String name) {
		this.name = name;
		return this;
	}
	/** ランクアップ閾値マスターの説明 */
	protected String description;

	/**
	 * ランクアップ閾値マスターの説明を取得
	 *
	 * @return ランクアップ閾値マスターの説明
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * ランクアップ閾値マスターの説明を設定
	 *
	 * @param description ランクアップ閾値マスターの説明
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * ランクアップ閾値マスターの説明を設定
	 *
	 * @param description ランクアップ閾値マスターの説明
	 * @return this
	 */
	public ThresholdMaster withDescription(String description) {
		this.description = description;
		return this;
	}
	/** ランクアップ閾値のメタデータ */
	protected String metadata;

	/**
	 * ランクアップ閾値のメタデータを取得
	 *
	 * @return ランクアップ閾値のメタデータ
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * ランクアップ閾値のメタデータを設定
	 *
	 * @param metadata ランクアップ閾値のメタデータ
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	/**
	 * ランクアップ閾値のメタデータを設定
	 *
	 * @param metadata ランクアップ閾値のメタデータ
	 * @return this
	 */
	public ThresholdMaster withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	/** ランクアップ経験値閾値リスト */
	protected List<Long> values;

	/**
	 * ランクアップ経験値閾値リストを取得
	 *
	 * @return ランクアップ経験値閾値リスト
	 */
	public List<Long> getValues() {
		return values;
	}

	/**
	 * ランクアップ経験値閾値リストを設定
	 *
	 * @param values ランクアップ経験値閾値リスト
	 */
	public void setValues(List<Long> values) {
		this.values = values;
	}

	/**
	 * ランクアップ経験値閾値リストを設定
	 *
	 * @param values ランクアップ経験値閾値リスト
	 * @return this
	 */
	public ThresholdMaster withValues(List<Long> values) {
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
	public ThresholdMaster withCreatedAt(Long createdAt) {
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
	public ThresholdMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> values = new ArrayList<>();
        if(this.values != null) {
            for(Long item : this.values) {
                values.add(JsonNodeFactory.instance.numberNode(item));
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("thresholdId", this.getThresholdId())
            .put("name", this.getName())
            .put("description", this.getDescription())
            .put("metadata", this.getMetadata())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        body_.set("values", JsonNodeFactory.instance.arrayNode().addAll(values));
        return body_;
    }
	@Override
	public int compareTo(ThresholdMaster o) {
		return thresholdId.compareTo(o.thresholdId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.thresholdId == null) ? 0 : this.thresholdId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
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
		ThresholdMaster other = (ThresholdMaster) o;
		if (thresholdId == null) {
			return other.thresholdId == null;
		} else if (!thresholdId.equals(other.thresholdId)) {
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
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
import io.gs2.model.IModel;

/**
 * スタミナモデルマスター
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class StaminaModelMaster implements IModel, Serializable, Comparable<StaminaModelMaster> {
	/** スタミナモデルマスター */
	protected String staminaModelId;

	/**
	 * スタミナモデルマスターを取得
	 *
	 * @return スタミナモデルマスター
	 */
	public String getStaminaModelId() {
		return staminaModelId;
	}

	/**
	 * スタミナモデルマスターを設定
	 *
	 * @param staminaModelId スタミナモデルマスター
	 */
	public void setStaminaModelId(String staminaModelId) {
		this.staminaModelId = staminaModelId;
	}

	/**
	 * スタミナモデルマスターを設定
	 *
	 * @param staminaModelId スタミナモデルマスター
	 * @return this
	 */
	public StaminaModelMaster withStaminaModelId(String staminaModelId) {
		this.staminaModelId = staminaModelId;
		return this;
	}
	/** スタミナの種類名 */
	protected String name;

	/**
	 * スタミナの種類名を取得
	 *
	 * @return スタミナの種類名
	 */
	public String getName() {
		return name;
	}

	/**
	 * スタミナの種類名を設定
	 *
	 * @param name スタミナの種類名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * スタミナの種類名を設定
	 *
	 * @param name スタミナの種類名
	 * @return this
	 */
	public StaminaModelMaster withName(String name) {
		this.name = name;
		return this;
	}
	/** スタミナの種類のメタデータ */
	protected String metadata;

	/**
	 * スタミナの種類のメタデータを取得
	 *
	 * @return スタミナの種類のメタデータ
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * スタミナの種類のメタデータを設定
	 *
	 * @param metadata スタミナの種類のメタデータ
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	/**
	 * スタミナの種類のメタデータを設定
	 *
	 * @param metadata スタミナの種類のメタデータ
	 * @return this
	 */
	public StaminaModelMaster withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	/** スタミナモデルマスターの説明 */
	protected String description;

	/**
	 * スタミナモデルマスターの説明を取得
	 *
	 * @return スタミナモデルマスターの説明
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * スタミナモデルマスターの説明を設定
	 *
	 * @param description スタミナモデルマスターの説明
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * スタミナモデルマスターの説明を設定
	 *
	 * @param description スタミナモデルマスターの説明
	 * @return this
	 */
	public StaminaModelMaster withDescription(String description) {
		this.description = description;
		return this;
	}
	/** スタミナを回復する速度(分) */
	protected Integer recoverIntervalMinutes;

	/**
	 * スタミナを回復する速度(分)を取得
	 *
	 * @return スタミナを回復する速度(分)
	 */
	public Integer getRecoverIntervalMinutes() {
		return recoverIntervalMinutes;
	}

	/**
	 * スタミナを回復する速度(分)を設定
	 *
	 * @param recoverIntervalMinutes スタミナを回復する速度(分)
	 */
	public void setRecoverIntervalMinutes(Integer recoverIntervalMinutes) {
		this.recoverIntervalMinutes = recoverIntervalMinutes;
	}

	/**
	 * スタミナを回復する速度(分)を設定
	 *
	 * @param recoverIntervalMinutes スタミナを回復する速度(分)
	 * @return this
	 */
	public StaminaModelMaster withRecoverIntervalMinutes(Integer recoverIntervalMinutes) {
		this.recoverIntervalMinutes = recoverIntervalMinutes;
		return this;
	}
	/** 時間経過後に回復する量 */
	protected Integer recoverValue;

	/**
	 * 時間経過後に回復する量を取得
	 *
	 * @return 時間経過後に回復する量
	 */
	public Integer getRecoverValue() {
		return recoverValue;
	}

	/**
	 * 時間経過後に回復する量を設定
	 *
	 * @param recoverValue 時間経過後に回復する量
	 */
	public void setRecoverValue(Integer recoverValue) {
		this.recoverValue = recoverValue;
	}

	/**
	 * 時間経過後に回復する量を設定
	 *
	 * @param recoverValue 時間経過後に回復する量
	 * @return this
	 */
	public StaminaModelMaster withRecoverValue(Integer recoverValue) {
		this.recoverValue = recoverValue;
		return this;
	}
	/** スタミナの最大値の初期値 */
	protected Integer initialCapacity;

	/**
	 * スタミナの最大値の初期値を取得
	 *
	 * @return スタミナの最大値の初期値
	 */
	public Integer getInitialCapacity() {
		return initialCapacity;
	}

	/**
	 * スタミナの最大値の初期値を設定
	 *
	 * @param initialCapacity スタミナの最大値の初期値
	 */
	public void setInitialCapacity(Integer initialCapacity) {
		this.initialCapacity = initialCapacity;
	}

	/**
	 * スタミナの最大値の初期値を設定
	 *
	 * @param initialCapacity スタミナの最大値の初期値
	 * @return this
	 */
	public StaminaModelMaster withInitialCapacity(Integer initialCapacity) {
		this.initialCapacity = initialCapacity;
		return this;
	}
	/** 最大値を超えて回復するか */
	protected Boolean isOverflow;

	/**
	 * 最大値を超えて回復するかを取得
	 *
	 * @return 最大値を超えて回復するか
	 */
	public Boolean getIsOverflow() {
		return isOverflow;
	}

	/**
	 * 最大値を超えて回復するかを設定
	 *
	 * @param isOverflow 最大値を超えて回復するか
	 */
	public void setIsOverflow(Boolean isOverflow) {
		this.isOverflow = isOverflow;
	}

	/**
	 * 最大値を超えて回復するかを設定
	 *
	 * @param isOverflow 最大値を超えて回復するか
	 * @return this
	 */
	public StaminaModelMaster withIsOverflow(Boolean isOverflow) {
		this.isOverflow = isOverflow;
		return this;
	}
	/** 溢れた状況での最大値 */
	protected Integer maxCapacity;

	/**
	 * 溢れた状況での最大値を取得
	 *
	 * @return 溢れた状況での最大値
	 */
	public Integer getMaxCapacity() {
		return maxCapacity;
	}

	/**
	 * 溢れた状況での最大値を設定
	 *
	 * @param maxCapacity 溢れた状況での最大値
	 */
	public void setMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	/**
	 * 溢れた状況での最大値を設定
	 *
	 * @param maxCapacity 溢れた状況での最大値
	 * @return this
	 */
	public StaminaModelMaster withMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
		return this;
	}
	/** GS2-Experience のランクによって最大スタミナ値を決定するスタミナの最大値テーブルマスター のGRN */
	protected String maxStaminaTableId;

	/**
	 * GS2-Experience のランクによって最大スタミナ値を決定するスタミナの最大値テーブルマスター のGRNを取得
	 *
	 * @return GS2-Experience のランクによって最大スタミナ値を決定するスタミナの最大値テーブルマスター のGRN
	 */
	public String getMaxStaminaTableId() {
		return maxStaminaTableId;
	}

	/**
	 * GS2-Experience のランクによって最大スタミナ値を決定するスタミナの最大値テーブルマスター のGRNを設定
	 *
	 * @param maxStaminaTableId GS2-Experience のランクによって最大スタミナ値を決定するスタミナの最大値テーブルマスター のGRN
	 */
	public void setMaxStaminaTableId(String maxStaminaTableId) {
		this.maxStaminaTableId = maxStaminaTableId;
	}

	/**
	 * GS2-Experience のランクによって最大スタミナ値を決定するスタミナの最大値テーブルマスター のGRNを設定
	 *
	 * @param maxStaminaTableId GS2-Experience のランクによって最大スタミナ値を決定するスタミナの最大値テーブルマスター のGRN
	 * @return this
	 */
	public StaminaModelMaster withMaxStaminaTableId(String maxStaminaTableId) {
		this.maxStaminaTableId = maxStaminaTableId;
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
	public StaminaModelMaster withCreatedAt(Long createdAt) {
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
	public StaminaModelMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("staminaModelId", this.getStaminaModelId())
            .put("name", this.getName())
            .put("metadata", this.getMetadata())
            .put("description", this.getDescription())
            .put("recoverIntervalMinutes", this.getRecoverIntervalMinutes())
            .put("recoverValue", this.getRecoverValue())
            .put("initialCapacity", this.getInitialCapacity())
            .put("isOverflow", this.getIsOverflow())
            .put("maxCapacity", this.getMaxCapacity())
            .put("maxStaminaTableId", this.getMaxStaminaTableId())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        return body_;
    }
	@Override
	public int compareTo(StaminaModelMaster o) {
		return staminaModelId.compareTo(o.staminaModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.staminaModelId == null) ? 0 : this.staminaModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.recoverIntervalMinutes == null) ? 0 : this.recoverIntervalMinutes.hashCode());
        result = prime * result + ((this.recoverValue == null) ? 0 : this.recoverValue.hashCode());
        result = prime * result + ((this.initialCapacity == null) ? 0 : this.initialCapacity.hashCode());
        result = prime * result + ((this.isOverflow == null) ? 0 : this.isOverflow.hashCode());
        result = prime * result + ((this.maxCapacity == null) ? 0 : this.maxCapacity.hashCode());
        result = prime * result + ((this.maxStaminaTableId == null) ? 0 : this.maxStaminaTableId.hashCode());
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
		StaminaModelMaster other = (StaminaModelMaster) o;
		if (staminaModelId == null) {
			return other.staminaModelId == null;
		} else if (!staminaModelId.equals(other.staminaModelId)) {
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
		if (recoverIntervalMinutes == null) {
			return other.recoverIntervalMinutes == null;
		} else if (!recoverIntervalMinutes.equals(other.recoverIntervalMinutes)) {
			return false;
		}
		if (recoverValue == null) {
			return other.recoverValue == null;
		} else if (!recoverValue.equals(other.recoverValue)) {
			return false;
		}
		if (initialCapacity == null) {
			return other.initialCapacity == null;
		} else if (!initialCapacity.equals(other.initialCapacity)) {
			return false;
		}
		if (isOverflow == null) {
			return other.isOverflow == null;
		} else if (!isOverflow.equals(other.isOverflow)) {
			return false;
		}
		if (maxCapacity == null) {
			return other.maxCapacity == null;
		} else if (!maxCapacity.equals(other.maxCapacity)) {
			return false;
		}
		if (maxStaminaTableId == null) {
			return other.maxStaminaTableId == null;
		} else if (!maxStaminaTableId.equals(other.maxStaminaTableId)) {
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
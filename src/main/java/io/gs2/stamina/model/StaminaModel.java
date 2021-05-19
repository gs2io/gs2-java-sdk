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
 * スタミナモデル
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class StaminaModel implements IModel, Serializable, Comparable<StaminaModel> {
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
	public StaminaModel withStaminaModelId(String staminaModelId) {
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
	public StaminaModel withName(String name) {
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
	public StaminaModel withMetadata(String metadata) {
		this.metadata = metadata;
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
	public StaminaModel withRecoverIntervalMinutes(Integer recoverIntervalMinutes) {
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
	public StaminaModel withRecoverValue(Integer recoverValue) {
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
	public StaminaModel withInitialCapacity(Integer initialCapacity) {
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
	public StaminaModel withIsOverflow(Boolean isOverflow) {
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
	public StaminaModel withMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
		return this;
	}
	/** GS2-Experience と連携する際に使用するスタミナ最大値テーブル */
	protected MaxStaminaTable maxStaminaTable;

	/**
	 * GS2-Experience と連携する際に使用するスタミナ最大値テーブルを取得
	 *
	 * @return GS2-Experience と連携する際に使用するスタミナ最大値テーブル
	 */
	public MaxStaminaTable getMaxStaminaTable() {
		return maxStaminaTable;
	}

	/**
	 * GS2-Experience と連携する際に使用するスタミナ最大値テーブルを設定
	 *
	 * @param maxStaminaTable GS2-Experience と連携する際に使用するスタミナ最大値テーブル
	 */
	public void setMaxStaminaTable(MaxStaminaTable maxStaminaTable) {
		this.maxStaminaTable = maxStaminaTable;
	}

	/**
	 * GS2-Experience と連携する際に使用するスタミナ最大値テーブルを設定
	 *
	 * @param maxStaminaTable GS2-Experience と連携する際に使用するスタミナ最大値テーブル
	 * @return this
	 */
	public StaminaModel withMaxStaminaTable(MaxStaminaTable maxStaminaTable) {
		this.maxStaminaTable = maxStaminaTable;
		return this;
	}
	/** GS2-Experience と連携する際に使用する回復間隔テーブル */
	protected RecoverIntervalTable recoverIntervalTable;

	/**
	 * GS2-Experience と連携する際に使用する回復間隔テーブルを取得
	 *
	 * @return GS2-Experience と連携する際に使用する回復間隔テーブル
	 */
	public RecoverIntervalTable getRecoverIntervalTable() {
		return recoverIntervalTable;
	}

	/**
	 * GS2-Experience と連携する際に使用する回復間隔テーブルを設定
	 *
	 * @param recoverIntervalTable GS2-Experience と連携する際に使用する回復間隔テーブル
	 */
	public void setRecoverIntervalTable(RecoverIntervalTable recoverIntervalTable) {
		this.recoverIntervalTable = recoverIntervalTable;
	}

	/**
	 * GS2-Experience と連携する際に使用する回復間隔テーブルを設定
	 *
	 * @param recoverIntervalTable GS2-Experience と連携する際に使用する回復間隔テーブル
	 * @return this
	 */
	public StaminaModel withRecoverIntervalTable(RecoverIntervalTable recoverIntervalTable) {
		this.recoverIntervalTable = recoverIntervalTable;
		return this;
	}
	/** GS2-Experience と連携する際に使用する回復量テーブル */
	protected RecoverValueTable recoverValueTable;

	/**
	 * GS2-Experience と連携する際に使用する回復量テーブルを取得
	 *
	 * @return GS2-Experience と連携する際に使用する回復量テーブル
	 */
	public RecoverValueTable getRecoverValueTable() {
		return recoverValueTable;
	}

	/**
	 * GS2-Experience と連携する際に使用する回復量テーブルを設定
	 *
	 * @param recoverValueTable GS2-Experience と連携する際に使用する回復量テーブル
	 */
	public void setRecoverValueTable(RecoverValueTable recoverValueTable) {
		this.recoverValueTable = recoverValueTable;
	}

	/**
	 * GS2-Experience と連携する際に使用する回復量テーブルを設定
	 *
	 * @param recoverValueTable GS2-Experience と連携する際に使用する回復量テーブル
	 * @return this
	 */
	public StaminaModel withRecoverValueTable(RecoverValueTable recoverValueTable) {
		this.recoverValueTable = recoverValueTable;
		return this;
	}

    public ObjectNode toJson() {
        JsonNode maxStaminaTable = this.getMaxStaminaTable().toJson();
        JsonNode recoverIntervalTable = this.getRecoverIntervalTable().toJson();
        JsonNode recoverValueTable = this.getRecoverValueTable().toJson();
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("staminaModelId", this.getStaminaModelId())
            .put("name", this.getName())
            .put("metadata", this.getMetadata())
            .put("recoverIntervalMinutes", this.getRecoverIntervalMinutes())
            .put("recoverValue", this.getRecoverValue())
            .put("initialCapacity", this.getInitialCapacity())
            .put("isOverflow", this.getIsOverflow())
            .put("maxCapacity", this.getMaxCapacity());
        body_.set("maxStaminaTable", maxStaminaTable);
        body_.set("recoverIntervalTable", recoverIntervalTable);
        body_.set("recoverValueTable", recoverValueTable);
        return body_;
    }
	@Override
	public int compareTo(StaminaModel o) {
		return staminaModelId.compareTo(o.staminaModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.staminaModelId == null) ? 0 : this.staminaModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.recoverIntervalMinutes == null) ? 0 : this.recoverIntervalMinutes.hashCode());
        result = prime * result + ((this.recoverValue == null) ? 0 : this.recoverValue.hashCode());
        result = prime * result + ((this.initialCapacity == null) ? 0 : this.initialCapacity.hashCode());
        result = prime * result + ((this.isOverflow == null) ? 0 : this.isOverflow.hashCode());
        result = prime * result + ((this.maxCapacity == null) ? 0 : this.maxCapacity.hashCode());
        result = prime * result + ((this.maxStaminaTable == null) ? 0 : this.maxStaminaTable.hashCode());
        result = prime * result + ((this.recoverIntervalTable == null) ? 0 : this.recoverIntervalTable.hashCode());
        result = prime * result + ((this.recoverValueTable == null) ? 0 : this.recoverValueTable.hashCode());
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
		StaminaModel other = (StaminaModel) o;
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
		if (maxStaminaTable == null) {
			return other.maxStaminaTable == null;
		} else if (!maxStaminaTable.equals(other.maxStaminaTable)) {
			return false;
		}
		if (recoverIntervalTable == null) {
			return other.recoverIntervalTable == null;
		} else if (!recoverIntervalTable.equals(other.recoverIntervalTable)) {
			return false;
		}
		if (recoverValueTable == null) {
			return other.recoverValueTable == null;
		} else if (!recoverValueTable.equals(other.recoverValueTable)) {
			return false;
		}
		return true;
	}
}
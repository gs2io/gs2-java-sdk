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

package io.gs2.inventory.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.model.IModel;

/**
 * インベントリモデルマスター
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class InventoryModelMaster implements IModel, Serializable, Comparable<InventoryModelMaster> {
	/** インベントリモデルマスター */
	protected String inventoryModelId;

	/**
	 * インベントリモデルマスターを取得
	 *
	 * @return インベントリモデルマスター
	 */
	public String getInventoryModelId() {
		return inventoryModelId;
	}

	/**
	 * インベントリモデルマスターを設定
	 *
	 * @param inventoryModelId インベントリモデルマスター
	 */
	public void setInventoryModelId(String inventoryModelId) {
		this.inventoryModelId = inventoryModelId;
	}

	/**
	 * インベントリモデルマスターを設定
	 *
	 * @param inventoryModelId インベントリモデルマスター
	 * @return this
	 */
	public InventoryModelMaster withInventoryModelId(String inventoryModelId) {
		this.inventoryModelId = inventoryModelId;
		return this;
	}
	/** インベントリの種類名 */
	protected String name;

	/**
	 * インベントリの種類名を取得
	 *
	 * @return インベントリの種類名
	 */
	public String getName() {
		return name;
	}

	/**
	 * インベントリの種類名を設定
	 *
	 * @param name インベントリの種類名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * インベントリの種類名を設定
	 *
	 * @param name インベントリの種類名
	 * @return this
	 */
	public InventoryModelMaster withName(String name) {
		this.name = name;
		return this;
	}
	/** インベントリの種類のメタデータ */
	protected String metadata;

	/**
	 * インベントリの種類のメタデータを取得
	 *
	 * @return インベントリの種類のメタデータ
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * インベントリの種類のメタデータを設定
	 *
	 * @param metadata インベントリの種類のメタデータ
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	/**
	 * インベントリの種類のメタデータを設定
	 *
	 * @param metadata インベントリの種類のメタデータ
	 * @return this
	 */
	public InventoryModelMaster withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	/** インベントリモデルマスターの説明 */
	protected String description;

	/**
	 * インベントリモデルマスターの説明を取得
	 *
	 * @return インベントリモデルマスターの説明
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * インベントリモデルマスターの説明を設定
	 *
	 * @param description インベントリモデルマスターの説明
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * インベントリモデルマスターの説明を設定
	 *
	 * @param description インベントリモデルマスターの説明
	 * @return this
	 */
	public InventoryModelMaster withDescription(String description) {
		this.description = description;
		return this;
	}
	/** インベントリの初期サイズ */
	protected Integer initialCapacity;

	/**
	 * インベントリの初期サイズを取得
	 *
	 * @return インベントリの初期サイズ
	 */
	public Integer getInitialCapacity() {
		return initialCapacity;
	}

	/**
	 * インベントリの初期サイズを設定
	 *
	 * @param initialCapacity インベントリの初期サイズ
	 */
	public void setInitialCapacity(Integer initialCapacity) {
		this.initialCapacity = initialCapacity;
	}

	/**
	 * インベントリの初期サイズを設定
	 *
	 * @param initialCapacity インベントリの初期サイズ
	 * @return this
	 */
	public InventoryModelMaster withInitialCapacity(Integer initialCapacity) {
		this.initialCapacity = initialCapacity;
		return this;
	}
	/** インベントリの最大サイズ */
	protected Integer maxCapacity;

	/**
	 * インベントリの最大サイズを取得
	 *
	 * @return インベントリの最大サイズ
	 */
	public Integer getMaxCapacity() {
		return maxCapacity;
	}

	/**
	 * インベントリの最大サイズを設定
	 *
	 * @param maxCapacity インベントリの最大サイズ
	 */
	public void setMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	/**
	 * インベントリの最大サイズを設定
	 *
	 * @param maxCapacity インベントリの最大サイズ
	 * @return this
	 */
	public InventoryModelMaster withMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
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
	public InventoryModelMaster withCreatedAt(Long createdAt) {
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
	public InventoryModelMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("inventoryModelId", this.getInventoryModelId())
            .put("name", this.getName())
            .put("metadata", this.getMetadata())
            .put("description", this.getDescription())
            .put("initialCapacity", this.getInitialCapacity())
            .put("maxCapacity", this.getMaxCapacity())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        return body_;
    }
	@Override
	public int compareTo(InventoryModelMaster o) {
		return inventoryModelId.compareTo(o.inventoryModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.inventoryModelId == null) ? 0 : this.inventoryModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.initialCapacity == null) ? 0 : this.initialCapacity.hashCode());
        result = prime * result + ((this.maxCapacity == null) ? 0 : this.maxCapacity.hashCode());
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
		InventoryModelMaster other = (InventoryModelMaster) o;
		if (inventoryModelId == null) {
			return other.inventoryModelId == null;
		} else if (!inventoryModelId.equals(other.inventoryModelId)) {
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
		if (initialCapacity == null) {
			return other.initialCapacity == null;
		} else if (!initialCapacity.equals(other.initialCapacity)) {
			return false;
		}
		if (maxCapacity == null) {
			return other.maxCapacity == null;
		} else if (!maxCapacity.equals(other.maxCapacity)) {
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
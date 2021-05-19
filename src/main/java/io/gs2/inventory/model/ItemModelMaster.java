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
import io.gs2.core.model.IModel;

/**
 * アイテムモデルマスター
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class ItemModelMaster implements IModel, Serializable, Comparable<ItemModelMaster> {
	/** アイテムモデルマスター */
	protected String itemModelId;

	/**
	 * アイテムモデルマスターを取得
	 *
	 * @return アイテムモデルマスター
	 */
	public String getItemModelId() {
		return itemModelId;
	}

	/**
	 * アイテムモデルマスターを設定
	 *
	 * @param itemModelId アイテムモデルマスター
	 */
	public void setItemModelId(String itemModelId) {
		this.itemModelId = itemModelId;
	}

	/**
	 * アイテムモデルマスターを設定
	 *
	 * @param itemModelId アイテムモデルマスター
	 * @return this
	 */
	public ItemModelMaster withItemModelId(String itemModelId) {
		this.itemModelId = itemModelId;
		return this;
	}
	/** アイテムの種類名 */
	protected String inventoryName;

	/**
	 * アイテムの種類名を取得
	 *
	 * @return アイテムの種類名
	 */
	public String getInventoryName() {
		return inventoryName;
	}

	/**
	 * アイテムの種類名を設定
	 *
	 * @param inventoryName アイテムの種類名
	 */
	public void setInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
	}

	/**
	 * アイテムの種類名を設定
	 *
	 * @param inventoryName アイテムの種類名
	 * @return this
	 */
	public ItemModelMaster withInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
		return this;
	}
	/** アイテムモデルの種類名 */
	protected String name;

	/**
	 * アイテムモデルの種類名を取得
	 *
	 * @return アイテムモデルの種類名
	 */
	public String getName() {
		return name;
	}

	/**
	 * アイテムモデルの種類名を設定
	 *
	 * @param name アイテムモデルの種類名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * アイテムモデルの種類名を設定
	 *
	 * @param name アイテムモデルの種類名
	 * @return this
	 */
	public ItemModelMaster withName(String name) {
		this.name = name;
		return this;
	}
	/** アイテムモデルマスターの説明 */
	protected String description;

	/**
	 * アイテムモデルマスターの説明を取得
	 *
	 * @return アイテムモデルマスターの説明
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * アイテムモデルマスターの説明を設定
	 *
	 * @param description アイテムモデルマスターの説明
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * アイテムモデルマスターの説明を設定
	 *
	 * @param description アイテムモデルマスターの説明
	 * @return this
	 */
	public ItemModelMaster withDescription(String description) {
		this.description = description;
		return this;
	}
	/** アイテムモデルの種類のメタデータ */
	protected String metadata;

	/**
	 * アイテムモデルの種類のメタデータを取得
	 *
	 * @return アイテムモデルの種類のメタデータ
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * アイテムモデルの種類のメタデータを設定
	 *
	 * @param metadata アイテムモデルの種類のメタデータ
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	/**
	 * アイテムモデルの種類のメタデータを設定
	 *
	 * @param metadata アイテムモデルの種類のメタデータ
	 * @return this
	 */
	public ItemModelMaster withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	/** スタック可能な最大数量 */
	protected Long stackingLimit;

	/**
	 * スタック可能な最大数量を取得
	 *
	 * @return スタック可能な最大数量
	 */
	public Long getStackingLimit() {
		return stackingLimit;
	}

	/**
	 * スタック可能な最大数量を設定
	 *
	 * @param stackingLimit スタック可能な最大数量
	 */
	public void setStackingLimit(Long stackingLimit) {
		this.stackingLimit = stackingLimit;
	}

	/**
	 * スタック可能な最大数量を設定
	 *
	 * @param stackingLimit スタック可能な最大数量
	 * @return this
	 */
	public ItemModelMaster withStackingLimit(Long stackingLimit) {
		this.stackingLimit = stackingLimit;
		return this;
	}
	/** スタック可能な最大数量を超えた時複数枠にアイテムを保管することを許すか */
	protected Boolean allowMultipleStacks;

	/**
	 * スタック可能な最大数量を超えた時複数枠にアイテムを保管することを許すかを取得
	 *
	 * @return スタック可能な最大数量を超えた時複数枠にアイテムを保管することを許すか
	 */
	public Boolean getAllowMultipleStacks() {
		return allowMultipleStacks;
	}

	/**
	 * スタック可能な最大数量を超えた時複数枠にアイテムを保管することを許すかを設定
	 *
	 * @param allowMultipleStacks スタック可能な最大数量を超えた時複数枠にアイテムを保管することを許すか
	 */
	public void setAllowMultipleStacks(Boolean allowMultipleStacks) {
		this.allowMultipleStacks = allowMultipleStacks;
	}

	/**
	 * スタック可能な最大数量を超えた時複数枠にアイテムを保管することを許すかを設定
	 *
	 * @param allowMultipleStacks スタック可能な最大数量を超えた時複数枠にアイテムを保管することを許すか
	 * @return this
	 */
	public ItemModelMaster withAllowMultipleStacks(Boolean allowMultipleStacks) {
		this.allowMultipleStacks = allowMultipleStacks;
		return this;
	}
	/** 表示順番 */
	protected Integer sortValue;

	/**
	 * 表示順番を取得
	 *
	 * @return 表示順番
	 */
	public Integer getSortValue() {
		return sortValue;
	}

	/**
	 * 表示順番を設定
	 *
	 * @param sortValue 表示順番
	 */
	public void setSortValue(Integer sortValue) {
		this.sortValue = sortValue;
	}

	/**
	 * 表示順番を設定
	 *
	 * @param sortValue 表示順番
	 * @return this
	 */
	public ItemModelMaster withSortValue(Integer sortValue) {
		this.sortValue = sortValue;
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
	public ItemModelMaster withCreatedAt(Long createdAt) {
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
	public ItemModelMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("itemModelId", this.getItemModelId())
            .put("inventoryName", this.getInventoryName())
            .put("name", this.getName())
            .put("description", this.getDescription())
            .put("metadata", this.getMetadata())
            .put("stackingLimit", this.getStackingLimit())
            .put("allowMultipleStacks", this.getAllowMultipleStacks())
            .put("sortValue", this.getSortValue())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        return body_;
    }
	@Override
	public int compareTo(ItemModelMaster o) {
		return itemModelId.compareTo(o.itemModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.itemModelId == null) ? 0 : this.itemModelId.hashCode());
        result = prime * result + ((this.inventoryName == null) ? 0 : this.inventoryName.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.stackingLimit == null) ? 0 : this.stackingLimit.hashCode());
        result = prime * result + ((this.allowMultipleStacks == null) ? 0 : this.allowMultipleStacks.hashCode());
        result = prime * result + ((this.sortValue == null) ? 0 : this.sortValue.hashCode());
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
		ItemModelMaster other = (ItemModelMaster) o;
		if (itemModelId == null) {
			return other.itemModelId == null;
		} else if (!itemModelId.equals(other.itemModelId)) {
			return false;
		}
		if (inventoryName == null) {
			return other.inventoryName == null;
		} else if (!inventoryName.equals(other.inventoryName)) {
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
		if (stackingLimit == null) {
			return other.stackingLimit == null;
		} else if (!stackingLimit.equals(other.stackingLimit)) {
			return false;
		}
		if (allowMultipleStacks == null) {
			return other.allowMultipleStacks == null;
		} else if (!allowMultipleStacks.equals(other.allowMultipleStacks)) {
			return false;
		}
		if (sortValue == null) {
			return other.sortValue == null;
		} else if (!sortValue.equals(other.sortValue)) {
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
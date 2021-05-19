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
 * アイテムモデル
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class ItemModel implements IModel, Serializable, Comparable<ItemModel> {
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
	public ItemModel withItemModelId(String itemModelId) {
		this.itemModelId = itemModelId;
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
	public ItemModel withName(String name) {
		this.name = name;
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
	public ItemModel withMetadata(String metadata) {
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
	public ItemModel withStackingLimit(Long stackingLimit) {
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
	public ItemModel withAllowMultipleStacks(Boolean allowMultipleStacks) {
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
	public ItemModel withSortValue(Integer sortValue) {
		this.sortValue = sortValue;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("itemModelId", this.getItemModelId())
            .put("name", this.getName())
            .put("metadata", this.getMetadata())
            .put("stackingLimit", this.getStackingLimit())
            .put("allowMultipleStacks", this.getAllowMultipleStacks())
            .put("sortValue", this.getSortValue());
        return body_;
    }
	@Override
	public int compareTo(ItemModel o) {
		return itemModelId.compareTo(o.itemModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.itemModelId == null) ? 0 : this.itemModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.stackingLimit == null) ? 0 : this.stackingLimit.hashCode());
        result = prime * result + ((this.allowMultipleStacks == null) ? 0 : this.allowMultipleStacks.hashCode());
        result = prime * result + ((this.sortValue == null) ? 0 : this.sortValue.hashCode());
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
		ItemModel other = (ItemModel) o;
		if (itemModelId == null) {
			return other.itemModelId == null;
		} else if (!itemModelId.equals(other.itemModelId)) {
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
		return true;
	}
}
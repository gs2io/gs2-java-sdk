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
 * インベントリモデル
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class InventoryModel implements IModel, Serializable, Comparable<InventoryModel> {
	/** インベントリモデル */
	protected String inventoryModelId;

	/**
	 * インベントリモデルを取得
	 *
	 * @return インベントリモデル
	 */
	public String getInventoryModelId() {
		return inventoryModelId;
	}

	/**
	 * インベントリモデルを設定
	 *
	 * @param inventoryModelId インベントリモデル
	 */
	public void setInventoryModelId(String inventoryModelId) {
		this.inventoryModelId = inventoryModelId;
	}

	/**
	 * インベントリモデルを設定
	 *
	 * @param inventoryModelId インベントリモデル
	 * @return this
	 */
	public InventoryModel withInventoryModelId(String inventoryModelId) {
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
	public InventoryModel withName(String name) {
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
	public InventoryModel withMetadata(String metadata) {
		this.metadata = metadata;
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
	public InventoryModel withInitialCapacity(Integer initialCapacity) {
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
	public InventoryModel withMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
		return this;
	}
	/** 参照元が登録されているアイテムセットは削除できなくする */
	protected Boolean protectReferencedItem;

	/**
	 * 参照元が登録されているアイテムセットは削除できなくするを取得
	 *
	 * @return 参照元が登録されているアイテムセットは削除できなくする
	 */
	public Boolean getProtectReferencedItem() {
		return protectReferencedItem;
	}

	/**
	 * 参照元が登録されているアイテムセットは削除できなくするを設定
	 *
	 * @param protectReferencedItem 参照元が登録されているアイテムセットは削除できなくする
	 */
	public void setProtectReferencedItem(Boolean protectReferencedItem) {
		this.protectReferencedItem = protectReferencedItem;
	}

	/**
	 * 参照元が登録されているアイテムセットは削除できなくするを設定
	 *
	 * @param protectReferencedItem 参照元が登録されているアイテムセットは削除できなくする
	 * @return this
	 */
	public InventoryModel withProtectReferencedItem(Boolean protectReferencedItem) {
		this.protectReferencedItem = protectReferencedItem;
		return this;
	}
	/** インベントリに格納可能なアイテムモデル一覧 */
	protected List<ItemModel> itemModels;

	/**
	 * インベントリに格納可能なアイテムモデル一覧を取得
	 *
	 * @return インベントリに格納可能なアイテムモデル一覧
	 */
	public List<ItemModel> getItemModels() {
		return itemModels;
	}

	/**
	 * インベントリに格納可能なアイテムモデル一覧を設定
	 *
	 * @param itemModels インベントリに格納可能なアイテムモデル一覧
	 */
	public void setItemModels(List<ItemModel> itemModels) {
		this.itemModels = itemModels;
	}

	/**
	 * インベントリに格納可能なアイテムモデル一覧を設定
	 *
	 * @param itemModels インベントリに格納可能なアイテムモデル一覧
	 * @return this
	 */
	public InventoryModel withItemModels(List<ItemModel> itemModels) {
		this.itemModels = itemModels;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> itemModels = new ArrayList<>();
        if(this.itemModels != null) {
            for(ItemModel item : this.itemModels) {
                itemModels.add(item.toJson());
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("inventoryModelId", this.getInventoryModelId())
            .put("name", this.getName())
            .put("metadata", this.getMetadata())
            .put("initialCapacity", this.getInitialCapacity())
            .put("maxCapacity", this.getMaxCapacity())
            .put("protectReferencedItem", this.getProtectReferencedItem());
        body_.set("itemModels", JsonNodeFactory.instance.arrayNode().addAll(itemModels));
        return body_;
    }
	@Override
	public int compareTo(InventoryModel o) {
		return inventoryModelId.compareTo(o.inventoryModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.inventoryModelId == null) ? 0 : this.inventoryModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.initialCapacity == null) ? 0 : this.initialCapacity.hashCode());
        result = prime * result + ((this.maxCapacity == null) ? 0 : this.maxCapacity.hashCode());
        result = prime * result + ((this.protectReferencedItem == null) ? 0 : this.protectReferencedItem.hashCode());
        result = prime * result + ((this.itemModels == null) ? 0 : this.itemModels.hashCode());
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
		InventoryModel other = (InventoryModel) o;
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
		if (protectReferencedItem == null) {
			return other.protectReferencedItem == null;
		} else if (!protectReferencedItem.equals(other.protectReferencedItem)) {
			return false;
		}
		if (itemModels == null) {
			return other.itemModels == null;
		} else if (!itemModels.equals(other.itemModels)) {
			return false;
		}
		return true;
	}
}
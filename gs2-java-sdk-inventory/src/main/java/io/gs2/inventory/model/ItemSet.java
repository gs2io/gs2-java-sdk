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
 * 有効期限ごとのアイテム所持数量
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class ItemSet implements IModel, Serializable, Comparable<ItemSet> {
	/** 有効期限ごとのアイテム所持数量 */
	protected String itemSetId;

	/**
	 * 有効期限ごとのアイテム所持数量を取得
	 *
	 * @return 有効期限ごとのアイテム所持数量
	 */
	public String getItemSetId() {
		return itemSetId;
	}

	/**
	 * 有効期限ごとのアイテム所持数量を設定
	 *
	 * @param itemSetId 有効期限ごとのアイテム所持数量
	 */
	public void setItemSetId(String itemSetId) {
		this.itemSetId = itemSetId;
	}

	/**
	 * 有効期限ごとのアイテム所持数量を設定
	 *
	 * @param itemSetId 有効期限ごとのアイテム所持数量
	 * @return this
	 */
	public ItemSet withItemSetId(String itemSetId) {
		this.itemSetId = itemSetId;
		return this;
	}
	/** インベントリの名前 */
	protected String inventoryName;

	/**
	 * インベントリの名前を取得
	 *
	 * @return インベントリの名前
	 */
	public String getInventoryName() {
		return inventoryName;
	}

	/**
	 * インベントリの名前を設定
	 *
	 * @param inventoryName インベントリの名前
	 */
	public void setInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
	}

	/**
	 * インベントリの名前を設定
	 *
	 * @param inventoryName インベントリの名前
	 * @return this
	 */
	public ItemSet withInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
		return this;
	}
	/** ユーザーID */
	protected String userId;

	/**
	 * ユーザーIDを取得
	 *
	 * @return ユーザーID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * ユーザーIDを設定
	 *
	 * @param userId ユーザーID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * ユーザーIDを設定
	 *
	 * @param userId ユーザーID
	 * @return this
	 */
	public ItemSet withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	/** アイテムマスターの名前 */
	protected String itemName;

	/**
	 * アイテムマスターの名前を取得
	 *
	 * @return アイテムマスターの名前
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * アイテムマスターの名前を設定
	 *
	 * @param itemName アイテムマスターの名前
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * アイテムマスターの名前を設定
	 *
	 * @param itemName アイテムマスターの名前
	 * @return this
	 */
	public ItemSet withItemName(String itemName) {
		this.itemName = itemName;
		return this;
	}
	/** 所持数量 */
	protected Long count;

	/**
	 * 所持数量を取得
	 *
	 * @return 所持数量
	 */
	public Long getCount() {
		return count;
	}

	/**
	 * 所持数量を設定
	 *
	 * @param count 所持数量
	 */
	public void setCount(Long count) {
		this.count = count;
	}

	/**
	 * 所持数量を設定
	 *
	 * @param count 所持数量
	 * @return this
	 */
	public ItemSet withCount(Long count) {
		this.count = count;
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
	public ItemSet withSortValue(Integer sortValue) {
		this.sortValue = sortValue;
		return this;
	}
	/** 有効期限 */
	protected Long expiresAt;

	/**
	 * 有効期限を取得
	 *
	 * @return 有効期限
	 */
	public Long getExpiresAt() {
		return expiresAt;
	}

	/**
	 * 有効期限を設定
	 *
	 * @param expiresAt 有効期限
	 */
	public void setExpiresAt(Long expiresAt) {
		this.expiresAt = expiresAt;
	}

	/**
	 * 有効期限を設定
	 *
	 * @param expiresAt 有効期限
	 * @return this
	 */
	public ItemSet withExpiresAt(Long expiresAt) {
		this.expiresAt = expiresAt;
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
	public ItemSet withCreatedAt(Long createdAt) {
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
	public ItemSet withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("itemSetId", this.getItemSetId())
            .put("inventoryName", this.getInventoryName())
            .put("userId", this.getUserId())
            .put("itemName", this.getItemName())
            .put("count", this.getCount())
            .put("sortValue", this.getSortValue())
            .put("expiresAt", this.getExpiresAt())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        return body_;
    }
	@Override
	public int compareTo(ItemSet o) {
		return itemSetId.compareTo(o.itemSetId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.itemSetId == null) ? 0 : this.itemSetId.hashCode());
        result = prime * result + ((this.inventoryName == null) ? 0 : this.inventoryName.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.itemName == null) ? 0 : this.itemName.hashCode());
        result = prime * result + ((this.count == null) ? 0 : this.count.hashCode());
        result = prime * result + ((this.sortValue == null) ? 0 : this.sortValue.hashCode());
        result = prime * result + ((this.expiresAt == null) ? 0 : this.expiresAt.hashCode());
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
		ItemSet other = (ItemSet) o;
		if (itemSetId == null) {
			return other.itemSetId == null;
		} else if (!itemSetId.equals(other.itemSetId)) {
			return false;
		}
		if (inventoryName == null) {
			return other.inventoryName == null;
		} else if (!inventoryName.equals(other.inventoryName)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (itemName == null) {
			return other.itemName == null;
		} else if (!itemName.equals(other.itemName)) {
			return false;
		}
		if (count == null) {
			return other.count == null;
		} else if (!count.equals(other.count)) {
			return false;
		}
		if (sortValue == null) {
			return other.sortValue == null;
		} else if (!sortValue.equals(other.sortValue)) {
			return false;
		}
		if (expiresAt == null) {
			return other.expiresAt == null;
		} else if (!expiresAt.equals(other.expiresAt)) {
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
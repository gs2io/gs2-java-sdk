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
 * インベントリ
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Inventory implements IModel, Serializable, Comparable<Inventory> {
	/** インベントリ */
	protected String inventoryId;

	/**
	 * インベントリを取得
	 *
	 * @return インベントリ
	 */
	public String getInventoryId() {
		return inventoryId;
	}

	/**
	 * インベントリを設定
	 *
	 * @param inventoryId インベントリ
	 */
	public void setInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
	}

	/**
	 * インベントリを設定
	 *
	 * @param inventoryId インベントリ
	 * @return this
	 */
	public Inventory withInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
		return this;
	}
	/** インベントリモデル名 */
	protected String inventoryName;

	/**
	 * インベントリモデル名を取得
	 *
	 * @return インベントリモデル名
	 */
	public String getInventoryName() {
		return inventoryName;
	}

	/**
	 * インベントリモデル名を設定
	 *
	 * @param inventoryName インベントリモデル名
	 */
	public void setInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
	}

	/**
	 * インベントリモデル名を設定
	 *
	 * @param inventoryName インベントリモデル名
	 * @return this
	 */
	public Inventory withInventoryName(String inventoryName) {
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
	public Inventory withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	/** 現在のインベントリのキャパシティ使用量 */
	protected Integer currentInventoryCapacityUsage;

	/**
	 * 現在のインベントリのキャパシティ使用量を取得
	 *
	 * @return 現在のインベントリのキャパシティ使用量
	 */
	public Integer getCurrentInventoryCapacityUsage() {
		return currentInventoryCapacityUsage;
	}

	/**
	 * 現在のインベントリのキャパシティ使用量を設定
	 *
	 * @param currentInventoryCapacityUsage 現在のインベントリのキャパシティ使用量
	 */
	public void setCurrentInventoryCapacityUsage(Integer currentInventoryCapacityUsage) {
		this.currentInventoryCapacityUsage = currentInventoryCapacityUsage;
	}

	/**
	 * 現在のインベントリのキャパシティ使用量を設定
	 *
	 * @param currentInventoryCapacityUsage 現在のインベントリのキャパシティ使用量
	 * @return this
	 */
	public Inventory withCurrentInventoryCapacityUsage(Integer currentInventoryCapacityUsage) {
		this.currentInventoryCapacityUsage = currentInventoryCapacityUsage;
		return this;
	}
	/** 現在のインベントリの最大キャパシティ */
	protected Integer currentInventoryMaxCapacity;

	/**
	 * 現在のインベントリの最大キャパシティを取得
	 *
	 * @return 現在のインベントリの最大キャパシティ
	 */
	public Integer getCurrentInventoryMaxCapacity() {
		return currentInventoryMaxCapacity;
	}

	/**
	 * 現在のインベントリの最大キャパシティを設定
	 *
	 * @param currentInventoryMaxCapacity 現在のインベントリの最大キャパシティ
	 */
	public void setCurrentInventoryMaxCapacity(Integer currentInventoryMaxCapacity) {
		this.currentInventoryMaxCapacity = currentInventoryMaxCapacity;
	}

	/**
	 * 現在のインベントリの最大キャパシティを設定
	 *
	 * @param currentInventoryMaxCapacity 現在のインベントリの最大キャパシティ
	 * @return this
	 */
	public Inventory withCurrentInventoryMaxCapacity(Integer currentInventoryMaxCapacity) {
		this.currentInventoryMaxCapacity = currentInventoryMaxCapacity;
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
	public Inventory withCreatedAt(Long createdAt) {
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
	public Inventory withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("inventoryId", this.getInventoryId())
            .put("inventoryName", this.getInventoryName())
            .put("userId", this.getUserId())
            .put("currentInventoryCapacityUsage", this.getCurrentInventoryCapacityUsage())
            .put("currentInventoryMaxCapacity", this.getCurrentInventoryMaxCapacity())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        return body_;
    }
	@Override
	public int compareTo(Inventory o) {
		return inventoryId.compareTo(o.inventoryId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.inventoryId == null) ? 0 : this.inventoryId.hashCode());
        result = prime * result + ((this.inventoryName == null) ? 0 : this.inventoryName.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.currentInventoryCapacityUsage == null) ? 0 : this.currentInventoryCapacityUsage.hashCode());
        result = prime * result + ((this.currentInventoryMaxCapacity == null) ? 0 : this.currentInventoryMaxCapacity.hashCode());
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
		Inventory other = (Inventory) o;
		if (inventoryId == null) {
			return other.inventoryId == null;
		} else if (!inventoryId.equals(other.inventoryId)) {
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
		if (currentInventoryCapacityUsage == null) {
			return other.currentInventoryCapacityUsage == null;
		} else if (!currentInventoryCapacityUsage.equals(other.currentInventoryCapacityUsage)) {
			return false;
		}
		if (currentInventoryMaxCapacity == null) {
			return other.currentInventoryMaxCapacity == null;
		} else if (!currentInventoryMaxCapacity.equals(other.currentInventoryMaxCapacity)) {
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
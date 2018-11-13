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

package io.gs2.consumableItem.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 所持品
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Inventory implements Serializable {

	/** 所持品ID */
	private String inventoryId;

	/** ユーザID */
	private String userId;

	/** 消費型アイテム名 */
	private String itemName;

	/** 所持数量 */
	private Integer count;

	/** 最大所持可能数量 */
	private Integer max;

	/** 有効期限(エポック秒) */
	private Integer expireAt;

	/** 作成日時(エポック秒) */
	private Integer createAt;

	/** 最終更新日時(エポック秒) */
	private Integer updateAt;


	/**
	 * 所持品IDを取得
	 *
	 * @return 所持品ID
	 */
	public String getInventoryId() {
		return inventoryId;
	}

	/**
	 * 所持品IDを設定
	 *
	 * @param inventoryId 所持品ID
	 */
	public void setInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
	}

	/**
	 * 所持品IDを設定
	 *
	 * @param inventoryId 所持品ID
	 * @return this
	 */
	public Inventory withInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
		return this;
	}

	/**
	 * ユーザIDを取得
	 *
	 * @return ユーザID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * ユーザIDを設定
	 *
	 * @param userId ユーザID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * ユーザIDを設定
	 *
	 * @param userId ユーザID
	 * @return this
	 */
	public Inventory withUserId(String userId) {
		this.userId = userId;
		return this;
	}

	/**
	 * 消費型アイテム名を取得
	 *
	 * @return 消費型アイテム名
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * 消費型アイテム名を設定
	 *
	 * @param itemName 消費型アイテム名
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 消費型アイテム名を設定
	 *
	 * @param itemName 消費型アイテム名
	 * @return this
	 */
	public Inventory withItemName(String itemName) {
		this.itemName = itemName;
		return this;
	}

	/**
	 * 所持数量を取得
	 *
	 * @return 所持数量
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * 所持数量を設定
	 *
	 * @param count 所持数量
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * 所持数量を設定
	 *
	 * @param count 所持数量
	 * @return this
	 */
	public Inventory withCount(Integer count) {
		this.count = count;
		return this;
	}

	/**
	 * 最大所持可能数量を取得
	 *
	 * @return 最大所持可能数量
	 */
	public Integer getMax() {
		return max;
	}

	/**
	 * 最大所持可能数量を設定
	 *
	 * @param max 最大所持可能数量
	 */
	public void setMax(Integer max) {
		this.max = max;
	}

	/**
	 * 最大所持可能数量を設定
	 *
	 * @param max 最大所持可能数量
	 * @return this
	 */
	public Inventory withMax(Integer max) {
		this.max = max;
		return this;
	}

	/**
	 * 有効期限(エポック秒)を取得
	 *
	 * @return 有効期限(エポック秒)
	 */
	public Integer getExpireAt() {
		return expireAt;
	}

	/**
	 * 有効期限(エポック秒)を設定
	 *
	 * @param expireAt 有効期限(エポック秒)
	 */
	public void setExpireAt(Integer expireAt) {
		this.expireAt = expireAt;
	}

	/**
	 * 有効期限(エポック秒)を設定
	 *
	 * @param expireAt 有効期限(エポック秒)
	 * @return this
	 */
	public Inventory withExpireAt(Integer expireAt) {
		this.expireAt = expireAt;
		return this;
	}

	/**
	 * 作成日時(エポック秒)を取得
	 *
	 * @return 作成日時(エポック秒)
	 */
	public Integer getCreateAt() {
		return createAt;
	}

	/**
	 * 作成日時(エポック秒)を設定
	 *
	 * @param createAt 作成日時(エポック秒)
	 */
	public void setCreateAt(Integer createAt) {
		this.createAt = createAt;
	}

	/**
	 * 作成日時(エポック秒)を設定
	 *
	 * @param createAt 作成日時(エポック秒)
	 * @return this
	 */
	public Inventory withCreateAt(Integer createAt) {
		this.createAt = createAt;
		return this;
	}

	/**
	 * 最終更新日時(エポック秒)を取得
	 *
	 * @return 最終更新日時(エポック秒)
	 */
	public Integer getUpdateAt() {
		return updateAt;
	}

	/**
	 * 最終更新日時(エポック秒)を設定
	 *
	 * @param updateAt 最終更新日時(エポック秒)
	 */
	public void setUpdateAt(Integer updateAt) {
		this.updateAt = updateAt;
	}

	/**
	 * 最終更新日時(エポック秒)を設定
	 *
	 * @param updateAt 最終更新日時(エポック秒)
	 * @return this
	 */
	public Inventory withUpdateAt(Integer updateAt) {
		this.updateAt = updateAt;
		return this;
	}


    public ObjectNode toJson() {

		ObjectNode body = JsonNodeFactory.instance.objectNode()

            .put("inventoryId", this.getInventoryId())
            .put("userId", this.getUserId())
            .put("itemName", this.getItemName())
            .put("count", this.getCount())
            .put("max", this.getMax())
            .put("expireAt", this.getExpireAt())
            .put("createAt", this.getCreateAt())
            .put("updateAt", this.getUpdateAt());

        return body;
    }
}
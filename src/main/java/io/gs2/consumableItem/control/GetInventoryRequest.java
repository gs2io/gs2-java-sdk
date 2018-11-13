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

package io.gs2.consumableItem.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.consumableItem.model.*;
import io.gs2.consumableItem.Gs2ConsumableItem;
import io.gs2.control.Gs2UserRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class GetInventoryRequest extends Gs2UserRequest<GetInventoryRequest> {

	public static class Constant extends Gs2ConsumableItem.Constant {
		public static final String FUNCTION = "GetInventory";
	}

	/** 消費型アイテムプールの名前 */
	private String itemPoolName;

	/** 消費型アイテムの名前 */
	private String itemName;

	/** 有効期限(エポック秒) */
	private Integer expireAt;


	/**
	 * 消費型アイテムプールの名前を取得
	 *
	 * @return 消費型アイテムプールの名前
	 */
	public String getItemPoolName() {
		return itemPoolName;
	}

	/**
	 * 消費型アイテムプールの名前を設定
	 *
	 * @param itemPoolName 消費型アイテムプールの名前
	 */
	public void setItemPoolName(String itemPoolName) {
		this.itemPoolName = itemPoolName;
	}

	/**
	 * 消費型アイテムプールの名前を設定
	 *
	 * @param itemPoolName 消費型アイテムプールの名前
	 * @return this
	 */
	public GetInventoryRequest withItemPoolName(String itemPoolName) {
		setItemPoolName(itemPoolName);
		return this;
	}

	/**
	 * 消費型アイテムの名前を取得
	 *
	 * @return 消費型アイテムの名前
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * 消費型アイテムの名前を設定
	 *
	 * @param itemName 消費型アイテムの名前
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 消費型アイテムの名前を設定
	 *
	 * @param itemName 消費型アイテムの名前
	 * @return this
	 */
	public GetInventoryRequest withItemName(String itemName) {
		setItemName(itemName);
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
	public GetInventoryRequest withExpireAt(Integer expireAt) {
		setExpireAt(expireAt);
		return this;
	}

}
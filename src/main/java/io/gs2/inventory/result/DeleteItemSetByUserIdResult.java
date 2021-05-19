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

package io.gs2.inventory.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.core.model.*;
import io.gs2.inventory.model.*;

/**
 * 有効期限ごとのアイテム所持数量を削除 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class DeleteItemSetByUserIdResult implements IResult, Serializable {
	/** 削除した有効期限ごとのアイテム所持数量のリスト */
	private List<ItemSet> items;
	/** アイテムモデル */
	private ItemModel itemModel;
	/** インベントリ */
	private Inventory inventory;

	/**
	 * 削除した有効期限ごとのアイテム所持数量のリストを取得
	 *
	 * @return 有効期限ごとのアイテム所持数量を削除
	 */
	public List<ItemSet> getItems() {
		return items;
	}

	/**
	 * 削除した有効期限ごとのアイテム所持数量のリストを設定
	 *
	 * @param items 有効期限ごとのアイテム所持数量を削除
	 */
	public void setItems(List<ItemSet> items) {
		this.items = items;
	}

	/**
	 * アイテムモデルを取得
	 *
	 * @return 有効期限ごとのアイテム所持数量を削除
	 */
	public ItemModel getItemModel() {
		return itemModel;
	}

	/**
	 * アイテムモデルを設定
	 *
	 * @param itemModel 有効期限ごとのアイテム所持数量を削除
	 */
	public void setItemModel(ItemModel itemModel) {
		this.itemModel = itemModel;
	}

	/**
	 * インベントリを取得
	 *
	 * @return 有効期限ごとのアイテム所持数量を削除
	 */
	public Inventory getInventory() {
		return inventory;
	}

	/**
	 * インベントリを設定
	 *
	 * @param inventory 有効期限ごとのアイテム所持数量を削除
	 */
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
}
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
 * スタンプシートでアイテムの参照元を削除 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class DeleteReferenceOfItemSetByStampSheetResult implements IResult, Serializable {
	/** この所持品の参照元リスト */
	private List<String> item;
	/** 参照元削除後の有効期限ごとのアイテム所持数量 */
	private ItemSet itemSet;
	/** アイテムモデル */
	private ItemModel itemModel;
	/** インベントリ */
	private Inventory inventory;

	/**
	 * この所持品の参照元リストを取得
	 *
	 * @return スタンプシートでアイテムの参照元を削除
	 */
	public List<String> getItem() {
		return item;
	}

	/**
	 * この所持品の参照元リストを設定
	 *
	 * @param item スタンプシートでアイテムの参照元を削除
	 */
	public void setItem(List<String> item) {
		this.item = item;
	}

	/**
	 * 参照元削除後の有効期限ごとのアイテム所持数量を取得
	 *
	 * @return スタンプシートでアイテムの参照元を削除
	 */
	public ItemSet getItemSet() {
		return itemSet;
	}

	/**
	 * 参照元削除後の有効期限ごとのアイテム所持数量を設定
	 *
	 * @param itemSet スタンプシートでアイテムの参照元を削除
	 */
	public void setItemSet(ItemSet itemSet) {
		this.itemSet = itemSet;
	}

	/**
	 * アイテムモデルを取得
	 *
	 * @return スタンプシートでアイテムの参照元を削除
	 */
	public ItemModel getItemModel() {
		return itemModel;
	}

	/**
	 * アイテムモデルを設定
	 *
	 * @param itemModel スタンプシートでアイテムの参照元を削除
	 */
	public void setItemModel(ItemModel itemModel) {
		this.itemModel = itemModel;
	}

	/**
	 * インベントリを取得
	 *
	 * @return スタンプシートでアイテムの参照元を削除
	 */
	public Inventory getInventory() {
		return inventory;
	}

	/**
	 * インベントリを設定
	 *
	 * @param inventory スタンプシートでアイテムの参照元を削除
	 */
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
}
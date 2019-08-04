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
import io.gs2.model.*;
import io.gs2.inventory.model.*;

/**
 * スタンプシートでインベントリのアイテムを消費 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class ConsumeItemSetByStampTaskResult implements IResult, Serializable {
	/** 消費後の有効期限ごとのアイテム所持数量のリスト */
	private List<ItemSet> items;
	/** アイテムモデル */
	private ItemModel itemModel;
	/** インベントリ */
	private Inventory inventory;
	/** スタンプタスクの実行結果を記録したコンテキスト */
	private String newContextStack;

	/**
	 * 消費後の有効期限ごとのアイテム所持数量のリストを取得
	 *
	 * @return スタンプシートでインベントリのアイテムを消費
	 */
	public List<ItemSet> getItems() {
		return items;
	}

	/**
	 * 消費後の有効期限ごとのアイテム所持数量のリストを設定
	 *
	 * @param items スタンプシートでインベントリのアイテムを消費
	 */
	public void setItems(List<ItemSet> items) {
		this.items = items;
	}

	/**
	 * アイテムモデルを取得
	 *
	 * @return スタンプシートでインベントリのアイテムを消費
	 */
	public ItemModel getItemModel() {
		return itemModel;
	}

	/**
	 * アイテムモデルを設定
	 *
	 * @param itemModel スタンプシートでインベントリのアイテムを消費
	 */
	public void setItemModel(ItemModel itemModel) {
		this.itemModel = itemModel;
	}

	/**
	 * インベントリを取得
	 *
	 * @return スタンプシートでインベントリのアイテムを消費
	 */
	public Inventory getInventory() {
		return inventory;
	}

	/**
	 * インベントリを設定
	 *
	 * @param inventory スタンプシートでインベントリのアイテムを消費
	 */
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	/**
	 * スタンプタスクの実行結果を記録したコンテキストを取得
	 *
	 * @return スタンプシートでインベントリのアイテムを消費
	 */
	public String getNewContextStack() {
		return newContextStack;
	}

	/**
	 * スタンプタスクの実行結果を記録したコンテキストを設定
	 *
	 * @param newContextStack スタンプシートでインベントリのアイテムを消費
	 */
	public void setNewContextStack(String newContextStack) {
		this.newContextStack = newContextStack;
	}
}
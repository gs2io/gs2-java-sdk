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
 * スタンプシートでインベントリのアイテムを検証 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class VerifyReferenceOfByStampTaskResult implements IResult, Serializable {
	/** この所持品の参照元のリスト */
	private List<String> item;
	/** 有効期限ごとのアイテム所持数量 */
	private ItemSet itemSet;
	/** アイテムモデル */
	private ItemModel itemModel;
	/** インベントリ */
	private Inventory inventory;
	/** スタンプタスクの実行結果を記録したコンテキスト */
	private String newContextStack;

	/**
	 * この所持品の参照元のリストを取得
	 *
	 * @return スタンプシートでインベントリのアイテムを検証
	 */
	public List<String> getItem() {
		return item;
	}

	/**
	 * この所持品の参照元のリストを設定
	 *
	 * @param item スタンプシートでインベントリのアイテムを検証
	 */
	public void setItem(List<String> item) {
		this.item = item;
	}

	/**
	 * 有効期限ごとのアイテム所持数量を取得
	 *
	 * @return スタンプシートでインベントリのアイテムを検証
	 */
	public ItemSet getItemSet() {
		return itemSet;
	}

	/**
	 * 有効期限ごとのアイテム所持数量を設定
	 *
	 * @param itemSet スタンプシートでインベントリのアイテムを検証
	 */
	public void setItemSet(ItemSet itemSet) {
		this.itemSet = itemSet;
	}

	/**
	 * アイテムモデルを取得
	 *
	 * @return スタンプシートでインベントリのアイテムを検証
	 */
	public ItemModel getItemModel() {
		return itemModel;
	}

	/**
	 * アイテムモデルを設定
	 *
	 * @param itemModel スタンプシートでインベントリのアイテムを検証
	 */
	public void setItemModel(ItemModel itemModel) {
		this.itemModel = itemModel;
	}

	/**
	 * インベントリを取得
	 *
	 * @return スタンプシートでインベントリのアイテムを検証
	 */
	public Inventory getInventory() {
		return inventory;
	}

	/**
	 * インベントリを設定
	 *
	 * @param inventory スタンプシートでインベントリのアイテムを検証
	 */
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	/**
	 * スタンプタスクの実行結果を記録したコンテキストを取得
	 *
	 * @return スタンプシートでインベントリのアイテムを検証
	 */
	public String getNewContextStack() {
		return newContextStack;
	}

	/**
	 * スタンプタスクの実行結果を記録したコンテキストを設定
	 *
	 * @param newContextStack スタンプシートでインベントリのアイテムを検証
	 */
	public void setNewContextStack(String newContextStack) {
		this.newContextStack = newContextStack;
	}
}
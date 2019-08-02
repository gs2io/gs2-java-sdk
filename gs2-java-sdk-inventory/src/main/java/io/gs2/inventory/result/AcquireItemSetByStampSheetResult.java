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
 * スタンプシートでアイテムをインベントリに追加 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class AcquireItemSetByStampSheetResult implements IResult, Serializable {
	/** 加算後の有効期限ごとのアイテム所持数量のリスト */
	private List<ItemSet> items;
	/** アイテムモデル */
	private ItemModel itemModel;
	/** インベントリ */
	private Inventory inventory;
	/** 所持数量の上限を超えて受け取れずに GS2-Inbox に転送したアイテムの数量 */
	private Long overflowCount;

	/**
	 * 加算後の有効期限ごとのアイテム所持数量のリストを取得
	 *
	 * @return スタンプシートでアイテムをインベントリに追加
	 */
	public List<ItemSet> getItems() {
		return items;
	}

	/**
	 * 加算後の有効期限ごとのアイテム所持数量のリストを設定
	 *
	 * @param items スタンプシートでアイテムをインベントリに追加
	 */
	public void setItems(List<ItemSet> items) {
		this.items = items;
	}

	/**
	 * アイテムモデルを取得
	 *
	 * @return スタンプシートでアイテムをインベントリに追加
	 */
	public ItemModel getItemModel() {
		return itemModel;
	}

	/**
	 * アイテムモデルを設定
	 *
	 * @param itemModel スタンプシートでアイテムをインベントリに追加
	 */
	public void setItemModel(ItemModel itemModel) {
		this.itemModel = itemModel;
	}

	/**
	 * インベントリを取得
	 *
	 * @return スタンプシートでアイテムをインベントリに追加
	 */
	public Inventory getInventory() {
		return inventory;
	}

	/**
	 * インベントリを設定
	 *
	 * @param inventory スタンプシートでアイテムをインベントリに追加
	 */
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	/**
	 * 所持数量の上限を超えて受け取れずに GS2-Inbox に転送したアイテムの数量を取得
	 *
	 * @return スタンプシートでアイテムをインベントリに追加
	 */
	public Long getOverflowCount() {
		return overflowCount;
	}

	/**
	 * 所持数量の上限を超えて受け取れずに GS2-Inbox に転送したアイテムの数量を設定
	 *
	 * @param overflowCount スタンプシートでアイテムをインベントリに追加
	 */
	public void setOverflowCount(Long overflowCount) {
		this.overflowCount = overflowCount;
	}
}
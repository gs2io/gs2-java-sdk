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
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class GetItemMasterRequest extends Gs2BasicRequest<GetItemMasterRequest> {

	public static class Constant extends Gs2ConsumableItem.Constant {
		public static final String FUNCTION = "GetItemMaster";
	}

	/** 仮想通貨の名前 */
	private String itemPoolName;

	/** 商品の名前 */
	private String itemName;


	/**
	 * 仮想通貨の名前を取得
	 *
	 * @return 仮想通貨の名前
	 */
	public String getItemPoolName() {
		return itemPoolName;
	}

	/**
	 * 仮想通貨の名前を設定
	 *
	 * @param itemPoolName 仮想通貨の名前
	 */
	public void setItemPoolName(String itemPoolName) {
		this.itemPoolName = itemPoolName;
	}

	/**
	 * 仮想通貨の名前を設定
	 *
	 * @param itemPoolName 仮想通貨の名前
	 * @return this
	 */
	public GetItemMasterRequest withItemPoolName(String itemPoolName) {
		setItemPoolName(itemPoolName);
		return this;
	}

	/**
	 * 商品の名前を取得
	 *
	 * @return 商品の名前
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * 商品の名前を設定
	 *
	 * @param itemName 商品の名前
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 商品の名前を設定
	 *
	 * @param itemName 商品の名前
	 * @return this
	 */
	public GetItemMasterRequest withItemName(String itemName) {
		setItemName(itemName);
		return this;
	}

}
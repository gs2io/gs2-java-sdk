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
public class GetItemPoolStatusRequest extends Gs2BasicRequest<GetItemPoolStatusRequest> {

	public static class Constant extends Gs2ConsumableItem.Constant {
		public static final String FUNCTION = "GetItemPoolStatus";
	}

	/** 状態を取得する消費型アイテムプールの名前 */
	private String itemPoolName;


	/**
	 * 状態を取得する消費型アイテムプールの名前を取得
	 *
	 * @return 状態を取得する消費型アイテムプールの名前
	 */
	public String getItemPoolName() {
		return itemPoolName;
	}

	/**
	 * 状態を取得する消費型アイテムプールの名前を設定
	 *
	 * @param itemPoolName 状態を取得する消費型アイテムプールの名前
	 */
	public void setItemPoolName(String itemPoolName) {
		this.itemPoolName = itemPoolName;
	}

	/**
	 * 状態を取得する消費型アイテムプールの名前を設定
	 *
	 * @param itemPoolName 状態を取得する消費型アイテムプールの名前
	 * @return this
	 */
	public GetItemPoolStatusRequest withItemPoolName(String itemPoolName) {
		setItemPoolName(itemPoolName);
		return this;
	}

}
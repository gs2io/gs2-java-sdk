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
 * キャパシティサイズを加算 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class AddCapacityByUserIdResult implements IResult, Serializable {
	/** キャパシティ加算後のインベントリ */
	private Inventory item;

	/**
	 * キャパシティ加算後のインベントリを取得
	 *
	 * @return キャパシティサイズを加算
	 */
	public Inventory getItem() {
		return item;
	}

	/**
	 * キャパシティ加算後のインベントリを設定
	 *
	 * @param item キャパシティサイズを加算
	 */
	public void setItem(Inventory item) {
		this.item = item;
	}
}
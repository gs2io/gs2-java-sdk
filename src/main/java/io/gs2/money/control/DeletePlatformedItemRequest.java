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

package io.gs2.money.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.money.Gs2Money;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DeletePlatformedItemRequest extends Gs2BasicRequest<DeletePlatformedItemRequest> {

	public static class Constant extends Gs2Money.Constant {
		public static final String FUNCTION = "DeletePlatformedItem";
	}

	/** 課金通貨の名前 */
	private String moneyName;

	/** 商品の名前 */
	private String itemName;

	/** プラットフォームの名前 */
	private String platform;


	/**
	 * 課金通貨の名前を取得
	 *
	 * @return 課金通貨の名前
	 */
	public String getMoneyName() {
		return moneyName;
	}

	/**
	 * 課金通貨の名前を設定
	 *
	 * @param moneyName 課金通貨の名前
	 */
	public void setMoneyName(String moneyName) {
		this.moneyName = moneyName;
	}

	/**
	 * 課金通貨の名前を設定
	 *
	 * @param moneyName 課金通貨の名前
	 * @return this
	 */
	public DeletePlatformedItemRequest withMoneyName(String moneyName) {
		setMoneyName(moneyName);
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
	public DeletePlatformedItemRequest withItemName(String itemName) {
		setItemName(itemName);
		return this;
	}

	/**
	 * プラットフォームの名前を取得
	 *
	 * @return プラットフォームの名前
	 */
	public String getPlatform() {
		return platform;
	}

	/**
	 * プラットフォームの名前を設定
	 *
	 * @param platform プラットフォームの名前
	 */
	public void setPlatform(String platform) {
		this.platform = platform;
	}

	/**
	 * プラットフォームの名前を設定
	 *
	 * @param platform プラットフォームの名前
	 * @return this
	 */
	public DeletePlatformedItemRequest withPlatform(String platform) {
		setPlatform(platform);
		return this;
	}

}
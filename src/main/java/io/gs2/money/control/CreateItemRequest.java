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
public class CreateItemRequest extends Gs2BasicRequest<CreateItemRequest> {

	public static class Constant extends Gs2Money.Constant {
		public static final String FUNCTION = "CreateItem";
	}

	/** 課金通貨の名前 */
	private String moneyName;

	/** 商品名 */
	private String name;

	/** 付与する課金通貨の数 */
	private Integer count;


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
	public CreateItemRequest withMoneyName(String moneyName) {
		setMoneyName(moneyName);
		return this;
	}

	/**
	 * 商品名を取得
	 *
	 * @return 商品名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 商品名を設定
	 *
	 * @param name 商品名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 商品名を設定
	 *
	 * @param name 商品名
	 * @return this
	 */
	public CreateItemRequest withName(String name) {
		setName(name);
		return this;
	}

	/**
	 * 付与する課金通貨の数を取得
	 *
	 * @return 付与する課金通貨の数
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * 付与する課金通貨の数を設定
	 *
	 * @param count 付与する課金通貨の数
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * 付与する課金通貨の数を設定
	 *
	 * @param count 付与する課金通貨の数
	 * @return this
	 */
	public CreateItemRequest withCount(Integer count) {
		setCount(count);
		return this;
	}

}
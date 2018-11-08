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
public class ChargeWalletByUserIdRequest extends Gs2BasicRequest<ChargeWalletByUserIdRequest> {

	public static class Constant extends Gs2Money.Constant {
		public static final String FUNCTION = "ChargeWalletByUserId";
	}

	/** 課金通貨の名前 */
	private String moneyName;

	/** ウォレットのスロット番号 */
	private Integer slot;

	/** ウォレットのユーザID */
	private String userId;

	/** 支払金額 */
	private Double price;

	/** 課金通貨付与量 */
	private Integer count;

	/** トランザクションID */
	private String transactionId;


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
	public ChargeWalletByUserIdRequest withMoneyName(String moneyName) {
		setMoneyName(moneyName);
		return this;
	}

	/**
	 * ウォレットのスロット番号を取得
	 *
	 * @return ウォレットのスロット番号
	 */
	public Integer getSlot() {
		return slot;
	}

	/**
	 * ウォレットのスロット番号を設定
	 *
	 * @param slot ウォレットのスロット番号
	 */
	public void setSlot(Integer slot) {
		this.slot = slot;
	}

	/**
	 * ウォレットのスロット番号を設定
	 *
	 * @param slot ウォレットのスロット番号
	 * @return this
	 */
	public ChargeWalletByUserIdRequest withSlot(Integer slot) {
		setSlot(slot);
		return this;
	}

	/**
	 * ウォレットのユーザIDを取得
	 *
	 * @return ウォレットのユーザID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * ウォレットのユーザIDを設定
	 *
	 * @param userId ウォレットのユーザID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * ウォレットのユーザIDを設定
	 *
	 * @param userId ウォレットのユーザID
	 * @return this
	 */
	public ChargeWalletByUserIdRequest withUserId(String userId) {
		setUserId(userId);
		return this;
	}

	/**
	 * 支払金額を取得
	 *
	 * @return 支払金額
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * 支払金額を設定
	 *
	 * @param price 支払金額
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * 支払金額を設定
	 *
	 * @param price 支払金額
	 * @return this
	 */
	public ChargeWalletByUserIdRequest withPrice(Double price) {
		setPrice(price);
		return this;
	}

	/**
	 * 課金通貨付与量を取得
	 *
	 * @return 課金通貨付与量
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * 課金通貨付与量を設定
	 *
	 * @param count 課金通貨付与量
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * 課金通貨付与量を設定
	 *
	 * @param count 課金通貨付与量
	 * @return this
	 */
	public ChargeWalletByUserIdRequest withCount(Integer count) {
		setCount(count);
		return this;
	}

	/**
	 * トランザクションIDを取得
	 *
	 * @return トランザクションID
	 */
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * トランザクションIDを設定
	 *
	 * @param transactionId トランザクションID
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * トランザクションIDを設定
	 *
	 * @param transactionId トランザクションID
	 * @return this
	 */
	public ChargeWalletByUserIdRequest withTransactionId(String transactionId) {
		setTransactionId(transactionId);
		return this;
	}

}
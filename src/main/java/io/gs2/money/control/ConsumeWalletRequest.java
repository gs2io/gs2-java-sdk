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
import io.gs2.control.Gs2UserRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class ConsumeWalletRequest extends Gs2UserRequest<ConsumeWalletRequest> {

	public static class Constant extends Gs2Money.Constant {
		public static final String FUNCTION = "ConsumeWallet";
	}

	/** 取得する課金通貨の名前 */
	private String moneyName;

	/** 取得するウォレットのスロット番号 */
	private Integer slot;

	/** 課金通貨消費量 */
	private Integer count;

	/** 用途ID */
	private Integer use;

	/** 有償課金通貨のみ消費対象としたい場合に true を指定します */
	private Boolean paidOnly;


	/**
	 * 取得する課金通貨の名前を取得
	 *
	 * @return 取得する課金通貨の名前
	 */
	public String getMoneyName() {
		return moneyName;
	}

	/**
	 * 取得する課金通貨の名前を設定
	 *
	 * @param moneyName 取得する課金通貨の名前
	 */
	public void setMoneyName(String moneyName) {
		this.moneyName = moneyName;
	}

	/**
	 * 取得する課金通貨の名前を設定
	 *
	 * @param moneyName 取得する課金通貨の名前
	 * @return this
	 */
	public ConsumeWalletRequest withMoneyName(String moneyName) {
		setMoneyName(moneyName);
		return this;
	}

	/**
	 * 取得するウォレットのスロット番号を取得
	 *
	 * @return 取得するウォレットのスロット番号
	 */
	public Integer getSlot() {
		return slot;
	}

	/**
	 * 取得するウォレットのスロット番号を設定
	 *
	 * @param slot 取得するウォレットのスロット番号
	 */
	public void setSlot(Integer slot) {
		this.slot = slot;
	}

	/**
	 * 取得するウォレットのスロット番号を設定
	 *
	 * @param slot 取得するウォレットのスロット番号
	 * @return this
	 */
	public ConsumeWalletRequest withSlot(Integer slot) {
		setSlot(slot);
		return this;
	}

	/**
	 * 課金通貨消費量を取得
	 *
	 * @return 課金通貨消費量
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * 課金通貨消費量を設定
	 *
	 * @param count 課金通貨消費量
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * 課金通貨消費量を設定
	 *
	 * @param count 課金通貨消費量
	 * @return this
	 */
	public ConsumeWalletRequest withCount(Integer count) {
		setCount(count);
		return this;
	}

	/**
	 * 用途IDを取得
	 *
	 * @return 用途ID
	 */
	public Integer getUse() {
		return use;
	}

	/**
	 * 用途IDを設定
	 *
	 * @param use 用途ID
	 */
	public void setUse(Integer use) {
		this.use = use;
	}

	/**
	 * 用途IDを設定
	 *
	 * @param use 用途ID
	 * @return this
	 */
	public ConsumeWalletRequest withUse(Integer use) {
		setUse(use);
		return this;
	}

	/**
	 * 有償課金通貨のみ消費対象としたい場合に true を指定しますを取得
	 *
	 * @return 有償課金通貨のみ消費対象としたい場合に true を指定します
	 */
	public Boolean getPaidOnly() {
		return paidOnly;
	}

	/**
	 * 有償課金通貨のみ消費対象としたい場合に true を指定しますを設定
	 *
	 * @param paidOnly 有償課金通貨のみ消費対象としたい場合に true を指定します
	 */
	public void setPaidOnly(Boolean paidOnly) {
		this.paidOnly = paidOnly;
	}

	/**
	 * 有償課金通貨のみ消費対象としたい場合に true を指定しますを設定
	 *
	 * @param paidOnly 有償課金通貨のみ消費対象としたい場合に true を指定します
	 * @return this
	 */
	public ConsumeWalletRequest withPaidOnly(Boolean paidOnly) {
		setPaidOnly(paidOnly);
		return this;
	}

}
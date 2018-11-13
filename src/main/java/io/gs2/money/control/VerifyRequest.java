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
public class VerifyRequest extends Gs2UserRequest<VerifyRequest> {

	public static class Constant extends Gs2Money.Constant {
		public static final String FUNCTION = "Verify";
	}

	/** 課金通貨の名前 */
	private String moneyName;

	/** スロット番号 */
	private Integer slot;

	/** レシートデータ */
	private String receipt;


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
	public VerifyRequest withMoneyName(String moneyName) {
		setMoneyName(moneyName);
		return this;
	}

	/**
	 * スロット番号を取得
	 *
	 * @return スロット番号
	 */
	public Integer getSlot() {
		return slot;
	}

	/**
	 * スロット番号を設定
	 *
	 * @param slot スロット番号
	 */
	public void setSlot(Integer slot) {
		this.slot = slot;
	}

	/**
	 * スロット番号を設定
	 *
	 * @param slot スロット番号
	 * @return this
	 */
	public VerifyRequest withSlot(Integer slot) {
		setSlot(slot);
		return this;
	}

	/**
	 * レシートデータを取得
	 *
	 * @return レシートデータ
	 */
	public String getReceipt() {
		return receipt;
	}

	/**
	 * レシートデータを設定
	 *
	 * @param receipt レシートデータ
	 */
	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	/**
	 * レシートデータを設定
	 *
	 * @param receipt レシートデータ
	 * @return this
	 */
	public VerifyRequest withReceipt(String receipt) {
		setReceipt(receipt);
		return this;
	}

}
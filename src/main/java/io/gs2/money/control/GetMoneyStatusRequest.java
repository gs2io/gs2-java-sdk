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
public class GetMoneyStatusRequest extends Gs2BasicRequest<GetMoneyStatusRequest> {

	public static class Constant extends Gs2Money.Constant {
		public static final String FUNCTION = "GetMoneyStatus";
	}

	/** 取得する課金通貨の名前 */
	private String moneyName;


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
	public GetMoneyStatusRequest withMoneyName(String moneyName) {
		setMoneyName(moneyName);
		return this;
	}

}
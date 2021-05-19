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

package io.gs2.money.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.core.model.*;
import io.gs2.money.model.*;

/**
 * ウォレットから残高を消費します のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class WithdrawResult implements IResult, Serializable {
	/** 消費後のウォレット */
	private Wallet item;
	/** 消費した通貨の価格 */
	private Float price;

	/**
	 * 消費後のウォレットを取得
	 *
	 * @return ウォレットから残高を消費します
	 */
	public Wallet getItem() {
		return item;
	}

	/**
	 * 消費後のウォレットを設定
	 *
	 * @param item ウォレットから残高を消費します
	 */
	public void setItem(Wallet item) {
		this.item = item;
	}

	/**
	 * 消費した通貨の価格を取得
	 *
	 * @return ウォレットから残高を消費します
	 */
	public Float getPrice() {
		return price;
	}

	/**
	 * 消費した通貨の価格を設定
	 *
	 * @param price ウォレットから残高を消費します
	 */
	public void setPrice(Float price) {
		this.price = price;
	}
}
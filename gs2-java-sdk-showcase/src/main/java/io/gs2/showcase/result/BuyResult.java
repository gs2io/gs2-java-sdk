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

package io.gs2.showcase.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.model.*;
import io.gs2.showcase.model.*;

/**
 * 陳列棚を取得 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class BuyResult implements IResult, Serializable {
	/** 商品 */
	private SalesItem item;
	/** 購入処理の実行に使用するスタンプシート */
	private String stampSheet;

	/**
	 * 商品を取得
	 *
	 * @return 陳列棚を取得
	 */
	public SalesItem getItem() {
		return item;
	}

	/**
	 * 商品を設定
	 *
	 * @param item 陳列棚を取得
	 */
	public void setItem(SalesItem item) {
		this.item = item;
	}

	/**
	 * 購入処理の実行に使用するスタンプシートを取得
	 *
	 * @return 陳列棚を取得
	 */
	public String getStampSheet() {
		return stampSheet;
	}

	/**
	 * 購入処理の実行に使用するスタンプシートを設定
	 *
	 * @param stampSheet 陳列棚を取得
	 */
	public void setStampSheet(String stampSheet) {
		this.stampSheet = stampSheet;
	}
}
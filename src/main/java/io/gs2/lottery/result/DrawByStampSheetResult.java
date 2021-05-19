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

package io.gs2.lottery.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.core.model.*;
import io.gs2.lottery.model.*;

/**
 * スタンプシートを使用して抽選処理を実行 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class DrawByStampSheetResult implements IResult, Serializable {
	/** 抽選結果の景品リスト */
	private List<DrawnPrize> items;
	/** 排出された景品を入手するスタンプシート */
	private String stampSheet;
	/** スタンプシートの署名計算に使用した暗号鍵GRN */
	private String stampSheetEncryptionKeyId;
	/** ボックスから取り出したアイテムのリスト */
	private BoxItems boxItems;

	/**
	 * 抽選結果の景品リストを取得
	 *
	 * @return スタンプシートを使用して抽選処理を実行
	 */
	public List<DrawnPrize> getItems() {
		return items;
	}

	/**
	 * 抽選結果の景品リストを設定
	 *
	 * @param items スタンプシートを使用して抽選処理を実行
	 */
	public void setItems(List<DrawnPrize> items) {
		this.items = items;
	}

	/**
	 * 排出された景品を入手するスタンプシートを取得
	 *
	 * @return スタンプシートを使用して抽選処理を実行
	 */
	public String getStampSheet() {
		return stampSheet;
	}

	/**
	 * 排出された景品を入手するスタンプシートを設定
	 *
	 * @param stampSheet スタンプシートを使用して抽選処理を実行
	 */
	public void setStampSheet(String stampSheet) {
		this.stampSheet = stampSheet;
	}

	/**
	 * スタンプシートの署名計算に使用した暗号鍵GRNを取得
	 *
	 * @return スタンプシートを使用して抽選処理を実行
	 */
	public String getStampSheetEncryptionKeyId() {
		return stampSheetEncryptionKeyId;
	}

	/**
	 * スタンプシートの署名計算に使用した暗号鍵GRNを設定
	 *
	 * @param stampSheetEncryptionKeyId スタンプシートを使用して抽選処理を実行
	 */
	public void setStampSheetEncryptionKeyId(String stampSheetEncryptionKeyId) {
		this.stampSheetEncryptionKeyId = stampSheetEncryptionKeyId;
	}

	/**
	 * ボックスから取り出したアイテムのリストを取得
	 *
	 * @return スタンプシートを使用して抽選処理を実行
	 */
	public BoxItems getBoxItems() {
		return boxItems;
	}

	/**
	 * ボックスから取り出したアイテムのリストを設定
	 *
	 * @param boxItems スタンプシートを使用して抽選処理を実行
	 */
	public void setBoxItems(BoxItems boxItems) {
		this.boxItems = boxItems;
	}
}
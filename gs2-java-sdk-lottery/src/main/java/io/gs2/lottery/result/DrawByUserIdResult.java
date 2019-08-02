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
import io.gs2.model.*;
import io.gs2.lottery.model.*;

/**
 * ユーザIDを指定して抽選を実行 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class DrawByUserIdResult implements IResult, Serializable {
	/** 抽選結果の景品リスト */
	private List<DrawnPrize> items;
	/** 排出された景品を入手するスタンプシート */
	private String stampSheet;
	/** ボックスから取り出したアイテムのリスト */
	private BoxItems boxItems;

	/**
	 * 抽選結果の景品リストを取得
	 *
	 * @return ユーザIDを指定して抽選を実行
	 */
	public List<DrawnPrize> getItems() {
		return items;
	}

	/**
	 * 抽選結果の景品リストを設定
	 *
	 * @param items ユーザIDを指定して抽選を実行
	 */
	public void setItems(List<DrawnPrize> items) {
		this.items = items;
	}

	/**
	 * 排出された景品を入手するスタンプシートを取得
	 *
	 * @return ユーザIDを指定して抽選を実行
	 */
	public String getStampSheet() {
		return stampSheet;
	}

	/**
	 * 排出された景品を入手するスタンプシートを設定
	 *
	 * @param stampSheet ユーザIDを指定して抽選を実行
	 */
	public void setStampSheet(String stampSheet) {
		this.stampSheet = stampSheet;
	}

	/**
	 * ボックスから取り出したアイテムのリストを取得
	 *
	 * @return ユーザIDを指定して抽選を実行
	 */
	public BoxItems getBoxItems() {
		return boxItems;
	}

	/**
	 * ボックスから取り出したアイテムのリストを設定
	 *
	 * @param boxItems ユーザIDを指定して抽選を実行
	 */
	public void setBoxItems(BoxItems boxItems) {
		this.boxItems = boxItems;
	}
}
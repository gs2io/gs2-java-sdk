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

package io.gs2.gacha.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.gacha.model.*;
import io.gs2.gacha.Gs2Gacha;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DescribePrizeMasterRequest extends Gs2BasicRequest<DescribePrizeMasterRequest> {

	public static class Constant extends Gs2Gacha.Constant {
		public static final String FUNCTION = "DescribePrizeMaster";
	}

	/** ガチャプールの名前を指定します。 */
	private String gachaPoolName;

	/** 排出確率テーブルの名前を指定します。 */
	private String prizeTableName;

	/** 景品レアリティの名前を指定します。 */
	private String rarityName;

	/** データの取得を開始する位置を指定するトークン */
	private String pageToken;

	/** データの取得件数 */
	private Integer limit;


	/**
	 * ガチャプールの名前を指定します。を取得
	 *
	 * @return ガチャプールの名前を指定します。
	 */
	public String getGachaPoolName() {
		return gachaPoolName;
	}

	/**
	 * ガチャプールの名前を指定します。を設定
	 *
	 * @param gachaPoolName ガチャプールの名前を指定します。
	 */
	public void setGachaPoolName(String gachaPoolName) {
		this.gachaPoolName = gachaPoolName;
	}

	/**
	 * ガチャプールの名前を指定します。を設定
	 *
	 * @param gachaPoolName ガチャプールの名前を指定します。
	 * @return this
	 */
	public DescribePrizeMasterRequest withGachaPoolName(String gachaPoolName) {
		setGachaPoolName(gachaPoolName);
		return this;
	}

	/**
	 * 排出確率テーブルの名前を指定します。を取得
	 *
	 * @return 排出確率テーブルの名前を指定します。
	 */
	public String getPrizeTableName() {
		return prizeTableName;
	}

	/**
	 * 排出確率テーブルの名前を指定します。を設定
	 *
	 * @param prizeTableName 排出確率テーブルの名前を指定します。
	 */
	public void setPrizeTableName(String prizeTableName) {
		this.prizeTableName = prizeTableName;
	}

	/**
	 * 排出確率テーブルの名前を指定します。を設定
	 *
	 * @param prizeTableName 排出確率テーブルの名前を指定します。
	 * @return this
	 */
	public DescribePrizeMasterRequest withPrizeTableName(String prizeTableName) {
		setPrizeTableName(prizeTableName);
		return this;
	}

	/**
	 * 景品レアリティの名前を指定します。を取得
	 *
	 * @return 景品レアリティの名前を指定します。
	 */
	public String getRarityName() {
		return rarityName;
	}

	/**
	 * 景品レアリティの名前を指定します。を設定
	 *
	 * @param rarityName 景品レアリティの名前を指定します。
	 */
	public void setRarityName(String rarityName) {
		this.rarityName = rarityName;
	}

	/**
	 * 景品レアリティの名前を指定します。を設定
	 *
	 * @param rarityName 景品レアリティの名前を指定します。
	 * @return this
	 */
	public DescribePrizeMasterRequest withRarityName(String rarityName) {
		setRarityName(rarityName);
		return this;
	}

	/**
	 * データの取得を開始する位置を指定するトークンを取得
	 *
	 * @return データの取得を開始する位置を指定するトークン
	 */
	public String getPageToken() {
		return pageToken;
	}

	/**
	 * データの取得を開始する位置を指定するトークンを設定
	 *
	 * @param pageToken データの取得を開始する位置を指定するトークン
	 */
	public void setPageToken(String pageToken) {
		this.pageToken = pageToken;
	}

	/**
	 * データの取得を開始する位置を指定するトークンを設定
	 *
	 * @param pageToken データの取得を開始する位置を指定するトークン
	 * @return this
	 */
	public DescribePrizeMasterRequest withPageToken(String pageToken) {
		setPageToken(pageToken);
		return this;
	}

	/**
	 * データの取得件数を取得
	 *
	 * @return データの取得件数
	 */
	public Integer getLimit() {
		return limit;
	}

	/**
	 * データの取得件数を設定
	 *
	 * @param limit データの取得件数
	 */
	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	/**
	 * データの取得件数を設定
	 *
	 * @param limit データの取得件数
	 * @return this
	 */
	public DescribePrizeMasterRequest withLimit(Integer limit) {
		setLimit(limit);
		return this;
	}

}
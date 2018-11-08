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
public class UpdateRarityMasterRequest extends Gs2BasicRequest<UpdateRarityMasterRequest> {

	public static class Constant extends Gs2Gacha.Constant {
		public static final String FUNCTION = "UpdateRarityMaster";
	}

	/** ガチャプールの名前を指定します。 */
	private String gachaPoolName;

	/** 排出確率テーブルの名前を指定します。 */
	private String prizeTableName;

	/** 景品レアリティの名前を指定します。 */
	private String rarityName;

	/** 排出重み */
	private Integer weight;


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
	public UpdateRarityMasterRequest withGachaPoolName(String gachaPoolName) {
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
	public UpdateRarityMasterRequest withPrizeTableName(String prizeTableName) {
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
	public UpdateRarityMasterRequest withRarityName(String rarityName) {
		setRarityName(rarityName);
		return this;
	}

	/**
	 * 排出重みを取得
	 *
	 * @return 排出重み
	 */
	public Integer getWeight() {
		return weight;
	}

	/**
	 * 排出重みを設定
	 *
	 * @param weight 排出重み
	 */
	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	/**
	 * 排出重みを設定
	 *
	 * @param weight 排出重み
	 * @return this
	 */
	public UpdateRarityMasterRequest withWeight(Integer weight) {
		setWeight(weight);
		return this;
	}

}
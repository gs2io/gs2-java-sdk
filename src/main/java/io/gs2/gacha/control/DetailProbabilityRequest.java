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
public class DetailProbabilityRequest extends Gs2BasicRequest<DetailProbabilityRequest> {

	public static class Constant extends Gs2Gacha.Constant {
		public static final String FUNCTION = "DetailProbability";
	}

	/** ガチャプールの名前 */
	private String gachaPoolName;

	/** ガチャの名前 */
	private String gachaName;

	/** 何回目の抽選時の抽選確率を取得するか */
	private Integer drawTime;


	/**
	 * ガチャプールの名前を取得
	 *
	 * @return ガチャプールの名前
	 */
	public String getGachaPoolName() {
		return gachaPoolName;
	}

	/**
	 * ガチャプールの名前を設定
	 *
	 * @param gachaPoolName ガチャプールの名前
	 */
	public void setGachaPoolName(String gachaPoolName) {
		this.gachaPoolName = gachaPoolName;
	}

	/**
	 * ガチャプールの名前を設定
	 *
	 * @param gachaPoolName ガチャプールの名前
	 * @return this
	 */
	public DetailProbabilityRequest withGachaPoolName(String gachaPoolName) {
		setGachaPoolName(gachaPoolName);
		return this;
	}

	/**
	 * ガチャの名前を取得
	 *
	 * @return ガチャの名前
	 */
	public String getGachaName() {
		return gachaName;
	}

	/**
	 * ガチャの名前を設定
	 *
	 * @param gachaName ガチャの名前
	 */
	public void setGachaName(String gachaName) {
		this.gachaName = gachaName;
	}

	/**
	 * ガチャの名前を設定
	 *
	 * @param gachaName ガチャの名前
	 * @return this
	 */
	public DetailProbabilityRequest withGachaName(String gachaName) {
		setGachaName(gachaName);
		return this;
	}

	/**
	 * 何回目の抽選時の抽選確率を取得するかを取得
	 *
	 * @return 何回目の抽選時の抽選確率を取得するか
	 */
	public Integer getDrawTime() {
		return drawTime;
	}

	/**
	 * 何回目の抽選時の抽選確率を取得するかを設定
	 *
	 * @param drawTime 何回目の抽選時の抽選確率を取得するか
	 */
	public void setDrawTime(Integer drawTime) {
		this.drawTime = drawTime;
	}

	/**
	 * 何回目の抽選時の抽選確率を取得するかを設定
	 *
	 * @param drawTime 何回目の抽選時の抽選確率を取得するか
	 * @return this
	 */
	public DetailProbabilityRequest withDrawTime(Integer drawTime) {
		setDrawTime(drawTime);
		return this;
	}

}
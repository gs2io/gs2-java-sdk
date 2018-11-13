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
public class UpdateCurrentGachaMasterRequest extends Gs2BasicRequest<UpdateCurrentGachaMasterRequest> {

	public static class Constant extends Gs2Gacha.Constant {
		public static final String FUNCTION = "UpdateCurrentGachaMaster";
	}

	/** ガチャプールの名前を指定します。 */
	private String gachaPoolName;

	/** ガチャマスターデータ */
	private String settings;


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
	public UpdateCurrentGachaMasterRequest withGachaPoolName(String gachaPoolName) {
		setGachaPoolName(gachaPoolName);
		return this;
	}

	/**
	 * ガチャマスターデータを取得
	 *
	 * @return ガチャマスターデータ
	 */
	public String getSettings() {
		return settings;
	}

	/**
	 * ガチャマスターデータを設定
	 *
	 * @param settings ガチャマスターデータ
	 */
	public void setSettings(String settings) {
		this.settings = settings;
	}

	/**
	 * ガチャマスターデータを設定
	 *
	 * @param settings ガチャマスターデータ
	 * @return this
	 */
	public UpdateCurrentGachaMasterRequest withSettings(String settings) {
		setSettings(settings);
		return this;
	}

}
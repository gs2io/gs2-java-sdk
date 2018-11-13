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

package io.gs2.gold.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.gold.model.*;
import io.gs2.gold.Gs2Gold;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateCurrentGoldMasterRequest extends Gs2BasicRequest<UpdateCurrentGoldMasterRequest> {

	public static class Constant extends Gs2Gold.Constant {
		public static final String FUNCTION = "UpdateCurrentGoldMaster";
	}

	/** ゴールドプールの名前 */
	private String goldPoolName;

	/** ゴールドマスターデータ */
	private String settings;


	/**
	 * ゴールドプールの名前を取得
	 *
	 * @return ゴールドプールの名前
	 */
	public String getGoldPoolName() {
		return goldPoolName;
	}

	/**
	 * ゴールドプールの名前を設定
	 *
	 * @param goldPoolName ゴールドプールの名前
	 */
	public void setGoldPoolName(String goldPoolName) {
		this.goldPoolName = goldPoolName;
	}

	/**
	 * ゴールドプールの名前を設定
	 *
	 * @param goldPoolName ゴールドプールの名前
	 * @return this
	 */
	public UpdateCurrentGoldMasterRequest withGoldPoolName(String goldPoolName) {
		setGoldPoolName(goldPoolName);
		return this;
	}

	/**
	 * ゴールドマスターデータを取得
	 *
	 * @return ゴールドマスターデータ
	 */
	public String getSettings() {
		return settings;
	}

	/**
	 * ゴールドマスターデータを設定
	 *
	 * @param settings ゴールドマスターデータ
	 */
	public void setSettings(String settings) {
		this.settings = settings;
	}

	/**
	 * ゴールドマスターデータを設定
	 *
	 * @param settings ゴールドマスターデータ
	 * @return this
	 */
	public UpdateCurrentGoldMasterRequest withSettings(String settings) {
		setSettings(settings);
		return this;
	}

}
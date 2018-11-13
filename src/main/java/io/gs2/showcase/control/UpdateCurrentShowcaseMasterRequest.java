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

package io.gs2.showcase.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.showcase.model.*;
import io.gs2.showcase.Gs2Showcase;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateCurrentShowcaseMasterRequest extends Gs2BasicRequest<UpdateCurrentShowcaseMasterRequest> {

	public static class Constant extends Gs2Showcase.Constant {
		public static final String FUNCTION = "UpdateCurrentShowcaseMaster";
	}

	/** ショーケースの名前 */
	private String showcaseName;

	/** ショーケースマスターデータ */
	private String settings;


	/**
	 * ショーケースの名前を取得
	 *
	 * @return ショーケースの名前
	 */
	public String getShowcaseName() {
		return showcaseName;
	}

	/**
	 * ショーケースの名前を設定
	 *
	 * @param showcaseName ショーケースの名前
	 */
	public void setShowcaseName(String showcaseName) {
		this.showcaseName = showcaseName;
	}

	/**
	 * ショーケースの名前を設定
	 *
	 * @param showcaseName ショーケースの名前
	 * @return this
	 */
	public UpdateCurrentShowcaseMasterRequest withShowcaseName(String showcaseName) {
		setShowcaseName(showcaseName);
		return this;
	}

	/**
	 * ショーケースマスターデータを取得
	 *
	 * @return ショーケースマスターデータ
	 */
	public String getSettings() {
		return settings;
	}

	/**
	 * ショーケースマスターデータを設定
	 *
	 * @param settings ショーケースマスターデータ
	 */
	public void setSettings(String settings) {
		this.settings = settings;
	}

	/**
	 * ショーケースマスターデータを設定
	 *
	 * @param settings ショーケースマスターデータ
	 * @return this
	 */
	public UpdateCurrentShowcaseMasterRequest withSettings(String settings) {
		setSettings(settings);
		return this;
	}

}
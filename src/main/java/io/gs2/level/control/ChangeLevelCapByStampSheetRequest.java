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

package io.gs2.level.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.level.model.*;
import io.gs2.level.Gs2Level;
import io.gs2.control.Gs2UserRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class ChangeLevelCapByStampSheetRequest extends Gs2UserRequest<ChangeLevelCapByStampSheetRequest> {

	public static class Constant extends Gs2Level.Constant {
		public static final String FUNCTION = "ChangeLevelCapByStampSheet";
	}

	/** スタンプシート */
	private String sheet;

	/** スタンプの暗号鍵 */
	private String keyName;


	/**
	 * スタンプシートを取得
	 *
	 * @return スタンプシート
	 */
	public String getSheet() {
		return sheet;
	}

	/**
	 * スタンプシートを設定
	 *
	 * @param sheet スタンプシート
	 */
	public void setSheet(String sheet) {
		this.sheet = sheet;
	}

	/**
	 * スタンプシートを設定
	 *
	 * @param sheet スタンプシート
	 * @return this
	 */
	public ChangeLevelCapByStampSheetRequest withSheet(String sheet) {
		setSheet(sheet);
		return this;
	}

	/**
	 * スタンプの暗号鍵を取得
	 *
	 * @return スタンプの暗号鍵
	 */
	public String getKeyName() {
		return keyName;
	}

	/**
	 * スタンプの暗号鍵を設定
	 *
	 * @param keyName スタンプの暗号鍵
	 */
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	/**
	 * スタンプの暗号鍵を設定
	 *
	 * @param keyName スタンプの暗号鍵
	 * @return this
	 */
	public ChangeLevelCapByStampSheetRequest withKeyName(String keyName) {
		setKeyName(keyName);
		return this;
	}

}
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
import io.gs2.control.Gs2UserRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DepositIntoWalletByStampSheetRequest extends Gs2UserRequest<DepositIntoWalletByStampSheetRequest> {

	public static class Constant extends Gs2Gold.Constant {
		public static final String FUNCTION = "DepositIntoWalletByStampSheet";
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
	public DepositIntoWalletByStampSheetRequest withSheet(String sheet) {
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
	public DepositIntoWalletByStampSheetRequest withKeyName(String keyName) {
		setKeyName(keyName);
		return this;
	}

}
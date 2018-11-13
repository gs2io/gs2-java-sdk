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

package io.gs2.auth.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.auth.Gs2Auth;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateTimeOnetimeTokenRequest extends Gs2BasicRequest<CreateTimeOnetimeTokenRequest> {

	public static class Constant extends Gs2Auth.Constant {
		public static final String FUNCTION = "CreateTimeOnetimeToken";
	}

	/** 認可スクリプトを指定します */
	private String scriptName;


	/**
	 * 認可スクリプトを指定しますを取得
	 *
	 * @return 認可スクリプトを指定します
	 */
	public String getScriptName() {
		return scriptName;
	}

	/**
	 * 認可スクリプトを指定しますを設定
	 *
	 * @param scriptName 認可スクリプトを指定します
	 */
	public void setScriptName(String scriptName) {
		this.scriptName = scriptName;
	}

	/**
	 * 認可スクリプトを指定しますを設定
	 *
	 * @param scriptName 認可スクリプトを指定します
	 * @return this
	 */
	public CreateTimeOnetimeTokenRequest withScriptName(String scriptName) {
		setScriptName(scriptName);
		return this;
	}

}
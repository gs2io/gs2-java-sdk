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
public class CreateOnceOnetimeTokenRequest extends Gs2BasicRequest<CreateOnceOnetimeTokenRequest> {

	public static class Constant extends Gs2Auth.Constant {
		public static final String FUNCTION = "CreateOnceOnetimeToken";
	}

	/** 認可処理に実行するスクリプト */
	private String scriptName;

	/** 認可するアクション */
	private String grant;

	/** grant で指定したアクションに引数として渡すことを許可する内容 */
	private String args;


	/**
	 * 認可処理に実行するスクリプトを取得
	 *
	 * @return 認可処理に実行するスクリプト
	 */
	public String getScriptName() {
		return scriptName;
	}

	/**
	 * 認可処理に実行するスクリプトを設定
	 *
	 * @param scriptName 認可処理に実行するスクリプト
	 */
	public void setScriptName(String scriptName) {
		this.scriptName = scriptName;
	}

	/**
	 * 認可処理に実行するスクリプトを設定
	 *
	 * @param scriptName 認可処理に実行するスクリプト
	 * @return this
	 */
	public CreateOnceOnetimeTokenRequest withScriptName(String scriptName) {
		setScriptName(scriptName);
		return this;
	}

	/**
	 * 認可するアクションを取得
	 *
	 * @return 認可するアクション
	 */
	public String getGrant() {
		return grant;
	}

	/**
	 * 認可するアクションを設定
	 *
	 * @param grant 認可するアクション
	 */
	public void setGrant(String grant) {
		this.grant = grant;
	}

	/**
	 * 認可するアクションを設定
	 *
	 * @param grant 認可するアクション
	 * @return this
	 */
	public CreateOnceOnetimeTokenRequest withGrant(String grant) {
		setGrant(grant);
		return this;
	}

	/**
	 * grant で指定したアクションに引数として渡すことを許可する内容を取得
	 *
	 * @return grant で指定したアクションに引数として渡すことを許可する内容
	 */
	public String getArgs() {
		return args;
	}

	/**
	 * grant で指定したアクションに引数として渡すことを許可する内容を設定
	 *
	 * @param args grant で指定したアクションに引数として渡すことを許可する内容
	 */
	public void setArgs(String args) {
		this.args = args;
	}

	/**
	 * grant で指定したアクションに引数として渡すことを許可する内容を設定
	 *
	 * @param args grant で指定したアクションに引数として渡すことを許可する内容
	 * @return this
	 */
	public CreateOnceOnetimeTokenRequest withArgs(String args) {
		setArgs(args);
		return this;
	}

}
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

package io.gs2.account.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.account.Gs2Account;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class AuthenticationRequest extends Gs2BasicRequest<AuthenticationRequest> {

	public static class Constant extends Gs2Account.Constant {
		public static final String FUNCTION = "Authentication";
	}

	/** ゲームの名前を指定します。 */
	private String gameName;

	/** 認証する対象アカウントのユーザIDを指定します。 */
	private String userId;

	/** 認証結果の暗号化に利用する GS2-Key の暗号鍵名 */
	private String keyName;

	/** 認証に利用するパスワード */
	private String password;


	/**
	 * ゲームの名前を指定します。を取得
	 *
	 * @return ゲームの名前を指定します。
	 */
	public String getGameName() {
		return gameName;
	}

	/**
	 * ゲームの名前を指定します。を設定
	 *
	 * @param gameName ゲームの名前を指定します。
	 */
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	/**
	 * ゲームの名前を指定します。を設定
	 *
	 * @param gameName ゲームの名前を指定します。
	 * @return this
	 */
	public AuthenticationRequest withGameName(String gameName) {
		setGameName(gameName);
		return this;
	}

	/**
	 * 認証する対象アカウントのユーザIDを指定します。を取得
	 *
	 * @return 認証する対象アカウントのユーザIDを指定します。
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 認証する対象アカウントのユーザIDを指定します。を設定
	 *
	 * @param userId 認証する対象アカウントのユーザIDを指定します。
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 認証する対象アカウントのユーザIDを指定します。を設定
	 *
	 * @param userId 認証する対象アカウントのユーザIDを指定します。
	 * @return this
	 */
	public AuthenticationRequest withUserId(String userId) {
		setUserId(userId);
		return this;
	}

	/**
	 * 認証結果の暗号化に利用する GS2-Key の暗号鍵名を取得
	 *
	 * @return 認証結果の暗号化に利用する GS2-Key の暗号鍵名
	 */
	public String getKeyName() {
		return keyName;
	}

	/**
	 * 認証結果の暗号化に利用する GS2-Key の暗号鍵名を設定
	 *
	 * @param keyName 認証結果の暗号化に利用する GS2-Key の暗号鍵名
	 */
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	/**
	 * 認証結果の暗号化に利用する GS2-Key の暗号鍵名を設定
	 *
	 * @param keyName 認証結果の暗号化に利用する GS2-Key の暗号鍵名
	 * @return this
	 */
	public AuthenticationRequest withKeyName(String keyName) {
		setKeyName(keyName);
		return this;
	}

	/**
	 * 認証に利用するパスワードを取得
	 *
	 * @return 認証に利用するパスワード
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 認証に利用するパスワードを設定
	 *
	 * @param password 認証に利用するパスワード
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 認証に利用するパスワードを設定
	 *
	 * @param password 認証に利用するパスワード
	 * @return this
	 */
	public AuthenticationRequest withPassword(String password) {
		setPassword(password);
		return this;
	}

}
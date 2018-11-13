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
public class DoTakeOverRequest extends Gs2BasicRequest<DoTakeOverRequest> {

	public static class Constant extends Gs2Account.Constant {
		public static final String FUNCTION = "DoTakeOver";
	}

	/** ゲームの名前を指定します。 */
	private String gameName;

	/** 引き継ぎ情報の種類を指定します。 */
	private Integer type;

	/** 引き継ぎ情報のユーザ固有ID */
	private String userIdentifier;

	/** 引き継ぎ設定に指定されたパスワード */
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
	public DoTakeOverRequest withGameName(String gameName) {
		setGameName(gameName);
		return this;
	}

	/**
	 * 引き継ぎ情報の種類を指定します。を取得
	 *
	 * @return 引き継ぎ情報の種類を指定します。
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 引き継ぎ情報の種類を指定します。を設定
	 *
	 * @param type 引き継ぎ情報の種類を指定します。
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 引き継ぎ情報の種類を指定します。を設定
	 *
	 * @param type 引き継ぎ情報の種類を指定します。
	 * @return this
	 */
	public DoTakeOverRequest withType(Integer type) {
		setType(type);
		return this;
	}

	/**
	 * 引き継ぎ情報のユーザ固有IDを取得
	 *
	 * @return 引き継ぎ情報のユーザ固有ID
	 */
	public String getUserIdentifier() {
		return userIdentifier;
	}

	/**
	 * 引き継ぎ情報のユーザ固有IDを設定
	 *
	 * @param userIdentifier 引き継ぎ情報のユーザ固有ID
	 */
	public void setUserIdentifier(String userIdentifier) {
		this.userIdentifier = userIdentifier;
	}

	/**
	 * 引き継ぎ情報のユーザ固有IDを設定
	 *
	 * @param userIdentifier 引き継ぎ情報のユーザ固有ID
	 * @return this
	 */
	public DoTakeOverRequest withUserIdentifier(String userIdentifier) {
		setUserIdentifier(userIdentifier);
		return this;
	}

	/**
	 * 引き継ぎ設定に指定されたパスワードを取得
	 *
	 * @return 引き継ぎ設定に指定されたパスワード
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 引き継ぎ設定に指定されたパスワードを設定
	 *
	 * @param password 引き継ぎ設定に指定されたパスワード
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 引き継ぎ設定に指定されたパスワードを設定
	 *
	 * @param password 引き継ぎ設定に指定されたパスワード
	 * @return this
	 */
	public DoTakeOverRequest withPassword(String password) {
		setPassword(password);
		return this;
	}

}
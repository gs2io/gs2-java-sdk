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
import io.gs2.control.Gs2UserRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateTakeOverRequest extends Gs2UserRequest<UpdateTakeOverRequest> {

	public static class Constant extends Gs2Account.Constant {
		public static final String FUNCTION = "UpdateTakeOver";
	}

	/** ゲームの名前を指定します。 */
	private String gameName;

	/** 更新する引き継ぎ情報の種類を指定します。 */
	private Integer type;

	/** 更新する引き継ぎ情報のユーザ固有IDを指定します。 */
	private String userIdentifier;

	/** 引き継ぎ時に利用する現在設定されているパスワード */
	private String oldPassword;

	/** 引き継ぎ時に利用する新しいパスワード */
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
	public UpdateTakeOverRequest withGameName(String gameName) {
		setGameName(gameName);
		return this;
	}

	/**
	 * 更新する引き継ぎ情報の種類を指定します。を取得
	 *
	 * @return 更新する引き継ぎ情報の種類を指定します。
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 更新する引き継ぎ情報の種類を指定します。を設定
	 *
	 * @param type 更新する引き継ぎ情報の種類を指定します。
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 更新する引き継ぎ情報の種類を指定します。を設定
	 *
	 * @param type 更新する引き継ぎ情報の種類を指定します。
	 * @return this
	 */
	public UpdateTakeOverRequest withType(Integer type) {
		setType(type);
		return this;
	}

	/**
	 * 更新する引き継ぎ情報のユーザ固有IDを指定します。を取得
	 *
	 * @return 更新する引き継ぎ情報のユーザ固有IDを指定します。
	 */
	public String getUserIdentifier() {
		return userIdentifier;
	}

	/**
	 * 更新する引き継ぎ情報のユーザ固有IDを指定します。を設定
	 *
	 * @param userIdentifier 更新する引き継ぎ情報のユーザ固有IDを指定します。
	 */
	public void setUserIdentifier(String userIdentifier) {
		this.userIdentifier = userIdentifier;
	}

	/**
	 * 更新する引き継ぎ情報のユーザ固有IDを指定します。を設定
	 *
	 * @param userIdentifier 更新する引き継ぎ情報のユーザ固有IDを指定します。
	 * @return this
	 */
	public UpdateTakeOverRequest withUserIdentifier(String userIdentifier) {
		setUserIdentifier(userIdentifier);
		return this;
	}

	/**
	 * 引き継ぎ時に利用する現在設定されているパスワードを取得
	 *
	 * @return 引き継ぎ時に利用する現在設定されているパスワード
	 */
	public String getOldPassword() {
		return oldPassword;
	}

	/**
	 * 引き継ぎ時に利用する現在設定されているパスワードを設定
	 *
	 * @param oldPassword 引き継ぎ時に利用する現在設定されているパスワード
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	/**
	 * 引き継ぎ時に利用する現在設定されているパスワードを設定
	 *
	 * @param oldPassword 引き継ぎ時に利用する現在設定されているパスワード
	 * @return this
	 */
	public UpdateTakeOverRequest withOldPassword(String oldPassword) {
		setOldPassword(oldPassword);
		return this;
	}

	/**
	 * 引き継ぎ時に利用する新しいパスワードを取得
	 *
	 * @return 引き継ぎ時に利用する新しいパスワード
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 引き継ぎ時に利用する新しいパスワードを設定
	 *
	 * @param password 引き継ぎ時に利用する新しいパスワード
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 引き継ぎ時に利用する新しいパスワードを設定
	 *
	 * @param password 引き継ぎ時に利用する新しいパスワード
	 * @return this
	 */
	public UpdateTakeOverRequest withPassword(String password) {
		setPassword(password);
		return this;
	}

}
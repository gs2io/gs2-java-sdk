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
public class GetTakeOverRequest extends Gs2UserRequest<GetTakeOverRequest> {

	public static class Constant extends Gs2Account.Constant {
		public static final String FUNCTION = "GetTakeOver";
	}

	/** ゲームの名前を指定します。 */
	private String gameName;

	/** 取得する引き継ぎ情報の種類を指定します。 */
	private Integer type;

	/** 取得する引き継ぎ情報のユーザ固有IDを指定します。 */
	private String userIdentifier;


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
	public GetTakeOverRequest withGameName(String gameName) {
		setGameName(gameName);
		return this;
	}

	/**
	 * 取得する引き継ぎ情報の種類を指定します。を取得
	 *
	 * @return 取得する引き継ぎ情報の種類を指定します。
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 取得する引き継ぎ情報の種類を指定します。を設定
	 *
	 * @param type 取得する引き継ぎ情報の種類を指定します。
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 取得する引き継ぎ情報の種類を指定します。を設定
	 *
	 * @param type 取得する引き継ぎ情報の種類を指定します。
	 * @return this
	 */
	public GetTakeOverRequest withType(Integer type) {
		setType(type);
		return this;
	}

	/**
	 * 取得する引き継ぎ情報のユーザ固有IDを指定します。を取得
	 *
	 * @return 取得する引き継ぎ情報のユーザ固有IDを指定します。
	 */
	public String getUserIdentifier() {
		return userIdentifier;
	}

	/**
	 * 取得する引き継ぎ情報のユーザ固有IDを指定します。を設定
	 *
	 * @param userIdentifier 取得する引き継ぎ情報のユーザ固有IDを指定します。
	 */
	public void setUserIdentifier(String userIdentifier) {
		this.userIdentifier = userIdentifier;
	}

	/**
	 * 取得する引き継ぎ情報のユーザ固有IDを指定します。を設定
	 *
	 * @param userIdentifier 取得する引き継ぎ情報のユーザ固有IDを指定します。
	 * @return this
	 */
	public GetTakeOverRequest withUserIdentifier(String userIdentifier) {
		setUserIdentifier(userIdentifier);
		return this;
	}

}
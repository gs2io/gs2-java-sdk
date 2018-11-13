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

package io.gs2.chat.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.chat.model.*;
import io.gs2.chat.Gs2Chat;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateRoomRequest extends Gs2BasicRequest<CreateRoomRequest> {

	public static class Constant extends Gs2Chat.Constant {
		public static final String FUNCTION = "CreateRoom";
	}

	/** ロビーの名前 */
	private String lobbyName;

	/** ルームID（指定しない場合は自動的に採番されます） */
	private String roomId;

	/** ルームへのアクセスを許可するユーザIDリストを指定 */
	private List<String> allowUserIds;

	/** ルームにアクセスする際にパスワードを要求する場合は文字列を指定 */
	private String password;


	/**
	 * ロビーの名前を取得
	 *
	 * @return ロビーの名前
	 */
	public String getLobbyName() {
		return lobbyName;
	}

	/**
	 * ロビーの名前を設定
	 *
	 * @param lobbyName ロビーの名前
	 */
	public void setLobbyName(String lobbyName) {
		this.lobbyName = lobbyName;
	}

	/**
	 * ロビーの名前を設定
	 *
	 * @param lobbyName ロビーの名前
	 * @return this
	 */
	public CreateRoomRequest withLobbyName(String lobbyName) {
		setLobbyName(lobbyName);
		return this;
	}

	/**
	 * ルームID（指定しない場合は自動的に採番されます）を取得
	 *
	 * @return ルームID（指定しない場合は自動的に採番されます）
	 */
	public String getRoomId() {
		return roomId;
	}

	/**
	 * ルームID（指定しない場合は自動的に採番されます）を設定
	 *
	 * @param roomId ルームID（指定しない場合は自動的に採番されます）
	 */
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	/**
	 * ルームID（指定しない場合は自動的に採番されます）を設定
	 *
	 * @param roomId ルームID（指定しない場合は自動的に採番されます）
	 * @return this
	 */
	public CreateRoomRequest withRoomId(String roomId) {
		setRoomId(roomId);
		return this;
	}

	/**
	 * ルームへのアクセスを許可するユーザIDリストを指定を取得
	 *
	 * @return ルームへのアクセスを許可するユーザIDリストを指定
	 */
	public List<String> getAllowUserIds() {
		return allowUserIds;
	}

	/**
	 * ルームへのアクセスを許可するユーザIDリストを指定を設定
	 *
	 * @param allowUserIds ルームへのアクセスを許可するユーザIDリストを指定
	 */
	public void setAllowUserIds(List<String> allowUserIds) {
		this.allowUserIds = allowUserIds;
	}

	/**
	 * ルームへのアクセスを許可するユーザIDリストを指定を設定
	 *
	 * @param allowUserIds ルームへのアクセスを許可するユーザIDリストを指定
	 * @return this
	 */
	public CreateRoomRequest withAllowUserIds(List<String> allowUserIds) {
		setAllowUserIds(allowUserIds);
		return this;
	}

	/**
	 * ルームにアクセスする際にパスワードを要求する場合は文字列を指定を取得
	 *
	 * @return ルームにアクセスする際にパスワードを要求する場合は文字列を指定
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * ルームにアクセスする際にパスワードを要求する場合は文字列を指定を設定
	 *
	 * @param password ルームにアクセスする際にパスワードを要求する場合は文字列を指定
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * ルームにアクセスする際にパスワードを要求する場合は文字列を指定を設定
	 *
	 * @param password ルームにアクセスする際にパスワードを要求する場合は文字列を指定
	 * @return this
	 */
	public CreateRoomRequest withPassword(String password) {
		setPassword(password);
		return this;
	}

}
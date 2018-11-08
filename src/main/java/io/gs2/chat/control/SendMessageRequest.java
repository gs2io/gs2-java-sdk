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
import io.gs2.control.Gs2UserRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class SendMessageRequest extends Gs2UserRequest<SendMessageRequest> {

	public static class Constant extends Gs2Chat.Constant {
		public static final String FUNCTION = "SendMessage";
	}

	/** ロビーの名前 */
	private String lobbyName;

	/** ルームID */
	private String roomId;

	/** メッセージテキスト */
	private String message;

	/** メッセージメタデータ */
	private String meta;

	/** パスワード */
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
	public SendMessageRequest withLobbyName(String lobbyName) {
		setLobbyName(lobbyName);
		return this;
	}

	/**
	 * ルームIDを取得
	 *
	 * @return ルームID
	 */
	public String getRoomId() {
		return roomId;
	}

	/**
	 * ルームIDを設定
	 *
	 * @param roomId ルームID
	 */
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	/**
	 * ルームIDを設定
	 *
	 * @param roomId ルームID
	 * @return this
	 */
	public SendMessageRequest withRoomId(String roomId) {
		setRoomId(roomId);
		return this;
	}

	/**
	 * メッセージテキストを取得
	 *
	 * @return メッセージテキスト
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * メッセージテキストを設定
	 *
	 * @param message メッセージテキスト
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * メッセージテキストを設定
	 *
	 * @param message メッセージテキスト
	 * @return this
	 */
	public SendMessageRequest withMessage(String message) {
		setMessage(message);
		return this;
	}

	/**
	 * メッセージメタデータを取得
	 *
	 * @return メッセージメタデータ
	 */
	public String getMeta() {
		return meta;
	}

	/**
	 * メッセージメタデータを設定
	 *
	 * @param meta メッセージメタデータ
	 */
	public void setMeta(String meta) {
		this.meta = meta;
	}

	/**
	 * メッセージメタデータを設定
	 *
	 * @param meta メッセージメタデータ
	 * @return this
	 */
	public SendMessageRequest withMeta(String meta) {
		setMeta(meta);
		return this;
	}

	/**
	 * パスワードを取得
	 *
	 * @return パスワード
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * パスワードを設定
	 *
	 * @param password パスワード
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * パスワードを設定
	 *
	 * @param password パスワード
	 * @return this
	 */
	public SendMessageRequest withPassword(String password) {
		setPassword(password);
		return this;
	}

}
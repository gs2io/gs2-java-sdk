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
public class CreateMySubscribeRequest extends Gs2UserRequest<CreateMySubscribeRequest> {

	public static class Constant extends Gs2Chat.Constant {
		public static final String FUNCTION = "CreateMySubscribe";
	}

	/** ロビーの名前 */
	private String lobbyName;

	/** ルームID */
	private String roomId;

	/** GS2-InGamePushNotification 使用時にオフライン転送を使用するか */
	private Boolean enableOfflineTransfer;

	/** GS2-InGamePushNotification 使用時のモバイルプッシュ通知で使用する通知音 */
	private String offlineTransferSound;

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
	public CreateMySubscribeRequest withLobbyName(String lobbyName) {
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
	public CreateMySubscribeRequest withRoomId(String roomId) {
		setRoomId(roomId);
		return this;
	}

	/**
	 * GS2-InGamePushNotification 使用時にオフライン転送を使用するかを取得
	 *
	 * @return GS2-InGamePushNotification 使用時にオフライン転送を使用するか
	 */
	public Boolean getEnableOfflineTransfer() {
		return enableOfflineTransfer;
	}

	/**
	 * GS2-InGamePushNotification 使用時にオフライン転送を使用するかを設定
	 *
	 * @param enableOfflineTransfer GS2-InGamePushNotification 使用時にオフライン転送を使用するか
	 */
	public void setEnableOfflineTransfer(Boolean enableOfflineTransfer) {
		this.enableOfflineTransfer = enableOfflineTransfer;
	}

	/**
	 * GS2-InGamePushNotification 使用時にオフライン転送を使用するかを設定
	 *
	 * @param enableOfflineTransfer GS2-InGamePushNotification 使用時にオフライン転送を使用するか
	 * @return this
	 */
	public CreateMySubscribeRequest withEnableOfflineTransfer(Boolean enableOfflineTransfer) {
		setEnableOfflineTransfer(enableOfflineTransfer);
		return this;
	}

	/**
	 * GS2-InGamePushNotification 使用時のモバイルプッシュ通知で使用する通知音を取得
	 *
	 * @return GS2-InGamePushNotification 使用時のモバイルプッシュ通知で使用する通知音
	 */
	public String getOfflineTransferSound() {
		return offlineTransferSound;
	}

	/**
	 * GS2-InGamePushNotification 使用時のモバイルプッシュ通知で使用する通知音を設定
	 *
	 * @param offlineTransferSound GS2-InGamePushNotification 使用時のモバイルプッシュ通知で使用する通知音
	 */
	public void setOfflineTransferSound(String offlineTransferSound) {
		this.offlineTransferSound = offlineTransferSound;
	}

	/**
	 * GS2-InGamePushNotification 使用時のモバイルプッシュ通知で使用する通知音を設定
	 *
	 * @param offlineTransferSound GS2-InGamePushNotification 使用時のモバイルプッシュ通知で使用する通知音
	 * @return this
	 */
	public CreateMySubscribeRequest withOfflineTransferSound(String offlineTransferSound) {
		setOfflineTransferSound(offlineTransferSound);
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
	public CreateMySubscribeRequest withPassword(String password) {
		setPassword(password);
		return this;
	}

}
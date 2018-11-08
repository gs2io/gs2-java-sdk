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
public class UpdateLobbyRequest extends Gs2BasicRequest<UpdateLobbyRequest> {

	public static class Constant extends Gs2Chat.Constant {
		public static final String FUNCTION = "UpdateLobby";
	}

	/** ロビーの名前 */
	private String lobbyName;

	/** 説明文 */
	private String description;

	/** サービスクラス */
	private String serviceClass;

	/** 通知方式 */
	private String notificationType;

	/** http/https を選択した際の通知先URL */
	private String notificationUrl;

	/** gs2-in-game-push-notification を選択した際の GS2-InGamePushNotification のゲーム名 */
	private String notificationGameName;

	/** ログを記録するか */
	private Boolean logging;

	/** ログを記録する日数 */
	private Integer loggingDate;

	/** ルーム作成時 に実行されるGS2-Script */
	private String createRoomTriggerScript;

	/** ルーム作成完了時 に実行されるGS2-Script */
	private String createRoomDoneTriggerScript;

	/** ルーム削除時 に実行されるGS2-Script */
	private String deleteRoomTriggerScript;

	/** ルーム削除完了時 に実行されるGS2-Script */
	private String deleteRoomDoneTriggerScript;

	/** ルーム購読時 に実行されるGS2-Script */
	private String createSubscribeTriggerScript;

	/** ルーム購読完了時 に実行されるGS2-Script */
	private String createSubscribeDoneTriggerScript;

	/** ルーム購読解除時 に実行されるGS2-Script */
	private String deleteSubscribeTriggerScript;

	/** ルーム購読解除完了時 に実行されるGS2-Script */
	private String deleteSubscribeDoneTriggerScript;

	/** メッセージ送信時 に実行されるGS2-Script */
	private String sendMessageTriggerScript;

	/** メッセージ送信完了時 に実行されるGS2-Script */
	private String sendMessageDoneTriggerScript;


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
	public UpdateLobbyRequest withLobbyName(String lobbyName) {
		setLobbyName(lobbyName);
		return this;
	}

	/**
	 * 説明文を取得
	 *
	 * @return 説明文
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 説明文を設定
	 *
	 * @param description 説明文
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 説明文を設定
	 *
	 * @param description 説明文
	 * @return this
	 */
	public UpdateLobbyRequest withDescription(String description) {
		setDescription(description);
		return this;
	}

	/**
	 * サービスクラスを取得
	 *
	 * @return サービスクラス
	 */
	public String getServiceClass() {
		return serviceClass;
	}

	/**
	 * サービスクラスを設定
	 *
	 * @param serviceClass サービスクラス
	 */
	public void setServiceClass(String serviceClass) {
		this.serviceClass = serviceClass;
	}

	/**
	 * サービスクラスを設定
	 *
	 * @param serviceClass サービスクラス
	 * @return this
	 */
	public UpdateLobbyRequest withServiceClass(String serviceClass) {
		setServiceClass(serviceClass);
		return this;
	}

	/**
	 * 通知方式を取得
	 *
	 * @return 通知方式
	 */
	public String getNotificationType() {
		return notificationType;
	}

	/**
	 * 通知方式を設定
	 *
	 * @param notificationType 通知方式
	 */
	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	/**
	 * 通知方式を設定
	 *
	 * @param notificationType 通知方式
	 * @return this
	 */
	public UpdateLobbyRequest withNotificationType(String notificationType) {
		setNotificationType(notificationType);
		return this;
	}

	/**
	 * http/https を選択した際の通知先URLを取得
	 *
	 * @return http/https を選択した際の通知先URL
	 */
	public String getNotificationUrl() {
		return notificationUrl;
	}

	/**
	 * http/https を選択した際の通知先URLを設定
	 *
	 * @param notificationUrl http/https を選択した際の通知先URL
	 */
	public void setNotificationUrl(String notificationUrl) {
		this.notificationUrl = notificationUrl;
	}

	/**
	 * http/https を選択した際の通知先URLを設定
	 *
	 * @param notificationUrl http/https を選択した際の通知先URL
	 * @return this
	 */
	public UpdateLobbyRequest withNotificationUrl(String notificationUrl) {
		setNotificationUrl(notificationUrl);
		return this;
	}

	/**
	 * gs2-in-game-push-notification を選択した際の GS2-InGamePushNotification のゲーム名を取得
	 *
	 * @return gs2-in-game-push-notification を選択した際の GS2-InGamePushNotification のゲーム名
	 */
	public String getNotificationGameName() {
		return notificationGameName;
	}

	/**
	 * gs2-in-game-push-notification を選択した際の GS2-InGamePushNotification のゲーム名を設定
	 *
	 * @param notificationGameName gs2-in-game-push-notification を選択した際の GS2-InGamePushNotification のゲーム名
	 */
	public void setNotificationGameName(String notificationGameName) {
		this.notificationGameName = notificationGameName;
	}

	/**
	 * gs2-in-game-push-notification を選択した際の GS2-InGamePushNotification のゲーム名を設定
	 *
	 * @param notificationGameName gs2-in-game-push-notification を選択した際の GS2-InGamePushNotification のゲーム名
	 * @return this
	 */
	public UpdateLobbyRequest withNotificationGameName(String notificationGameName) {
		setNotificationGameName(notificationGameName);
		return this;
	}

	/**
	 * ログを記録するかを取得
	 *
	 * @return ログを記録するか
	 */
	public Boolean getLogging() {
		return logging;
	}

	/**
	 * ログを記録するかを設定
	 *
	 * @param logging ログを記録するか
	 */
	public void setLogging(Boolean logging) {
		this.logging = logging;
	}

	/**
	 * ログを記録するかを設定
	 *
	 * @param logging ログを記録するか
	 * @return this
	 */
	public UpdateLobbyRequest withLogging(Boolean logging) {
		setLogging(logging);
		return this;
	}

	/**
	 * ログを記録する日数を取得
	 *
	 * @return ログを記録する日数
	 */
	public Integer getLoggingDate() {
		return loggingDate;
	}

	/**
	 * ログを記録する日数を設定
	 *
	 * @param loggingDate ログを記録する日数
	 */
	public void setLoggingDate(Integer loggingDate) {
		this.loggingDate = loggingDate;
	}

	/**
	 * ログを記録する日数を設定
	 *
	 * @param loggingDate ログを記録する日数
	 * @return this
	 */
	public UpdateLobbyRequest withLoggingDate(Integer loggingDate) {
		setLoggingDate(loggingDate);
		return this;
	}

	/**
	 * ルーム作成時 に実行されるGS2-Scriptを取得
	 *
	 * @return ルーム作成時 に実行されるGS2-Script
	 */
	public String getCreateRoomTriggerScript() {
		return createRoomTriggerScript;
	}

	/**
	 * ルーム作成時 に実行されるGS2-Scriptを設定
	 *
	 * @param createRoomTriggerScript ルーム作成時 に実行されるGS2-Script
	 */
	public void setCreateRoomTriggerScript(String createRoomTriggerScript) {
		this.createRoomTriggerScript = createRoomTriggerScript;
	}

	/**
	 * ルーム作成時 に実行されるGS2-Scriptを設定
	 *
	 * @param createRoomTriggerScript ルーム作成時 に実行されるGS2-Script
	 * @return this
	 */
	public UpdateLobbyRequest withCreateRoomTriggerScript(String createRoomTriggerScript) {
		setCreateRoomTriggerScript(createRoomTriggerScript);
		return this;
	}

	/**
	 * ルーム作成完了時 に実行されるGS2-Scriptを取得
	 *
	 * @return ルーム作成完了時 に実行されるGS2-Script
	 */
	public String getCreateRoomDoneTriggerScript() {
		return createRoomDoneTriggerScript;
	}

	/**
	 * ルーム作成完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param createRoomDoneTriggerScript ルーム作成完了時 に実行されるGS2-Script
	 */
	public void setCreateRoomDoneTriggerScript(String createRoomDoneTriggerScript) {
		this.createRoomDoneTriggerScript = createRoomDoneTriggerScript;
	}

	/**
	 * ルーム作成完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param createRoomDoneTriggerScript ルーム作成完了時 に実行されるGS2-Script
	 * @return this
	 */
	public UpdateLobbyRequest withCreateRoomDoneTriggerScript(String createRoomDoneTriggerScript) {
		setCreateRoomDoneTriggerScript(createRoomDoneTriggerScript);
		return this;
	}

	/**
	 * ルーム削除時 に実行されるGS2-Scriptを取得
	 *
	 * @return ルーム削除時 に実行されるGS2-Script
	 */
	public String getDeleteRoomTriggerScript() {
		return deleteRoomTriggerScript;
	}

	/**
	 * ルーム削除時 に実行されるGS2-Scriptを設定
	 *
	 * @param deleteRoomTriggerScript ルーム削除時 に実行されるGS2-Script
	 */
	public void setDeleteRoomTriggerScript(String deleteRoomTriggerScript) {
		this.deleteRoomTriggerScript = deleteRoomTriggerScript;
	}

	/**
	 * ルーム削除時 に実行されるGS2-Scriptを設定
	 *
	 * @param deleteRoomTriggerScript ルーム削除時 に実行されるGS2-Script
	 * @return this
	 */
	public UpdateLobbyRequest withDeleteRoomTriggerScript(String deleteRoomTriggerScript) {
		setDeleteRoomTriggerScript(deleteRoomTriggerScript);
		return this;
	}

	/**
	 * ルーム削除完了時 に実行されるGS2-Scriptを取得
	 *
	 * @return ルーム削除完了時 に実行されるGS2-Script
	 */
	public String getDeleteRoomDoneTriggerScript() {
		return deleteRoomDoneTriggerScript;
	}

	/**
	 * ルーム削除完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param deleteRoomDoneTriggerScript ルーム削除完了時 に実行されるGS2-Script
	 */
	public void setDeleteRoomDoneTriggerScript(String deleteRoomDoneTriggerScript) {
		this.deleteRoomDoneTriggerScript = deleteRoomDoneTriggerScript;
	}

	/**
	 * ルーム削除完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param deleteRoomDoneTriggerScript ルーム削除完了時 に実行されるGS2-Script
	 * @return this
	 */
	public UpdateLobbyRequest withDeleteRoomDoneTriggerScript(String deleteRoomDoneTriggerScript) {
		setDeleteRoomDoneTriggerScript(deleteRoomDoneTriggerScript);
		return this;
	}

	/**
	 * ルーム購読時 に実行されるGS2-Scriptを取得
	 *
	 * @return ルーム購読時 に実行されるGS2-Script
	 */
	public String getCreateSubscribeTriggerScript() {
		return createSubscribeTriggerScript;
	}

	/**
	 * ルーム購読時 に実行されるGS2-Scriptを設定
	 *
	 * @param createSubscribeTriggerScript ルーム購読時 に実行されるGS2-Script
	 */
	public void setCreateSubscribeTriggerScript(String createSubscribeTriggerScript) {
		this.createSubscribeTriggerScript = createSubscribeTriggerScript;
	}

	/**
	 * ルーム購読時 に実行されるGS2-Scriptを設定
	 *
	 * @param createSubscribeTriggerScript ルーム購読時 に実行されるGS2-Script
	 * @return this
	 */
	public UpdateLobbyRequest withCreateSubscribeTriggerScript(String createSubscribeTriggerScript) {
		setCreateSubscribeTriggerScript(createSubscribeTriggerScript);
		return this;
	}

	/**
	 * ルーム購読完了時 に実行されるGS2-Scriptを取得
	 *
	 * @return ルーム購読完了時 に実行されるGS2-Script
	 */
	public String getCreateSubscribeDoneTriggerScript() {
		return createSubscribeDoneTriggerScript;
	}

	/**
	 * ルーム購読完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param createSubscribeDoneTriggerScript ルーム購読完了時 に実行されるGS2-Script
	 */
	public void setCreateSubscribeDoneTriggerScript(String createSubscribeDoneTriggerScript) {
		this.createSubscribeDoneTriggerScript = createSubscribeDoneTriggerScript;
	}

	/**
	 * ルーム購読完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param createSubscribeDoneTriggerScript ルーム購読完了時 に実行されるGS2-Script
	 * @return this
	 */
	public UpdateLobbyRequest withCreateSubscribeDoneTriggerScript(String createSubscribeDoneTriggerScript) {
		setCreateSubscribeDoneTriggerScript(createSubscribeDoneTriggerScript);
		return this;
	}

	/**
	 * ルーム購読解除時 に実行されるGS2-Scriptを取得
	 *
	 * @return ルーム購読解除時 に実行されるGS2-Script
	 */
	public String getDeleteSubscribeTriggerScript() {
		return deleteSubscribeTriggerScript;
	}

	/**
	 * ルーム購読解除時 に実行されるGS2-Scriptを設定
	 *
	 * @param deleteSubscribeTriggerScript ルーム購読解除時 に実行されるGS2-Script
	 */
	public void setDeleteSubscribeTriggerScript(String deleteSubscribeTriggerScript) {
		this.deleteSubscribeTriggerScript = deleteSubscribeTriggerScript;
	}

	/**
	 * ルーム購読解除時 に実行されるGS2-Scriptを設定
	 *
	 * @param deleteSubscribeTriggerScript ルーム購読解除時 に実行されるGS2-Script
	 * @return this
	 */
	public UpdateLobbyRequest withDeleteSubscribeTriggerScript(String deleteSubscribeTriggerScript) {
		setDeleteSubscribeTriggerScript(deleteSubscribeTriggerScript);
		return this;
	}

	/**
	 * ルーム購読解除完了時 に実行されるGS2-Scriptを取得
	 *
	 * @return ルーム購読解除完了時 に実行されるGS2-Script
	 */
	public String getDeleteSubscribeDoneTriggerScript() {
		return deleteSubscribeDoneTriggerScript;
	}

	/**
	 * ルーム購読解除完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param deleteSubscribeDoneTriggerScript ルーム購読解除完了時 に実行されるGS2-Script
	 */
	public void setDeleteSubscribeDoneTriggerScript(String deleteSubscribeDoneTriggerScript) {
		this.deleteSubscribeDoneTriggerScript = deleteSubscribeDoneTriggerScript;
	}

	/**
	 * ルーム購読解除完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param deleteSubscribeDoneTriggerScript ルーム購読解除完了時 に実行されるGS2-Script
	 * @return this
	 */
	public UpdateLobbyRequest withDeleteSubscribeDoneTriggerScript(String deleteSubscribeDoneTriggerScript) {
		setDeleteSubscribeDoneTriggerScript(deleteSubscribeDoneTriggerScript);
		return this;
	}

	/**
	 * メッセージ送信時 に実行されるGS2-Scriptを取得
	 *
	 * @return メッセージ送信時 に実行されるGS2-Script
	 */
	public String getSendMessageTriggerScript() {
		return sendMessageTriggerScript;
	}

	/**
	 * メッセージ送信時 に実行されるGS2-Scriptを設定
	 *
	 * @param sendMessageTriggerScript メッセージ送信時 に実行されるGS2-Script
	 */
	public void setSendMessageTriggerScript(String sendMessageTriggerScript) {
		this.sendMessageTriggerScript = sendMessageTriggerScript;
	}

	/**
	 * メッセージ送信時 に実行されるGS2-Scriptを設定
	 *
	 * @param sendMessageTriggerScript メッセージ送信時 に実行されるGS2-Script
	 * @return this
	 */
	public UpdateLobbyRequest withSendMessageTriggerScript(String sendMessageTriggerScript) {
		setSendMessageTriggerScript(sendMessageTriggerScript);
		return this;
	}

	/**
	 * メッセージ送信完了時 に実行されるGS2-Scriptを取得
	 *
	 * @return メッセージ送信完了時 に実行されるGS2-Script
	 */
	public String getSendMessageDoneTriggerScript() {
		return sendMessageDoneTriggerScript;
	}

	/**
	 * メッセージ送信完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param sendMessageDoneTriggerScript メッセージ送信完了時 に実行されるGS2-Script
	 */
	public void setSendMessageDoneTriggerScript(String sendMessageDoneTriggerScript) {
		this.sendMessageDoneTriggerScript = sendMessageDoneTriggerScript;
	}

	/**
	 * メッセージ送信完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param sendMessageDoneTriggerScript メッセージ送信完了時 に実行されるGS2-Script
	 * @return this
	 */
	public UpdateLobbyRequest withSendMessageDoneTriggerScript(String sendMessageDoneTriggerScript) {
		setSendMessageDoneTriggerScript(sendMessageDoneTriggerScript);
		return this;
	}

}
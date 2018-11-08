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

package io.gs2.chat.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * ロビー
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Lobby implements Serializable {

	/** ロビーGRN */
	private String lobbyId;

	/** オーナーID */
	private String ownerId;

	/** ゲーム名 */
	private String name;

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

	/** 作成日時(エポック秒) */
	private Integer createAt;

	/** 最終更新日時(エポック秒) */
	private Integer updateAt;


	/**
	 * ロビーGRNを取得
	 *
	 * @return ロビーGRN
	 */
	public String getLobbyId() {
		return lobbyId;
	}

	/**
	 * ロビーGRNを設定
	 *
	 * @param lobbyId ロビーGRN
	 */
	public void setLobbyId(String lobbyId) {
		this.lobbyId = lobbyId;
	}

	/**
	 * ロビーGRNを設定
	 *
	 * @param lobbyId ロビーGRN
	 * @return this
	 */
	public Lobby withLobbyId(String lobbyId) {
		this.lobbyId = lobbyId;
		return this;
	}

	/**
	 * オーナーIDを取得
	 *
	 * @return オーナーID
	 */
	public String getOwnerId() {
		return ownerId;
	}

	/**
	 * オーナーIDを設定
	 *
	 * @param ownerId オーナーID
	 */
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * オーナーIDを設定
	 *
	 * @param ownerId オーナーID
	 * @return this
	 */
	public Lobby withOwnerId(String ownerId) {
		this.ownerId = ownerId;
		return this;
	}

	/**
	 * ゲーム名を取得
	 *
	 * @return ゲーム名
	 */
	public String getName() {
		return name;
	}

	/**
	 * ゲーム名を設定
	 *
	 * @param name ゲーム名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ゲーム名を設定
	 *
	 * @param name ゲーム名
	 * @return this
	 */
	public Lobby withName(String name) {
		this.name = name;
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
	public Lobby withDescription(String description) {
		this.description = description;
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
	public Lobby withServiceClass(String serviceClass) {
		this.serviceClass = serviceClass;
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
	public Lobby withNotificationType(String notificationType) {
		this.notificationType = notificationType;
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
	public Lobby withNotificationUrl(String notificationUrl) {
		this.notificationUrl = notificationUrl;
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
	public Lobby withNotificationGameName(String notificationGameName) {
		this.notificationGameName = notificationGameName;
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
	public Lobby withLogging(Boolean logging) {
		this.logging = logging;
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
	public Lobby withLoggingDate(Integer loggingDate) {
		this.loggingDate = loggingDate;
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
	public Lobby withCreateRoomTriggerScript(String createRoomTriggerScript) {
		this.createRoomTriggerScript = createRoomTriggerScript;
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
	public Lobby withCreateRoomDoneTriggerScript(String createRoomDoneTriggerScript) {
		this.createRoomDoneTriggerScript = createRoomDoneTriggerScript;
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
	public Lobby withDeleteRoomTriggerScript(String deleteRoomTriggerScript) {
		this.deleteRoomTriggerScript = deleteRoomTriggerScript;
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
	public Lobby withDeleteRoomDoneTriggerScript(String deleteRoomDoneTriggerScript) {
		this.deleteRoomDoneTriggerScript = deleteRoomDoneTriggerScript;
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
	public Lobby withCreateSubscribeTriggerScript(String createSubscribeTriggerScript) {
		this.createSubscribeTriggerScript = createSubscribeTriggerScript;
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
	public Lobby withCreateSubscribeDoneTriggerScript(String createSubscribeDoneTriggerScript) {
		this.createSubscribeDoneTriggerScript = createSubscribeDoneTriggerScript;
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
	public Lobby withDeleteSubscribeTriggerScript(String deleteSubscribeTriggerScript) {
		this.deleteSubscribeTriggerScript = deleteSubscribeTriggerScript;
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
	public Lobby withDeleteSubscribeDoneTriggerScript(String deleteSubscribeDoneTriggerScript) {
		this.deleteSubscribeDoneTriggerScript = deleteSubscribeDoneTriggerScript;
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
	public Lobby withSendMessageTriggerScript(String sendMessageTriggerScript) {
		this.sendMessageTriggerScript = sendMessageTriggerScript;
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
	public Lobby withSendMessageDoneTriggerScript(String sendMessageDoneTriggerScript) {
		this.sendMessageDoneTriggerScript = sendMessageDoneTriggerScript;
		return this;
	}

	/**
	 * 作成日時(エポック秒)を取得
	 *
	 * @return 作成日時(エポック秒)
	 */
	public Integer getCreateAt() {
		return createAt;
	}

	/**
	 * 作成日時(エポック秒)を設定
	 *
	 * @param createAt 作成日時(エポック秒)
	 */
	public void setCreateAt(Integer createAt) {
		this.createAt = createAt;
	}

	/**
	 * 作成日時(エポック秒)を設定
	 *
	 * @param createAt 作成日時(エポック秒)
	 * @return this
	 */
	public Lobby withCreateAt(Integer createAt) {
		this.createAt = createAt;
		return this;
	}

	/**
	 * 最終更新日時(エポック秒)を取得
	 *
	 * @return 最終更新日時(エポック秒)
	 */
	public Integer getUpdateAt() {
		return updateAt;
	}

	/**
	 * 最終更新日時(エポック秒)を設定
	 *
	 * @param updateAt 最終更新日時(エポック秒)
	 */
	public void setUpdateAt(Integer updateAt) {
		this.updateAt = updateAt;
	}

	/**
	 * 最終更新日時(エポック秒)を設定
	 *
	 * @param updateAt 最終更新日時(エポック秒)
	 * @return this
	 */
	public Lobby withUpdateAt(Integer updateAt) {
		this.updateAt = updateAt;
		return this;
	}


    public ObjectNode toJson() {

		ObjectNode body = JsonNodeFactory.instance.objectNode()

            .put("lobbyId", this.getLobbyId())
            .put("ownerId", this.getOwnerId())
            .put("name", this.getName())
            .put("description", this.getDescription())
            .put("serviceClass", this.getServiceClass())
            .put("notificationType", this.getNotificationType())
            .put("notificationUrl", this.getNotificationUrl())
            .put("notificationGameName", this.getNotificationGameName())
            .put("logging", this.getLogging())
            .put("loggingDate", this.getLoggingDate())
            .put("createRoomTriggerScript", this.getCreateRoomTriggerScript())
            .put("createRoomDoneTriggerScript", this.getCreateRoomDoneTriggerScript())
            .put("deleteRoomTriggerScript", this.getDeleteRoomTriggerScript())
            .put("deleteRoomDoneTriggerScript", this.getDeleteRoomDoneTriggerScript())
            .put("createSubscribeTriggerScript", this.getCreateSubscribeTriggerScript())
            .put("createSubscribeDoneTriggerScript", this.getCreateSubscribeDoneTriggerScript())
            .put("deleteSubscribeTriggerScript", this.getDeleteSubscribeTriggerScript())
            .put("deleteSubscribeDoneTriggerScript", this.getDeleteSubscribeDoneTriggerScript())
            .put("sendMessageTriggerScript", this.getSendMessageTriggerScript())
            .put("sendMessageDoneTriggerScript", this.getSendMessageDoneTriggerScript())
            .put("createAt", this.getCreateAt())
            .put("updateAt", this.getUpdateAt());

        return body;
    }
}
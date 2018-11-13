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

package io.gs2.jobQueue.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.jobQueue.model.*;
import io.gs2.jobQueue.Gs2JobQueue;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateQueueRequest extends Gs2BasicRequest<CreateQueueRequest> {

	public static class Constant extends Gs2JobQueue.Constant {
		public static final String FUNCTION = "CreateQueue";
	}

	/** 名前 */
	private String name;

	/** 説明文 */
	private String description;

	/** ジョブが追加されたときの通知方式 */
	private String notificationType;

	/** http/https を選択した際の通知先URL */
	private String notificationUrl;

	/** gs2-in-game-push-notification を選択した際の GS2-InGamePushNotification のゲーム名 */
	private String notificationGameName;


	/**
	 * 名前を取得
	 *
	 * @return 名前
	 */
	public String getName() {
		return name;
	}

	/**
	 * 名前を設定
	 *
	 * @param name 名前
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 名前を設定
	 *
	 * @param name 名前
	 * @return this
	 */
	public CreateQueueRequest withName(String name) {
		setName(name);
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
	public CreateQueueRequest withDescription(String description) {
		setDescription(description);
		return this;
	}

	/**
	 * ジョブが追加されたときの通知方式を取得
	 *
	 * @return ジョブが追加されたときの通知方式
	 */
	public String getNotificationType() {
		return notificationType;
	}

	/**
	 * ジョブが追加されたときの通知方式を設定
	 *
	 * @param notificationType ジョブが追加されたときの通知方式
	 */
	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	/**
	 * ジョブが追加されたときの通知方式を設定
	 *
	 * @param notificationType ジョブが追加されたときの通知方式
	 * @return this
	 */
	public CreateQueueRequest withNotificationType(String notificationType) {
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
	public CreateQueueRequest withNotificationUrl(String notificationUrl) {
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
	public CreateQueueRequest withNotificationGameName(String notificationGameName) {
		setNotificationGameName(notificationGameName);
		return this;
	}

}
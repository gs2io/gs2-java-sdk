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
public class UpdateQueueRequest extends Gs2BasicRequest<UpdateQueueRequest> {

	public static class Constant extends Gs2JobQueue.Constant {
		public static final String FUNCTION = "UpdateQueue";
	}

	/** ジョブキューの名前を指定します。 */
	private String queueName;

	/** 説明文 */
	private String description;

	/** ジョブが追加されたときの通知方式 */
	private String notificationType;

	/** http/https を選択した際の通知先URL */
	private String notificationUrl;

	/** gs2-in-game-push-notification を選択した際の GS2-InGamePushNotification のゲーム名 */
	private String notificationGameName;


	/**
	 * ジョブキューの名前を指定します。を取得
	 *
	 * @return ジョブキューの名前を指定します。
	 */
	public String getQueueName() {
		return queueName;
	}

	/**
	 * ジョブキューの名前を指定します。を設定
	 *
	 * @param queueName ジョブキューの名前を指定します。
	 */
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	/**
	 * ジョブキューの名前を指定します。を設定
	 *
	 * @param queueName ジョブキューの名前を指定します。
	 * @return this
	 */
	public UpdateQueueRequest withQueueName(String queueName) {
		setQueueName(queueName);
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
	public UpdateQueueRequest withDescription(String description) {
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
	public UpdateQueueRequest withNotificationType(String notificationType) {
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
	public UpdateQueueRequest withNotificationUrl(String notificationUrl) {
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
	public UpdateQueueRequest withNotificationGameName(String notificationGameName) {
		setNotificationGameName(notificationGameName);
		return this;
	}

}
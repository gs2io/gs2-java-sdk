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

package io.gs2.notification.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.notification.Gs2Notification;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateSubscribeRequest extends Gs2BasicRequest<CreateSubscribeRequest> {

	public static class Constant extends Gs2Notification.Constant {
		public static final String FUNCTION = "CreateSubscribe";
	}

	/** 通知の名前を指定します。 */
	private String notificationName;

	/** 通知に利用する方式 */
	private String type;

	/** 通知先 */
	private String endpoint;


	/**
	 * 通知の名前を指定します。を取得
	 *
	 * @return 通知の名前を指定します。
	 */
	public String getNotificationName() {
		return notificationName;
	}

	/**
	 * 通知の名前を指定します。を設定
	 *
	 * @param notificationName 通知の名前を指定します。
	 */
	public void setNotificationName(String notificationName) {
		this.notificationName = notificationName;
	}

	/**
	 * 通知の名前を指定します。を設定
	 *
	 * @param notificationName 通知の名前を指定します。
	 * @return this
	 */
	public CreateSubscribeRequest withNotificationName(String notificationName) {
		setNotificationName(notificationName);
		return this;
	}

	/**
	 * 通知に利用する方式を取得
	 *
	 * @return 通知に利用する方式
	 */
	public String getType() {
		return type;
	}

	/**
	 * 通知に利用する方式を設定
	 *
	 * @param type 通知に利用する方式
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 通知に利用する方式を設定
	 *
	 * @param type 通知に利用する方式
	 * @return this
	 */
	public CreateSubscribeRequest withType(String type) {
		setType(type);
		return this;
	}

	/**
	 * 通知先を取得
	 *
	 * @return 通知先
	 */
	public String getEndpoint() {
		return endpoint;
	}

	/**
	 * 通知先を設定
	 *
	 * @param endpoint 通知先
	 */
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	/**
	 * 通知先を設定
	 *
	 * @param endpoint 通知先
	 * @return this
	 */
	public CreateSubscribeRequest withEndpoint(String endpoint) {
		setEndpoint(endpoint);
		return this;
	}

}
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

package io.gs2.inbox.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.inbox.model.*;
import io.gs2.inbox.Gs2Inbox;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class SendMessageRequest extends Gs2BasicRequest<SendMessageRequest> {

	public static class Constant extends Gs2Inbox.Constant {
		public static final String FUNCTION = "SendMessage";
	}

	/** 受信ボックスの名前を指定します。 */
	private String inboxName;

	/** メッセージを送信する相手のユーザID */
	private String userId;

	/** 送信するメッセージ本文 */
	private String message;

	/** true を設定すると、メッセージ開封時に受信ボックスに指定された連携用URLにメッセージIDが通知されます */
	private Boolean cooperation;


	/**
	 * 受信ボックスの名前を指定します。を取得
	 *
	 * @return 受信ボックスの名前を指定します。
	 */
	public String getInboxName() {
		return inboxName;
	}

	/**
	 * 受信ボックスの名前を指定します。を設定
	 *
	 * @param inboxName 受信ボックスの名前を指定します。
	 */
	public void setInboxName(String inboxName) {
		this.inboxName = inboxName;
	}

	/**
	 * 受信ボックスの名前を指定します。を設定
	 *
	 * @param inboxName 受信ボックスの名前を指定します。
	 * @return this
	 */
	public SendMessageRequest withInboxName(String inboxName) {
		setInboxName(inboxName);
		return this;
	}

	/**
	 * メッセージを送信する相手のユーザIDを取得
	 *
	 * @return メッセージを送信する相手のユーザID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * メッセージを送信する相手のユーザIDを設定
	 *
	 * @param userId メッセージを送信する相手のユーザID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * メッセージを送信する相手のユーザIDを設定
	 *
	 * @param userId メッセージを送信する相手のユーザID
	 * @return this
	 */
	public SendMessageRequest withUserId(String userId) {
		setUserId(userId);
		return this;
	}

	/**
	 * 送信するメッセージ本文を取得
	 *
	 * @return 送信するメッセージ本文
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 送信するメッセージ本文を設定
	 *
	 * @param message 送信するメッセージ本文
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 送信するメッセージ本文を設定
	 *
	 * @param message 送信するメッセージ本文
	 * @return this
	 */
	public SendMessageRequest withMessage(String message) {
		setMessage(message);
		return this;
	}

	/**
	 * true を設定すると、メッセージ開封時に受信ボックスに指定された連携用URLにメッセージIDが通知されますを取得
	 *
	 * @return true を設定すると、メッセージ開封時に受信ボックスに指定された連携用URLにメッセージIDが通知されます
	 */
	public Boolean getCooperation() {
		return cooperation;
	}

	/**
	 * true を設定すると、メッセージ開封時に受信ボックスに指定された連携用URLにメッセージIDが通知されますを設定
	 *
	 * @param cooperation true を設定すると、メッセージ開封時に受信ボックスに指定された連携用URLにメッセージIDが通知されます
	 */
	public void setCooperation(Boolean cooperation) {
		this.cooperation = cooperation;
	}

	/**
	 * true を設定すると、メッセージ開封時に受信ボックスに指定された連携用URLにメッセージIDが通知されますを設定
	 *
	 * @param cooperation true を設定すると、メッセージ開封時に受信ボックスに指定された連携用URLにメッセージIDが通知されます
	 * @return this
	 */
	public SendMessageRequest withCooperation(Boolean cooperation) {
		setCooperation(cooperation);
		return this;
	}

}
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
public class UpdateInboxRequest extends Gs2BasicRequest<UpdateInboxRequest> {

	public static class Constant extends Gs2Inbox.Constant {
		public static final String FUNCTION = "UpdateInbox";
	}

	/** 受信ボックスの名前を指定します。 */
	private String inboxName;

	/** 説明文 */
	private String description;

	/** サービスクラス */
	private String serviceClass;

	/** メッセージの開封通知先URL */
	private String cooperationUrl;

	/** メッセージ受信時 に実行されるGS2-Script */
	private String receiveMessageTriggerScript;

	/** メッセージ受信完了時 に実行されるGS2-Script */
	private String receiveMessageDoneTriggerScript;

	/** メッセージ開封時 に実行されるGS2-Script */
	private String readMessageTriggerScript;

	/** メッセージ開封完了時 に実行されるGS2-Script */
	private String readMessageDoneTriggerScript;

	/** メッセージ削除時 に実行されるGS2-Script */
	private String deleteMessageTriggerScript;

	/** メッセージ削除完了時 に実行されるGS2-Script */
	private String deleteMessageDoneTriggerScript;


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
	public UpdateInboxRequest withInboxName(String inboxName) {
		setInboxName(inboxName);
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
	public UpdateInboxRequest withDescription(String description) {
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
	public UpdateInboxRequest withServiceClass(String serviceClass) {
		setServiceClass(serviceClass);
		return this;
	}

	/**
	 * メッセージの開封通知先URLを取得
	 *
	 * @return メッセージの開封通知先URL
	 */
	public String getCooperationUrl() {
		return cooperationUrl;
	}

	/**
	 * メッセージの開封通知先URLを設定
	 *
	 * @param cooperationUrl メッセージの開封通知先URL
	 */
	public void setCooperationUrl(String cooperationUrl) {
		this.cooperationUrl = cooperationUrl;
	}

	/**
	 * メッセージの開封通知先URLを設定
	 *
	 * @param cooperationUrl メッセージの開封通知先URL
	 * @return this
	 */
	public UpdateInboxRequest withCooperationUrl(String cooperationUrl) {
		setCooperationUrl(cooperationUrl);
		return this;
	}

	/**
	 * メッセージ受信時 に実行されるGS2-Scriptを取得
	 *
	 * @return メッセージ受信時 に実行されるGS2-Script
	 */
	public String getReceiveMessageTriggerScript() {
		return receiveMessageTriggerScript;
	}

	/**
	 * メッセージ受信時 に実行されるGS2-Scriptを設定
	 *
	 * @param receiveMessageTriggerScript メッセージ受信時 に実行されるGS2-Script
	 */
	public void setReceiveMessageTriggerScript(String receiveMessageTriggerScript) {
		this.receiveMessageTriggerScript = receiveMessageTriggerScript;
	}

	/**
	 * メッセージ受信時 に実行されるGS2-Scriptを設定
	 *
	 * @param receiveMessageTriggerScript メッセージ受信時 に実行されるGS2-Script
	 * @return this
	 */
	public UpdateInboxRequest withReceiveMessageTriggerScript(String receiveMessageTriggerScript) {
		setReceiveMessageTriggerScript(receiveMessageTriggerScript);
		return this;
	}

	/**
	 * メッセージ受信完了時 に実行されるGS2-Scriptを取得
	 *
	 * @return メッセージ受信完了時 に実行されるGS2-Script
	 */
	public String getReceiveMessageDoneTriggerScript() {
		return receiveMessageDoneTriggerScript;
	}

	/**
	 * メッセージ受信完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param receiveMessageDoneTriggerScript メッセージ受信完了時 に実行されるGS2-Script
	 */
	public void setReceiveMessageDoneTriggerScript(String receiveMessageDoneTriggerScript) {
		this.receiveMessageDoneTriggerScript = receiveMessageDoneTriggerScript;
	}

	/**
	 * メッセージ受信完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param receiveMessageDoneTriggerScript メッセージ受信完了時 に実行されるGS2-Script
	 * @return this
	 */
	public UpdateInboxRequest withReceiveMessageDoneTriggerScript(String receiveMessageDoneTriggerScript) {
		setReceiveMessageDoneTriggerScript(receiveMessageDoneTriggerScript);
		return this;
	}

	/**
	 * メッセージ開封時 に実行されるGS2-Scriptを取得
	 *
	 * @return メッセージ開封時 に実行されるGS2-Script
	 */
	public String getReadMessageTriggerScript() {
		return readMessageTriggerScript;
	}

	/**
	 * メッセージ開封時 に実行されるGS2-Scriptを設定
	 *
	 * @param readMessageTriggerScript メッセージ開封時 に実行されるGS2-Script
	 */
	public void setReadMessageTriggerScript(String readMessageTriggerScript) {
		this.readMessageTriggerScript = readMessageTriggerScript;
	}

	/**
	 * メッセージ開封時 に実行されるGS2-Scriptを設定
	 *
	 * @param readMessageTriggerScript メッセージ開封時 に実行されるGS2-Script
	 * @return this
	 */
	public UpdateInboxRequest withReadMessageTriggerScript(String readMessageTriggerScript) {
		setReadMessageTriggerScript(readMessageTriggerScript);
		return this;
	}

	/**
	 * メッセージ開封完了時 に実行されるGS2-Scriptを取得
	 *
	 * @return メッセージ開封完了時 に実行されるGS2-Script
	 */
	public String getReadMessageDoneTriggerScript() {
		return readMessageDoneTriggerScript;
	}

	/**
	 * メッセージ開封完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param readMessageDoneTriggerScript メッセージ開封完了時 に実行されるGS2-Script
	 */
	public void setReadMessageDoneTriggerScript(String readMessageDoneTriggerScript) {
		this.readMessageDoneTriggerScript = readMessageDoneTriggerScript;
	}

	/**
	 * メッセージ開封完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param readMessageDoneTriggerScript メッセージ開封完了時 に実行されるGS2-Script
	 * @return this
	 */
	public UpdateInboxRequest withReadMessageDoneTriggerScript(String readMessageDoneTriggerScript) {
		setReadMessageDoneTriggerScript(readMessageDoneTriggerScript);
		return this;
	}

	/**
	 * メッセージ削除時 に実行されるGS2-Scriptを取得
	 *
	 * @return メッセージ削除時 に実行されるGS2-Script
	 */
	public String getDeleteMessageTriggerScript() {
		return deleteMessageTriggerScript;
	}

	/**
	 * メッセージ削除時 に実行されるGS2-Scriptを設定
	 *
	 * @param deleteMessageTriggerScript メッセージ削除時 に実行されるGS2-Script
	 */
	public void setDeleteMessageTriggerScript(String deleteMessageTriggerScript) {
		this.deleteMessageTriggerScript = deleteMessageTriggerScript;
	}

	/**
	 * メッセージ削除時 に実行されるGS2-Scriptを設定
	 *
	 * @param deleteMessageTriggerScript メッセージ削除時 に実行されるGS2-Script
	 * @return this
	 */
	public UpdateInboxRequest withDeleteMessageTriggerScript(String deleteMessageTriggerScript) {
		setDeleteMessageTriggerScript(deleteMessageTriggerScript);
		return this;
	}

	/**
	 * メッセージ削除完了時 に実行されるGS2-Scriptを取得
	 *
	 * @return メッセージ削除完了時 に実行されるGS2-Script
	 */
	public String getDeleteMessageDoneTriggerScript() {
		return deleteMessageDoneTriggerScript;
	}

	/**
	 * メッセージ削除完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param deleteMessageDoneTriggerScript メッセージ削除完了時 に実行されるGS2-Script
	 */
	public void setDeleteMessageDoneTriggerScript(String deleteMessageDoneTriggerScript) {
		this.deleteMessageDoneTriggerScript = deleteMessageDoneTriggerScript;
	}

	/**
	 * メッセージ削除完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param deleteMessageDoneTriggerScript メッセージ削除完了時 に実行されるGS2-Script
	 * @return this
	 */
	public UpdateInboxRequest withDeleteMessageDoneTriggerScript(String deleteMessageDoneTriggerScript) {
		setDeleteMessageDoneTriggerScript(deleteMessageDoneTriggerScript);
		return this;
	}

}
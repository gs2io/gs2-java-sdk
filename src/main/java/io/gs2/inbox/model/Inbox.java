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

package io.gs2.inbox.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 受信ボックス
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Inbox implements Serializable {

	/** 受信ボックスGRN */
	private String inboxId;

	/** オーナーID */
	private String ownerId;

	/** 受信ボックス名 */
	private String name;

	/** 説明文 */
	private String description;

	/** サービスクラス */
	private String serviceClass;

	/** 開封時自動削除 */
	private Boolean autoDelete;

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

	/** 作成日時(エポック秒) */
	private Integer createAt;

	/** 最終更新日時(エポック秒) */
	private Integer updateAt;


	/**
	 * 受信ボックスGRNを取得
	 *
	 * @return 受信ボックスGRN
	 */
	public String getInboxId() {
		return inboxId;
	}

	/**
	 * 受信ボックスGRNを設定
	 *
	 * @param inboxId 受信ボックスGRN
	 */
	public void setInboxId(String inboxId) {
		this.inboxId = inboxId;
	}

	/**
	 * 受信ボックスGRNを設定
	 *
	 * @param inboxId 受信ボックスGRN
	 * @return this
	 */
	public Inbox withInboxId(String inboxId) {
		this.inboxId = inboxId;
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
	public Inbox withOwnerId(String ownerId) {
		this.ownerId = ownerId;
		return this;
	}

	/**
	 * 受信ボックス名を取得
	 *
	 * @return 受信ボックス名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 受信ボックス名を設定
	 *
	 * @param name 受信ボックス名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 受信ボックス名を設定
	 *
	 * @param name 受信ボックス名
	 * @return this
	 */
	public Inbox withName(String name) {
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
	public Inbox withDescription(String description) {
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
	public Inbox withServiceClass(String serviceClass) {
		this.serviceClass = serviceClass;
		return this;
	}

	/**
	 * 開封時自動削除を取得
	 *
	 * @return 開封時自動削除
	 */
	public Boolean getAutoDelete() {
		return autoDelete;
	}

	/**
	 * 開封時自動削除を設定
	 *
	 * @param autoDelete 開封時自動削除
	 */
	public void setAutoDelete(Boolean autoDelete) {
		this.autoDelete = autoDelete;
	}

	/**
	 * 開封時自動削除を設定
	 *
	 * @param autoDelete 開封時自動削除
	 * @return this
	 */
	public Inbox withAutoDelete(Boolean autoDelete) {
		this.autoDelete = autoDelete;
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
	public Inbox withCooperationUrl(String cooperationUrl) {
		this.cooperationUrl = cooperationUrl;
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
	public Inbox withReceiveMessageTriggerScript(String receiveMessageTriggerScript) {
		this.receiveMessageTriggerScript = receiveMessageTriggerScript;
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
	public Inbox withReceiveMessageDoneTriggerScript(String receiveMessageDoneTriggerScript) {
		this.receiveMessageDoneTriggerScript = receiveMessageDoneTriggerScript;
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
	public Inbox withReadMessageTriggerScript(String readMessageTriggerScript) {
		this.readMessageTriggerScript = readMessageTriggerScript;
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
	public Inbox withReadMessageDoneTriggerScript(String readMessageDoneTriggerScript) {
		this.readMessageDoneTriggerScript = readMessageDoneTriggerScript;
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
	public Inbox withDeleteMessageTriggerScript(String deleteMessageTriggerScript) {
		this.deleteMessageTriggerScript = deleteMessageTriggerScript;
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
	public Inbox withDeleteMessageDoneTriggerScript(String deleteMessageDoneTriggerScript) {
		this.deleteMessageDoneTriggerScript = deleteMessageDoneTriggerScript;
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
	public Inbox withCreateAt(Integer createAt) {
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
	public Inbox withUpdateAt(Integer updateAt) {
		this.updateAt = updateAt;
		return this;
	}


    public ObjectNode toJson() {

		ObjectNode body = JsonNodeFactory.instance.objectNode()

            .put("inboxId", this.getInboxId())
            .put("ownerId", this.getOwnerId())
            .put("name", this.getName())
            .put("description", this.getDescription())
            .put("serviceClass", this.getServiceClass())
            .put("autoDelete", this.getAutoDelete())
            .put("cooperationUrl", this.getCooperationUrl())
            .put("receiveMessageTriggerScript", this.getReceiveMessageTriggerScript())
            .put("receiveMessageDoneTriggerScript", this.getReceiveMessageDoneTriggerScript())
            .put("readMessageTriggerScript", this.getReadMessageTriggerScript())
            .put("readMessageDoneTriggerScript", this.getReadMessageDoneTriggerScript())
            .put("deleteMessageTriggerScript", this.getDeleteMessageTriggerScript())
            .put("deleteMessageDoneTriggerScript", this.getDeleteMessageDoneTriggerScript())
            .put("createAt", this.getCreateAt())
            .put("updateAt", this.getUpdateAt());

        return body;
    }
}
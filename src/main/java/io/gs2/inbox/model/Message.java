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
 * メッセージ
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Message implements Serializable {

	/** メッセージID */
	private String messageId;

	/** 受信ボックスGRN */
	private String inboxId;

	/** 発言者ユーザID */
	private String userId;

	/** メッセージ本文 */
	private String message;

	/** 開封時に通知を出すか */
	private Boolean cooperation;

	/** 既読状態 */
	private Boolean read;

	/** 受信日時(エポック秒) */
	private Integer date;


	/**
	 * メッセージIDを取得
	 *
	 * @return メッセージID
	 */
	public String getMessageId() {
		return messageId;
	}

	/**
	 * メッセージIDを設定
	 *
	 * @param messageId メッセージID
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	/**
	 * メッセージIDを設定
	 *
	 * @param messageId メッセージID
	 * @return this
	 */
	public Message withMessageId(String messageId) {
		this.messageId = messageId;
		return this;
	}

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
	public Message withInboxId(String inboxId) {
		this.inboxId = inboxId;
		return this;
	}

	/**
	 * 発言者ユーザIDを取得
	 *
	 * @return 発言者ユーザID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 発言者ユーザIDを設定
	 *
	 * @param userId 発言者ユーザID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 発言者ユーザIDを設定
	 *
	 * @param userId 発言者ユーザID
	 * @return this
	 */
	public Message withUserId(String userId) {
		this.userId = userId;
		return this;
	}

	/**
	 * メッセージ本文を取得
	 *
	 * @return メッセージ本文
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * メッセージ本文を設定
	 *
	 * @param message メッセージ本文
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * メッセージ本文を設定
	 *
	 * @param message メッセージ本文
	 * @return this
	 */
	public Message withMessage(String message) {
		this.message = message;
		return this;
	}

	/**
	 * 開封時に通知を出すかを取得
	 *
	 * @return 開封時に通知を出すか
	 */
	public Boolean getCooperation() {
		return cooperation;
	}

	/**
	 * 開封時に通知を出すかを設定
	 *
	 * @param cooperation 開封時に通知を出すか
	 */
	public void setCooperation(Boolean cooperation) {
		this.cooperation = cooperation;
	}

	/**
	 * 開封時に通知を出すかを設定
	 *
	 * @param cooperation 開封時に通知を出すか
	 * @return this
	 */
	public Message withCooperation(Boolean cooperation) {
		this.cooperation = cooperation;
		return this;
	}

	/**
	 * 既読状態を取得
	 *
	 * @return 既読状態
	 */
	public Boolean getRead() {
		return read;
	}

	/**
	 * 既読状態を設定
	 *
	 * @param read 既読状態
	 */
	public void setRead(Boolean read) {
		this.read = read;
	}

	/**
	 * 既読状態を設定
	 *
	 * @param read 既読状態
	 * @return this
	 */
	public Message withRead(Boolean read) {
		this.read = read;
		return this;
	}

	/**
	 * 受信日時(エポック秒)を取得
	 *
	 * @return 受信日時(エポック秒)
	 */
	public Integer getDate() {
		return date;
	}

	/**
	 * 受信日時(エポック秒)を設定
	 *
	 * @param date 受信日時(エポック秒)
	 */
	public void setDate(Integer date) {
		this.date = date;
	}

	/**
	 * 受信日時(エポック秒)を設定
	 *
	 * @param date 受信日時(エポック秒)
	 * @return this
	 */
	public Message withDate(Integer date) {
		this.date = date;
		return this;
	}


    public ObjectNode toJson() {

		ObjectNode body = JsonNodeFactory.instance.objectNode()

            .put("messageId", this.getMessageId())
            .put("inboxId", this.getInboxId())
            .put("userId", this.getUserId())
            .put("message", this.getMessage())
            .put("cooperation", this.getCooperation())
            .put("read", this.getRead())
            .put("date", this.getDate());

        return body;
    }
}
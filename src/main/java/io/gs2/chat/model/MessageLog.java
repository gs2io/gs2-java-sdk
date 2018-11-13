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
 * メッセージログ
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class MessageLog implements Serializable {

	/** メッセージID */
	private String messageId;

	/** ルームID */
	private String roomId;

	/** 発言者ユーザID */
	private String userId;

	/** メッセージテキスト */
	private String message;

	/** メッセージメタデータ */
	private String meta;

	/** 作成日時(エポック秒) */
	private Integer createAt;


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
	public MessageLog withMessageId(String messageId) {
		this.messageId = messageId;
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
	public MessageLog withRoomId(String roomId) {
		this.roomId = roomId;
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
	public MessageLog withUserId(String userId) {
		this.userId = userId;
		return this;
	}

	/**
	 * メッセージテキストを取得
	 *
	 * @return メッセージテキスト
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * メッセージテキストを設定
	 *
	 * @param message メッセージテキスト
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * メッセージテキストを設定
	 *
	 * @param message メッセージテキスト
	 * @return this
	 */
	public MessageLog withMessage(String message) {
		this.message = message;
		return this;
	}

	/**
	 * メッセージメタデータを取得
	 *
	 * @return メッセージメタデータ
	 */
	public String getMeta() {
		return meta;
	}

	/**
	 * メッセージメタデータを設定
	 *
	 * @param meta メッセージメタデータ
	 */
	public void setMeta(String meta) {
		this.meta = meta;
	}

	/**
	 * メッセージメタデータを設定
	 *
	 * @param meta メッセージメタデータ
	 * @return this
	 */
	public MessageLog withMeta(String meta) {
		this.meta = meta;
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
	public MessageLog withCreateAt(Integer createAt) {
		this.createAt = createAt;
		return this;
	}


    public ObjectNode toJson() {

		ObjectNode body = JsonNodeFactory.instance.objectNode()

            .put("messageId", this.getMessageId())
            .put("roomId", this.getRoomId())
            .put("userId", this.getUserId())
            .put("message", this.getMessage())
            .put("meta", this.getMeta())
            .put("createAt", this.getCreateAt());

        return body;
    }
}
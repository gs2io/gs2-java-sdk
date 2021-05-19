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
import io.gs2.core.model.IModel;

/**
 * メッセージ
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Message implements IModel, Serializable, Comparable<Message> {
	/** メッセージ */
	protected String messageId;

	/**
	 * メッセージを取得
	 *
	 * @return メッセージ
	 */
	public String getMessageId() {
		return messageId;
	}

	/**
	 * メッセージを設定
	 *
	 * @param messageId メッセージ
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	/**
	 * メッセージを設定
	 *
	 * @param messageId メッセージ
	 * @return this
	 */
	public Message withMessageId(String messageId) {
		this.messageId = messageId;
		return this;
	}
	/** ルーム名 */
	protected String roomName;

	/**
	 * ルーム名を取得
	 *
	 * @return ルーム名
	 */
	public String getRoomName() {
		return roomName;
	}

	/**
	 * ルーム名を設定
	 *
	 * @param roomName ルーム名
	 */
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	/**
	 * ルーム名を設定
	 *
	 * @param roomName ルーム名
	 * @return this
	 */
	public Message withRoomName(String roomName) {
		this.roomName = roomName;
		return this;
	}
	/** メッセージ名 */
	protected String name;

	/**
	 * メッセージ名を取得
	 *
	 * @return メッセージ名
	 */
	public String getName() {
		return name;
	}

	/**
	 * メッセージ名を設定
	 *
	 * @param name メッセージ名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * メッセージ名を設定
	 *
	 * @param name メッセージ名
	 * @return this
	 */
	public Message withName(String name) {
		this.name = name;
		return this;
	}
	/** 発言したユーザID */
	protected String userId;

	/**
	 * 発言したユーザIDを取得
	 *
	 * @return 発言したユーザID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 発言したユーザIDを設定
	 *
	 * @param userId 発言したユーザID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 発言したユーザIDを設定
	 *
	 * @param userId 発言したユーザID
	 * @return this
	 */
	public Message withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	/** メッセージの種類を分類したい時の種類番号 */
	protected Integer category;

	/**
	 * メッセージの種類を分類したい時の種類番号を取得
	 *
	 * @return メッセージの種類を分類したい時の種類番号
	 */
	public Integer getCategory() {
		return category;
	}

	/**
	 * メッセージの種類を分類したい時の種類番号を設定
	 *
	 * @param category メッセージの種類を分類したい時の種類番号
	 */
	public void setCategory(Integer category) {
		this.category = category;
	}

	/**
	 * メッセージの種類を分類したい時の種類番号を設定
	 *
	 * @param category メッセージの種類を分類したい時の種類番号
	 * @return this
	 */
	public Message withCategory(Integer category) {
		this.category = category;
		return this;
	}
	/** メタデータ */
	protected String metadata;

	/**
	 * メタデータを取得
	 *
	 * @return メタデータ
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * メタデータを設定
	 *
	 * @param metadata メタデータ
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	/**
	 * メタデータを設定
	 *
	 * @param metadata メタデータ
	 * @return this
	 */
	public Message withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	/** 作成日時 */
	protected Long createdAt;

	/**
	 * 作成日時を取得
	 *
	 * @return 作成日時
	 */
	public Long getCreatedAt() {
		return createdAt;
	}

	/**
	 * 作成日時を設定
	 *
	 * @param createdAt 作成日時
	 */
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * 作成日時を設定
	 *
	 * @param createdAt 作成日時
	 * @return this
	 */
	public Message withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("messageId", this.getMessageId())
            .put("roomName", this.getRoomName())
            .put("name", this.getName())
            .put("userId", this.getUserId())
            .put("category", this.getCategory())
            .put("metadata", this.getMetadata())
            .put("createdAt", this.getCreatedAt());
        return body_;
    }
	@Override
	public int compareTo(Message o) {
		return messageId.compareTo(o.messageId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.messageId == null) ? 0 : this.messageId.hashCode());
        result = prime * result + ((this.roomName == null) ? 0 : this.roomName.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.category == null) ? 0 : this.category.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		Message other = (Message) o;
		if (messageId == null) {
			return other.messageId == null;
		} else if (!messageId.equals(other.messageId)) {
			return false;
		}
		if (roomName == null) {
			return other.roomName == null;
		} else if (!roomName.equals(other.roomName)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (category == null) {
			return other.category == null;
		} else if (!category.equals(other.category)) {
			return false;
		}
		if (metadata == null) {
			return other.metadata == null;
		} else if (!metadata.equals(other.metadata)) {
			return false;
		}
		if (createdAt == null) {
			return other.createdAt == null;
		} else if (!createdAt.equals(other.createdAt)) {
			return false;
		}
		return true;
	}
}
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
import io.gs2.model.IModel;

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
	/** メッセージID */
	protected String name;

	/**
	 * メッセージIDを取得
	 *
	 * @return メッセージID
	 */
	public String getName() {
		return name;
	}

	/**
	 * メッセージIDを設定
	 *
	 * @param name メッセージID
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * メッセージIDを設定
	 *
	 * @param name メッセージID
	 * @return this
	 */
	public Message withName(String name) {
		this.name = name;
		return this;
	}
	/** ユーザーID */
	protected String userId;

	/**
	 * ユーザーIDを取得
	 *
	 * @return ユーザーID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * ユーザーIDを設定
	 *
	 * @param userId ユーザーID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * ユーザーIDを設定
	 *
	 * @param userId ユーザーID
	 * @return this
	 */
	public Message withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	/** メッセージの内容に相当するメタデータ */
	protected String metadata;

	/**
	 * メッセージの内容に相当するメタデータを取得
	 *
	 * @return メッセージの内容に相当するメタデータ
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * メッセージの内容に相当するメタデータを設定
	 *
	 * @param metadata メッセージの内容に相当するメタデータ
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	/**
	 * メッセージの内容に相当するメタデータを設定
	 *
	 * @param metadata メッセージの内容に相当するメタデータ
	 * @return this
	 */
	public Message withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	/** 既読状態 */
	protected Boolean isRead;

	/**
	 * 既読状態を取得
	 *
	 * @return 既読状態
	 */
	public Boolean getIsRead() {
		return isRead;
	}

	/**
	 * 既読状態を設定
	 *
	 * @param isRead 既読状態
	 */
	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	/**
	 * 既読状態を設定
	 *
	 * @param isRead 既読状態
	 * @return this
	 */
	public Message withIsRead(Boolean isRead) {
		this.isRead = isRead;
		return this;
	}
	/** 開封時に実行する入手アクション */
	protected List<AcquireAction> readAcquireActions;

	/**
	 * 開封時に実行する入手アクションを取得
	 *
	 * @return 開封時に実行する入手アクション
	 */
	public List<AcquireAction> getReadAcquireActions() {
		return readAcquireActions;
	}

	/**
	 * 開封時に実行する入手アクションを設定
	 *
	 * @param readAcquireActions 開封時に実行する入手アクション
	 */
	public void setReadAcquireActions(List<AcquireAction> readAcquireActions) {
		this.readAcquireActions = readAcquireActions;
	}

	/**
	 * 開封時に実行する入手アクションを設定
	 *
	 * @param readAcquireActions 開封時に実行する入手アクション
	 * @return this
	 */
	public Message withReadAcquireActions(List<AcquireAction> readAcquireActions) {
		this.readAcquireActions = readAcquireActions;
		return this;
	}
	/** 作成日時 */
	protected Long receivedAt;

	/**
	 * 作成日時を取得
	 *
	 * @return 作成日時
	 */
	public Long getReceivedAt() {
		return receivedAt;
	}

	/**
	 * 作成日時を設定
	 *
	 * @param receivedAt 作成日時
	 */
	public void setReceivedAt(Long receivedAt) {
		this.receivedAt = receivedAt;
	}

	/**
	 * 作成日時を設定
	 *
	 * @param receivedAt 作成日時
	 * @return this
	 */
	public Message withReceivedAt(Long receivedAt) {
		this.receivedAt = receivedAt;
		return this;
	}
	/** 最終更新日時 */
	protected Long readAt;

	/**
	 * 最終更新日時を取得
	 *
	 * @return 最終更新日時
	 */
	public Long getReadAt() {
		return readAt;
	}

	/**
	 * 最終更新日時を設定
	 *
	 * @param readAt 最終更新日時
	 */
	public void setReadAt(Long readAt) {
		this.readAt = readAt;
	}

	/**
	 * 最終更新日時を設定
	 *
	 * @param readAt 最終更新日時
	 * @return this
	 */
	public Message withReadAt(Long readAt) {
		this.readAt = readAt;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> readAcquireActions = new ArrayList<>();
        if(this.readAcquireActions != null) {
            for(AcquireAction item : this.readAcquireActions) {
                readAcquireActions.add(item.toJson());
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("messageId", this.getMessageId())
            .put("name", this.getName())
            .put("userId", this.getUserId())
            .put("metadata", this.getMetadata())
            .put("isRead", this.getIsRead())
            .put("receivedAt", this.getReceivedAt())
            .put("readAt", this.getReadAt());
        body_.set("readAcquireActions", JsonNodeFactory.instance.arrayNode().addAll(readAcquireActions));
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
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.isRead == null) ? 0 : this.isRead.hashCode());
        result = prime * result + ((this.readAcquireActions == null) ? 0 : this.readAcquireActions.hashCode());
        result = prime * result + ((this.receivedAt == null) ? 0 : this.receivedAt.hashCode());
        result = prime * result + ((this.readAt == null) ? 0 : this.readAt.hashCode());
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
		if (metadata == null) {
			return other.metadata == null;
		} else if (!metadata.equals(other.metadata)) {
			return false;
		}
		if (isRead == null) {
			return other.isRead == null;
		} else if (!isRead.equals(other.isRead)) {
			return false;
		}
		if (readAcquireActions == null) {
			return other.readAcquireActions == null;
		} else if (!readAcquireActions.equals(other.readAcquireActions)) {
			return false;
		}
		if (receivedAt == null) {
			return other.receivedAt == null;
		} else if (!receivedAt.equals(other.receivedAt)) {
			return false;
		}
		if (readAt == null) {
			return other.readAt == null;
		} else if (!readAt.equals(other.readAt)) {
			return false;
		}
		return true;
	}
}
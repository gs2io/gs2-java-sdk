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
 * ルーム
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Room implements IModel, Serializable, Comparable<Room> {
	/** ルーム */
	protected String roomId;

	/**
	 * ルームを取得
	 *
	 * @return ルーム
	 */
	public String getRoomId() {
		return roomId;
	}

	/**
	 * ルームを設定
	 *
	 * @param roomId ルーム
	 */
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	/**
	 * ルームを設定
	 *
	 * @param roomId ルーム
	 * @return this
	 */
	public Room withRoomId(String roomId) {
		this.roomId = roomId;
		return this;
	}
	/** ルーム名 */
	protected String name;

	/**
	 * ルーム名を取得
	 *
	 * @return ルーム名
	 */
	public String getName() {
		return name;
	}

	/**
	 * ルーム名を設定
	 *
	 * @param name ルーム名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ルーム名を設定
	 *
	 * @param name ルーム名
	 * @return this
	 */
	public Room withName(String name) {
		this.name = name;
		return this;
	}
	/** ルームを作成したユーザID */
	protected String userId;

	/**
	 * ルームを作成したユーザIDを取得
	 *
	 * @return ルームを作成したユーザID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * ルームを作成したユーザIDを設定
	 *
	 * @param userId ルームを作成したユーザID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * ルームを作成したユーザIDを設定
	 *
	 * @param userId ルームを作成したユーザID
	 * @return this
	 */
	public Room withUserId(String userId) {
		this.userId = userId;
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
	public Room withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	/** メッセージを投稿するために必要となるパスワード */
	protected String password;

	/**
	 * メッセージを投稿するために必要となるパスワードを取得
	 *
	 * @return メッセージを投稿するために必要となるパスワード
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * メッセージを投稿するために必要となるパスワードを設定
	 *
	 * @param password メッセージを投稿するために必要となるパスワード
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * メッセージを投稿するために必要となるパスワードを設定
	 *
	 * @param password メッセージを投稿するために必要となるパスワード
	 * @return this
	 */
	public Room withPassword(String password) {
		this.password = password;
		return this;
	}
	/** ルームに参加可能なユーザIDリスト */
	protected List<String> whiteListUserIds;

	/**
	 * ルームに参加可能なユーザIDリストを取得
	 *
	 * @return ルームに参加可能なユーザIDリスト
	 */
	public List<String> getWhiteListUserIds() {
		return whiteListUserIds;
	}

	/**
	 * ルームに参加可能なユーザIDリストを設定
	 *
	 * @param whiteListUserIds ルームに参加可能なユーザIDリスト
	 */
	public void setWhiteListUserIds(List<String> whiteListUserIds) {
		this.whiteListUserIds = whiteListUserIds;
	}

	/**
	 * ルームに参加可能なユーザIDリストを設定
	 *
	 * @param whiteListUserIds ルームに参加可能なユーザIDリスト
	 * @return this
	 */
	public Room withWhiteListUserIds(List<String> whiteListUserIds) {
		this.whiteListUserIds = whiteListUserIds;
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
	public Room withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	/** 最終更新日時 */
	protected Long updatedAt;

	/**
	 * 最終更新日時を取得
	 *
	 * @return 最終更新日時
	 */
	public Long getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * 最終更新日時を設定
	 *
	 * @param updatedAt 最終更新日時
	 */
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * 最終更新日時を設定
	 *
	 * @param updatedAt 最終更新日時
	 * @return this
	 */
	public Room withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> whiteListUserIds = new ArrayList<>();
        if(this.whiteListUserIds != null) {
            for(String item : this.whiteListUserIds) {
                whiteListUserIds.add(JsonNodeFactory.instance.textNode(item));
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("roomId", this.getRoomId())
            .put("name", this.getName())
            .put("userId", this.getUserId())
            .put("metadata", this.getMetadata())
            .put("password", this.getPassword())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        body_.set("whiteListUserIds", JsonNodeFactory.instance.arrayNode().addAll(whiteListUserIds));
        return body_;
    }
	@Override
	public int compareTo(Room o) {
		return roomId.compareTo(o.roomId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.roomId == null) ? 0 : this.roomId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.password == null) ? 0 : this.password.hashCode());
        result = prime * result + ((this.whiteListUserIds == null) ? 0 : this.whiteListUserIds.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
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
		Room other = (Room) o;
		if (roomId == null) {
			return other.roomId == null;
		} else if (!roomId.equals(other.roomId)) {
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
		if (password == null) {
			return other.password == null;
		} else if (!password.equals(other.password)) {
			return false;
		}
		if (whiteListUserIds == null) {
			return other.whiteListUserIds == null;
		} else if (!whiteListUserIds.equals(other.whiteListUserIds)) {
			return false;
		}
		if (createdAt == null) {
			return other.createdAt == null;
		} else if (!createdAt.equals(other.createdAt)) {
			return false;
		}
		if (updatedAt == null) {
			return other.updatedAt == null;
		} else if (!updatedAt.equals(other.updatedAt)) {
			return false;
		}
		return true;
	}
}
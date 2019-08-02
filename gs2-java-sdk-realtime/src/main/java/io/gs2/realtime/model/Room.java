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

package io.gs2.realtime.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * ルーム
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Room implements Serializable, Comparable<Room> {
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
	/** IPアドレス */
	protected String ipAddress;

	/**
	 * IPアドレスを取得
	 *
	 * @return IPアドレス
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * IPアドレスを設定
	 *
	 * @param ipAddress IPアドレス
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * IPアドレスを設定
	 *
	 * @param ipAddress IPアドレス
	 * @return this
	 */
	public Room withIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
		return this;
	}
	/** 待受ポート */
	protected Integer port;

	/**
	 * 待受ポートを取得
	 *
	 * @return 待受ポート
	 */
	public Integer getPort() {
		return port;
	}

	/**
	 * 待受ポートを設定
	 *
	 * @param port 待受ポート
	 */
	public void setPort(Integer port) {
		this.port = port;
	}

	/**
	 * 待受ポートを設定
	 *
	 * @param port 待受ポート
	 * @return this
	 */
	public Room withPort(Integer port) {
		this.port = port;
		return this;
	}
	/** 暗号鍵 */
	protected String encryptionKey;

	/**
	 * 暗号鍵を取得
	 *
	 * @return 暗号鍵
	 */
	public String getEncryptionKey() {
		return encryptionKey;
	}

	/**
	 * 暗号鍵を設定
	 *
	 * @param encryptionKey 暗号鍵
	 */
	public void setEncryptionKey(String encryptionKey) {
		this.encryptionKey = encryptionKey;
	}

	/**
	 * 暗号鍵を設定
	 *
	 * @param encryptionKey 暗号鍵
	 * @return this
	 */
	public Room withEncryptionKey(String encryptionKey) {
		this.encryptionKey = encryptionKey;
		return this;
	}
	/** ルームの作成が終わったときに通知を受けるユーザIDリスト */
	protected List<String> notificationUserIds;

	/**
	 * ルームの作成が終わったときに通知を受けるユーザIDリストを取得
	 *
	 * @return ルームの作成が終わったときに通知を受けるユーザIDリスト
	 */
	public List<String> getNotificationUserIds() {
		return notificationUserIds;
	}

	/**
	 * ルームの作成が終わったときに通知を受けるユーザIDリストを設定
	 *
	 * @param notificationUserIds ルームの作成が終わったときに通知を受けるユーザIDリスト
	 */
	public void setNotificationUserIds(List<String> notificationUserIds) {
		this.notificationUserIds = notificationUserIds;
	}

	/**
	 * ルームの作成が終わったときに通知を受けるユーザIDリストを設定
	 *
	 * @param notificationUserIds ルームの作成が終わったときに通知を受けるユーザIDリスト
	 * @return this
	 */
	public Room withNotificationUserIds(List<String> notificationUserIds) {
		this.notificationUserIds = notificationUserIds;
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
        List<JsonNode> notificationUserIds = new ArrayList<>();
        if(this.notificationUserIds != null) {
            for(String item : this.notificationUserIds) {
                notificationUserIds.add(JsonNodeFactory.instance.textNode(item));
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("roomId", this.getRoomId())
            .put("name", this.getName())
            .put("ipAddress", this.getIpAddress())
            .put("port", this.getPort())
            .put("encryptionKey", this.getEncryptionKey())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        body_.set("notificationUserIds", JsonNodeFactory.instance.arrayNode().addAll(notificationUserIds));
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
        result = prime * result + ((this.ipAddress == null) ? 0 : this.ipAddress.hashCode());
        result = prime * result + ((this.port == null) ? 0 : this.port.hashCode());
        result = prime * result + ((this.encryptionKey == null) ? 0 : this.encryptionKey.hashCode());
        result = prime * result + ((this.notificationUserIds == null) ? 0 : this.notificationUserIds.hashCode());
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
		if (ipAddress == null) {
			return other.ipAddress == null;
		} else if (!ipAddress.equals(other.ipAddress)) {
			return false;
		}
		if (port == null) {
			return other.port == null;
		} else if (!port.equals(other.port)) {
			return false;
		}
		if (encryptionKey == null) {
			return other.encryptionKey == null;
		} else if (!encryptionKey.equals(other.encryptionKey)) {
			return false;
		}
		if (notificationUserIds == null) {
			return other.notificationUserIds == null;
		} else if (!notificationUserIds.equals(other.notificationUserIds)) {
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
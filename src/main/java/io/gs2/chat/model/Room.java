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
 * ルーム
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Room implements Serializable {

	/** ルームID */
	private String roomId;

	/** 参加可能なユーザIDリスト */
	private List<String> allowUserIds;

	/** メッセージの送受信にパスワードが必要か */
	private Boolean needPassword;

	/** 作成日時(エポック秒) */
	private Integer createAt;


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
	public Room withRoomId(String roomId) {
		this.roomId = roomId;
		return this;
	}

	/**
	 * 参加可能なユーザIDリストを取得
	 *
	 * @return 参加可能なユーザIDリスト
	 */
	public List<String> getAllowUserIds() {
		return allowUserIds;
	}

	/**
	 * 参加可能なユーザIDリストを設定
	 *
	 * @param allowUserIds 参加可能なユーザIDリスト
	 */
	public void setAllowUserIds(List<String> allowUserIds) {
		this.allowUserIds = allowUserIds;
	}

	/**
	 * 参加可能なユーザIDリストを設定
	 *
	 * @param allowUserIds 参加可能なユーザIDリスト
	 * @return this
	 */
	public Room withAllowUserIds(List<String> allowUserIds) {
		this.allowUserIds = allowUserIds;
		return this;
	}

	/**
	 * メッセージの送受信にパスワードが必要かを取得
	 *
	 * @return メッセージの送受信にパスワードが必要か
	 */
	public Boolean getNeedPassword() {
		return needPassword;
	}

	/**
	 * メッセージの送受信にパスワードが必要かを設定
	 *
	 * @param needPassword メッセージの送受信にパスワードが必要か
	 */
	public void setNeedPassword(Boolean needPassword) {
		this.needPassword = needPassword;
	}

	/**
	 * メッセージの送受信にパスワードが必要かを設定
	 *
	 * @param needPassword メッセージの送受信にパスワードが必要か
	 * @return this
	 */
	public Room withNeedPassword(Boolean needPassword) {
		this.needPassword = needPassword;
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
	public Room withCreateAt(Integer createAt) {
		this.createAt = createAt;
		return this;
	}


    public ObjectNode toJson() {

        List<JsonNode> allowUserIdsNode = new ArrayList<>();
        if(this.allowUserIds != null) {
            for(String item : this.allowUserIds) {
                allowUserIdsNode.add(JsonNodeFactory.instance.textNode(item));
            }
        }
		ObjectNode body = JsonNodeFactory.instance.objectNode()

            .put("roomId", this.getRoomId())
            .put("needPassword", this.getNeedPassword())
            .put("createAt", this.getCreateAt());

        body.set("allowUserIds", JsonNodeFactory.instance.arrayNode().addAll(allowUserIdsNode));
        return body;
    }
}
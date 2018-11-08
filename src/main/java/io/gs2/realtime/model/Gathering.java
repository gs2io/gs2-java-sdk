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
 * ギャザリング
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Gathering implements Serializable {

	/** ギャザリングID */
	private String gatheringId;

	/** ギャザリングプールGRN */
	private String gatheringPoolId;

	/** オーナーID */
	private String ownerId;

	/** ギャザリング名 */
	private String name;

	/** ホストIPアドレス */
	private String ipAddress;

	/** ホストポート */
	private Integer port;

	/** 暗号鍵 */
	private String secret;

	/** 参加可能なユーザIDリスト */
	private List<String> userIds;

	/** 作成日時(エポック秒) */
	private Integer createAt;

	/** 最終更新日時(エポック秒) */
	private Integer updateAt;


	/**
	 * ギャザリングIDを取得
	 *
	 * @return ギャザリングID
	 */
	public String getGatheringId() {
		return gatheringId;
	}

	/**
	 * ギャザリングIDを設定
	 *
	 * @param gatheringId ギャザリングID
	 */
	public void setGatheringId(String gatheringId) {
		this.gatheringId = gatheringId;
	}

	/**
	 * ギャザリングIDを設定
	 *
	 * @param gatheringId ギャザリングID
	 * @return this
	 */
	public Gathering withGatheringId(String gatheringId) {
		this.gatheringId = gatheringId;
		return this;
	}

	/**
	 * ギャザリングプールGRNを取得
	 *
	 * @return ギャザリングプールGRN
	 */
	public String getGatheringPoolId() {
		return gatheringPoolId;
	}

	/**
	 * ギャザリングプールGRNを設定
	 *
	 * @param gatheringPoolId ギャザリングプールGRN
	 */
	public void setGatheringPoolId(String gatheringPoolId) {
		this.gatheringPoolId = gatheringPoolId;
	}

	/**
	 * ギャザリングプールGRNを設定
	 *
	 * @param gatheringPoolId ギャザリングプールGRN
	 * @return this
	 */
	public Gathering withGatheringPoolId(String gatheringPoolId) {
		this.gatheringPoolId = gatheringPoolId;
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
	public Gathering withOwnerId(String ownerId) {
		this.ownerId = ownerId;
		return this;
	}

	/**
	 * ギャザリング名を取得
	 *
	 * @return ギャザリング名
	 */
	public String getName() {
		return name;
	}

	/**
	 * ギャザリング名を設定
	 *
	 * @param name ギャザリング名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ギャザリング名を設定
	 *
	 * @param name ギャザリング名
	 * @return this
	 */
	public Gathering withName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * ホストIPアドレスを取得
	 *
	 * @return ホストIPアドレス
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * ホストIPアドレスを設定
	 *
	 * @param ipAddress ホストIPアドレス
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * ホストIPアドレスを設定
	 *
	 * @param ipAddress ホストIPアドレス
	 * @return this
	 */
	public Gathering withIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
		return this;
	}

	/**
	 * ホストポートを取得
	 *
	 * @return ホストポート
	 */
	public Integer getPort() {
		return port;
	}

	/**
	 * ホストポートを設定
	 *
	 * @param port ホストポート
	 */
	public void setPort(Integer port) {
		this.port = port;
	}

	/**
	 * ホストポートを設定
	 *
	 * @param port ホストポート
	 * @return this
	 */
	public Gathering withPort(Integer port) {
		this.port = port;
		return this;
	}

	/**
	 * 暗号鍵を取得
	 *
	 * @return 暗号鍵
	 */
	public String getSecret() {
		return secret;
	}

	/**
	 * 暗号鍵を設定
	 *
	 * @param secret 暗号鍵
	 */
	public void setSecret(String secret) {
		this.secret = secret;
	}

	/**
	 * 暗号鍵を設定
	 *
	 * @param secret 暗号鍵
	 * @return this
	 */
	public Gathering withSecret(String secret) {
		this.secret = secret;
		return this;
	}

	/**
	 * 参加可能なユーザIDリストを取得
	 *
	 * @return 参加可能なユーザIDリスト
	 */
	public List<String> getUserIds() {
		return userIds;
	}

	/**
	 * 参加可能なユーザIDリストを設定
	 *
	 * @param userIds 参加可能なユーザIDリスト
	 */
	public void setUserIds(List<String> userIds) {
		this.userIds = userIds;
	}

	/**
	 * 参加可能なユーザIDリストを設定
	 *
	 * @param userIds 参加可能なユーザIDリスト
	 * @return this
	 */
	public Gathering withUserIds(List<String> userIds) {
		this.userIds = userIds;
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
	public Gathering withCreateAt(Integer createAt) {
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
	public Gathering withUpdateAt(Integer updateAt) {
		this.updateAt = updateAt;
		return this;
	}


    public ObjectNode toJson() {

        List<JsonNode> userIdsNode = new ArrayList<>();
        if(this.userIds != null) {
            for(String item : this.userIds) {
                userIdsNode.add(JsonNodeFactory.instance.textNode(item));
            }
        }
		ObjectNode body = JsonNodeFactory.instance.objectNode()

            .put("gatheringId", this.getGatheringId())
            .put("gatheringPoolId", this.getGatheringPoolId())
            .put("ownerId", this.getOwnerId())
            .put("name", this.getName())
            .put("ipAddress", this.getIpAddress())
            .put("port", this.getPort())
            .put("secret", this.getSecret())
            .put("createAt", this.getCreateAt())
            .put("updateAt", this.getUpdateAt());

        body.set("userIds", JsonNodeFactory.instance.arrayNode().addAll(userIdsNode));
        return body;
    }
}
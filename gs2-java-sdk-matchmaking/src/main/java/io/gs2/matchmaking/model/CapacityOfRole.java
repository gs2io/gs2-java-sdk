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

package io.gs2.matchmaking.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.model.IModel;

/**
 * ロールごとのキャパシティ
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CapacityOfRole implements IModel, Serializable {
	/** ロール名 */
	protected String roleName;

	/**
	 * ロール名を取得
	 *
	 * @return ロール名
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * ロール名を設定
	 *
	 * @param roleName ロール名
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * ロール名を設定
	 *
	 * @param roleName ロール名
	 * @return this
	 */
	public CapacityOfRole withRoleName(String roleName) {
		this.roleName = roleName;
		return this;
	}
	/** ロール名の別名リスト */
	protected List<String> roleAliases;

	/**
	 * ロール名の別名リストを取得
	 *
	 * @return ロール名の別名リスト
	 */
	public List<String> getRoleAliases() {
		return roleAliases;
	}

	/**
	 * ロール名の別名リストを設定
	 *
	 * @param roleAliases ロール名の別名リスト
	 */
	public void setRoleAliases(List<String> roleAliases) {
		this.roleAliases = roleAliases;
	}

	/**
	 * ロール名の別名リストを設定
	 *
	 * @param roleAliases ロール名の別名リスト
	 * @return this
	 */
	public CapacityOfRole withRoleAliases(List<String> roleAliases) {
		this.roleAliases = roleAliases;
		return this;
	}
	/** 募集人数 */
	protected Integer capacity;

	/**
	 * 募集人数を取得
	 *
	 * @return 募集人数
	 */
	public Integer getCapacity() {
		return capacity;
	}

	/**
	 * 募集人数を設定
	 *
	 * @param capacity 募集人数
	 */
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	/**
	 * 募集人数を設定
	 *
	 * @param capacity 募集人数
	 * @return this
	 */
	public CapacityOfRole withCapacity(Integer capacity) {
		this.capacity = capacity;
		return this;
	}
	/** 参加者のプレイヤー情報リスト */
	protected List<Player> participants;

	/**
	 * 参加者のプレイヤー情報リストを取得
	 *
	 * @return 参加者のプレイヤー情報リスト
	 */
	public List<Player> getParticipants() {
		return participants;
	}

	/**
	 * 参加者のプレイヤー情報リストを設定
	 *
	 * @param participants 参加者のプレイヤー情報リスト
	 */
	public void setParticipants(List<Player> participants) {
		this.participants = participants;
	}

	/**
	 * 参加者のプレイヤー情報リストを設定
	 *
	 * @param participants 参加者のプレイヤー情報リスト
	 * @return this
	 */
	public CapacityOfRole withParticipants(List<Player> participants) {
		this.participants = participants;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> roleAliases = new ArrayList<>();
        if(this.roleAliases != null) {
            for(String item : this.roleAliases) {
                roleAliases.add(JsonNodeFactory.instance.textNode(item));
            }
        }
        List<JsonNode> participants = new ArrayList<>();
        if(this.participants != null) {
            for(Player item : this.participants) {
                participants.add(item.toJson());
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("roleName", this.getRoleName())
            .put("capacity", this.getCapacity());
        body_.set("roleAliases", JsonNodeFactory.instance.arrayNode().addAll(roleAliases));
        body_.set("participants", JsonNodeFactory.instance.arrayNode().addAll(participants));
        return body_;
    }
}
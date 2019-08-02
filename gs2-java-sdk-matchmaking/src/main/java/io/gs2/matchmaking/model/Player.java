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
 * プレイヤー情報
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Player implements IModel, Serializable {
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
	public Player withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	/** 属性値のリスト */
	protected List<Attribute> attributes;

	/**
	 * 属性値のリストを取得
	 *
	 * @return 属性値のリスト
	 */
	public List<Attribute> getAttributes() {
		return attributes;
	}

	/**
	 * 属性値のリストを設定
	 *
	 * @param attributes 属性値のリスト
	 */
	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	/**
	 * 属性値のリストを設定
	 *
	 * @param attributes 属性値のリスト
	 * @return this
	 */
	public Player withAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
		return this;
	}
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
	public Player withRoleName(String roleName) {
		this.roleName = roleName;
		return this;
	}
	/** 参加を拒否するユーザIDリスト */
	protected List<String> denyUserIds;

	/**
	 * 参加を拒否するユーザIDリストを取得
	 *
	 * @return 参加を拒否するユーザIDリスト
	 */
	public List<String> getDenyUserIds() {
		return denyUserIds;
	}

	/**
	 * 参加を拒否するユーザIDリストを設定
	 *
	 * @param denyUserIds 参加を拒否するユーザIDリスト
	 */
	public void setDenyUserIds(List<String> denyUserIds) {
		this.denyUserIds = denyUserIds;
	}

	/**
	 * 参加を拒否するユーザIDリストを設定
	 *
	 * @param denyUserIds 参加を拒否するユーザIDリスト
	 * @return this
	 */
	public Player withDenyUserIds(List<String> denyUserIds) {
		this.denyUserIds = denyUserIds;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> attributes = new ArrayList<>();
        if(this.attributes != null) {
            for(Attribute item : this.attributes) {
                attributes.add(item.toJson());
            }
        }
        List<JsonNode> denyUserIds = new ArrayList<>();
        if(this.denyUserIds != null) {
            for(String item : this.denyUserIds) {
                denyUserIds.add(JsonNodeFactory.instance.textNode(item));
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("userId", this.getUserId())
            .put("roleName", this.getRoleName());
        body_.set("attributes", JsonNodeFactory.instance.arrayNode().addAll(attributes));
        body_.set("denyUserIds", JsonNodeFactory.instance.arrayNode().addAll(denyUserIds));
        return body_;
    }
}
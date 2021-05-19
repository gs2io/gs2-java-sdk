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

package io.gs2.quest.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * クリア報酬
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Reward implements IModel, Serializable {
	/** スタンプシートで実行するアクションの種類 */
	protected String action;

	/**
	 * スタンプシートで実行するアクションの種類を取得
	 *
	 * @return スタンプシートで実行するアクションの種類
	 */
	public String getAction() {
		return action;
	}

	/**
	 * スタンプシートで実行するアクションの種類を設定
	 *
	 * @param action スタンプシートで実行するアクションの種類
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * スタンプシートで実行するアクションの種類を設定
	 *
	 * @param action スタンプシートで実行するアクションの種類
	 * @return this
	 */
	public Reward withAction(String action) {
		this.action = action;
		return this;
	}
	/** リクエストモデル */
	protected String request;

	/**
	 * リクエストモデルを取得
	 *
	 * @return リクエストモデル
	 */
	public String getRequest() {
		return request;
	}

	/**
	 * リクエストモデルを設定
	 *
	 * @param request リクエストモデル
	 */
	public void setRequest(String request) {
		this.request = request;
	}

	/**
	 * リクエストモデルを設定
	 *
	 * @param request リクエストモデル
	 * @return this
	 */
	public Reward withRequest(String request) {
		this.request = request;
		return this;
	}
	/** 入手するリソースGRN */
	protected String itemId;

	/**
	 * 入手するリソースGRNを取得
	 *
	 * @return 入手するリソースGRN
	 */
	public String getItemId() {
		return itemId;
	}

	/**
	 * 入手するリソースGRNを設定
	 *
	 * @param itemId 入手するリソースGRN
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	/**
	 * 入手するリソースGRNを設定
	 *
	 * @param itemId 入手するリソースGRN
	 * @return this
	 */
	public Reward withItemId(String itemId) {
		this.itemId = itemId;
		return this;
	}
	/** 入手する数量 */
	protected Integer value;

	/**
	 * 入手する数量を取得
	 *
	 * @return 入手する数量
	 */
	public Integer getValue() {
		return value;
	}

	/**
	 * 入手する数量を設定
	 *
	 * @param value 入手する数量
	 */
	public void setValue(Integer value) {
		this.value = value;
	}

	/**
	 * 入手する数量を設定
	 *
	 * @param value 入手する数量
	 * @return this
	 */
	public Reward withValue(Integer value) {
		this.value = value;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("action", this.getAction())
            .put("request", this.getRequest())
            .put("itemId", this.getItemId())
            .put("value", this.getValue());
        return body_;
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.action == null) ? 0 : this.action.hashCode());
        result = prime * result + ((this.request == null) ? 0 : this.request.hashCode());
        result = prime * result + ((this.itemId == null) ? 0 : this.itemId.hashCode());
        result = prime * result + ((this.value == null) ? 0 : this.value.hashCode());
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
		Reward other = (Reward) o;
		if (action == null) {
			return other.action == null;
		} else if (!action.equals(other.action)) {
			return false;
		}
		if (request == null) {
			return other.request == null;
		} else if (!request.equals(other.request)) {
			return false;
		}
		if (itemId == null) {
			return other.itemId == null;
		} else if (!itemId.equals(other.itemId)) {
			return false;
		}
		if (value == null) {
			return other.value == null;
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}
}
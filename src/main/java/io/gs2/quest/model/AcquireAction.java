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
 * 入手アクション
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class AcquireAction implements IModel, Serializable {
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
	public AcquireAction withAction(String action) {
		this.action = action;
		return this;
	}
	/** 入手リクエストのJSON */
	protected String request;

	/**
	 * 入手リクエストのJSONを取得
	 *
	 * @return 入手リクエストのJSON
	 */
	public String getRequest() {
		return request;
	}

	/**
	 * 入手リクエストのJSONを設定
	 *
	 * @param request 入手リクエストのJSON
	 */
	public void setRequest(String request) {
		this.request = request;
	}

	/**
	 * 入手リクエストのJSONを設定
	 *
	 * @param request 入手リクエストのJSON
	 * @return this
	 */
	public AcquireAction withRequest(String request) {
		this.request = request;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("action", this.getAction())
            .put("request", this.getRequest());
        return body_;
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.action == null) ? 0 : this.action.hashCode());
        result = prime * result + ((this.request == null) ? 0 : this.request.hashCode());
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
		AcquireAction other = (AcquireAction) o;
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
		return true;
	}
}
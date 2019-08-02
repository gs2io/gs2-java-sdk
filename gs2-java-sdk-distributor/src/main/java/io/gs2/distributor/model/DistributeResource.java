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

package io.gs2.distributor.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.model.IModel;

/**
 * 加算するリソース
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class DistributeResource implements IModel, Serializable {
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
	public DistributeResource withAction(String action) {
		this.action = action;
		return this;
	}
	/** 加算リクエストのJSON */
	protected String request;

	/**
	 * 加算リクエストのJSONを取得
	 *
	 * @return 加算リクエストのJSON
	 */
	public String getRequest() {
		return request;
	}

	/**
	 * 加算リクエストのJSONを設定
	 *
	 * @param request 加算リクエストのJSON
	 */
	public void setRequest(String request) {
		this.request = request;
	}

	/**
	 * 加算リクエストのJSONを設定
	 *
	 * @param request 加算リクエストのJSON
	 * @return this
	 */
	public DistributeResource withRequest(String request) {
		this.request = request;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("action", this.getAction())
            .put("request", this.getRequest());
        return body_;
    }
}
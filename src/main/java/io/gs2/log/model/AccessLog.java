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

package io.gs2.log.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * アクセスログ
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class AccessLog implements IModel, Serializable {
	/** 日時 */
	protected Long timestamp;

	/**
	 * 日時を取得
	 *
	 * @return 日時
	 */
	public Long getTimestamp() {
		return timestamp;
	}

	/**
	 * 日時を設定
	 *
	 * @param timestamp 日時
	 */
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * 日時を設定
	 *
	 * @param timestamp 日時
	 * @return this
	 */
	public AccessLog withTimestamp(Long timestamp) {
		this.timestamp = timestamp;
		return this;
	}
	/** リクエストID */
	protected String requestId;

	/**
	 * リクエストIDを取得
	 *
	 * @return リクエストID
	 */
	public String getRequestId() {
		return requestId;
	}

	/**
	 * リクエストIDを設定
	 *
	 * @param requestId リクエストID
	 */
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	/**
	 * リクエストIDを設定
	 *
	 * @param requestId リクエストID
	 * @return this
	 */
	public AccessLog withRequestId(String requestId) {
		this.requestId = requestId;
		return this;
	}
	/** マイクロサービスの種類 */
	protected String service;

	/**
	 * マイクロサービスの種類を取得
	 *
	 * @return マイクロサービスの種類
	 */
	public String getService() {
		return service;
	}

	/**
	 * マイクロサービスの種類を設定
	 *
	 * @param service マイクロサービスの種類
	 */
	public void setService(String service) {
		this.service = service;
	}

	/**
	 * マイクロサービスの種類を設定
	 *
	 * @param service マイクロサービスの種類
	 * @return this
	 */
	public AccessLog withService(String service) {
		this.service = service;
		return this;
	}
	/** マイクロサービスのメソッド */
	protected String method;

	/**
	 * マイクロサービスのメソッドを取得
	 *
	 * @return マイクロサービスのメソッド
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * マイクロサービスのメソッドを設定
	 *
	 * @param method マイクロサービスのメソッド
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * マイクロサービスのメソッドを設定
	 *
	 * @param method マイクロサービスのメソッド
	 * @return this
	 */
	public AccessLog withMethod(String method) {
		this.method = method;
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
	public AccessLog withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	/** リクエストパラメータ */
	protected String request;

	/**
	 * リクエストパラメータを取得
	 *
	 * @return リクエストパラメータ
	 */
	public String getRequest() {
		return request;
	}

	/**
	 * リクエストパラメータを設定
	 *
	 * @param request リクエストパラメータ
	 */
	public void setRequest(String request) {
		this.request = request;
	}

	/**
	 * リクエストパラメータを設定
	 *
	 * @param request リクエストパラメータ
	 * @return this
	 */
	public AccessLog withRequest(String request) {
		this.request = request;
		return this;
	}
	/** 応答内容 */
	protected String result;

	/**
	 * 応答内容を取得
	 *
	 * @return 応答内容
	 */
	public String getResult() {
		return result;
	}

	/**
	 * 応答内容を設定
	 *
	 * @param result 応答内容
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * 応答内容を設定
	 *
	 * @param result 応答内容
	 * @return this
	 */
	public AccessLog withResult(String result) {
		this.result = result;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("timestamp", this.getTimestamp())
            .put("requestId", this.getRequestId())
            .put("service", this.getService())
            .put("method", this.getMethod())
            .put("userId", this.getUserId())
            .put("request", this.getRequest())
            .put("result", this.getResult());
        return body_;
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.timestamp == null) ? 0 : this.timestamp.hashCode());
        result = prime * result + ((this.requestId == null) ? 0 : this.requestId.hashCode());
        result = prime * result + ((this.service == null) ? 0 : this.service.hashCode());
        result = prime * result + ((this.method == null) ? 0 : this.method.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.request == null) ? 0 : this.request.hashCode());
        result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
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
		AccessLog other = (AccessLog) o;
		if (timestamp == null) {
			return other.timestamp == null;
		} else if (!timestamp.equals(other.timestamp)) {
			return false;
		}
		if (requestId == null) {
			return other.requestId == null;
		} else if (!requestId.equals(other.requestId)) {
			return false;
		}
		if (service == null) {
			return other.service == null;
		} else if (!service.equals(other.service)) {
			return false;
		}
		if (method == null) {
			return other.method == null;
		} else if (!method.equals(other.method)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (request == null) {
			return other.request == null;
		} else if (!request.equals(other.request)) {
			return false;
		}
		if (result == null) {
			return other.result == null;
		} else if (!result.equals(other.result)) {
			return false;
		}
		return true;
	}
}
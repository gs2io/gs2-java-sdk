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
 * アクセスログ集計
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class AccessLogCount implements IModel, Serializable {
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
	public AccessLogCount withService(String service) {
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
	public AccessLogCount withMethod(String method) {
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
	public AccessLogCount withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	/** 回数 */
	protected Long count;

	/**
	 * 回数を取得
	 *
	 * @return 回数
	 */
	public Long getCount() {
		return count;
	}

	/**
	 * 回数を設定
	 *
	 * @param count 回数
	 */
	public void setCount(Long count) {
		this.count = count;
	}

	/**
	 * 回数を設定
	 *
	 * @param count 回数
	 * @return this
	 */
	public AccessLogCount withCount(Long count) {
		this.count = count;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("service", this.getService())
            .put("method", this.getMethod())
            .put("userId", this.getUserId())
            .put("count", this.getCount());
        return body_;
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.service == null) ? 0 : this.service.hashCode());
        result = prime * result + ((this.method == null) ? 0 : this.method.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.count == null) ? 0 : this.count.hashCode());
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
		AccessLogCount other = (AccessLogCount) o;
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
		if (count == null) {
			return other.count == null;
		} else if (!count.equals(other.count)) {
			return false;
		}
		return true;
	}
}
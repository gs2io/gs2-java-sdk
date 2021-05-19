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

package io.gs2.gateway.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * WebSocketセッション
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Session implements IModel, Serializable, Comparable<Session> {
	/** WebSocketセッション のGRN */
	protected String sessionId;

	/**
	 * WebSocketセッション のGRNを取得
	 *
	 * @return WebSocketセッション のGRN
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * WebSocketセッション のGRNを設定
	 *
	 * @param sessionId WebSocketセッション のGRN
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * WebSocketセッション のGRNを設定
	 *
	 * @param sessionId WebSocketセッション のGRN
	 * @return this
	 */
	public Session withSessionId(String sessionId) {
		this.sessionId = sessionId;
		return this;
	}
	/** オーナーID */
	protected String ownerId;

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
	public Session withOwnerId(String ownerId) {
		this.ownerId = ownerId;
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
	public Session withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	/** WebSocketセッション名 */
	protected String sessionName;

	/**
	 * WebSocketセッション名を取得
	 *
	 * @return WebSocketセッション名
	 */
	public String getSessionName() {
		return sessionName;
	}

	/**
	 * WebSocketセッション名を設定
	 *
	 * @param sessionName WebSocketセッション名
	 */
	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}

	/**
	 * WebSocketセッション名を設定
	 *
	 * @param sessionName WebSocketセッション名
	 * @return this
	 */
	public Session withSessionName(String sessionName) {
		this.sessionName = sessionName;
		return this;
	}
	/** API Gateway の APIID */
	protected String apiId;

	/**
	 * API Gateway の APIIDを取得
	 *
	 * @return API Gateway の APIID
	 */
	public String getApiId() {
		return apiId;
	}

	/**
	 * API Gateway の APIIDを設定
	 *
	 * @param apiId API Gateway の APIID
	 */
	public void setApiId(String apiId) {
		this.apiId = apiId;
	}

	/**
	 * API Gateway の APIIDを設定
	 *
	 * @param apiId API Gateway の APIID
	 * @return this
	 */
	public Session withApiId(String apiId) {
		this.apiId = apiId;
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
	public Session withCreatedAt(Long createdAt) {
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
	public Session withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("sessionId", this.getSessionId())
            .put("ownerId", this.getOwnerId())
            .put("userId", this.getUserId())
            .put("sessionName", this.getSessionName())
            .put("apiId", this.getApiId())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        return body_;
    }
	@Override
	public int compareTo(Session o) {
		return sessionId.compareTo(o.sessionId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.sessionId == null) ? 0 : this.sessionId.hashCode());
        result = prime * result + ((this.ownerId == null) ? 0 : this.ownerId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.sessionName == null) ? 0 : this.sessionName.hashCode());
        result = prime * result + ((this.apiId == null) ? 0 : this.apiId.hashCode());
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
		Session other = (Session) o;
		if (sessionId == null) {
			return other.sessionId == null;
		} else if (!sessionId.equals(other.sessionId)) {
			return false;
		}
		if (ownerId == null) {
			return other.ownerId == null;
		} else if (!ownerId.equals(other.ownerId)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (sessionName == null) {
			return other.sessionName == null;
		} else if (!sessionName.equals(other.sessionName)) {
			return false;
		}
		if (apiId == null) {
			return other.apiId == null;
		} else if (!apiId.equals(other.apiId)) {
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
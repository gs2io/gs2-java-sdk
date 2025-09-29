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

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.IModel;


@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class WebSocketSession implements IModel, Serializable, Comparable<WebSocketSession> {
	private String webSocketSessionId;
	private String connectionId;
	private String namespaceName;
	private String userId;
	private String sessionId;
	private Long createdAt;
	private Long updatedAt;
	private Long revision;
	public String getWebSocketSessionId() {
		return webSocketSessionId;
	}
	public void setWebSocketSessionId(String webSocketSessionId) {
		this.webSocketSessionId = webSocketSessionId;
	}
	public WebSocketSession withWebSocketSessionId(String webSocketSessionId) {
		this.webSocketSessionId = webSocketSessionId;
		return this;
	}
	public String getConnectionId() {
		return connectionId;
	}
	public void setConnectionId(String connectionId) {
		this.connectionId = connectionId;
	}
	public WebSocketSession withConnectionId(String connectionId) {
		this.connectionId = connectionId;
		return this;
	}
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public WebSocketSession withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public WebSocketSession withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public WebSocketSession withSessionId(String sessionId) {
		this.sessionId = sessionId;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public WebSocketSession withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public WebSocketSession withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public WebSocketSession withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static WebSocketSession fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new WebSocketSession()
            .withWebSocketSessionId(data.get("webSocketSessionId") == null || data.get("webSocketSessionId").isNull() ? null : data.get("webSocketSessionId").asText())
            .withConnectionId(data.get("connectionId") == null || data.get("connectionId").isNull() ? null : data.get("connectionId").asText())
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withSessionId(data.get("sessionId") == null || data.get("sessionId").isNull() ? null : data.get("sessionId").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("webSocketSessionId", getWebSocketSessionId());
                put("connectionId", getConnectionId());
                put("namespaceName", getNamespaceName());
                put("userId", getUserId());
                put("sessionId", getSessionId());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(WebSocketSession o) {
		return webSocketSessionId.compareTo(o.webSocketSessionId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.webSocketSessionId == null) ? 0 : this.webSocketSessionId.hashCode());
        result = prime * result + ((this.connectionId == null) ? 0 : this.connectionId.hashCode());
        result = prime * result + ((this.namespaceName == null) ? 0 : this.namespaceName.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.sessionId == null) ? 0 : this.sessionId.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
        result = prime * result + ((this.revision == null) ? 0 : this.revision.hashCode());
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
		WebSocketSession other = (WebSocketSession) o;
		if (webSocketSessionId == null) {
			return other.webSocketSessionId == null;
		} else if (!webSocketSessionId.equals(other.webSocketSessionId)) {
			return false;
		}
		if (connectionId == null) {
			return other.connectionId == null;
		} else if (!connectionId.equals(other.connectionId)) {
			return false;
		}
		if (namespaceName == null) {
			return other.namespaceName == null;
		} else if (!namespaceName.equals(other.namespaceName)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (sessionId == null) {
			return other.sessionId == null;
		} else if (!sessionId.equals(other.sessionId)) {
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
		if (revision == null) {
			return other.revision == null;
		} else if (!revision.equals(other.revision)) {
			return false;
		}
		return true;
	}
}
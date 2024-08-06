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
public class InGameLog implements IModel, Serializable, Comparable<InGameLog> {
	private Long timestamp;
	private String requestId;
	private String userId;
	private List<InGameLogTag> tags;
	private String payload;
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public InGameLog withTimestamp(Long timestamp) {
		this.timestamp = timestamp;
		return this;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public InGameLog withRequestId(String requestId) {
		this.requestId = requestId;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public InGameLog withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public List<InGameLogTag> getTags() {
		return tags;
	}
	public void setTags(List<InGameLogTag> tags) {
		this.tags = tags;
	}
	public InGameLog withTags(List<InGameLogTag> tags) {
		this.tags = tags;
		return this;
	}
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	public InGameLog withPayload(String payload) {
		this.payload = payload;
		return this;
	}

    public static InGameLog fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new InGameLog()
            .withTimestamp(data.get("timestamp") == null || data.get("timestamp").isNull() ? null : data.get("timestamp").longValue())
            .withRequestId(data.get("requestId") == null || data.get("requestId").isNull() ? null : data.get("requestId").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withTags(data.get("tags") == null || data.get("tags").isNull() ? new ArrayList<InGameLogTag>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("tags").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return InGameLogTag.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withPayload(data.get("payload") == null || data.get("payload").isNull() ? null : data.get("payload").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("timestamp", getTimestamp());
                put("requestId", getRequestId());
                put("userId", getUserId());
                put("tags", getTags() == null ? new ArrayList<InGameLogTag>() :
                    getTags().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("payload", getPayload());
            }}
        );
    }

	@Override
	public int compareTo(InGameLog o) {
		return requestId.compareTo(o.requestId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.timestamp == null) ? 0 : this.timestamp.hashCode());
        result = prime * result + ((this.requestId == null) ? 0 : this.requestId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.tags == null) ? 0 : this.tags.hashCode());
        result = prime * result + ((this.payload == null) ? 0 : this.payload.hashCode());
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
		InGameLog other = (InGameLog) o;
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
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (tags == null) {
			return other.tags == null;
		} else if (!tags.equals(other.tags)) {
			return false;
		}
		if (payload == null) {
			return other.payload == null;
		} else if (!payload.equals(other.payload)) {
			return false;
		}
		return true;
	}
}
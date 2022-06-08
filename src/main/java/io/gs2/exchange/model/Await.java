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

package io.gs2.exchange.model;

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
public class Await implements IModel, Serializable, Comparable<Await> {
	private String awaitId;
	private String userId;
	private String rateName;
	private String name;
	private Integer count;
	private Long exchangedAt;
	public String getAwaitId() {
		return awaitId;
	}
	public void setAwaitId(String awaitId) {
		this.awaitId = awaitId;
	}
	public Await withAwaitId(String awaitId) {
		this.awaitId = awaitId;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Await withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getRateName() {
		return rateName;
	}
	public void setRateName(String rateName) {
		this.rateName = rateName;
	}
	public Await withRateName(String rateName) {
		this.rateName = rateName;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Await withName(String name) {
		this.name = name;
		return this;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Await withCount(Integer count) {
		this.count = count;
		return this;
	}
	public Long getExchangedAt() {
		return exchangedAt;
	}
	public void setExchangedAt(Long exchangedAt) {
		this.exchangedAt = exchangedAt;
	}
	public Await withExchangedAt(Long exchangedAt) {
		this.exchangedAt = exchangedAt;
		return this;
	}

    public static Await fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Await()
            .withAwaitId(data.get("awaitId") == null || data.get("awaitId").isNull() ? null : data.get("awaitId").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withRateName(data.get("rateName") == null || data.get("rateName").isNull() ? null : data.get("rateName").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withCount(data.get("count") == null || data.get("count").isNull() ? null : data.get("count").intValue())
            .withExchangedAt(data.get("exchangedAt") == null || data.get("exchangedAt").isNull() ? null : data.get("exchangedAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("awaitId", getAwaitId());
                put("userId", getUserId());
                put("rateName", getRateName());
                put("name", getName());
                put("count", getCount());
                put("exchangedAt", getExchangedAt());
            }}
        );
    }

	@Override
	public int compareTo(Await o) {
		return awaitId.compareTo(o.awaitId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.awaitId == null) ? 0 : this.awaitId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.rateName == null) ? 0 : this.rateName.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.count == null) ? 0 : this.count.hashCode());
        result = prime * result + ((this.exchangedAt == null) ? 0 : this.exchangedAt.hashCode());
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
		Await other = (Await) o;
		if (awaitId == null) {
			return other.awaitId == null;
		} else if (!awaitId.equals(other.awaitId)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (rateName == null) {
			return other.rateName == null;
		} else if (!rateName.equals(other.rateName)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (count == null) {
			return other.count == null;
		} else if (!count.equals(other.count)) {
			return false;
		}
		if (exchangedAt == null) {
			return other.exchangedAt == null;
		} else if (!exchangedAt.equals(other.exchangedAt)) {
			return false;
		}
		return true;
	}
}
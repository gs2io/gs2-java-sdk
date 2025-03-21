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
	private Integer skipSeconds;
	private List<Config> config;
	private Long acquirableAt;
	private Long exchangedAt;
	private Long createdAt;
	private Long revision;
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
	public Integer getSkipSeconds() {
		return skipSeconds;
	}
	public void setSkipSeconds(Integer skipSeconds) {
		this.skipSeconds = skipSeconds;
	}
	public Await withSkipSeconds(Integer skipSeconds) {
		this.skipSeconds = skipSeconds;
		return this;
	}
	public List<Config> getConfig() {
		return config;
	}
	public void setConfig(List<Config> config) {
		this.config = config;
	}
	public Await withConfig(List<Config> config) {
		this.config = config;
		return this;
	}
	public Long getAcquirableAt() {
		return acquirableAt;
	}
	public void setAcquirableAt(Long acquirableAt) {
		this.acquirableAt = acquirableAt;
	}
	public Await withAcquirableAt(Long acquirableAt) {
		this.acquirableAt = acquirableAt;
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
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public Await withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public Await withRevision(Long revision) {
		this.revision = revision;
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
            .withSkipSeconds(data.get("skipSeconds") == null || data.get("skipSeconds").isNull() ? null : data.get("skipSeconds").intValue())
            .withConfig(data.get("config") == null || data.get("config").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("config").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return Config.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withAcquirableAt(data.get("acquirableAt") == null || data.get("acquirableAt").isNull() ? null : data.get("acquirableAt").longValue())
            .withExchangedAt(data.get("exchangedAt") == null || data.get("exchangedAt").isNull() ? null : data.get("exchangedAt").longValue())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("awaitId", getAwaitId());
                put("userId", getUserId());
                put("rateName", getRateName());
                put("name", getName());
                put("count", getCount());
                put("skipSeconds", getSkipSeconds());
                put("config", getConfig() == null ? null :
                    getConfig().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("acquirableAt", getAcquirableAt());
                put("exchangedAt", getExchangedAt());
                put("createdAt", getCreatedAt());
                put("revision", getRevision());
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
        result = prime * result + ((this.skipSeconds == null) ? 0 : this.skipSeconds.hashCode());
        result = prime * result + ((this.config == null) ? 0 : this.config.hashCode());
        result = prime * result + ((this.acquirableAt == null) ? 0 : this.acquirableAt.hashCode());
        result = prime * result + ((this.exchangedAt == null) ? 0 : this.exchangedAt.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
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
		if (skipSeconds == null) {
			return other.skipSeconds == null;
		} else if (!skipSeconds.equals(other.skipSeconds)) {
			return false;
		}
		if (config == null) {
			return other.config == null;
		} else if (!config.equals(other.config)) {
			return false;
		}
		if (acquirableAt == null) {
			return other.acquirableAt == null;
		} else if (!acquirableAt.equals(other.acquirableAt)) {
			return false;
		}
		if (exchangedAt == null) {
			return other.exchangedAt == null;
		} else if (!exchangedAt.equals(other.exchangedAt)) {
			return false;
		}
		if (createdAt == null) {
			return other.createdAt == null;
		} else if (!createdAt.equals(other.createdAt)) {
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
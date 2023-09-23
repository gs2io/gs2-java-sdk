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

package io.gs2.account.model;

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
public class BanStatus implements IModel, Serializable {
	private String name;
	private String reason;
	private Long releaseTimestamp;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BanStatus withName(String name) {
		this.name = name;
		return this;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public BanStatus withReason(String reason) {
		this.reason = reason;
		return this;
	}
	public Long getReleaseTimestamp() {
		return releaseTimestamp;
	}
	public void setReleaseTimestamp(Long releaseTimestamp) {
		this.releaseTimestamp = releaseTimestamp;
	}
	public BanStatus withReleaseTimestamp(Long releaseTimestamp) {
		this.releaseTimestamp = releaseTimestamp;
		return this;
	}

    public static BanStatus fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new BanStatus()
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withReason(data.get("reason") == null || data.get("reason").isNull() ? null : data.get("reason").asText())
            .withReleaseTimestamp(data.get("releaseTimestamp") == null || data.get("releaseTimestamp").isNull() ? null : data.get("releaseTimestamp").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("name", getName());
                put("reason", getReason());
                put("releaseTimestamp", getReleaseTimestamp());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.reason == null) ? 0 : this.reason.hashCode());
        result = prime * result + ((this.releaseTimestamp == null) ? 0 : this.releaseTimestamp.hashCode());
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
		BanStatus other = (BanStatus) o;
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (reason == null) {
			return other.reason == null;
		} else if (!reason.equals(other.reason)) {
			return false;
		}
		if (releaseTimestamp == null) {
			return other.releaseTimestamp == null;
		} else if (!releaseTimestamp.equals(other.releaseTimestamp)) {
			return false;
		}
		return true;
	}
}
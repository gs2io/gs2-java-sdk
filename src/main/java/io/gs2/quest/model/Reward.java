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
public class Reward implements IModel, Serializable {
	private String action;
	private String request;
	private String itemId;
	private Integer value;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Reward withAction(String action) {
		this.action = action;
		return this;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public Reward withRequest(String request) {
		this.request = request;
		return this;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public Reward withItemId(String itemId) {
		this.itemId = itemId;
		return this;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Reward withValue(Integer value) {
		this.value = value;
		return this;
	}

    public static Reward fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Reward()
            .withAction(data.get("action") == null || data.get("action").isNull() ? null : data.get("action").asText())
            .withRequest(data.get("request") == null || data.get("request").isNull() ? null : data.get("request").asText())
            .withItemId(data.get("itemId") == null || data.get("itemId").isNull() ? null : data.get("itemId").asText())
            .withValue(data.get("value") == null || data.get("value").isNull() ? null : data.get("value").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("action", getAction());
                put("request", getRequest());
                put("itemId", getItemId());
                put("value", getValue());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.action == null) ? 0 : this.action.hashCode());
        result = prime * result + ((this.request == null) ? 0 : this.request.hashCode());
        result = prime * result + ((this.itemId == null) ? 0 : this.itemId.hashCode());
        result = prime * result + ((this.value == null) ? 0 : this.value.hashCode());
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
		Reward other = (Reward) o;
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
		if (itemId == null) {
			return other.itemId == null;
		} else if (!itemId.equals(other.itemId)) {
			return false;
		}
		if (value == null) {
			return other.value == null;
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}
}
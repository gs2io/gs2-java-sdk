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

package io.gs2.grade.model;

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
public class AcquireAction implements IModel, Serializable {
	private String action;
	private String request;
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public AcquireAction withAction(String action) {
		this.action = action;
		return this;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public AcquireAction withRequest(String request) {
		this.request = request;
		return this;
	}

    public static AcquireAction fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new AcquireAction()
            .withAction(data.get("action") == null || data.get("action").isNull() ? null : data.get("action").asText())
            .withRequest(data.get("request") == null || data.get("request").isNull() ? null : data.get("request").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("action", getAction());
                put("request", getRequest());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.action == null) ? 0 : this.action.hashCode());
        result = prime * result + ((this.request == null) ? 0 : this.request.hashCode());
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
		AcquireAction other = (AcquireAction) o;
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
		return true;
	}
}
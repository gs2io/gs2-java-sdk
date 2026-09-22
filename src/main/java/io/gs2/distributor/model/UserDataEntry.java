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

package io.gs2.distributor.model;

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
public class UserDataEntry implements IModel, Serializable {
	private String service;
	private String namespaceName;
	private String kind;
	private String payload;
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public UserDataEntry withService(String service) {
		this.service = service;
		return this;
	}
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public UserDataEntry withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public UserDataEntry withKind(String kind) {
		this.kind = kind;
		return this;
	}
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	public UserDataEntry withPayload(String payload) {
		this.payload = payload;
		return this;
	}

    public static UserDataEntry fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UserDataEntry()
            .withService(data.get("service") == null || data.get("service").isNull() ? null : data.get("service").asText())
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withKind(data.get("kind") == null || data.get("kind").isNull() ? null : data.get("kind").asText())
            .withPayload(data.get("payload") == null || data.get("payload").isNull() ? null : data.get("payload").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("service", getService());
                put("namespaceName", getNamespaceName());
                put("kind", getKind());
                put("payload", getPayload());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.service == null) ? 0 : this.service.hashCode());
        result = prime * result + ((this.namespaceName == null) ? 0 : this.namespaceName.hashCode());
        result = prime * result + ((this.kind == null) ? 0 : this.kind.hashCode());
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
		UserDataEntry other = (UserDataEntry) o;
		if (service == null) {
			return other.service == null;
		} else if (!service.equals(other.service)) {
			return false;
		}
		if (namespaceName == null) {
			return other.namespaceName == null;
		} else if (!namespaceName.equals(other.namespaceName)) {
			return false;
		}
		if (kind == null) {
			return other.kind == null;
		} else if (!kind.equals(other.kind)) {
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
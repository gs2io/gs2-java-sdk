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

package io.gs2.freeze.model;

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
public class Microservice implements IModel, Serializable {
	private String name;
	private String version;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Microservice withName(String name) {
		this.name = name;
		return this;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Microservice withVersion(String version) {
		this.version = version;
		return this;
	}

    public static Microservice fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Microservice()
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withVersion(data.get("version") == null || data.get("version").isNull() ? null : data.get("version").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("name", getName());
                put("version", getVersion());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.version == null) ? 0 : this.version.hashCode());
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
		Microservice other = (Microservice) o;
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (version == null) {
			return other.version == null;
		} else if (!version.equals(other.version)) {
			return false;
		}
		return true;
	}
}
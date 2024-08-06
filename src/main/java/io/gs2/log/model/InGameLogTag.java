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
public class InGameLogTag implements IModel, Serializable {
	private String key;
	private String value;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public InGameLogTag withKey(String key) {
		this.key = key;
		return this;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public InGameLogTag withValue(String value) {
		this.value = value;
		return this;
	}

    public static InGameLogTag fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new InGameLogTag()
            .withKey(data.get("key") == null || data.get("key").isNull() ? null : data.get("key").asText())
            .withValue(data.get("value") == null || data.get("value").isNull() ? null : data.get("value").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("key", getKey());
                put("value", getValue());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.key == null) ? 0 : this.key.hashCode());
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
		InGameLogTag other = (InGameLogTag) o;
		if (key == null) {
			return other.key == null;
		} else if (!key.equals(other.key)) {
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
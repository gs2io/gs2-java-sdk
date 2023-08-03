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

package io.gs2.ranking.model;

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
public class Scope implements IModel, Serializable {
	private String name;
	private Long targetDays;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Scope withName(String name) {
		this.name = name;
		return this;
	}
	public Long getTargetDays() {
		return targetDays;
	}
	public void setTargetDays(Long targetDays) {
		this.targetDays = targetDays;
	}
	public Scope withTargetDays(Long targetDays) {
		this.targetDays = targetDays;
		return this;
	}

    public static Scope fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Scope()
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withTargetDays(data.get("targetDays") == null || data.get("targetDays").isNull() ? null : data.get("targetDays").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("name", getName());
                put("targetDays", getTargetDays());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.targetDays == null) ? 0 : this.targetDays.hashCode());
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
		Scope other = (Scope) o;
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (targetDays == null) {
			return other.targetDays == null;
		} else if (!targetDays.equals(other.targetDays)) {
			return false;
		}
		return true;
	}
}
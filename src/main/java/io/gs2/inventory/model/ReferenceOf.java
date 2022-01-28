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

package io.gs2.inventory.model;

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
public class ReferenceOf implements IModel, Serializable, Comparable<ReferenceOf> {
	private String referenceOfId;
	private String name;

	public String getReferenceOfId() {
		return referenceOfId;
	}

	public void setReferenceOfId(String referenceOfId) {
		this.referenceOfId = referenceOfId;
	}

	public ReferenceOf withReferenceOfId(String referenceOfId) {
		this.referenceOfId = referenceOfId;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ReferenceOf withName(String name) {
		this.name = name;
		return this;
	}

    public static ReferenceOf fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new ReferenceOf()
            .withReferenceOfId(data.get("referenceOfId") == null || data.get("referenceOfId").isNull() ? null : data.get("referenceOfId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("referenceOfId", getReferenceOfId());
                put("name", getName());
            }}
        );
    }

	@Override
	public int compareTo(ReferenceOf o) {
		return referenceOfId.compareTo(o.referenceOfId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.referenceOfId == null) ? 0 : this.referenceOfId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
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
		ReferenceOf other = (ReferenceOf) o;
		if (referenceOfId == null) {
			return other.referenceOfId == null;
		} else if (!referenceOfId.equals(other.referenceOfId)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}
}
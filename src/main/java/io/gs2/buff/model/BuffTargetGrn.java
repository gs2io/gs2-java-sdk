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

package io.gs2.buff.model;

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
public class BuffTargetGrn implements IModel, Serializable {
	private String targetModelName;
	private String targetGrn;
	public String getTargetModelName() {
		return targetModelName;
	}
	public void setTargetModelName(String targetModelName) {
		this.targetModelName = targetModelName;
	}
	public BuffTargetGrn withTargetModelName(String targetModelName) {
		this.targetModelName = targetModelName;
		return this;
	}
	public String getTargetGrn() {
		return targetGrn;
	}
	public void setTargetGrn(String targetGrn) {
		this.targetGrn = targetGrn;
	}
	public BuffTargetGrn withTargetGrn(String targetGrn) {
		this.targetGrn = targetGrn;
		return this;
	}

    public static BuffTargetGrn fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new BuffTargetGrn()
            .withTargetModelName(data.get("targetModelName") == null || data.get("targetModelName").isNull() ? null : data.get("targetModelName").asText())
            .withTargetGrn(data.get("targetGrn") == null || data.get("targetGrn").isNull() ? null : data.get("targetGrn").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("targetModelName", getTargetModelName());
                put("targetGrn", getTargetGrn());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.targetModelName == null) ? 0 : this.targetModelName.hashCode());
        result = prime * result + ((this.targetGrn == null) ? 0 : this.targetGrn.hashCode());
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
		BuffTargetGrn other = (BuffTargetGrn) o;
		if (targetModelName == null) {
			return other.targetModelName == null;
		} else if (!targetModelName.equals(other.targetModelName)) {
			return false;
		}
		if (targetGrn == null) {
			return other.targetGrn == null;
		} else if (!targetGrn.equals(other.targetGrn)) {
			return false;
		}
		return true;
	}
}
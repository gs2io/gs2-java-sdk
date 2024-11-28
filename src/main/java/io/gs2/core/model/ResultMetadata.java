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

package io.gs2.core.model;

import java.util.*;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;


@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class ResultMetadata implements IModel, Serializable {
	private String uncommitted;
	public String getUncommitted() {
		return uncommitted;
	}
	public void setUncommitted(String uncommitted) {
		this.uncommitted = uncommitted;
	}
	public ResultMetadata withUncommitted(String uncommitted) {
		this.uncommitted = uncommitted;
		return this;
	}

    public static ResultMetadata fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new ResultMetadata()
            .withUncommitted(data.get("uncommitted") == null || data.get("uncommitted").isNull() ? null : data.get("uncommitted").toString());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("uncommitted", getUncommitted() != null ? getUncommitted() : null);
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.uncommitted == null) ? 0 : this.uncommitted.hashCode());
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
		ResultMetadata other = (ResultMetadata) o;
		if (uncommitted == null) {
			return other.uncommitted == null;
		} else if (!uncommitted.equals(other.uncommitted)) {
			return false;
		}
		return true;
	}
}
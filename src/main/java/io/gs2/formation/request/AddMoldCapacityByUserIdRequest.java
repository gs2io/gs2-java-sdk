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

package io.gs2.formation.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class AddMoldCapacityByUserIdRequest extends Gs2BasicRequest<AddMoldCapacityByUserIdRequest> {
    private String namespaceName;
    private String userId;
    private String moldName;
    private Integer capacity;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public AddMoldCapacityByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public AddMoldCapacityByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getMoldName() {
		return moldName;
	}
	public void setMoldName(String moldName) {
		this.moldName = moldName;
	}
	public AddMoldCapacityByUserIdRequest withMoldName(String moldName) {
		this.moldName = moldName;
		return this;
	}
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	public AddMoldCapacityByUserIdRequest withCapacity(Integer capacity) {
		this.capacity = capacity;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public AddMoldCapacityByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static AddMoldCapacityByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new AddMoldCapacityByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withMoldName(data.get("moldName") == null || data.get("moldName").isNull() ? null : data.get("moldName").asText())
            .withCapacity(data.get("capacity") == null || data.get("capacity").isNull() ? null : data.get("capacity").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("userId", getUserId());
                put("moldName", getMoldName());
                put("capacity", getCapacity());
            }}
        );
    }
}
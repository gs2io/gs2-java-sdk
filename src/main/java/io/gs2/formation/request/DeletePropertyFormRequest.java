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
public class DeletePropertyFormRequest extends Gs2BasicRequest<DeletePropertyFormRequest> {
    private String namespaceName;
    private String accessToken;
    private String propertyFormModelName;
    private String propertyId;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public DeletePropertyFormRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public DeletePropertyFormRequest withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}
	public String getPropertyFormModelName() {
		return propertyFormModelName;
	}
	public void setPropertyFormModelName(String propertyFormModelName) {
		this.propertyFormModelName = propertyFormModelName;
	}
	public DeletePropertyFormRequest withPropertyFormModelName(String propertyFormModelName) {
		this.propertyFormModelName = propertyFormModelName;
		return this;
	}
	public String getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	public DeletePropertyFormRequest withPropertyId(String propertyId) {
		this.propertyId = propertyId;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public DeletePropertyFormRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static DeletePropertyFormRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new DeletePropertyFormRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withAccessToken(data.get("accessToken") == null || data.get("accessToken").isNull() ? null : data.get("accessToken").asText())
            .withPropertyFormModelName(data.get("propertyFormModelName") == null || data.get("propertyFormModelName").isNull() ? null : data.get("propertyFormModelName").asText())
            .withPropertyId(data.get("propertyId") == null || data.get("propertyId").isNull() ? null : data.get("propertyId").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("accessToken", getAccessToken());
                put("propertyFormModelName", getPropertyFormModelName());
                put("propertyId", getPropertyId());
            }}
        );
    }
}
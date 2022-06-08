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

package io.gs2.datastore.request;

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
public class RestoreDataObjectRequest extends Gs2BasicRequest<RestoreDataObjectRequest> {
    private String namespaceName;
    private String dataObjectId;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public RestoreDataObjectRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getDataObjectId() {
		return dataObjectId;
	}
	public void setDataObjectId(String dataObjectId) {
		this.dataObjectId = dataObjectId;
	}
	public RestoreDataObjectRequest withDataObjectId(String dataObjectId) {
		this.dataObjectId = dataObjectId;
		return this;
	}

    public static RestoreDataObjectRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new RestoreDataObjectRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withDataObjectId(data.get("dataObjectId") == null || data.get("dataObjectId").isNull() ? null : data.get("dataObjectId").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("dataObjectId", getDataObjectId());
            }}
        );
    }
}
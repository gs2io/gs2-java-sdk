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

package io.gs2.skillTree.request;

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
public class GetStatusModelMasterRequest extends Gs2BasicRequest<GetStatusModelMasterRequest> {
    private String namespaceName;
    private String nodeModelName;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public GetStatusModelMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getNodeModelName() {
		return nodeModelName;
	}
	public void setNodeModelName(String nodeModelName) {
		this.nodeModelName = nodeModelName;
	}
	public GetStatusModelMasterRequest withNodeModelName(String nodeModelName) {
		this.nodeModelName = nodeModelName;
		return this;
	}

    public static GetStatusModelMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GetStatusModelMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withNodeModelName(data.get("nodeModelName") == null || data.get("nodeModelName").isNull() ? null : data.get("nodeModelName").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("nodeModelName", getNodeModelName());
            }}
        );
    }
}
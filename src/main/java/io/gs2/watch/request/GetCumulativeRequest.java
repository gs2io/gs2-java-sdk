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

package io.gs2.watch.request;

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
public class GetCumulativeRequest extends Gs2BasicRequest<GetCumulativeRequest> {
    private String name;
    private String resourceGrn;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GetCumulativeRequest withName(String name) {
		this.name = name;
		return this;
	}

	public String getResourceGrn() {
		return resourceGrn;
	}

	public void setResourceGrn(String resourceGrn) {
		this.resourceGrn = resourceGrn;
	}

	public GetCumulativeRequest withResourceGrn(String resourceGrn) {
		this.resourceGrn = resourceGrn;
		return this;
	}

    public static GetCumulativeRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GetCumulativeRequest()
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withResourceGrn(data.get("resourceGrn") == null || data.get("resourceGrn").isNull() ? null : data.get("resourceGrn").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("name", getName());
                put("resourceGrn", getResourceGrn());
            }}
        );
    }
}
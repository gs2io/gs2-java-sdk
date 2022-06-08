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
public class PrepareDownloadByGenerationRequest extends Gs2BasicRequest<PrepareDownloadByGenerationRequest> {
    private String namespaceName;
    private String accessToken;
    private String dataObjectId;
    private String generation;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public PrepareDownloadByGenerationRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public PrepareDownloadByGenerationRequest withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}
	public String getDataObjectId() {
		return dataObjectId;
	}
	public void setDataObjectId(String dataObjectId) {
		this.dataObjectId = dataObjectId;
	}
	public PrepareDownloadByGenerationRequest withDataObjectId(String dataObjectId) {
		this.dataObjectId = dataObjectId;
		return this;
	}
	public String getGeneration() {
		return generation;
	}
	public void setGeneration(String generation) {
		this.generation = generation;
	}
	public PrepareDownloadByGenerationRequest withGeneration(String generation) {
		this.generation = generation;
		return this;
	}

    public static PrepareDownloadByGenerationRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new PrepareDownloadByGenerationRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withAccessToken(data.get("accessToken") == null || data.get("accessToken").isNull() ? null : data.get("accessToken").asText())
            .withDataObjectId(data.get("dataObjectId") == null || data.get("dataObjectId").isNull() ? null : data.get("dataObjectId").asText())
            .withGeneration(data.get("generation") == null || data.get("generation").isNull() ? null : data.get("generation").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("accessToken", getAccessToken());
                put("dataObjectId", getDataObjectId());
                put("generation", getGeneration());
            }}
        );
    }
}
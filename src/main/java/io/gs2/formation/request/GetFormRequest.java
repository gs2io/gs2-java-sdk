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
public class GetFormRequest extends Gs2BasicRequest<GetFormRequest> {
    private String namespaceName;
    private String accessToken;
    private String moldModelName;
    private Integer index;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public GetFormRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public GetFormRequest withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}
	public String getMoldModelName() {
		return moldModelName;
	}
	public void setMoldModelName(String moldModelName) {
		this.moldModelName = moldModelName;
	}
	public GetFormRequest withMoldModelName(String moldModelName) {
		this.moldModelName = moldModelName;
		return this;
	}
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public GetFormRequest withIndex(Integer index) {
		this.index = index;
		return this;
	}

    public static GetFormRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GetFormRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withAccessToken(data.get("accessToken") == null || data.get("accessToken").isNull() ? null : data.get("accessToken").asText())
            .withMoldModelName(data.get("moldModelName") == null || data.get("moldModelName").isNull() ? null : data.get("moldModelName").asText())
            .withIndex(data.get("index") == null || data.get("index").isNull() ? null : data.get("index").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("accessToken", getAccessToken());
                put("moldModelName", getMoldModelName());
                put("index", getIndex());
            }}
        );
    }
}
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

package io.gs2.news.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.news.model.*;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class PrepareUpdateCurrentNewsMasterResult implements IResult, Serializable {
    private String uploadToken;
    private String templateUploadUrl;

	public String getUploadToken() {
		return uploadToken;
	}

	public void setUploadToken(String uploadToken) {
		this.uploadToken = uploadToken;
	}

	public PrepareUpdateCurrentNewsMasterResult withUploadToken(String uploadToken) {
		this.uploadToken = uploadToken;
		return this;
	}

	public String getTemplateUploadUrl() {
		return templateUploadUrl;
	}

	public void setTemplateUploadUrl(String templateUploadUrl) {
		this.templateUploadUrl = templateUploadUrl;
	}

	public PrepareUpdateCurrentNewsMasterResult withTemplateUploadUrl(String templateUploadUrl) {
		this.templateUploadUrl = templateUploadUrl;
		return this;
	}

    public static PrepareUpdateCurrentNewsMasterResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new PrepareUpdateCurrentNewsMasterResult()
            .withUploadToken(data.get("uploadToken") == null || data.get("uploadToken").isNull() ? null : data.get("uploadToken").asText())
            .withTemplateUploadUrl(data.get("templateUploadUrl") == null || data.get("templateUploadUrl").isNull() ? null : data.get("templateUploadUrl").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("uploadToken", getUploadToken());
                put("templateUploadUrl", getTemplateUploadUrl());
            }}
        );
    }
}
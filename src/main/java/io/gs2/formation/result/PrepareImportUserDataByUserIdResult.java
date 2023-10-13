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

package io.gs2.formation.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.formation.model.*;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class PrepareImportUserDataByUserIdResult implements IResult, Serializable {
    private String uploadToken;
    private String uploadUrl;

	public String getUploadToken() {
		return uploadToken;
	}

	public void setUploadToken(String uploadToken) {
		this.uploadToken = uploadToken;
	}

	public PrepareImportUserDataByUserIdResult withUploadToken(String uploadToken) {
		this.uploadToken = uploadToken;
		return this;
	}

	public String getUploadUrl() {
		return uploadUrl;
	}

	public void setUploadUrl(String uploadUrl) {
		this.uploadUrl = uploadUrl;
	}

	public PrepareImportUserDataByUserIdResult withUploadUrl(String uploadUrl) {
		this.uploadUrl = uploadUrl;
		return this;
	}

    public static PrepareImportUserDataByUserIdResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new PrepareImportUserDataByUserIdResult()
            .withUploadToken(data.get("uploadToken") == null || data.get("uploadToken").isNull() ? null : data.get("uploadToken").asText())
            .withUploadUrl(data.get("uploadUrl") == null || data.get("uploadUrl").isNull() ? null : data.get("uploadUrl").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("uploadToken", getUploadToken());
                put("uploadUrl", getUploadUrl());
            }}
        );
    }
}
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

package io.gs2.dictionary.request;

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
public class GetEntryWithSignatureByUserIdRequest extends Gs2BasicRequest<GetEntryWithSignatureByUserIdRequest> {
    private String namespaceName;
    private String userId;
    private String entryModelName;
    private String keyId;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public GetEntryWithSignatureByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public GetEntryWithSignatureByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getEntryModelName() {
		return entryModelName;
	}
	public void setEntryModelName(String entryModelName) {
		this.entryModelName = entryModelName;
	}
	public GetEntryWithSignatureByUserIdRequest withEntryModelName(String entryModelName) {
		this.entryModelName = entryModelName;
		return this;
	}
	public String getKeyId() {
		return keyId;
	}
	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}
	public GetEntryWithSignatureByUserIdRequest withKeyId(String keyId) {
		this.keyId = keyId;
		return this;
	}

    public static GetEntryWithSignatureByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GetEntryWithSignatureByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withEntryModelName(data.get("entryModelName") == null || data.get("entryModelName").isNull() ? null : data.get("entryModelName").asText())
            .withKeyId(data.get("keyId") == null || data.get("keyId").isNull() ? null : data.get("keyId").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("userId", getUserId());
                put("entryModelName", getEntryModelName());
                put("keyId", getKeyId());
            }}
        );
    }
}
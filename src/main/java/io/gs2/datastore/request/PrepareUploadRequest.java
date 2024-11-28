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
public class PrepareUploadRequest extends Gs2BasicRequest<PrepareUploadRequest> {
    private String namespaceName;
    private String accessToken;
    private String name;
    private String contentType;
    private String scope;
    private List<String> allowUserIds;
    private Boolean updateIfExists;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public PrepareUploadRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public PrepareUploadRequest withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PrepareUploadRequest withName(String name) {
		this.name = name;
		return this;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public PrepareUploadRequest withContentType(String contentType) {
		this.contentType = contentType;
		return this;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public PrepareUploadRequest withScope(String scope) {
		this.scope = scope;
		return this;
	}
	public List<String> getAllowUserIds() {
		return allowUserIds;
	}
	public void setAllowUserIds(List<String> allowUserIds) {
		this.allowUserIds = allowUserIds;
	}
	public PrepareUploadRequest withAllowUserIds(List<String> allowUserIds) {
		this.allowUserIds = allowUserIds;
		return this;
	}
	public Boolean getUpdateIfExists() {
		return updateIfExists;
	}
	public void setUpdateIfExists(Boolean updateIfExists) {
		this.updateIfExists = updateIfExists;
	}
	public PrepareUploadRequest withUpdateIfExists(Boolean updateIfExists) {
		this.updateIfExists = updateIfExists;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public PrepareUploadRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static PrepareUploadRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new PrepareUploadRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withAccessToken(data.get("accessToken") == null || data.get("accessToken").isNull() ? null : data.get("accessToken").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withContentType(data.get("contentType") == null || data.get("contentType").isNull() ? null : data.get("contentType").asText())
            .withScope(data.get("scope") == null || data.get("scope").isNull() ? null : data.get("scope").asText())
            .withAllowUserIds(data.get("allowUserIds") == null || data.get("allowUserIds").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("allowUserIds").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()))
            .withUpdateIfExists(data.get("updateIfExists") == null || data.get("updateIfExists").isNull() ? null : data.get("updateIfExists").booleanValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("accessToken", getAccessToken());
                put("name", getName());
                put("contentType", getContentType());
                put("scope", getScope());
                put("allowUserIds", getAllowUserIds() == null ? null :
                    getAllowUserIds().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("updateIfExists", getUpdateIfExists());
            }}
        );
    }
}
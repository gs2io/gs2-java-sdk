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

package io.gs2.money2.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.money2.model.AppleAppStoreContent;
import io.gs2.money2.model.GooglePlayContent;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreateStoreContentModelMasterRequest extends Gs2BasicRequest<CreateStoreContentModelMasterRequest> {
    private String namespaceName;
    private String name;
    private String description;
    private String metadata;
    private AppleAppStoreContent appleAppStore;
    private GooglePlayContent googlePlay;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public CreateStoreContentModelMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CreateStoreContentModelMasterRequest withName(String name) {
		this.name = name;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public CreateStoreContentModelMasterRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public CreateStoreContentModelMasterRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public AppleAppStoreContent getAppleAppStore() {
		return appleAppStore;
	}
	public void setAppleAppStore(AppleAppStoreContent appleAppStore) {
		this.appleAppStore = appleAppStore;
	}
	public CreateStoreContentModelMasterRequest withAppleAppStore(AppleAppStoreContent appleAppStore) {
		this.appleAppStore = appleAppStore;
		return this;
	}
	public GooglePlayContent getGooglePlay() {
		return googlePlay;
	}
	public void setGooglePlay(GooglePlayContent googlePlay) {
		this.googlePlay = googlePlay;
	}
	public CreateStoreContentModelMasterRequest withGooglePlay(GooglePlayContent googlePlay) {
		this.googlePlay = googlePlay;
		return this;
	}

    public static CreateStoreContentModelMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CreateStoreContentModelMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withAppleAppStore(data.get("appleAppStore") == null || data.get("appleAppStore").isNull() ? null : AppleAppStoreContent.fromJson(data.get("appleAppStore")))
            .withGooglePlay(data.get("googlePlay") == null || data.get("googlePlay").isNull() ? null : GooglePlayContent.fromJson(data.get("googlePlay")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("name", getName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("appleAppStore", getAppleAppStore() != null ? getAppleAppStore().toJson() : null);
                put("googlePlay", getGooglePlay() != null ? getGooglePlay().toJson() : null);
            }}
        );
    }
}
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

package io.gs2.distributor.request;

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
public class CreateDistributorModelMasterRequest extends Gs2BasicRequest<CreateDistributorModelMasterRequest> {
    private String namespaceName;
    private String name;
    private String description;
    private String metadata;
    private String inboxNamespaceId;
    private List<String> whiteListTargetIds;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public CreateDistributorModelMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CreateDistributorModelMasterRequest withName(String name) {
		this.name = name;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public CreateDistributorModelMasterRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public CreateDistributorModelMasterRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public String getInboxNamespaceId() {
		return inboxNamespaceId;
	}
	public void setInboxNamespaceId(String inboxNamespaceId) {
		this.inboxNamespaceId = inboxNamespaceId;
	}
	public CreateDistributorModelMasterRequest withInboxNamespaceId(String inboxNamespaceId) {
		this.inboxNamespaceId = inboxNamespaceId;
		return this;
	}
	public List<String> getWhiteListTargetIds() {
		return whiteListTargetIds;
	}
	public void setWhiteListTargetIds(List<String> whiteListTargetIds) {
		this.whiteListTargetIds = whiteListTargetIds;
	}
	public CreateDistributorModelMasterRequest withWhiteListTargetIds(List<String> whiteListTargetIds) {
		this.whiteListTargetIds = whiteListTargetIds;
		return this;
	}

    public static CreateDistributorModelMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CreateDistributorModelMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withInboxNamespaceId(data.get("inboxNamespaceId") == null || data.get("inboxNamespaceId").isNull() ? null : data.get("inboxNamespaceId").asText())
            .withWhiteListTargetIds(data.get("whiteListTargetIds") == null || data.get("whiteListTargetIds").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("whiteListTargetIds").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("name", getName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("inboxNamespaceId", getInboxNamespaceId());
                put("whiteListTargetIds", getWhiteListTargetIds() == null ? null :
                    getWhiteListTargetIds().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }
}
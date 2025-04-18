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

package io.gs2.showcase.request;

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
public class UpdateSalesItemGroupMasterRequest extends Gs2BasicRequest<UpdateSalesItemGroupMasterRequest> {
    private String namespaceName;
    private String salesItemGroupName;
    private String description;
    private String metadata;
    private List<String> salesItemNames;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public UpdateSalesItemGroupMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getSalesItemGroupName() {
		return salesItemGroupName;
	}
	public void setSalesItemGroupName(String salesItemGroupName) {
		this.salesItemGroupName = salesItemGroupName;
	}
	public UpdateSalesItemGroupMasterRequest withSalesItemGroupName(String salesItemGroupName) {
		this.salesItemGroupName = salesItemGroupName;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UpdateSalesItemGroupMasterRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public UpdateSalesItemGroupMasterRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public List<String> getSalesItemNames() {
		return salesItemNames;
	}
	public void setSalesItemNames(List<String> salesItemNames) {
		this.salesItemNames = salesItemNames;
	}
	public UpdateSalesItemGroupMasterRequest withSalesItemNames(List<String> salesItemNames) {
		this.salesItemNames = salesItemNames;
		return this;
	}

    public static UpdateSalesItemGroupMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateSalesItemGroupMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withSalesItemGroupName(data.get("salesItemGroupName") == null || data.get("salesItemGroupName").isNull() ? null : data.get("salesItemGroupName").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withSalesItemNames(data.get("salesItemNames") == null || data.get("salesItemNames").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("salesItemNames").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("salesItemGroupName", getSalesItemGroupName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("salesItemNames", getSalesItemNames() == null ? null :
                    getSalesItemNames().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }
}
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

package io.gs2.skillTree.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.skillTree.model.VerifyAction;
import io.gs2.skillTree.model.ConsumeAction;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreateNodeModelMasterRequest extends Gs2BasicRequest<CreateNodeModelMasterRequest> {
    private String namespaceName;
    private String name;
    private String description;
    private String metadata;
    private List<VerifyAction> releaseVerifyActions;
    private List<ConsumeAction> releaseConsumeActions;
    private Float restrainReturnRate;
    private List<String> premiseNodeNames;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public CreateNodeModelMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CreateNodeModelMasterRequest withName(String name) {
		this.name = name;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public CreateNodeModelMasterRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public CreateNodeModelMasterRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public List<VerifyAction> getReleaseVerifyActions() {
		return releaseVerifyActions;
	}
	public void setReleaseVerifyActions(List<VerifyAction> releaseVerifyActions) {
		this.releaseVerifyActions = releaseVerifyActions;
	}
	public CreateNodeModelMasterRequest withReleaseVerifyActions(List<VerifyAction> releaseVerifyActions) {
		this.releaseVerifyActions = releaseVerifyActions;
		return this;
	}
	public List<ConsumeAction> getReleaseConsumeActions() {
		return releaseConsumeActions;
	}
	public void setReleaseConsumeActions(List<ConsumeAction> releaseConsumeActions) {
		this.releaseConsumeActions = releaseConsumeActions;
	}
	public CreateNodeModelMasterRequest withReleaseConsumeActions(List<ConsumeAction> releaseConsumeActions) {
		this.releaseConsumeActions = releaseConsumeActions;
		return this;
	}
	public Float getRestrainReturnRate() {
		return restrainReturnRate;
	}
	public void setRestrainReturnRate(Float restrainReturnRate) {
		this.restrainReturnRate = restrainReturnRate;
	}
	public CreateNodeModelMasterRequest withRestrainReturnRate(Float restrainReturnRate) {
		this.restrainReturnRate = restrainReturnRate;
		return this;
	}
	public List<String> getPremiseNodeNames() {
		return premiseNodeNames;
	}
	public void setPremiseNodeNames(List<String> premiseNodeNames) {
		this.premiseNodeNames = premiseNodeNames;
	}
	public CreateNodeModelMasterRequest withPremiseNodeNames(List<String> premiseNodeNames) {
		this.premiseNodeNames = premiseNodeNames;
		return this;
	}

    public static CreateNodeModelMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CreateNodeModelMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withReleaseVerifyActions(data.get("releaseVerifyActions") == null || data.get("releaseVerifyActions").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("releaseVerifyActions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return VerifyAction.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withReleaseConsumeActions(data.get("releaseConsumeActions") == null || data.get("releaseConsumeActions").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("releaseConsumeActions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return ConsumeAction.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withRestrainReturnRate(data.get("restrainReturnRate") == null || data.get("restrainReturnRate").isNull() ? null : data.get("restrainReturnRate").floatValue())
            .withPremiseNodeNames(data.get("premiseNodeNames") == null || data.get("premiseNodeNames").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("premiseNodeNames").elements(), Spliterator.NONNULL), false).map(item -> {
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
                put("releaseVerifyActions", getReleaseVerifyActions() == null ? null :
                    getReleaseVerifyActions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("releaseConsumeActions", getReleaseConsumeActions() == null ? null :
                    getReleaseConsumeActions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("restrainReturnRate", getRestrainReturnRate());
                put("premiseNodeNames", getPremiseNodeNames() == null ? null :
                    getPremiseNodeNames().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }
}
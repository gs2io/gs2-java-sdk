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

package io.gs2.enchant.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.enchant.model.RarityParameterCountModel;
import io.gs2.enchant.model.RarityParameterValueModel;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class UpdateRarityParameterModelMasterRequest extends Gs2BasicRequest<UpdateRarityParameterModelMasterRequest> {
    private String namespaceName;
    private String parameterName;
    private String description;
    private String metadata;
    private Integer maximumParameterCount;
    private List<RarityParameterCountModel> parameterCounts;
    private List<RarityParameterValueModel> parameters;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public UpdateRarityParameterModelMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	public UpdateRarityParameterModelMasterRequest withParameterName(String parameterName) {
		this.parameterName = parameterName;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UpdateRarityParameterModelMasterRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public UpdateRarityParameterModelMasterRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public Integer getMaximumParameterCount() {
		return maximumParameterCount;
	}
	public void setMaximumParameterCount(Integer maximumParameterCount) {
		this.maximumParameterCount = maximumParameterCount;
	}
	public UpdateRarityParameterModelMasterRequest withMaximumParameterCount(Integer maximumParameterCount) {
		this.maximumParameterCount = maximumParameterCount;
		return this;
	}
	public List<RarityParameterCountModel> getParameterCounts() {
		return parameterCounts;
	}
	public void setParameterCounts(List<RarityParameterCountModel> parameterCounts) {
		this.parameterCounts = parameterCounts;
	}
	public UpdateRarityParameterModelMasterRequest withParameterCounts(List<RarityParameterCountModel> parameterCounts) {
		this.parameterCounts = parameterCounts;
		return this;
	}
	public List<RarityParameterValueModel> getParameters() {
		return parameters;
	}
	public void setParameters(List<RarityParameterValueModel> parameters) {
		this.parameters = parameters;
	}
	public UpdateRarityParameterModelMasterRequest withParameters(List<RarityParameterValueModel> parameters) {
		this.parameters = parameters;
		return this;
	}

    public static UpdateRarityParameterModelMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateRarityParameterModelMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withParameterName(data.get("parameterName") == null || data.get("parameterName").isNull() ? null : data.get("parameterName").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withMaximumParameterCount(data.get("maximumParameterCount") == null || data.get("maximumParameterCount").isNull() ? null : data.get("maximumParameterCount").intValue())
            .withParameterCounts(data.get("parameterCounts") == null || data.get("parameterCounts").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("parameterCounts").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return RarityParameterCountModel.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withParameters(data.get("parameters") == null || data.get("parameters").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("parameters").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return RarityParameterValueModel.fromJson(item);
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("parameterName", getParameterName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("maximumParameterCount", getMaximumParameterCount());
                put("parameterCounts", getParameterCounts() == null ? null :
                    getParameterCounts().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("parameters", getParameters() == null ? null :
                    getParameters().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }
}
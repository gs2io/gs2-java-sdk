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
import io.gs2.enchant.model.BalanceParameterValueModel;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class UpdateBalanceParameterModelMasterRequest extends Gs2BasicRequest<UpdateBalanceParameterModelMasterRequest> {
    private String namespaceName;
    private String parameterName;
    private String description;
    private String metadata;
    private Long totalValue;
    private String initialValueStrategy;
    private List<BalanceParameterValueModel> parameters;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public UpdateBalanceParameterModelMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	public UpdateBalanceParameterModelMasterRequest withParameterName(String parameterName) {
		this.parameterName = parameterName;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UpdateBalanceParameterModelMasterRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public UpdateBalanceParameterModelMasterRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public Long getTotalValue() {
		return totalValue;
	}
	public void setTotalValue(Long totalValue) {
		this.totalValue = totalValue;
	}
	public UpdateBalanceParameterModelMasterRequest withTotalValue(Long totalValue) {
		this.totalValue = totalValue;
		return this;
	}
	public String getInitialValueStrategy() {
		return initialValueStrategy;
	}
	public void setInitialValueStrategy(String initialValueStrategy) {
		this.initialValueStrategy = initialValueStrategy;
	}
	public UpdateBalanceParameterModelMasterRequest withInitialValueStrategy(String initialValueStrategy) {
		this.initialValueStrategy = initialValueStrategy;
		return this;
	}
	public List<BalanceParameterValueModel> getParameters() {
		return parameters;
	}
	public void setParameters(List<BalanceParameterValueModel> parameters) {
		this.parameters = parameters;
	}
	public UpdateBalanceParameterModelMasterRequest withParameters(List<BalanceParameterValueModel> parameters) {
		this.parameters = parameters;
		return this;
	}

    public static UpdateBalanceParameterModelMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateBalanceParameterModelMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withParameterName(data.get("parameterName") == null || data.get("parameterName").isNull() ? null : data.get("parameterName").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withTotalValue(data.get("totalValue") == null || data.get("totalValue").isNull() ? null : data.get("totalValue").longValue())
            .withInitialValueStrategy(data.get("initialValueStrategy") == null || data.get("initialValueStrategy").isNull() ? null : data.get("initialValueStrategy").asText())
            .withParameters(data.get("parameters") == null || data.get("parameters").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("parameters").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return BalanceParameterValueModel.fromJson(item);
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
                put("totalValue", getTotalValue());
                put("initialValueStrategy", getInitialValueStrategy());
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
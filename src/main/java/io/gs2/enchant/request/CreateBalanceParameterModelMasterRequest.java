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
public class CreateBalanceParameterModelMasterRequest extends Gs2BasicRequest<CreateBalanceParameterModelMasterRequest> {
    private String namespaceName;
    private String name;
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
	public CreateBalanceParameterModelMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CreateBalanceParameterModelMasterRequest withName(String name) {
		this.name = name;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public CreateBalanceParameterModelMasterRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public CreateBalanceParameterModelMasterRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public Long getTotalValue() {
		return totalValue;
	}
	public void setTotalValue(Long totalValue) {
		this.totalValue = totalValue;
	}
	public CreateBalanceParameterModelMasterRequest withTotalValue(Long totalValue) {
		this.totalValue = totalValue;
		return this;
	}
	public String getInitialValueStrategy() {
		return initialValueStrategy;
	}
	public void setInitialValueStrategy(String initialValueStrategy) {
		this.initialValueStrategy = initialValueStrategy;
	}
	public CreateBalanceParameterModelMasterRequest withInitialValueStrategy(String initialValueStrategy) {
		this.initialValueStrategy = initialValueStrategy;
		return this;
	}
	public List<BalanceParameterValueModel> getParameters() {
		return parameters;
	}
	public void setParameters(List<BalanceParameterValueModel> parameters) {
		this.parameters = parameters;
	}
	public CreateBalanceParameterModelMasterRequest withParameters(List<BalanceParameterValueModel> parameters) {
		this.parameters = parameters;
		return this;
	}

    public static CreateBalanceParameterModelMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CreateBalanceParameterModelMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withTotalValue(data.get("totalValue") == null || data.get("totalValue").isNull() ? null : data.get("totalValue").longValue())
            .withInitialValueStrategy(data.get("initialValueStrategy") == null || data.get("initialValueStrategy").isNull() ? null : data.get("initialValueStrategy").asText())
            .withParameters(data.get("parameters") == null || data.get("parameters").isNull() ? new ArrayList<BalanceParameterValueModel>() :
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
                put("name", getName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("totalValue", getTotalValue());
                put("initialValueStrategy", getInitialValueStrategy());
                put("parameters", getParameters() == null ? new ArrayList<BalanceParameterValueModel>() :
                    getParameters().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }
}
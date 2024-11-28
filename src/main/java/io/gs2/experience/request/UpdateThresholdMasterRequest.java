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

package io.gs2.experience.request;

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
public class UpdateThresholdMasterRequest extends Gs2BasicRequest<UpdateThresholdMasterRequest> {
    private String namespaceName;
    private String thresholdName;
    private String description;
    private String metadata;
    private List<Long> values;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public UpdateThresholdMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getThresholdName() {
		return thresholdName;
	}
	public void setThresholdName(String thresholdName) {
		this.thresholdName = thresholdName;
	}
	public UpdateThresholdMasterRequest withThresholdName(String thresholdName) {
		this.thresholdName = thresholdName;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UpdateThresholdMasterRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public UpdateThresholdMasterRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public List<Long> getValues() {
		return values;
	}
	public void setValues(List<Long> values) {
		this.values = values;
	}
	public UpdateThresholdMasterRequest withValues(List<Long> values) {
		this.values = values;
		return this;
	}

    public static UpdateThresholdMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateThresholdMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withThresholdName(data.get("thresholdName") == null || data.get("thresholdName").isNull() ? null : data.get("thresholdName").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withValues(data.get("values") == null || data.get("values").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("values").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.longValue();
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("thresholdName", getThresholdName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("values", getValues() == null ? null :
                    getValues().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }
}
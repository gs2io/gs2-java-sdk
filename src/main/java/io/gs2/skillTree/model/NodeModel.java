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

package io.gs2.skillTree.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.IModel;


@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class NodeModel implements IModel, Serializable, Comparable<NodeModel> {
	private String nodeModelId;
	private String name;
	private String metadata;
	private List<VerifyAction> releaseVerifyActions;
	private List<ConsumeAction> releaseConsumeActions;
	private List<AcquireAction> returnAcquireActions;
	private Float restrainReturnRate;
	private List<String> premiseNodeNames;
	public String getNodeModelId() {
		return nodeModelId;
	}
	public void setNodeModelId(String nodeModelId) {
		this.nodeModelId = nodeModelId;
	}
	public NodeModel withNodeModelId(String nodeModelId) {
		this.nodeModelId = nodeModelId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public NodeModel withName(String name) {
		this.name = name;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public NodeModel withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public List<VerifyAction> getReleaseVerifyActions() {
		return releaseVerifyActions;
	}
	public void setReleaseVerifyActions(List<VerifyAction> releaseVerifyActions) {
		this.releaseVerifyActions = releaseVerifyActions;
	}
	public NodeModel withReleaseVerifyActions(List<VerifyAction> releaseVerifyActions) {
		this.releaseVerifyActions = releaseVerifyActions;
		return this;
	}
	public List<ConsumeAction> getReleaseConsumeActions() {
		return releaseConsumeActions;
	}
	public void setReleaseConsumeActions(List<ConsumeAction> releaseConsumeActions) {
		this.releaseConsumeActions = releaseConsumeActions;
	}
	public NodeModel withReleaseConsumeActions(List<ConsumeAction> releaseConsumeActions) {
		this.releaseConsumeActions = releaseConsumeActions;
		return this;
	}
	public List<AcquireAction> getReturnAcquireActions() {
		return returnAcquireActions;
	}
	public void setReturnAcquireActions(List<AcquireAction> returnAcquireActions) {
		this.returnAcquireActions = returnAcquireActions;
	}
	public NodeModel withReturnAcquireActions(List<AcquireAction> returnAcquireActions) {
		this.returnAcquireActions = returnAcquireActions;
		return this;
	}
	public Float getRestrainReturnRate() {
		return restrainReturnRate;
	}
	public void setRestrainReturnRate(Float restrainReturnRate) {
		this.restrainReturnRate = restrainReturnRate;
	}
	public NodeModel withRestrainReturnRate(Float restrainReturnRate) {
		this.restrainReturnRate = restrainReturnRate;
		return this;
	}
	public List<String> getPremiseNodeNames() {
		return premiseNodeNames;
	}
	public void setPremiseNodeNames(List<String> premiseNodeNames) {
		this.premiseNodeNames = premiseNodeNames;
	}
	public NodeModel withPremiseNodeNames(List<String> premiseNodeNames) {
		this.premiseNodeNames = premiseNodeNames;
		return this;
	}

    public static NodeModel fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new NodeModel()
            .withNodeModelId(data.get("nodeModelId") == null || data.get("nodeModelId").isNull() ? null : data.get("nodeModelId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withReleaseVerifyActions(data.get("releaseVerifyActions") == null || data.get("releaseVerifyActions").isNull() ? new ArrayList<VerifyAction>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("releaseVerifyActions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return VerifyAction.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withReleaseConsumeActions(data.get("releaseConsumeActions") == null || data.get("releaseConsumeActions").isNull() ? new ArrayList<ConsumeAction>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("releaseConsumeActions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return ConsumeAction.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withReturnAcquireActions(data.get("returnAcquireActions") == null || data.get("returnAcquireActions").isNull() ? new ArrayList<AcquireAction>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("returnAcquireActions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return AcquireAction.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withRestrainReturnRate(data.get("restrainReturnRate") == null || data.get("restrainReturnRate").isNull() ? null : data.get("restrainReturnRate").floatValue())
            .withPremiseNodeNames(data.get("premiseNodeNames") == null || data.get("premiseNodeNames").isNull() ? new ArrayList<String>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("premiseNodeNames").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("nodeModelId", getNodeModelId());
                put("name", getName());
                put("metadata", getMetadata());
                put("releaseVerifyActions", getReleaseVerifyActions() == null ? new ArrayList<VerifyAction>() :
                    getReleaseVerifyActions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("releaseConsumeActions", getReleaseConsumeActions() == null ? new ArrayList<ConsumeAction>() :
                    getReleaseConsumeActions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("returnAcquireActions", getReturnAcquireActions() == null ? new ArrayList<AcquireAction>() :
                    getReturnAcquireActions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("restrainReturnRate", getRestrainReturnRate());
                put("premiseNodeNames", getPremiseNodeNames() == null ? new ArrayList<String>() :
                    getPremiseNodeNames().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }

	@Override
	public int compareTo(NodeModel o) {
		return nodeModelId.compareTo(o.nodeModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.nodeModelId == null) ? 0 : this.nodeModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.releaseVerifyActions == null) ? 0 : this.releaseVerifyActions.hashCode());
        result = prime * result + ((this.releaseConsumeActions == null) ? 0 : this.releaseConsumeActions.hashCode());
        result = prime * result + ((this.returnAcquireActions == null) ? 0 : this.returnAcquireActions.hashCode());
        result = prime * result + ((this.restrainReturnRate == null) ? 0 : this.restrainReturnRate.hashCode());
        result = prime * result + ((this.premiseNodeNames == null) ? 0 : this.premiseNodeNames.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		NodeModel other = (NodeModel) o;
		if (nodeModelId == null) {
			return other.nodeModelId == null;
		} else if (!nodeModelId.equals(other.nodeModelId)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (metadata == null) {
			return other.metadata == null;
		} else if (!metadata.equals(other.metadata)) {
			return false;
		}
		if (releaseVerifyActions == null) {
			return other.releaseVerifyActions == null;
		} else if (!releaseVerifyActions.equals(other.releaseVerifyActions)) {
			return false;
		}
		if (releaseConsumeActions == null) {
			return other.releaseConsumeActions == null;
		} else if (!releaseConsumeActions.equals(other.releaseConsumeActions)) {
			return false;
		}
		if (returnAcquireActions == null) {
			return other.returnAcquireActions == null;
		} else if (!returnAcquireActions.equals(other.returnAcquireActions)) {
			return false;
		}
		if (restrainReturnRate == null) {
			return other.restrainReturnRate == null;
		} else if (!restrainReturnRate.equals(other.restrainReturnRate)) {
			return false;
		}
		if (premiseNodeNames == null) {
			return other.premiseNodeNames == null;
		} else if (!premiseNodeNames.equals(other.premiseNodeNames)) {
			return false;
		}
		return true;
	}
}
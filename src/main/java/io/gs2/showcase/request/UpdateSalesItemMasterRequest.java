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
import io.gs2.showcase.model.VerifyAction;
import io.gs2.showcase.model.ConsumeAction;
import io.gs2.showcase.model.AcquireAction;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class UpdateSalesItemMasterRequest extends Gs2BasicRequest<UpdateSalesItemMasterRequest> {
    private String namespaceName;
    private String salesItemName;
    private String description;
    private String metadata;
    private List<VerifyAction> verifyActions;
    private List<ConsumeAction> consumeActions;
    private List<AcquireAction> acquireActions;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public UpdateSalesItemMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getSalesItemName() {
		return salesItemName;
	}
	public void setSalesItemName(String salesItemName) {
		this.salesItemName = salesItemName;
	}
	public UpdateSalesItemMasterRequest withSalesItemName(String salesItemName) {
		this.salesItemName = salesItemName;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UpdateSalesItemMasterRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public UpdateSalesItemMasterRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public List<VerifyAction> getVerifyActions() {
		return verifyActions;
	}
	public void setVerifyActions(List<VerifyAction> verifyActions) {
		this.verifyActions = verifyActions;
	}
	public UpdateSalesItemMasterRequest withVerifyActions(List<VerifyAction> verifyActions) {
		this.verifyActions = verifyActions;
		return this;
	}
	public List<ConsumeAction> getConsumeActions() {
		return consumeActions;
	}
	public void setConsumeActions(List<ConsumeAction> consumeActions) {
		this.consumeActions = consumeActions;
	}
	public UpdateSalesItemMasterRequest withConsumeActions(List<ConsumeAction> consumeActions) {
		this.consumeActions = consumeActions;
		return this;
	}
	public List<AcquireAction> getAcquireActions() {
		return acquireActions;
	}
	public void setAcquireActions(List<AcquireAction> acquireActions) {
		this.acquireActions = acquireActions;
	}
	public UpdateSalesItemMasterRequest withAcquireActions(List<AcquireAction> acquireActions) {
		this.acquireActions = acquireActions;
		return this;
	}

    public static UpdateSalesItemMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateSalesItemMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withSalesItemName(data.get("salesItemName") == null || data.get("salesItemName").isNull() ? null : data.get("salesItemName").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withVerifyActions(data.get("verifyActions") == null || data.get("verifyActions").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("verifyActions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return VerifyAction.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withConsumeActions(data.get("consumeActions") == null || data.get("consumeActions").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("consumeActions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return ConsumeAction.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withAcquireActions(data.get("acquireActions") == null || data.get("acquireActions").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("acquireActions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return AcquireAction.fromJson(item);
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("salesItemName", getSalesItemName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("verifyActions", getVerifyActions() == null ? null :
                    getVerifyActions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("consumeActions", getConsumeActions() == null ? null :
                    getConsumeActions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("acquireActions", getAcquireActions() == null ? null :
                    getAcquireActions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }
}
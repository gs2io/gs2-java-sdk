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
import io.gs2.distributor.model.DistributeResource;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class DistributeRequest extends Gs2BasicRequest<DistributeRequest> {
    private String namespaceName;
    private String distributorName;
    private String userId;
    private DistributeResource distributeResource;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public DistributeRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getDistributorName() {
		return distributorName;
	}
	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
	}
	public DistributeRequest withDistributorName(String distributorName) {
		this.distributorName = distributorName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public DistributeRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public DistributeResource getDistributeResource() {
		return distributeResource;
	}
	public void setDistributeResource(DistributeResource distributeResource) {
		this.distributeResource = distributeResource;
	}
	public DistributeRequest withDistributeResource(DistributeResource distributeResource) {
		this.distributeResource = distributeResource;
		return this;
	}

    public static DistributeRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new DistributeRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withDistributorName(data.get("distributorName") == null || data.get("distributorName").isNull() ? null : data.get("distributorName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withDistributeResource(data.get("distributeResource") == null || data.get("distributeResource").isNull() ? null : DistributeResource.fromJson(data.get("distributeResource")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("distributorName", getDistributorName());
                put("userId", getUserId());
                put("distributeResource", getDistributeResource() != null ? getDistributeResource().toJson() : null);
            }}
        );
    }
}
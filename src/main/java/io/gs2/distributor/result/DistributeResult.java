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

package io.gs2.distributor.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.distributor.model.*;
import io.gs2.distributor.model.DistributeResource;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class DistributeResult implements IResult, Serializable {
    private DistributeResource distributeResource;
    private String inboxNamespaceId;
    private String result;

	public DistributeResource getDistributeResource() {
		return distributeResource;
	}

	public void setDistributeResource(DistributeResource distributeResource) {
		this.distributeResource = distributeResource;
	}

	public DistributeResult withDistributeResource(DistributeResource distributeResource) {
		this.distributeResource = distributeResource;
		return this;
	}

	public String getInboxNamespaceId() {
		return inboxNamespaceId;
	}

	public void setInboxNamespaceId(String inboxNamespaceId) {
		this.inboxNamespaceId = inboxNamespaceId;
	}

	public DistributeResult withInboxNamespaceId(String inboxNamespaceId) {
		this.inboxNamespaceId = inboxNamespaceId;
		return this;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public DistributeResult withResult(String result) {
		this.result = result;
		return this;
	}

    public static DistributeResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new DistributeResult()
            .withDistributeResource(data.get("distributeResource") == null || data.get("distributeResource").isNull() ? null : DistributeResource.fromJson(data.get("distributeResource")))
            .withInboxNamespaceId(data.get("inboxNamespaceId") == null || data.get("inboxNamespaceId").isNull() ? null : data.get("inboxNamespaceId").asText())
            .withResult(data.get("result") == null || data.get("result").isNull() ? null : data.get("result").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("distributeResource", getDistributeResource() != null ? getDistributeResource().toJson() : null);
                put("inboxNamespaceId", getInboxNamespaceId());
                put("result", getResult());
            }}
        );
    }
}
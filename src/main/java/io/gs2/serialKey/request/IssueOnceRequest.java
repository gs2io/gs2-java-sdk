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

package io.gs2.serialKey.request;

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
public class IssueOnceRequest extends Gs2BasicRequest<IssueOnceRequest> {
    private String namespaceName;
    private String campaignModelName;
    private String metadata;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public IssueOnceRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getCampaignModelName() {
		return campaignModelName;
	}
	public void setCampaignModelName(String campaignModelName) {
		this.campaignModelName = campaignModelName;
	}
	public IssueOnceRequest withCampaignModelName(String campaignModelName) {
		this.campaignModelName = campaignModelName;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public IssueOnceRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}

    public static IssueOnceRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new IssueOnceRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withCampaignModelName(data.get("campaignModelName") == null || data.get("campaignModelName").isNull() ? null : data.get("campaignModelName").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("campaignModelName", getCampaignModelName());
                put("metadata", getMetadata());
            }}
        );
    }
}
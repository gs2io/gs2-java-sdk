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
public class DownloadSerialCodesRequest extends Gs2BasicRequest<DownloadSerialCodesRequest> {
    private String namespaceName;
    private String campaignModelName;
    private String issueJobName;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public DownloadSerialCodesRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getCampaignModelName() {
		return campaignModelName;
	}
	public void setCampaignModelName(String campaignModelName) {
		this.campaignModelName = campaignModelName;
	}
	public DownloadSerialCodesRequest withCampaignModelName(String campaignModelName) {
		this.campaignModelName = campaignModelName;
		return this;
	}
	public String getIssueJobName() {
		return issueJobName;
	}
	public void setIssueJobName(String issueJobName) {
		this.issueJobName = issueJobName;
	}
	public DownloadSerialCodesRequest withIssueJobName(String issueJobName) {
		this.issueJobName = issueJobName;
		return this;
	}

    public static DownloadSerialCodesRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new DownloadSerialCodesRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withCampaignModelName(data.get("campaignModelName") == null || data.get("campaignModelName").isNull() ? null : data.get("campaignModelName").asText())
            .withIssueJobName(data.get("issueJobName") == null || data.get("issueJobName").isNull() ? null : data.get("issueJobName").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("campaignModelName", getCampaignModelName());
                put("issueJobName", getIssueJobName());
            }}
        );
    }
}
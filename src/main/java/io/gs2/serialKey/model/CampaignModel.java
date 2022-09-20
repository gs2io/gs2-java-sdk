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

package io.gs2.serialKey.model;

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
public class CampaignModel implements IModel, Serializable, Comparable<CampaignModel> {
	private String campaignId;
	private String name;
	private String metadata;
	private Boolean enableCampaignCode;
	public String getCampaignId() {
		return campaignId;
	}
	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;
	}
	public CampaignModel withCampaignId(String campaignId) {
		this.campaignId = campaignId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CampaignModel withName(String name) {
		this.name = name;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public CampaignModel withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public Boolean getEnableCampaignCode() {
		return enableCampaignCode;
	}
	public void setEnableCampaignCode(Boolean enableCampaignCode) {
		this.enableCampaignCode = enableCampaignCode;
	}
	public CampaignModel withEnableCampaignCode(Boolean enableCampaignCode) {
		this.enableCampaignCode = enableCampaignCode;
		return this;
	}

    public static CampaignModel fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CampaignModel()
            .withCampaignId(data.get("campaignId") == null || data.get("campaignId").isNull() ? null : data.get("campaignId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withEnableCampaignCode(data.get("enableCampaignCode") == null || data.get("enableCampaignCode").isNull() ? null : data.get("enableCampaignCode").booleanValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("campaignId", getCampaignId());
                put("name", getName());
                put("metadata", getMetadata());
                put("enableCampaignCode", getEnableCampaignCode());
            }}
        );
    }

	@Override
	public int compareTo(CampaignModel o) {
		return campaignId.compareTo(o.campaignId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.campaignId == null) ? 0 : this.campaignId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.enableCampaignCode == null) ? 0 : this.enableCampaignCode.hashCode());
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
		CampaignModel other = (CampaignModel) o;
		if (campaignId == null) {
			return other.campaignId == null;
		} else if (!campaignId.equals(other.campaignId)) {
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
		if (enableCampaignCode == null) {
			return other.enableCampaignCode == null;
		} else if (!enableCampaignCode.equals(other.enableCampaignCode)) {
			return false;
		}
		return true;
	}
}
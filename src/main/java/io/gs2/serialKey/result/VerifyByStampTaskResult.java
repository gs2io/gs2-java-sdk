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

package io.gs2.serialKey.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.serialKey.model.*;
import io.gs2.serialKey.model.SerialKey;
import io.gs2.serialKey.model.CampaignModel;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class VerifyByStampTaskResult implements IResult, Serializable {
    private SerialKey item;
    private CampaignModel campaignModel;
    private String newContextStack;

	public SerialKey getItem() {
		return item;
	}

	public void setItem(SerialKey item) {
		this.item = item;
	}

	public VerifyByStampTaskResult withItem(SerialKey item) {
		this.item = item;
		return this;
	}

	public CampaignModel getCampaignModel() {
		return campaignModel;
	}

	public void setCampaignModel(CampaignModel campaignModel) {
		this.campaignModel = campaignModel;
	}

	public VerifyByStampTaskResult withCampaignModel(CampaignModel campaignModel) {
		this.campaignModel = campaignModel;
		return this;
	}

	public String getNewContextStack() {
		return newContextStack;
	}

	public void setNewContextStack(String newContextStack) {
		this.newContextStack = newContextStack;
	}

	public VerifyByStampTaskResult withNewContextStack(String newContextStack) {
		this.newContextStack = newContextStack;
		return this;
	}

    public static VerifyByStampTaskResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new VerifyByStampTaskResult()
            .withItem(data.get("item") == null || data.get("item").isNull() ? null : SerialKey.fromJson(data.get("item")))
            .withCampaignModel(data.get("campaignModel") == null || data.get("campaignModel").isNull() ? null : CampaignModel.fromJson(data.get("campaignModel")))
            .withNewContextStack(data.get("newContextStack") == null || data.get("newContextStack").isNull() ? null : data.get("newContextStack").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("item", getItem() != null ? getItem().toJson() : null);
                put("campaignModel", getCampaignModel() != null ? getCampaignModel().toJson() : null);
                put("newContextStack", getNewContextStack());
            }}
        );
    }
}
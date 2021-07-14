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

package io.gs2.matchmaking.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.matchmaking.model.AttributeRange;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class UpdateGatheringRequest extends Gs2BasicRequest<UpdateGatheringRequest> {
    private String namespaceName;
    private String gatheringName;
    private String accessToken;
    private List<AttributeRange> attributeRanges;

	public String getNamespaceName() {
		return namespaceName;
	}

	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}

	public UpdateGatheringRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}

	public String getGatheringName() {
		return gatheringName;
	}

	public void setGatheringName(String gatheringName) {
		this.gatheringName = gatheringName;
	}

	public UpdateGatheringRequest withGatheringName(String gatheringName) {
		this.gatheringName = gatheringName;
		return this;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public UpdateGatheringRequest withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}

	public List<AttributeRange> getAttributeRanges() {
		return attributeRanges;
	}

	public void setAttributeRanges(List<AttributeRange> attributeRanges) {
		this.attributeRanges = attributeRanges;
	}

	public UpdateGatheringRequest withAttributeRanges(List<AttributeRange> attributeRanges) {
		this.attributeRanges = attributeRanges;
		return this;
	}

    public static UpdateGatheringRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateGatheringRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withGatheringName(data.get("gatheringName") == null || data.get("gatheringName").isNull() ? null : data.get("gatheringName").asText())
            .withAccessToken(data.get("accessToken") == null || data.get("accessToken").isNull() ? null : data.get("accessToken").asText())
            .withAttributeRanges(data.get("attributeRanges") == null || data.get("attributeRanges").isNull() ? new ArrayList<AttributeRange>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("attributeRanges").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return AttributeRange.fromJson(item);
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("gatheringName", getGatheringName());
                put("accessToken", getAccessToken());
                put("attributeRanges", getAttributeRanges() == null ? new ArrayList<AttributeRange>() :
                    getAttributeRanges().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }
}
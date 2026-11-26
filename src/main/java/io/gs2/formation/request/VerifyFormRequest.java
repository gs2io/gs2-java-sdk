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

package io.gs2.formation.request;

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
public class VerifyFormRequest extends Gs2BasicRequest<VerifyFormRequest> {
    private String namespaceName;
    private String accessToken;
    private String moldModelName;
    private Integer index;
    private String slotName;
    private String propertyIdRegex;
    private String countType;
    private String verifyType;
    private Integer count;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public VerifyFormRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public VerifyFormRequest withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}
	public String getMoldModelName() {
		return moldModelName;
	}
	public void setMoldModelName(String moldModelName) {
		this.moldModelName = moldModelName;
	}
	public VerifyFormRequest withMoldModelName(String moldModelName) {
		this.moldModelName = moldModelName;
		return this;
	}
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public VerifyFormRequest withIndex(Integer index) {
		this.index = index;
		return this;
	}
	public String getSlotName() {
		return slotName;
	}
	public void setSlotName(String slotName) {
		this.slotName = slotName;
	}
	public VerifyFormRequest withSlotName(String slotName) {
		this.slotName = slotName;
		return this;
	}
	public String getPropertyIdRegex() {
		return propertyIdRegex;
	}
	public void setPropertyIdRegex(String propertyIdRegex) {
		this.propertyIdRegex = propertyIdRegex;
	}
	public VerifyFormRequest withPropertyIdRegex(String propertyIdRegex) {
		this.propertyIdRegex = propertyIdRegex;
		return this;
	}
	public String getCountType() {
		return countType;
	}
	public void setCountType(String countType) {
		this.countType = countType;
	}
	public VerifyFormRequest withCountType(String countType) {
		this.countType = countType;
		return this;
	}
	public String getVerifyType() {
		return verifyType;
	}
	public void setVerifyType(String verifyType) {
		this.verifyType = verifyType;
	}
	public VerifyFormRequest withVerifyType(String verifyType) {
		this.verifyType = verifyType;
		return this;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public VerifyFormRequest withCount(Integer count) {
		this.count = count;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public VerifyFormRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static VerifyFormRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new VerifyFormRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withAccessToken(data.get("accessToken") == null || data.get("accessToken").isNull() ? null : data.get("accessToken").asText())
            .withMoldModelName(data.get("moldModelName") == null || data.get("moldModelName").isNull() ? null : data.get("moldModelName").asText())
            .withIndex(data.get("index") == null || data.get("index").isNull() ? null : data.get("index").intValue())
            .withSlotName(data.get("slotName") == null || data.get("slotName").isNull() ? null : data.get("slotName").asText())
            .withPropertyIdRegex(data.get("propertyIdRegex") == null || data.get("propertyIdRegex").isNull() ? null : data.get("propertyIdRegex").asText())
            .withCountType(data.get("countType") == null || data.get("countType").isNull() ? null : data.get("countType").asText())
            .withVerifyType(data.get("verifyType") == null || data.get("verifyType").isNull() ? null : data.get("verifyType").asText())
            .withCount(data.get("count") == null || data.get("count").isNull() ? null : data.get("count").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("accessToken", getAccessToken());
                put("moldModelName", getMoldModelName());
                put("index", getIndex());
                put("slotName", getSlotName());
                put("propertyIdRegex", getPropertyIdRegex());
                put("countType", getCountType());
                put("verifyType", getVerifyType());
                put("count", getCount());
            }}
        );
    }
}
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

package io.gs2.money.request;

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
public class WithdrawRequest extends Gs2BasicRequest<WithdrawRequest> {
    private String namespaceName;
    private String accessToken;
    private Integer slot;
    private Integer count;
    private Boolean paidOnly;

	public String getNamespaceName() {
		return namespaceName;
	}

	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}

	public WithdrawRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public WithdrawRequest withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}

	public Integer getSlot() {
		return slot;
	}

	public void setSlot(Integer slot) {
		this.slot = slot;
	}

	public WithdrawRequest withSlot(Integer slot) {
		this.slot = slot;
		return this;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public WithdrawRequest withCount(Integer count) {
		this.count = count;
		return this;
	}

	public Boolean getPaidOnly() {
		return paidOnly;
	}

	public void setPaidOnly(Boolean paidOnly) {
		this.paidOnly = paidOnly;
	}

	public WithdrawRequest withPaidOnly(Boolean paidOnly) {
		this.paidOnly = paidOnly;
		return this;
	}

    public static WithdrawRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new WithdrawRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withAccessToken(data.get("accessToken") == null || data.get("accessToken").isNull() ? null : data.get("accessToken").asText())
            .withSlot(data.get("slot") == null || data.get("slot").isNull() ? null : data.get("slot").intValue())
            .withCount(data.get("count") == null || data.get("count").isNull() ? null : data.get("count").intValue())
            .withPaidOnly(data.get("paidOnly") == null || data.get("paidOnly").isNull() ? null : data.get("paidOnly").booleanValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("accessToken", getAccessToken());
                put("slot", getSlot());
                put("count", getCount());
                put("paidOnly", getPaidOnly());
            }}
        );
    }
}
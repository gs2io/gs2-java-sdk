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

package io.gs2.lottery.request;

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
public class ResetPrizeLimitRequest extends Gs2BasicRequest<ResetPrizeLimitRequest> {
    private String namespaceName;
    private String prizeTableName;
    private String prizeId;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public ResetPrizeLimitRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getPrizeTableName() {
		return prizeTableName;
	}
	public void setPrizeTableName(String prizeTableName) {
		this.prizeTableName = prizeTableName;
	}
	public ResetPrizeLimitRequest withPrizeTableName(String prizeTableName) {
		this.prizeTableName = prizeTableName;
		return this;
	}
	public String getPrizeId() {
		return prizeId;
	}
	public void setPrizeId(String prizeId) {
		this.prizeId = prizeId;
	}
	public ResetPrizeLimitRequest withPrizeId(String prizeId) {
		this.prizeId = prizeId;
		return this;
	}

    public static ResetPrizeLimitRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new ResetPrizeLimitRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withPrizeTableName(data.get("prizeTableName") == null || data.get("prizeTableName").isNull() ? null : data.get("prizeTableName").asText())
            .withPrizeId(data.get("prizeId") == null || data.get("prizeId").isNull() ? null : data.get("prizeId").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("prizeTableName", getPrizeTableName());
                put("prizeId", getPrizeId());
            }}
        );
    }
}
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

package io.gs2.loginReward.request;

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
public class GetBonusModelRequest extends Gs2BasicRequest<GetBonusModelRequest> {
    private String namespaceName;
    private String bonusModelName;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public GetBonusModelRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getBonusModelName() {
		return bonusModelName;
	}
	public void setBonusModelName(String bonusModelName) {
		this.bonusModelName = bonusModelName;
	}
	public GetBonusModelRequest withBonusModelName(String bonusModelName) {
		this.bonusModelName = bonusModelName;
		return this;
	}

    public static GetBonusModelRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GetBonusModelRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withBonusModelName(data.get("bonusModelName") == null || data.get("bonusModelName").isNull() ? null : data.get("bonusModelName").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("bonusModelName", getBonusModelName());
            }}
        );
    }
}
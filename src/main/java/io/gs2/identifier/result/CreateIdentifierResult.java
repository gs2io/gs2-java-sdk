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

package io.gs2.identifier.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.identifier.model.*;
import io.gs2.identifier.model.Identifier;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreateIdentifierResult implements IResult, Serializable {
    private Identifier item;
    private String clientSecret;

	public Identifier getItem() {
		return item;
	}

	public void setItem(Identifier item) {
		this.item = item;
	}

	public CreateIdentifierResult withItem(Identifier item) {
		this.item = item;
		return this;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public CreateIdentifierResult withClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
		return this;
	}

    public static CreateIdentifierResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CreateIdentifierResult()
            .withItem(data.get("item") == null || data.get("item").isNull() ? null : Identifier.fromJson(data.get("item")))
            .withClientSecret(data.get("clientSecret") == null || data.get("clientSecret").isNull() ? null : data.get("clientSecret").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("item", getItem() != null ? getItem().toJson() : null);
                put("clientSecret", getClientSecret());
            }}
        );
    }
}
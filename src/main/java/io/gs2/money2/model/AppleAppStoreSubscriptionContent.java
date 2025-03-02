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

package io.gs2.money2.model;

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
public class AppleAppStoreSubscriptionContent implements IModel, Serializable {
	private String subscriptionGroupIdentifier;
	public String getSubscriptionGroupIdentifier() {
		return subscriptionGroupIdentifier;
	}
	public void setSubscriptionGroupIdentifier(String subscriptionGroupIdentifier) {
		this.subscriptionGroupIdentifier = subscriptionGroupIdentifier;
	}
	public AppleAppStoreSubscriptionContent withSubscriptionGroupIdentifier(String subscriptionGroupIdentifier) {
		this.subscriptionGroupIdentifier = subscriptionGroupIdentifier;
		return this;
	}

    public static AppleAppStoreSubscriptionContent fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new AppleAppStoreSubscriptionContent()
            .withSubscriptionGroupIdentifier(data.get("subscriptionGroupIdentifier") == null || data.get("subscriptionGroupIdentifier").isNull() ? null : data.get("subscriptionGroupIdentifier").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("subscriptionGroupIdentifier", getSubscriptionGroupIdentifier());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.subscriptionGroupIdentifier == null) ? 0 : this.subscriptionGroupIdentifier.hashCode());
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
		AppleAppStoreSubscriptionContent other = (AppleAppStoreSubscriptionContent) o;
		if (subscriptionGroupIdentifier == null) {
			return other.subscriptionGroupIdentifier == null;
		} else if (!subscriptionGroupIdentifier.equals(other.subscriptionGroupIdentifier)) {
			return false;
		}
		return true;
	}
}
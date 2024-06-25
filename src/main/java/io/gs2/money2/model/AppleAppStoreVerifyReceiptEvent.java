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
public class AppleAppStoreVerifyReceiptEvent implements IModel, Serializable {
	private String environment;
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	public AppleAppStoreVerifyReceiptEvent withEnvironment(String environment) {
		this.environment = environment;
		return this;
	}

    public static AppleAppStoreVerifyReceiptEvent fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new AppleAppStoreVerifyReceiptEvent()
            .withEnvironment(data.get("environment") == null || data.get("environment").isNull() ? null : data.get("environment").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("environment", getEnvironment());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.environment == null) ? 0 : this.environment.hashCode());
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
		AppleAppStoreVerifyReceiptEvent other = (AppleAppStoreVerifyReceiptEvent) o;
		if (environment == null) {
			return other.environment == null;
		} else if (!environment.equals(other.environment)) {
			return false;
		}
		return true;
	}
}
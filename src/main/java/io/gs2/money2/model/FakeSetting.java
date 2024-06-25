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
public class FakeSetting implements IModel, Serializable {
	private String acceptFakeReceipt;
	public String getAcceptFakeReceipt() {
		return acceptFakeReceipt;
	}
	public void setAcceptFakeReceipt(String acceptFakeReceipt) {
		this.acceptFakeReceipt = acceptFakeReceipt;
	}
	public FakeSetting withAcceptFakeReceipt(String acceptFakeReceipt) {
		this.acceptFakeReceipt = acceptFakeReceipt;
		return this;
	}

    public static FakeSetting fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new FakeSetting()
            .withAcceptFakeReceipt(data.get("acceptFakeReceipt") == null || data.get("acceptFakeReceipt").isNull() ? null : data.get("acceptFakeReceipt").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("acceptFakeReceipt", getAcceptFakeReceipt());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.acceptFakeReceipt == null) ? 0 : this.acceptFakeReceipt.hashCode());
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
		FakeSetting other = (FakeSetting) o;
		if (acceptFakeReceipt == null) {
			return other.acceptFakeReceipt == null;
		} else if (!acceptFakeReceipt.equals(other.acceptFakeReceipt)) {
			return false;
		}
		return true;
	}
}
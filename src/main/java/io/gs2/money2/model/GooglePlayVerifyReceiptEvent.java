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
public class GooglePlayVerifyReceiptEvent implements IModel, Serializable {
	private String purchaseToken;
	public String getPurchaseToken() {
		return purchaseToken;
	}
	public void setPurchaseToken(String purchaseToken) {
		this.purchaseToken = purchaseToken;
	}
	public GooglePlayVerifyReceiptEvent withPurchaseToken(String purchaseToken) {
		this.purchaseToken = purchaseToken;
		return this;
	}

    public static GooglePlayVerifyReceiptEvent fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GooglePlayVerifyReceiptEvent()
            .withPurchaseToken(data.get("purchaseToken") == null || data.get("purchaseToken").isNull() ? null : data.get("purchaseToken").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("purchaseToken", getPurchaseToken());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.purchaseToken == null) ? 0 : this.purchaseToken.hashCode());
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
		GooglePlayVerifyReceiptEvent other = (GooglePlayVerifyReceiptEvent) o;
		if (purchaseToken == null) {
			return other.purchaseToken == null;
		} else if (!purchaseToken.equals(other.purchaseToken)) {
			return false;
		}
		return true;
	}
}
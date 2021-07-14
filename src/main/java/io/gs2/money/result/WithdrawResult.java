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

package io.gs2.money.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.money.model.*;
import io.gs2.money.model.WalletDetail;
import io.gs2.money.model.Wallet;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class WithdrawResult implements IResult, Serializable {
    private Wallet item;
    private Float price;

	public Wallet getItem() {
		return item;
	}

	public void setItem(Wallet item) {
		this.item = item;
	}

	public WithdrawResult withItem(Wallet item) {
		this.item = item;
		return this;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public WithdrawResult withPrice(Float price) {
		this.price = price;
		return this;
	}

    public static WithdrawResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new WithdrawResult()
            .withItem(data.get("item") == null || data.get("item").isNull() ? null : Wallet.fromJson(data.get("item")))
            .withPrice(data.get("price") == null || data.get("price").isNull() ? null : data.get("price").floatValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("item", getItem() != null ? getItem().toJson() : null);
                put("price", getPrice());
            }}
        );
    }
}
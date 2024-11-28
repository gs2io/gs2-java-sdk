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

package io.gs2.distributor.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.distributor.model.*;
import io.gs2.distributor.model.VerifyActionResult;
import io.gs2.distributor.model.ConsumeActionResult;
import io.gs2.distributor.model.AcquireActionResult;
import io.gs2.distributor.model.TransactionResult;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class GetTransactionResultByUserIdResult implements IResult, Serializable {
    private TransactionResult item;

	public TransactionResult getItem() {
		return item;
	}

	public void setItem(TransactionResult item) {
		this.item = item;
	}

	public GetTransactionResultByUserIdResult withItem(TransactionResult item) {
		this.item = item;
		return this;
	}

    public static GetTransactionResultByUserIdResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GetTransactionResultByUserIdResult()
            .withItem(data.get("item") == null || data.get("item").isNull() ? null : TransactionResult.fromJson(data.get("item")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("item", getItem() != null ? getItem().toJson() : null);
            }}
        );
    }
}
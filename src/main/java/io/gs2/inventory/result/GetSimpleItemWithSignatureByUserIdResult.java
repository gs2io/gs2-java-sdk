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

package io.gs2.inventory.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.inventory.model.*;
import io.gs2.inventory.model.SimpleItem;
import io.gs2.inventory.model.SimpleItemModel;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class GetSimpleItemWithSignatureByUserIdResult implements IResult, Serializable {
    private SimpleItem item;
    private SimpleItemModel simpleItemModel;
    private String body;
    private String signature;

	public SimpleItem getItem() {
		return item;
	}

	public void setItem(SimpleItem item) {
		this.item = item;
	}

	public GetSimpleItemWithSignatureByUserIdResult withItem(SimpleItem item) {
		this.item = item;
		return this;
	}

	public SimpleItemModel getSimpleItemModel() {
		return simpleItemModel;
	}

	public void setSimpleItemModel(SimpleItemModel simpleItemModel) {
		this.simpleItemModel = simpleItemModel;
	}

	public GetSimpleItemWithSignatureByUserIdResult withSimpleItemModel(SimpleItemModel simpleItemModel) {
		this.simpleItemModel = simpleItemModel;
		return this;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public GetSimpleItemWithSignatureByUserIdResult withBody(String body) {
		this.body = body;
		return this;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public GetSimpleItemWithSignatureByUserIdResult withSignature(String signature) {
		this.signature = signature;
		return this;
	}

    public static GetSimpleItemWithSignatureByUserIdResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GetSimpleItemWithSignatureByUserIdResult()
            .withItem(data.get("item") == null || data.get("item").isNull() ? null : SimpleItem.fromJson(data.get("item")))
            .withSimpleItemModel(data.get("simpleItemModel") == null || data.get("simpleItemModel").isNull() ? null : SimpleItemModel.fromJson(data.get("simpleItemModel")))
            .withBody(data.get("body") == null || data.get("body").isNull() ? null : data.get("body").asText())
            .withSignature(data.get("signature") == null || data.get("signature").isNull() ? null : data.get("signature").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("item", getItem() != null ? getItem().toJson() : null);
                put("simpleItemModel", getSimpleItemModel() != null ? getSimpleItemModel().toJson() : null);
                put("body", getBody());
                put("signature", getSignature());
            }}
        );
    }
}
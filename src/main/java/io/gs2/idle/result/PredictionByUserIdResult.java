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

package io.gs2.idle.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.idle.model.*;
import io.gs2.idle.model.AcquireAction;
import io.gs2.idle.model.Status;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class PredictionByUserIdResult implements IResult, Serializable {
    private List<AcquireAction> items;
    private Status status;

	public List<AcquireAction> getItems() {
		return items;
	}

	public void setItems(List<AcquireAction> items) {
		this.items = items;
	}

	public PredictionByUserIdResult withItems(List<AcquireAction> items) {
		this.items = items;
		return this;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public PredictionByUserIdResult withStatus(Status status) {
		this.status = status;
		return this;
	}

    public static PredictionByUserIdResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new PredictionByUserIdResult()
            .withItems(data.get("items") == null || data.get("items").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("items").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return AcquireAction.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withStatus(data.get("status") == null || data.get("status").isNull() ? null : Status.fromJson(data.get("status")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("items", getItems() == null ? null :
                    getItems().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("status", getStatus() != null ? getStatus().toJson() : null);
            }}
        );
    }
}
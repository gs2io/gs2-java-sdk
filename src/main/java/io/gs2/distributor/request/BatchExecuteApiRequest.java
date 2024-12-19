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

package io.gs2.distributor.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.distributor.model.BatchRequestPayload;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class BatchExecuteApiRequest extends Gs2BasicRequest<BatchExecuteApiRequest> {
    private List<BatchRequestPayload> requestPayloads;
	public List<BatchRequestPayload> getRequestPayloads() {
		return requestPayloads;
	}
	public void setRequestPayloads(List<BatchRequestPayload> requestPayloads) {
		this.requestPayloads = requestPayloads;
	}
	public BatchExecuteApiRequest withRequestPayloads(List<BatchRequestPayload> requestPayloads) {
		this.requestPayloads = requestPayloads;
		return this;
	}

    public static BatchExecuteApiRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new BatchExecuteApiRequest()
            .withRequestPayloads(data.get("requestPayloads") == null || data.get("requestPayloads").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("requestPayloads").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return BatchRequestPayload.fromJson(item);
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("requestPayloads", getRequestPayloads() == null ? null :
                    getRequestPayloads().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }
}
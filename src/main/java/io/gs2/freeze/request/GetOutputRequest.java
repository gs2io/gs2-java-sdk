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

package io.gs2.freeze.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class GetOutputRequest extends Gs2BasicRequest<GetOutputRequest> {
    private String stageName;
    private String outputName;
	public String getStageName() {
		return stageName;
	}
	public void setStageName(String stageName) {
		this.stageName = stageName;
	}
	public GetOutputRequest withStageName(String stageName) {
		this.stageName = stageName;
		return this;
	}
	public String getOutputName() {
		return outputName;
	}
	public void setOutputName(String outputName) {
		this.outputName = outputName;
	}
	public GetOutputRequest withOutputName(String outputName) {
		this.outputName = outputName;
		return this;
	}

    public static GetOutputRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GetOutputRequest()
            .withStageName(data.get("stageName") == null || data.get("stageName").isNull() ? null : data.get("stageName").asText())
            .withOutputName(data.get("outputName") == null || data.get("outputName").isNull() ? null : data.get("outputName").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("stageName", getStageName());
                put("outputName", getOutputName());
            }}
        );
    }
}
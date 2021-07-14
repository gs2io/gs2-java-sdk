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

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class RunStampSheetExpressWithoutNamespaceResult implements IResult, Serializable {
    private List<String> taskResults;
    private String sheetResult;

	public List<String> getTaskResults() {
		return taskResults;
	}

	public void setTaskResults(List<String> taskResults) {
		this.taskResults = taskResults;
	}

	public RunStampSheetExpressWithoutNamespaceResult withTaskResults(List<String> taskResults) {
		this.taskResults = taskResults;
		return this;
	}

	public String getSheetResult() {
		return sheetResult;
	}

	public void setSheetResult(String sheetResult) {
		this.sheetResult = sheetResult;
	}

	public RunStampSheetExpressWithoutNamespaceResult withSheetResult(String sheetResult) {
		this.sheetResult = sheetResult;
		return this;
	}

    public static RunStampSheetExpressWithoutNamespaceResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new RunStampSheetExpressWithoutNamespaceResult()
            .withTaskResults(data.get("taskResults") == null || data.get("taskResults").isNull() ? new ArrayList<String>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("taskResults").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()))
            .withSheetResult(data.get("sheetResult") == null || data.get("sheetResult").isNull() ? null : data.get("sheetResult").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("taskResults", getTaskResults() == null ? new ArrayList<String>() :
                    getTaskResults().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("sheetResult", getSheetResult());
            }}
        );
    }
}
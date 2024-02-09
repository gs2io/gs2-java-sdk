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
    private List<Integer> taskResultCodes;
    private List<String> taskResults;
    private Integer sheetResultCode;
    private String sheetResult;

	public List<Integer> getTaskResultCodes() {
		return taskResultCodes;
	}

	public void setTaskResultCodes(List<Integer> taskResultCodes) {
		this.taskResultCodes = taskResultCodes;
	}

	public RunStampSheetExpressWithoutNamespaceResult withTaskResultCodes(List<Integer> taskResultCodes) {
		this.taskResultCodes = taskResultCodes;
		return this;
	}

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

	public Integer getSheetResultCode() {
		return sheetResultCode;
	}

	public void setSheetResultCode(Integer sheetResultCode) {
		this.sheetResultCode = sheetResultCode;
	}

	public RunStampSheetExpressWithoutNamespaceResult withSheetResultCode(Integer sheetResultCode) {
		this.sheetResultCode = sheetResultCode;
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
            .withTaskResultCodes(data.get("taskResultCodes") == null || data.get("taskResultCodes").isNull() ? new ArrayList<Integer>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("taskResultCodes").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.intValue();
                }
            ).collect(Collectors.toList()))
            .withTaskResults(data.get("taskResults") == null || data.get("taskResults").isNull() ? new ArrayList<String>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("taskResults").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()))
            .withSheetResultCode(data.get("sheetResultCode") == null || data.get("sheetResultCode").isNull() ? null : data.get("sheetResultCode").intValue())
            .withSheetResult(data.get("sheetResult") == null || data.get("sheetResult").isNull() ? null : data.get("sheetResult").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("taskResultCodes", getTaskResultCodes() == null ? new ArrayList<Integer>() :
                    getTaskResultCodes().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("taskResults", getTaskResults() == null ? new ArrayList<String>() :
                    getTaskResults().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("sheetResultCode", getSheetResultCode());
                put("sheetResult", getSheetResult());
            }}
        );
    }
}
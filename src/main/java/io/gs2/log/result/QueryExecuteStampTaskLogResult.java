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

package io.gs2.log.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.log.model.*;
import io.gs2.log.model.ExecuteStampTaskLog;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class QueryExecuteStampTaskLogResult implements IResult, Serializable {
    private List<ExecuteStampTaskLog> items;
    private String nextPageToken;
    private Long totalCount;
    private Long scanSize;

	public List<ExecuteStampTaskLog> getItems() {
		return items;
	}

	public void setItems(List<ExecuteStampTaskLog> items) {
		this.items = items;
	}

	public QueryExecuteStampTaskLogResult withItems(List<ExecuteStampTaskLog> items) {
		this.items = items;
		return this;
	}

	public String getNextPageToken() {
		return nextPageToken;
	}

	public void setNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
	}

	public QueryExecuteStampTaskLogResult withNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
		return this;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public QueryExecuteStampTaskLogResult withTotalCount(Long totalCount) {
		this.totalCount = totalCount;
		return this;
	}

	public Long getScanSize() {
		return scanSize;
	}

	public void setScanSize(Long scanSize) {
		this.scanSize = scanSize;
	}

	public QueryExecuteStampTaskLogResult withScanSize(Long scanSize) {
		this.scanSize = scanSize;
		return this;
	}

    public static QueryExecuteStampTaskLogResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new QueryExecuteStampTaskLogResult()
            .withItems(data.get("items") == null || data.get("items").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("items").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return ExecuteStampTaskLog.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withNextPageToken(data.get("nextPageToken") == null || data.get("nextPageToken").isNull() ? null : data.get("nextPageToken").asText())
            .withTotalCount(data.get("totalCount") == null || data.get("totalCount").isNull() ? null : data.get("totalCount").longValue())
            .withScanSize(data.get("scanSize") == null || data.get("scanSize").isNull() ? null : data.get("scanSize").longValue());
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
                put("nextPageToken", getNextPageToken());
                put("totalCount", getTotalCount());
                put("scanSize", getScanSize());
            }}
        );
    }
}
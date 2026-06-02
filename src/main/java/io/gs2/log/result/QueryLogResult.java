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
import io.gs2.log.model.Label;
import io.gs2.log.model.LogEntry;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class QueryLogResult implements IResult, Serializable {
    private List<LogEntry> items;
    private Integer totalEntryCount;
    private String nextPageToken;

	public List<LogEntry> getItems() {
		return items;
	}

	public void setItems(List<LogEntry> items) {
		this.items = items;
	}

	public QueryLogResult withItems(List<LogEntry> items) {
		this.items = items;
		return this;
	}

	public Integer getTotalEntryCount() {
		return totalEntryCount;
	}

	public void setTotalEntryCount(Integer totalEntryCount) {
		this.totalEntryCount = totalEntryCount;
	}

	public QueryLogResult withTotalEntryCount(Integer totalEntryCount) {
		this.totalEntryCount = totalEntryCount;
		return this;
	}

	public String getNextPageToken() {
		return nextPageToken;
	}

	public void setNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
	}

	public QueryLogResult withNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
		return this;
	}

    public static QueryLogResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new QueryLogResult()
            .withItems(data.get("items") == null || data.get("items").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("items").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return LogEntry.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withTotalEntryCount(data.get("totalEntryCount") == null || data.get("totalEntryCount").isNull() ? null : data.get("totalEntryCount").intValue())
            .withNextPageToken(data.get("nextPageToken") == null || data.get("nextPageToken").isNull() ? null : data.get("nextPageToken").asText());
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
                put("totalEntryCount", getTotalEntryCount());
                put("nextPageToken", getNextPageToken());
            }}
        );
    }
}
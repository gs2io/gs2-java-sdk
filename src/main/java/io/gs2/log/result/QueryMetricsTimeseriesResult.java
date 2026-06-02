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
import io.gs2.log.model.TimeseriesValue;
import io.gs2.log.model.TimeseriesPoint;
import io.gs2.log.model.TimeseriesMetadata;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class QueryMetricsTimeseriesResult implements IResult, Serializable {
    private List<TimeseriesPoint> items;
    private TimeseriesMetadata timeseriesMetadata;

	public List<TimeseriesPoint> getItems() {
		return items;
	}

	public void setItems(List<TimeseriesPoint> items) {
		this.items = items;
	}

	public QueryMetricsTimeseriesResult withItems(List<TimeseriesPoint> items) {
		this.items = items;
		return this;
	}

	public TimeseriesMetadata getTimeseriesMetadata() {
		return timeseriesMetadata;
	}

	public void setTimeseriesMetadata(TimeseriesMetadata timeseriesMetadata) {
		this.timeseriesMetadata = timeseriesMetadata;
	}

	public QueryMetricsTimeseriesResult withTimeseriesMetadata(TimeseriesMetadata timeseriesMetadata) {
		this.timeseriesMetadata = timeseriesMetadata;
		return this;
	}

    public static QueryMetricsTimeseriesResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new QueryMetricsTimeseriesResult()
            .withItems(data.get("items") == null || data.get("items").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("items").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return TimeseriesPoint.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withTimeseriesMetadata(data.get("timeseriesMetadata") == null || data.get("timeseriesMetadata").isNull() ? null : TimeseriesMetadata.fromJson(data.get("timeseriesMetadata")));
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
                put("timeseriesMetadata", getTimeseriesMetadata() != null ? getTimeseriesMetadata().toJson() : null);
            }}
        );
    }
}
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

package io.gs2.log.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.IModel;


@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Trace implements IModel, Serializable {
	private String traceId;
	private List<LogEntry> spans;
	private Boolean truncated;
	public String getTraceId() {
		return traceId;
	}
	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}
	public Trace withTraceId(String traceId) {
		this.traceId = traceId;
		return this;
	}
	public List<LogEntry> getSpans() {
		return spans;
	}
	public void setSpans(List<LogEntry> spans) {
		this.spans = spans;
	}
	public Trace withSpans(List<LogEntry> spans) {
		this.spans = spans;
		return this;
	}
	public Boolean getTruncated() {
		return truncated;
	}
	public void setTruncated(Boolean truncated) {
		this.truncated = truncated;
	}
	public Trace withTruncated(Boolean truncated) {
		this.truncated = truncated;
		return this;
	}

    public static Trace fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Trace()
            .withTraceId(data.get("traceId") == null || data.get("traceId").isNull() ? null : data.get("traceId").asText())
            .withSpans(data.get("spans") == null || data.get("spans").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("spans").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return LogEntry.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withTruncated(data.get("truncated") == null || data.get("truncated").isNull() ? null : data.get("truncated").booleanValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("traceId", getTraceId());
                put("spans", getSpans() == null ? null :
                    getSpans().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("truncated", getTruncated());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.traceId == null) ? 0 : this.traceId.hashCode());
        result = prime * result + ((this.spans == null) ? 0 : this.spans.hashCode());
        result = prime * result + ((this.truncated == null) ? 0 : this.truncated.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		Trace other = (Trace) o;
		if (traceId == null) {
			return other.traceId == null;
		} else if (!traceId.equals(other.traceId)) {
			return false;
		}
		if (spans == null) {
			return other.spans == null;
		} else if (!spans.equals(other.spans)) {
			return false;
		}
		if (truncated == null) {
			return other.truncated == null;
		} else if (!truncated.equals(other.truncated)) {
			return false;
		}
		return true;
	}
}
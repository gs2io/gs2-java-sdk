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
public class LogEntry implements IModel, Serializable {
	private Long timestamp;
	private String status;
	private Long duration;
	private String line;
	private List<Label> labels;
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public LogEntry withTimestamp(Long timestamp) {
		this.timestamp = timestamp;
		return this;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LogEntry withStatus(String status) {
		this.status = status;
		return this;
	}
	public Long getDuration() {
		return duration;
	}
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	public LogEntry withDuration(Long duration) {
		this.duration = duration;
		return this;
	}
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	public LogEntry withLine(String line) {
		this.line = line;
		return this;
	}
	public List<Label> getLabels() {
		return labels;
	}
	public void setLabels(List<Label> labels) {
		this.labels = labels;
	}
	public LogEntry withLabels(List<Label> labels) {
		this.labels = labels;
		return this;
	}

    public static LogEntry fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new LogEntry()
            .withTimestamp(data.get("timestamp") == null || data.get("timestamp").isNull() ? null : data.get("timestamp").longValue())
            .withStatus(data.get("status") == null || data.get("status").isNull() ? null : data.get("status").asText())
            .withDuration(data.get("duration") == null || data.get("duration").isNull() ? null : data.get("duration").longValue())
            .withLine(data.get("line") == null || data.get("line").isNull() ? null : data.get("line").asText())
            .withLabels(data.get("labels") == null || data.get("labels").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("labels").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return Label.fromJson(item);
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("timestamp", getTimestamp());
                put("status", getStatus());
                put("duration", getDuration());
                put("line", getLine());
                put("labels", getLabels() == null ? null :
                    getLabels().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.timestamp == null) ? 0 : this.timestamp.hashCode());
        result = prime * result + ((this.status == null) ? 0 : this.status.hashCode());
        result = prime * result + ((this.duration == null) ? 0 : this.duration.hashCode());
        result = prime * result + ((this.line == null) ? 0 : this.line.hashCode());
        result = prime * result + ((this.labels == null) ? 0 : this.labels.hashCode());
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
		LogEntry other = (LogEntry) o;
		if (timestamp == null) {
			return other.timestamp == null;
		} else if (!timestamp.equals(other.timestamp)) {
			return false;
		}
		if (status == null) {
			return other.status == null;
		} else if (!status.equals(other.status)) {
			return false;
		}
		if (duration == null) {
			return other.duration == null;
		} else if (!duration.equals(other.duration)) {
			return false;
		}
		if (line == null) {
			return other.line == null;
		} else if (!line.equals(other.line)) {
			return false;
		}
		if (labels == null) {
			return other.labels == null;
		} else if (!labels.equals(other.labels)) {
			return false;
		}
		return true;
	}
}
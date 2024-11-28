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

package io.gs2.exchange.model;

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
public class LogRate implements IModel, Serializable {
	private Double base;
	private List<Double> logs;
	public Double getBase() {
		return base;
	}
	public void setBase(Double base) {
		this.base = base;
	}
	public LogRate withBase(Double base) {
		this.base = base;
		return this;
	}
	public List<Double> getLogs() {
		return logs;
	}
	public void setLogs(List<Double> logs) {
		this.logs = logs;
	}
	public LogRate withLogs(List<Double> logs) {
		this.logs = logs;
		return this;
	}

    public static LogRate fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new LogRate()
            .withBase(data.get("base") == null || data.get("base").isNull() ? null : data.get("base").doubleValue())
            .withLogs(data.get("logs") == null || data.get("logs").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("logs").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.doubleValue();
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("base", getBase());
                put("logs", getLogs() == null ? null :
                    getLogs().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.base == null) ? 0 : this.base.hashCode());
        result = prime * result + ((this.logs == null) ? 0 : this.logs.hashCode());
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
		LogRate other = (LogRate) o;
		if (base == null) {
			return other.base == null;
		} else if (!base.equals(other.base)) {
			return false;
		}
		if (logs == null) {
			return other.logs == null;
		} else if (!logs.equals(other.logs)) {
			return false;
		}
		return true;
	}
}
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

package io.gs2.seasonRating.model;

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
public class LogSetting implements IModel, Serializable {
	private String loggingNamespaceId;
	public String getLoggingNamespaceId() {
		return loggingNamespaceId;
	}
	public void setLoggingNamespaceId(String loggingNamespaceId) {
		this.loggingNamespaceId = loggingNamespaceId;
	}
	public LogSetting withLoggingNamespaceId(String loggingNamespaceId) {
		this.loggingNamespaceId = loggingNamespaceId;
		return this;
	}

    public static LogSetting fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new LogSetting()
            .withLoggingNamespaceId(data.get("loggingNamespaceId") == null || data.get("loggingNamespaceId").isNull() ? null : data.get("loggingNamespaceId").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("loggingNamespaceId", getLoggingNamespaceId());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.loggingNamespaceId == null) ? 0 : this.loggingNamespaceId.hashCode());
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
		LogSetting other = (LogSetting) o;
		if (loggingNamespaceId == null) {
			return other.loggingNamespaceId == null;
		} else if (!loggingNamespaceId.equals(other.loggingNamespaceId)) {
			return false;
		}
		return true;
	}
}
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

package io.gs2.adReward.model;

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
public class AppLovinMax implements IModel, Serializable {
	private String allowAdUnitId;
	private String eventKey;
	public String getAllowAdUnitId() {
		return allowAdUnitId;
	}
	public void setAllowAdUnitId(String allowAdUnitId) {
		this.allowAdUnitId = allowAdUnitId;
	}
	public AppLovinMax withAllowAdUnitId(String allowAdUnitId) {
		this.allowAdUnitId = allowAdUnitId;
		return this;
	}
	public String getEventKey() {
		return eventKey;
	}
	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}
	public AppLovinMax withEventKey(String eventKey) {
		this.eventKey = eventKey;
		return this;
	}

    public static AppLovinMax fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new AppLovinMax()
            .withAllowAdUnitId(data.get("allowAdUnitId") == null || data.get("allowAdUnitId").isNull() ? null : data.get("allowAdUnitId").asText())
            .withEventKey(data.get("eventKey") == null || data.get("eventKey").isNull() ? null : data.get("eventKey").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("allowAdUnitId", getAllowAdUnitId());
                put("eventKey", getEventKey());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.allowAdUnitId == null) ? 0 : this.allowAdUnitId.hashCode());
        result = prime * result + ((this.eventKey == null) ? 0 : this.eventKey.hashCode());
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
		AppLovinMax other = (AppLovinMax) o;
		if (allowAdUnitId == null) {
			return other.allowAdUnitId == null;
		} else if (!allowAdUnitId.equals(other.allowAdUnitId)) {
			return false;
		}
		if (eventKey == null) {
			return other.eventKey == null;
		} else if (!eventKey.equals(other.eventKey)) {
			return false;
		}
		return true;
	}
}
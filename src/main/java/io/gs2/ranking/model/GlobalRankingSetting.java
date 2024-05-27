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

package io.gs2.ranking.model;

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
public class GlobalRankingSetting implements IModel, Serializable {
	private Boolean uniqueByUserId;
	private Integer calculateIntervalMinutes;
	private FixedTiming calculateFixedTiming;
	private List<Scope> additionalScopes;
	private List<String> ignoreUserIds;
	private String generation;
	public Boolean getUniqueByUserId() {
		return uniqueByUserId;
	}
	public void setUniqueByUserId(Boolean uniqueByUserId) {
		this.uniqueByUserId = uniqueByUserId;
	}
	public GlobalRankingSetting withUniqueByUserId(Boolean uniqueByUserId) {
		this.uniqueByUserId = uniqueByUserId;
		return this;
	}
	public Integer getCalculateIntervalMinutes() {
		return calculateIntervalMinutes;
	}
	public void setCalculateIntervalMinutes(Integer calculateIntervalMinutes) {
		this.calculateIntervalMinutes = calculateIntervalMinutes;
	}
	public GlobalRankingSetting withCalculateIntervalMinutes(Integer calculateIntervalMinutes) {
		this.calculateIntervalMinutes = calculateIntervalMinutes;
		return this;
	}
	public FixedTiming getCalculateFixedTiming() {
		return calculateFixedTiming;
	}
	public void setCalculateFixedTiming(FixedTiming calculateFixedTiming) {
		this.calculateFixedTiming = calculateFixedTiming;
	}
	public GlobalRankingSetting withCalculateFixedTiming(FixedTiming calculateFixedTiming) {
		this.calculateFixedTiming = calculateFixedTiming;
		return this;
	}
	public List<Scope> getAdditionalScopes() {
		return additionalScopes;
	}
	public void setAdditionalScopes(List<Scope> additionalScopes) {
		this.additionalScopes = additionalScopes;
	}
	public GlobalRankingSetting withAdditionalScopes(List<Scope> additionalScopes) {
		this.additionalScopes = additionalScopes;
		return this;
	}
	public List<String> getIgnoreUserIds() {
		return ignoreUserIds;
	}
	public void setIgnoreUserIds(List<String> ignoreUserIds) {
		this.ignoreUserIds = ignoreUserIds;
	}
	public GlobalRankingSetting withIgnoreUserIds(List<String> ignoreUserIds) {
		this.ignoreUserIds = ignoreUserIds;
		return this;
	}
	public String getGeneration() {
		return generation;
	}
	public void setGeneration(String generation) {
		this.generation = generation;
	}
	public GlobalRankingSetting withGeneration(String generation) {
		this.generation = generation;
		return this;
	}

    public static GlobalRankingSetting fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GlobalRankingSetting()
            .withUniqueByUserId(data.get("uniqueByUserId") == null || data.get("uniqueByUserId").isNull() ? null : data.get("uniqueByUserId").booleanValue())
            .withCalculateIntervalMinutes(data.get("calculateIntervalMinutes") == null || data.get("calculateIntervalMinutes").isNull() ? null : data.get("calculateIntervalMinutes").intValue())
            .withCalculateFixedTiming(data.get("calculateFixedTiming") == null || data.get("calculateFixedTiming").isNull() ? null : FixedTiming.fromJson(data.get("calculateFixedTiming")))
            .withAdditionalScopes(data.get("additionalScopes") == null || data.get("additionalScopes").isNull() ? new ArrayList<Scope>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("additionalScopes").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return Scope.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withIgnoreUserIds(data.get("ignoreUserIds") == null || data.get("ignoreUserIds").isNull() ? new ArrayList<String>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("ignoreUserIds").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()))
            .withGeneration(data.get("generation") == null || data.get("generation").isNull() ? null : data.get("generation").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("uniqueByUserId", getUniqueByUserId());
                put("calculateIntervalMinutes", getCalculateIntervalMinutes());
                put("calculateFixedTiming", getCalculateFixedTiming() != null ? getCalculateFixedTiming().toJson() : null);
                put("additionalScopes", getAdditionalScopes() == null ? new ArrayList<Scope>() :
                    getAdditionalScopes().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("ignoreUserIds", getIgnoreUserIds() == null ? new ArrayList<String>() :
                    getIgnoreUserIds().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("generation", getGeneration());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.uniqueByUserId == null) ? 0 : this.uniqueByUserId.hashCode());
        result = prime * result + ((this.calculateIntervalMinutes == null) ? 0 : this.calculateIntervalMinutes.hashCode());
        result = prime * result + ((this.calculateFixedTiming == null) ? 0 : this.calculateFixedTiming.hashCode());
        result = prime * result + ((this.additionalScopes == null) ? 0 : this.additionalScopes.hashCode());
        result = prime * result + ((this.ignoreUserIds == null) ? 0 : this.ignoreUserIds.hashCode());
        result = prime * result + ((this.generation == null) ? 0 : this.generation.hashCode());
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
		GlobalRankingSetting other = (GlobalRankingSetting) o;
		if (uniqueByUserId == null) {
			return other.uniqueByUserId == null;
		} else if (!uniqueByUserId.equals(other.uniqueByUserId)) {
			return false;
		}
		if (calculateIntervalMinutes == null) {
			return other.calculateIntervalMinutes == null;
		} else if (!calculateIntervalMinutes.equals(other.calculateIntervalMinutes)) {
			return false;
		}
		if (calculateFixedTiming == null) {
			return other.calculateFixedTiming == null;
		} else if (!calculateFixedTiming.equals(other.calculateFixedTiming)) {
			return false;
		}
		if (additionalScopes == null) {
			return other.additionalScopes == null;
		} else if (!additionalScopes.equals(other.additionalScopes)) {
			return false;
		}
		if (ignoreUserIds == null) {
			return other.ignoreUserIds == null;
		} else if (!ignoreUserIds.equals(other.ignoreUserIds)) {
			return false;
		}
		if (generation == null) {
			return other.generation == null;
		} else if (!generation.equals(other.generation)) {
			return false;
		}
		return true;
	}
}
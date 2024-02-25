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
public class TierModel implements IModel, Serializable {
	private String metadata;
	private Integer raiseRankBonus;
	private Integer entryFee;
	private Integer minimumChangePoint;
	private Integer maximumChangePoint;
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public TierModel withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public Integer getRaiseRankBonus() {
		return raiseRankBonus;
	}
	public void setRaiseRankBonus(Integer raiseRankBonus) {
		this.raiseRankBonus = raiseRankBonus;
	}
	public TierModel withRaiseRankBonus(Integer raiseRankBonus) {
		this.raiseRankBonus = raiseRankBonus;
		return this;
	}
	public Integer getEntryFee() {
		return entryFee;
	}
	public void setEntryFee(Integer entryFee) {
		this.entryFee = entryFee;
	}
	public TierModel withEntryFee(Integer entryFee) {
		this.entryFee = entryFee;
		return this;
	}
	public Integer getMinimumChangePoint() {
		return minimumChangePoint;
	}
	public void setMinimumChangePoint(Integer minimumChangePoint) {
		this.minimumChangePoint = minimumChangePoint;
	}
	public TierModel withMinimumChangePoint(Integer minimumChangePoint) {
		this.minimumChangePoint = minimumChangePoint;
		return this;
	}
	public Integer getMaximumChangePoint() {
		return maximumChangePoint;
	}
	public void setMaximumChangePoint(Integer maximumChangePoint) {
		this.maximumChangePoint = maximumChangePoint;
	}
	public TierModel withMaximumChangePoint(Integer maximumChangePoint) {
		this.maximumChangePoint = maximumChangePoint;
		return this;
	}

    public static TierModel fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new TierModel()
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withRaiseRankBonus(data.get("raiseRankBonus") == null || data.get("raiseRankBonus").isNull() ? null : data.get("raiseRankBonus").intValue())
            .withEntryFee(data.get("entryFee") == null || data.get("entryFee").isNull() ? null : data.get("entryFee").intValue())
            .withMinimumChangePoint(data.get("minimumChangePoint") == null || data.get("minimumChangePoint").isNull() ? null : data.get("minimumChangePoint").intValue())
            .withMaximumChangePoint(data.get("maximumChangePoint") == null || data.get("maximumChangePoint").isNull() ? null : data.get("maximumChangePoint").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("metadata", getMetadata());
                put("raiseRankBonus", getRaiseRankBonus());
                put("entryFee", getEntryFee());
                put("minimumChangePoint", getMinimumChangePoint());
                put("maximumChangePoint", getMaximumChangePoint());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.raiseRankBonus == null) ? 0 : this.raiseRankBonus.hashCode());
        result = prime * result + ((this.entryFee == null) ? 0 : this.entryFee.hashCode());
        result = prime * result + ((this.minimumChangePoint == null) ? 0 : this.minimumChangePoint.hashCode());
        result = prime * result + ((this.maximumChangePoint == null) ? 0 : this.maximumChangePoint.hashCode());
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
		TierModel other = (TierModel) o;
		if (metadata == null) {
			return other.metadata == null;
		} else if (!metadata.equals(other.metadata)) {
			return false;
		}
		if (raiseRankBonus == null) {
			return other.raiseRankBonus == null;
		} else if (!raiseRankBonus.equals(other.raiseRankBonus)) {
			return false;
		}
		if (entryFee == null) {
			return other.entryFee == null;
		} else if (!entryFee.equals(other.entryFee)) {
			return false;
		}
		if (minimumChangePoint == null) {
			return other.minimumChangePoint == null;
		} else if (!minimumChangePoint.equals(other.minimumChangePoint)) {
			return false;
		}
		if (maximumChangePoint == null) {
			return other.maximumChangePoint == null;
		} else if (!maximumChangePoint.equals(other.maximumChangePoint)) {
			return false;
		}
		return true;
	}
}
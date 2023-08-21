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

package io.gs2.lottery.model;

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
public class PrizeLimit implements IModel, Serializable, Comparable<PrizeLimit> {
	private String prizeLimitId;
	private String prizeId;
	private Integer drawnCount;
	private Long createdAt;
	private Long updatedAt;
	private Long revision;
	public String getPrizeLimitId() {
		return prizeLimitId;
	}
	public void setPrizeLimitId(String prizeLimitId) {
		this.prizeLimitId = prizeLimitId;
	}
	public PrizeLimit withPrizeLimitId(String prizeLimitId) {
		this.prizeLimitId = prizeLimitId;
		return this;
	}
	public String getPrizeId() {
		return prizeId;
	}
	public void setPrizeId(String prizeId) {
		this.prizeId = prizeId;
	}
	public PrizeLimit withPrizeId(String prizeId) {
		this.prizeId = prizeId;
		return this;
	}
	public Integer getDrawnCount() {
		return drawnCount;
	}
	public void setDrawnCount(Integer drawnCount) {
		this.drawnCount = drawnCount;
	}
	public PrizeLimit withDrawnCount(Integer drawnCount) {
		this.drawnCount = drawnCount;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public PrizeLimit withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public PrizeLimit withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public PrizeLimit withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static PrizeLimit fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new PrizeLimit()
            .withPrizeLimitId(data.get("prizeLimitId") == null || data.get("prizeLimitId").isNull() ? null : data.get("prizeLimitId").asText())
            .withPrizeId(data.get("prizeId") == null || data.get("prizeId").isNull() ? null : data.get("prizeId").asText())
            .withDrawnCount(data.get("drawnCount") == null || data.get("drawnCount").isNull() ? null : data.get("drawnCount").intValue())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("prizeLimitId", getPrizeLimitId());
                put("prizeId", getPrizeId());
                put("drawnCount", getDrawnCount());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(PrizeLimit o) {
		return prizeLimitId.compareTo(o.prizeLimitId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.prizeLimitId == null) ? 0 : this.prizeLimitId.hashCode());
        result = prime * result + ((this.prizeId == null) ? 0 : this.prizeId.hashCode());
        result = prime * result + ((this.drawnCount == null) ? 0 : this.drawnCount.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
        result = prime * result + ((this.revision == null) ? 0 : this.revision.hashCode());
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
		PrizeLimit other = (PrizeLimit) o;
		if (prizeLimitId == null) {
			return other.prizeLimitId == null;
		} else if (!prizeLimitId.equals(other.prizeLimitId)) {
			return false;
		}
		if (prizeId == null) {
			return other.prizeId == null;
		} else if (!prizeId.equals(other.prizeId)) {
			return false;
		}
		if (drawnCount == null) {
			return other.drawnCount == null;
		} else if (!drawnCount.equals(other.drawnCount)) {
			return false;
		}
		if (createdAt == null) {
			return other.createdAt == null;
		} else if (!createdAt.equals(other.createdAt)) {
			return false;
		}
		if (updatedAt == null) {
			return other.updatedAt == null;
		} else if (!updatedAt.equals(other.updatedAt)) {
			return false;
		}
		if (revision == null) {
			return other.revision == null;
		} else if (!revision.equals(other.revision)) {
			return false;
		}
		return true;
	}
}
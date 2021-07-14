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
public class LotteryModelMaster implements IModel, Serializable, Comparable<LotteryModelMaster> {
	private String lotteryModelId;
	private String name;
	private String metadata;
	private String description;
	private String mode;
	private String method;
	private String prizeTableName;
	private String choicePrizeTableScriptId;
	private Long createdAt;
	private Long updatedAt;

	public String getLotteryModelId() {
		return lotteryModelId;
	}

	public void setLotteryModelId(String lotteryModelId) {
		this.lotteryModelId = lotteryModelId;
	}

	public LotteryModelMaster withLotteryModelId(String lotteryModelId) {
		this.lotteryModelId = lotteryModelId;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LotteryModelMaster withName(String name) {
		this.name = name;
		return this;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public LotteryModelMaster withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LotteryModelMaster withDescription(String description) {
		this.description = description;
		return this;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public LotteryModelMaster withMode(String mode) {
		this.mode = mode;
		return this;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public LotteryModelMaster withMethod(String method) {
		this.method = method;
		return this;
	}

	public String getPrizeTableName() {
		return prizeTableName;
	}

	public void setPrizeTableName(String prizeTableName) {
		this.prizeTableName = prizeTableName;
	}

	public LotteryModelMaster withPrizeTableName(String prizeTableName) {
		this.prizeTableName = prizeTableName;
		return this;
	}

	public String getChoicePrizeTableScriptId() {
		return choicePrizeTableScriptId;
	}

	public void setChoicePrizeTableScriptId(String choicePrizeTableScriptId) {
		this.choicePrizeTableScriptId = choicePrizeTableScriptId;
	}

	public LotteryModelMaster withChoicePrizeTableScriptId(String choicePrizeTableScriptId) {
		this.choicePrizeTableScriptId = choicePrizeTableScriptId;
		return this;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public LotteryModelMaster withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public Long getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	public LotteryModelMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public static LotteryModelMaster fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new LotteryModelMaster()
            .withLotteryModelId(data.get("lotteryModelId") == null || data.get("lotteryModelId").isNull() ? null : data.get("lotteryModelId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMode(data.get("mode") == null || data.get("mode").isNull() ? null : data.get("mode").asText())
            .withMethod(data.get("method") == null || data.get("method").isNull() ? null : data.get("method").asText())
            .withPrizeTableName(data.get("prizeTableName") == null || data.get("prizeTableName").isNull() ? null : data.get("prizeTableName").asText())
            .withChoicePrizeTableScriptId(data.get("choicePrizeTableScriptId") == null || data.get("choicePrizeTableScriptId").isNull() ? null : data.get("choicePrizeTableScriptId").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("lotteryModelId", getLotteryModelId());
                put("name", getName());
                put("metadata", getMetadata());
                put("description", getDescription());
                put("mode", getMode());
                put("method", getMethod());
                put("prizeTableName", getPrizeTableName());
                put("choicePrizeTableScriptId", getChoicePrizeTableScriptId());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
            }}
        );
    }

	@Override
	public int compareTo(LotteryModelMaster o) {
		return lotteryModelId.compareTo(o.lotteryModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.lotteryModelId == null) ? 0 : this.lotteryModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.mode == null) ? 0 : this.mode.hashCode());
        result = prime * result + ((this.method == null) ? 0 : this.method.hashCode());
        result = prime * result + ((this.prizeTableName == null) ? 0 : this.prizeTableName.hashCode());
        result = prime * result + ((this.choicePrizeTableScriptId == null) ? 0 : this.choicePrizeTableScriptId.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
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
		LotteryModelMaster other = (LotteryModelMaster) o;
		if (lotteryModelId == null) {
			return other.lotteryModelId == null;
		} else if (!lotteryModelId.equals(other.lotteryModelId)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (metadata == null) {
			return other.metadata == null;
		} else if (!metadata.equals(other.metadata)) {
			return false;
		}
		if (description == null) {
			return other.description == null;
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (mode == null) {
			return other.mode == null;
		} else if (!mode.equals(other.mode)) {
			return false;
		}
		if (method == null) {
			return other.method == null;
		} else if (!method.equals(other.method)) {
			return false;
		}
		if (prizeTableName == null) {
			return other.prizeTableName == null;
		} else if (!prizeTableName.equals(other.prizeTableName)) {
			return false;
		}
		if (choicePrizeTableScriptId == null) {
			return other.choicePrizeTableScriptId == null;
		} else if (!choicePrizeTableScriptId.equals(other.choicePrizeTableScriptId)) {
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
		return true;
	}
}
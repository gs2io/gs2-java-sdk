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
public class LotteryModel implements IModel, Serializable, Comparable<LotteryModel> {
	private String lotteryModelId;
	private String name;
	private String metadata;
	private String mode;
	private String method;
	private String prizeTableName;
	private String choicePrizeTableScriptId;
	public String getLotteryModelId() {
		return lotteryModelId;
	}
	public void setLotteryModelId(String lotteryModelId) {
		this.lotteryModelId = lotteryModelId;
	}
	public LotteryModel withLotteryModelId(String lotteryModelId) {
		this.lotteryModelId = lotteryModelId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LotteryModel withName(String name) {
		this.name = name;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public LotteryModel withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public LotteryModel withMode(String mode) {
		this.mode = mode;
		return this;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public LotteryModel withMethod(String method) {
		this.method = method;
		return this;
	}
	public String getPrizeTableName() {
		return prizeTableName;
	}
	public void setPrizeTableName(String prizeTableName) {
		this.prizeTableName = prizeTableName;
	}
	public LotteryModel withPrizeTableName(String prizeTableName) {
		this.prizeTableName = prizeTableName;
		return this;
	}
	public String getChoicePrizeTableScriptId() {
		return choicePrizeTableScriptId;
	}
	public void setChoicePrizeTableScriptId(String choicePrizeTableScriptId) {
		this.choicePrizeTableScriptId = choicePrizeTableScriptId;
	}
	public LotteryModel withChoicePrizeTableScriptId(String choicePrizeTableScriptId) {
		this.choicePrizeTableScriptId = choicePrizeTableScriptId;
		return this;
	}

    public static LotteryModel fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new LotteryModel()
            .withLotteryModelId(data.get("lotteryModelId") == null || data.get("lotteryModelId").isNull() ? null : data.get("lotteryModelId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withMode(data.get("mode") == null || data.get("mode").isNull() ? null : data.get("mode").asText())
            .withMethod(data.get("method") == null || data.get("method").isNull() ? null : data.get("method").asText())
            .withPrizeTableName(data.get("prizeTableName") == null || data.get("prizeTableName").isNull() ? null : data.get("prizeTableName").asText())
            .withChoicePrizeTableScriptId(data.get("choicePrizeTableScriptId") == null || data.get("choicePrizeTableScriptId").isNull() ? null : data.get("choicePrizeTableScriptId").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("lotteryModelId", getLotteryModelId());
                put("name", getName());
                put("metadata", getMetadata());
                put("mode", getMode());
                put("method", getMethod());
                put("prizeTableName", getPrizeTableName());
                put("choicePrizeTableScriptId", getChoicePrizeTableScriptId());
            }}
        );
    }

	@Override
	public int compareTo(LotteryModel o) {
		return lotteryModelId.compareTo(o.lotteryModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.lotteryModelId == null) ? 0 : this.lotteryModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.mode == null) ? 0 : this.mode.hashCode());
        result = prime * result + ((this.method == null) ? 0 : this.method.hashCode());
        result = prime * result + ((this.prizeTableName == null) ? 0 : this.prizeTableName.hashCode());
        result = prime * result + ((this.choicePrizeTableScriptId == null) ? 0 : this.choicePrizeTableScriptId.hashCode());
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
		LotteryModel other = (LotteryModel) o;
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
		return true;
	}
}
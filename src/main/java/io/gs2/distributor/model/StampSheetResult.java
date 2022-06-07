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

package io.gs2.distributor.model;

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
public class StampSheetResult implements IModel, Serializable, Comparable<StampSheetResult> {
	private String stampSheetResultId;
	private String userId;
	private String transactionId;
	private List<String> taskResults;
	private String sheetResult;
	private String nextTransactionId;
	private Long createdAt;

	public String getStampSheetResultId() {
		return stampSheetResultId;
	}

	public void setStampSheetResultId(String stampSheetResultId) {
		this.stampSheetResultId = stampSheetResultId;
	}

	public StampSheetResult withStampSheetResultId(String stampSheetResultId) {
		this.stampSheetResultId = stampSheetResultId;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public StampSheetResult withUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public StampSheetResult withTransactionId(String transactionId) {
		this.transactionId = transactionId;
		return this;
	}

	public List<String> getTaskResults() {
		return taskResults;
	}

	public void setTaskResults(List<String> taskResults) {
		this.taskResults = taskResults;
	}

	public StampSheetResult withTaskResults(List<String> taskResults) {
		this.taskResults = taskResults;
		return this;
	}

	public String getSheetResult() {
		return sheetResult;
	}

	public void setSheetResult(String sheetResult) {
		this.sheetResult = sheetResult;
	}

	public StampSheetResult withSheetResult(String sheetResult) {
		this.sheetResult = sheetResult;
		return this;
	}

	public String getNextTransactionId() {
		return nextTransactionId;
	}

	public void setNextTransactionId(String nextTransactionId) {
		this.nextTransactionId = nextTransactionId;
	}

	public StampSheetResult withNextTransactionId(String nextTransactionId) {
		this.nextTransactionId = nextTransactionId;
		return this;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public StampSheetResult withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

    public static StampSheetResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new StampSheetResult()
            .withStampSheetResultId(data.get("stampSheetResultId") == null || data.get("stampSheetResultId").isNull() ? null : data.get("stampSheetResultId").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withTransactionId(data.get("transactionId") == null || data.get("transactionId").isNull() ? null : data.get("transactionId").asText())
            .withTaskResults(data.get("taskResults") == null || data.get("taskResults").isNull() ? new ArrayList<String>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("taskResults").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()))
            .withSheetResult(data.get("sheetResult") == null || data.get("sheetResult").isNull() ? null : data.get("sheetResult").asText())
            .withNextTransactionId(data.get("nextTransactionId") == null || data.get("nextTransactionId").isNull() ? null : data.get("nextTransactionId").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("stampSheetResultId", getStampSheetResultId());
                put("userId", getUserId());
                put("transactionId", getTransactionId());
                put("taskResults", getTaskResults() == null ? new ArrayList<String>() :
                    getTaskResults().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("sheetResult", getSheetResult());
                put("nextTransactionId", getNextTransactionId());
                put("createdAt", getCreatedAt());
            }}
        );
    }

	@Override
	public int compareTo(StampSheetResult o) {
		return stampSheetResultId.compareTo(o.stampSheetResultId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.stampSheetResultId == null) ? 0 : this.stampSheetResultId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.transactionId == null) ? 0 : this.transactionId.hashCode());
        result = prime * result + ((this.taskResults == null) ? 0 : this.taskResults.hashCode());
        result = prime * result + ((this.sheetResult == null) ? 0 : this.sheetResult.hashCode());
        result = prime * result + ((this.nextTransactionId == null) ? 0 : this.nextTransactionId.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
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
		StampSheetResult other = (StampSheetResult) o;
		if (stampSheetResultId == null) {
			return other.stampSheetResultId == null;
		} else if (!stampSheetResultId.equals(other.stampSheetResultId)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (transactionId == null) {
			return other.transactionId == null;
		} else if (!transactionId.equals(other.transactionId)) {
			return false;
		}
		if (taskResults == null) {
			return other.taskResults == null;
		} else if (!taskResults.equals(other.taskResults)) {
			return false;
		}
		if (sheetResult == null) {
			return other.sheetResult == null;
		} else if (!sheetResult.equals(other.sheetResult)) {
			return false;
		}
		if (nextTransactionId == null) {
			return other.nextTransactionId == null;
		} else if (!nextTransactionId.equals(other.nextTransactionId)) {
			return false;
		}
		if (createdAt == null) {
			return other.createdAt == null;
		} else if (!createdAt.equals(other.createdAt)) {
			return false;
		}
		return true;
	}
}
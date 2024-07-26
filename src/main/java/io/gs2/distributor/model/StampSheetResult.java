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
	private List<VerifyAction> verifyTaskRequests;
	private List<ConsumeAction> taskRequests;
	private AcquireAction sheetRequest;
	private List<Integer> verifyTaskResultCodes;
	private List<String> verifyTaskResults;
	private List<Integer> taskResultCodes;
	private List<String> taskResults;
	private Integer sheetResultCode;
	private String sheetResult;
	private String nextTransactionId;
	private Long createdAt;
	private Long revision;
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
	public List<VerifyAction> getVerifyTaskRequests() {
		return verifyTaskRequests;
	}
	public void setVerifyTaskRequests(List<VerifyAction> verifyTaskRequests) {
		this.verifyTaskRequests = verifyTaskRequests;
	}
	public StampSheetResult withVerifyTaskRequests(List<VerifyAction> verifyTaskRequests) {
		this.verifyTaskRequests = verifyTaskRequests;
		return this;
	}
	public List<ConsumeAction> getTaskRequests() {
		return taskRequests;
	}
	public void setTaskRequests(List<ConsumeAction> taskRequests) {
		this.taskRequests = taskRequests;
	}
	public StampSheetResult withTaskRequests(List<ConsumeAction> taskRequests) {
		this.taskRequests = taskRequests;
		return this;
	}
	public AcquireAction getSheetRequest() {
		return sheetRequest;
	}
	public void setSheetRequest(AcquireAction sheetRequest) {
		this.sheetRequest = sheetRequest;
	}
	public StampSheetResult withSheetRequest(AcquireAction sheetRequest) {
		this.sheetRequest = sheetRequest;
		return this;
	}
	public List<Integer> getVerifyTaskResultCodes() {
		return verifyTaskResultCodes;
	}
	public void setVerifyTaskResultCodes(List<Integer> verifyTaskResultCodes) {
		this.verifyTaskResultCodes = verifyTaskResultCodes;
	}
	public StampSheetResult withVerifyTaskResultCodes(List<Integer> verifyTaskResultCodes) {
		this.verifyTaskResultCodes = verifyTaskResultCodes;
		return this;
	}
	public List<String> getVerifyTaskResults() {
		return verifyTaskResults;
	}
	public void setVerifyTaskResults(List<String> verifyTaskResults) {
		this.verifyTaskResults = verifyTaskResults;
	}
	public StampSheetResult withVerifyTaskResults(List<String> verifyTaskResults) {
		this.verifyTaskResults = verifyTaskResults;
		return this;
	}
	public List<Integer> getTaskResultCodes() {
		return taskResultCodes;
	}
	public void setTaskResultCodes(List<Integer> taskResultCodes) {
		this.taskResultCodes = taskResultCodes;
	}
	public StampSheetResult withTaskResultCodes(List<Integer> taskResultCodes) {
		this.taskResultCodes = taskResultCodes;
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
	public Integer getSheetResultCode() {
		return sheetResultCode;
	}
	public void setSheetResultCode(Integer sheetResultCode) {
		this.sheetResultCode = sheetResultCode;
	}
	public StampSheetResult withSheetResultCode(Integer sheetResultCode) {
		this.sheetResultCode = sheetResultCode;
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
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public StampSheetResult withRevision(Long revision) {
		this.revision = revision;
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
            .withVerifyTaskRequests(data.get("verifyTaskRequests") == null || data.get("verifyTaskRequests").isNull() ? new ArrayList<VerifyAction>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("verifyTaskRequests").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return VerifyAction.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withTaskRequests(data.get("taskRequests") == null || data.get("taskRequests").isNull() ? new ArrayList<ConsumeAction>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("taskRequests").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return ConsumeAction.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withSheetRequest(data.get("sheetRequest") == null || data.get("sheetRequest").isNull() ? null : AcquireAction.fromJson(data.get("sheetRequest")))
            .withVerifyTaskResultCodes(data.get("verifyTaskResultCodes") == null || data.get("verifyTaskResultCodes").isNull() ? new ArrayList<Integer>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("verifyTaskResultCodes").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.intValue();
                }
            ).collect(Collectors.toList()))
            .withVerifyTaskResults(data.get("verifyTaskResults") == null || data.get("verifyTaskResults").isNull() ? new ArrayList<String>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("verifyTaskResults").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()))
            .withTaskResultCodes(data.get("taskResultCodes") == null || data.get("taskResultCodes").isNull() ? new ArrayList<Integer>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("taskResultCodes").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.intValue();
                }
            ).collect(Collectors.toList()))
            .withTaskResults(data.get("taskResults") == null || data.get("taskResults").isNull() ? new ArrayList<String>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("taskResults").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()))
            .withSheetResultCode(data.get("sheetResultCode") == null || data.get("sheetResultCode").isNull() ? null : data.get("sheetResultCode").intValue())
            .withSheetResult(data.get("sheetResult") == null || data.get("sheetResult").isNull() ? null : data.get("sheetResult").asText())
            .withNextTransactionId(data.get("nextTransactionId") == null || data.get("nextTransactionId").isNull() ? null : data.get("nextTransactionId").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("stampSheetResultId", getStampSheetResultId());
                put("userId", getUserId());
                put("transactionId", getTransactionId());
                put("verifyTaskRequests", getVerifyTaskRequests() == null ? new ArrayList<VerifyAction>() :
                    getVerifyTaskRequests().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("taskRequests", getTaskRequests() == null ? new ArrayList<ConsumeAction>() :
                    getTaskRequests().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("sheetRequest", getSheetRequest() != null ? getSheetRequest().toJson() : null);
                put("verifyTaskResultCodes", getVerifyTaskResultCodes() == null ? new ArrayList<Integer>() :
                    getVerifyTaskResultCodes().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("verifyTaskResults", getVerifyTaskResults() == null ? new ArrayList<String>() :
                    getVerifyTaskResults().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("taskResultCodes", getTaskResultCodes() == null ? new ArrayList<Integer>() :
                    getTaskResultCodes().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("taskResults", getTaskResults() == null ? new ArrayList<String>() :
                    getTaskResults().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("sheetResultCode", getSheetResultCode());
                put("sheetResult", getSheetResult());
                put("nextTransactionId", getNextTransactionId());
                put("createdAt", getCreatedAt());
                put("revision", getRevision());
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
        result = prime * result + ((this.verifyTaskRequests == null) ? 0 : this.verifyTaskRequests.hashCode());
        result = prime * result + ((this.taskRequests == null) ? 0 : this.taskRequests.hashCode());
        result = prime * result + ((this.sheetRequest == null) ? 0 : this.sheetRequest.hashCode());
        result = prime * result + ((this.verifyTaskResultCodes == null) ? 0 : this.verifyTaskResultCodes.hashCode());
        result = prime * result + ((this.verifyTaskResults == null) ? 0 : this.verifyTaskResults.hashCode());
        result = prime * result + ((this.taskResultCodes == null) ? 0 : this.taskResultCodes.hashCode());
        result = prime * result + ((this.taskResults == null) ? 0 : this.taskResults.hashCode());
        result = prime * result + ((this.sheetResultCode == null) ? 0 : this.sheetResultCode.hashCode());
        result = prime * result + ((this.sheetResult == null) ? 0 : this.sheetResult.hashCode());
        result = prime * result + ((this.nextTransactionId == null) ? 0 : this.nextTransactionId.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
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
		if (verifyTaskRequests == null) {
			return other.verifyTaskRequests == null;
		} else if (!verifyTaskRequests.equals(other.verifyTaskRequests)) {
			return false;
		}
		if (taskRequests == null) {
			return other.taskRequests == null;
		} else if (!taskRequests.equals(other.taskRequests)) {
			return false;
		}
		if (sheetRequest == null) {
			return other.sheetRequest == null;
		} else if (!sheetRequest.equals(other.sheetRequest)) {
			return false;
		}
		if (verifyTaskResultCodes == null) {
			return other.verifyTaskResultCodes == null;
		} else if (!verifyTaskResultCodes.equals(other.verifyTaskResultCodes)) {
			return false;
		}
		if (verifyTaskResults == null) {
			return other.verifyTaskResults == null;
		} else if (!verifyTaskResults.equals(other.verifyTaskResults)) {
			return false;
		}
		if (taskResultCodes == null) {
			return other.taskResultCodes == null;
		} else if (!taskResultCodes.equals(other.taskResultCodes)) {
			return false;
		}
		if (taskResults == null) {
			return other.taskResults == null;
		} else if (!taskResults.equals(other.taskResults)) {
			return false;
		}
		if (sheetResultCode == null) {
			return other.sheetResultCode == null;
		} else if (!sheetResultCode.equals(other.sheetResultCode)) {
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
		if (revision == null) {
			return other.revision == null;
		} else if (!revision.equals(other.revision)) {
			return false;
		}
		return true;
	}
}
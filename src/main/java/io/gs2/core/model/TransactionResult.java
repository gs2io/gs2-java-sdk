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

package io.gs2.core.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;


@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class TransactionResult implements IModel, Serializable {
	private String transactionId;
	private List<VerifyActionResult> verifyResults;
	private List<ConsumeActionResult> consumeResults;
	private List<AcquireActionResult> acquireResults;
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public TransactionResult withTransactionId(String transactionId) {
		this.transactionId = transactionId;
		return this;
	}
	public List<VerifyActionResult> getVerifyResults() {
		return verifyResults;
	}
	public void setVerifyResults(List<VerifyActionResult> verifyResults) {
		this.verifyResults = verifyResults;
	}
	public TransactionResult withVerifyResults(List<VerifyActionResult> verifyResults) {
		this.verifyResults = verifyResults;
		return this;
	}
	public List<ConsumeActionResult> getConsumeResults() {
		return consumeResults;
	}
	public void setConsumeResults(List<ConsumeActionResult> consumeResults) {
		this.consumeResults = consumeResults;
	}
	public TransactionResult withConsumeResults(List<ConsumeActionResult> consumeResults) {
		this.consumeResults = consumeResults;
		return this;
	}
	public List<AcquireActionResult> getAcquireResults() {
		return acquireResults;
	}
	public void setAcquireResults(List<AcquireActionResult> acquireResults) {
		this.acquireResults = acquireResults;
	}
	public TransactionResult withAcquireResults(List<AcquireActionResult> acquireResults) {
		this.acquireResults = acquireResults;
		return this;
	}

    public static TransactionResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new TransactionResult()
            .withTransactionId(data.get("transactionId") == null || data.get("transactionId").isNull() ? null : data.get("transactionId").asText())
            .withVerifyResults(data.get("verifyResults") == null || data.get("verifyResults").isNull() ? new ArrayList<VerifyActionResult>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("verifyResults").elements(), Spliterator.NONNULL), false).map(item -> {
                    return io.gs2.core.model.VerifyActionResult.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withConsumeResults(data.get("consumeResults") == null || data.get("consumeResults").isNull() ? new ArrayList<ConsumeActionResult>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("consumeResults").elements(), Spliterator.NONNULL), false).map(item -> {
                    return io.gs2.core.model.ConsumeActionResult.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withAcquireResults(data.get("acquireResults") == null || data.get("acquireResults").isNull() ? new ArrayList<AcquireActionResult>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("acquireResults").elements(), Spliterator.NONNULL), false).map(item -> {
                    return io.gs2.core.model.AcquireActionResult.fromJson(item);
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("transactionId", getTransactionId());
                put("verifyResults", getVerifyResults() == null ? new ArrayList<VerifyActionResult>() :
                    getVerifyResults().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("consumeResults", getConsumeResults() == null ? new ArrayList<ConsumeActionResult>() :
                    getConsumeResults().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("acquireResults", getAcquireResults() == null ? new ArrayList<AcquireActionResult>() :
                    getAcquireResults().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.transactionId == null) ? 0 : this.transactionId.hashCode());
        result = prime * result + ((this.verifyResults == null) ? 0 : this.verifyResults.hashCode());
        result = prime * result + ((this.consumeResults == null) ? 0 : this.consumeResults.hashCode());
        result = prime * result + ((this.acquireResults == null) ? 0 : this.acquireResults.hashCode());
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
		TransactionResult other = (TransactionResult) o;
		if (transactionId == null) {
			return other.transactionId == null;
		} else if (!transactionId.equals(other.transactionId)) {
			return false;
		}
		if (verifyResults == null) {
			return other.verifyResults == null;
		} else if (!verifyResults.equals(other.verifyResults)) {
			return false;
		}
		if (consumeResults == null) {
			return other.consumeResults == null;
		} else if (!consumeResults.equals(other.consumeResults)) {
			return false;
		}
		if (acquireResults == null) {
			return other.acquireResults == null;
		} else if (!acquireResults.equals(other.acquireResults)) {
			return false;
		}
		return true;
	}
}
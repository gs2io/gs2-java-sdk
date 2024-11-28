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

package io.gs2.experience.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.experience.model.*;
import io.gs2.experience.model.AcquireAction;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class MultiplyAcquireActionsByUserIdResult implements IResult, Serializable {
    private List<AcquireAction> items;
    private String transactionId;
    private String stampSheet;
    private String stampSheetEncryptionKeyId;
    private Boolean autoRunStampSheet;
    private Boolean atomicCommit;
    private String transaction;
    private io.gs2.core.model.TransactionResult transactionResult;

	public List<AcquireAction> getItems() {
		return items;
	}

	public void setItems(List<AcquireAction> items) {
		this.items = items;
	}

	public MultiplyAcquireActionsByUserIdResult withItems(List<AcquireAction> items) {
		this.items = items;
		return this;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public MultiplyAcquireActionsByUserIdResult withTransactionId(String transactionId) {
		this.transactionId = transactionId;
		return this;
	}

	public String getStampSheet() {
		return stampSheet;
	}

	public void setStampSheet(String stampSheet) {
		this.stampSheet = stampSheet;
	}

	public MultiplyAcquireActionsByUserIdResult withStampSheet(String stampSheet) {
		this.stampSheet = stampSheet;
		return this;
	}

	public String getStampSheetEncryptionKeyId() {
		return stampSheetEncryptionKeyId;
	}

	public void setStampSheetEncryptionKeyId(String stampSheetEncryptionKeyId) {
		this.stampSheetEncryptionKeyId = stampSheetEncryptionKeyId;
	}

	public MultiplyAcquireActionsByUserIdResult withStampSheetEncryptionKeyId(String stampSheetEncryptionKeyId) {
		this.stampSheetEncryptionKeyId = stampSheetEncryptionKeyId;
		return this;
	}

	public Boolean getAutoRunStampSheet() {
		return autoRunStampSheet;
	}

	public void setAutoRunStampSheet(Boolean autoRunStampSheet) {
		this.autoRunStampSheet = autoRunStampSheet;
	}

	public MultiplyAcquireActionsByUserIdResult withAutoRunStampSheet(Boolean autoRunStampSheet) {
		this.autoRunStampSheet = autoRunStampSheet;
		return this;
	}

	public Boolean getAtomicCommit() {
		return atomicCommit;
	}

	public void setAtomicCommit(Boolean atomicCommit) {
		this.atomicCommit = atomicCommit;
	}

	public MultiplyAcquireActionsByUserIdResult withAtomicCommit(Boolean atomicCommit) {
		this.atomicCommit = atomicCommit;
		return this;
	}

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	public MultiplyAcquireActionsByUserIdResult withTransaction(String transaction) {
		this.transaction = transaction;
		return this;
	}

	public io.gs2.core.model.TransactionResult getTransactionResult() {
		return transactionResult;
	}

	public void setTransactionResult(io.gs2.core.model.TransactionResult transactionResult) {
		this.transactionResult = transactionResult;
	}

	public MultiplyAcquireActionsByUserIdResult withTransactionResult(io.gs2.core.model.TransactionResult transactionResult) {
		this.transactionResult = transactionResult;
		return this;
	}

    public static MultiplyAcquireActionsByUserIdResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new MultiplyAcquireActionsByUserIdResult()
            .withItems(data.get("items") == null || data.get("items").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("items").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return AcquireAction.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withTransactionId(data.get("transactionId") == null || data.get("transactionId").isNull() ? null : data.get("transactionId").asText())
            .withStampSheet(data.get("stampSheet") == null || data.get("stampSheet").isNull() ? null : data.get("stampSheet").asText())
            .withStampSheetEncryptionKeyId(data.get("stampSheetEncryptionKeyId") == null || data.get("stampSheetEncryptionKeyId").isNull() ? null : data.get("stampSheetEncryptionKeyId").asText())
            .withAutoRunStampSheet(data.get("autoRunStampSheet") == null || data.get("autoRunStampSheet").isNull() ? null : data.get("autoRunStampSheet").booleanValue())
            .withAtomicCommit(data.get("atomicCommit") == null || data.get("atomicCommit").isNull() ? null : data.get("atomicCommit").booleanValue())
            .withTransaction(data.get("transaction") == null || data.get("transaction").isNull() ? null : data.get("transaction").asText())
            .withTransactionResult(data.get("transactionResult") == null || data.get("transactionResult").isNull() ? null : io.gs2.core.model.TransactionResult.fromJson(data.get("transactionResult")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("items", getItems() == null ? null :
                    getItems().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("transactionId", getTransactionId());
                put("stampSheet", getStampSheet());
                put("stampSheetEncryptionKeyId", getStampSheetEncryptionKeyId());
                put("autoRunStampSheet", getAutoRunStampSheet());
                put("atomicCommit", getAtomicCommit());
                put("transaction", getTransaction());
                put("transactionResult", getTransactionResult() != null ? getTransactionResult().toJson() : null);
            }}
        );
    }
}
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

package io.gs2.ranking2.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.ranking2.model.*;
import io.gs2.ranking2.model.AcquireAction;
import io.gs2.ranking2.model.RankingReward;
import io.gs2.ranking2.model.GlobalRankingModel;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class ReceiveGlobalRankingReceivedRewardResult implements IResult, Serializable {
    private GlobalRankingModel item;
    private List<AcquireAction> acquireActions;
    private String transactionId;
    private String stampSheet;
    private String stampSheetEncryptionKeyId;
    private Boolean autoRunStampSheet;
    private Boolean atomicCommit;
    private String transaction;
    private io.gs2.core.model.TransactionResult transactionResult;

	public GlobalRankingModel getItem() {
		return item;
	}

	public void setItem(GlobalRankingModel item) {
		this.item = item;
	}

	public ReceiveGlobalRankingReceivedRewardResult withItem(GlobalRankingModel item) {
		this.item = item;
		return this;
	}

	public List<AcquireAction> getAcquireActions() {
		return acquireActions;
	}

	public void setAcquireActions(List<AcquireAction> acquireActions) {
		this.acquireActions = acquireActions;
	}

	public ReceiveGlobalRankingReceivedRewardResult withAcquireActions(List<AcquireAction> acquireActions) {
		this.acquireActions = acquireActions;
		return this;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public ReceiveGlobalRankingReceivedRewardResult withTransactionId(String transactionId) {
		this.transactionId = transactionId;
		return this;
	}

	public String getStampSheet() {
		return stampSheet;
	}

	public void setStampSheet(String stampSheet) {
		this.stampSheet = stampSheet;
	}

	public ReceiveGlobalRankingReceivedRewardResult withStampSheet(String stampSheet) {
		this.stampSheet = stampSheet;
		return this;
	}

	public String getStampSheetEncryptionKeyId() {
		return stampSheetEncryptionKeyId;
	}

	public void setStampSheetEncryptionKeyId(String stampSheetEncryptionKeyId) {
		this.stampSheetEncryptionKeyId = stampSheetEncryptionKeyId;
	}

	public ReceiveGlobalRankingReceivedRewardResult withStampSheetEncryptionKeyId(String stampSheetEncryptionKeyId) {
		this.stampSheetEncryptionKeyId = stampSheetEncryptionKeyId;
		return this;
	}

	public Boolean getAutoRunStampSheet() {
		return autoRunStampSheet;
	}

	public void setAutoRunStampSheet(Boolean autoRunStampSheet) {
		this.autoRunStampSheet = autoRunStampSheet;
	}

	public ReceiveGlobalRankingReceivedRewardResult withAutoRunStampSheet(Boolean autoRunStampSheet) {
		this.autoRunStampSheet = autoRunStampSheet;
		return this;
	}

	public Boolean getAtomicCommit() {
		return atomicCommit;
	}

	public void setAtomicCommit(Boolean atomicCommit) {
		this.atomicCommit = atomicCommit;
	}

	public ReceiveGlobalRankingReceivedRewardResult withAtomicCommit(Boolean atomicCommit) {
		this.atomicCommit = atomicCommit;
		return this;
	}

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	public ReceiveGlobalRankingReceivedRewardResult withTransaction(String transaction) {
		this.transaction = transaction;
		return this;
	}

	public io.gs2.core.model.TransactionResult getTransactionResult() {
		return transactionResult;
	}

	public void setTransactionResult(io.gs2.core.model.TransactionResult transactionResult) {
		this.transactionResult = transactionResult;
	}

	public ReceiveGlobalRankingReceivedRewardResult withTransactionResult(io.gs2.core.model.TransactionResult transactionResult) {
		this.transactionResult = transactionResult;
		return this;
	}

    public static ReceiveGlobalRankingReceivedRewardResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new ReceiveGlobalRankingReceivedRewardResult()
            .withItem(data.get("item") == null || data.get("item").isNull() ? null : GlobalRankingModel.fromJson(data.get("item")))
            .withAcquireActions(data.get("acquireActions") == null || data.get("acquireActions").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("acquireActions").elements(), Spliterator.NONNULL), false).map(item -> {
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
                put("item", getItem() != null ? getItem().toJson() : null);
                put("acquireActions", getAcquireActions() == null ? null :
                    getAcquireActions().stream().map(item -> {
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
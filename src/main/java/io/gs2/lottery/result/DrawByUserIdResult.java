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

package io.gs2.lottery.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.lottery.model.*;
import io.gs2.lottery.model.AcquireAction;
import io.gs2.lottery.model.DrawnPrize;
import io.gs2.lottery.model.BoxItem;
import io.gs2.lottery.model.BoxItems;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class DrawByUserIdResult implements IResult, Serializable {
    private List<DrawnPrize> items;
    private BoxItems boxItems;
    private String transactionId;
    private String stampSheet;
    private String stampSheetEncryptionKeyId;
    private Boolean autoRunStampSheet;
    private Boolean atomicCommit;
    private String transaction;
    private io.gs2.core.model.TransactionResult transactionResult;

	public List<DrawnPrize> getItems() {
		return items;
	}

	public void setItems(List<DrawnPrize> items) {
		this.items = items;
	}

	public DrawByUserIdResult withItems(List<DrawnPrize> items) {
		this.items = items;
		return this;
	}

	public BoxItems getBoxItems() {
		return boxItems;
	}

	public void setBoxItems(BoxItems boxItems) {
		this.boxItems = boxItems;
	}

	public DrawByUserIdResult withBoxItems(BoxItems boxItems) {
		this.boxItems = boxItems;
		return this;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public DrawByUserIdResult withTransactionId(String transactionId) {
		this.transactionId = transactionId;
		return this;
	}

	public String getStampSheet() {
		return stampSheet;
	}

	public void setStampSheet(String stampSheet) {
		this.stampSheet = stampSheet;
	}

	public DrawByUserIdResult withStampSheet(String stampSheet) {
		this.stampSheet = stampSheet;
		return this;
	}

	public String getStampSheetEncryptionKeyId() {
		return stampSheetEncryptionKeyId;
	}

	public void setStampSheetEncryptionKeyId(String stampSheetEncryptionKeyId) {
		this.stampSheetEncryptionKeyId = stampSheetEncryptionKeyId;
	}

	public DrawByUserIdResult withStampSheetEncryptionKeyId(String stampSheetEncryptionKeyId) {
		this.stampSheetEncryptionKeyId = stampSheetEncryptionKeyId;
		return this;
	}

	public Boolean getAutoRunStampSheet() {
		return autoRunStampSheet;
	}

	public void setAutoRunStampSheet(Boolean autoRunStampSheet) {
		this.autoRunStampSheet = autoRunStampSheet;
	}

	public DrawByUserIdResult withAutoRunStampSheet(Boolean autoRunStampSheet) {
		this.autoRunStampSheet = autoRunStampSheet;
		return this;
	}

	public Boolean getAtomicCommit() {
		return atomicCommit;
	}

	public void setAtomicCommit(Boolean atomicCommit) {
		this.atomicCommit = atomicCommit;
	}

	public DrawByUserIdResult withAtomicCommit(Boolean atomicCommit) {
		this.atomicCommit = atomicCommit;
		return this;
	}

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	public DrawByUserIdResult withTransaction(String transaction) {
		this.transaction = transaction;
		return this;
	}

	public io.gs2.core.model.TransactionResult getTransactionResult() {
		return transactionResult;
	}

	public void setTransactionResult(io.gs2.core.model.TransactionResult transactionResult) {
		this.transactionResult = transactionResult;
	}

	public DrawByUserIdResult withTransactionResult(io.gs2.core.model.TransactionResult transactionResult) {
		this.transactionResult = transactionResult;
		return this;
	}

    public static DrawByUserIdResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new DrawByUserIdResult()
            .withItems(data.get("items") == null || data.get("items").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("items").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return DrawnPrize.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withBoxItems(data.get("boxItems") == null || data.get("boxItems").isNull() ? null : BoxItems.fromJson(data.get("boxItems")))
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
                put("boxItems", getBoxItems() != null ? getBoxItems().toJson() : null);
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
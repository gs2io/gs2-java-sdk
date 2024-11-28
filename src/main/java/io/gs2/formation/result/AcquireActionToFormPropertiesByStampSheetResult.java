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

package io.gs2.formation.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.formation.model.*;
import io.gs2.formation.model.Slot;
import io.gs2.formation.model.Form;
import io.gs2.formation.model.Mold;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class AcquireActionToFormPropertiesByStampSheetResult implements IResult, Serializable {
    private Form item;
    private Mold mold;
    private String transactionId;
    private String stampSheet;
    private String stampSheetEncryptionKeyId;
    private Boolean autoRunStampSheet;
    private Boolean atomicCommit;
    private String transaction;
    private io.gs2.core.model.TransactionResult transactionResult;

	public Form getItem() {
		return item;
	}

	public void setItem(Form item) {
		this.item = item;
	}

	public AcquireActionToFormPropertiesByStampSheetResult withItem(Form item) {
		this.item = item;
		return this;
	}

	public Mold getMold() {
		return mold;
	}

	public void setMold(Mold mold) {
		this.mold = mold;
	}

	public AcquireActionToFormPropertiesByStampSheetResult withMold(Mold mold) {
		this.mold = mold;
		return this;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public AcquireActionToFormPropertiesByStampSheetResult withTransactionId(String transactionId) {
		this.transactionId = transactionId;
		return this;
	}

	public String getStampSheet() {
		return stampSheet;
	}

	public void setStampSheet(String stampSheet) {
		this.stampSheet = stampSheet;
	}

	public AcquireActionToFormPropertiesByStampSheetResult withStampSheet(String stampSheet) {
		this.stampSheet = stampSheet;
		return this;
	}

	public String getStampSheetEncryptionKeyId() {
		return stampSheetEncryptionKeyId;
	}

	public void setStampSheetEncryptionKeyId(String stampSheetEncryptionKeyId) {
		this.stampSheetEncryptionKeyId = stampSheetEncryptionKeyId;
	}

	public AcquireActionToFormPropertiesByStampSheetResult withStampSheetEncryptionKeyId(String stampSheetEncryptionKeyId) {
		this.stampSheetEncryptionKeyId = stampSheetEncryptionKeyId;
		return this;
	}

	public Boolean getAutoRunStampSheet() {
		return autoRunStampSheet;
	}

	public void setAutoRunStampSheet(Boolean autoRunStampSheet) {
		this.autoRunStampSheet = autoRunStampSheet;
	}

	public AcquireActionToFormPropertiesByStampSheetResult withAutoRunStampSheet(Boolean autoRunStampSheet) {
		this.autoRunStampSheet = autoRunStampSheet;
		return this;
	}

	public Boolean getAtomicCommit() {
		return atomicCommit;
	}

	public void setAtomicCommit(Boolean atomicCommit) {
		this.atomicCommit = atomicCommit;
	}

	public AcquireActionToFormPropertiesByStampSheetResult withAtomicCommit(Boolean atomicCommit) {
		this.atomicCommit = atomicCommit;
		return this;
	}

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	public AcquireActionToFormPropertiesByStampSheetResult withTransaction(String transaction) {
		this.transaction = transaction;
		return this;
	}

	public io.gs2.core.model.TransactionResult getTransactionResult() {
		return transactionResult;
	}

	public void setTransactionResult(io.gs2.core.model.TransactionResult transactionResult) {
		this.transactionResult = transactionResult;
	}

	public AcquireActionToFormPropertiesByStampSheetResult withTransactionResult(io.gs2.core.model.TransactionResult transactionResult) {
		this.transactionResult = transactionResult;
		return this;
	}

    public static AcquireActionToFormPropertiesByStampSheetResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new AcquireActionToFormPropertiesByStampSheetResult()
            .withItem(data.get("item") == null || data.get("item").isNull() ? null : Form.fromJson(data.get("item")))
            .withMold(data.get("mold") == null || data.get("mold").isNull() ? null : Mold.fromJson(data.get("mold")))
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
                put("mold", getMold() != null ? getMold().toJson() : null);
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
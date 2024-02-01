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

package io.gs2.exchange.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.exchange.model.*;
import io.gs2.exchange.model.Config;
import io.gs2.exchange.model.Await;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class AcquireResult implements IResult, Serializable {
    private Await item;
    private String transactionId;
    private String stampSheet;
    private String stampSheetEncryptionKeyId;
    private Boolean autoRunStampSheet;

	public Await getItem() {
		return item;
	}

	public void setItem(Await item) {
		this.item = item;
	}

	public AcquireResult withItem(Await item) {
		this.item = item;
		return this;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public AcquireResult withTransactionId(String transactionId) {
		this.transactionId = transactionId;
		return this;
	}

	public String getStampSheet() {
		return stampSheet;
	}

	public void setStampSheet(String stampSheet) {
		this.stampSheet = stampSheet;
	}

	public AcquireResult withStampSheet(String stampSheet) {
		this.stampSheet = stampSheet;
		return this;
	}

	public String getStampSheetEncryptionKeyId() {
		return stampSheetEncryptionKeyId;
	}

	public void setStampSheetEncryptionKeyId(String stampSheetEncryptionKeyId) {
		this.stampSheetEncryptionKeyId = stampSheetEncryptionKeyId;
	}

	public AcquireResult withStampSheetEncryptionKeyId(String stampSheetEncryptionKeyId) {
		this.stampSheetEncryptionKeyId = stampSheetEncryptionKeyId;
		return this;
	}

	public Boolean getAutoRunStampSheet() {
		return autoRunStampSheet;
	}

	public void setAutoRunStampSheet(Boolean autoRunStampSheet) {
		this.autoRunStampSheet = autoRunStampSheet;
	}

	public AcquireResult withAutoRunStampSheet(Boolean autoRunStampSheet) {
		this.autoRunStampSheet = autoRunStampSheet;
		return this;
	}

    public static AcquireResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new AcquireResult()
            .withItem(data.get("item") == null || data.get("item").isNull() ? null : Await.fromJson(data.get("item")))
            .withTransactionId(data.get("transactionId") == null || data.get("transactionId").isNull() ? null : data.get("transactionId").asText())
            .withStampSheet(data.get("stampSheet") == null || data.get("stampSheet").isNull() ? null : data.get("stampSheet").asText())
            .withStampSheetEncryptionKeyId(data.get("stampSheetEncryptionKeyId") == null || data.get("stampSheetEncryptionKeyId").isNull() ? null : data.get("stampSheetEncryptionKeyId").asText())
            .withAutoRunStampSheet(data.get("autoRunStampSheet") == null || data.get("autoRunStampSheet").isNull() ? null : data.get("autoRunStampSheet").booleanValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("item", getItem() != null ? getItem().toJson() : null);
                put("transactionId", getTransactionId());
                put("stampSheet", getStampSheet());
                put("stampSheetEncryptionKeyId", getStampSheetEncryptionKeyId());
                put("autoRunStampSheet", getAutoRunStampSheet());
            }}
        );
    }
}
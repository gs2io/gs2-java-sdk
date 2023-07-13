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

package io.gs2.loginReward.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.loginReward.model.*;
import io.gs2.loginReward.model.ReceiveStatus;
import io.gs2.loginReward.model.AcquireAction;
import io.gs2.loginReward.model.Reward;
import io.gs2.loginReward.model.ConsumeAction;
import io.gs2.loginReward.model.BonusModel;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class ReceiveByUserIdResult implements IResult, Serializable {
    private ReceiveStatus item;
    private BonusModel bonusModel;
    private String transactionId;
    private String stampSheet;
    private String stampSheetEncryptionKeyId;
    private Boolean autoRunStampSheet;

	public ReceiveStatus getItem() {
		return item;
	}

	public void setItem(ReceiveStatus item) {
		this.item = item;
	}

	public ReceiveByUserIdResult withItem(ReceiveStatus item) {
		this.item = item;
		return this;
	}

	public BonusModel getBonusModel() {
		return bonusModel;
	}

	public void setBonusModel(BonusModel bonusModel) {
		this.bonusModel = bonusModel;
	}

	public ReceiveByUserIdResult withBonusModel(BonusModel bonusModel) {
		this.bonusModel = bonusModel;
		return this;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public ReceiveByUserIdResult withTransactionId(String transactionId) {
		this.transactionId = transactionId;
		return this;
	}

	public String getStampSheet() {
		return stampSheet;
	}

	public void setStampSheet(String stampSheet) {
		this.stampSheet = stampSheet;
	}

	public ReceiveByUserIdResult withStampSheet(String stampSheet) {
		this.stampSheet = stampSheet;
		return this;
	}

	public String getStampSheetEncryptionKeyId() {
		return stampSheetEncryptionKeyId;
	}

	public void setStampSheetEncryptionKeyId(String stampSheetEncryptionKeyId) {
		this.stampSheetEncryptionKeyId = stampSheetEncryptionKeyId;
	}

	public ReceiveByUserIdResult withStampSheetEncryptionKeyId(String stampSheetEncryptionKeyId) {
		this.stampSheetEncryptionKeyId = stampSheetEncryptionKeyId;
		return this;
	}

	public Boolean getAutoRunStampSheet() {
		return autoRunStampSheet;
	}

	public void setAutoRunStampSheet(Boolean autoRunStampSheet) {
		this.autoRunStampSheet = autoRunStampSheet;
	}

	public ReceiveByUserIdResult withAutoRunStampSheet(Boolean autoRunStampSheet) {
		this.autoRunStampSheet = autoRunStampSheet;
		return this;
	}

    public static ReceiveByUserIdResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new ReceiveByUserIdResult()
            .withItem(data.get("item") == null || data.get("item").isNull() ? null : ReceiveStatus.fromJson(data.get("item")))
            .withBonusModel(data.get("bonusModel") == null || data.get("bonusModel").isNull() ? null : BonusModel.fromJson(data.get("bonusModel")))
            .withTransactionId(data.get("transactionId") == null || data.get("transactionId").isNull() ? null : data.get("transactionId").asText())
            .withStampSheet(data.get("stampSheet") == null || data.get("stampSheet").isNull() ? null : data.get("stampSheet").asText())
            .withStampSheetEncryptionKeyId(data.get("stampSheetEncryptionKeyId") == null || data.get("stampSheetEncryptionKeyId").isNull() ? null : data.get("stampSheetEncryptionKeyId").asText())
            .withAutoRunStampSheet(data.get("autoRunStampSheet") == null || data.get("autoRunStampSheet").isNull() ? null : data.get("autoRunStampSheet").booleanValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("item", getItem() != null ? getItem().toJson() : null);
                put("bonusModel", getBonusModel() != null ? getBonusModel().toJson() : null);
                put("transactionId", getTransactionId());
                put("stampSheet", getStampSheet());
                put("stampSheetEncryptionKeyId", getStampSheetEncryptionKeyId());
                put("autoRunStampSheet", getAutoRunStampSheet());
            }}
        );
    }
}
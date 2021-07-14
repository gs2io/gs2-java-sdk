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

package io.gs2.enhance.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.enhance.model.*;
import io.gs2.enhance.model.BonusRate;
import io.gs2.enhance.model.RateModel;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class DirectEnhanceByStampSheetResult implements IResult, Serializable {
    private RateModel item;
    private String stampSheet;
    private String stampSheetEncryptionKeyId;
    private Long acquireExperience;
    private Float bonusRate;

	public RateModel getItem() {
		return item;
	}

	public void setItem(RateModel item) {
		this.item = item;
	}

	public DirectEnhanceByStampSheetResult withItem(RateModel item) {
		this.item = item;
		return this;
	}

	public String getStampSheet() {
		return stampSheet;
	}

	public void setStampSheet(String stampSheet) {
		this.stampSheet = stampSheet;
	}

	public DirectEnhanceByStampSheetResult withStampSheet(String stampSheet) {
		this.stampSheet = stampSheet;
		return this;
	}

	public String getStampSheetEncryptionKeyId() {
		return stampSheetEncryptionKeyId;
	}

	public void setStampSheetEncryptionKeyId(String stampSheetEncryptionKeyId) {
		this.stampSheetEncryptionKeyId = stampSheetEncryptionKeyId;
	}

	public DirectEnhanceByStampSheetResult withStampSheetEncryptionKeyId(String stampSheetEncryptionKeyId) {
		this.stampSheetEncryptionKeyId = stampSheetEncryptionKeyId;
		return this;
	}

	public Long getAcquireExperience() {
		return acquireExperience;
	}

	public void setAcquireExperience(Long acquireExperience) {
		this.acquireExperience = acquireExperience;
	}

	public DirectEnhanceByStampSheetResult withAcquireExperience(Long acquireExperience) {
		this.acquireExperience = acquireExperience;
		return this;
	}

	public Float getBonusRate() {
		return bonusRate;
	}

	public void setBonusRate(Float bonusRate) {
		this.bonusRate = bonusRate;
	}

	public DirectEnhanceByStampSheetResult withBonusRate(Float bonusRate) {
		this.bonusRate = bonusRate;
		return this;
	}

    public static DirectEnhanceByStampSheetResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new DirectEnhanceByStampSheetResult()
            .withItem(data.get("item") == null || data.get("item").isNull() ? null : RateModel.fromJson(data.get("item")))
            .withStampSheet(data.get("stampSheet") == null || data.get("stampSheet").isNull() ? null : data.get("stampSheet").asText())
            .withStampSheetEncryptionKeyId(data.get("stampSheetEncryptionKeyId") == null || data.get("stampSheetEncryptionKeyId").isNull() ? null : data.get("stampSheetEncryptionKeyId").asText())
            .withAcquireExperience(data.get("acquireExperience") == null || data.get("acquireExperience").isNull() ? null : data.get("acquireExperience").longValue())
            .withBonusRate(data.get("bonusRate") == null || data.get("bonusRate").isNull() ? null : data.get("bonusRate").floatValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("item", getItem() != null ? getItem().toJson() : null);
                put("stampSheet", getStampSheet());
                put("stampSheetEncryptionKeyId", getStampSheetEncryptionKeyId());
                put("acquireExperience", getAcquireExperience());
                put("bonusRate", getBonusRate());
            }}
        );
    }
}
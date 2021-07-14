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
public class DrawByStampSheetResult implements IResult, Serializable {
    private List<DrawnPrize> items;
    private String stampSheet;
    private String stampSheetEncryptionKeyId;
    private BoxItems boxItems;

	public List<DrawnPrize> getItems() {
		return items;
	}

	public void setItems(List<DrawnPrize> items) {
		this.items = items;
	}

	public DrawByStampSheetResult withItems(List<DrawnPrize> items) {
		this.items = items;
		return this;
	}

	public String getStampSheet() {
		return stampSheet;
	}

	public void setStampSheet(String stampSheet) {
		this.stampSheet = stampSheet;
	}

	public DrawByStampSheetResult withStampSheet(String stampSheet) {
		this.stampSheet = stampSheet;
		return this;
	}

	public String getStampSheetEncryptionKeyId() {
		return stampSheetEncryptionKeyId;
	}

	public void setStampSheetEncryptionKeyId(String stampSheetEncryptionKeyId) {
		this.stampSheetEncryptionKeyId = stampSheetEncryptionKeyId;
	}

	public DrawByStampSheetResult withStampSheetEncryptionKeyId(String stampSheetEncryptionKeyId) {
		this.stampSheetEncryptionKeyId = stampSheetEncryptionKeyId;
		return this;
	}

	public BoxItems getBoxItems() {
		return boxItems;
	}

	public void setBoxItems(BoxItems boxItems) {
		this.boxItems = boxItems;
	}

	public DrawByStampSheetResult withBoxItems(BoxItems boxItems) {
		this.boxItems = boxItems;
		return this;
	}

    public static DrawByStampSheetResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new DrawByStampSheetResult()
            .withItems(data.get("items") == null || data.get("items").isNull() ? new ArrayList<DrawnPrize>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("items").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return DrawnPrize.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withStampSheet(data.get("stampSheet") == null || data.get("stampSheet").isNull() ? null : data.get("stampSheet").asText())
            .withStampSheetEncryptionKeyId(data.get("stampSheetEncryptionKeyId") == null || data.get("stampSheetEncryptionKeyId").isNull() ? null : data.get("stampSheetEncryptionKeyId").asText())
            .withBoxItems(data.get("boxItems") == null || data.get("boxItems").isNull() ? null : BoxItems.fromJson(data.get("boxItems")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("items", getItems() == null ? new ArrayList<DrawnPrize>() :
                    getItems().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("stampSheet", getStampSheet());
                put("stampSheetEncryptionKeyId", getStampSheetEncryptionKeyId());
                put("boxItems", getBoxItems() != null ? getBoxItems().toJson() : null);
            }}
        );
    }
}
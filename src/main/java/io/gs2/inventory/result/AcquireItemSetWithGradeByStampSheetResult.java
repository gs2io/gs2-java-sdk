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

package io.gs2.inventory.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.inventory.model.*;
import io.gs2.inventory.model.ItemSet;
import io.gs2.inventory.model.ItemModel;
import io.gs2.inventory.model.Inventory;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class AcquireItemSetWithGradeByStampSheetResult implements IResult, Serializable {
    private ItemSet item;
    private io.gs2.grade.model.Status status;
    private ItemModel itemModel;
    private Inventory inventory;
    private Long overflowCount;

	public ItemSet getItem() {
		return item;
	}

	public void setItem(ItemSet item) {
		this.item = item;
	}

	public AcquireItemSetWithGradeByStampSheetResult withItem(ItemSet item) {
		this.item = item;
		return this;
	}

	public io.gs2.grade.model.Status getStatus() {
		return status;
	}

	public void setStatus(io.gs2.grade.model.Status status) {
		this.status = status;
	}

	public AcquireItemSetWithGradeByStampSheetResult withStatus(io.gs2.grade.model.Status status) {
		this.status = status;
		return this;
	}

	public ItemModel getItemModel() {
		return itemModel;
	}

	public void setItemModel(ItemModel itemModel) {
		this.itemModel = itemModel;
	}

	public AcquireItemSetWithGradeByStampSheetResult withItemModel(ItemModel itemModel) {
		this.itemModel = itemModel;
		return this;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public AcquireItemSetWithGradeByStampSheetResult withInventory(Inventory inventory) {
		this.inventory = inventory;
		return this;
	}

	public Long getOverflowCount() {
		return overflowCount;
	}

	public void setOverflowCount(Long overflowCount) {
		this.overflowCount = overflowCount;
	}

	public AcquireItemSetWithGradeByStampSheetResult withOverflowCount(Long overflowCount) {
		this.overflowCount = overflowCount;
		return this;
	}

    public static AcquireItemSetWithGradeByStampSheetResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new AcquireItemSetWithGradeByStampSheetResult()
            .withItem(data.get("item") == null || data.get("item").isNull() ? null : ItemSet.fromJson(data.get("item")))
            .withStatus(data.get("status") == null || data.get("status").isNull() ? null : io.gs2.grade.model.Status.fromJson(data.get("status")))
            .withItemModel(data.get("itemModel") == null || data.get("itemModel").isNull() ? null : ItemModel.fromJson(data.get("itemModel")))
            .withInventory(data.get("inventory") == null || data.get("inventory").isNull() ? null : Inventory.fromJson(data.get("inventory")))
            .withOverflowCount(data.get("overflowCount") == null || data.get("overflowCount").isNull() ? null : data.get("overflowCount").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("item", getItem() != null ? getItem().toJson() : null);
                put("status", getStatus() != null ? getStatus().toJson() : null);
                put("itemModel", getItemModel() != null ? getItemModel().toJson() : null);
                put("inventory", getInventory() != null ? getInventory().toJson() : null);
                put("overflowCount", getOverflowCount());
            }}
        );
    }
}
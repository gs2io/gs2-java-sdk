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
public class GetItemSetResult implements IResult, Serializable {
    private List<ItemSet> items;
    private ItemModel itemModel;
    private Inventory inventory;

	public List<ItemSet> getItems() {
		return items;
	}

	public void setItems(List<ItemSet> items) {
		this.items = items;
	}

	public GetItemSetResult withItems(List<ItemSet> items) {
		this.items = items;
		return this;
	}

	public ItemModel getItemModel() {
		return itemModel;
	}

	public void setItemModel(ItemModel itemModel) {
		this.itemModel = itemModel;
	}

	public GetItemSetResult withItemModel(ItemModel itemModel) {
		this.itemModel = itemModel;
		return this;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public GetItemSetResult withInventory(Inventory inventory) {
		this.inventory = inventory;
		return this;
	}

    public static GetItemSetResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GetItemSetResult()
            .withItems(data.get("items") == null || data.get("items").isNull() ? new ArrayList<ItemSet>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("items").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return ItemSet.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withItemModel(data.get("itemModel") == null || data.get("itemModel").isNull() ? null : ItemModel.fromJson(data.get("itemModel")))
            .withInventory(data.get("inventory") == null || data.get("inventory").isNull() ? null : Inventory.fromJson(data.get("inventory")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("items", getItems() == null ? new ArrayList<ItemSet>() :
                    getItems().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("itemModel", getItemModel() != null ? getItemModel().toJson() : null);
                put("inventory", getInventory() != null ? getInventory().toJson() : null);
            }}
        );
    }
}
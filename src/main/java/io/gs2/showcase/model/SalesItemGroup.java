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

package io.gs2.showcase.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.IModel;


@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class SalesItemGroup implements IModel, Serializable {
	private String name;
	private String metadata;
	private List<SalesItem> salesItems;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SalesItemGroup withName(String name) {
		this.name = name;
		return this;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public SalesItemGroup withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}

	public List<SalesItem> getSalesItems() {
		return salesItems;
	}

	public void setSalesItems(List<SalesItem> salesItems) {
		this.salesItems = salesItems;
	}

	public SalesItemGroup withSalesItems(List<SalesItem> salesItems) {
		this.salesItems = salesItems;
		return this;
	}

    public static SalesItemGroup fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new SalesItemGroup()
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withSalesItems(data.get("salesItems") == null || data.get("salesItems").isNull() ? new ArrayList<SalesItem>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("salesItems").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return SalesItem.fromJson(item);
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("name", getName());
                put("metadata", getMetadata());
                put("salesItems", getSalesItems() == null ? new ArrayList<SalesItem>() :
                    getSalesItems().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.salesItems == null) ? 0 : this.salesItems.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		SalesItemGroup other = (SalesItemGroup) o;
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (metadata == null) {
			return other.metadata == null;
		} else if (!metadata.equals(other.metadata)) {
			return false;
		}
		if (salesItems == null) {
			return other.salesItems == null;
		} else if (!salesItems.equals(other.salesItems)) {
			return false;
		}
		return true;
	}
}
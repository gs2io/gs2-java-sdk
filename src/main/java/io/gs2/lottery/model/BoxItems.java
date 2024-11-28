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

package io.gs2.lottery.model;

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
public class BoxItems implements IModel, Serializable, Comparable<BoxItems> {
	private String boxId;
	private String prizeTableName;
	private String userId;
	private List<BoxItem> items;
	public String getBoxId() {
		return boxId;
	}
	public void setBoxId(String boxId) {
		this.boxId = boxId;
	}
	public BoxItems withBoxId(String boxId) {
		this.boxId = boxId;
		return this;
	}
	public String getPrizeTableName() {
		return prizeTableName;
	}
	public void setPrizeTableName(String prizeTableName) {
		this.prizeTableName = prizeTableName;
	}
	public BoxItems withPrizeTableName(String prizeTableName) {
		this.prizeTableName = prizeTableName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public BoxItems withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public List<BoxItem> getItems() {
		return items;
	}
	public void setItems(List<BoxItem> items) {
		this.items = items;
	}
	public BoxItems withItems(List<BoxItem> items) {
		this.items = items;
		return this;
	}

    public static BoxItems fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new BoxItems()
            .withBoxId(data.get("boxId") == null || data.get("boxId").isNull() ? null : data.get("boxId").asText())
            .withPrizeTableName(data.get("prizeTableName") == null || data.get("prizeTableName").isNull() ? null : data.get("prizeTableName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withItems(data.get("items") == null || data.get("items").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("items").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return BoxItem.fromJson(item);
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("boxId", getBoxId());
                put("prizeTableName", getPrizeTableName());
                put("userId", getUserId());
                put("items", getItems() == null ? null :
                    getItems().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }

	@Override
	public int compareTo(BoxItems o) {
		return boxId.compareTo(o.boxId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.boxId == null) ? 0 : this.boxId.hashCode());
        result = prime * result + ((this.prizeTableName == null) ? 0 : this.prizeTableName.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.items == null) ? 0 : this.items.hashCode());
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
		BoxItems other = (BoxItems) o;
		if (boxId == null) {
			return other.boxId == null;
		} else if (!boxId.equals(other.boxId)) {
			return false;
		}
		if (prizeTableName == null) {
			return other.prizeTableName == null;
		} else if (!prizeTableName.equals(other.prizeTableName)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (items == null) {
			return other.items == null;
		} else if (!items.equals(other.items)) {
			return false;
		}
		return true;
	}
}
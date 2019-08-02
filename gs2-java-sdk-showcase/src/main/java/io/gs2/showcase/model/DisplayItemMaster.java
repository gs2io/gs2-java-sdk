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

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.model.IModel;

/**
 * None
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class DisplayItemMaster implements IModel, Serializable, Comparable<DisplayItemMaster> {
	/** 陳列商品ID */
	protected String displayItemId;

	/**
	 * 陳列商品IDを取得
	 *
	 * @return 陳列商品ID
	 */
	public String getDisplayItemId() {
		return displayItemId;
	}

	/**
	 * 陳列商品IDを設定
	 *
	 * @param displayItemId 陳列商品ID
	 */
	public void setDisplayItemId(String displayItemId) {
		this.displayItemId = displayItemId;
	}

	/**
	 * 陳列商品IDを設定
	 *
	 * @param displayItemId 陳列商品ID
	 * @return this
	 */
	public DisplayItemMaster withDisplayItemId(String displayItemId) {
		this.displayItemId = displayItemId;
		return this;
	}
	/** 種類 */
	protected String type;

	/**
	 * 種類を取得
	 *
	 * @return 種類
	 */
	public String getType() {
		return type;
	}

	/**
	 * 種類を設定
	 *
	 * @param type 種類
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 種類を設定
	 *
	 * @param type 種類
	 * @return this
	 */
	public DisplayItemMaster withType(String type) {
		this.type = type;
		return this;
	}
	/** 陳列する商品の名前 */
	protected String salesItemName;

	/**
	 * 陳列する商品の名前を取得
	 *
	 * @return 陳列する商品の名前
	 */
	public String getSalesItemName() {
		return salesItemName;
	}

	/**
	 * 陳列する商品の名前を設定
	 *
	 * @param salesItemName 陳列する商品の名前
	 */
	public void setSalesItemName(String salesItemName) {
		this.salesItemName = salesItemName;
	}

	/**
	 * 陳列する商品の名前を設定
	 *
	 * @param salesItemName 陳列する商品の名前
	 * @return this
	 */
	public DisplayItemMaster withSalesItemName(String salesItemName) {
		this.salesItemName = salesItemName;
		return this;
	}
	/** 陳列する商品グループの名前 */
	protected String salesItemGroupName;

	/**
	 * 陳列する商品グループの名前を取得
	 *
	 * @return 陳列する商品グループの名前
	 */
	public String getSalesItemGroupName() {
		return salesItemGroupName;
	}

	/**
	 * 陳列する商品グループの名前を設定
	 *
	 * @param salesItemGroupName 陳列する商品グループの名前
	 */
	public void setSalesItemGroupName(String salesItemGroupName) {
		this.salesItemGroupName = salesItemGroupName;
	}

	/**
	 * 陳列する商品グループの名前を設定
	 *
	 * @param salesItemGroupName 陳列する商品グループの名前
	 * @return this
	 */
	public DisplayItemMaster withSalesItemGroupName(String salesItemGroupName) {
		this.salesItemGroupName = salesItemGroupName;
		return this;
	}
	/** 販売期間とするイベントマスター のGRN */
	protected String salesPeriodEventId;

	/**
	 * 販売期間とするイベントマスター のGRNを取得
	 *
	 * @return 販売期間とするイベントマスター のGRN
	 */
	public String getSalesPeriodEventId() {
		return salesPeriodEventId;
	}

	/**
	 * 販売期間とするイベントマスター のGRNを設定
	 *
	 * @param salesPeriodEventId 販売期間とするイベントマスター のGRN
	 */
	public void setSalesPeriodEventId(String salesPeriodEventId) {
		this.salesPeriodEventId = salesPeriodEventId;
	}

	/**
	 * 販売期間とするイベントマスター のGRNを設定
	 *
	 * @param salesPeriodEventId 販売期間とするイベントマスター のGRN
	 * @return this
	 */
	public DisplayItemMaster withSalesPeriodEventId(String salesPeriodEventId) {
		this.salesPeriodEventId = salesPeriodEventId;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("displayItemId", this.getDisplayItemId())
            .put("type", this.getType())
            .put("salesItemName", this.getSalesItemName())
            .put("salesItemGroupName", this.getSalesItemGroupName())
            .put("salesPeriodEventId", this.getSalesPeriodEventId());
        return body_;
    }
	@Override
	public int compareTo(DisplayItemMaster o) {
		return displayItemId.compareTo(o.displayItemId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.displayItemId == null) ? 0 : this.displayItemId.hashCode());
        result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
        result = prime * result + ((this.salesItemName == null) ? 0 : this.salesItemName.hashCode());
        result = prime * result + ((this.salesItemGroupName == null) ? 0 : this.salesItemGroupName.hashCode());
        result = prime * result + ((this.salesPeriodEventId == null) ? 0 : this.salesPeriodEventId.hashCode());
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
		DisplayItemMaster other = (DisplayItemMaster) o;
		if (displayItemId == null) {
			return other.displayItemId == null;
		} else if (!displayItemId.equals(other.displayItemId)) {
			return false;
		}
		if (type == null) {
			return other.type == null;
		} else if (!type.equals(other.type)) {
			return false;
		}
		if (salesItemName == null) {
			return other.salesItemName == null;
		} else if (!salesItemName.equals(other.salesItemName)) {
			return false;
		}
		if (salesItemGroupName == null) {
			return other.salesItemGroupName == null;
		} else if (!salesItemGroupName.equals(other.salesItemGroupName)) {
			return false;
		}
		if (salesPeriodEventId == null) {
			return other.salesPeriodEventId == null;
		} else if (!salesPeriodEventId.equals(other.salesPeriodEventId)) {
			return false;
		}
		return true;
	}
}
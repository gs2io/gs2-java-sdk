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
import io.gs2.core.model.IModel;

/**
 * 陳列された商品
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class DisplayItem implements IModel, Serializable, Comparable<DisplayItem> {
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
	public DisplayItem withDisplayItemId(String displayItemId) {
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
	public DisplayItem withType(String type) {
		this.type = type;
		return this;
	}
	/** 陳列する商品 */
	protected SalesItem salesItem;

	/**
	 * 陳列する商品を取得
	 *
	 * @return 陳列する商品
	 */
	public SalesItem getSalesItem() {
		return salesItem;
	}

	/**
	 * 陳列する商品を設定
	 *
	 * @param salesItem 陳列する商品
	 */
	public void setSalesItem(SalesItem salesItem) {
		this.salesItem = salesItem;
	}

	/**
	 * 陳列する商品を設定
	 *
	 * @param salesItem 陳列する商品
	 * @return this
	 */
	public DisplayItem withSalesItem(SalesItem salesItem) {
		this.salesItem = salesItem;
		return this;
	}
	/** 陳列する商品グループ */
	protected SalesItemGroup salesItemGroup;

	/**
	 * 陳列する商品グループを取得
	 *
	 * @return 陳列する商品グループ
	 */
	public SalesItemGroup getSalesItemGroup() {
		return salesItemGroup;
	}

	/**
	 * 陳列する商品グループを設定
	 *
	 * @param salesItemGroup 陳列する商品グループ
	 */
	public void setSalesItemGroup(SalesItemGroup salesItemGroup) {
		this.salesItemGroup = salesItemGroup;
	}

	/**
	 * 陳列する商品グループを設定
	 *
	 * @param salesItemGroup 陳列する商品グループ
	 * @return this
	 */
	public DisplayItem withSalesItemGroup(SalesItemGroup salesItemGroup) {
		this.salesItemGroup = salesItemGroup;
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
	public DisplayItem withSalesPeriodEventId(String salesPeriodEventId) {
		this.salesPeriodEventId = salesPeriodEventId;
		return this;
	}

    public ObjectNode toJson() {
        JsonNode salesItem = this.getSalesItem().toJson();
        JsonNode salesItemGroup = this.getSalesItemGroup().toJson();
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("displayItemId", this.getDisplayItemId())
            .put("type", this.getType())
            .put("salesPeriodEventId", this.getSalesPeriodEventId());
        body_.set("salesItem", salesItem);
        body_.set("salesItemGroup", salesItemGroup);
        return body_;
    }
	@Override
	public int compareTo(DisplayItem o) {
		return displayItemId.compareTo(o.displayItemId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.displayItemId == null) ? 0 : this.displayItemId.hashCode());
        result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
        result = prime * result + ((this.salesItem == null) ? 0 : this.salesItem.hashCode());
        result = prime * result + ((this.salesItemGroup == null) ? 0 : this.salesItemGroup.hashCode());
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
		DisplayItem other = (DisplayItem) o;
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
		if (salesItem == null) {
			return other.salesItem == null;
		} else if (!salesItem.equals(other.salesItem)) {
			return false;
		}
		if (salesItemGroup == null) {
			return other.salesItemGroup == null;
		} else if (!salesItemGroup.equals(other.salesItemGroup)) {
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
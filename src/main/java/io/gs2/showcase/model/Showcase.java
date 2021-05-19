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
 * 陳列棚
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Showcase implements IModel, Serializable, Comparable<Showcase> {
	/** 陳列棚 */
	protected String showcaseId;

	/**
	 * 陳列棚を取得
	 *
	 * @return 陳列棚
	 */
	public String getShowcaseId() {
		return showcaseId;
	}

	/**
	 * 陳列棚を設定
	 *
	 * @param showcaseId 陳列棚
	 */
	public void setShowcaseId(String showcaseId) {
		this.showcaseId = showcaseId;
	}

	/**
	 * 陳列棚を設定
	 *
	 * @param showcaseId 陳列棚
	 * @return this
	 */
	public Showcase withShowcaseId(String showcaseId) {
		this.showcaseId = showcaseId;
		return this;
	}
	/** 商品名 */
	protected String name;

	/**
	 * 商品名を取得
	 *
	 * @return 商品名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 商品名を設定
	 *
	 * @param name 商品名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 商品名を設定
	 *
	 * @param name 商品名
	 * @return this
	 */
	public Showcase withName(String name) {
		this.name = name;
		return this;
	}
	/** 商品のメタデータ */
	protected String metadata;

	/**
	 * 商品のメタデータを取得
	 *
	 * @return 商品のメタデータ
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * 商品のメタデータを設定
	 *
	 * @param metadata 商品のメタデータ
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	/**
	 * 商品のメタデータを設定
	 *
	 * @param metadata 商品のメタデータ
	 * @return this
	 */
	public Showcase withMetadata(String metadata) {
		this.metadata = metadata;
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
	public Showcase withSalesPeriodEventId(String salesPeriodEventId) {
		this.salesPeriodEventId = salesPeriodEventId;
		return this;
	}
	/** インベントリに格納可能なアイテムモデル一覧 */
	protected List<DisplayItem> displayItems;

	/**
	 * インベントリに格納可能なアイテムモデル一覧を取得
	 *
	 * @return インベントリに格納可能なアイテムモデル一覧
	 */
	public List<DisplayItem> getDisplayItems() {
		return displayItems;
	}

	/**
	 * インベントリに格納可能なアイテムモデル一覧を設定
	 *
	 * @param displayItems インベントリに格納可能なアイテムモデル一覧
	 */
	public void setDisplayItems(List<DisplayItem> displayItems) {
		this.displayItems = displayItems;
	}

	/**
	 * インベントリに格納可能なアイテムモデル一覧を設定
	 *
	 * @param displayItems インベントリに格納可能なアイテムモデル一覧
	 * @return this
	 */
	public Showcase withDisplayItems(List<DisplayItem> displayItems) {
		this.displayItems = displayItems;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> displayItems = new ArrayList<>();
        if(this.displayItems != null) {
            for(DisplayItem item : this.displayItems) {
                displayItems.add(item.toJson());
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("showcaseId", this.getShowcaseId())
            .put("name", this.getName())
            .put("metadata", this.getMetadata())
            .put("salesPeriodEventId", this.getSalesPeriodEventId());
        body_.set("displayItems", JsonNodeFactory.instance.arrayNode().addAll(displayItems));
        return body_;
    }
	@Override
	public int compareTo(Showcase o) {
		return showcaseId.compareTo(o.showcaseId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.showcaseId == null) ? 0 : this.showcaseId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.salesPeriodEventId == null) ? 0 : this.salesPeriodEventId.hashCode());
        result = prime * result + ((this.displayItems == null) ? 0 : this.displayItems.hashCode());
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
		Showcase other = (Showcase) o;
		if (showcaseId == null) {
			return other.showcaseId == null;
		} else if (!showcaseId.equals(other.showcaseId)) {
			return false;
		}
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
		if (salesPeriodEventId == null) {
			return other.salesPeriodEventId == null;
		} else if (!salesPeriodEventId.equals(other.salesPeriodEventId)) {
			return false;
		}
		if (displayItems == null) {
			return other.displayItems == null;
		} else if (!displayItems.equals(other.displayItems)) {
			return false;
		}
		return true;
	}
}
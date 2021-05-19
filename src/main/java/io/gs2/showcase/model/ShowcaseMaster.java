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
 * 陳列棚マスター
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class ShowcaseMaster implements IModel, Serializable, Comparable<ShowcaseMaster> {
	/** 陳列棚マスター */
	protected String showcaseId;

	/**
	 * 陳列棚マスターを取得
	 *
	 * @return 陳列棚マスター
	 */
	public String getShowcaseId() {
		return showcaseId;
	}

	/**
	 * 陳列棚マスターを設定
	 *
	 * @param showcaseId 陳列棚マスター
	 */
	public void setShowcaseId(String showcaseId) {
		this.showcaseId = showcaseId;
	}

	/**
	 * 陳列棚マスターを設定
	 *
	 * @param showcaseId 陳列棚マスター
	 * @return this
	 */
	public ShowcaseMaster withShowcaseId(String showcaseId) {
		this.showcaseId = showcaseId;
		return this;
	}
	/** 陳列棚名 */
	protected String name;

	/**
	 * 陳列棚名を取得
	 *
	 * @return 陳列棚名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 陳列棚名を設定
	 *
	 * @param name 陳列棚名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 陳列棚名を設定
	 *
	 * @param name 陳列棚名
	 * @return this
	 */
	public ShowcaseMaster withName(String name) {
		this.name = name;
		return this;
	}
	/** 陳列棚マスターの説明 */
	protected String description;

	/**
	 * 陳列棚マスターの説明を取得
	 *
	 * @return 陳列棚マスターの説明
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 陳列棚マスターの説明を設定
	 *
	 * @param description 陳列棚マスターの説明
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 陳列棚マスターの説明を設定
	 *
	 * @param description 陳列棚マスターの説明
	 * @return this
	 */
	public ShowcaseMaster withDescription(String description) {
		this.description = description;
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
	public ShowcaseMaster withMetadata(String metadata) {
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
	public ShowcaseMaster withSalesPeriodEventId(String salesPeriodEventId) {
		this.salesPeriodEventId = salesPeriodEventId;
		return this;
	}
	/** 陳列する商品モデル一覧 */
	protected List<DisplayItemMaster> displayItems;

	/**
	 * 陳列する商品モデル一覧を取得
	 *
	 * @return 陳列する商品モデル一覧
	 */
	public List<DisplayItemMaster> getDisplayItems() {
		return displayItems;
	}

	/**
	 * 陳列する商品モデル一覧を設定
	 *
	 * @param displayItems 陳列する商品モデル一覧
	 */
	public void setDisplayItems(List<DisplayItemMaster> displayItems) {
		this.displayItems = displayItems;
	}

	/**
	 * 陳列する商品モデル一覧を設定
	 *
	 * @param displayItems 陳列する商品モデル一覧
	 * @return this
	 */
	public ShowcaseMaster withDisplayItems(List<DisplayItemMaster> displayItems) {
		this.displayItems = displayItems;
		return this;
	}
	/** 作成日時 */
	protected Long createdAt;

	/**
	 * 作成日時を取得
	 *
	 * @return 作成日時
	 */
	public Long getCreatedAt() {
		return createdAt;
	}

	/**
	 * 作成日時を設定
	 *
	 * @param createdAt 作成日時
	 */
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * 作成日時を設定
	 *
	 * @param createdAt 作成日時
	 * @return this
	 */
	public ShowcaseMaster withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	/** 最終更新日時 */
	protected Long updatedAt;

	/**
	 * 最終更新日時を取得
	 *
	 * @return 最終更新日時
	 */
	public Long getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * 最終更新日時を設定
	 *
	 * @param updatedAt 最終更新日時
	 */
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * 最終更新日時を設定
	 *
	 * @param updatedAt 最終更新日時
	 * @return this
	 */
	public ShowcaseMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> displayItems = new ArrayList<>();
        if(this.displayItems != null) {
            for(DisplayItemMaster item : this.displayItems) {
                displayItems.add(item.toJson());
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("showcaseId", this.getShowcaseId())
            .put("name", this.getName())
            .put("description", this.getDescription())
            .put("metadata", this.getMetadata())
            .put("salesPeriodEventId", this.getSalesPeriodEventId())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        body_.set("displayItems", JsonNodeFactory.instance.arrayNode().addAll(displayItems));
        return body_;
    }
	@Override
	public int compareTo(ShowcaseMaster o) {
		return showcaseId.compareTo(o.showcaseId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.showcaseId == null) ? 0 : this.showcaseId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.salesPeriodEventId == null) ? 0 : this.salesPeriodEventId.hashCode());
        result = prime * result + ((this.displayItems == null) ? 0 : this.displayItems.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
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
		ShowcaseMaster other = (ShowcaseMaster) o;
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
		if (description == null) {
			return other.description == null;
		} else if (!description.equals(other.description)) {
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
		if (createdAt == null) {
			return other.createdAt == null;
		} else if (!createdAt.equals(other.createdAt)) {
			return false;
		}
		if (updatedAt == null) {
			return other.updatedAt == null;
		} else if (!updatedAt.equals(other.updatedAt)) {
			return false;
		}
		return true;
	}
}
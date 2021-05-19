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
 * 商品マスター
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class SalesItemMaster implements IModel, Serializable, Comparable<SalesItemMaster> {
	/** 商品マスター */
	protected String salesItemId;

	/**
	 * 商品マスターを取得
	 *
	 * @return 商品マスター
	 */
	public String getSalesItemId() {
		return salesItemId;
	}

	/**
	 * 商品マスターを設定
	 *
	 * @param salesItemId 商品マスター
	 */
	public void setSalesItemId(String salesItemId) {
		this.salesItemId = salesItemId;
	}

	/**
	 * 商品マスターを設定
	 *
	 * @param salesItemId 商品マスター
	 * @return this
	 */
	public SalesItemMaster withSalesItemId(String salesItemId) {
		this.salesItemId = salesItemId;
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
	public SalesItemMaster withName(String name) {
		this.name = name;
		return this;
	}
	/** 商品マスターの説明 */
	protected String description;

	/**
	 * 商品マスターの説明を取得
	 *
	 * @return 商品マスターの説明
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 商品マスターの説明を設定
	 *
	 * @param description 商品マスターの説明
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 商品マスターの説明を設定
	 *
	 * @param description 商品マスターの説明
	 * @return this
	 */
	public SalesItemMaster withDescription(String description) {
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
	public SalesItemMaster withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	/** 消費アクションリスト */
	protected List<ConsumeAction> consumeActions;

	/**
	 * 消費アクションリストを取得
	 *
	 * @return 消費アクションリスト
	 */
	public List<ConsumeAction> getConsumeActions() {
		return consumeActions;
	}

	/**
	 * 消費アクションリストを設定
	 *
	 * @param consumeActions 消費アクションリスト
	 */
	public void setConsumeActions(List<ConsumeAction> consumeActions) {
		this.consumeActions = consumeActions;
	}

	/**
	 * 消費アクションリストを設定
	 *
	 * @param consumeActions 消費アクションリスト
	 * @return this
	 */
	public SalesItemMaster withConsumeActions(List<ConsumeAction> consumeActions) {
		this.consumeActions = consumeActions;
		return this;
	}
	/** 入手アクションリスト */
	protected List<AcquireAction> acquireActions;

	/**
	 * 入手アクションリストを取得
	 *
	 * @return 入手アクションリスト
	 */
	public List<AcquireAction> getAcquireActions() {
		return acquireActions;
	}

	/**
	 * 入手アクションリストを設定
	 *
	 * @param acquireActions 入手アクションリスト
	 */
	public void setAcquireActions(List<AcquireAction> acquireActions) {
		this.acquireActions = acquireActions;
	}

	/**
	 * 入手アクションリストを設定
	 *
	 * @param acquireActions 入手アクションリスト
	 * @return this
	 */
	public SalesItemMaster withAcquireActions(List<AcquireAction> acquireActions) {
		this.acquireActions = acquireActions;
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
	public SalesItemMaster withCreatedAt(Long createdAt) {
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
	public SalesItemMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> consumeActions = new ArrayList<>();
        if(this.consumeActions != null) {
            for(ConsumeAction item : this.consumeActions) {
                consumeActions.add(item.toJson());
            }
        }
        List<JsonNode> acquireActions = new ArrayList<>();
        if(this.acquireActions != null) {
            for(AcquireAction item : this.acquireActions) {
                acquireActions.add(item.toJson());
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("salesItemId", this.getSalesItemId())
            .put("name", this.getName())
            .put("description", this.getDescription())
            .put("metadata", this.getMetadata())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        body_.set("consumeActions", JsonNodeFactory.instance.arrayNode().addAll(consumeActions));
        body_.set("acquireActions", JsonNodeFactory.instance.arrayNode().addAll(acquireActions));
        return body_;
    }
	@Override
	public int compareTo(SalesItemMaster o) {
		return salesItemId.compareTo(o.salesItemId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.salesItemId == null) ? 0 : this.salesItemId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.consumeActions == null) ? 0 : this.consumeActions.hashCode());
        result = prime * result + ((this.acquireActions == null) ? 0 : this.acquireActions.hashCode());
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
		SalesItemMaster other = (SalesItemMaster) o;
		if (salesItemId == null) {
			return other.salesItemId == null;
		} else if (!salesItemId.equals(other.salesItemId)) {
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
		if (consumeActions == null) {
			return other.consumeActions == null;
		} else if (!consumeActions.equals(other.consumeActions)) {
			return false;
		}
		if (acquireActions == null) {
			return other.acquireActions == null;
		} else if (!acquireActions.equals(other.acquireActions)) {
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
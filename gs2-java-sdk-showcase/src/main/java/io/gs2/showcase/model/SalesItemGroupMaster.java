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
 * 商品グループマスター
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class SalesItemGroupMaster implements IModel, Serializable, Comparable<SalesItemGroupMaster> {
	/** 商品グループマスター */
	protected String salesItemGroupId;

	/**
	 * 商品グループマスターを取得
	 *
	 * @return 商品グループマスター
	 */
	public String getSalesItemGroupId() {
		return salesItemGroupId;
	}

	/**
	 * 商品グループマスターを設定
	 *
	 * @param salesItemGroupId 商品グループマスター
	 */
	public void setSalesItemGroupId(String salesItemGroupId) {
		this.salesItemGroupId = salesItemGroupId;
	}

	/**
	 * 商品グループマスターを設定
	 *
	 * @param salesItemGroupId 商品グループマスター
	 * @return this
	 */
	public SalesItemGroupMaster withSalesItemGroupId(String salesItemGroupId) {
		this.salesItemGroupId = salesItemGroupId;
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
	public SalesItemGroupMaster withName(String name) {
		this.name = name;
		return this;
	}
	/** 商品グループマスターの説明 */
	protected String description;

	/**
	 * 商品グループマスターの説明を取得
	 *
	 * @return 商品グループマスターの説明
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 商品グループマスターの説明を設定
	 *
	 * @param description 商品グループマスターの説明
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 商品グループマスターの説明を設定
	 *
	 * @param description 商品グループマスターの説明
	 * @return this
	 */
	public SalesItemGroupMaster withDescription(String description) {
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
	public SalesItemGroupMaster withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	/** 商品グループに含める商品リスト */
	protected List<String> salesItemNames;

	/**
	 * 商品グループに含める商品リストを取得
	 *
	 * @return 商品グループに含める商品リスト
	 */
	public List<String> getSalesItemNames() {
		return salesItemNames;
	}

	/**
	 * 商品グループに含める商品リストを設定
	 *
	 * @param salesItemNames 商品グループに含める商品リスト
	 */
	public void setSalesItemNames(List<String> salesItemNames) {
		this.salesItemNames = salesItemNames;
	}

	/**
	 * 商品グループに含める商品リストを設定
	 *
	 * @param salesItemNames 商品グループに含める商品リスト
	 * @return this
	 */
	public SalesItemGroupMaster withSalesItemNames(List<String> salesItemNames) {
		this.salesItemNames = salesItemNames;
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
	public SalesItemGroupMaster withCreatedAt(Long createdAt) {
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
	public SalesItemGroupMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> salesItemNames = new ArrayList<>();
        if(this.salesItemNames != null) {
            for(String item : this.salesItemNames) {
                salesItemNames.add(JsonNodeFactory.instance.textNode(item));
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("salesItemGroupId", this.getSalesItemGroupId())
            .put("name", this.getName())
            .put("description", this.getDescription())
            .put("metadata", this.getMetadata())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        body_.set("salesItemNames", JsonNodeFactory.instance.arrayNode().addAll(salesItemNames));
        return body_;
    }
	@Override
	public int compareTo(SalesItemGroupMaster o) {
		return salesItemGroupId.compareTo(o.salesItemGroupId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.salesItemGroupId == null) ? 0 : this.salesItemGroupId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.salesItemNames == null) ? 0 : this.salesItemNames.hashCode());
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
		SalesItemGroupMaster other = (SalesItemGroupMaster) o;
		if (salesItemGroupId == null) {
			return other.salesItemGroupId == null;
		} else if (!salesItemGroupId.equals(other.salesItemGroupId)) {
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
		if (salesItemNames == null) {
			return other.salesItemNames == null;
		} else if (!salesItemNames.equals(other.salesItemNames)) {
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
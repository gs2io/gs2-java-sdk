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
 * 商品グループ
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class SalesItemGroup implements IModel, Serializable, Comparable<SalesItemGroup> {
	/** 商品グループ名 */
	protected String name;

	/**
	 * 商品グループ名を取得
	 *
	 * @return 商品グループ名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 商品グループ名を設定
	 *
	 * @param name 商品グループ名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 商品グループ名を設定
	 *
	 * @param name 商品グループ名
	 * @return this
	 */
	public SalesItemGroup withName(String name) {
		this.name = name;
		return this;
	}
	/** メタデータ */
	protected String metadata;

	/**
	 * メタデータを取得
	 *
	 * @return メタデータ
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * メタデータを設定
	 *
	 * @param metadata メタデータ
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	/**
	 * メタデータを設定
	 *
	 * @param metadata メタデータ
	 * @return this
	 */
	public SalesItemGroup withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	/** 商品リスト */
	protected List<SalesItem> salesItems;

	/**
	 * 商品リストを取得
	 *
	 * @return 商品リスト
	 */
	public List<SalesItem> getSalesItems() {
		return salesItems;
	}

	/**
	 * 商品リストを設定
	 *
	 * @param salesItems 商品リスト
	 */
	public void setSalesItems(List<SalesItem> salesItems) {
		this.salesItems = salesItems;
	}

	/**
	 * 商品リストを設定
	 *
	 * @param salesItems 商品リスト
	 * @return this
	 */
	public SalesItemGroup withSalesItems(List<SalesItem> salesItems) {
		this.salesItems = salesItems;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> salesItems = new ArrayList<>();
        if(this.salesItems != null) {
            for(SalesItem item : this.salesItems) {
                salesItems.add(item.toJson());
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("name", this.getName())
            .put("metadata", this.getMetadata());
        body_.set("salesItems", JsonNodeFactory.instance.arrayNode().addAll(salesItems));
        return body_;
    }
	@Override
	public int compareTo(SalesItemGroup o) {
		return name.compareTo(o.name);
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
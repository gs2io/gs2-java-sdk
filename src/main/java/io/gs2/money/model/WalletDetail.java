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

package io.gs2.money.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * ウォレットの詳細
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class WalletDetail implements IModel, Serializable {
	/** 単価 */
	protected Float price;

	/**
	 * 単価を取得
	 *
	 * @return 単価
	 */
	public Float getPrice() {
		return price;
	}

	/**
	 * 単価を設定
	 *
	 * @param price 単価
	 */
	public void setPrice(Float price) {
		this.price = price;
	}

	/**
	 * 単価を設定
	 *
	 * @param price 単価
	 * @return this
	 */
	public WalletDetail withPrice(Float price) {
		this.price = price;
		return this;
	}
	/** 所持量 */
	protected Integer count;

	/**
	 * 所持量を取得
	 *
	 * @return 所持量
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * 所持量を設定
	 *
	 * @param count 所持量
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * 所持量を設定
	 *
	 * @param count 所持量
	 * @return this
	 */
	public WalletDetail withCount(Integer count) {
		this.count = count;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("price", this.getPrice())
            .put("count", this.getCount());
        return body_;
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.price == null) ? 0 : this.price.hashCode());
        result = prime * result + ((this.count == null) ? 0 : this.count.hashCode());
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
		WalletDetail other = (WalletDetail) o;
		if (price == null) {
			return other.price == null;
		} else if (!price.equals(other.price)) {
			return false;
		}
		if (count == null) {
			return other.count == null;
		} else if (!count.equals(other.count)) {
			return false;
		}
		return true;
	}
}
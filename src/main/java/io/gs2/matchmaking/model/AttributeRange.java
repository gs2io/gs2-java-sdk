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

package io.gs2.matchmaking.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * ギャザリング参加可能な属性値の範囲
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class AttributeRange implements IModel, Serializable {
	/** 属性名 */
	protected String name;

	/**
	 * 属性名を取得
	 *
	 * @return 属性名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 属性名を設定
	 *
	 * @param name 属性名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 属性名を設定
	 *
	 * @param name 属性名
	 * @return this
	 */
	public AttributeRange withName(String name) {
		this.name = name;
		return this;
	}
	/** ギャザリング参加可能な属性値の最小値 */
	protected Integer min;

	/**
	 * ギャザリング参加可能な属性値の最小値を取得
	 *
	 * @return ギャザリング参加可能な属性値の最小値
	 */
	public Integer getMin() {
		return min;
	}

	/**
	 * ギャザリング参加可能な属性値の最小値を設定
	 *
	 * @param min ギャザリング参加可能な属性値の最小値
	 */
	public void setMin(Integer min) {
		this.min = min;
	}

	/**
	 * ギャザリング参加可能な属性値の最小値を設定
	 *
	 * @param min ギャザリング参加可能な属性値の最小値
	 * @return this
	 */
	public AttributeRange withMin(Integer min) {
		this.min = min;
		return this;
	}
	/** ギャザリング参加可能な属性値の最大値 */
	protected Integer max;

	/**
	 * ギャザリング参加可能な属性値の最大値を取得
	 *
	 * @return ギャザリング参加可能な属性値の最大値
	 */
	public Integer getMax() {
		return max;
	}

	/**
	 * ギャザリング参加可能な属性値の最大値を設定
	 *
	 * @param max ギャザリング参加可能な属性値の最大値
	 */
	public void setMax(Integer max) {
		this.max = max;
	}

	/**
	 * ギャザリング参加可能な属性値の最大値を設定
	 *
	 * @param max ギャザリング参加可能な属性値の最大値
	 * @return this
	 */
	public AttributeRange withMax(Integer max) {
		this.max = max;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("name", this.getName())
            .put("min", this.getMin())
            .put("max", this.getMax());
        return body_;
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.min == null) ? 0 : this.min.hashCode());
        result = prime * result + ((this.max == null) ? 0 : this.max.hashCode());
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
		AttributeRange other = (AttributeRange) o;
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (min == null) {
			return other.min == null;
		} else if (!min.equals(other.min)) {
			return false;
		}
		if (max == null) {
			return other.max == null;
		} else if (!max.equals(other.max)) {
			return false;
		}
		return true;
	}
}
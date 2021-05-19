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

package io.gs2.ranking.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * 集計日時
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CalculatedAt implements IModel, Serializable {
	/** カテゴリ名 */
	protected String categoryName;

	/**
	 * カテゴリ名を取得
	 *
	 * @return カテゴリ名
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * カテゴリ名を設定
	 *
	 * @param categoryName カテゴリ名
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * カテゴリ名を設定
	 *
	 * @param categoryName カテゴリ名
	 * @return this
	 */
	public CalculatedAt withCategoryName(String categoryName) {
		this.categoryName = categoryName;
		return this;
	}
	/** 集計日時 */
	protected Long calculatedAt;

	/**
	 * 集計日時を取得
	 *
	 * @return 集計日時
	 */
	public Long getCalculatedAt() {
		return calculatedAt;
	}

	/**
	 * 集計日時を設定
	 *
	 * @param calculatedAt 集計日時
	 */
	public void setCalculatedAt(Long calculatedAt) {
		this.calculatedAt = calculatedAt;
	}

	/**
	 * 集計日時を設定
	 *
	 * @param calculatedAt 集計日時
	 * @return this
	 */
	public CalculatedAt withCalculatedAt(Long calculatedAt) {
		this.calculatedAt = calculatedAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("categoryName", this.getCategoryName())
            .put("calculatedAt", this.getCalculatedAt());
        return body_;
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.categoryName == null) ? 0 : this.categoryName.hashCode());
        result = prime * result + ((this.calculatedAt == null) ? 0 : this.calculatedAt.hashCode());
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
		CalculatedAt other = (CalculatedAt) o;
		if (categoryName == null) {
			return other.categoryName == null;
		} else if (!categoryName.equals(other.categoryName)) {
			return false;
		}
		if (calculatedAt == null) {
			return other.calculatedAt == null;
		} else if (!calculatedAt.equals(other.calculatedAt)) {
			return false;
		}
		return true;
	}
}
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

package io.gs2.enhance.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * 素材
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Material implements IModel, Serializable {
	/** 強化対象の GS2-Inventory アイテムセットGRN */
	protected String materialItemSetId;

	/**
	 * 強化対象の GS2-Inventory アイテムセットGRNを取得
	 *
	 * @return 強化対象の GS2-Inventory アイテムセットGRN
	 */
	public String getMaterialItemSetId() {
		return materialItemSetId;
	}

	/**
	 * 強化対象の GS2-Inventory アイテムセットGRNを設定
	 *
	 * @param materialItemSetId 強化対象の GS2-Inventory アイテムセットGRN
	 */
	public void setMaterialItemSetId(String materialItemSetId) {
		this.materialItemSetId = materialItemSetId;
	}

	/**
	 * 強化対象の GS2-Inventory アイテムセットGRNを設定
	 *
	 * @param materialItemSetId 強化対象の GS2-Inventory アイテムセットGRN
	 * @return this
	 */
	public Material withMaterialItemSetId(String materialItemSetId) {
		this.materialItemSetId = materialItemSetId;
		return this;
	}
	/** 消費数量 */
	protected Integer count;

	/**
	 * 消費数量を取得
	 *
	 * @return 消費数量
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * 消費数量を設定
	 *
	 * @param count 消費数量
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * 消費数量を設定
	 *
	 * @param count 消費数量
	 * @return this
	 */
	public Material withCount(Integer count) {
		this.count = count;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("materialItemSetId", this.getMaterialItemSetId())
            .put("count", this.getCount());
        return body_;
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.materialItemSetId == null) ? 0 : this.materialItemSetId.hashCode());
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
		Material other = (Material) o;
		if (materialItemSetId == null) {
			return other.materialItemSetId == null;
		} else if (!materialItemSetId.equals(other.materialItemSetId)) {
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
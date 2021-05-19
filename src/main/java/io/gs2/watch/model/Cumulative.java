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

package io.gs2.watch.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * 累積値
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Cumulative implements IModel, Serializable, Comparable<Cumulative> {
	/** 累積値 */
	protected String cumulativeId;

	/**
	 * 累積値を取得
	 *
	 * @return 累積値
	 */
	public String getCumulativeId() {
		return cumulativeId;
	}

	/**
	 * 累積値を設定
	 *
	 * @param cumulativeId 累積値
	 */
	public void setCumulativeId(String cumulativeId) {
		this.cumulativeId = cumulativeId;
	}

	/**
	 * 累積値を設定
	 *
	 * @param cumulativeId 累積値
	 * @return this
	 */
	public Cumulative withCumulativeId(String cumulativeId) {
		this.cumulativeId = cumulativeId;
		return this;
	}
	/** オーナーID */
	protected String ownerId;

	/**
	 * オーナーIDを取得
	 *
	 * @return オーナーID
	 */
	public String getOwnerId() {
		return ownerId;
	}

	/**
	 * オーナーIDを設定
	 *
	 * @param ownerId オーナーID
	 */
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * オーナーIDを設定
	 *
	 * @param ownerId オーナーID
	 * @return this
	 */
	public Cumulative withOwnerId(String ownerId) {
		this.ownerId = ownerId;
		return this;
	}
	/** リソースのGRN */
	protected String resourceGrn;

	/**
	 * リソースのGRNを取得
	 *
	 * @return リソースのGRN
	 */
	public String getResourceGrn() {
		return resourceGrn;
	}

	/**
	 * リソースのGRNを設定
	 *
	 * @param resourceGrn リソースのGRN
	 */
	public void setResourceGrn(String resourceGrn) {
		this.resourceGrn = resourceGrn;
	}

	/**
	 * リソースのGRNを設定
	 *
	 * @param resourceGrn リソースのGRN
	 * @return this
	 */
	public Cumulative withResourceGrn(String resourceGrn) {
		this.resourceGrn = resourceGrn;
		return this;
	}
	/** 名前 */
	protected String name;

	/**
	 * 名前を取得
	 *
	 * @return 名前
	 */
	public String getName() {
		return name;
	}

	/**
	 * 名前を設定
	 *
	 * @param name 名前
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 名前を設定
	 *
	 * @param name 名前
	 * @return this
	 */
	public Cumulative withName(String name) {
		this.name = name;
		return this;
	}
	/** 累積値 */
	protected Long value;

	/**
	 * 累積値を取得
	 *
	 * @return 累積値
	 */
	public Long getValue() {
		return value;
	}

	/**
	 * 累積値を設定
	 *
	 * @param value 累積値
	 */
	public void setValue(Long value) {
		this.value = value;
	}

	/**
	 * 累積値を設定
	 *
	 * @param value 累積値
	 * @return this
	 */
	public Cumulative withValue(Long value) {
		this.value = value;
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
	public Cumulative withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("cumulativeId", this.getCumulativeId())
            .put("ownerId", this.getOwnerId())
            .put("resourceGrn", this.getResourceGrn())
            .put("name", this.getName())
            .put("value", this.getValue())
            .put("updatedAt", this.getUpdatedAt());
        return body_;
    }
	@Override
	public int compareTo(Cumulative o) {
		return cumulativeId.compareTo(o.cumulativeId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.cumulativeId == null) ? 0 : this.cumulativeId.hashCode());
        result = prime * result + ((this.ownerId == null) ? 0 : this.ownerId.hashCode());
        result = prime * result + ((this.resourceGrn == null) ? 0 : this.resourceGrn.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.value == null) ? 0 : this.value.hashCode());
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
		Cumulative other = (Cumulative) o;
		if (cumulativeId == null) {
			return other.cumulativeId == null;
		} else if (!cumulativeId.equals(other.cumulativeId)) {
			return false;
		}
		if (ownerId == null) {
			return other.ownerId == null;
		} else if (!ownerId.equals(other.ownerId)) {
			return false;
		}
		if (resourceGrn == null) {
			return other.resourceGrn == null;
		} else if (!resourceGrn.equals(other.resourceGrn)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (value == null) {
			return other.value == null;
		} else if (!value.equals(other.value)) {
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
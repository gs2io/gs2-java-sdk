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

package io.gs2.formation.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * フォームの保存領域マスター
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class MoldModelMaster implements IModel, Serializable, Comparable<MoldModelMaster> {
	/** フォームの保存領域マスター */
	protected String moldModelId;

	/**
	 * フォームの保存領域マスターを取得
	 *
	 * @return フォームの保存領域マスター
	 */
	public String getMoldModelId() {
		return moldModelId;
	}

	/**
	 * フォームの保存領域マスターを設定
	 *
	 * @param moldModelId フォームの保存領域マスター
	 */
	public void setMoldModelId(String moldModelId) {
		this.moldModelId = moldModelId;
	}

	/**
	 * フォームの保存領域マスターを設定
	 *
	 * @param moldModelId フォームの保存領域マスター
	 * @return this
	 */
	public MoldModelMaster withMoldModelId(String moldModelId) {
		this.moldModelId = moldModelId;
		return this;
	}
	/** フォームの保存領域名 */
	protected String name;

	/**
	 * フォームの保存領域名を取得
	 *
	 * @return フォームの保存領域名
	 */
	public String getName() {
		return name;
	}

	/**
	 * フォームの保存領域名を設定
	 *
	 * @param name フォームの保存領域名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * フォームの保存領域名を設定
	 *
	 * @param name フォームの保存領域名
	 * @return this
	 */
	public MoldModelMaster withName(String name) {
		this.name = name;
		return this;
	}
	/** フォームの保存領域マスターの説明 */
	protected String description;

	/**
	 * フォームの保存領域マスターの説明を取得
	 *
	 * @return フォームの保存領域マスターの説明
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * フォームの保存領域マスターの説明を設定
	 *
	 * @param description フォームの保存領域マスターの説明
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * フォームの保存領域マスターの説明を設定
	 *
	 * @param description フォームの保存領域マスターの説明
	 * @return this
	 */
	public MoldModelMaster withDescription(String description) {
		this.description = description;
		return this;
	}
	/** フォームの保存領域のメタデータ */
	protected String metadata;

	/**
	 * フォームの保存領域のメタデータを取得
	 *
	 * @return フォームの保存領域のメタデータ
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * フォームの保存領域のメタデータを設定
	 *
	 * @param metadata フォームの保存領域のメタデータ
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	/**
	 * フォームの保存領域のメタデータを設定
	 *
	 * @param metadata フォームの保存領域のメタデータ
	 * @return this
	 */
	public MoldModelMaster withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	/** フォームを保存できる初期キャパシティ */
	protected Integer initialMaxCapacity;

	/**
	 * フォームを保存できる初期キャパシティを取得
	 *
	 * @return フォームを保存できる初期キャパシティ
	 */
	public Integer getInitialMaxCapacity() {
		return initialMaxCapacity;
	}

	/**
	 * フォームを保存できる初期キャパシティを設定
	 *
	 * @param initialMaxCapacity フォームを保存できる初期キャパシティ
	 */
	public void setInitialMaxCapacity(Integer initialMaxCapacity) {
		this.initialMaxCapacity = initialMaxCapacity;
	}

	/**
	 * フォームを保存できる初期キャパシティを設定
	 *
	 * @param initialMaxCapacity フォームを保存できる初期キャパシティ
	 * @return this
	 */
	public MoldModelMaster withInitialMaxCapacity(Integer initialMaxCapacity) {
		this.initialMaxCapacity = initialMaxCapacity;
		return this;
	}
	/** フォームを保存できるキャパシティ */
	protected Integer maxCapacity;

	/**
	 * フォームを保存できるキャパシティを取得
	 *
	 * @return フォームを保存できるキャパシティ
	 */
	public Integer getMaxCapacity() {
		return maxCapacity;
	}

	/**
	 * フォームを保存できるキャパシティを設定
	 *
	 * @param maxCapacity フォームを保存できるキャパシティ
	 */
	public void setMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	/**
	 * フォームを保存できるキャパシティを設定
	 *
	 * @param maxCapacity フォームを保存できるキャパシティ
	 * @return this
	 */
	public MoldModelMaster withMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
		return this;
	}
	/** フォーム名 */
	protected String formModelName;

	/**
	 * フォーム名を取得
	 *
	 * @return フォーム名
	 */
	public String getFormModelName() {
		return formModelName;
	}

	/**
	 * フォーム名を設定
	 *
	 * @param formModelName フォーム名
	 */
	public void setFormModelName(String formModelName) {
		this.formModelName = formModelName;
	}

	/**
	 * フォーム名を設定
	 *
	 * @param formModelName フォーム名
	 * @return this
	 */
	public MoldModelMaster withFormModelName(String formModelName) {
		this.formModelName = formModelName;
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
	public MoldModelMaster withCreatedAt(Long createdAt) {
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
	public MoldModelMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("moldModelId", this.getMoldModelId())
            .put("name", this.getName())
            .put("description", this.getDescription())
            .put("metadata", this.getMetadata())
            .put("initialMaxCapacity", this.getInitialMaxCapacity())
            .put("maxCapacity", this.getMaxCapacity())
            .put("formModelName", this.getFormModelName())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        return body_;
    }
	@Override
	public int compareTo(MoldModelMaster o) {
		return moldModelId.compareTo(o.moldModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.moldModelId == null) ? 0 : this.moldModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.initialMaxCapacity == null) ? 0 : this.initialMaxCapacity.hashCode());
        result = prime * result + ((this.maxCapacity == null) ? 0 : this.maxCapacity.hashCode());
        result = prime * result + ((this.formModelName == null) ? 0 : this.formModelName.hashCode());
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
		MoldModelMaster other = (MoldModelMaster) o;
		if (moldModelId == null) {
			return other.moldModelId == null;
		} else if (!moldModelId.equals(other.moldModelId)) {
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
		if (initialMaxCapacity == null) {
			return other.initialMaxCapacity == null;
		} else if (!initialMaxCapacity.equals(other.initialMaxCapacity)) {
			return false;
		}
		if (maxCapacity == null) {
			return other.maxCapacity == null;
		} else if (!maxCapacity.equals(other.maxCapacity)) {
			return false;
		}
		if (formModelName == null) {
			return other.formModelName == null;
		} else if (!formModelName.equals(other.formModelName)) {
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
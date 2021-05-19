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
 * フォームの保存領域
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class MoldModel implements IModel, Serializable, Comparable<MoldModel> {
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
	public MoldModel withMoldModelId(String moldModelId) {
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
	public MoldModel withName(String name) {
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
	public MoldModel withMetadata(String metadata) {
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
	public MoldModel withInitialMaxCapacity(Integer initialMaxCapacity) {
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
	public MoldModel withMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
		return this;
	}
	/** None */
	protected FormModel formModel;

	/**
	 * Noneを取得
	 *
	 * @return None
	 */
	public FormModel getFormModel() {
		return formModel;
	}

	/**
	 * Noneを設定
	 *
	 * @param formModel None
	 */
	public void setFormModel(FormModel formModel) {
		this.formModel = formModel;
	}

	/**
	 * Noneを設定
	 *
	 * @param formModel None
	 * @return this
	 */
	public MoldModel withFormModel(FormModel formModel) {
		this.formModel = formModel;
		return this;
	}

    public ObjectNode toJson() {
        JsonNode formModel = this.getFormModel().toJson();
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("moldModelId", this.getMoldModelId())
            .put("name", this.getName())
            .put("metadata", this.getMetadata())
            .put("initialMaxCapacity", this.getInitialMaxCapacity())
            .put("maxCapacity", this.getMaxCapacity());
        body_.set("formModel", formModel);
        return body_;
    }
	@Override
	public int compareTo(MoldModel o) {
		return moldModelId.compareTo(o.moldModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.moldModelId == null) ? 0 : this.moldModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.initialMaxCapacity == null) ? 0 : this.initialMaxCapacity.hashCode());
        result = prime * result + ((this.maxCapacity == null) ? 0 : this.maxCapacity.hashCode());
        result = prime * result + ((this.formModel == null) ? 0 : this.formModel.hashCode());
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
		MoldModel other = (MoldModel) o;
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
		if (formModel == null) {
			return other.formModel == null;
		} else if (!formModel.equals(other.formModel)) {
			return false;
		}
		return true;
	}
}
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
 * フォーム
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Form implements IModel, Serializable, Comparable<Form> {
	/** フォーム */
	protected String formId;

	/**
	 * フォームを取得
	 *
	 * @return フォーム
	 */
	public String getFormId() {
		return formId;
	}

	/**
	 * フォームを設定
	 *
	 * @param formId フォーム
	 */
	public void setFormId(String formId) {
		this.formId = formId;
	}

	/**
	 * フォームを設定
	 *
	 * @param formId フォーム
	 * @return this
	 */
	public Form withFormId(String formId) {
		this.formId = formId;
		return this;
	}
	/** フォームの保存領域の名前 */
	protected String name;

	/**
	 * フォームの保存領域の名前を取得
	 *
	 * @return フォームの保存領域の名前
	 */
	public String getName() {
		return name;
	}

	/**
	 * フォームの保存領域の名前を設定
	 *
	 * @param name フォームの保存領域の名前
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * フォームの保存領域の名前を設定
	 *
	 * @param name フォームの保存領域の名前
	 * @return this
	 */
	public Form withName(String name) {
		this.name = name;
		return this;
	}
	/** 保存領域のインデックス */
	protected Integer index;

	/**
	 * 保存領域のインデックスを取得
	 *
	 * @return 保存領域のインデックス
	 */
	public Integer getIndex() {
		return index;
	}

	/**
	 * 保存領域のインデックスを設定
	 *
	 * @param index 保存領域のインデックス
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}

	/**
	 * 保存領域のインデックスを設定
	 *
	 * @param index 保存領域のインデックス
	 * @return this
	 */
	public Form withIndex(Integer index) {
		this.index = index;
		return this;
	}
	/** スロットリスト */
	protected List<Slot> slots;

	/**
	 * スロットリストを取得
	 *
	 * @return スロットリスト
	 */
	public List<Slot> getSlots() {
		return slots;
	}

	/**
	 * スロットリストを設定
	 *
	 * @param slots スロットリスト
	 */
	public void setSlots(List<Slot> slots) {
		this.slots = slots;
	}

	/**
	 * スロットリストを設定
	 *
	 * @param slots スロットリスト
	 * @return this
	 */
	public Form withSlots(List<Slot> slots) {
		this.slots = slots;
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
	public Form withCreatedAt(Long createdAt) {
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
	public Form withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> slots = new ArrayList<>();
        if(this.slots != null) {
            for(Slot item : this.slots) {
                slots.add(item.toJson());
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("formId", this.getFormId())
            .put("name", this.getName())
            .put("index", this.getIndex())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        body_.set("slots", JsonNodeFactory.instance.arrayNode().addAll(slots));
        return body_;
    }
	@Override
	public int compareTo(Form o) {
		return formId.compareTo(o.formId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.formId == null) ? 0 : this.formId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.index == null) ? 0 : this.index.hashCode());
        result = prime * result + ((this.slots == null) ? 0 : this.slots.hashCode());
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
		Form other = (Form) o;
		if (formId == null) {
			return other.formId == null;
		} else if (!formId.equals(other.formId)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (index == null) {
			return other.index == null;
		} else if (!index.equals(other.index)) {
			return false;
		}
		if (slots == null) {
			return other.slots == null;
		} else if (!slots.equals(other.slots)) {
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
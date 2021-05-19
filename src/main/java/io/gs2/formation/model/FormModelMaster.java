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
 * フォームマスター
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class FormModelMaster implements IModel, Serializable, Comparable<FormModelMaster> {
	/** フォームマスター */
	protected String formModelId;

	/**
	 * フォームマスターを取得
	 *
	 * @return フォームマスター
	 */
	public String getFormModelId() {
		return formModelId;
	}

	/**
	 * フォームマスターを設定
	 *
	 * @param formModelId フォームマスター
	 */
	public void setFormModelId(String formModelId) {
		this.formModelId = formModelId;
	}

	/**
	 * フォームマスターを設定
	 *
	 * @param formModelId フォームマスター
	 * @return this
	 */
	public FormModelMaster withFormModelId(String formModelId) {
		this.formModelId = formModelId;
		return this;
	}
	/** フォーム名 */
	protected String name;

	/**
	 * フォーム名を取得
	 *
	 * @return フォーム名
	 */
	public String getName() {
		return name;
	}

	/**
	 * フォーム名を設定
	 *
	 * @param name フォーム名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * フォーム名を設定
	 *
	 * @param name フォーム名
	 * @return this
	 */
	public FormModelMaster withName(String name) {
		this.name = name;
		return this;
	}
	/** フォームマスターの説明 */
	protected String description;

	/**
	 * フォームマスターの説明を取得
	 *
	 * @return フォームマスターの説明
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * フォームマスターの説明を設定
	 *
	 * @param description フォームマスターの説明
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * フォームマスターの説明を設定
	 *
	 * @param description フォームマスターの説明
	 * @return this
	 */
	public FormModelMaster withDescription(String description) {
		this.description = description;
		return this;
	}
	/** フォームのメタデータ */
	protected String metadata;

	/**
	 * フォームのメタデータを取得
	 *
	 * @return フォームのメタデータ
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * フォームのメタデータを設定
	 *
	 * @param metadata フォームのメタデータ
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	/**
	 * フォームのメタデータを設定
	 *
	 * @param metadata フォームのメタデータ
	 * @return this
	 */
	public FormModelMaster withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	/** スロットリスト */
	protected List<SlotModel> slots;

	/**
	 * スロットリストを取得
	 *
	 * @return スロットリスト
	 */
	public List<SlotModel> getSlots() {
		return slots;
	}

	/**
	 * スロットリストを設定
	 *
	 * @param slots スロットリスト
	 */
	public void setSlots(List<SlotModel> slots) {
		this.slots = slots;
	}

	/**
	 * スロットリストを設定
	 *
	 * @param slots スロットリスト
	 * @return this
	 */
	public FormModelMaster withSlots(List<SlotModel> slots) {
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
	public FormModelMaster withCreatedAt(Long createdAt) {
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
	public FormModelMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> slots = new ArrayList<>();
        if(this.slots != null) {
            for(SlotModel item : this.slots) {
                slots.add(item.toJson());
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("formModelId", this.getFormModelId())
            .put("name", this.getName())
            .put("description", this.getDescription())
            .put("metadata", this.getMetadata())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        body_.set("slots", JsonNodeFactory.instance.arrayNode().addAll(slots));
        return body_;
    }
	@Override
	public int compareTo(FormModelMaster o) {
		return formModelId.compareTo(o.formModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.formModelId == null) ? 0 : this.formModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
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
		FormModelMaster other = (FormModelMaster) o;
		if (formModelId == null) {
			return other.formModelId == null;
		} else if (!formModelId.equals(other.formModelId)) {
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
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
 * フォームモデル
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class FormModel implements IModel, Serializable, Comparable<FormModel> {
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
	public FormModel withFormModelId(String formModelId) {
		this.formModelId = formModelId;
		return this;
	}
	/** フォームの種類名 */
	protected String name;

	/**
	 * フォームの種類名を取得
	 *
	 * @return フォームの種類名
	 */
	public String getName() {
		return name;
	}

	/**
	 * フォームの種類名を設定
	 *
	 * @param name フォームの種類名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * フォームの種類名を設定
	 *
	 * @param name フォームの種類名
	 * @return this
	 */
	public FormModel withName(String name) {
		this.name = name;
		return this;
	}
	/** フォームの種類のメタデータ */
	protected String metadata;

	/**
	 * フォームの種類のメタデータを取得
	 *
	 * @return フォームの種類のメタデータ
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * フォームの種類のメタデータを設定
	 *
	 * @param metadata フォームの種類のメタデータ
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	/**
	 * フォームの種類のメタデータを設定
	 *
	 * @param metadata フォームの種類のメタデータ
	 * @return this
	 */
	public FormModel withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	/** スリットリスト */
	protected List<SlotModel> slots;

	/**
	 * スリットリストを取得
	 *
	 * @return スリットリスト
	 */
	public List<SlotModel> getSlots() {
		return slots;
	}

	/**
	 * スリットリストを設定
	 *
	 * @param slots スリットリスト
	 */
	public void setSlots(List<SlotModel> slots) {
		this.slots = slots;
	}

	/**
	 * スリットリストを設定
	 *
	 * @param slots スリットリスト
	 * @return this
	 */
	public FormModel withSlots(List<SlotModel> slots) {
		this.slots = slots;
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
            .put("metadata", this.getMetadata());
        body_.set("slots", JsonNodeFactory.instance.arrayNode().addAll(slots));
        return body_;
    }
	@Override
	public int compareTo(FormModel o) {
		return formModelId.compareTo(o.formModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.formModelId == null) ? 0 : this.formModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.slots == null) ? 0 : this.slots.hashCode());
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
		FormModel other = (FormModel) o;
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
		return true;
	}
}
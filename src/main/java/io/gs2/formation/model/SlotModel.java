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
 * スロットモデル
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class SlotModel implements IModel, Serializable {
	/** スロットモデル名 */
	protected String name;

	/**
	 * スロットモデル名を取得
	 *
	 * @return スロットモデル名
	 */
	public String getName() {
		return name;
	}

	/**
	 * スロットモデル名を設定
	 *
	 * @param name スロットモデル名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * スロットモデル名を設定
	 *
	 * @param name スロットモデル名
	 * @return this
	 */
	public SlotModel withName(String name) {
		this.name = name;
		return this;
	}
	/** プロパティとして設定可能な値の正規表現 */
	protected String propertyRegex;

	/**
	 * プロパティとして設定可能な値の正規表現を取得
	 *
	 * @return プロパティとして設定可能な値の正規表現
	 */
	public String getPropertyRegex() {
		return propertyRegex;
	}

	/**
	 * プロパティとして設定可能な値の正規表現を設定
	 *
	 * @param propertyRegex プロパティとして設定可能な値の正規表現
	 */
	public void setPropertyRegex(String propertyRegex) {
		this.propertyRegex = propertyRegex;
	}

	/**
	 * プロパティとして設定可能な値の正規表現を設定
	 *
	 * @param propertyRegex プロパティとして設定可能な値の正規表現
	 * @return this
	 */
	public SlotModel withPropertyRegex(String propertyRegex) {
		this.propertyRegex = propertyRegex;
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
	public SlotModel withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("name", this.getName())
            .put("propertyRegex", this.getPropertyRegex())
            .put("metadata", this.getMetadata());
        return body_;
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.propertyRegex == null) ? 0 : this.propertyRegex.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
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
		SlotModel other = (SlotModel) o;
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (propertyRegex == null) {
			return other.propertyRegex == null;
		} else if (!propertyRegex.equals(other.propertyRegex)) {
			return false;
		}
		if (metadata == null) {
			return other.metadata == null;
		} else if (!metadata.equals(other.metadata)) {
			return false;
		}
		return true;
	}
}
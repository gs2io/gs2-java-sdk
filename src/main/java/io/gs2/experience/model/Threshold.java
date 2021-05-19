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

package io.gs2.experience.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * ランクアップ閾値
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Threshold implements IModel, Serializable {
	/** ランクアップ閾値のメタデータ */
	protected String metadata;

	/**
	 * ランクアップ閾値のメタデータを取得
	 *
	 * @return ランクアップ閾値のメタデータ
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * ランクアップ閾値のメタデータを設定
	 *
	 * @param metadata ランクアップ閾値のメタデータ
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	/**
	 * ランクアップ閾値のメタデータを設定
	 *
	 * @param metadata ランクアップ閾値のメタデータ
	 * @return this
	 */
	public Threshold withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	/** ランクアップ経験値閾値リスト */
	protected List<Long> values;

	/**
	 * ランクアップ経験値閾値リストを取得
	 *
	 * @return ランクアップ経験値閾値リスト
	 */
	public List<Long> getValues() {
		return values;
	}

	/**
	 * ランクアップ経験値閾値リストを設定
	 *
	 * @param values ランクアップ経験値閾値リスト
	 */
	public void setValues(List<Long> values) {
		this.values = values;
	}

	/**
	 * ランクアップ経験値閾値リストを設定
	 *
	 * @param values ランクアップ経験値閾値リスト
	 * @return this
	 */
	public Threshold withValues(List<Long> values) {
		this.values = values;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> values = new ArrayList<>();
        if(this.values != null) {
            for(Long item : this.values) {
                values.add(JsonNodeFactory.instance.numberNode(item));
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("metadata", this.getMetadata());
        body_.set("values", JsonNodeFactory.instance.arrayNode().addAll(values));
        return body_;
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.values == null) ? 0 : this.values.hashCode());
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
		Threshold other = (Threshold) o;
		if (metadata == null) {
			return other.metadata == null;
		} else if (!metadata.equals(other.metadata)) {
			return false;
		}
		if (values == null) {
			return other.values == null;
		} else if (!values.equals(other.values)) {
			return false;
		}
		return true;
	}
}
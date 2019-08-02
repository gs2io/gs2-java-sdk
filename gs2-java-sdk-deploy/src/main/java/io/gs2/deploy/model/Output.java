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

package io.gs2.deploy.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.model.IModel;

/**
 * アウトプット
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Output implements IModel, Serializable, Comparable<Output> {
	/** アウトプット */
	protected String outputId;

	/**
	 * アウトプットを取得
	 *
	 * @return アウトプット
	 */
	public String getOutputId() {
		return outputId;
	}

	/**
	 * アウトプットを設定
	 *
	 * @param outputId アウトプット
	 */
	public void setOutputId(String outputId) {
		this.outputId = outputId;
	}

	/**
	 * アウトプットを設定
	 *
	 * @param outputId アウトプット
	 * @return this
	 */
	public Output withOutputId(String outputId) {
		this.outputId = outputId;
		return this;
	}
	/** アウトプット名 */
	protected String name;

	/**
	 * アウトプット名を取得
	 *
	 * @return アウトプット名
	 */
	public String getName() {
		return name;
	}

	/**
	 * アウトプット名を設定
	 *
	 * @param name アウトプット名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * アウトプット名を設定
	 *
	 * @param name アウトプット名
	 * @return this
	 */
	public Output withName(String name) {
		this.name = name;
		return this;
	}
	/** 値 */
	protected String value;

	/**
	 * 値を取得
	 *
	 * @return 値
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 値を設定
	 *
	 * @param value 値
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * 値を設定
	 *
	 * @param value 値
	 * @return this
	 */
	public Output withValue(String value) {
		this.value = value;
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
	public Output withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("outputId", this.getOutputId())
            .put("name", this.getName())
            .put("value", this.getValue())
            .put("createdAt", this.getCreatedAt());
        return body_;
    }
	@Override
	public int compareTo(Output o) {
		return outputId.compareTo(o.outputId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.outputId == null) ? 0 : this.outputId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.value == null) ? 0 : this.value.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
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
		Output other = (Output) o;
		if (outputId == null) {
			return other.outputId == null;
		} else if (!outputId.equals(other.outputId)) {
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
		if (createdAt == null) {
			return other.createdAt == null;
		} else if (!createdAt.equals(other.createdAt)) {
			return false;
		}
		return true;
	}
}
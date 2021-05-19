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

package io.gs2.datastore.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * データオブジェクト履歴
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class DataObjectHistory implements IModel, Serializable, Comparable<DataObjectHistory> {
	/** データオブジェクト履歴 */
	protected String dataObjectHistoryId;

	/**
	 * データオブジェクト履歴を取得
	 *
	 * @return データオブジェクト履歴
	 */
	public String getDataObjectHistoryId() {
		return dataObjectHistoryId;
	}

	/**
	 * データオブジェクト履歴を設定
	 *
	 * @param dataObjectHistoryId データオブジェクト履歴
	 */
	public void setDataObjectHistoryId(String dataObjectHistoryId) {
		this.dataObjectHistoryId = dataObjectHistoryId;
	}

	/**
	 * データオブジェクト履歴を設定
	 *
	 * @param dataObjectHistoryId データオブジェクト履歴
	 * @return this
	 */
	public DataObjectHistory withDataObjectHistoryId(String dataObjectHistoryId) {
		this.dataObjectHistoryId = dataObjectHistoryId;
		return this;
	}
	/** データオブジェクト名 */
	protected String dataObjectName;

	/**
	 * データオブジェクト名を取得
	 *
	 * @return データオブジェクト名
	 */
	public String getDataObjectName() {
		return dataObjectName;
	}

	/**
	 * データオブジェクト名を設定
	 *
	 * @param dataObjectName データオブジェクト名
	 */
	public void setDataObjectName(String dataObjectName) {
		this.dataObjectName = dataObjectName;
	}

	/**
	 * データオブジェクト名を設定
	 *
	 * @param dataObjectName データオブジェクト名
	 * @return this
	 */
	public DataObjectHistory withDataObjectName(String dataObjectName) {
		this.dataObjectName = dataObjectName;
		return this;
	}
	/** 世代ID */
	protected String generation;

	/**
	 * 世代IDを取得
	 *
	 * @return 世代ID
	 */
	public String getGeneration() {
		return generation;
	}

	/**
	 * 世代IDを設定
	 *
	 * @param generation 世代ID
	 */
	public void setGeneration(String generation) {
		this.generation = generation;
	}

	/**
	 * 世代IDを設定
	 *
	 * @param generation 世代ID
	 * @return this
	 */
	public DataObjectHistory withGeneration(String generation) {
		this.generation = generation;
		return this;
	}
	/** データサイズ */
	protected Long contentLength;

	/**
	 * データサイズを取得
	 *
	 * @return データサイズ
	 */
	public Long getContentLength() {
		return contentLength;
	}

	/**
	 * データサイズを設定
	 *
	 * @param contentLength データサイズ
	 */
	public void setContentLength(Long contentLength) {
		this.contentLength = contentLength;
	}

	/**
	 * データサイズを設定
	 *
	 * @param contentLength データサイズ
	 * @return this
	 */
	public DataObjectHistory withContentLength(Long contentLength) {
		this.contentLength = contentLength;
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
	public DataObjectHistory withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("dataObjectHistoryId", this.getDataObjectHistoryId())
            .put("dataObjectName", this.getDataObjectName())
            .put("generation", this.getGeneration())
            .put("contentLength", this.getContentLength())
            .put("createdAt", this.getCreatedAt());
        return body_;
    }
	@Override
	public int compareTo(DataObjectHistory o) {
		return dataObjectHistoryId.compareTo(o.dataObjectHistoryId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.dataObjectHistoryId == null) ? 0 : this.dataObjectHistoryId.hashCode());
        result = prime * result + ((this.dataObjectName == null) ? 0 : this.dataObjectName.hashCode());
        result = prime * result + ((this.generation == null) ? 0 : this.generation.hashCode());
        result = prime * result + ((this.contentLength == null) ? 0 : this.contentLength.hashCode());
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
		DataObjectHistory other = (DataObjectHistory) o;
		if (dataObjectHistoryId == null) {
			return other.dataObjectHistoryId == null;
		} else if (!dataObjectHistoryId.equals(other.dataObjectHistoryId)) {
			return false;
		}
		if (dataObjectName == null) {
			return other.dataObjectName == null;
		} else if (!dataObjectName.equals(other.dataObjectName)) {
			return false;
		}
		if (generation == null) {
			return other.generation == null;
		} else if (!generation.equals(other.generation)) {
			return false;
		}
		if (contentLength == null) {
			return other.contentLength == null;
		} else if (!contentLength.equals(other.contentLength)) {
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
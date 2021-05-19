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

package io.gs2.dictionary.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * エントリーモデルマスター
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EntryModelMaster implements IModel, Serializable, Comparable<EntryModelMaster> {
	/** エントリーモデルマスター */
	protected String entryModelId;

	/**
	 * エントリーモデルマスターを取得
	 *
	 * @return エントリーモデルマスター
	 */
	public String getEntryModelId() {
		return entryModelId;
	}

	/**
	 * エントリーモデルマスターを設定
	 *
	 * @param entryModelId エントリーモデルマスター
	 */
	public void setEntryModelId(String entryModelId) {
		this.entryModelId = entryModelId;
	}

	/**
	 * エントリーモデルマスターを設定
	 *
	 * @param entryModelId エントリーモデルマスター
	 * @return this
	 */
	public EntryModelMaster withEntryModelId(String entryModelId) {
		this.entryModelId = entryModelId;
		return this;
	}
	/** エントリーモデル名 */
	protected String name;

	/**
	 * エントリーモデル名を取得
	 *
	 * @return エントリーモデル名
	 */
	public String getName() {
		return name;
	}

	/**
	 * エントリーモデル名を設定
	 *
	 * @param name エントリーモデル名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * エントリーモデル名を設定
	 *
	 * @param name エントリーモデル名
	 * @return this
	 */
	public EntryModelMaster withName(String name) {
		this.name = name;
		return this;
	}
	/** エントリーモデルマスターの説明 */
	protected String description;

	/**
	 * エントリーモデルマスターの説明を取得
	 *
	 * @return エントリーモデルマスターの説明
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * エントリーモデルマスターの説明を設定
	 *
	 * @param description エントリーモデルマスターの説明
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * エントリーモデルマスターの説明を設定
	 *
	 * @param description エントリーモデルマスターの説明
	 * @return this
	 */
	public EntryModelMaster withDescription(String description) {
		this.description = description;
		return this;
	}
	/** エントリーモデルのメタデータ */
	protected String metadata;

	/**
	 * エントリーモデルのメタデータを取得
	 *
	 * @return エントリーモデルのメタデータ
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * エントリーモデルのメタデータを設定
	 *
	 * @param metadata エントリーモデルのメタデータ
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	/**
	 * エントリーモデルのメタデータを設定
	 *
	 * @param metadata エントリーモデルのメタデータ
	 * @return this
	 */
	public EntryModelMaster withMetadata(String metadata) {
		this.metadata = metadata;
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
	public EntryModelMaster withCreatedAt(Long createdAt) {
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
	public EntryModelMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("entryModelId", this.getEntryModelId())
            .put("name", this.getName())
            .put("description", this.getDescription())
            .put("metadata", this.getMetadata())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        return body_;
    }
	@Override
	public int compareTo(EntryModelMaster o) {
		return entryModelId.compareTo(o.entryModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.entryModelId == null) ? 0 : this.entryModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
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
		EntryModelMaster other = (EntryModelMaster) o;
		if (entryModelId == null) {
			return other.entryModelId == null;
		} else if (!entryModelId.equals(other.entryModelId)) {
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
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
 * エントリーモデル
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EntryModel implements IModel, Serializable, Comparable<EntryModel> {
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
	public EntryModel withEntryModelId(String entryModelId) {
		this.entryModelId = entryModelId;
		return this;
	}
	/** エントリーの種類名 */
	protected String name;

	/**
	 * エントリーの種類名を取得
	 *
	 * @return エントリーの種類名
	 */
	public String getName() {
		return name;
	}

	/**
	 * エントリーの種類名を設定
	 *
	 * @param name エントリーの種類名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * エントリーの種類名を設定
	 *
	 * @param name エントリーの種類名
	 * @return this
	 */
	public EntryModel withName(String name) {
		this.name = name;
		return this;
	}
	/** エントリーの種類のメタデータ */
	protected String metadata;

	/**
	 * エントリーの種類のメタデータを取得
	 *
	 * @return エントリーの種類のメタデータ
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * エントリーの種類のメタデータを設定
	 *
	 * @param metadata エントリーの種類のメタデータ
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	/**
	 * エントリーの種類のメタデータを設定
	 *
	 * @param metadata エントリーの種類のメタデータ
	 * @return this
	 */
	public EntryModel withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("entryModelId", this.getEntryModelId())
            .put("name", this.getName())
            .put("metadata", this.getMetadata());
        return body_;
    }
	@Override
	public int compareTo(EntryModel o) {
		return entryModelId.compareTo(o.entryModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.entryModelId == null) ? 0 : this.entryModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
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
		EntryModel other = (EntryModel) o;
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
		if (metadata == null) {
			return other.metadata == null;
		} else if (!metadata.equals(other.metadata)) {
			return false;
		}
		return true;
	}
}
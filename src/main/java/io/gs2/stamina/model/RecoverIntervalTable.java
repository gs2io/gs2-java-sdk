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

package io.gs2.stamina.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * スタミナ回復間隔テーブル
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class RecoverIntervalTable implements IModel, Serializable, Comparable<RecoverIntervalTable> {
	/** スタミナ回復間隔テーブルマスター */
	protected String recoverIntervalTableId;

	/**
	 * スタミナ回復間隔テーブルマスターを取得
	 *
	 * @return スタミナ回復間隔テーブルマスター
	 */
	public String getRecoverIntervalTableId() {
		return recoverIntervalTableId;
	}

	/**
	 * スタミナ回復間隔テーブルマスターを設定
	 *
	 * @param recoverIntervalTableId スタミナ回復間隔テーブルマスター
	 */
	public void setRecoverIntervalTableId(String recoverIntervalTableId) {
		this.recoverIntervalTableId = recoverIntervalTableId;
	}

	/**
	 * スタミナ回復間隔テーブルマスターを設定
	 *
	 * @param recoverIntervalTableId スタミナ回復間隔テーブルマスター
	 * @return this
	 */
	public RecoverIntervalTable withRecoverIntervalTableId(String recoverIntervalTableId) {
		this.recoverIntervalTableId = recoverIntervalTableId;
		return this;
	}
	/** スタミナ回復間隔テーブル名 */
	protected String name;

	/**
	 * スタミナ回復間隔テーブル名を取得
	 *
	 * @return スタミナ回復間隔テーブル名
	 */
	public String getName() {
		return name;
	}

	/**
	 * スタミナ回復間隔テーブル名を設定
	 *
	 * @param name スタミナ回復間隔テーブル名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * スタミナ回復間隔テーブル名を設定
	 *
	 * @param name スタミナ回復間隔テーブル名
	 * @return this
	 */
	public RecoverIntervalTable withName(String name) {
		this.name = name;
		return this;
	}
	/** スタミナ回復間隔テーブルのメタデータ */
	protected String metadata;

	/**
	 * スタミナ回復間隔テーブルのメタデータを取得
	 *
	 * @return スタミナ回復間隔テーブルのメタデータ
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * スタミナ回復間隔テーブルのメタデータを設定
	 *
	 * @param metadata スタミナ回復間隔テーブルのメタデータ
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	/**
	 * スタミナ回復間隔テーブルのメタデータを設定
	 *
	 * @param metadata スタミナ回復間隔テーブルのメタデータ
	 * @return this
	 */
	public RecoverIntervalTable withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	/** 経験値の種類マスター のGRN */
	protected String experienceModelId;

	/**
	 * 経験値の種類マスター のGRNを取得
	 *
	 * @return 経験値の種類マスター のGRN
	 */
	public String getExperienceModelId() {
		return experienceModelId;
	}

	/**
	 * 経験値の種類マスター のGRNを設定
	 *
	 * @param experienceModelId 経験値の種類マスター のGRN
	 */
	public void setExperienceModelId(String experienceModelId) {
		this.experienceModelId = experienceModelId;
	}

	/**
	 * 経験値の種類マスター のGRNを設定
	 *
	 * @param experienceModelId 経験値の種類マスター のGRN
	 * @return this
	 */
	public RecoverIntervalTable withExperienceModelId(String experienceModelId) {
		this.experienceModelId = experienceModelId;
		return this;
	}
	/** ランク毎のスタミナ回復間隔テーブル */
	protected List<Integer> values;

	/**
	 * ランク毎のスタミナ回復間隔テーブルを取得
	 *
	 * @return ランク毎のスタミナ回復間隔テーブル
	 */
	public List<Integer> getValues() {
		return values;
	}

	/**
	 * ランク毎のスタミナ回復間隔テーブルを設定
	 *
	 * @param values ランク毎のスタミナ回復間隔テーブル
	 */
	public void setValues(List<Integer> values) {
		this.values = values;
	}

	/**
	 * ランク毎のスタミナ回復間隔テーブルを設定
	 *
	 * @param values ランク毎のスタミナ回復間隔テーブル
	 * @return this
	 */
	public RecoverIntervalTable withValues(List<Integer> values) {
		this.values = values;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> values = new ArrayList<>();
        if(this.values != null) {
            for(Integer item : this.values) {
                values.add(JsonNodeFactory.instance.numberNode(item));
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("recoverIntervalTableId", this.getRecoverIntervalTableId())
            .put("name", this.getName())
            .put("metadata", this.getMetadata())
            .put("experienceModelId", this.getExperienceModelId());
        body_.set("values", JsonNodeFactory.instance.arrayNode().addAll(values));
        return body_;
    }
	@Override
	public int compareTo(RecoverIntervalTable o) {
		return recoverIntervalTableId.compareTo(o.recoverIntervalTableId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.recoverIntervalTableId == null) ? 0 : this.recoverIntervalTableId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.experienceModelId == null) ? 0 : this.experienceModelId.hashCode());
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
		RecoverIntervalTable other = (RecoverIntervalTable) o;
		if (recoverIntervalTableId == null) {
			return other.recoverIntervalTableId == null;
		} else if (!recoverIntervalTableId.equals(other.recoverIntervalTableId)) {
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
		if (experienceModelId == null) {
			return other.experienceModelId == null;
		} else if (!experienceModelId.equals(other.experienceModelId)) {
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
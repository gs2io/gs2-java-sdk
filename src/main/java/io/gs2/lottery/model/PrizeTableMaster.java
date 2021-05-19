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

package io.gs2.lottery.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * 排出確率テーブルマスター
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class PrizeTableMaster implements IModel, Serializable, Comparable<PrizeTableMaster> {
	/** 排出確率テーブルマスター */
	protected String prizeTableId;

	/**
	 * 排出確率テーブルマスターを取得
	 *
	 * @return 排出確率テーブルマスター
	 */
	public String getPrizeTableId() {
		return prizeTableId;
	}

	/**
	 * 排出確率テーブルマスターを設定
	 *
	 * @param prizeTableId 排出確率テーブルマスター
	 */
	public void setPrizeTableId(String prizeTableId) {
		this.prizeTableId = prizeTableId;
	}

	/**
	 * 排出確率テーブルマスターを設定
	 *
	 * @param prizeTableId 排出確率テーブルマスター
	 * @return this
	 */
	public PrizeTableMaster withPrizeTableId(String prizeTableId) {
		this.prizeTableId = prizeTableId;
		return this;
	}
	/** 排出確率テーブル名 */
	protected String name;

	/**
	 * 排出確率テーブル名を取得
	 *
	 * @return 排出確率テーブル名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 排出確率テーブル名を設定
	 *
	 * @param name 排出確率テーブル名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 排出確率テーブル名を設定
	 *
	 * @param name 排出確率テーブル名
	 * @return this
	 */
	public PrizeTableMaster withName(String name) {
		this.name = name;
		return this;
	}
	/** 排出確率テーブルのメタデータ */
	protected String metadata;

	/**
	 * 排出確率テーブルのメタデータを取得
	 *
	 * @return 排出確率テーブルのメタデータ
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * 排出確率テーブルのメタデータを設定
	 *
	 * @param metadata 排出確率テーブルのメタデータ
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	/**
	 * 排出確率テーブルのメタデータを設定
	 *
	 * @param metadata 排出確率テーブルのメタデータ
	 * @return this
	 */
	public PrizeTableMaster withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	/** 排出確率テーブルマスターの説明 */
	protected String description;

	/**
	 * 排出確率テーブルマスターの説明を取得
	 *
	 * @return 排出確率テーブルマスターの説明
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 排出確率テーブルマスターの説明を設定
	 *
	 * @param description 排出確率テーブルマスターの説明
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 排出確率テーブルマスターの説明を設定
	 *
	 * @param description 排出確率テーブルマスターの説明
	 * @return this
	 */
	public PrizeTableMaster withDescription(String description) {
		this.description = description;
		return this;
	}
	/** 景品リスト */
	protected List<Prize> prizes;

	/**
	 * 景品リストを取得
	 *
	 * @return 景品リスト
	 */
	public List<Prize> getPrizes() {
		return prizes;
	}

	/**
	 * 景品リストを設定
	 *
	 * @param prizes 景品リスト
	 */
	public void setPrizes(List<Prize> prizes) {
		this.prizes = prizes;
	}

	/**
	 * 景品リストを設定
	 *
	 * @param prizes 景品リスト
	 * @return this
	 */
	public PrizeTableMaster withPrizes(List<Prize> prizes) {
		this.prizes = prizes;
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
	public PrizeTableMaster withCreatedAt(Long createdAt) {
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
	public PrizeTableMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> prizes = new ArrayList<>();
        if(this.prizes != null) {
            for(Prize item : this.prizes) {
                prizes.add(item.toJson());
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("prizeTableId", this.getPrizeTableId())
            .put("name", this.getName())
            .put("metadata", this.getMetadata())
            .put("description", this.getDescription())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        body_.set("prizes", JsonNodeFactory.instance.arrayNode().addAll(prizes));
        return body_;
    }
	@Override
	public int compareTo(PrizeTableMaster o) {
		return prizeTableId.compareTo(o.prizeTableId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.prizeTableId == null) ? 0 : this.prizeTableId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.prizes == null) ? 0 : this.prizes.hashCode());
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
		PrizeTableMaster other = (PrizeTableMaster) o;
		if (prizeTableId == null) {
			return other.prizeTableId == null;
		} else if (!prizeTableId.equals(other.prizeTableId)) {
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
		if (description == null) {
			return other.description == null;
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (prizes == null) {
			return other.prizes == null;
		} else if (!prizes.equals(other.prizes)) {
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
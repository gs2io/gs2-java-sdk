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
import io.gs2.model.IModel;

/**
 * 排出確率テーブル
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class PrizeTable implements IModel, Serializable, Comparable<PrizeTable> {
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
	public PrizeTable withPrizeTableId(String prizeTableId) {
		this.prizeTableId = prizeTableId;
		return this;
	}
	/** 景品テーブル名 */
	protected String name;

	/**
	 * 景品テーブル名を取得
	 *
	 * @return 景品テーブル名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 景品テーブル名を設定
	 *
	 * @param name 景品テーブル名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 景品テーブル名を設定
	 *
	 * @param name 景品テーブル名
	 * @return this
	 */
	public PrizeTable withName(String name) {
		this.name = name;
		return this;
	}
	/** 景品テーブルのメタデータ */
	protected String metadata;

	/**
	 * 景品テーブルのメタデータを取得
	 *
	 * @return 景品テーブルのメタデータ
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * 景品テーブルのメタデータを設定
	 *
	 * @param metadata 景品テーブルのメタデータ
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	/**
	 * 景品テーブルのメタデータを設定
	 *
	 * @param metadata 景品テーブルのメタデータ
	 * @return this
	 */
	public PrizeTable withMetadata(String metadata) {
		this.metadata = metadata;
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
	public PrizeTable withPrizes(List<Prize> prizes) {
		this.prizes = prizes;
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
            .put("metadata", this.getMetadata());
        body_.set("prizes", JsonNodeFactory.instance.arrayNode().addAll(prizes));
        return body_;
    }
	@Override
	public int compareTo(PrizeTable o) {
		return prizeTableId.compareTo(o.prizeTableId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.prizeTableId == null) ? 0 : this.prizeTableId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.prizes == null) ? 0 : this.prizes.hashCode());
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
		PrizeTable other = (PrizeTable) o;
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
		if (prizes == null) {
			return other.prizes == null;
		} else if (!prizes.equals(other.prizes)) {
			return false;
		}
		return true;
	}
}
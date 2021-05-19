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
 * 見出し
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Toc implements IModel, Serializable, Comparable<Toc> {
	/** 見出し */
	protected String tocId;

	/**
	 * 見出しを取得
	 *
	 * @return 見出し
	 */
	public String getTocId() {
		return tocId;
	}

	/**
	 * 見出しを設定
	 *
	 * @param tocId 見出し
	 */
	public void setTocId(String tocId) {
		this.tocId = tocId;
	}

	/**
	 * 見出しを設定
	 *
	 * @param tocId 見出し
	 * @return this
	 */
	public Toc withTocId(String tocId) {
		this.tocId = tocId;
		return this;
	}
	/** ユーザーID */
	protected String userId;

	/**
	 * ユーザーIDを取得
	 *
	 * @return ユーザーID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * ユーザーIDを設定
	 *
	 * @param userId ユーザーID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * ユーザーIDを設定
	 *
	 * @param userId ユーザーID
	 * @return this
	 */
	public Toc withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	/** インデックス */
	protected Integer index;

	/**
	 * インデックスを取得
	 *
	 * @return インデックス
	 */
	public Integer getIndex() {
		return index;
	}

	/**
	 * インデックスを設定
	 *
	 * @param index インデックス
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}

	/**
	 * インデックスを設定
	 *
	 * @param index インデックス
	 * @return this
	 */
	public Toc withIndex(Integer index) {
		this.index = index;
		return this;
	}
	/** エントリーのリスト */
	protected List<Entry> entries;

	/**
	 * エントリーのリストを取得
	 *
	 * @return エントリーのリスト
	 */
	public List<Entry> getEntries() {
		return entries;
	}

	/**
	 * エントリーのリストを設定
	 *
	 * @param entries エントリーのリスト
	 */
	public void setEntries(List<Entry> entries) {
		this.entries = entries;
	}

	/**
	 * エントリーのリストを設定
	 *
	 * @param entries エントリーのリスト
	 * @return this
	 */
	public Toc withEntries(List<Entry> entries) {
		this.entries = entries;
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
	public Toc withCreatedAt(Long createdAt) {
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
	public Toc withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> entries = new ArrayList<>();
        if(this.entries != null) {
            for(Entry item : this.entries) {
                entries.add(item.toJson());
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("tocId", this.getTocId())
            .put("userId", this.getUserId())
            .put("index", this.getIndex())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        body_.set("entries", JsonNodeFactory.instance.arrayNode().addAll(entries));
        return body_;
    }
	@Override
	public int compareTo(Toc o) {
		return tocId.compareTo(o.tocId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.tocId == null) ? 0 : this.tocId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.index == null) ? 0 : this.index.hashCode());
        result = prime * result + ((this.entries == null) ? 0 : this.entries.hashCode());
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
		Toc other = (Toc) o;
		if (tocId == null) {
			return other.tocId == null;
		} else if (!tocId.equals(other.tocId)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (index == null) {
			return other.index == null;
		} else if (!index.equals(other.index)) {
			return false;
		}
		if (entries == null) {
			return other.entries == null;
		} else if (!entries.equals(other.entries)) {
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
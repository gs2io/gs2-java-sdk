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
 * エントリー
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Entry implements IModel, Serializable, Comparable<Entry> {
	/** エントリー のGRN */
	protected String entryId;

	/**
	 * エントリー のGRNを取得
	 *
	 * @return エントリー のGRN
	 */
	public String getEntryId() {
		return entryId;
	}

	/**
	 * エントリー のGRNを設定
	 *
	 * @param entryId エントリー のGRN
	 */
	public void setEntryId(String entryId) {
		this.entryId = entryId;
	}

	/**
	 * エントリー のGRNを設定
	 *
	 * @param entryId エントリー のGRN
	 * @return this
	 */
	public Entry withEntryId(String entryId) {
		this.entryId = entryId;
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
	public Entry withUserId(String userId) {
		this.userId = userId;
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
	public Entry withName(String name) {
		this.name = name;
		return this;
	}
	/** None */
	protected Long acquiredAt;

	/**
	 * Noneを取得
	 *
	 * @return None
	 */
	public Long getAcquiredAt() {
		return acquiredAt;
	}

	/**
	 * Noneを設定
	 *
	 * @param acquiredAt None
	 */
	public void setAcquiredAt(Long acquiredAt) {
		this.acquiredAt = acquiredAt;
	}

	/**
	 * Noneを設定
	 *
	 * @param acquiredAt None
	 * @return this
	 */
	public Entry withAcquiredAt(Long acquiredAt) {
		this.acquiredAt = acquiredAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("entryId", this.getEntryId())
            .put("userId", this.getUserId())
            .put("name", this.getName())
            .put("acquiredAt", this.getAcquiredAt());
        return body_;
    }
	@Override
	public int compareTo(Entry o) {
		return entryId.compareTo(o.entryId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.entryId == null) ? 0 : this.entryId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.acquiredAt == null) ? 0 : this.acquiredAt.hashCode());
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
		Entry other = (Entry) o;
		if (entryId == null) {
			return other.entryId == null;
		} else if (!entryId.equals(other.entryId)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (acquiredAt == null) {
			return other.acquiredAt == null;
		} else if (!acquiredAt.equals(other.acquiredAt)) {
			return false;
		}
		return true;
	}
}
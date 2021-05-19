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

package io.gs2.exchange.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * 交換待機
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Await implements IModel, Serializable, Comparable<Await> {
	/** 交換待機 */
	protected String awaitId;

	/**
	 * 交換待機を取得
	 *
	 * @return 交換待機
	 */
	public String getAwaitId() {
		return awaitId;
	}

	/**
	 * 交換待機を設定
	 *
	 * @param awaitId 交換待機
	 */
	public void setAwaitId(String awaitId) {
		this.awaitId = awaitId;
	}

	/**
	 * 交換待機を設定
	 *
	 * @param awaitId 交換待機
	 * @return this
	 */
	public Await withAwaitId(String awaitId) {
		this.awaitId = awaitId;
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
	public Await withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	/** 交換レート名 */
	protected String rateName;

	/**
	 * 交換レート名を取得
	 *
	 * @return 交換レート名
	 */
	public String getRateName() {
		return rateName;
	}

	/**
	 * 交換レート名を設定
	 *
	 * @param rateName 交換レート名
	 */
	public void setRateName(String rateName) {
		this.rateName = rateName;
	}

	/**
	 * 交換レート名を設定
	 *
	 * @param rateName 交換レート名
	 * @return this
	 */
	public Await withRateName(String rateName) {
		this.rateName = rateName;
		return this;
	}
	/** 交換待機の名前 */
	protected String name;

	/**
	 * 交換待機の名前を取得
	 *
	 * @return 交換待機の名前
	 */
	public String getName() {
		return name;
	}

	/**
	 * 交換待機の名前を設定
	 *
	 * @param name 交換待機の名前
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 交換待機の名前を設定
	 *
	 * @param name 交換待機の名前
	 * @return this
	 */
	public Await withName(String name) {
		this.name = name;
		return this;
	}
	/** 交換数 */
	protected Integer count;

	/**
	 * 交換数を取得
	 *
	 * @return 交換数
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * 交換数を設定
	 *
	 * @param count 交換数
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * 交換数を設定
	 *
	 * @param count 交換数
	 * @return this
	 */
	public Await withCount(Integer count) {
		this.count = count;
		return this;
	}
	/** 作成日時 */
	protected Long exchangedAt;

	/**
	 * 作成日時を取得
	 *
	 * @return 作成日時
	 */
	public Long getExchangedAt() {
		return exchangedAt;
	}

	/**
	 * 作成日時を設定
	 *
	 * @param exchangedAt 作成日時
	 */
	public void setExchangedAt(Long exchangedAt) {
		this.exchangedAt = exchangedAt;
	}

	/**
	 * 作成日時を設定
	 *
	 * @param exchangedAt 作成日時
	 * @return this
	 */
	public Await withExchangedAt(Long exchangedAt) {
		this.exchangedAt = exchangedAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("awaitId", this.getAwaitId())
            .put("userId", this.getUserId())
            .put("rateName", this.getRateName())
            .put("name", this.getName())
            .put("count", this.getCount())
            .put("exchangedAt", this.getExchangedAt());
        return body_;
    }
	@Override
	public int compareTo(Await o) {
		return awaitId.compareTo(o.awaitId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.awaitId == null) ? 0 : this.awaitId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.rateName == null) ? 0 : this.rateName.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.count == null) ? 0 : this.count.hashCode());
        result = prime * result + ((this.exchangedAt == null) ? 0 : this.exchangedAt.hashCode());
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
		Await other = (Await) o;
		if (awaitId == null) {
			return other.awaitId == null;
		} else if (!awaitId.equals(other.awaitId)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (rateName == null) {
			return other.rateName == null;
		} else if (!rateName.equals(other.rateName)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (count == null) {
			return other.count == null;
		} else if (!count.equals(other.count)) {
			return false;
		}
		if (exchangedAt == null) {
			return other.exchangedAt == null;
		} else if (!exchangedAt.equals(other.exchangedAt)) {
			return false;
		}
		return true;
	}
}
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

package io.gs2.mission.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.model.IModel;

/**
 * カウンター
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Counter implements IModel, Serializable, Comparable<Counter> {
	/** カウンター */
	protected String counterId;

	/**
	 * カウンターを取得
	 *
	 * @return カウンター
	 */
	public String getCounterId() {
		return counterId;
	}

	/**
	 * カウンターを設定
	 *
	 * @param counterId カウンター
	 */
	public void setCounterId(String counterId) {
		this.counterId = counterId;
	}

	/**
	 * カウンターを設定
	 *
	 * @param counterId カウンター
	 * @return this
	 */
	public Counter withCounterId(String counterId) {
		this.counterId = counterId;
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
	public Counter withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	/** カウンター名 */
	protected String name;

	/**
	 * カウンター名を取得
	 *
	 * @return カウンター名
	 */
	public String getName() {
		return name;
	}

	/**
	 * カウンター名を設定
	 *
	 * @param name カウンター名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * カウンター名を設定
	 *
	 * @param name カウンター名
	 * @return this
	 */
	public Counter withName(String name) {
		this.name = name;
		return this;
	}
	/** 値 */
	protected List<ScopedValue> values;

	/**
	 * 値を取得
	 *
	 * @return 値
	 */
	public List<ScopedValue> getValues() {
		return values;
	}

	/**
	 * 値を設定
	 *
	 * @param values 値
	 */
	public void setValues(List<ScopedValue> values) {
		this.values = values;
	}

	/**
	 * 値を設定
	 *
	 * @param values 値
	 * @return this
	 */
	public Counter withValues(List<ScopedValue> values) {
		this.values = values;
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
	public Counter withCreatedAt(Long createdAt) {
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
	public Counter withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> values = new ArrayList<>();
        if(this.values != null) {
            for(ScopedValue item : this.values) {
                values.add(item.toJson());
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("counterId", this.getCounterId())
            .put("userId", this.getUserId())
            .put("name", this.getName())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        body_.set("values", JsonNodeFactory.instance.arrayNode().addAll(values));
        return body_;
    }
	@Override
	public int compareTo(Counter o) {
		return counterId.compareTo(o.counterId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.counterId == null) ? 0 : this.counterId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.values == null) ? 0 : this.values.hashCode());
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
		Counter other = (Counter) o;
		if (counterId == null) {
			return other.counterId == null;
		} else if (!counterId.equals(other.counterId)) {
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
		if (values == null) {
			return other.values == null;
		} else if (!values.equals(other.values)) {
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
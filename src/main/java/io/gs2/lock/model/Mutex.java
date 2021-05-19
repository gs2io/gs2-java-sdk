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

package io.gs2.lock.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * ミューテックス
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Mutex implements IModel, Serializable, Comparable<Mutex> {
	/** ミューテックス */
	protected String mutexId;

	/**
	 * ミューテックスを取得
	 *
	 * @return ミューテックス
	 */
	public String getMutexId() {
		return mutexId;
	}

	/**
	 * ミューテックスを設定
	 *
	 * @param mutexId ミューテックス
	 */
	public void setMutexId(String mutexId) {
		this.mutexId = mutexId;
	}

	/**
	 * ミューテックスを設定
	 *
	 * @param mutexId ミューテックス
	 * @return this
	 */
	public Mutex withMutexId(String mutexId) {
		this.mutexId = mutexId;
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
	public Mutex withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	/** プロパティID */
	protected String propertyId;

	/**
	 * プロパティIDを取得
	 *
	 * @return プロパティID
	 */
	public String getPropertyId() {
		return propertyId;
	}

	/**
	 * プロパティIDを設定
	 *
	 * @param propertyId プロパティID
	 */
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	/**
	 * プロパティIDを設定
	 *
	 * @param propertyId プロパティID
	 * @return this
	 */
	public Mutex withPropertyId(String propertyId) {
		this.propertyId = propertyId;
		return this;
	}
	/** ロックを取得したトランザクションID */
	protected String transactionId;

	/**
	 * ロックを取得したトランザクションIDを取得
	 *
	 * @return ロックを取得したトランザクションID
	 */
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * ロックを取得したトランザクションIDを設定
	 *
	 * @param transactionId ロックを取得したトランザクションID
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * ロックを取得したトランザクションIDを設定
	 *
	 * @param transactionId ロックを取得したトランザクションID
	 * @return this
	 */
	public Mutex withTransactionId(String transactionId) {
		this.transactionId = transactionId;
		return this;
	}
	/** 参照回数 */
	protected Integer referenceCount;

	/**
	 * 参照回数を取得
	 *
	 * @return 参照回数
	 */
	public Integer getReferenceCount() {
		return referenceCount;
	}

	/**
	 * 参照回数を設定
	 *
	 * @param referenceCount 参照回数
	 */
	public void setReferenceCount(Integer referenceCount) {
		this.referenceCount = referenceCount;
	}

	/**
	 * 参照回数を設定
	 *
	 * @param referenceCount 参照回数
	 * @return this
	 */
	public Mutex withReferenceCount(Integer referenceCount) {
		this.referenceCount = referenceCount;
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
	public Mutex withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	/** ロックの有効期限 */
	protected Long ttlAt;

	/**
	 * ロックの有効期限を取得
	 *
	 * @return ロックの有効期限
	 */
	public Long getTtlAt() {
		return ttlAt;
	}

	/**
	 * ロックの有効期限を設定
	 *
	 * @param ttlAt ロックの有効期限
	 */
	public void setTtlAt(Long ttlAt) {
		this.ttlAt = ttlAt;
	}

	/**
	 * ロックの有効期限を設定
	 *
	 * @param ttlAt ロックの有効期限
	 * @return this
	 */
	public Mutex withTtlAt(Long ttlAt) {
		this.ttlAt = ttlAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("mutexId", this.getMutexId())
            .put("userId", this.getUserId())
            .put("propertyId", this.getPropertyId())
            .put("transactionId", this.getTransactionId())
            .put("referenceCount", this.getReferenceCount())
            .put("createdAt", this.getCreatedAt())
            .put("ttlAt", this.getTtlAt());
        return body_;
    }
	@Override
	public int compareTo(Mutex o) {
		return mutexId.compareTo(o.mutexId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.mutexId == null) ? 0 : this.mutexId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.propertyId == null) ? 0 : this.propertyId.hashCode());
        result = prime * result + ((this.transactionId == null) ? 0 : this.transactionId.hashCode());
        result = prime * result + ((this.referenceCount == null) ? 0 : this.referenceCount.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.ttlAt == null) ? 0 : this.ttlAt.hashCode());
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
		Mutex other = (Mutex) o;
		if (mutexId == null) {
			return other.mutexId == null;
		} else if (!mutexId.equals(other.mutexId)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (propertyId == null) {
			return other.propertyId == null;
		} else if (!propertyId.equals(other.propertyId)) {
			return false;
		}
		if (transactionId == null) {
			return other.transactionId == null;
		} else if (!transactionId.equals(other.transactionId)) {
			return false;
		}
		if (referenceCount == null) {
			return other.referenceCount == null;
		} else if (!referenceCount.equals(other.referenceCount)) {
			return false;
		}
		if (createdAt == null) {
			return other.createdAt == null;
		} else if (!createdAt.equals(other.createdAt)) {
			return false;
		}
		if (ttlAt == null) {
			return other.ttlAt == null;
		} else if (!ttlAt.equals(other.ttlAt)) {
			return false;
		}
		return true;
	}
}
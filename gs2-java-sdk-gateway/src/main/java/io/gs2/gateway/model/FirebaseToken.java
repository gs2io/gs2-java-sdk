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

package io.gs2.gateway.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.model.IModel;

/**
 * Firebaseデバイストークン
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class FirebaseToken implements IModel, Serializable, Comparable<FirebaseToken> {
	/** Firebaseデバイストークン のGRN */
	protected String firebaseTokenId;

	/**
	 * Firebaseデバイストークン のGRNを取得
	 *
	 * @return Firebaseデバイストークン のGRN
	 */
	public String getFirebaseTokenId() {
		return firebaseTokenId;
	}

	/**
	 * Firebaseデバイストークン のGRNを設定
	 *
	 * @param firebaseTokenId Firebaseデバイストークン のGRN
	 */
	public void setFirebaseTokenId(String firebaseTokenId) {
		this.firebaseTokenId = firebaseTokenId;
	}

	/**
	 * Firebaseデバイストークン のGRNを設定
	 *
	 * @param firebaseTokenId Firebaseデバイストークン のGRN
	 * @return this
	 */
	public FirebaseToken withFirebaseTokenId(String firebaseTokenId) {
		this.firebaseTokenId = firebaseTokenId;
		return this;
	}
	/** オーナーID */
	protected String ownerId;

	/**
	 * オーナーIDを取得
	 *
	 * @return オーナーID
	 */
	public String getOwnerId() {
		return ownerId;
	}

	/**
	 * オーナーIDを設定
	 *
	 * @param ownerId オーナーID
	 */
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * オーナーIDを設定
	 *
	 * @param ownerId オーナーID
	 * @return this
	 */
	public FirebaseToken withOwnerId(String ownerId) {
		this.ownerId = ownerId;
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
	public FirebaseToken withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	/** Firebase Cloud Messaging のデバイストークン */
	protected String token;

	/**
	 * Firebase Cloud Messaging のデバイストークンを取得
	 *
	 * @return Firebase Cloud Messaging のデバイストークン
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Firebase Cloud Messaging のデバイストークンを設定
	 *
	 * @param token Firebase Cloud Messaging のデバイストークン
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * Firebase Cloud Messaging のデバイストークンを設定
	 *
	 * @param token Firebase Cloud Messaging のデバイストークン
	 * @return this
	 */
	public FirebaseToken withToken(String token) {
		this.token = token;
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
	public FirebaseToken withCreatedAt(Long createdAt) {
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
	public FirebaseToken withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("firebaseTokenId", this.getFirebaseTokenId())
            .put("ownerId", this.getOwnerId())
            .put("userId", this.getUserId())
            .put("token", this.getToken())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        return body_;
    }
	@Override
	public int compareTo(FirebaseToken o) {
		return firebaseTokenId.compareTo(o.firebaseTokenId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.firebaseTokenId == null) ? 0 : this.firebaseTokenId.hashCode());
        result = prime * result + ((this.ownerId == null) ? 0 : this.ownerId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.token == null) ? 0 : this.token.hashCode());
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
		FirebaseToken other = (FirebaseToken) o;
		if (firebaseTokenId == null) {
			return other.firebaseTokenId == null;
		} else if (!firebaseTokenId.equals(other.firebaseTokenId)) {
			return false;
		}
		if (ownerId == null) {
			return other.ownerId == null;
		} else if (!ownerId.equals(other.ownerId)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (token == null) {
			return other.token == null;
		} else if (!token.equals(other.token)) {
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
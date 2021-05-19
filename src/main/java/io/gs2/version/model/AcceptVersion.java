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

package io.gs2.version.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * 承認したバージョン
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class AcceptVersion implements IModel, Serializable, Comparable<AcceptVersion> {
	/** 承認したバージョン */
	protected String acceptVersionId;

	/**
	 * 承認したバージョンを取得
	 *
	 * @return 承認したバージョン
	 */
	public String getAcceptVersionId() {
		return acceptVersionId;
	}

	/**
	 * 承認したバージョンを設定
	 *
	 * @param acceptVersionId 承認したバージョン
	 */
	public void setAcceptVersionId(String acceptVersionId) {
		this.acceptVersionId = acceptVersionId;
	}

	/**
	 * 承認したバージョンを設定
	 *
	 * @param acceptVersionId 承認したバージョン
	 * @return this
	 */
	public AcceptVersion withAcceptVersionId(String acceptVersionId) {
		this.acceptVersionId = acceptVersionId;
		return this;
	}
	/** 承認したバージョン名 */
	protected String versionName;

	/**
	 * 承認したバージョン名を取得
	 *
	 * @return 承認したバージョン名
	 */
	public String getVersionName() {
		return versionName;
	}

	/**
	 * 承認したバージョン名を設定
	 *
	 * @param versionName 承認したバージョン名
	 */
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	/**
	 * 承認したバージョン名を設定
	 *
	 * @param versionName 承認したバージョン名
	 * @return this
	 */
	public AcceptVersion withVersionName(String versionName) {
		this.versionName = versionName;
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
	public AcceptVersion withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	/** 承認したバージョン */
	protected Version version;

	/**
	 * 承認したバージョンを取得
	 *
	 * @return 承認したバージョン
	 */
	public Version getVersion() {
		return version;
	}

	/**
	 * 承認したバージョンを設定
	 *
	 * @param version 承認したバージョン
	 */
	public void setVersion(Version version) {
		this.version = version;
	}

	/**
	 * 承認したバージョンを設定
	 *
	 * @param version 承認したバージョン
	 * @return this
	 */
	public AcceptVersion withVersion(Version version) {
		this.version = version;
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
	public AcceptVersion withCreatedAt(Long createdAt) {
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
	public AcceptVersion withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
        JsonNode version = this.getVersion().toJson();
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("acceptVersionId", this.getAcceptVersionId())
            .put("versionName", this.getVersionName())
            .put("userId", this.getUserId())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        body_.set("version", version);
        return body_;
    }
	@Override
	public int compareTo(AcceptVersion o) {
		return acceptVersionId.compareTo(o.acceptVersionId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.acceptVersionId == null) ? 0 : this.acceptVersionId.hashCode());
        result = prime * result + ((this.versionName == null) ? 0 : this.versionName.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.version == null) ? 0 : this.version.hashCode());
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
		AcceptVersion other = (AcceptVersion) o;
		if (acceptVersionId == null) {
			return other.acceptVersionId == null;
		} else if (!acceptVersionId.equals(other.acceptVersionId)) {
			return false;
		}
		if (versionName == null) {
			return other.versionName == null;
		} else if (!versionName.equals(other.versionName)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (version == null) {
			return other.version == null;
		} else if (!version.equals(other.version)) {
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
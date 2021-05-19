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
 * 署名検証に使用するバージョン
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class SignTargetVersion implements IModel, Serializable {
	/** None */
	protected String region;

	/**
	 * Noneを取得
	 *
	 * @return None
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * Noneを設定
	 *
	 * @param region None
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * Noneを設定
	 *
	 * @param region None
	 * @return this
	 */
	public SignTargetVersion withRegion(String region) {
		this.region = region;
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
	public SignTargetVersion withOwnerId(String ownerId) {
		this.ownerId = ownerId;
		return this;
	}
	/** ネームスペース名 */
	protected String namespaceName;

	/**
	 * ネームスペース名を取得
	 *
	 * @return ネームスペース名
	 */
	public String getNamespaceName() {
		return namespaceName;
	}

	/**
	 * ネームスペース名を設定
	 *
	 * @param namespaceName ネームスペース名
	 */
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}

	/**
	 * ネームスペース名を設定
	 *
	 * @param namespaceName ネームスペース名
	 * @return this
	 */
	public SignTargetVersion withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	/** バージョンの種類名 */
	protected String versionName;

	/**
	 * バージョンの種類名を取得
	 *
	 * @return バージョンの種類名
	 */
	public String getVersionName() {
		return versionName;
	}

	/**
	 * バージョンの種類名を設定
	 *
	 * @param versionName バージョンの種類名
	 */
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	/**
	 * バージョンの種類名を設定
	 *
	 * @param versionName バージョンの種類名
	 * @return this
	 */
	public SignTargetVersion withVersionName(String versionName) {
		this.versionName = versionName;
		return this;
	}
	/** バージョン */
	protected Version version;

	/**
	 * バージョンを取得
	 *
	 * @return バージョン
	 */
	public Version getVersion() {
		return version;
	}

	/**
	 * バージョンを設定
	 *
	 * @param version バージョン
	 */
	public void setVersion(Version version) {
		this.version = version;
	}

	/**
	 * バージョンを設定
	 *
	 * @param version バージョン
	 * @return this
	 */
	public SignTargetVersion withVersion(Version version) {
		this.version = version;
		return this;
	}

    public ObjectNode toJson() {
        JsonNode version = this.getVersion().toJson();
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("region", this.getRegion())
            .put("ownerId", this.getOwnerId())
            .put("namespaceName", this.getNamespaceName())
            .put("versionName", this.getVersionName());
        body_.set("version", version);
        return body_;
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.region == null) ? 0 : this.region.hashCode());
        result = prime * result + ((this.ownerId == null) ? 0 : this.ownerId.hashCode());
        result = prime * result + ((this.namespaceName == null) ? 0 : this.namespaceName.hashCode());
        result = prime * result + ((this.versionName == null) ? 0 : this.versionName.hashCode());
        result = prime * result + ((this.version == null) ? 0 : this.version.hashCode());
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
		SignTargetVersion other = (SignTargetVersion) o;
		if (region == null) {
			return other.region == null;
		} else if (!region.equals(other.region)) {
			return false;
		}
		if (ownerId == null) {
			return other.ownerId == null;
		} else if (!ownerId.equals(other.ownerId)) {
			return false;
		}
		if (namespaceName == null) {
			return other.namespaceName == null;
		} else if (!namespaceName.equals(other.namespaceName)) {
			return false;
		}
		if (versionName == null) {
			return other.versionName == null;
		} else if (!versionName.equals(other.versionName)) {
			return false;
		}
		if (version == null) {
			return other.version == null;
		} else if (!version.equals(other.version)) {
			return false;
		}
		return true;
	}
}
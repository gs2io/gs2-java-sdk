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
 * バージョン設定
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class VersionModel implements IModel, Serializable, Comparable<VersionModel> {
	/** バージョン設定 */
	protected String versionModelId;

	/**
	 * バージョン設定を取得
	 *
	 * @return バージョン設定
	 */
	public String getVersionModelId() {
		return versionModelId;
	}

	/**
	 * バージョン設定を設定
	 *
	 * @param versionModelId バージョン設定
	 */
	public void setVersionModelId(String versionModelId) {
		this.versionModelId = versionModelId;
	}

	/**
	 * バージョン設定を設定
	 *
	 * @param versionModelId バージョン設定
	 * @return this
	 */
	public VersionModel withVersionModelId(String versionModelId) {
		this.versionModelId = versionModelId;
		return this;
	}
	/** バージョンの種類名 */
	protected String name;

	/**
	 * バージョンの種類名を取得
	 *
	 * @return バージョンの種類名
	 */
	public String getName() {
		return name;
	}

	/**
	 * バージョンの種類名を設定
	 *
	 * @param name バージョンの種類名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * バージョンの種類名を設定
	 *
	 * @param name バージョンの種類名
	 * @return this
	 */
	public VersionModel withName(String name) {
		this.name = name;
		return this;
	}
	/** バージョンの種類のメタデータ */
	protected String metadata;

	/**
	 * バージョンの種類のメタデータを取得
	 *
	 * @return バージョンの種類のメタデータ
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * バージョンの種類のメタデータを設定
	 *
	 * @param metadata バージョンの種類のメタデータ
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	/**
	 * バージョンの種類のメタデータを設定
	 *
	 * @param metadata バージョンの種類のメタデータ
	 * @return this
	 */
	public VersionModel withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	/** バージョンアップを促すバージョン */
	protected Version warningVersion;

	/**
	 * バージョンアップを促すバージョンを取得
	 *
	 * @return バージョンアップを促すバージョン
	 */
	public Version getWarningVersion() {
		return warningVersion;
	}

	/**
	 * バージョンアップを促すバージョンを設定
	 *
	 * @param warningVersion バージョンアップを促すバージョン
	 */
	public void setWarningVersion(Version warningVersion) {
		this.warningVersion = warningVersion;
	}

	/**
	 * バージョンアップを促すバージョンを設定
	 *
	 * @param warningVersion バージョンアップを促すバージョン
	 * @return this
	 */
	public VersionModel withWarningVersion(Version warningVersion) {
		this.warningVersion = warningVersion;
		return this;
	}
	/** バージョンチェックを蹴るバージョン */
	protected Version errorVersion;

	/**
	 * バージョンチェックを蹴るバージョンを取得
	 *
	 * @return バージョンチェックを蹴るバージョン
	 */
	public Version getErrorVersion() {
		return errorVersion;
	}

	/**
	 * バージョンチェックを蹴るバージョンを設定
	 *
	 * @param errorVersion バージョンチェックを蹴るバージョン
	 */
	public void setErrorVersion(Version errorVersion) {
		this.errorVersion = errorVersion;
	}

	/**
	 * バージョンチェックを蹴るバージョンを設定
	 *
	 * @param errorVersion バージョンチェックを蹴るバージョン
	 * @return this
	 */
	public VersionModel withErrorVersion(Version errorVersion) {
		this.errorVersion = errorVersion;
		return this;
	}
	/** 判定に使用するバージョン値の種類 */
	protected String scope;

	/**
	 * 判定に使用するバージョン値の種類を取得
	 *
	 * @return 判定に使用するバージョン値の種類
	 */
	public String getScope() {
		return scope;
	}

	/**
	 * 判定に使用するバージョン値の種類を設定
	 *
	 * @param scope 判定に使用するバージョン値の種類
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}

	/**
	 * 判定に使用するバージョン値の種類を設定
	 *
	 * @param scope 判定に使用するバージョン値の種類
	 * @return this
	 */
	public VersionModel withScope(String scope) {
		this.scope = scope;
		return this;
	}
	/** 現在のバージョン */
	protected Version currentVersion;

	/**
	 * 現在のバージョンを取得
	 *
	 * @return 現在のバージョン
	 */
	public Version getCurrentVersion() {
		return currentVersion;
	}

	/**
	 * 現在のバージョンを設定
	 *
	 * @param currentVersion 現在のバージョン
	 */
	public void setCurrentVersion(Version currentVersion) {
		this.currentVersion = currentVersion;
	}

	/**
	 * 現在のバージョンを設定
	 *
	 * @param currentVersion 現在のバージョン
	 * @return this
	 */
	public VersionModel withCurrentVersion(Version currentVersion) {
		this.currentVersion = currentVersion;
		return this;
	}
	/** 判定するバージョン値に署名検証を必要とするか */
	protected Boolean needSignature;

	/**
	 * 判定するバージョン値に署名検証を必要とするかを取得
	 *
	 * @return 判定するバージョン値に署名検証を必要とするか
	 */
	public Boolean getNeedSignature() {
		return needSignature;
	}

	/**
	 * 判定するバージョン値に署名検証を必要とするかを設定
	 *
	 * @param needSignature 判定するバージョン値に署名検証を必要とするか
	 */
	public void setNeedSignature(Boolean needSignature) {
		this.needSignature = needSignature;
	}

	/**
	 * 判定するバージョン値に署名検証を必要とするかを設定
	 *
	 * @param needSignature 判定するバージョン値に署名検証を必要とするか
	 * @return this
	 */
	public VersionModel withNeedSignature(Boolean needSignature) {
		this.needSignature = needSignature;
		return this;
	}
	/** 署名検証に使用する暗号鍵 のGRN */
	protected String signatureKeyId;

	/**
	 * 署名検証に使用する暗号鍵 のGRNを取得
	 *
	 * @return 署名検証に使用する暗号鍵 のGRN
	 */
	public String getSignatureKeyId() {
		return signatureKeyId;
	}

	/**
	 * 署名検証に使用する暗号鍵 のGRNを設定
	 *
	 * @param signatureKeyId 署名検証に使用する暗号鍵 のGRN
	 */
	public void setSignatureKeyId(String signatureKeyId) {
		this.signatureKeyId = signatureKeyId;
	}

	/**
	 * 署名検証に使用する暗号鍵 のGRNを設定
	 *
	 * @param signatureKeyId 署名検証に使用する暗号鍵 のGRN
	 * @return this
	 */
	public VersionModel withSignatureKeyId(String signatureKeyId) {
		this.signatureKeyId = signatureKeyId;
		return this;
	}

    public ObjectNode toJson() {
        JsonNode warningVersion = this.getWarningVersion().toJson();
        JsonNode errorVersion = this.getErrorVersion().toJson();
        JsonNode currentVersion = this.getCurrentVersion().toJson();
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("versionModelId", this.getVersionModelId())
            .put("name", this.getName())
            .put("metadata", this.getMetadata())
            .put("scope", this.getScope())
            .put("needSignature", this.getNeedSignature())
            .put("signatureKeyId", this.getSignatureKeyId());
        body_.set("warningVersion", warningVersion);
        body_.set("errorVersion", errorVersion);
        body_.set("currentVersion", currentVersion);
        return body_;
    }
	@Override
	public int compareTo(VersionModel o) {
		return versionModelId.compareTo(o.versionModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.versionModelId == null) ? 0 : this.versionModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.warningVersion == null) ? 0 : this.warningVersion.hashCode());
        result = prime * result + ((this.errorVersion == null) ? 0 : this.errorVersion.hashCode());
        result = prime * result + ((this.scope == null) ? 0 : this.scope.hashCode());
        result = prime * result + ((this.currentVersion == null) ? 0 : this.currentVersion.hashCode());
        result = prime * result + ((this.needSignature == null) ? 0 : this.needSignature.hashCode());
        result = prime * result + ((this.signatureKeyId == null) ? 0 : this.signatureKeyId.hashCode());
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
		VersionModel other = (VersionModel) o;
		if (versionModelId == null) {
			return other.versionModelId == null;
		} else if (!versionModelId.equals(other.versionModelId)) {
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
		if (warningVersion == null) {
			return other.warningVersion == null;
		} else if (!warningVersion.equals(other.warningVersion)) {
			return false;
		}
		if (errorVersion == null) {
			return other.errorVersion == null;
		} else if (!errorVersion.equals(other.errorVersion)) {
			return false;
		}
		if (scope == null) {
			return other.scope == null;
		} else if (!scope.equals(other.scope)) {
			return false;
		}
		if (currentVersion == null) {
			return other.currentVersion == null;
		} else if (!currentVersion.equals(other.currentVersion)) {
			return false;
		}
		if (needSignature == null) {
			return other.needSignature == null;
		} else if (!needSignature.equals(other.needSignature)) {
			return false;
		}
		if (signatureKeyId == null) {
			return other.signatureKeyId == null;
		} else if (!signatureKeyId.equals(other.signatureKeyId)) {
			return false;
		}
		return true;
	}
}
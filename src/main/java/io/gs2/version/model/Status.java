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
 * バージョンの検証結果
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Status implements IModel, Serializable {
	/** バージョン設定 */
	protected VersionModel versionModel;

	/**
	 * バージョン設定を取得
	 *
	 * @return バージョン設定
	 */
	public VersionModel getVersionModel() {
		return versionModel;
	}

	/**
	 * バージョン設定を設定
	 *
	 * @param versionModel バージョン設定
	 */
	public void setVersionModel(VersionModel versionModel) {
		this.versionModel = versionModel;
	}

	/**
	 * バージョン設定を設定
	 *
	 * @param versionModel バージョン設定
	 * @return this
	 */
	public Status withVersionModel(VersionModel versionModel) {
		this.versionModel = versionModel;
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
	public Status withCurrentVersion(Version currentVersion) {
		this.currentVersion = currentVersion;
		return this;
	}

    public ObjectNode toJson() {
        JsonNode versionModel = this.getVersionModel().toJson();
        JsonNode currentVersion = this.getCurrentVersion().toJson();
		ObjectNode body_ = JsonNodeFactory.instance.objectNode();
        body_.set("versionModel", versionModel);
        body_.set("currentVersion", currentVersion);
        return body_;
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.versionModel == null) ? 0 : this.versionModel.hashCode());
        result = prime * result + ((this.currentVersion == null) ? 0 : this.currentVersion.hashCode());
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
		Status other = (Status) o;
		if (versionModel == null) {
			return other.versionModel == null;
		} else if (!versionModel.equals(other.versionModel)) {
			return false;
		}
		if (currentVersion == null) {
			return other.currentVersion == null;
		} else if (!currentVersion.equals(other.currentVersion)) {
			return false;
		}
		return true;
	}
}
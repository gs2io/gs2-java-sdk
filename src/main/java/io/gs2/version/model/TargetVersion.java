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
 * 検証するバージョン
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class TargetVersion implements IModel, Serializable {
	/** バージョンの名前 */
	protected String versionName;

	/**
	 * バージョンの名前を取得
	 *
	 * @return バージョンの名前
	 */
	public String getVersionName() {
		return versionName;
	}

	/**
	 * バージョンの名前を設定
	 *
	 * @param versionName バージョンの名前
	 */
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	/**
	 * バージョンの名前を設定
	 *
	 * @param versionName バージョンの名前
	 * @return this
	 */
	public TargetVersion withVersionName(String versionName) {
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
	public TargetVersion withVersion(Version version) {
		this.version = version;
		return this;
	}
	/** ボディ */
	protected String body;

	/**
	 * ボディを取得
	 *
	 * @return ボディ
	 */
	public String getBody() {
		return body;
	}

	/**
	 * ボディを設定
	 *
	 * @param body ボディ
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * ボディを設定
	 *
	 * @param body ボディ
	 * @return this
	 */
	public TargetVersion withBody(String body) {
		this.body = body;
		return this;
	}
	/** 署名 */
	protected String signature;

	/**
	 * 署名を取得
	 *
	 * @return 署名
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * 署名を設定
	 *
	 * @param signature 署名
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}

	/**
	 * 署名を設定
	 *
	 * @param signature 署名
	 * @return this
	 */
	public TargetVersion withSignature(String signature) {
		this.signature = signature;
		return this;
	}

    public ObjectNode toJson() {
        JsonNode version = this.getVersion().toJson();
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("versionName", this.getVersionName())
            .put("body", this.getBody())
            .put("signature", this.getSignature());
        body_.set("version", version);
        return body_;
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.versionName == null) ? 0 : this.versionName.hashCode());
        result = prime * result + ((this.version == null) ? 0 : this.version.hashCode());
        result = prime * result + ((this.body == null) ? 0 : this.body.hashCode());
        result = prime * result + ((this.signature == null) ? 0 : this.signature.hashCode());
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
		TargetVersion other = (TargetVersion) o;
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
		if (body == null) {
			return other.body == null;
		} else if (!body.equals(other.body)) {
			return false;
		}
		if (signature == null) {
			return other.signature == null;
		} else if (!signature.equals(other.signature)) {
			return false;
		}
		return true;
	}
}
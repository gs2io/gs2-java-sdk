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

package io.gs2.formation.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * 署名付きスロット
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class SlotWithSignature implements IModel, Serializable {
	/** スロットモデル名 */
	protected String name;

	/**
	 * スロットモデル名を取得
	 *
	 * @return スロットモデル名
	 */
	public String getName() {
		return name;
	}

	/**
	 * スロットモデル名を設定
	 *
	 * @param name スロットモデル名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * スロットモデル名を設定
	 *
	 * @param name スロットモデル名
	 * @return this
	 */
	public SlotWithSignature withName(String name) {
		this.name = name;
		return this;
	}
	/** プロパティの種類 */
	protected String propertyType;

	/**
	 * プロパティの種類を取得
	 *
	 * @return プロパティの種類
	 */
	public String getPropertyType() {
		return propertyType;
	}

	/**
	 * プロパティの種類を設定
	 *
	 * @param propertyType プロパティの種類
	 */
	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	/**
	 * プロパティの種類を設定
	 *
	 * @param propertyType プロパティの種類
	 * @return this
	 */
	public SlotWithSignature withPropertyType(String propertyType) {
		this.propertyType = propertyType;
		return this;
	}
	/** ペイロード */
	protected String body;

	/**
	 * ペイロードを取得
	 *
	 * @return ペイロード
	 */
	public String getBody() {
		return body;
	}

	/**
	 * ペイロードを設定
	 *
	 * @param body ペイロード
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * ペイロードを設定
	 *
	 * @param body ペイロード
	 * @return this
	 */
	public SlotWithSignature withBody(String body) {
		this.body = body;
		return this;
	}
	/** プロパティIDのリソースを所有していることを証明する署名 */
	protected String signature;

	/**
	 * プロパティIDのリソースを所有していることを証明する署名を取得
	 *
	 * @return プロパティIDのリソースを所有していることを証明する署名
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * プロパティIDのリソースを所有していることを証明する署名を設定
	 *
	 * @param signature プロパティIDのリソースを所有していることを証明する署名
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}

	/**
	 * プロパティIDのリソースを所有していることを証明する署名を設定
	 *
	 * @param signature プロパティIDのリソースを所有していることを証明する署名
	 * @return this
	 */
	public SlotWithSignature withSignature(String signature) {
		this.signature = signature;
		return this;
	}
	/** メタデータ */
	protected String metadata;

	/**
	 * メタデータを取得
	 *
	 * @return メタデータ
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * メタデータを設定
	 *
	 * @param metadata メタデータ
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	/**
	 * メタデータを設定
	 *
	 * @param metadata メタデータ
	 * @return this
	 */
	public SlotWithSignature withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("name", this.getName())
            .put("propertyType", this.getPropertyType())
            .put("body", this.getBody())
            .put("signature", this.getSignature())
            .put("metadata", this.getMetadata());
        return body_;
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.propertyType == null) ? 0 : this.propertyType.hashCode());
        result = prime * result + ((this.body == null) ? 0 : this.body.hashCode());
        result = prime * result + ((this.signature == null) ? 0 : this.signature.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
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
		SlotWithSignature other = (SlotWithSignature) o;
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (propertyType == null) {
			return other.propertyType == null;
		} else if (!propertyType.equals(other.propertyType)) {
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
		if (metadata == null) {
			return other.metadata == null;
		} else if (!metadata.equals(other.metadata)) {
			return false;
		}
		return true;
	}
}
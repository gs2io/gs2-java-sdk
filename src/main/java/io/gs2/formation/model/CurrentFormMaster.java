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
 * 現在有効なフォーム設定
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CurrentFormMaster implements IModel, Serializable {
	/** ネームスペース */
	protected String namespaceId;

	/**
	 * ネームスペースを取得
	 *
	 * @return ネームスペース
	 */
	public String getNamespaceId() {
		return namespaceId;
	}

	/**
	 * ネームスペースを設定
	 *
	 * @param namespaceId ネームスペース
	 */
	public void setNamespaceId(String namespaceId) {
		this.namespaceId = namespaceId;
	}

	/**
	 * ネームスペースを設定
	 *
	 * @param namespaceId ネームスペース
	 * @return this
	 */
	public CurrentFormMaster withNamespaceId(String namespaceId) {
		this.namespaceId = namespaceId;
		return this;
	}
	/** マスターデータ */
	protected String settings;

	/**
	 * マスターデータを取得
	 *
	 * @return マスターデータ
	 */
	public String getSettings() {
		return settings;
	}

	/**
	 * マスターデータを設定
	 *
	 * @param settings マスターデータ
	 */
	public void setSettings(String settings) {
		this.settings = settings;
	}

	/**
	 * マスターデータを設定
	 *
	 * @param settings マスターデータ
	 * @return this
	 */
	public CurrentFormMaster withSettings(String settings) {
		this.settings = settings;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("namespaceId", this.getNamespaceId())
            .put("settings", this.getSettings());
        return body_;
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.namespaceId == null) ? 0 : this.namespaceId.hashCode());
        result = prime * result + ((this.settings == null) ? 0 : this.settings.hashCode());
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
		CurrentFormMaster other = (CurrentFormMaster) o;
		if (namespaceId == null) {
			return other.namespaceId == null;
		} else if (!namespaceId.equals(other.namespaceId)) {
			return false;
		}
		if (settings == null) {
			return other.settings == null;
		} else if (!settings.equals(other.settings)) {
			return false;
		}
		return true;
	}
}
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

package io.gs2.enhance.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * 現在有効な強化レート設定
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CurrentRateMaster implements IModel, Serializable {
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
	public CurrentRateMaster withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
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
	public CurrentRateMaster withSettings(String settings) {
		this.settings = settings;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("namespaceName", this.getNamespaceName())
            .put("settings", this.getSettings());
        return body_;
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.namespaceName == null) ? 0 : this.namespaceName.hashCode());
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
		CurrentRateMaster other = (CurrentRateMaster) o;
		if (namespaceName == null) {
			return other.namespaceName == null;
		} else if (!namespaceName.equals(other.namespaceName)) {
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
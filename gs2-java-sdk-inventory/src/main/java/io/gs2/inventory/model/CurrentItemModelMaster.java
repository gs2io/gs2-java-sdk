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

package io.gs2.inventory.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.model.IModel;

/**
 * 現在有効な所持品マスター
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CurrentItemModelMaster implements IModel, Serializable {
	/** カテゴリー名 */
	protected String namespaceName;

	/**
	 * カテゴリー名を取得
	 *
	 * @return カテゴリー名
	 */
	public String getNamespaceName() {
		return namespaceName;
	}

	/**
	 * カテゴリー名を設定
	 *
	 * @param namespaceName カテゴリー名
	 */
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}

	/**
	 * カテゴリー名を設定
	 *
	 * @param namespaceName カテゴリー名
	 * @return this
	 */
	public CurrentItemModelMaster withNamespaceName(String namespaceName) {
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
	public CurrentItemModelMaster withSettings(String settings) {
		this.settings = settings;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("namespaceName", this.getNamespaceName())
            .put("settings", this.getSettings());
        return body_;
    }
}
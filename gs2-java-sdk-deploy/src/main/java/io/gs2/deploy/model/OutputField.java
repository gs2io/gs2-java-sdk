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

package io.gs2.deploy.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.model.IModel;

/**
 * Output に記録するフィールド
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class OutputField implements IModel, Serializable {
	/** 名前 */
	protected String name;

	/**
	 * 名前を取得
	 *
	 * @return 名前
	 */
	public String getName() {
		return name;
	}

	/**
	 * 名前を設定
	 *
	 * @param name 名前
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 名前を設定
	 *
	 * @param name 名前
	 * @return this
	 */
	public OutputField withName(String name) {
		this.name = name;
		return this;
	}
	/** フィールド名 */
	protected String fieldName;

	/**
	 * フィールド名を取得
	 *
	 * @return フィールド名
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * フィールド名を設定
	 *
	 * @param fieldName フィールド名
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * フィールド名を設定
	 *
	 * @param fieldName フィールド名
	 * @return this
	 */
	public OutputField withFieldName(String fieldName) {
		this.fieldName = fieldName;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("name", this.getName())
            .put("fieldName", this.getFieldName());
        return body_;
    }
}
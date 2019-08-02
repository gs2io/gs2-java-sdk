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

package io.gs2.matchmaking.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.model.IModel;

/**
 * プレイヤーの属性値
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Attribute implements IModel, Serializable {
	/** 属性名 */
	protected String name;

	/**
	 * 属性名を取得
	 *
	 * @return 属性名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 属性名を設定
	 *
	 * @param name 属性名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 属性名を設定
	 *
	 * @param name 属性名
	 * @return this
	 */
	public Attribute withName(String name) {
		this.name = name;
		return this;
	}
	/** 属性値 */
	protected Integer value;

	/**
	 * 属性値を取得
	 *
	 * @return 属性値
	 */
	public Integer getValue() {
		return value;
	}

	/**
	 * 属性値を設定
	 *
	 * @param value 属性値
	 */
	public void setValue(Integer value) {
		this.value = value;
	}

	/**
	 * 属性値を設定
	 *
	 * @param value 属性値
	 * @return this
	 */
	public Attribute withValue(Integer value) {
		this.value = value;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("name", this.getName())
            .put("value", this.getValue());
        return body_;
    }
}
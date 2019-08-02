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

package io.gs2.mission.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.model.IModel;

/**
 * 設定値
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Config implements IModel, Serializable {
	/** 名前 */
	protected String key;

	/**
	 * 名前を取得
	 *
	 * @return 名前
	 */
	public String getKey() {
		return key;
	}

	/**
	 * 名前を設定
	 *
	 * @param key 名前
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * 名前を設定
	 *
	 * @param key 名前
	 * @return this
	 */
	public Config withKey(String key) {
		this.key = key;
		return this;
	}
	/** 値 */
	protected String value;

	/**
	 * 値を取得
	 *
	 * @return 値
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 値を設定
	 *
	 * @param value 値
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * 値を設定
	 *
	 * @param value 値
	 * @return this
	 */
	public Config withValue(String value) {
		this.value = value;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("key", this.getKey())
            .put("value", this.getValue());
        return body_;
    }
}
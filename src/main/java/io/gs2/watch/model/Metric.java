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

package io.gs2.watch.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * メトリック
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Metric implements Serializable {

	/** タイムスタンプ(エポック秒) */
	private Integer timestamp;

	/** 値 */
	private Double value;


	/**
	 * タイムスタンプ(エポック秒)を取得
	 *
	 * @return タイムスタンプ(エポック秒)
	 */
	public Integer getTimestamp() {
		return timestamp;
	}

	/**
	 * タイムスタンプ(エポック秒)を設定
	 *
	 * @param timestamp タイムスタンプ(エポック秒)
	 */
	public void setTimestamp(Integer timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * タイムスタンプ(エポック秒)を設定
	 *
	 * @param timestamp タイムスタンプ(エポック秒)
	 * @return this
	 */
	public Metric withTimestamp(Integer timestamp) {
		this.timestamp = timestamp;
		return this;
	}

	/**
	 * 値を取得
	 *
	 * @return 値
	 */
	public Double getValue() {
		return value;
	}

	/**
	 * 値を設定
	 *
	 * @param value 値
	 */
	public void setValue(Double value) {
		this.value = value;
	}

	/**
	 * 値を設定
	 *
	 * @param value 値
	 * @return this
	 */
	public Metric withValue(Double value) {
		this.value = value;
		return this;
	}


    public ObjectNode toJson() {

		ObjectNode body = JsonNodeFactory.instance.objectNode()

            .put("timestamp", this.getTimestamp())
            .put("value", this.getValue());

        return body;
    }
}
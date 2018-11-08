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

package io.gs2.gacha.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * レアリティ毎の排出率
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Probability implements Serializable {

	/** レアリティ名 */
	private String name;

	/** 排出率 */
	private Float rate;


	/**
	 * レアリティ名を取得
	 *
	 * @return レアリティ名
	 */
	public String getName() {
		return name;
	}

	/**
	 * レアリティ名を設定
	 *
	 * @param name レアリティ名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * レアリティ名を設定
	 *
	 * @param name レアリティ名
	 * @return this
	 */
	public Probability withName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * 排出率を取得
	 *
	 * @return 排出率
	 */
	public Float getRate() {
		return rate;
	}

	/**
	 * 排出率を設定
	 *
	 * @param rate 排出率
	 */
	public void setRate(Float rate) {
		this.rate = rate;
	}

	/**
	 * 排出率を設定
	 *
	 * @param rate 排出率
	 * @return this
	 */
	public Probability withRate(Float rate) {
		this.rate = rate;
		return this;
	}


    public ObjectNode toJson() {

		ObjectNode body = JsonNodeFactory.instance.objectNode()

            .put("name", this.getName())
            .put("rate", this.getRate());

        return body;
    }
}
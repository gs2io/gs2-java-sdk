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
 * ガチャ
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Gacha implements Serializable {

	/** ガチャ名 */
	private String name;

	/** メタデータ */
	private String meta;

	/** 抽選回数 */
	private Integer drawCount;


	/**
	 * ガチャ名を取得
	 *
	 * @return ガチャ名
	 */
	public String getName() {
		return name;
	}

	/**
	 * ガチャ名を設定
	 *
	 * @param name ガチャ名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ガチャ名を設定
	 *
	 * @param name ガチャ名
	 * @return this
	 */
	public Gacha withName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * メタデータを取得
	 *
	 * @return メタデータ
	 */
	public String getMeta() {
		return meta;
	}

	/**
	 * メタデータを設定
	 *
	 * @param meta メタデータ
	 */
	public void setMeta(String meta) {
		this.meta = meta;
	}

	/**
	 * メタデータを設定
	 *
	 * @param meta メタデータ
	 * @return this
	 */
	public Gacha withMeta(String meta) {
		this.meta = meta;
		return this;
	}

	/**
	 * 抽選回数を取得
	 *
	 * @return 抽選回数
	 */
	public Integer getDrawCount() {
		return drawCount;
	}

	/**
	 * 抽選回数を設定
	 *
	 * @param drawCount 抽選回数
	 */
	public void setDrawCount(Integer drawCount) {
		this.drawCount = drawCount;
	}

	/**
	 * 抽選回数を設定
	 *
	 * @param drawCount 抽選回数
	 * @return this
	 */
	public Gacha withDrawCount(Integer drawCount) {
		this.drawCount = drawCount;
		return this;
	}


    public ObjectNode toJson() {

		ObjectNode body = JsonNodeFactory.instance.objectNode()

            .put("name", this.getName())
            .put("meta", this.getMeta())
            .put("drawCount", this.getDrawCount());

        return body;
    }
}
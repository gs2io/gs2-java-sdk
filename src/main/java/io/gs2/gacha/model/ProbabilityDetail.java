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
 * リソース毎の排出率
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class ProbabilityDetail implements Serializable {

	/** レアリティ名 */
	private String rarityName;

	/** リソースID */
	private String resourceId;

	/** 排出率 */
	private Float rate;


	/**
	 * レアリティ名を取得
	 *
	 * @return レアリティ名
	 */
	public String getRarityName() {
		return rarityName;
	}

	/**
	 * レアリティ名を設定
	 *
	 * @param rarityName レアリティ名
	 */
	public void setRarityName(String rarityName) {
		this.rarityName = rarityName;
	}

	/**
	 * レアリティ名を設定
	 *
	 * @param rarityName レアリティ名
	 * @return this
	 */
	public ProbabilityDetail withRarityName(String rarityName) {
		this.rarityName = rarityName;
		return this;
	}

	/**
	 * リソースIDを取得
	 *
	 * @return リソースID
	 */
	public String getResourceId() {
		return resourceId;
	}

	/**
	 * リソースIDを設定
	 *
	 * @param resourceId リソースID
	 */
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	/**
	 * リソースIDを設定
	 *
	 * @param resourceId リソースID
	 * @return this
	 */
	public ProbabilityDetail withResourceId(String resourceId) {
		this.resourceId = resourceId;
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
	public ProbabilityDetail withRate(Float rate) {
		this.rate = rate;
		return this;
	}


    public ObjectNode toJson() {

		ObjectNode body = JsonNodeFactory.instance.objectNode()

            .put("rarityName", this.getRarityName())
            .put("resourceId", this.getResourceId())
            .put("rate", this.getRate());

        return body;
    }
}
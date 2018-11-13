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
 * 景品レアリティ
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class RarityMaster implements Serializable {

	/** 景品レアリティGRN */
	private String rarityId;

	/** 景品レアリティ名 */
	private String name;

	/** 排出重み */
	private Integer weight;

	/** 作成日時(エポック秒) */
	private Integer createAt;

	/** 最終更新日時(エポック秒) */
	private Integer updateAt;


	/**
	 * 景品レアリティGRNを取得
	 *
	 * @return 景品レアリティGRN
	 */
	public String getRarityId() {
		return rarityId;
	}

	/**
	 * 景品レアリティGRNを設定
	 *
	 * @param rarityId 景品レアリティGRN
	 */
	public void setRarityId(String rarityId) {
		this.rarityId = rarityId;
	}

	/**
	 * 景品レアリティGRNを設定
	 *
	 * @param rarityId 景品レアリティGRN
	 * @return this
	 */
	public RarityMaster withRarityId(String rarityId) {
		this.rarityId = rarityId;
		return this;
	}

	/**
	 * 景品レアリティ名を取得
	 *
	 * @return 景品レアリティ名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 景品レアリティ名を設定
	 *
	 * @param name 景品レアリティ名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 景品レアリティ名を設定
	 *
	 * @param name 景品レアリティ名
	 * @return this
	 */
	public RarityMaster withName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * 排出重みを取得
	 *
	 * @return 排出重み
	 */
	public Integer getWeight() {
		return weight;
	}

	/**
	 * 排出重みを設定
	 *
	 * @param weight 排出重み
	 */
	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	/**
	 * 排出重みを設定
	 *
	 * @param weight 排出重み
	 * @return this
	 */
	public RarityMaster withWeight(Integer weight) {
		this.weight = weight;
		return this;
	}

	/**
	 * 作成日時(エポック秒)を取得
	 *
	 * @return 作成日時(エポック秒)
	 */
	public Integer getCreateAt() {
		return createAt;
	}

	/**
	 * 作成日時(エポック秒)を設定
	 *
	 * @param createAt 作成日時(エポック秒)
	 */
	public void setCreateAt(Integer createAt) {
		this.createAt = createAt;
	}

	/**
	 * 作成日時(エポック秒)を設定
	 *
	 * @param createAt 作成日時(エポック秒)
	 * @return this
	 */
	public RarityMaster withCreateAt(Integer createAt) {
		this.createAt = createAt;
		return this;
	}

	/**
	 * 最終更新日時(エポック秒)を取得
	 *
	 * @return 最終更新日時(エポック秒)
	 */
	public Integer getUpdateAt() {
		return updateAt;
	}

	/**
	 * 最終更新日時(エポック秒)を設定
	 *
	 * @param updateAt 最終更新日時(エポック秒)
	 */
	public void setUpdateAt(Integer updateAt) {
		this.updateAt = updateAt;
	}

	/**
	 * 最終更新日時(エポック秒)を設定
	 *
	 * @param updateAt 最終更新日時(エポック秒)
	 * @return this
	 */
	public RarityMaster withUpdateAt(Integer updateAt) {
		this.updateAt = updateAt;
		return this;
	}


    public ObjectNode toJson() {

		ObjectNode body = JsonNodeFactory.instance.objectNode()

            .put("rarityId", this.getRarityId())
            .put("name", this.getName())
            .put("weight", this.getWeight())
            .put("createAt", this.getCreateAt())
            .put("updateAt", this.getUpdateAt());

        return body;
    }
}
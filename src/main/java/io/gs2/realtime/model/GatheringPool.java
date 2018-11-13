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

package io.gs2.realtime.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * ギャザリングプール
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class GatheringPool implements Serializable {

	/** ギャザリングプールGRN */
	private String gatheringPoolId;

	/** オーナーID */
	private String ownerId;

	/** ギャザリングプール名 */
	private String name;

	/** 説明文 */
	private String description;

	/** 作成日時(エポック秒) */
	private Integer createAt;

	/** 最終更新日時(エポック秒) */
	private Integer updateAt;


	/**
	 * ギャザリングプールGRNを取得
	 *
	 * @return ギャザリングプールGRN
	 */
	public String getGatheringPoolId() {
		return gatheringPoolId;
	}

	/**
	 * ギャザリングプールGRNを設定
	 *
	 * @param gatheringPoolId ギャザリングプールGRN
	 */
	public void setGatheringPoolId(String gatheringPoolId) {
		this.gatheringPoolId = gatheringPoolId;
	}

	/**
	 * ギャザリングプールGRNを設定
	 *
	 * @param gatheringPoolId ギャザリングプールGRN
	 * @return this
	 */
	public GatheringPool withGatheringPoolId(String gatheringPoolId) {
		this.gatheringPoolId = gatheringPoolId;
		return this;
	}

	/**
	 * オーナーIDを取得
	 *
	 * @return オーナーID
	 */
	public String getOwnerId() {
		return ownerId;
	}

	/**
	 * オーナーIDを設定
	 *
	 * @param ownerId オーナーID
	 */
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * オーナーIDを設定
	 *
	 * @param ownerId オーナーID
	 * @return this
	 */
	public GatheringPool withOwnerId(String ownerId) {
		this.ownerId = ownerId;
		return this;
	}

	/**
	 * ギャザリングプール名を取得
	 *
	 * @return ギャザリングプール名
	 */
	public String getName() {
		return name;
	}

	/**
	 * ギャザリングプール名を設定
	 *
	 * @param name ギャザリングプール名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ギャザリングプール名を設定
	 *
	 * @param name ギャザリングプール名
	 * @return this
	 */
	public GatheringPool withName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * 説明文を取得
	 *
	 * @return 説明文
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 説明文を設定
	 *
	 * @param description 説明文
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 説明文を設定
	 *
	 * @param description 説明文
	 * @return this
	 */
	public GatheringPool withDescription(String description) {
		this.description = description;
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
	public GatheringPool withCreateAt(Integer createAt) {
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
	public GatheringPool withUpdateAt(Integer updateAt) {
		this.updateAt = updateAt;
		return this;
	}


    public ObjectNode toJson() {

		ObjectNode body = JsonNodeFactory.instance.objectNode()

            .put("gatheringPoolId", this.getGatheringPoolId())
            .put("ownerId", this.getOwnerId())
            .put("name", this.getName())
            .put("description", this.getDescription())
            .put("createAt", this.getCreateAt())
            .put("updateAt", this.getUpdateAt());

        return body;
    }
}
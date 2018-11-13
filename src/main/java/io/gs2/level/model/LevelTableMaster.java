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

package io.gs2.level.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * レベルテーブル
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class LevelTableMaster implements Serializable {

	/** レベルテーブルGRN */
	private String levelTableId;

	/** レベルテーブル名 */
	private String name;

	/** 作成日時(エポック秒) */
	private Integer createAt;

	/** 最終更新日時(エポック秒) */
	private Integer updateAt;


	/**
	 * レベルテーブルGRNを取得
	 *
	 * @return レベルテーブルGRN
	 */
	public String getLevelTableId() {
		return levelTableId;
	}

	/**
	 * レベルテーブルGRNを設定
	 *
	 * @param levelTableId レベルテーブルGRN
	 */
	public void setLevelTableId(String levelTableId) {
		this.levelTableId = levelTableId;
	}

	/**
	 * レベルテーブルGRNを設定
	 *
	 * @param levelTableId レベルテーブルGRN
	 * @return this
	 */
	public LevelTableMaster withLevelTableId(String levelTableId) {
		this.levelTableId = levelTableId;
		return this;
	}

	/**
	 * レベルテーブル名を取得
	 *
	 * @return レベルテーブル名
	 */
	public String getName() {
		return name;
	}

	/**
	 * レベルテーブル名を設定
	 *
	 * @param name レベルテーブル名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * レベルテーブル名を設定
	 *
	 * @param name レベルテーブル名
	 * @return this
	 */
	public LevelTableMaster withName(String name) {
		this.name = name;
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
	public LevelTableMaster withCreateAt(Integer createAt) {
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
	public LevelTableMaster withUpdateAt(Integer updateAt) {
		this.updateAt = updateAt;
		return this;
	}


    public ObjectNode toJson() {

		ObjectNode body = JsonNodeFactory.instance.objectNode()

            .put("levelTableId", this.getLevelTableId())
            .put("name", this.getName())
            .put("createAt", this.getCreateAt())
            .put("updateAt", this.getUpdateAt());

        return body;
    }
}
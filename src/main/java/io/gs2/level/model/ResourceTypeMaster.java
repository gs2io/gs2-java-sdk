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
 * リソースタイプ
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class ResourceTypeMaster implements Serializable {

	/** リソースタイプGRN */
	private String resourceTypeId;

	/** リソースタイプ名 */
	private String name;

	/** メタデータ */
	private String meta;

	/** レベルテーブル名 */
	private String levelTableName;

	/** デフォルト取得済み経験値 */
	private Long defaultExperience;

	/** デフォルトレベルキャップ */
	private Integer defaultLevelCap;

	/** 最大レベルキャップ */
	private Integer maxLevelCap;

	/** 作成日時(エポック秒) */
	private Integer createAt;

	/** 最終更新日時(エポック秒) */
	private Integer updateAt;


	/**
	 * リソースタイプGRNを取得
	 *
	 * @return リソースタイプGRN
	 */
	public String getResourceTypeId() {
		return resourceTypeId;
	}

	/**
	 * リソースタイプGRNを設定
	 *
	 * @param resourceTypeId リソースタイプGRN
	 */
	public void setResourceTypeId(String resourceTypeId) {
		this.resourceTypeId = resourceTypeId;
	}

	/**
	 * リソースタイプGRNを設定
	 *
	 * @param resourceTypeId リソースタイプGRN
	 * @return this
	 */
	public ResourceTypeMaster withResourceTypeId(String resourceTypeId) {
		this.resourceTypeId = resourceTypeId;
		return this;
	}

	/**
	 * リソースタイプ名を取得
	 *
	 * @return リソースタイプ名
	 */
	public String getName() {
		return name;
	}

	/**
	 * リソースタイプ名を設定
	 *
	 * @param name リソースタイプ名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * リソースタイプ名を設定
	 *
	 * @param name リソースタイプ名
	 * @return this
	 */
	public ResourceTypeMaster withName(String name) {
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
	public ResourceTypeMaster withMeta(String meta) {
		this.meta = meta;
		return this;
	}

	/**
	 * レベルテーブル名を取得
	 *
	 * @return レベルテーブル名
	 */
	public String getLevelTableName() {
		return levelTableName;
	}

	/**
	 * レベルテーブル名を設定
	 *
	 * @param levelTableName レベルテーブル名
	 */
	public void setLevelTableName(String levelTableName) {
		this.levelTableName = levelTableName;
	}

	/**
	 * レベルテーブル名を設定
	 *
	 * @param levelTableName レベルテーブル名
	 * @return this
	 */
	public ResourceTypeMaster withLevelTableName(String levelTableName) {
		this.levelTableName = levelTableName;
		return this;
	}

	/**
	 * デフォルト取得済み経験値を取得
	 *
	 * @return デフォルト取得済み経験値
	 */
	public Long getDefaultExperience() {
		return defaultExperience;
	}

	/**
	 * デフォルト取得済み経験値を設定
	 *
	 * @param defaultExperience デフォルト取得済み経験値
	 */
	public void setDefaultExperience(Long defaultExperience) {
		this.defaultExperience = defaultExperience;
	}

	/**
	 * デフォルト取得済み経験値を設定
	 *
	 * @param defaultExperience デフォルト取得済み経験値
	 * @return this
	 */
	public ResourceTypeMaster withDefaultExperience(Long defaultExperience) {
		this.defaultExperience = defaultExperience;
		return this;
	}

	/**
	 * デフォルトレベルキャップを取得
	 *
	 * @return デフォルトレベルキャップ
	 */
	public Integer getDefaultLevelCap() {
		return defaultLevelCap;
	}

	/**
	 * デフォルトレベルキャップを設定
	 *
	 * @param defaultLevelCap デフォルトレベルキャップ
	 */
	public void setDefaultLevelCap(Integer defaultLevelCap) {
		this.defaultLevelCap = defaultLevelCap;
	}

	/**
	 * デフォルトレベルキャップを設定
	 *
	 * @param defaultLevelCap デフォルトレベルキャップ
	 * @return this
	 */
	public ResourceTypeMaster withDefaultLevelCap(Integer defaultLevelCap) {
		this.defaultLevelCap = defaultLevelCap;
		return this;
	}

	/**
	 * 最大レベルキャップを取得
	 *
	 * @return 最大レベルキャップ
	 */
	public Integer getMaxLevelCap() {
		return maxLevelCap;
	}

	/**
	 * 最大レベルキャップを設定
	 *
	 * @param maxLevelCap 最大レベルキャップ
	 */
	public void setMaxLevelCap(Integer maxLevelCap) {
		this.maxLevelCap = maxLevelCap;
	}

	/**
	 * 最大レベルキャップを設定
	 *
	 * @param maxLevelCap 最大レベルキャップ
	 * @return this
	 */
	public ResourceTypeMaster withMaxLevelCap(Integer maxLevelCap) {
		this.maxLevelCap = maxLevelCap;
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
	public ResourceTypeMaster withCreateAt(Integer createAt) {
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
	public ResourceTypeMaster withUpdateAt(Integer updateAt) {
		this.updateAt = updateAt;
		return this;
	}


    public ObjectNode toJson() {

		ObjectNode body = JsonNodeFactory.instance.objectNode()

            .put("resourceTypeId", this.getResourceTypeId())
            .put("name", this.getName())
            .put("meta", this.getMeta())
            .put("levelTableName", this.getLevelTableName())
            .put("defaultExperience", this.getDefaultExperience())
            .put("defaultLevelCap", this.getDefaultLevelCap())
            .put("maxLevelCap", this.getMaxLevelCap())
            .put("createAt", this.getCreateAt())
            .put("updateAt", this.getUpdateAt());

        return body;
    }
}
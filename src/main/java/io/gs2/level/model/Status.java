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
 * ステータス
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Status implements Serializable {

	/** ステータスGRN */
	private String statusId;

	/** リソースタイプ名 */
	private String resourceTypeName;

	/** ユーザID */
	private String userId;

	/** リソースID */
	private String resourceId;

	/** レベル */
	private Integer level;

	/** レベルキャップ */
	private Integer levelCap;

	/** 累計獲得経験値 */
	private Long experience;

	/** 次のレベルに上がる累計獲得経験値 */
	private Long nextLevelExperience;

	/** 作成日時(エポック秒) */
	private Integer createAt;

	/** 最終更新日時(エポック秒) */
	private Integer updateAt;


	/**
	 * ステータスGRNを取得
	 *
	 * @return ステータスGRN
	 */
	public String getStatusId() {
		return statusId;
	}

	/**
	 * ステータスGRNを設定
	 *
	 * @param statusId ステータスGRN
	 */
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	/**
	 * ステータスGRNを設定
	 *
	 * @param statusId ステータスGRN
	 * @return this
	 */
	public Status withStatusId(String statusId) {
		this.statusId = statusId;
		return this;
	}

	/**
	 * リソースタイプ名を取得
	 *
	 * @return リソースタイプ名
	 */
	public String getResourceTypeName() {
		return resourceTypeName;
	}

	/**
	 * リソースタイプ名を設定
	 *
	 * @param resourceTypeName リソースタイプ名
	 */
	public void setResourceTypeName(String resourceTypeName) {
		this.resourceTypeName = resourceTypeName;
	}

	/**
	 * リソースタイプ名を設定
	 *
	 * @param resourceTypeName リソースタイプ名
	 * @return this
	 */
	public Status withResourceTypeName(String resourceTypeName) {
		this.resourceTypeName = resourceTypeName;
		return this;
	}

	/**
	 * ユーザIDを取得
	 *
	 * @return ユーザID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * ユーザIDを設定
	 *
	 * @param userId ユーザID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * ユーザIDを設定
	 *
	 * @param userId ユーザID
	 * @return this
	 */
	public Status withUserId(String userId) {
		this.userId = userId;
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
	public Status withResourceId(String resourceId) {
		this.resourceId = resourceId;
		return this;
	}

	/**
	 * レベルを取得
	 *
	 * @return レベル
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * レベルを設定
	 *
	 * @param level レベル
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * レベルを設定
	 *
	 * @param level レベル
	 * @return this
	 */
	public Status withLevel(Integer level) {
		this.level = level;
		return this;
	}

	/**
	 * レベルキャップを取得
	 *
	 * @return レベルキャップ
	 */
	public Integer getLevelCap() {
		return levelCap;
	}

	/**
	 * レベルキャップを設定
	 *
	 * @param levelCap レベルキャップ
	 */
	public void setLevelCap(Integer levelCap) {
		this.levelCap = levelCap;
	}

	/**
	 * レベルキャップを設定
	 *
	 * @param levelCap レベルキャップ
	 * @return this
	 */
	public Status withLevelCap(Integer levelCap) {
		this.levelCap = levelCap;
		return this;
	}

	/**
	 * 累計獲得経験値を取得
	 *
	 * @return 累計獲得経験値
	 */
	public Long getExperience() {
		return experience;
	}

	/**
	 * 累計獲得経験値を設定
	 *
	 * @param experience 累計獲得経験値
	 */
	public void setExperience(Long experience) {
		this.experience = experience;
	}

	/**
	 * 累計獲得経験値を設定
	 *
	 * @param experience 累計獲得経験値
	 * @return this
	 */
	public Status withExperience(Long experience) {
		this.experience = experience;
		return this;
	}

	/**
	 * 次のレベルに上がる累計獲得経験値を取得
	 *
	 * @return 次のレベルに上がる累計獲得経験値
	 */
	public Long getNextLevelExperience() {
		return nextLevelExperience;
	}

	/**
	 * 次のレベルに上がる累計獲得経験値を設定
	 *
	 * @param nextLevelExperience 次のレベルに上がる累計獲得経験値
	 */
	public void setNextLevelExperience(Long nextLevelExperience) {
		this.nextLevelExperience = nextLevelExperience;
	}

	/**
	 * 次のレベルに上がる累計獲得経験値を設定
	 *
	 * @param nextLevelExperience 次のレベルに上がる累計獲得経験値
	 * @return this
	 */
	public Status withNextLevelExperience(Long nextLevelExperience) {
		this.nextLevelExperience = nextLevelExperience;
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
	public Status withCreateAt(Integer createAt) {
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
	public Status withUpdateAt(Integer updateAt) {
		this.updateAt = updateAt;
		return this;
	}


    public ObjectNode toJson() {

		ObjectNode body = JsonNodeFactory.instance.objectNode()

            .put("statusId", this.getStatusId())
            .put("resourceTypeName", this.getResourceTypeName())
            .put("userId", this.getUserId())
            .put("resourceId", this.getResourceId())
            .put("level", this.getLevel())
            .put("levelCap", this.getLevelCap())
            .put("experience", this.getExperience())
            .put("nextLevelExperience", this.getNextLevelExperience())
            .put("createAt", this.getCreateAt())
            .put("updateAt", this.getUpdateAt());

        return body;
    }
}
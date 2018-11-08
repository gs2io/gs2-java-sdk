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
 * レベルアップ経験値閾値
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class LevelThresholdMaster implements Serializable {

	/** レベルアップ経験値閾値GRN */
	private String thresholdId;

	/** レベルアップ経験値閾値 */
	private Long threshold;

	/** 作成日時(エポック秒) */
	private Integer createAt;

	/** 最終更新日時(エポック秒) */
	private Integer updateAt;


	/**
	 * レベルアップ経験値閾値GRNを取得
	 *
	 * @return レベルアップ経験値閾値GRN
	 */
	public String getThresholdId() {
		return thresholdId;
	}

	/**
	 * レベルアップ経験値閾値GRNを設定
	 *
	 * @param thresholdId レベルアップ経験値閾値GRN
	 */
	public void setThresholdId(String thresholdId) {
		this.thresholdId = thresholdId;
	}

	/**
	 * レベルアップ経験値閾値GRNを設定
	 *
	 * @param thresholdId レベルアップ経験値閾値GRN
	 * @return this
	 */
	public LevelThresholdMaster withThresholdId(String thresholdId) {
		this.thresholdId = thresholdId;
		return this;
	}

	/**
	 * レベルアップ経験値閾値を取得
	 *
	 * @return レベルアップ経験値閾値
	 */
	public Long getThreshold() {
		return threshold;
	}

	/**
	 * レベルアップ経験値閾値を設定
	 *
	 * @param threshold レベルアップ経験値閾値
	 */
	public void setThreshold(Long threshold) {
		this.threshold = threshold;
	}

	/**
	 * レベルアップ経験値閾値を設定
	 *
	 * @param threshold レベルアップ経験値閾値
	 * @return this
	 */
	public LevelThresholdMaster withThreshold(Long threshold) {
		this.threshold = threshold;
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
	public LevelThresholdMaster withCreateAt(Integer createAt) {
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
	public LevelThresholdMaster withUpdateAt(Integer updateAt) {
		this.updateAt = updateAt;
		return this;
	}


    public ObjectNode toJson() {

		ObjectNode body = JsonNodeFactory.instance.objectNode()

            .put("thresholdId", this.getThresholdId())
            .put("threshold", this.getThreshold())
            .put("createAt", this.getCreateAt())
            .put("updateAt", this.getUpdateAt());

        return body;
    }
}
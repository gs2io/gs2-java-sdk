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

package io.gs2.level.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.level.model.*;
import io.gs2.level.Gs2Level;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateLevelThresholdMasterRequest extends Gs2BasicRequest<CreateLevelThresholdMasterRequest> {

	public static class Constant extends Gs2Level.Constant {
		public static final String FUNCTION = "CreateLevelThresholdMaster";
	}

	/** リソースプール */
	private String resourcePoolName;

	/** レベルテーブル */
	private String levelTableName;

	/** レベルアップ経験値閾値 */
	private Long threshold;


	/**
	 * リソースプールを取得
	 *
	 * @return リソースプール
	 */
	public String getResourcePoolName() {
		return resourcePoolName;
	}

	/**
	 * リソースプールを設定
	 *
	 * @param resourcePoolName リソースプール
	 */
	public void setResourcePoolName(String resourcePoolName) {
		this.resourcePoolName = resourcePoolName;
	}

	/**
	 * リソースプールを設定
	 *
	 * @param resourcePoolName リソースプール
	 * @return this
	 */
	public CreateLevelThresholdMasterRequest withResourcePoolName(String resourcePoolName) {
		setResourcePoolName(resourcePoolName);
		return this;
	}

	/**
	 * レベルテーブルを取得
	 *
	 * @return レベルテーブル
	 */
	public String getLevelTableName() {
		return levelTableName;
	}

	/**
	 * レベルテーブルを設定
	 *
	 * @param levelTableName レベルテーブル
	 */
	public void setLevelTableName(String levelTableName) {
		this.levelTableName = levelTableName;
	}

	/**
	 * レベルテーブルを設定
	 *
	 * @param levelTableName レベルテーブル
	 * @return this
	 */
	public CreateLevelThresholdMasterRequest withLevelTableName(String levelTableName) {
		setLevelTableName(levelTableName);
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
	public CreateLevelThresholdMasterRequest withThreshold(Long threshold) {
		setThreshold(threshold);
		return this;
	}

}
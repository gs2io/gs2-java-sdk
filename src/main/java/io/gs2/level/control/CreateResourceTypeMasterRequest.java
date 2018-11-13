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
public class CreateResourceTypeMasterRequest extends Gs2BasicRequest<CreateResourceTypeMasterRequest> {

	public static class Constant extends Gs2Level.Constant {
		public static final String FUNCTION = "CreateResourceTypeMaster";
	}

	/** リソースプール */
	private String resourcePoolName;

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
	public CreateResourceTypeMasterRequest withResourcePoolName(String resourcePoolName) {
		setResourcePoolName(resourcePoolName);
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
	public CreateResourceTypeMasterRequest withName(String name) {
		setName(name);
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
	public CreateResourceTypeMasterRequest withMeta(String meta) {
		setMeta(meta);
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
	public CreateResourceTypeMasterRequest withLevelTableName(String levelTableName) {
		setLevelTableName(levelTableName);
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
	public CreateResourceTypeMasterRequest withDefaultExperience(Long defaultExperience) {
		setDefaultExperience(defaultExperience);
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
	public CreateResourceTypeMasterRequest withDefaultLevelCap(Integer defaultLevelCap) {
		setDefaultLevelCap(defaultLevelCap);
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
	public CreateResourceTypeMasterRequest withMaxLevelCap(Integer maxLevelCap) {
		setMaxLevelCap(maxLevelCap);
		return this;
	}

}
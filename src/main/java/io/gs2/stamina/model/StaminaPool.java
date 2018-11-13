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

package io.gs2.stamina.model;

import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * スタミナプール
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class StaminaPool implements Serializable {

	/** スタミナプールGRN */
	private String staminaPoolId;

	/** オーナーID */
	private String ownerId;

	/** スタミナプール名 */
	private String name;

	/** 説明文 */
	private String description;

	/** サービスクラス */
	private String serviceClass;

	/** スタミナの回復速度(秒) */
	private Integer increaseInterval;

	/** スタミナ消費時 に実行されるGS2-Script */
	private String consumeStaminaTriggerScript;

	/** スタミナ消費完了時 に実行されるGS2-Script */
	private String consumeStaminaDoneTriggerScript;

	/** スタミナ回復時 に実行されるGS2-Script */
	private String addStaminaTriggerScript;

	/** スタミナ回復完了時 に実行されるGS2-Script */
	private String addStaminaDoneTriggerScript;

	/** スタミナの最大値取得 に実行されるGS2-Script */
	private String getMaxStaminaTriggerScript;

	/** 作成日時(エポック秒) */
	private Integer createAt;

	/** 最終更新日時(エポック秒) */
	private Integer updateAt;


	/**
	 * スタミナプールGRNを取得
	 *
	 * @return スタミナプールGRN
	 */
	public String getStaminaPoolId() {
		return staminaPoolId;
	}

	/**
	 * スタミナプールGRNを設定
	 *
	 * @param staminaPoolId スタミナプールGRN
	 */
	public void setStaminaPoolId(String staminaPoolId) {
		this.staminaPoolId = staminaPoolId;
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
	 * スタミナプール名を取得
	 *
	 * @return スタミナプール名
	 */
	public String getName() {
		return name;
	}

	/**
	 * スタミナプール名を設定
	 *
	 * @param name スタミナプール名
	 */
	public void setName(String name) {
		this.name = name;
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
	 * サービスクラスを取得
	 *
	 * @return サービスクラス
	 */
	public String getServiceClass() {
		return serviceClass;
	}

	/**
	 * サービスクラスを設定
	 *
	 * @param serviceClass サービスクラス
	 */
	public void setServiceClass(String serviceClass) {
		this.serviceClass = serviceClass;
	}

	/**
	 * スタミナの回復速度(秒)を取得
	 *
	 * @return スタミナの回復速度(秒)
	 */
	public Integer getIncreaseInterval() {
		return increaseInterval;
	}

	/**
	 * スタミナの回復速度(秒)を設定
	 *
	 * @param increaseInterval スタミナの回復速度(秒)
	 */
	public void setIncreaseInterval(Integer increaseInterval) {
		this.increaseInterval = increaseInterval;
	}

	/**
	 * スタミナ消費時 に実行されるGS2-Scriptを取得
	 *
	 * @return スタミナ消費時 に実行されるGS2-Script
	 */
	public String getConsumeStaminaTriggerScript() {
		return consumeStaminaTriggerScript;
	}

	/**
	 * スタミナ消費時 に実行されるGS2-Scriptを設定
	 *
	 * @param consumeStaminaTriggerScript スタミナ消費時 に実行されるGS2-Script
	 */
	public void setConsumeStaminaTriggerScript(String consumeStaminaTriggerScript) {
		this.consumeStaminaTriggerScript = consumeStaminaTriggerScript;
	}

	/**
	 * スタミナ消費完了時 に実行されるGS2-Scriptを取得
	 *
	 * @return スタミナ消費完了時 に実行されるGS2-Script
	 */
	public String getConsumeStaminaDoneTriggerScript() {
		return consumeStaminaDoneTriggerScript;
	}

	/**
	 * スタミナ消費完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param consumeStaminaDoneTriggerScript スタミナ消費完了時 に実行されるGS2-Script
	 */
	public void setConsumeStaminaDoneTriggerScript(String consumeStaminaDoneTriggerScript) {
		this.consumeStaminaDoneTriggerScript = consumeStaminaDoneTriggerScript;
	}

	/**
	 * スタミナ回復時 に実行されるGS2-Scriptを取得
	 *
	 * @return スタミナ回復時 に実行されるGS2-Script
	 */
	public String getAddStaminaTriggerScript() {
		return addStaminaTriggerScript;
	}

	/**
	 * スタミナ回復時 に実行されるGS2-Scriptを設定
	 *
	 * @param addStaminaTriggerScript スタミナ回復時 に実行されるGS2-Script
	 */
	public void setAddStaminaTriggerScript(String addStaminaTriggerScript) {
		this.addStaminaTriggerScript = addStaminaTriggerScript;
	}

	/**
	 * スタミナ回復完了時 に実行されるGS2-Scriptを取得
	 *
	 * @return スタミナ回復完了時 に実行されるGS2-Script
	 */
	public String getAddStaminaDoneTriggerScript() {
		return addStaminaDoneTriggerScript;
	}

	/**
	 * スタミナ回復完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param addStaminaDoneTriggerScript スタミナ回復完了時 に実行されるGS2-Script
	 */
	public void setAddStaminaDoneTriggerScript(String addStaminaDoneTriggerScript) {
		this.addStaminaDoneTriggerScript = addStaminaDoneTriggerScript;
	}

	/**
	 * スタミナの最大値取得 に実行されるGS2-Scriptを取得
	 *
	 * @return スタミナの最大値取得 に実行されるGS2-Script
	 */
	public String getGetMaxStaminaTriggerScript() {
		return getMaxStaminaTriggerScript;
	}

	/**
	 * スタミナの最大値取得 に実行されるGS2-Scriptを設定
	 *
	 * @param getMaxStaminaTriggerScript スタミナの最大値取得 に実行されるGS2-Script
	 */
	public void setGetMaxStaminaTriggerScript(String getMaxStaminaTriggerScript) {
		this.getMaxStaminaTriggerScript = getMaxStaminaTriggerScript;
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

}
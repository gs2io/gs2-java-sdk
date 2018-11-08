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

package io.gs2.stamina.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.stamina.Gs2Stamina;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateStaminaPoolRequest extends Gs2BasicRequest<UpdateStaminaPoolRequest> {

	public static class Constant extends Gs2Stamina.Constant {
		public static final String FUNCTION = "UpdateStaminaPool";
	}

	/** スタミナプールの名前を指定します。 */
	private String staminaPoolName;

	/** スタミナプールの説明 */
	private String description;

	/** スタミナプールのサービスクラス */
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


	/**
	 * スタミナプールの名前を指定します。を取得
	 *
	 * @return スタミナプールの名前を指定します。
	 */
	public String getStaminaPoolName() {
		return staminaPoolName;
	}

	/**
	 * スタミナプールの名前を指定します。を設定
	 *
	 * @param staminaPoolName スタミナプールの名前を指定します。
	 */
	public void setStaminaPoolName(String staminaPoolName) {
		this.staminaPoolName = staminaPoolName;
	}

	/**
	 * スタミナプールの名前を指定します。を設定
	 *
	 * @param staminaPoolName スタミナプールの名前を指定します。
	 * @return this
	 */
	public UpdateStaminaPoolRequest withStaminaPoolName(String staminaPoolName) {
		setStaminaPoolName(staminaPoolName);
		return this;
	}

	/**
	 * スタミナプールの説明を取得
	 *
	 * @return スタミナプールの説明
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * スタミナプールの説明を設定
	 *
	 * @param description スタミナプールの説明
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * スタミナプールの説明を設定
	 *
	 * @param description スタミナプールの説明
	 * @return this
	 */
	public UpdateStaminaPoolRequest withDescription(String description) {
		setDescription(description);
		return this;
	}

	/**
	 * スタミナプールのサービスクラスを取得
	 *
	 * @return スタミナプールのサービスクラス
	 */
	public String getServiceClass() {
		return serviceClass;
	}

	/**
	 * スタミナプールのサービスクラスを設定
	 *
	 * @param serviceClass スタミナプールのサービスクラス
	 */
	public void setServiceClass(String serviceClass) {
		this.serviceClass = serviceClass;
	}

	/**
	 * スタミナプールのサービスクラスを設定
	 *
	 * @param serviceClass スタミナプールのサービスクラス
	 * @return this
	 */
	public UpdateStaminaPoolRequest withServiceClass(String serviceClass) {
		setServiceClass(serviceClass);
		return this;
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
	 * スタミナの回復速度(秒)を設定
	 *
	 * @param increaseInterval スタミナの回復速度(秒)
	 * @return this
	 */
	public UpdateStaminaPoolRequest withIncreaseInterval(Integer increaseInterval) {
		setIncreaseInterval(increaseInterval);
		return this;
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
	 * スタミナ消費時 に実行されるGS2-Scriptを設定
	 *
	 * @param consumeStaminaTriggerScript スタミナ消費時 に実行されるGS2-Script
	 * @return this
	 */
	public UpdateStaminaPoolRequest withConsumeStaminaTriggerScript(String consumeStaminaTriggerScript) {
		setConsumeStaminaTriggerScript(consumeStaminaTriggerScript);
		return this;
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
	 * スタミナ消費完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param consumeStaminaDoneTriggerScript スタミナ消費完了時 に実行されるGS2-Script
	 * @return this
	 */
	public UpdateStaminaPoolRequest withConsumeStaminaDoneTriggerScript(String consumeStaminaDoneTriggerScript) {
		setConsumeStaminaDoneTriggerScript(consumeStaminaDoneTriggerScript);
		return this;
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
	 * スタミナ回復時 に実行されるGS2-Scriptを設定
	 *
	 * @param addStaminaTriggerScript スタミナ回復時 に実行されるGS2-Script
	 * @return this
	 */
	public UpdateStaminaPoolRequest withAddStaminaTriggerScript(String addStaminaTriggerScript) {
		setAddStaminaTriggerScript(addStaminaTriggerScript);
		return this;
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
	 * スタミナ回復完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param addStaminaDoneTriggerScript スタミナ回復完了時 に実行されるGS2-Script
	 * @return this
	 */
	public UpdateStaminaPoolRequest withAddStaminaDoneTriggerScript(String addStaminaDoneTriggerScript) {
		setAddStaminaDoneTriggerScript(addStaminaDoneTriggerScript);
		return this;
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
	 * スタミナの最大値取得 に実行されるGS2-Scriptを設定
	 *
	 * @param getMaxStaminaTriggerScript スタミナの最大値取得 に実行されるGS2-Script
	 * @return this
	 */
	public UpdateStaminaPoolRequest withGetMaxStaminaTriggerScript(String getMaxStaminaTriggerScript) {
		setGetMaxStaminaTriggerScript(getMaxStaminaTriggerScript);
		return this;
	}

}
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
import io.gs2.control.Gs2UserRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class ConsumeStaminaRequest extends Gs2UserRequest<ConsumeStaminaRequest> {

	public static class Constant extends Gs2Stamina.Constant {
		public static final String FUNCTION = "ConsumeStamina";
	}

	/** スタミナプールの名前を指定します。 */
	private String staminaPoolName;

	/** スタミナの増減量 */
	private Integer variation;

	/** スタミナの最大値 */
	private Integer maxValue;


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
	public ConsumeStaminaRequest withStaminaPoolName(String staminaPoolName) {
		setStaminaPoolName(staminaPoolName);
		return this;
	}

	/**
	 * スタミナの増減量を取得
	 *
	 * @return スタミナの増減量
	 */
	public Integer getVariation() {
		return variation;
	}

	/**
	 * スタミナの増減量を設定
	 *
	 * @param variation スタミナの増減量
	 */
	public void setVariation(Integer variation) {
		this.variation = variation;
	}

	/**
	 * スタミナの増減量を設定
	 *
	 * @param variation スタミナの増減量
	 * @return this
	 */
	public ConsumeStaminaRequest withVariation(Integer variation) {
		setVariation(variation);
		return this;
	}

	/**
	 * スタミナの最大値を取得
	 *
	 * @return スタミナの最大値
	 */
	public Integer getMaxValue() {
		return maxValue;
	}

	/**
	 * スタミナの最大値を設定
	 *
	 * @param maxValue スタミナの最大値
	 */
	public void setMaxValue(Integer maxValue) {
		this.maxValue = maxValue;
	}

	/**
	 * スタミナの最大値を設定
	 *
	 * @param maxValue スタミナの最大値
	 * @return this
	 */
	public ConsumeStaminaRequest withMaxValue(Integer maxValue) {
		setMaxValue(maxValue);
		return this;
	}

}
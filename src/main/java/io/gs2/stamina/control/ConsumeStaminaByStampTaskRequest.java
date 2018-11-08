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
public class ConsumeStaminaByStampTaskRequest extends Gs2UserRequest<ConsumeStaminaByStampTaskRequest> {

	public static class Constant extends Gs2Stamina.Constant {
		public static final String FUNCTION = "ConsumeStaminaByStampTask";
	}

	/** スタンプタスク */
	private String task;

	/** スタンプの暗号鍵 */
	private String keyName;

	/** トランザクションID */
	private String transactionId;

	/** スタミナの最大値 */
	private Integer maxValue;


	/**
	 * スタンプタスクを取得
	 *
	 * @return スタンプタスク
	 */
	public String getTask() {
		return task;
	}

	/**
	 * スタンプタスクを設定
	 *
	 * @param task スタンプタスク
	 */
	public void setTask(String task) {
		this.task = task;
	}

	/**
	 * スタンプタスクを設定
	 *
	 * @param task スタンプタスク
	 * @return this
	 */
	public ConsumeStaminaByStampTaskRequest withTask(String task) {
		setTask(task);
		return this;
	}

	/**
	 * スタンプの暗号鍵を取得
	 *
	 * @return スタンプの暗号鍵
	 */
	public String getKeyName() {
		return keyName;
	}

	/**
	 * スタンプの暗号鍵を設定
	 *
	 * @param keyName スタンプの暗号鍵
	 */
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	/**
	 * スタンプの暗号鍵を設定
	 *
	 * @param keyName スタンプの暗号鍵
	 * @return this
	 */
	public ConsumeStaminaByStampTaskRequest withKeyName(String keyName) {
		setKeyName(keyName);
		return this;
	}

	/**
	 * トランザクションIDを取得
	 *
	 * @return トランザクションID
	 */
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * トランザクションIDを設定
	 *
	 * @param transactionId トランザクションID
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * トランザクションIDを設定
	 *
	 * @param transactionId トランザクションID
	 * @return this
	 */
	public ConsumeStaminaByStampTaskRequest withTransactionId(String transactionId) {
		setTransactionId(transactionId);
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
	public ConsumeStaminaByStampTaskRequest withMaxValue(Integer maxValue) {
		setMaxValue(maxValue);
		return this;
	}

}
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

package io.gs2.consumableItem.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.consumableItem.model.*;
import io.gs2.consumableItem.Gs2ConsumableItem;
import io.gs2.control.Gs2UserRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class ConsumeItemByStampTaskRequest extends Gs2UserRequest<ConsumeItemByStampTaskRequest> {

	public static class Constant extends Gs2ConsumableItem.Constant {
		public static final String FUNCTION = "ConsumeItemByStampTask";
	}

	/** スタンプタスク */
	private String task;

	/** スタンプの暗号鍵 */
	private String keyName;

	/** トランザクションID */
	private String transactionId;


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
	public ConsumeItemByStampTaskRequest withTask(String task) {
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
	public ConsumeItemByStampTaskRequest withKeyName(String keyName) {
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
	public ConsumeItemByStampTaskRequest withTransactionId(String transactionId) {
		setTransactionId(transactionId);
		return this;
	}

}
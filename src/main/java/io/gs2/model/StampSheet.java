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

package io.gs2.model;

import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * スタンプシート
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class StampSheet implements Serializable {

	/** スタンプシートデータ */
	private String sheet;

	/**   */
	private List<StampTask> tasks;

	/** スタンプシート関連の処理の実行で使用するトランザクションID */
	private String transactionId;


	/**
	 * スタンプシートデータを取得
	 *
	 * @return スタンプシートデータ
	 */
	public String getSheet() {
		return sheet;
	}

	/**
	 * スタンプシートデータを設定
	 *
	 * @param sheet スタンプシートデータ
	 */
	public void setSheet(String sheet) {
		this.sheet = sheet;
	}

	/**
	 * スタンプタスク一覧 を取得
	 *
	 * @return   スタンプタスク一覧
	 */
	public List<StampTask> getTasks() {
		return tasks;
	}

	/**
	 * スタンプタスク一覧 を設定
	 *
	 * @param tasks  スタンプタスク一覧
	 */
	public void setTasks(List<StampTask> tasks) {
		this.tasks = tasks;
	}

	/**
	 * スタンプシート関連の処理の実行で使用するトランザクションIDを取得
	 *
	 * @return スタンプシート関連の処理の実行で使用するトランザクションID
	 */
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * スタンプシート関連の処理の実行で使用するトランザクションIDを設定
	 *
	 * @param transactionId スタンプシート関連の処理の実行で使用するトランザクションID
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

}
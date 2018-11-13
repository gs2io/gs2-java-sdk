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
 * スタンプシートタスク
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class StampTask implements Serializable {

	/** アクション名 */
	private String action;

	/** スタンプシートタスクデータ */
	private String task;


	/**
	 * アクション名を取得
	 *
	 * @return アクション名
	 */
	public String getAction() {
		return action;
	}

	/**
	 * アクション名を設定
	 *
	 * @param action アクション名
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * スタンプシートタスクデータを取得
	 *
	 * @return スタンプシートタスクデータ
	 */
	public String getTask() {
		return task;
	}

	/**
	 * スタンプシートタスクデータを設定
	 *
	 * @param task スタンプシートタスクデータ
	 */
	public void setTask(String task) {
		this.task = task;
	}

}
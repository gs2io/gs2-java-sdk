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

package io.gs2.distributor.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.model.*;
import io.gs2.distributor.model.*;

/**
 * スタンプシートのタスクを実行する のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class RunStampTaskResult implements IResult, Serializable {
	/** タスクの実行結果を反映したコンテキストスタック */
	private String contextStack;
	/** レスポンス内容 */
	private String result;

	/**
	 * タスクの実行結果を反映したコンテキストスタックを取得
	 *
	 * @return スタンプシートのタスクを実行する
	 */
	public String getContextStack() {
		return contextStack;
	}

	/**
	 * タスクの実行結果を反映したコンテキストスタックを設定
	 *
	 * @param contextStack スタンプシートのタスクを実行する
	 */
	public void setContextStack(String contextStack) {
		this.contextStack = contextStack;
	}

	/**
	 * レスポンス内容を取得
	 *
	 * @return スタンプシートのタスクを実行する
	 */
	public String getResult() {
		return result;
	}

	/**
	 * レスポンス内容を設定
	 *
	 * @param result スタンプシートのタスクを実行する
	 */
	public void setResult(String result) {
		this.result = result;
	}
}
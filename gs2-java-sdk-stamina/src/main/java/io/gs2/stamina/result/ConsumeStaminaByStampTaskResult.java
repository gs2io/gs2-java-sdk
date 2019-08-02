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

package io.gs2.stamina.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.model.*;
import io.gs2.stamina.model.*;

/**
 * スタンプタスクを使用してスタミナを消費 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class ConsumeStaminaByStampTaskResult implements IResult, Serializable {
	/** スタミナ */
	private Stamina item;
	/** スタンプタスクの実行結果を記録したコンテキスト */
	private String newContextStack;

	/**
	 * スタミナを取得
	 *
	 * @return スタンプタスクを使用してスタミナを消費
	 */
	public Stamina getItem() {
		return item;
	}

	/**
	 * スタミナを設定
	 *
	 * @param item スタンプタスクを使用してスタミナを消費
	 */
	public void setItem(Stamina item) {
		this.item = item;
	}

	/**
	 * スタンプタスクの実行結果を記録したコンテキストを取得
	 *
	 * @return スタンプタスクを使用してスタミナを消費
	 */
	public String getNewContextStack() {
		return newContextStack;
	}

	/**
	 * スタンプタスクの実行結果を記録したコンテキストを設定
	 *
	 * @param newContextStack スタンプタスクを使用してスタミナを消費
	 */
	public void setNewContextStack(String newContextStack) {
		this.newContextStack = newContextStack;
	}
}
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

package io.gs2.enhance.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.core.model.*;
import io.gs2.enhance.model.*;

/**
 * スタンプタスクで 強化実行 を削除 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class DeleteProgressByStampTaskResult implements IResult, Serializable {
	/** 強化実行 */
	private Progress item;
	/** スタンプタスクの実行結果を記録したコンテキスト */
	private String newContextStack;

	/**
	 * 強化実行を取得
	 *
	 * @return スタンプタスクで 強化実行 を削除
	 */
	public Progress getItem() {
		return item;
	}

	/**
	 * 強化実行を設定
	 *
	 * @param item スタンプタスクで 強化実行 を削除
	 */
	public void setItem(Progress item) {
		this.item = item;
	}

	/**
	 * スタンプタスクの実行結果を記録したコンテキストを取得
	 *
	 * @return スタンプタスクで 強化実行 を削除
	 */
	public String getNewContextStack() {
		return newContextStack;
	}

	/**
	 * スタンプタスクの実行結果を記録したコンテキストを設定
	 *
	 * @param newContextStack スタンプタスクで 強化実行 を削除
	 */
	public void setNewContextStack(String newContextStack) {
		this.newContextStack = newContextStack;
	}
}
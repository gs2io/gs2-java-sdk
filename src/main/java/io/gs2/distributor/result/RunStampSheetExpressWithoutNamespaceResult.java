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
import io.gs2.core.model.*;
import io.gs2.distributor.model.*;

/**
 * スタンプタスクおよびスタンプシートを実行する のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class RunStampSheetExpressWithoutNamespaceResult implements IResult, Serializable {
	/** スタンプタスクの実行結果 */
	private List<String> taskResults;
	/** スタンプシートの実行結果レスポンス内容 */
	private String sheetResult;

	/**
	 * スタンプタスクの実行結果を取得
	 *
	 * @return スタンプタスクおよびスタンプシートを実行する
	 */
	public List<String> getTaskResults() {
		return taskResults;
	}

	/**
	 * スタンプタスクの実行結果を設定
	 *
	 * @param taskResults スタンプタスクおよびスタンプシートを実行する
	 */
	public void setTaskResults(List<String> taskResults) {
		this.taskResults = taskResults;
	}

	/**
	 * スタンプシートの実行結果レスポンス内容を取得
	 *
	 * @return スタンプタスクおよびスタンプシートを実行する
	 */
	public String getSheetResult() {
		return sheetResult;
	}

	/**
	 * スタンプシートの実行結果レスポンス内容を設定
	 *
	 * @param sheetResult スタンプタスクおよびスタンプシートを実行する
	 */
	public void setSheetResult(String sheetResult) {
		this.sheetResult = sheetResult;
	}
}
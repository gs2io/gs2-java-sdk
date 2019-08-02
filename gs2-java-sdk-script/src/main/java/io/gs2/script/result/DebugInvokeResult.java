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

package io.gs2.script.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.model.*;
import io.gs2.script.model.*;

/**
 * スクリプトを実行します のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class DebugInvokeResult implements IResult, Serializable {
	/** ステータスコード */
	private Integer code;
	/** 戻り値 */
	private String result;
	/** スクリプトの実行時間(ミリ秒) */
	private Integer executeTime;
	/** 費用の計算対象となった時間(秒) */
	private Integer charged;
	/** 標準出力の内容のリスト */
	private List<String> output;

	/**
	 * ステータスコードを取得
	 *
	 * @return スクリプトを実行します
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * ステータスコードを設定
	 *
	 * @param code スクリプトを実行します
	 */
	public void setCode(Integer code) {
		this.code = code;
	}

	/**
	 * 戻り値を取得
	 *
	 * @return スクリプトを実行します
	 */
	public String getResult() {
		return result;
	}

	/**
	 * 戻り値を設定
	 *
	 * @param result スクリプトを実行します
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * スクリプトの実行時間(ミリ秒)を取得
	 *
	 * @return スクリプトを実行します
	 */
	public Integer getExecuteTime() {
		return executeTime;
	}

	/**
	 * スクリプトの実行時間(ミリ秒)を設定
	 *
	 * @param executeTime スクリプトを実行します
	 */
	public void setExecuteTime(Integer executeTime) {
		this.executeTime = executeTime;
	}

	/**
	 * 費用の計算対象となった時間(秒)を取得
	 *
	 * @return スクリプトを実行します
	 */
	public Integer getCharged() {
		return charged;
	}

	/**
	 * 費用の計算対象となった時間(秒)を設定
	 *
	 * @param charged スクリプトを実行します
	 */
	public void setCharged(Integer charged) {
		this.charged = charged;
	}

	/**
	 * 標準出力の内容のリストを取得
	 *
	 * @return スクリプトを実行します
	 */
	public List<String> getOutput() {
		return output;
	}

	/**
	 * 標準出力の内容のリストを設定
	 *
	 * @param output スクリプトを実行します
	 */
	public void setOutput(List<String> output) {
		this.output = output;
	}
}
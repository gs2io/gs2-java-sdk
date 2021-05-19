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

package io.gs2.version.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.core.model.*;
import io.gs2.version.model.*;

/**
 * スタンプシートのタスクを実行する のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CalculateSignatureResult implements IResult, Serializable {
	/** ボディ */
	private String body;
	/** 署名 */
	private String signature;

	/**
	 * ボディを取得
	 *
	 * @return スタンプシートのタスクを実行する
	 */
	public String getBody() {
		return body;
	}

	/**
	 * ボディを設定
	 *
	 * @param body スタンプシートのタスクを実行する
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * 署名を取得
	 *
	 * @return スタンプシートのタスクを実行する
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * 署名を設定
	 *
	 * @param signature スタンプシートのタスクを実行する
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}
}
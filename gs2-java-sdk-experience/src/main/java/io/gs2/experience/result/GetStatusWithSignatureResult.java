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

package io.gs2.experience.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.model.*;
import io.gs2.experience.model.*;

/**
 * ステータスを取得 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class GetStatusWithSignatureResult implements IResult, Serializable {
	/** ステータス */
	private Status item;
	/** 検証対象のオブジェクト */
	private String body;
	/** 署名 */
	private String signature;

	/**
	 * ステータスを取得
	 *
	 * @return ステータスを取得
	 */
	public Status getItem() {
		return item;
	}

	/**
	 * ステータスを設定
	 *
	 * @param item ステータスを取得
	 */
	public void setItem(Status item) {
		this.item = item;
	}

	/**
	 * 検証対象のオブジェクトを取得
	 *
	 * @return ステータスを取得
	 */
	public String getBody() {
		return body;
	}

	/**
	 * 検証対象のオブジェクトを設定
	 *
	 * @param body ステータスを取得
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * 署名を取得
	 *
	 * @return ステータスを取得
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * 署名を設定
	 *
	 * @param signature ステータスを取得
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}
}
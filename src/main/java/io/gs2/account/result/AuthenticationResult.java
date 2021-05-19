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

package io.gs2.account.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.core.model.*;
import io.gs2.account.model.*;

/**
 * ゲームプレイヤーアカウントを認証 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class AuthenticationResult implements IResult, Serializable {
	/** ゲームプレイヤーアカウント */
	private Account item;
	/** 署名対象のアカウント情報 */
	private String body;
	/** 署名 */
	private String signature;

	/**
	 * ゲームプレイヤーアカウントを取得
	 *
	 * @return ゲームプレイヤーアカウントを認証
	 */
	public Account getItem() {
		return item;
	}

	/**
	 * ゲームプレイヤーアカウントを設定
	 *
	 * @param item ゲームプレイヤーアカウントを認証
	 */
	public void setItem(Account item) {
		this.item = item;
	}

	/**
	 * 署名対象のアカウント情報を取得
	 *
	 * @return ゲームプレイヤーアカウントを認証
	 */
	public String getBody() {
		return body;
	}

	/**
	 * 署名対象のアカウント情報を設定
	 *
	 * @param body ゲームプレイヤーアカウントを認証
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * 署名を取得
	 *
	 * @return ゲームプレイヤーアカウントを認証
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * 署名を設定
	 *
	 * @param signature ゲームプレイヤーアカウントを認証
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}
}
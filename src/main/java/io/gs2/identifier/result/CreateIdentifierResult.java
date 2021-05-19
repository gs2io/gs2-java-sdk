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

package io.gs2.identifier.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.core.model.*;
import io.gs2.identifier.model.*;

/**
 * クレデンシャルを新規作成します のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreateIdentifierResult implements IResult, Serializable {
	/** 作成したクレデンシャル */
	private Identifier item;
	/** クライアントシークレット */
	private String clientSecret;

	/**
	 * 作成したクレデンシャルを取得
	 *
	 * @return クレデンシャルを新規作成します
	 */
	public Identifier getItem() {
		return item;
	}

	/**
	 * 作成したクレデンシャルを設定
	 *
	 * @param item クレデンシャルを新規作成します
	 */
	public void setItem(Identifier item) {
		this.item = item;
	}

	/**
	 * クライアントシークレットを取得
	 *
	 * @return クレデンシャルを新規作成します
	 */
	public String getClientSecret() {
		return clientSecret;
	}

	/**
	 * クライアントシークレットを設定
	 *
	 * @param clientSecret クレデンシャルを新規作成します
	 */
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
}
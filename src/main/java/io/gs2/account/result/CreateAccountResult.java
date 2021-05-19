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
 * ゲームプレイヤーアカウントを新規作成 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreateAccountResult implements IResult, Serializable {
	/** 作成したゲームプレイヤーアカウント */
	private Account item;

	/**
	 * 作成したゲームプレイヤーアカウントを取得
	 *
	 * @return ゲームプレイヤーアカウントを新規作成
	 */
	public Account getItem() {
		return item;
	}

	/**
	 * 作成したゲームプレイヤーアカウントを設定
	 *
	 * @param item ゲームプレイヤーアカウントを新規作成
	 */
	public void setItem(Account item) {
		this.item = item;
	}
}
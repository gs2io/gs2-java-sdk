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

package io.gs2.quest.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.core.model.*;
import io.gs2.quest.model.*;

/**
 * ユーザIDを指定してクエスト挑戦を削除 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class DeleteProgressByUserIdResult implements IResult, Serializable {
	/** クエスト挑戦 */
	private Progress item;

	/**
	 * クエスト挑戦を取得
	 *
	 * @return ユーザIDを指定してクエスト挑戦を削除
	 */
	public Progress getItem() {
		return item;
	}

	/**
	 * クエスト挑戦を設定
	 *
	 * @param item ユーザIDを指定してクエスト挑戦を削除
	 */
	public void setItem(Progress item) {
		this.item = item;
	}
}
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
 * ユーザーIDを指定して引き継ぎ設定の一覧を取得 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class DescribeTakeOversByUserIdResult implements IResult, Serializable {
	/** 引き継ぎ設定のリスト */
	private List<TakeOver> items;
	/** リストの続きを取得するためのページトークン */
	private String nextPageToken;

	/**
	 * 引き継ぎ設定のリストを取得
	 *
	 * @return ユーザーIDを指定して引き継ぎ設定の一覧を取得
	 */
	public List<TakeOver> getItems() {
		return items;
	}

	/**
	 * 引き継ぎ設定のリストを設定
	 *
	 * @param items ユーザーIDを指定して引き継ぎ設定の一覧を取得
	 */
	public void setItems(List<TakeOver> items) {
		this.items = items;
	}

	/**
	 * リストの続きを取得するためのページトークンを取得
	 *
	 * @return ユーザーIDを指定して引き継ぎ設定の一覧を取得
	 */
	public String getNextPageToken() {
		return nextPageToken;
	}

	/**
	 * リストの続きを取得するためのページトークンを設定
	 *
	 * @param nextPageToken ユーザーIDを指定して引き継ぎ設定の一覧を取得
	 */
	public void setNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
	}
}
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

package io.gs2.money.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.model.*;
import io.gs2.money.model.*;

/**
 * ウォレットの詳細を取得します のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class DescribeWalletDetailsByUserIdResult implements IResult, Serializable {
	/** ウォレットの詳細のリスト */
	private List<WalletDetail> items;
	/** リストの続きを取得するためのページトークン */
	private String nextPageToken;

	/**
	 * ウォレットの詳細のリストを取得
	 *
	 * @return ウォレットの詳細を取得します
	 */
	public List<WalletDetail> getItems() {
		return items;
	}

	/**
	 * ウォレットの詳細のリストを設定
	 *
	 * @param items ウォレットの詳細を取得します
	 */
	public void setItems(List<WalletDetail> items) {
		this.items = items;
	}

	/**
	 * リストの続きを取得するためのページトークンを取得
	 *
	 * @return ウォレットの詳細を取得します
	 */
	public String getNextPageToken() {
		return nextPageToken;
	}

	/**
	 * リストの続きを取得するためのページトークンを設定
	 *
	 * @param nextPageToken ウォレットの詳細を取得します
	 */
	public void setNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
	}
}
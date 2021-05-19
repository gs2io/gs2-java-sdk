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

package io.gs2.exchange.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.core.model.*;
import io.gs2.exchange.model.*;

/**
 * 交換待機の一覧を取得 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class DescribeAwaitsResult implements IResult, Serializable {
	/** 交換待機のリスト */
	private List<Await> items;
	/** 次のページを取得するためのトークン */
	private String nextPageToken;

	/**
	 * 交換待機のリストを取得
	 *
	 * @return 交換待機の一覧を取得
	 */
	public List<Await> getItems() {
		return items;
	}

	/**
	 * 交換待機のリストを設定
	 *
	 * @param items 交換待機の一覧を取得
	 */
	public void setItems(List<Await> items) {
		this.items = items;
	}

	/**
	 * 次のページを取得するためのトークンを取得
	 *
	 * @return 交換待機の一覧を取得
	 */
	public String getNextPageToken() {
		return nextPageToken;
	}

	/**
	 * 次のページを取得するためのトークンを設定
	 *
	 * @param nextPageToken 交換待機の一覧を取得
	 */
	public void setNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
	}
}
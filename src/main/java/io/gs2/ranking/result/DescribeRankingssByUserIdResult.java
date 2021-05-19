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

package io.gs2.ranking.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.core.model.*;
import io.gs2.ranking.model.*;

/**
 * ユーザIDを指定してランキングを取得 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class DescribeRankingssByUserIdResult implements IResult, Serializable {
	/** ランキングのリスト */
	private List<Ranking> items;
	/** リストの続きを取得するためのページトークン */
	private String nextPageToken;

	/**
	 * ランキングのリストを取得
	 *
	 * @return ユーザIDを指定してランキングを取得
	 */
	public List<Ranking> getItems() {
		return items;
	}

	/**
	 * ランキングのリストを設定
	 *
	 * @param items ユーザIDを指定してランキングを取得
	 */
	public void setItems(List<Ranking> items) {
		this.items = items;
	}

	/**
	 * リストの続きを取得するためのページトークンを取得
	 *
	 * @return ユーザIDを指定してランキングを取得
	 */
	public String getNextPageToken() {
		return nextPageToken;
	}

	/**
	 * リストの続きを取得するためのページトークンを設定
	 *
	 * @param nextPageToken ユーザIDを指定してランキングを取得
	 */
	public void setNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
	}
}
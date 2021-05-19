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

package io.gs2.log.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.core.model.*;
import io.gs2.log.model.*;

/**
 * スタンプシート発行ログの一覧を取得 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class QueryIssueStampSheetLogResult implements IResult, Serializable {
	/** スタンプシート発行ログのリスト */
	private List<IssueStampSheetLog> items;
	/** リストの続きを取得するためのページトークン */
	private String nextPageToken;
	/** クエリ結果の総件数 */
	private Long totalCount;
	/** 検索時にスキャンした総容量 */
	private Long scanSize;

	/**
	 * スタンプシート発行ログのリストを取得
	 *
	 * @return スタンプシート発行ログの一覧を取得
	 */
	public List<IssueStampSheetLog> getItems() {
		return items;
	}

	/**
	 * スタンプシート発行ログのリストを設定
	 *
	 * @param items スタンプシート発行ログの一覧を取得
	 */
	public void setItems(List<IssueStampSheetLog> items) {
		this.items = items;
	}

	/**
	 * リストの続きを取得するためのページトークンを取得
	 *
	 * @return スタンプシート発行ログの一覧を取得
	 */
	public String getNextPageToken() {
		return nextPageToken;
	}

	/**
	 * リストの続きを取得するためのページトークンを設定
	 *
	 * @param nextPageToken スタンプシート発行ログの一覧を取得
	 */
	public void setNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
	}

	/**
	 * クエリ結果の総件数を取得
	 *
	 * @return スタンプシート発行ログの一覧を取得
	 */
	public Long getTotalCount() {
		return totalCount;
	}

	/**
	 * クエリ結果の総件数を設定
	 *
	 * @param totalCount スタンプシート発行ログの一覧を取得
	 */
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 検索時にスキャンした総容量を取得
	 *
	 * @return スタンプシート発行ログの一覧を取得
	 */
	public Long getScanSize() {
		return scanSize;
	}

	/**
	 * 検索時にスキャンした総容量を設定
	 *
	 * @param scanSize スタンプシート発行ログの一覧を取得
	 */
	public void setScanSize(Long scanSize) {
		this.scanSize = scanSize;
	}
}
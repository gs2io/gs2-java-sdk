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

package io.gs2.version.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.core.model.*;
import io.gs2.version.model.*;

/**
 * 承認したバージョンの一覧を取得 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class DescribeAcceptVersionsByUserIdResult implements IResult, Serializable {
	/** 承認したバージョンのリスト */
	private List<AcceptVersion> items;
	/** リストの続きを取得するためのページトークン */
	private String nextPageToken;

	/**
	 * 承認したバージョンのリストを取得
	 *
	 * @return 承認したバージョンの一覧を取得
	 */
	public List<AcceptVersion> getItems() {
		return items;
	}

	/**
	 * 承認したバージョンのリストを設定
	 *
	 * @param items 承認したバージョンの一覧を取得
	 */
	public void setItems(List<AcceptVersion> items) {
		this.items = items;
	}

	/**
	 * リストの続きを取得するためのページトークンを取得
	 *
	 * @return 承認したバージョンの一覧を取得
	 */
	public String getNextPageToken() {
		return nextPageToken;
	}

	/**
	 * リストの続きを取得するためのページトークンを設定
	 *
	 * @param nextPageToken 承認したバージョンの一覧を取得
	 */
	public void setNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
	}
}
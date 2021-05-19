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

package io.gs2.key.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.core.model.*;
import io.gs2.key.model.*;

/**
 * GitHub のAPIキーの一覧を取得します のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class DescribeGitHubApiKeysResult implements IResult, Serializable {
	/** GitHub のAPIキーのリスト */
	private List<GitHubApiKey> items;
	/** リストの続きを取得するためのページトークン */
	private String nextPageToken;

	/**
	 * GitHub のAPIキーのリストを取得
	 *
	 * @return GitHub のAPIキーの一覧を取得します
	 */
	public List<GitHubApiKey> getItems() {
		return items;
	}

	/**
	 * GitHub のAPIキーのリストを設定
	 *
	 * @param items GitHub のAPIキーの一覧を取得します
	 */
	public void setItems(List<GitHubApiKey> items) {
		this.items = items;
	}

	/**
	 * リストの続きを取得するためのページトークンを取得
	 *
	 * @return GitHub のAPIキーの一覧を取得します
	 */
	public String getNextPageToken() {
		return nextPageToken;
	}

	/**
	 * リストの続きを取得するためのページトークンを設定
	 *
	 * @param nextPageToken GitHub のAPIキーの一覧を取得します
	 */
	public void setNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
	}
}
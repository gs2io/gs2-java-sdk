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

package io.gs2.script.control;

import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.script.model.*;

/**
 * @author Game Server Services, Inc.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class DescribeScriptResult {

	/** スクリプト */
	private List<Script> items;

	/** 次のページを読み込むためのトークン */
	private String nextPageToken;


	/**
	 * スクリプトを取得
	 *
	 * @return スクリプト
	 */
	public List<Script> getItems() {
		return items;
	}

	/**
	 * スクリプトを設定
	 *
	 * @param items スクリプト
	 */
	public void setItems(List<Script> items) {
		this.items = items;
	}

	/**
	 * 次のページを読み込むためのトークンを取得
	 *
	 * @return 次のページを読み込むためのトークン
	 */
	public String getNextPageToken() {
		return nextPageToken;
	}

	/**
	 * 次のページを読み込むためのトークンを設定
	 *
	 * @param nextPageToken 次のページを読み込むためのトークン
	 */
	public void setNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
	}

}
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

package io.gs2.limit.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.model.*;
import io.gs2.limit.model.*;

/**
 * 回数制限の種類マスターの一覧を取得 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class DescribeLimitModelMastersResult implements IResult, Serializable {
	/** 回数制限の種類マスターのリスト */
	private List<LimitModelMaster> items;
	/** リストの続きを取得するためのページトークン */
	private String nextPageToken;

	/**
	 * 回数制限の種類マスターのリストを取得
	 *
	 * @return 回数制限の種類マスターの一覧を取得
	 */
	public List<LimitModelMaster> getItems() {
		return items;
	}

	/**
	 * 回数制限の種類マスターのリストを設定
	 *
	 * @param items 回数制限の種類マスターの一覧を取得
	 */
	public void setItems(List<LimitModelMaster> items) {
		this.items = items;
	}

	/**
	 * リストの続きを取得するためのページトークンを取得
	 *
	 * @return 回数制限の種類マスターの一覧を取得
	 */
	public String getNextPageToken() {
		return nextPageToken;
	}

	/**
	 * リストの続きを取得するためのページトークンを設定
	 *
	 * @param nextPageToken 回数制限の種類マスターの一覧を取得
	 */
	public void setNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
	}
}
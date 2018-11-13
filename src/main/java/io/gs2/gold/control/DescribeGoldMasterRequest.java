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

package io.gs2.gold.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.gold.model.*;
import io.gs2.gold.Gs2Gold;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DescribeGoldMasterRequest extends Gs2BasicRequest<DescribeGoldMasterRequest> {

	public static class Constant extends Gs2Gold.Constant {
		public static final String FUNCTION = "DescribeGoldMaster";
	}

	/** ゴールドプールの名前 */
	private String goldPoolName;

	/** データの取得を開始する位置を指定するトークン */
	private String pageToken;

	/** データの取得件数 */
	private Integer limit;


	/**
	 * ゴールドプールの名前を取得
	 *
	 * @return ゴールドプールの名前
	 */
	public String getGoldPoolName() {
		return goldPoolName;
	}

	/**
	 * ゴールドプールの名前を設定
	 *
	 * @param goldPoolName ゴールドプールの名前
	 */
	public void setGoldPoolName(String goldPoolName) {
		this.goldPoolName = goldPoolName;
	}

	/**
	 * ゴールドプールの名前を設定
	 *
	 * @param goldPoolName ゴールドプールの名前
	 * @return this
	 */
	public DescribeGoldMasterRequest withGoldPoolName(String goldPoolName) {
		setGoldPoolName(goldPoolName);
		return this;
	}

	/**
	 * データの取得を開始する位置を指定するトークンを取得
	 *
	 * @return データの取得を開始する位置を指定するトークン
	 */
	public String getPageToken() {
		return pageToken;
	}

	/**
	 * データの取得を開始する位置を指定するトークンを設定
	 *
	 * @param pageToken データの取得を開始する位置を指定するトークン
	 */
	public void setPageToken(String pageToken) {
		this.pageToken = pageToken;
	}

	/**
	 * データの取得を開始する位置を指定するトークンを設定
	 *
	 * @param pageToken データの取得を開始する位置を指定するトークン
	 * @return this
	 */
	public DescribeGoldMasterRequest withPageToken(String pageToken) {
		setPageToken(pageToken);
		return this;
	}

	/**
	 * データの取得件数を取得
	 *
	 * @return データの取得件数
	 */
	public Integer getLimit() {
		return limit;
	}

	/**
	 * データの取得件数を設定
	 *
	 * @param limit データの取得件数
	 */
	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	/**
	 * データの取得件数を設定
	 *
	 * @param limit データの取得件数
	 * @return this
	 */
	public DescribeGoldMasterRequest withLimit(Integer limit) {
		setLimit(limit);
		return this;
	}

}
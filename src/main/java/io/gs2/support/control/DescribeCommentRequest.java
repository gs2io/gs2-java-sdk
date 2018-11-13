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

package io.gs2.support.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.support.Gs2Support;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DescribeCommentRequest extends Gs2BasicRequest<DescribeCommentRequest> {

	public static class Constant extends Gs2Support.Constant {
		public static final String FUNCTION = "DescribeComment";
	}

	/** 問い合わせのGRNを指定します。 */
	private String issueId;

	/** データの取得を開始する位置を指定するトークン */
	private String pageToken;

	/** データの取得件数 */
	private Integer limit;


	/**
	 * 問い合わせのGRNを指定します。を取得
	 *
	 * @return 問い合わせのGRNを指定します。
	 */
	public String getIssueId() {
		return issueId;
	}

	/**
	 * 問い合わせのGRNを指定します。を設定
	 *
	 * @param issueId 問い合わせのGRNを指定します。
	 */
	public void setIssueId(String issueId) {
		this.issueId = issueId;
	}

	/**
	 * 問い合わせのGRNを指定します。を設定
	 *
	 * @param issueId 問い合わせのGRNを指定します。
	 * @return this
	 */
	public DescribeCommentRequest withIssueId(String issueId) {
		setIssueId(issueId);
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
	public DescribeCommentRequest withPageToken(String pageToken) {
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
	public DescribeCommentRequest withLimit(Integer limit) {
		setLimit(limit);
		return this;
	}

}
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
public class GetCommentRequest extends Gs2BasicRequest<GetCommentRequest> {

	public static class Constant extends Gs2Support.Constant {
		public static final String FUNCTION = "GetComment";
	}

	/** 問い合わせのGRNを指定します。 */
	private String issueId;

	/** コメントのGRNを指定します。 */
	private String commentId;


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
	public GetCommentRequest withIssueId(String issueId) {
		setIssueId(issueId);
		return this;
	}

	/**
	 * コメントのGRNを指定します。を取得
	 *
	 * @return コメントのGRNを指定します。
	 */
	public String getCommentId() {
		return commentId;
	}

	/**
	 * コメントのGRNを指定します。を設定
	 *
	 * @param commentId コメントのGRNを指定します。
	 */
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	/**
	 * コメントのGRNを指定します。を設定
	 *
	 * @param commentId コメントのGRNを指定します。
	 * @return this
	 */
	public GetCommentRequest withCommentId(String commentId) {
		setCommentId(commentId);
		return this;
	}

}
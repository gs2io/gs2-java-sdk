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
public class CreateCommentRequest extends Gs2BasicRequest<CreateCommentRequest> {

	public static class Constant extends Gs2Support.Constant {
		public static final String FUNCTION = "CreateComment";
	}

	/** ケースのGRNを指定します。 */
	private String issueId;

	/** コメントの本文 */
	private String body;

	/** コメントしたユーザのメールアドレス */
	private String email;


	/**
	 * ケースのGRNを指定します。を取得
	 *
	 * @return ケースのGRNを指定します。
	 */
	public String getIssueId() {
		return issueId;
	}

	/**
	 * ケースのGRNを指定します。を設定
	 *
	 * @param issueId ケースのGRNを指定します。
	 */
	public void setIssueId(String issueId) {
		this.issueId = issueId;
	}

	/**
	 * ケースのGRNを指定します。を設定
	 *
	 * @param issueId ケースのGRNを指定します。
	 * @return this
	 */
	public CreateCommentRequest withIssueId(String issueId) {
		setIssueId(issueId);
		return this;
	}

	/**
	 * コメントの本文を取得
	 *
	 * @return コメントの本文
	 */
	public String getBody() {
		return body;
	}

	/**
	 * コメントの本文を設定
	 *
	 * @param body コメントの本文
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * コメントの本文を設定
	 *
	 * @param body コメントの本文
	 * @return this
	 */
	public CreateCommentRequest withBody(String body) {
		setBody(body);
		return this;
	}

	/**
	 * コメントしたユーザのメールアドレスを取得
	 *
	 * @return コメントしたユーザのメールアドレス
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * コメントしたユーザのメールアドレスを設定
	 *
	 * @param email コメントしたユーザのメールアドレス
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * コメントしたユーザのメールアドレスを設定
	 *
	 * @param email コメントしたユーザのメールアドレス
	 * @return this
	 */
	public CreateCommentRequest withEmail(String email) {
		setEmail(email);
		return this;
	}

}
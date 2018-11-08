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
public class CreateIssueRequest extends Gs2BasicRequest<CreateIssueRequest> {

	public static class Constant extends Gs2Support.Constant {
		public static final String FUNCTION = "CreateIssue";
	}

	/** カテゴリ */
	private String category;

	/** ケースの本文 */
	private String body;

	/** ケースの概略 */
	private String description;

	/** ケースの返信先 */
	private String email;

	/** サブカテゴリ */
	private String subCategory;


	/**
	 * カテゴリを取得
	 *
	 * @return カテゴリ
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * カテゴリを設定
	 *
	 * @param category カテゴリ
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * カテゴリを設定
	 *
	 * @param category カテゴリ
	 * @return this
	 */
	public CreateIssueRequest withCategory(String category) {
		setCategory(category);
		return this;
	}

	/**
	 * ケースの本文を取得
	 *
	 * @return ケースの本文
	 */
	public String getBody() {
		return body;
	}

	/**
	 * ケースの本文を設定
	 *
	 * @param body ケースの本文
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * ケースの本文を設定
	 *
	 * @param body ケースの本文
	 * @return this
	 */
	public CreateIssueRequest withBody(String body) {
		setBody(body);
		return this;
	}

	/**
	 * ケースの概略を取得
	 *
	 * @return ケースの概略
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * ケースの概略を設定
	 *
	 * @param description ケースの概略
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * ケースの概略を設定
	 *
	 * @param description ケースの概略
	 * @return this
	 */
	public CreateIssueRequest withDescription(String description) {
		setDescription(description);
		return this;
	}

	/**
	 * ケースの返信先を取得
	 *
	 * @return ケースの返信先
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * ケースの返信先を設定
	 *
	 * @param email ケースの返信先
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * ケースの返信先を設定
	 *
	 * @param email ケースの返信先
	 * @return this
	 */
	public CreateIssueRequest withEmail(String email) {
		setEmail(email);
		return this;
	}

	/**
	 * サブカテゴリを取得
	 *
	 * @return サブカテゴリ
	 */
	public String getSubCategory() {
		return subCategory;
	}

	/**
	 * サブカテゴリを設定
	 *
	 * @param subCategory サブカテゴリ
	 */
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	/**
	 * サブカテゴリを設定
	 *
	 * @param subCategory サブカテゴリ
	 * @return this
	 */
	public CreateIssueRequest withSubCategory(String subCategory) {
		setSubCategory(subCategory);
		return this;
	}

}
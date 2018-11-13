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
public class DescribeIssueSubCategoryRequest extends Gs2BasicRequest<DescribeIssueSubCategoryRequest> {

	public static class Constant extends Gs2Support.Constant {
		public static final String FUNCTION = "DescribeIssueSubCategory";
	}

	/** カテゴリを指定します。 */
	private String category;


	/**
	 * カテゴリを指定します。を取得
	 *
	 * @return カテゴリを指定します。
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * カテゴリを指定します。を設定
	 *
	 * @param category カテゴリを指定します。
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * カテゴリを指定します。を設定
	 *
	 * @param category カテゴリを指定します。
	 * @return this
	 */
	public DescribeIssueSubCategoryRequest withCategory(String category) {
		setCategory(category);
		return this;
	}

}
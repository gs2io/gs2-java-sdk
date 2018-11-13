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

package io.gs2.showcase.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.showcase.model.*;
import io.gs2.showcase.Gs2Showcase;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DeleteShowcaseItemMasterRequest extends Gs2BasicRequest<DeleteShowcaseItemMasterRequest> {

	public static class Constant extends Gs2Showcase.Constant {
		public static final String FUNCTION = "DeleteShowcaseItemMaster";
	}

	/** ショーケースの名前 */
	private String showcaseName;

	/** 商品の種類 */
	private String category;

	/** 商品/商品グループ名 */
	private String resourceId;


	/**
	 * ショーケースの名前を取得
	 *
	 * @return ショーケースの名前
	 */
	public String getShowcaseName() {
		return showcaseName;
	}

	/**
	 * ショーケースの名前を設定
	 *
	 * @param showcaseName ショーケースの名前
	 */
	public void setShowcaseName(String showcaseName) {
		this.showcaseName = showcaseName;
	}

	/**
	 * ショーケースの名前を設定
	 *
	 * @param showcaseName ショーケースの名前
	 * @return this
	 */
	public DeleteShowcaseItemMasterRequest withShowcaseName(String showcaseName) {
		setShowcaseName(showcaseName);
		return this;
	}

	/**
	 * 商品の種類を取得
	 *
	 * @return 商品の種類
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * 商品の種類を設定
	 *
	 * @param category 商品の種類
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * 商品の種類を設定
	 *
	 * @param category 商品の種類
	 * @return this
	 */
	public DeleteShowcaseItemMasterRequest withCategory(String category) {
		setCategory(category);
		return this;
	}

	/**
	 * 商品/商品グループ名を取得
	 *
	 * @return 商品/商品グループ名
	 */
	public String getResourceId() {
		return resourceId;
	}

	/**
	 * 商品/商品グループ名を設定
	 *
	 * @param resourceId 商品/商品グループ名
	 */
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	/**
	 * 商品/商品グループ名を設定
	 *
	 * @param resourceId 商品/商品グループ名
	 * @return this
	 */
	public DeleteShowcaseItemMasterRequest withResourceId(String resourceId) {
		setResourceId(resourceId);
		return this;
	}

}
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

package io.gs2.level.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.level.model.*;
import io.gs2.level.Gs2Level;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class GetStatusByUserIdAndResourceTypeRequest extends Gs2BasicRequest<GetStatusByUserIdAndResourceTypeRequest> {

	public static class Constant extends Gs2Level.Constant {
		public static final String FUNCTION = "GetStatusByUserId";
	}

	/** リソースプール */
	private String resourcePoolName;

	/** リソースタイプ */
	private String resourceTypeName;

	/** ユーザID */
	private String userId;

	/** リソースID */
	private String resourceId;


	/**
	 * リソースプールを取得
	 *
	 * @return リソースプール
	 */
	public String getResourcePoolName() {
		return resourcePoolName;
	}

	/**
	 * リソースプールを設定
	 *
	 * @param resourcePoolName リソースプール
	 */
	public void setResourcePoolName(String resourcePoolName) {
		this.resourcePoolName = resourcePoolName;
	}

	/**
	 * リソースプールを設定
	 *
	 * @param resourcePoolName リソースプール
	 * @return this
	 */
	public GetStatusByUserIdAndResourceTypeRequest withResourcePoolName(String resourcePoolName) {
		setResourcePoolName(resourcePoolName);
		return this;
	}

	/**
	 * リソースタイプを取得
	 *
	 * @return リソースタイプ
	 */
	public String getResourceTypeName() {
		return resourceTypeName;
	}

	/**
	 * リソースタイプを設定
	 *
	 * @param resourceTypeName リソースタイプ
	 */
	public void setResourceTypeName(String resourceTypeName) {
		this.resourceTypeName = resourceTypeName;
	}

	/**
	 * リソースタイプを設定
	 *
	 * @param resourceTypeName リソースタイプ
	 * @return this
	 */
	public GetStatusByUserIdAndResourceTypeRequest withResourceTypeName(String resourceTypeName) {
		setResourceTypeName(resourceTypeName);
		return this;
	}

	/**
	 * ユーザIDを取得
	 *
	 * @return ユーザID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * ユーザIDを設定
	 *
	 * @param userId ユーザID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * ユーザIDを設定
	 *
	 * @param userId ユーザID
	 * @return this
	 */
	public GetStatusByUserIdAndResourceTypeRequest withUserId(String userId) {
		setUserId(userId);
		return this;
	}

	/**
	 * リソースIDを取得
	 *
	 * @return リソースID
	 */
	public String getResourceId() {
		return resourceId;
	}

	/**
	 * リソースIDを設定
	 *
	 * @param resourceId リソースID
	 */
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	/**
	 * リソースIDを設定
	 *
	 * @param resourceId リソースID
	 * @return this
	 */
	public GetStatusByUserIdAndResourceTypeRequest withResourceId(String resourceId) {
		setResourceId(resourceId);
		return this;
	}

}
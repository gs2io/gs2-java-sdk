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
public class GetStatusByUserIdRequest extends Gs2BasicRequest<GetStatusByUserIdRequest> {

	public static class Constant extends Gs2Level.Constant {
		public static final String FUNCTION = "GetStatusByUserId";
	}

	/** リソースプール */
	private String resourcePoolName;

	/** ユーザID */
	private String userId;

	/** ステータスID */
	private String statusId;


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
	public GetStatusByUserIdRequest withResourcePoolName(String resourcePoolName) {
		setResourcePoolName(resourcePoolName);
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
	public GetStatusByUserIdRequest withUserId(String userId) {
		setUserId(userId);
		return this;
	}

	/**
	 * ステータスIDを取得
	 *
	 * @return ステータスID
	 */
	public String getStatusId() {
		return statusId;
	}

	/**
	 * ステータスIDを設定
	 *
	 * @param statusId ステータスID
	 */
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	/**
	 * ステータスIDを設定
	 *
	 * @param statusId ステータスID
	 * @return this
	 */
	public GetStatusByUserIdRequest withStatusId(String statusId) {
		setStatusId(statusId);
		return this;
	}

}
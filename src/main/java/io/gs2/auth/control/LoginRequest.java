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

package io.gs2.auth.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.auth.Gs2Auth;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class LoginRequest extends Gs2BasicRequest<LoginRequest> {

	public static class Constant extends Gs2Auth.Constant {
		public static final String FUNCTION = "Login";
	}

	/** ログインするサービスID */
	private String serviceId;

	/** ログインするユーザのID */
	private String userId;


	/**
	 * ログインするサービスIDを取得
	 *
	 * @return ログインするサービスID
	 */
	public String getServiceId() {
		return serviceId;
	}

	/**
	 * ログインするサービスIDを設定
	 *
	 * @param serviceId ログインするサービスID
	 */
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	/**
	 * ログインするサービスIDを設定
	 *
	 * @param serviceId ログインするサービスID
	 * @return this
	 */
	public LoginRequest withServiceId(String serviceId) {
		setServiceId(serviceId);
		return this;
	}

	/**
	 * ログインするユーザのIDを取得
	 *
	 * @return ログインするユーザのID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * ログインするユーザのIDを設定
	 *
	 * @param userId ログインするユーザのID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * ログインするユーザのIDを設定
	 *
	 * @param userId ログインするユーザのID
	 * @return this
	 */
	public LoginRequest withUserId(String userId) {
		setUserId(userId);
		return this;
	}

}
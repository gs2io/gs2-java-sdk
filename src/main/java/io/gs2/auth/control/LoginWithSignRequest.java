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
public class LoginWithSignRequest extends Gs2BasicRequest<LoginWithSignRequest> {

	public static class Constant extends Gs2Auth.Constant {
		public static final String FUNCTION = "LoginWithSign";
	}

	/** ログインするサービスID */
	private String serviceId;

	/** ログインするユーザのIDを指定してください */
	private String userId;

	/** GS2-Accountの認証署名の作成に使用した GS2-Key の暗号鍵名 */
	private String keyName;

	/** GS2-Accountの認証署名 */
	private String sign;


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
	public LoginWithSignRequest withServiceId(String serviceId) {
		setServiceId(serviceId);
		return this;
	}

	/**
	 * ログインするユーザのIDを指定してくださいを取得
	 *
	 * @return ログインするユーザのIDを指定してください
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * ログインするユーザのIDを指定してくださいを設定
	 *
	 * @param userId ログインするユーザのIDを指定してください
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * ログインするユーザのIDを指定してくださいを設定
	 *
	 * @param userId ログインするユーザのIDを指定してください
	 * @return this
	 */
	public LoginWithSignRequest withUserId(String userId) {
		setUserId(userId);
		return this;
	}

	/**
	 * GS2-Accountの認証署名の作成に使用した GS2-Key の暗号鍵名を取得
	 *
	 * @return GS2-Accountの認証署名の作成に使用した GS2-Key の暗号鍵名
	 */
	public String getKeyName() {
		return keyName;
	}

	/**
	 * GS2-Accountの認証署名の作成に使用した GS2-Key の暗号鍵名を設定
	 *
	 * @param keyName GS2-Accountの認証署名の作成に使用した GS2-Key の暗号鍵名
	 */
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	/**
	 * GS2-Accountの認証署名の作成に使用した GS2-Key の暗号鍵名を設定
	 *
	 * @param keyName GS2-Accountの認証署名の作成に使用した GS2-Key の暗号鍵名
	 * @return this
	 */
	public LoginWithSignRequest withKeyName(String keyName) {
		setKeyName(keyName);
		return this;
	}

	/**
	 * GS2-Accountの認証署名を取得
	 *
	 * @return GS2-Accountの認証署名
	 */
	public String getSign() {
		return sign;
	}

	/**
	 * GS2-Accountの認証署名を設定
	 *
	 * @param sign GS2-Accountの認証署名
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}

	/**
	 * GS2-Accountの認証署名を設定
	 *
	 * @param sign GS2-Accountの認証署名
	 * @return this
	 */
	public LoginWithSignRequest withSign(String sign) {
		setSign(sign);
		return this;
	}

}
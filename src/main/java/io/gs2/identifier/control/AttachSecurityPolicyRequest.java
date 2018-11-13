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

package io.gs2.identifier.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.identifier.Gs2Identifier;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class AttachSecurityPolicyRequest extends Gs2BasicRequest<AttachSecurityPolicyRequest> {

	public static class Constant extends Gs2Identifier.Constant {
		public static final String FUNCTION = "AttachSecurityPolicy";
	}

	/** ユーザの名前を指定します。 */
	private String userName;

	/** セキュリティポリシーのGRN */
	private String securityPolicyId;


	/**
	 * ユーザの名前を指定します。を取得
	 *
	 * @return ユーザの名前を指定します。
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * ユーザの名前を指定します。を設定
	 *
	 * @param userName ユーザの名前を指定します。
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * ユーザの名前を指定します。を設定
	 *
	 * @param userName ユーザの名前を指定します。
	 * @return this
	 */
	public AttachSecurityPolicyRequest withUserName(String userName) {
		setUserName(userName);
		return this;
	}

	/**
	 * セキュリティポリシーのGRNを取得
	 *
	 * @return セキュリティポリシーのGRN
	 */
	public String getSecurityPolicyId() {
		return securityPolicyId;
	}

	/**
	 * セキュリティポリシーのGRNを設定
	 *
	 * @param securityPolicyId セキュリティポリシーのGRN
	 */
	public void setSecurityPolicyId(String securityPolicyId) {
		this.securityPolicyId = securityPolicyId;
	}

	/**
	 * セキュリティポリシーのGRNを設定
	 *
	 * @param securityPolicyId セキュリティポリシーのGRN
	 * @return this
	 */
	public AttachSecurityPolicyRequest withSecurityPolicyId(String securityPolicyId) {
		setSecurityPolicyId(securityPolicyId);
		return this;
	}

}
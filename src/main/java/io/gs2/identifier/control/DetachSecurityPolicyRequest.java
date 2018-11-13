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
public class DetachSecurityPolicyRequest extends Gs2BasicRequest<DetachSecurityPolicyRequest> {

	public static class Constant extends Gs2Identifier.Constant {
		public static final String FUNCTION = "DetachSecurityPolicy";
	}

	/** ユーザの名前 */
	private String userName;

	/** セキュリティポリシーGRN */
	private String securityPolicyId;


	/**
	 * ユーザの名前を取得
	 *
	 * @return ユーザの名前
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * ユーザの名前を設定
	 *
	 * @param userName ユーザの名前
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * ユーザの名前を設定
	 *
	 * @param userName ユーザの名前
	 * @return this
	 */
	public DetachSecurityPolicyRequest withUserName(String userName) {
		setUserName(userName);
		return this;
	}

	/**
	 * セキュリティポリシーGRNを取得
	 *
	 * @return セキュリティポリシーGRN
	 */
	public String getSecurityPolicyId() {
		return securityPolicyId;
	}

	/**
	 * セキュリティポリシーGRNを設定
	 *
	 * @param securityPolicyId セキュリティポリシーGRN
	 */
	public void setSecurityPolicyId(String securityPolicyId) {
		this.securityPolicyId = securityPolicyId;
	}

	/**
	 * セキュリティポリシーGRNを設定
	 *
	 * @param securityPolicyId セキュリティポリシーGRN
	 * @return this
	 */
	public DetachSecurityPolicyRequest withSecurityPolicyId(String securityPolicyId) {
		setSecurityPolicyId(securityPolicyId);
		return this;
	}

}
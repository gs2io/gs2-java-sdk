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
public class UpdateSecurityPolicyRequest extends Gs2BasicRequest<UpdateSecurityPolicyRequest> {

	public static class Constant extends Gs2Identifier.Constant {
		public static final String FUNCTION = "UpdateSecurityPolicy";
	}

	/** セキュリティポリシーの名前を指定します。 */
	private String securityPolicyName;

	/** ポリシードキュメント */
	private String policy;


	/**
	 * セキュリティポリシーの名前を指定します。を取得
	 *
	 * @return セキュリティポリシーの名前を指定します。
	 */
	public String getSecurityPolicyName() {
		return securityPolicyName;
	}

	/**
	 * セキュリティポリシーの名前を指定します。を設定
	 *
	 * @param securityPolicyName セキュリティポリシーの名前を指定します。
	 */
	public void setSecurityPolicyName(String securityPolicyName) {
		this.securityPolicyName = securityPolicyName;
	}

	/**
	 * セキュリティポリシーの名前を指定します。を設定
	 *
	 * @param securityPolicyName セキュリティポリシーの名前を指定します。
	 * @return this
	 */
	public UpdateSecurityPolicyRequest withSecurityPolicyName(String securityPolicyName) {
		setSecurityPolicyName(securityPolicyName);
		return this;
	}

	/**
	 * ポリシードキュメントを取得
	 *
	 * @return ポリシードキュメント
	 */
	public String getPolicy() {
		return policy;
	}

	/**
	 * ポリシードキュメントを設定
	 *
	 * @param policy ポリシードキュメント
	 */
	public void setPolicy(String policy) {
		this.policy = policy;
	}

	/**
	 * ポリシードキュメントを設定
	 *
	 * @param policy ポリシードキュメント
	 * @return this
	 */
	public UpdateSecurityPolicyRequest withPolicy(String policy) {
		setPolicy(policy);
		return this;
	}

}
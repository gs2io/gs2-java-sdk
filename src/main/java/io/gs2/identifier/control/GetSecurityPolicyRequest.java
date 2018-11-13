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
public class GetSecurityPolicyRequest extends Gs2BasicRequest<GetSecurityPolicyRequest> {

	public static class Constant extends Gs2Identifier.Constant {
		public static final String FUNCTION = "GetSecurityPolicy";
	}

	/** セキュリティポリシーの名前を指定します。 */
	private String securityPolicyName;


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
	public GetSecurityPolicyRequest withSecurityPolicyName(String securityPolicyName) {
		setSecurityPolicyName(securityPolicyName);
		return this;
	}

}
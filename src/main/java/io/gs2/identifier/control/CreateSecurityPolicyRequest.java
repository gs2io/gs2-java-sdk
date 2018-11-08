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
public class CreateSecurityPolicyRequest extends Gs2BasicRequest<CreateSecurityPolicyRequest> {

	public static class Constant extends Gs2Identifier.Constant {
		public static final String FUNCTION = "CreateSecurityPolicy";
	}

	/** 名前 */
	private String name;

	/** ポリシードキュメント */
	private String policy;


	/**
	 * 名前を取得
	 *
	 * @return 名前
	 */
	public String getName() {
		return name;
	}

	/**
	 * 名前を設定
	 *
	 * @param name 名前
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 名前を設定
	 *
	 * @param name 名前
	 * @return this
	 */
	public CreateSecurityPolicyRequest withName(String name) {
		setName(name);
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
	public CreateSecurityPolicyRequest withPolicy(String policy) {
		setPolicy(policy);
		return this;
	}

}
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

package io.gs2.identifier.model;

import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * セキュリティポリシー
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class SecurityPolicy implements Serializable {

	/** セキュリティポリシーID */
	private String securityPolicyId;

	/** オーナーID */
	private String ownerId;

	/** セキュリティポリシー名 */
	private String name;

	/** ポリシードキュメント */
	private String policy;

	/** 作成日時(エポック秒) */
	private Integer createAt;

	/** 最終更新日時(エポック秒) */
	private Integer updateAt;


	/**
	 * セキュリティポリシーIDを取得
	 *
	 * @return セキュリティポリシーID
	 */
	public String getSecurityPolicyId() {
		return securityPolicyId;
	}

	/**
	 * セキュリティポリシーIDを設定
	 *
	 * @param securityPolicyId セキュリティポリシーID
	 */
	public void setSecurityPolicyId(String securityPolicyId) {
		this.securityPolicyId = securityPolicyId;
	}

	/**
	 * オーナーIDを取得
	 *
	 * @return オーナーID
	 */
	public String getOwnerId() {
		return ownerId;
	}

	/**
	 * オーナーIDを設定
	 *
	 * @param ownerId オーナーID
	 */
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * セキュリティポリシー名を取得
	 *
	 * @return セキュリティポリシー名
	 */
	public String getName() {
		return name;
	}

	/**
	 * セキュリティポリシー名を設定
	 *
	 * @param name セキュリティポリシー名
	 */
	public void setName(String name) {
		this.name = name;
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
	 * 作成日時(エポック秒)を取得
	 *
	 * @return 作成日時(エポック秒)
	 */
	public Integer getCreateAt() {
		return createAt;
	}

	/**
	 * 作成日時(エポック秒)を設定
	 *
	 * @param createAt 作成日時(エポック秒)
	 */
	public void setCreateAt(Integer createAt) {
		this.createAt = createAt;
	}

	/**
	 * 最終更新日時(エポック秒)を取得
	 *
	 * @return 最終更新日時(エポック秒)
	 */
	public Integer getUpdateAt() {
		return updateAt;
	}

	/**
	 * 最終更新日時(エポック秒)を設定
	 *
	 * @param updateAt 最終更新日時(エポック秒)
	 */
	public void setUpdateAt(Integer updateAt) {
		this.updateAt = updateAt;
	}

}
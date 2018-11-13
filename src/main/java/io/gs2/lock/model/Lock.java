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

package io.gs2.lock.model;

import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * ロック
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Lock implements Serializable {

	/** ロックプールGRN */
	private String lockPoolId;

	/** オーナーID */
	private String userId;

	/** トランザクションID */
	private String transactionId;

	/** リソース名 */
	private String resourceName;

	/** 有効期限 */
	private Integer ttl;


	/**
	 * ロックプールGRNを取得
	 *
	 * @return ロックプールGRN
	 */
	public String getLockPoolId() {
		return lockPoolId;
	}

	/**
	 * ロックプールGRNを設定
	 *
	 * @param lockPoolId ロックプールGRN
	 */
	public void setLockPoolId(String lockPoolId) {
		this.lockPoolId = lockPoolId;
	}

	/**
	 * オーナーIDを取得
	 *
	 * @return オーナーID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * オーナーIDを設定
	 *
	 * @param userId オーナーID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * トランザクションIDを取得
	 *
	 * @return トランザクションID
	 */
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * トランザクションIDを設定
	 *
	 * @param transactionId トランザクションID
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * リソース名を取得
	 *
	 * @return リソース名
	 */
	public String getResourceName() {
		return resourceName;
	}

	/**
	 * リソース名を設定
	 *
	 * @param resourceName リソース名
	 */
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	/**
	 * 有効期限を取得
	 *
	 * @return 有効期限
	 */
	public Integer getTtl() {
		return ttl;
	}

	/**
	 * 有効期限を設定
	 *
	 * @param ttl 有効期限
	 */
	public void setTtl(Integer ttl) {
		this.ttl = ttl;
	}

}
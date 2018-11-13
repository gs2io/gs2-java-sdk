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
 * ユーザ
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Identifier implements Serializable {

	/** ユーザGRN */
	private String identifierId;

	/** オーナーID */
	private String ownerId;

	/** ユーザID */
	private String userId;

	/** クライアントID */
	private String clientId;

	/** 作成日時(エポック秒) */
	private Integer createAt;


	/**
	 * ユーザGRNを取得
	 *
	 * @return ユーザGRN
	 */
	public String getIdentifierId() {
		return identifierId;
	}

	/**
	 * ユーザGRNを設定
	 *
	 * @param identifierId ユーザGRN
	 */
	public void setIdentifierId(String identifierId) {
		this.identifierId = identifierId;
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
	 * クライアントIDを取得
	 *
	 * @return クライアントID
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * クライアントIDを設定
	 *
	 * @param clientId クライアントID
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
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

}
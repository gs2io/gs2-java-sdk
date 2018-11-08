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
public class User implements Serializable {

	/** ユーザGRN */
	private String userId;

	/** オーナーID */
	private String ownerId;

	/** ユーザ名 */
	private String name;

	/** 作成日時(エポック秒) */
	private Integer createAt;


	/**
	 * ユーザGRNを取得
	 *
	 * @return ユーザGRN
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * ユーザGRNを設定
	 *
	 * @param userId ユーザGRN
	 */
	public void setUserId(String userId) {
		this.userId = userId;
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
	 * ユーザ名を取得
	 *
	 * @return ユーザ名
	 */
	public String getName() {
		return name;
	}

	/**
	 * ユーザ名を設定
	 *
	 * @param name ユーザ名
	 */
	public void setName(String name) {
		this.name = name;
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
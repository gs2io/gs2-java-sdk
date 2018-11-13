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

package io.gs2.account.model;

import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 引き継ぎ情報
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class TakeOver implements Serializable {

	/** ユーザID */
	private String userId;

	/** アカウント種別 */
	private Integer type;

	/** ユーザ識別子 */
	private String userIdentifier;

	/** 作成日時(エポック秒) */
	private Integer createAt;


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
	 * アカウント種別を取得
	 *
	 * @return アカウント種別
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * アカウント種別を設定
	 *
	 * @param type アカウント種別
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * ユーザ識別子を取得
	 *
	 * @return ユーザ識別子
	 */
	public String getUserIdentifier() {
		return userIdentifier;
	}

	/**
	 * ユーザ識別子を設定
	 *
	 * @param userIdentifier ユーザ識別子
	 */
	public void setUserIdentifier(String userIdentifier) {
		this.userIdentifier = userIdentifier;
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
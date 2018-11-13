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

package io.gs2.stamina.model;

import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * スタミナ
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Stamina implements Serializable {

	/** ユーザID */
	private String userId;

	/** スタミナ値 */
	private Integer value;

	/** 最大値を超えて保持しているスタミナ値 */
	private Integer overflow;

	/** 最終更新日時(エポック秒) */
	private Integer lastUpdateAt;


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
	 * スタミナ値を取得
	 *
	 * @return スタミナ値
	 */
	public Integer getValue() {
		return value;
	}

	/**
	 * スタミナ値を設定
	 *
	 * @param value スタミナ値
	 */
	public void setValue(Integer value) {
		this.value = value;
	}

	/**
	 * 最大値を超えて保持しているスタミナ値を取得
	 *
	 * @return 最大値を超えて保持しているスタミナ値
	 */
	public Integer getOverflow() {
		return overflow;
	}

	/**
	 * 最大値を超えて保持しているスタミナ値を設定
	 *
	 * @param overflow 最大値を超えて保持しているスタミナ値
	 */
	public void setOverflow(Integer overflow) {
		this.overflow = overflow;
	}

	/**
	 * 最終更新日時(エポック秒)を取得
	 *
	 * @return 最終更新日時(エポック秒)
	 */
	public Integer getLastUpdateAt() {
		return lastUpdateAt;
	}

	/**
	 * 最終更新日時(エポック秒)を設定
	 *
	 * @param lastUpdateAt 最終更新日時(エポック秒)
	 */
	public void setLastUpdateAt(Integer lastUpdateAt) {
		this.lastUpdateAt = lastUpdateAt;
	}

}
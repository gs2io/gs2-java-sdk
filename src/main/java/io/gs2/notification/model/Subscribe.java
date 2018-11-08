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

package io.gs2.notification.model;

import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 購読
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Subscribe implements Serializable {

	/** 購読GRN */
	private String subscribeId;

	/** 通知GRN */
	private String notificationId;

	/** オーナーID */
	private String ownerId;

	/** 通知方法 */
	private String type;

	/** type = email: メールアドレス */
	private String endpoint;

	/** 作成日時(エポック秒) */
	private Integer createAt;

	/** 最終更新日時(エポック秒) */
	private Integer updateAt;


	/**
	 * 購読GRNを取得
	 *
	 * @return 購読GRN
	 */
	public String getSubscribeId() {
		return subscribeId;
	}

	/**
	 * 購読GRNを設定
	 *
	 * @param subscribeId 購読GRN
	 */
	public void setSubscribeId(String subscribeId) {
		this.subscribeId = subscribeId;
	}

	/**
	 * 通知GRNを取得
	 *
	 * @return 通知GRN
	 */
	public String getNotificationId() {
		return notificationId;
	}

	/**
	 * 通知GRNを設定
	 *
	 * @param notificationId 通知GRN
	 */
	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
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
	 * 通知方法を取得
	 *
	 * @return 通知方法
	 */
	public String getType() {
		return type;
	}

	/**
	 * 通知方法を設定
	 *
	 * @param type 通知方法
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * type = email: メールアドレスを取得
	 *
	 * @return type = email: メールアドレス
	 */
	public String getEndpoint() {
		return endpoint;
	}

	/**
	 * type = email: メールアドレスを設定
	 *
	 * @param endpoint type = email: メールアドレス
	 */
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
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
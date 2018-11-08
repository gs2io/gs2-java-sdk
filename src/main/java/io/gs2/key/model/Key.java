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

package io.gs2.key.model;

import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 暗号鍵
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Key implements Serializable {

	/** 暗号鍵GRN */
	private String keyId;

	/** オーナーID */
	private String ownerId;

	/** 暗号鍵名 */
	private String name;

	/** 作成日時(エポック秒) */
	private Integer createAt;


	/**
	 * 暗号鍵GRNを取得
	 *
	 * @return 暗号鍵GRN
	 */
	public String getKeyId() {
		return keyId;
	}

	/**
	 * 暗号鍵GRNを設定
	 *
	 * @param keyId 暗号鍵GRN
	 */
	public void setKeyId(String keyId) {
		this.keyId = keyId;
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
	 * 暗号鍵名を取得
	 *
	 * @return 暗号鍵名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 暗号鍵名を設定
	 *
	 * @param name 暗号鍵名
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
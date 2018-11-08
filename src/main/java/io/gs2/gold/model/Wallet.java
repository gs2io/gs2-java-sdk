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

package io.gs2.gold.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * ウォレット
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Wallet implements Serializable {

	/** ウォレットGRN */
	private String walletId;

	/** ユーザID */
	private String userId;

	/** ゴールド名 */
	private String goldName;

	/** メタデータ */
	private String meta;

	/** 残高 */
	private Long balance;

	/** 直近の期間の取得量 */
	private Long latestGain;

	/** 作成日時(エポック秒) */
	private Integer createAt;

	/** 最終更新日時(エポック秒) */
	private Integer updateAt;


	/**
	 * ウォレットGRNを取得
	 *
	 * @return ウォレットGRN
	 */
	public String getWalletId() {
		return walletId;
	}

	/**
	 * ウォレットGRNを設定
	 *
	 * @param walletId ウォレットGRN
	 */
	public void setWalletId(String walletId) {
		this.walletId = walletId;
	}

	/**
	 * ウォレットGRNを設定
	 *
	 * @param walletId ウォレットGRN
	 * @return this
	 */
	public Wallet withWalletId(String walletId) {
		this.walletId = walletId;
		return this;
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
	 * ユーザIDを設定
	 *
	 * @param userId ユーザID
	 * @return this
	 */
	public Wallet withUserId(String userId) {
		this.userId = userId;
		return this;
	}

	/**
	 * ゴールド名を取得
	 *
	 * @return ゴールド名
	 */
	public String getGoldName() {
		return goldName;
	}

	/**
	 * ゴールド名を設定
	 *
	 * @param goldName ゴールド名
	 */
	public void setGoldName(String goldName) {
		this.goldName = goldName;
	}

	/**
	 * ゴールド名を設定
	 *
	 * @param goldName ゴールド名
	 * @return this
	 */
	public Wallet withGoldName(String goldName) {
		this.goldName = goldName;
		return this;
	}

	/**
	 * メタデータを取得
	 *
	 * @return メタデータ
	 */
	public String getMeta() {
		return meta;
	}

	/**
	 * メタデータを設定
	 *
	 * @param meta メタデータ
	 */
	public void setMeta(String meta) {
		this.meta = meta;
	}

	/**
	 * メタデータを設定
	 *
	 * @param meta メタデータ
	 * @return this
	 */
	public Wallet withMeta(String meta) {
		this.meta = meta;
		return this;
	}

	/**
	 * 残高を取得
	 *
	 * @return 残高
	 */
	public Long getBalance() {
		return balance;
	}

	/**
	 * 残高を設定
	 *
	 * @param balance 残高
	 */
	public void setBalance(Long balance) {
		this.balance = balance;
	}

	/**
	 * 残高を設定
	 *
	 * @param balance 残高
	 * @return this
	 */
	public Wallet withBalance(Long balance) {
		this.balance = balance;
		return this;
	}

	/**
	 * 直近の期間の取得量を取得
	 *
	 * @return 直近の期間の取得量
	 */
	public Long getLatestGain() {
		return latestGain;
	}

	/**
	 * 直近の期間の取得量を設定
	 *
	 * @param latestGain 直近の期間の取得量
	 */
	public void setLatestGain(Long latestGain) {
		this.latestGain = latestGain;
	}

	/**
	 * 直近の期間の取得量を設定
	 *
	 * @param latestGain 直近の期間の取得量
	 * @return this
	 */
	public Wallet withLatestGain(Long latestGain) {
		this.latestGain = latestGain;
		return this;
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
	 * 作成日時(エポック秒)を設定
	 *
	 * @param createAt 作成日時(エポック秒)
	 * @return this
	 */
	public Wallet withCreateAt(Integer createAt) {
		this.createAt = createAt;
		return this;
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

	/**
	 * 最終更新日時(エポック秒)を設定
	 *
	 * @param updateAt 最終更新日時(エポック秒)
	 * @return this
	 */
	public Wallet withUpdateAt(Integer updateAt) {
		this.updateAt = updateAt;
		return this;
	}


    public ObjectNode toJson() {

		ObjectNode body = JsonNodeFactory.instance.objectNode()

            .put("walletId", this.getWalletId())
            .put("userId", this.getUserId())
            .put("goldName", this.getGoldName())
            .put("meta", this.getMeta())
            .put("balance", this.getBalance())
            .put("latestGain", this.getLatestGain())
            .put("createAt", this.getCreateAt())
            .put("updateAt", this.getUpdateAt());

        return body;
    }
}
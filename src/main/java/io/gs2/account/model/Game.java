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
 * ゲーム
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Game implements Serializable {

	/** ゲームGRN */
	private String gameId;

	/** オーナーID */
	private String ownerId;

	/** ゲーム名 */
	private String name;

	/** 説明文 */
	private String description;

	/** サービスクラス */
	private String serviceClass;

	/** アカウント引き継ぎ時にパスワードを変更するか */
	private Boolean changePasswordIfTakeOver;

	/** アカウント新規作成時 に実行されるGS2-Script */
	private String createAccountTriggerScript;

	/** アカウント新規作成完了時 に実行されるGS2-Script */
	private String createAccountDoneTriggerScript;

	/** 認証時 に実行されるGS2-Script */
	private String authenticationTriggerScript;

	/** 認証完了時 に実行されるGS2-Script */
	private String authenticationDoneTriggerScript;

	/** 引き継ぎ情報登録時 に実行されるGS2-Script */
	private String createTakeOverTriggerScript;

	/** 引き継ぎ情報登録完了時 に実行されるGS2-Script */
	private String createTakeOverDoneTriggerScript;

	/** 引き継ぎ実行時 に実行されるGS2-Script */
	private String doTakeOverTriggerScript;

	/** 引き継ぎ実行完了時 に実行されるGS2-Script */
	private String doTakeOverDoneTriggerScript;

	/** 作成日時(エポック秒) */
	private Integer createAt;

	/** 最終更新日時(エポック秒) */
	private Integer updateAt;


	/**
	 * ゲームGRNを取得
	 *
	 * @return ゲームGRN
	 */
	public String getGameId() {
		return gameId;
	}

	/**
	 * ゲームGRNを設定
	 *
	 * @param gameId ゲームGRN
	 */
	public void setGameId(String gameId) {
		this.gameId = gameId;
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
	 * ゲーム名を取得
	 *
	 * @return ゲーム名
	 */
	public String getName() {
		return name;
	}

	/**
	 * ゲーム名を設定
	 *
	 * @param name ゲーム名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 説明文を取得
	 *
	 * @return 説明文
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 説明文を設定
	 *
	 * @param description 説明文
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * サービスクラスを取得
	 *
	 * @return サービスクラス
	 */
	public String getServiceClass() {
		return serviceClass;
	}

	/**
	 * サービスクラスを設定
	 *
	 * @param serviceClass サービスクラス
	 */
	public void setServiceClass(String serviceClass) {
		this.serviceClass = serviceClass;
	}

	/**
	 * アカウント引き継ぎ時にパスワードを変更するかを取得
	 *
	 * @return アカウント引き継ぎ時にパスワードを変更するか
	 */
	public Boolean getChangePasswordIfTakeOver() {
		return changePasswordIfTakeOver;
	}

	/**
	 * アカウント引き継ぎ時にパスワードを変更するかを設定
	 *
	 * @param changePasswordIfTakeOver アカウント引き継ぎ時にパスワードを変更するか
	 */
	public void setChangePasswordIfTakeOver(Boolean changePasswordIfTakeOver) {
		this.changePasswordIfTakeOver = changePasswordIfTakeOver;
	}

	/**
	 * アカウント新規作成時 に実行されるGS2-Scriptを取得
	 *
	 * @return アカウント新規作成時 に実行されるGS2-Script
	 */
	public String getCreateAccountTriggerScript() {
		return createAccountTriggerScript;
	}

	/**
	 * アカウント新規作成時 に実行されるGS2-Scriptを設定
	 *
	 * @param createAccountTriggerScript アカウント新規作成時 に実行されるGS2-Script
	 */
	public void setCreateAccountTriggerScript(String createAccountTriggerScript) {
		this.createAccountTriggerScript = createAccountTriggerScript;
	}

	/**
	 * アカウント新規作成完了時 に実行されるGS2-Scriptを取得
	 *
	 * @return アカウント新規作成完了時 に実行されるGS2-Script
	 */
	public String getCreateAccountDoneTriggerScript() {
		return createAccountDoneTriggerScript;
	}

	/**
	 * アカウント新規作成完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param createAccountDoneTriggerScript アカウント新規作成完了時 に実行されるGS2-Script
	 */
	public void setCreateAccountDoneTriggerScript(String createAccountDoneTriggerScript) {
		this.createAccountDoneTriggerScript = createAccountDoneTriggerScript;
	}

	/**
	 * 認証時 に実行されるGS2-Scriptを取得
	 *
	 * @return 認証時 に実行されるGS2-Script
	 */
	public String getAuthenticationTriggerScript() {
		return authenticationTriggerScript;
	}

	/**
	 * 認証時 に実行されるGS2-Scriptを設定
	 *
	 * @param authenticationTriggerScript 認証時 に実行されるGS2-Script
	 */
	public void setAuthenticationTriggerScript(String authenticationTriggerScript) {
		this.authenticationTriggerScript = authenticationTriggerScript;
	}

	/**
	 * 認証完了時 に実行されるGS2-Scriptを取得
	 *
	 * @return 認証完了時 に実行されるGS2-Script
	 */
	public String getAuthenticationDoneTriggerScript() {
		return authenticationDoneTriggerScript;
	}

	/**
	 * 認証完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param authenticationDoneTriggerScript 認証完了時 に実行されるGS2-Script
	 */
	public void setAuthenticationDoneTriggerScript(String authenticationDoneTriggerScript) {
		this.authenticationDoneTriggerScript = authenticationDoneTriggerScript;
	}

	/**
	 * 引き継ぎ情報登録時 に実行されるGS2-Scriptを取得
	 *
	 * @return 引き継ぎ情報登録時 に実行されるGS2-Script
	 */
	public String getCreateTakeOverTriggerScript() {
		return createTakeOverTriggerScript;
	}

	/**
	 * 引き継ぎ情報登録時 に実行されるGS2-Scriptを設定
	 *
	 * @param createTakeOverTriggerScript 引き継ぎ情報登録時 に実行されるGS2-Script
	 */
	public void setCreateTakeOverTriggerScript(String createTakeOverTriggerScript) {
		this.createTakeOverTriggerScript = createTakeOverTriggerScript;
	}

	/**
	 * 引き継ぎ情報登録完了時 に実行されるGS2-Scriptを取得
	 *
	 * @return 引き継ぎ情報登録完了時 に実行されるGS2-Script
	 */
	public String getCreateTakeOverDoneTriggerScript() {
		return createTakeOverDoneTriggerScript;
	}

	/**
	 * 引き継ぎ情報登録完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param createTakeOverDoneTriggerScript 引き継ぎ情報登録完了時 に実行されるGS2-Script
	 */
	public void setCreateTakeOverDoneTriggerScript(String createTakeOverDoneTriggerScript) {
		this.createTakeOverDoneTriggerScript = createTakeOverDoneTriggerScript;
	}

	/**
	 * 引き継ぎ実行時 に実行されるGS2-Scriptを取得
	 *
	 * @return 引き継ぎ実行時 に実行されるGS2-Script
	 */
	public String getDoTakeOverTriggerScript() {
		return doTakeOverTriggerScript;
	}

	/**
	 * 引き継ぎ実行時 に実行されるGS2-Scriptを設定
	 *
	 * @param doTakeOverTriggerScript 引き継ぎ実行時 に実行されるGS2-Script
	 */
	public void setDoTakeOverTriggerScript(String doTakeOverTriggerScript) {
		this.doTakeOverTriggerScript = doTakeOverTriggerScript;
	}

	/**
	 * 引き継ぎ実行完了時 に実行されるGS2-Scriptを取得
	 *
	 * @return 引き継ぎ実行完了時 に実行されるGS2-Script
	 */
	public String getDoTakeOverDoneTriggerScript() {
		return doTakeOverDoneTriggerScript;
	}

	/**
	 * 引き継ぎ実行完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param doTakeOverDoneTriggerScript 引き継ぎ実行完了時 に実行されるGS2-Script
	 */
	public void setDoTakeOverDoneTriggerScript(String doTakeOverDoneTriggerScript) {
		this.doTakeOverDoneTriggerScript = doTakeOverDoneTriggerScript;
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
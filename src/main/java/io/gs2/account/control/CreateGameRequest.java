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

package io.gs2.account.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.account.Gs2Account;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateGameRequest extends Gs2BasicRequest<CreateGameRequest> {

	public static class Constant extends Gs2Account.Constant {
		public static final String FUNCTION = "CreateGame";
	}

	/** ゲームの名前 */
	private String name;

	/** ゲームの説明 */
	private String description;

	/** ゲームのサービスクラス */
	private String serviceClass;

	/** 引き継ぎ時にアカウントのパスワードを変更するか */
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


	/**
	 * ゲームの名前を取得
	 *
	 * @return ゲームの名前
	 */
	public String getName() {
		return name;
	}

	/**
	 * ゲームの名前を設定
	 *
	 * @param name ゲームの名前
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ゲームの名前を設定
	 *
	 * @param name ゲームの名前
	 * @return this
	 */
	public CreateGameRequest withName(String name) {
		setName(name);
		return this;
	}

	/**
	 * ゲームの説明を取得
	 *
	 * @return ゲームの説明
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * ゲームの説明を設定
	 *
	 * @param description ゲームの説明
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * ゲームの説明を設定
	 *
	 * @param description ゲームの説明
	 * @return this
	 */
	public CreateGameRequest withDescription(String description) {
		setDescription(description);
		return this;
	}

	/**
	 * ゲームのサービスクラスを取得
	 *
	 * @return ゲームのサービスクラス
	 */
	public String getServiceClass() {
		return serviceClass;
	}

	/**
	 * ゲームのサービスクラスを設定
	 *
	 * @param serviceClass ゲームのサービスクラス
	 */
	public void setServiceClass(String serviceClass) {
		this.serviceClass = serviceClass;
	}

	/**
	 * ゲームのサービスクラスを設定
	 *
	 * @param serviceClass ゲームのサービスクラス
	 * @return this
	 */
	public CreateGameRequest withServiceClass(String serviceClass) {
		setServiceClass(serviceClass);
		return this;
	}

	/**
	 * 引き継ぎ時にアカウントのパスワードを変更するかを取得
	 *
	 * @return 引き継ぎ時にアカウントのパスワードを変更するか
	 */
	public Boolean getChangePasswordIfTakeOver() {
		return changePasswordIfTakeOver;
	}

	/**
	 * 引き継ぎ時にアカウントのパスワードを変更するかを設定
	 *
	 * @param changePasswordIfTakeOver 引き継ぎ時にアカウントのパスワードを変更するか
	 */
	public void setChangePasswordIfTakeOver(Boolean changePasswordIfTakeOver) {
		this.changePasswordIfTakeOver = changePasswordIfTakeOver;
	}

	/**
	 * 引き継ぎ時にアカウントのパスワードを変更するかを設定
	 *
	 * @param changePasswordIfTakeOver 引き継ぎ時にアカウントのパスワードを変更するか
	 * @return this
	 */
	public CreateGameRequest withChangePasswordIfTakeOver(Boolean changePasswordIfTakeOver) {
		setChangePasswordIfTakeOver(changePasswordIfTakeOver);
		return this;
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
	 * アカウント新規作成時 に実行されるGS2-Scriptを設定
	 *
	 * @param createAccountTriggerScript アカウント新規作成時 に実行されるGS2-Script
	 * @return this
	 */
	public CreateGameRequest withCreateAccountTriggerScript(String createAccountTriggerScript) {
		setCreateAccountTriggerScript(createAccountTriggerScript);
		return this;
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
	 * アカウント新規作成完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param createAccountDoneTriggerScript アカウント新規作成完了時 に実行されるGS2-Script
	 * @return this
	 */
	public CreateGameRequest withCreateAccountDoneTriggerScript(String createAccountDoneTriggerScript) {
		setCreateAccountDoneTriggerScript(createAccountDoneTriggerScript);
		return this;
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
	 * 認証時 に実行されるGS2-Scriptを設定
	 *
	 * @param authenticationTriggerScript 認証時 に実行されるGS2-Script
	 * @return this
	 */
	public CreateGameRequest withAuthenticationTriggerScript(String authenticationTriggerScript) {
		setAuthenticationTriggerScript(authenticationTriggerScript);
		return this;
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
	 * 認証完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param authenticationDoneTriggerScript 認証完了時 に実行されるGS2-Script
	 * @return this
	 */
	public CreateGameRequest withAuthenticationDoneTriggerScript(String authenticationDoneTriggerScript) {
		setAuthenticationDoneTriggerScript(authenticationDoneTriggerScript);
		return this;
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
	 * 引き継ぎ情報登録時 に実行されるGS2-Scriptを設定
	 *
	 * @param createTakeOverTriggerScript 引き継ぎ情報登録時 に実行されるGS2-Script
	 * @return this
	 */
	public CreateGameRequest withCreateTakeOverTriggerScript(String createTakeOverTriggerScript) {
		setCreateTakeOverTriggerScript(createTakeOverTriggerScript);
		return this;
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
	 * 引き継ぎ情報登録完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param createTakeOverDoneTriggerScript 引き継ぎ情報登録完了時 に実行されるGS2-Script
	 * @return this
	 */
	public CreateGameRequest withCreateTakeOverDoneTriggerScript(String createTakeOverDoneTriggerScript) {
		setCreateTakeOverDoneTriggerScript(createTakeOverDoneTriggerScript);
		return this;
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
	 * 引き継ぎ実行時 に実行されるGS2-Scriptを設定
	 *
	 * @param doTakeOverTriggerScript 引き継ぎ実行時 に実行されるGS2-Script
	 * @return this
	 */
	public CreateGameRequest withDoTakeOverTriggerScript(String doTakeOverTriggerScript) {
		setDoTakeOverTriggerScript(doTakeOverTriggerScript);
		return this;
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
	 * 引き継ぎ実行完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param doTakeOverDoneTriggerScript 引き継ぎ実行完了時 に実行されるGS2-Script
	 * @return this
	 */
	public CreateGameRequest withDoTakeOverDoneTriggerScript(String doTakeOverDoneTriggerScript) {
		setDoTakeOverDoneTriggerScript(doTakeOverDoneTriggerScript);
		return this;
	}

}
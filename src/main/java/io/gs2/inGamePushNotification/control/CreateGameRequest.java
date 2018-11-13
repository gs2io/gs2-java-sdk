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

package io.gs2.inGamePushNotification.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.inGamePushNotification.Gs2InGamePushNotification;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateGameRequest extends Gs2BasicRequest<CreateGameRequest> {

	public static class Constant extends Gs2InGamePushNotification.Constant {
		public static final String FUNCTION = "CreateGame";
	}

	/** ゲーム名 */
	private String name;

	/** 説明文 */
	private String description;

	/** サービスクラス */
	private String serviceClass;

	/** オフライン転送方式 */
	private String offlineTransfer;

	/** オフライン転送先URL */
	private String notificationUrl;

	/** Firebaseのサーバーキー */
	private String notificationFirebaseServerKey;

	/** クライアント証明書発行時 に実行されるGS2-Script */
	private String createCertificateTriggerScript;

	/** クライアント証明書発行完了時 に実行されるGS2-Script */
	private String createCertificateDoneTriggerScript;

	/** クライアント証明書削除時 に実行されるGS2-Script */
	private String deleteCertificateTriggerScript;

	/** クライアント証明書削除完了時 に実行されるGS2-Script */
	private String deleteCertificateDoneTriggerScript;

	/** 通知送信時 に実行されるGS2-Script */
	private String publishTriggerScript;

	/** 通知送信完了時 に実行されるGS2-Script */
	private String publishDoneTriggerScript;

	/** Firebaseデバイストークン登録時 に実行されるGS2-Script */
	private String setFirebaseTokenTriggerScript;

	/** Firebaseデバイストークン登録完了時 に実行されるGS2-Script */
	private String setFirebaseTokenDoneTriggerScript;


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
	 * ゲーム名を設定
	 *
	 * @param name ゲーム名
	 * @return this
	 */
	public CreateGameRequest withName(String name) {
		setName(name);
		return this;
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
	 * 説明文を設定
	 *
	 * @param description 説明文
	 * @return this
	 */
	public CreateGameRequest withDescription(String description) {
		setDescription(description);
		return this;
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
	 * サービスクラスを設定
	 *
	 * @param serviceClass サービスクラス
	 * @return this
	 */
	public CreateGameRequest withServiceClass(String serviceClass) {
		setServiceClass(serviceClass);
		return this;
	}

	/**
	 * オフライン転送方式を取得
	 *
	 * @return オフライン転送方式
	 */
	public String getOfflineTransfer() {
		return offlineTransfer;
	}

	/**
	 * オフライン転送方式を設定
	 *
	 * @param offlineTransfer オフライン転送方式
	 */
	public void setOfflineTransfer(String offlineTransfer) {
		this.offlineTransfer = offlineTransfer;
	}

	/**
	 * オフライン転送方式を設定
	 *
	 * @param offlineTransfer オフライン転送方式
	 * @return this
	 */
	public CreateGameRequest withOfflineTransfer(String offlineTransfer) {
		setOfflineTransfer(offlineTransfer);
		return this;
	}

	/**
	 * オフライン転送先URLを取得
	 *
	 * @return オフライン転送先URL
	 */
	public String getNotificationUrl() {
		return notificationUrl;
	}

	/**
	 * オフライン転送先URLを設定
	 *
	 * @param notificationUrl オフライン転送先URL
	 */
	public void setNotificationUrl(String notificationUrl) {
		this.notificationUrl = notificationUrl;
	}

	/**
	 * オフライン転送先URLを設定
	 *
	 * @param notificationUrl オフライン転送先URL
	 * @return this
	 */
	public CreateGameRequest withNotificationUrl(String notificationUrl) {
		setNotificationUrl(notificationUrl);
		return this;
	}

	/**
	 * Firebaseのサーバーキーを取得
	 *
	 * @return Firebaseのサーバーキー
	 */
	public String getNotificationFirebaseServerKey() {
		return notificationFirebaseServerKey;
	}

	/**
	 * Firebaseのサーバーキーを設定
	 *
	 * @param notificationFirebaseServerKey Firebaseのサーバーキー
	 */
	public void setNotificationFirebaseServerKey(String notificationFirebaseServerKey) {
		this.notificationFirebaseServerKey = notificationFirebaseServerKey;
	}

	/**
	 * Firebaseのサーバーキーを設定
	 *
	 * @param notificationFirebaseServerKey Firebaseのサーバーキー
	 * @return this
	 */
	public CreateGameRequest withNotificationFirebaseServerKey(String notificationFirebaseServerKey) {
		setNotificationFirebaseServerKey(notificationFirebaseServerKey);
		return this;
	}

	/**
	 * クライアント証明書発行時 に実行されるGS2-Scriptを取得
	 *
	 * @return クライアント証明書発行時 に実行されるGS2-Script
	 */
	public String getCreateCertificateTriggerScript() {
		return createCertificateTriggerScript;
	}

	/**
	 * クライアント証明書発行時 に実行されるGS2-Scriptを設定
	 *
	 * @param createCertificateTriggerScript クライアント証明書発行時 に実行されるGS2-Script
	 */
	public void setCreateCertificateTriggerScript(String createCertificateTriggerScript) {
		this.createCertificateTriggerScript = createCertificateTriggerScript;
	}

	/**
	 * クライアント証明書発行時 に実行されるGS2-Scriptを設定
	 *
	 * @param createCertificateTriggerScript クライアント証明書発行時 に実行されるGS2-Script
	 * @return this
	 */
	public CreateGameRequest withCreateCertificateTriggerScript(String createCertificateTriggerScript) {
		setCreateCertificateTriggerScript(createCertificateTriggerScript);
		return this;
	}

	/**
	 * クライアント証明書発行完了時 に実行されるGS2-Scriptを取得
	 *
	 * @return クライアント証明書発行完了時 に実行されるGS2-Script
	 */
	public String getCreateCertificateDoneTriggerScript() {
		return createCertificateDoneTriggerScript;
	}

	/**
	 * クライアント証明書発行完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param createCertificateDoneTriggerScript クライアント証明書発行完了時 に実行されるGS2-Script
	 */
	public void setCreateCertificateDoneTriggerScript(String createCertificateDoneTriggerScript) {
		this.createCertificateDoneTriggerScript = createCertificateDoneTriggerScript;
	}

	/**
	 * クライアント証明書発行完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param createCertificateDoneTriggerScript クライアント証明書発行完了時 に実行されるGS2-Script
	 * @return this
	 */
	public CreateGameRequest withCreateCertificateDoneTriggerScript(String createCertificateDoneTriggerScript) {
		setCreateCertificateDoneTriggerScript(createCertificateDoneTriggerScript);
		return this;
	}

	/**
	 * クライアント証明書削除時 に実行されるGS2-Scriptを取得
	 *
	 * @return クライアント証明書削除時 に実行されるGS2-Script
	 */
	public String getDeleteCertificateTriggerScript() {
		return deleteCertificateTriggerScript;
	}

	/**
	 * クライアント証明書削除時 に実行されるGS2-Scriptを設定
	 *
	 * @param deleteCertificateTriggerScript クライアント証明書削除時 に実行されるGS2-Script
	 */
	public void setDeleteCertificateTriggerScript(String deleteCertificateTriggerScript) {
		this.deleteCertificateTriggerScript = deleteCertificateTriggerScript;
	}

	/**
	 * クライアント証明書削除時 に実行されるGS2-Scriptを設定
	 *
	 * @param deleteCertificateTriggerScript クライアント証明書削除時 に実行されるGS2-Script
	 * @return this
	 */
	public CreateGameRequest withDeleteCertificateTriggerScript(String deleteCertificateTriggerScript) {
		setDeleteCertificateTriggerScript(deleteCertificateTriggerScript);
		return this;
	}

	/**
	 * クライアント証明書削除完了時 に実行されるGS2-Scriptを取得
	 *
	 * @return クライアント証明書削除完了時 に実行されるGS2-Script
	 */
	public String getDeleteCertificateDoneTriggerScript() {
		return deleteCertificateDoneTriggerScript;
	}

	/**
	 * クライアント証明書削除完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param deleteCertificateDoneTriggerScript クライアント証明書削除完了時 に実行されるGS2-Script
	 */
	public void setDeleteCertificateDoneTriggerScript(String deleteCertificateDoneTriggerScript) {
		this.deleteCertificateDoneTriggerScript = deleteCertificateDoneTriggerScript;
	}

	/**
	 * クライアント証明書削除完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param deleteCertificateDoneTriggerScript クライアント証明書削除完了時 に実行されるGS2-Script
	 * @return this
	 */
	public CreateGameRequest withDeleteCertificateDoneTriggerScript(String deleteCertificateDoneTriggerScript) {
		setDeleteCertificateDoneTriggerScript(deleteCertificateDoneTriggerScript);
		return this;
	}

	/**
	 * 通知送信時 に実行されるGS2-Scriptを取得
	 *
	 * @return 通知送信時 に実行されるGS2-Script
	 */
	public String getPublishTriggerScript() {
		return publishTriggerScript;
	}

	/**
	 * 通知送信時 に実行されるGS2-Scriptを設定
	 *
	 * @param publishTriggerScript 通知送信時 に実行されるGS2-Script
	 */
	public void setPublishTriggerScript(String publishTriggerScript) {
		this.publishTriggerScript = publishTriggerScript;
	}

	/**
	 * 通知送信時 に実行されるGS2-Scriptを設定
	 *
	 * @param publishTriggerScript 通知送信時 に実行されるGS2-Script
	 * @return this
	 */
	public CreateGameRequest withPublishTriggerScript(String publishTriggerScript) {
		setPublishTriggerScript(publishTriggerScript);
		return this;
	}

	/**
	 * 通知送信完了時 に実行されるGS2-Scriptを取得
	 *
	 * @return 通知送信完了時 に実行されるGS2-Script
	 */
	public String getPublishDoneTriggerScript() {
		return publishDoneTriggerScript;
	}

	/**
	 * 通知送信完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param publishDoneTriggerScript 通知送信完了時 に実行されるGS2-Script
	 */
	public void setPublishDoneTriggerScript(String publishDoneTriggerScript) {
		this.publishDoneTriggerScript = publishDoneTriggerScript;
	}

	/**
	 * 通知送信完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param publishDoneTriggerScript 通知送信完了時 に実行されるGS2-Script
	 * @return this
	 */
	public CreateGameRequest withPublishDoneTriggerScript(String publishDoneTriggerScript) {
		setPublishDoneTriggerScript(publishDoneTriggerScript);
		return this;
	}

	/**
	 * Firebaseデバイストークン登録時 に実行されるGS2-Scriptを取得
	 *
	 * @return Firebaseデバイストークン登録時 に実行されるGS2-Script
	 */
	public String getSetFirebaseTokenTriggerScript() {
		return setFirebaseTokenTriggerScript;
	}

	/**
	 * Firebaseデバイストークン登録時 に実行されるGS2-Scriptを設定
	 *
	 * @param setFirebaseTokenTriggerScript Firebaseデバイストークン登録時 に実行されるGS2-Script
	 */
	public void setSetFirebaseTokenTriggerScript(String setFirebaseTokenTriggerScript) {
		this.setFirebaseTokenTriggerScript = setFirebaseTokenTriggerScript;
	}

	/**
	 * Firebaseデバイストークン登録時 に実行されるGS2-Scriptを設定
	 *
	 * @param setFirebaseTokenTriggerScript Firebaseデバイストークン登録時 に実行されるGS2-Script
	 * @return this
	 */
	public CreateGameRequest withSetFirebaseTokenTriggerScript(String setFirebaseTokenTriggerScript) {
		setSetFirebaseTokenTriggerScript(setFirebaseTokenTriggerScript);
		return this;
	}

	/**
	 * Firebaseデバイストークン登録完了時 に実行されるGS2-Scriptを取得
	 *
	 * @return Firebaseデバイストークン登録完了時 に実行されるGS2-Script
	 */
	public String getSetFirebaseTokenDoneTriggerScript() {
		return setFirebaseTokenDoneTriggerScript;
	}

	/**
	 * Firebaseデバイストークン登録完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param setFirebaseTokenDoneTriggerScript Firebaseデバイストークン登録完了時 に実行されるGS2-Script
	 */
	public void setSetFirebaseTokenDoneTriggerScript(String setFirebaseTokenDoneTriggerScript) {
		this.setFirebaseTokenDoneTriggerScript = setFirebaseTokenDoneTriggerScript;
	}

	/**
	 * Firebaseデバイストークン登録完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param setFirebaseTokenDoneTriggerScript Firebaseデバイストークン登録完了時 に実行されるGS2-Script
	 * @return this
	 */
	public CreateGameRequest withSetFirebaseTokenDoneTriggerScript(String setFirebaseTokenDoneTriggerScript) {
		setSetFirebaseTokenDoneTriggerScript(setFirebaseTokenDoneTriggerScript);
		return this;
	}

}
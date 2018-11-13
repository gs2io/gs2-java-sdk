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
public class PublishRequest extends Gs2BasicRequest<PublishRequest> {

	public static class Constant extends Gs2InGamePushNotification.Constant {
		public static final String FUNCTION = "Publish";
	}

	/** ゲームの名前 */
	private String gameName;

	/** 通知の送信先ユーザID */
	private String userId;

	/** 件名 */
	private String subject;

	/** 本文 */
	private String body;

	/** 対象ユーザがオフラインの場合に転送を実行するか */
	private Boolean enableOfflineTransfer;

	/** Firebaseへの転送時に使用する通知音ファイル名 */
	private String offlineTransferSound;


	/**
	 * ゲームの名前を取得
	 *
	 * @return ゲームの名前
	 */
	public String getGameName() {
		return gameName;
	}

	/**
	 * ゲームの名前を設定
	 *
	 * @param gameName ゲームの名前
	 */
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	/**
	 * ゲームの名前を設定
	 *
	 * @param gameName ゲームの名前
	 * @return this
	 */
	public PublishRequest withGameName(String gameName) {
		setGameName(gameName);
		return this;
	}

	/**
	 * 通知の送信先ユーザIDを取得
	 *
	 * @return 通知の送信先ユーザID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 通知の送信先ユーザIDを設定
	 *
	 * @param userId 通知の送信先ユーザID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 通知の送信先ユーザIDを設定
	 *
	 * @param userId 通知の送信先ユーザID
	 * @return this
	 */
	public PublishRequest withUserId(String userId) {
		setUserId(userId);
		return this;
	}

	/**
	 * 件名を取得
	 *
	 * @return 件名
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * 件名を設定
	 *
	 * @param subject 件名
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * 件名を設定
	 *
	 * @param subject 件名
	 * @return this
	 */
	public PublishRequest withSubject(String subject) {
		setSubject(subject);
		return this;
	}

	/**
	 * 本文を取得
	 *
	 * @return 本文
	 */
	public String getBody() {
		return body;
	}

	/**
	 * 本文を設定
	 *
	 * @param body 本文
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * 本文を設定
	 *
	 * @param body 本文
	 * @return this
	 */
	public PublishRequest withBody(String body) {
		setBody(body);
		return this;
	}

	/**
	 * 対象ユーザがオフラインの場合に転送を実行するかを取得
	 *
	 * @return 対象ユーザがオフラインの場合に転送を実行するか
	 */
	public Boolean getEnableOfflineTransfer() {
		return enableOfflineTransfer;
	}

	/**
	 * 対象ユーザがオフラインの場合に転送を実行するかを設定
	 *
	 * @param enableOfflineTransfer 対象ユーザがオフラインの場合に転送を実行するか
	 */
	public void setEnableOfflineTransfer(Boolean enableOfflineTransfer) {
		this.enableOfflineTransfer = enableOfflineTransfer;
	}

	/**
	 * 対象ユーザがオフラインの場合に転送を実行するかを設定
	 *
	 * @param enableOfflineTransfer 対象ユーザがオフラインの場合に転送を実行するか
	 * @return this
	 */
	public PublishRequest withEnableOfflineTransfer(Boolean enableOfflineTransfer) {
		setEnableOfflineTransfer(enableOfflineTransfer);
		return this;
	}

	/**
	 * Firebaseへの転送時に使用する通知音ファイル名を取得
	 *
	 * @return Firebaseへの転送時に使用する通知音ファイル名
	 */
	public String getOfflineTransferSound() {
		return offlineTransferSound;
	}

	/**
	 * Firebaseへの転送時に使用する通知音ファイル名を設定
	 *
	 * @param offlineTransferSound Firebaseへの転送時に使用する通知音ファイル名
	 */
	public void setOfflineTransferSound(String offlineTransferSound) {
		this.offlineTransferSound = offlineTransferSound;
	}

	/**
	 * Firebaseへの転送時に使用する通知音ファイル名を設定
	 *
	 * @param offlineTransferSound Firebaseへの転送時に使用する通知音ファイル名
	 * @return this
	 */
	public PublishRequest withOfflineTransferSound(String offlineTransferSound) {
		setOfflineTransferSound(offlineTransferSound);
		return this;
	}

}
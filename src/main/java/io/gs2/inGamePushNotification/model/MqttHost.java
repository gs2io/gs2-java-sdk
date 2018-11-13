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

package io.gs2.inGamePushNotification.model;

import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * MQTTサーバ
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class MqttHost implements Serializable {

	/** ゲームGRN */
	private String gameId;

	/** ホスト名 */
	private String host;

	/** 待受ポート */
	private Integer port;

	/** ルート証明書 */
	private String rootCertificate;


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
	 * ホスト名を取得
	 *
	 * @return ホスト名
	 */
	public String getHost() {
		return host;
	}

	/**
	 * ホスト名を設定
	 *
	 * @param host ホスト名
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * 待受ポートを取得
	 *
	 * @return 待受ポート
	 */
	public Integer getPort() {
		return port;
	}

	/**
	 * 待受ポートを設定
	 *
	 * @param port 待受ポート
	 */
	public void setPort(Integer port) {
		this.port = port;
	}

	/**
	 * ルート証明書を取得
	 *
	 * @return ルート証明書
	 */
	public String getRootCertificate() {
		return rootCertificate;
	}

	/**
	 * ルート証明書を設定
	 *
	 * @param rootCertificate ルート証明書
	 */
	public void setRootCertificate(String rootCertificate) {
		this.rootCertificate = rootCertificate;
	}

}
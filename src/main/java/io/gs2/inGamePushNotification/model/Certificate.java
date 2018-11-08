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
 * クライアント証明書
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Certificate implements Serializable {

	/** 証明書ID */
	private String certificateId;

	/** クライアント証明書 */
	private String certificate;

	/** 秘密鍵 */
	private String privateKey;

	/** PFXフォーマットの秘密鍵 */
	private String pfx;


	/**
	 * 証明書IDを取得
	 *
	 * @return 証明書ID
	 */
	public String getCertificateId() {
		return certificateId;
	}

	/**
	 * 証明書IDを設定
	 *
	 * @param certificateId 証明書ID
	 */
	public void setCertificateId(String certificateId) {
		this.certificateId = certificateId;
	}

	/**
	 * クライアント証明書を取得
	 *
	 * @return クライアント証明書
	 */
	public String getCertificate() {
		return certificate;
	}

	/**
	 * クライアント証明書を設定
	 *
	 * @param certificate クライアント証明書
	 */
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	/**
	 * 秘密鍵を取得
	 *
	 * @return 秘密鍵
	 */
	public String getPrivateKey() {
		return privateKey;
	}

	/**
	 * 秘密鍵を設定
	 *
	 * @param privateKey 秘密鍵
	 */
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	/**
	 * PFXフォーマットの秘密鍵を取得
	 *
	 * @return PFXフォーマットの秘密鍵
	 */
	public String getPfx() {
		return pfx;
	}

	/**
	 * PFXフォーマットの秘密鍵を設定
	 *
	 * @param pfx PFXフォーマットの秘密鍵
	 */
	public void setPfx(String pfx) {
		this.pfx = pfx;
	}

}
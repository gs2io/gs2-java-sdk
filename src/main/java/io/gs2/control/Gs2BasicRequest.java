/*
 * Copyright 2016 Game Server Services, Inc. or its affiliates. All Rights
 * Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package io.gs2.control;

import java.io.Serializable;

@SuppressWarnings("serial")
abstract public class Gs2BasicRequest<T extends Gs2BasicRequest<?>> implements Serializable {

	/** GS2認証クライアントID */
	private String xGs2ClientId;
	/** タイムスタンプ */
	private Long xGs2Timestamp;
    /** GS2認証署名 */
    private String xGs2RequestSign;
    /** GS2リクエストID */
    private String xGs2RequestId;
	
	/**
	 * GS2認証クライアントIDを取得。
	 * 
	 * @return GS2認証クライアントID
	 */
	String getxGs2ClientId() {
		return xGs2ClientId;
	}

	/**
	 * GS2認証クライアントIDを設定。
	 * 通常は自動的に計算されるため、この値を設定する必要はありません。
	 * 
	 * @param xGs2ClientId GS2認証クライアントID
	 */
	@Deprecated
	void setxGs2ClientId(String xGs2ClientId) {
		this.xGs2ClientId = xGs2ClientId;
	}

	/**
	 * GS2認証クライアントIDを設定。
	 * 通常は自動的に計算されるため、この値を設定する必要はありません。
	 * 
	 * @param xGs2ClientId GS2認証クライアントID
	 */
	@Deprecated
	@SuppressWarnings("unchecked")
	T withxGs2ClientId(String xGs2ClientId) {
		setxGs2ClientId(xGs2ClientId);
		return (T)this;
	}

	/**
	 * タイムスタンプを取得。
	 * 
	 * @return タイムスタンプ
	 */
	Long getxGs2Timestamp() {
		return xGs2Timestamp;
	}

	/**
	 * タイムスタンプを設定。
	 * 通常は自動的に計算されるため、この値を設定する必要はありません。
	 * 
	 * @param xGs2Timestamp タイムスタンプ
	 */
	@Deprecated
	void setxGs2Timestamp(Long xGs2Timestamp) {
		this.xGs2Timestamp = xGs2Timestamp;
	}

	/**
	 * タイムスタンプを設定。
	 * 通常は自動的に計算されるため、この値を設定する必要はありません。
	 * 
	 * @param xGs2Timestamp タイムスタンプ
	 */
	@Deprecated
	@SuppressWarnings("unchecked")
	T withxGs2Timestamp(Long xGs2Timestamp) {
		setxGs2Timestamp(xGs2Timestamp);
		return (T)this;
	}

    /**
     * GS2認証署名を取得。
     *
     * @return GS2認証署名
     */
    String getxGs2RequestSign() {
        return xGs2RequestSign;
    }

    /**
     * GS2認証署名を設定。
     * 通常は自動的に計算されるため、この値を設定する必要はありません。
     *
     * @param xGs2RequestSign GS2認証署名
     */
    @Deprecated
    void setxGs2RequestSign(String xGs2RequestSign) {
        this.xGs2RequestSign = xGs2RequestSign;
    }

    /**
     * GS2認証署名を設定。
     * 通常は自動的に計算されるため、この値を設定する必要はありません。
     *
     * @param xGs2RequestSign GS2認証署名
     */
    @Deprecated
    @SuppressWarnings("unchecked")
    T withxGs2RequestSign(String xGs2RequestSign) {
        setxGs2RequestSign(xGs2RequestSign);
        return (T)this;
    }

    /**
     * GS2リクエストIDを取得。
     *
     * @return GS2リクエストID
     */
    public String getRequestId() {
        return xGs2RequestId;
    }

    /**
     * GS2リクエストIDを設定。
     *
     * @param xGs2RequestId GS2リクエストID
     */
    public void setRequestId(String xGs2RequestId) {
        this.xGs2RequestId = xGs2RequestId;
    }

    /**
     * GS2リクエストIDを設定。
     *
     * @param xGs2RequestId GS2リクエストID
     */
    @SuppressWarnings("unchecked")
    public T withRequestId(String xGs2RequestId) {
        setRequestId(xGs2RequestId);
        return (T)this;
    }

}

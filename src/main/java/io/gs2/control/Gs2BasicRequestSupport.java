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

public class Gs2BasicRequestSupport {

	/**
	 * GS2認証クライアントIDを取得。
	 * 
	 * @param request リクエストオブジェクト
	 * @return GS2認証クライアントID
	 */
	public static String getxGs2ClientId(Gs2BasicRequest<?> request) {
		return request.getxGs2ClientId();
	}

	/**
	 * GS2認証クライアントIDを設定。
	 * 通常は自動的に計算されるため、この値を設定する必要はありません。
	 * 
	 * @param request リクエストオブジェクト
	 * @param xGs2ClientId GS2認証クライアントID
	 */
	@SuppressWarnings("deprecation")
	public static void setxGs2ClientId(Gs2BasicRequest<?> request, String xGs2ClientId) {
		request.setxGs2ClientId(xGs2ClientId);
	}

	/**
	 * タイムスタンプを取得。
	 * 
	 * @param request リクエストオブジェクト
	 * @return タイムスタンプ
	 */
	public static Long getxGs2Timestamp(Gs2BasicRequest<?> request) {
		return request.getxGs2Timestamp();
	}

	/**
	 * タイムスタンプを設定。
	 * 通常は自動的に計算されるため、この値を設定する必要はありません。
	 * 
	 * @param request リクエストオブジェクト
	 * @param xGs2Timestamp タイムスタンプ
	 */
	@SuppressWarnings("deprecation")
	public static void setxGs2Timestamp(Gs2BasicRequest<?> request, Long xGs2Timestamp) {
		request.setxGs2Timestamp(xGs2Timestamp);
	}

	/**
	 * GS2認証署名を取得。
	 *
	 * @param request リクエストオブジェクト
	 * @return GS2認証署名
	 */
	public static String getxGs2RequestSign(Gs2BasicRequest<?> request) {
		return request.getxGs2RequestSign();
	}

	/**
	 * GS2認証署名を設定。
	 * 通常は自動的に計算されるため、この値を設定する必要はありません。
	 *
	 * @param request リクエストオブジェクト
	 * @param xGs2RequestSign GS2認証署名
	 */
	@SuppressWarnings("deprecation")
	public static void setxGs2RequestSign(Gs2BasicRequest<?> request, String xGs2RequestSign) {
		request.setxGs2RequestSign(xGs2RequestSign);
	}
}

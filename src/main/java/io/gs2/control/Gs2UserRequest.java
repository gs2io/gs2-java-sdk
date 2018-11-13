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

@SuppressWarnings("serial")
abstract public class Gs2UserRequest<T extends Gs2UserRequest<?>> extends Gs2BasicRequest<T> {

	/** アクセストークン */
	String accessToken;

	/**
	 * アクセストークンを取得。
	 * 
	 * @return アクセストークン
	 */
	public String getAccessToken() {
		return accessToken;
	}
	
	/**
	 * アクセストークンを設定。
	 * 
	 * @param accessToken アクセストークン
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	/**
	 * アクセストークンを設定。
	 * 
	 * @param accessToken アクセストークン
	 * @return this
	 */
	@SuppressWarnings("unchecked")
	public T withAccessToken(String accessToken) {
		setAccessToken(accessToken);
		return (T)this;
	}
}

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
 * 通知結果
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class PublishResponse implements Serializable {

	/** 通知に使用した方式 */
	private String type;

	/** 件名 */
	private String subject;

	/** 本文 */
	private String body;


	/**
	 * 通知に使用した方式を取得
	 *
	 * @return 通知に使用した方式
	 */
	public String getType() {
		return type;
	}

	/**
	 * 通知に使用した方式を設定
	 *
	 * @param type 通知に使用した方式
	 */
	public void setType(String type) {
		this.type = type;
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

}
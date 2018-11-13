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

package io.gs2.timer.model;

import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * タイマー
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Timer implements Serializable {

	/** タイマーGRN */
	private String timerId;

	/** オーナーID */
	private String ownerId;

	/** タイマープールGRN */
	private String timerPoolId;

	/** コールバックHTTPメソッド */
	private String callbackMethod;

	/** コールバックURL */
	private String callbackUrl;

	/** コールバックボディ(PUT/POSTのときのみ有効) */
	private String callbackBody;

	/** コールバック時間(エポック秒) */
	private Integer executeTime;

	/** 最大リトライ回数 */
	private Integer retryMax;

	/** 作成日時(エポック秒) */
	private Integer createAt;


	/**
	 * タイマーGRNを取得
	 *
	 * @return タイマーGRN
	 */
	public String getTimerId() {
		return timerId;
	}

	/**
	 * タイマーGRNを設定
	 *
	 * @param timerId タイマーGRN
	 */
	public void setTimerId(String timerId) {
		this.timerId = timerId;
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
	 * タイマープールGRNを取得
	 *
	 * @return タイマープールGRN
	 */
	public String getTimerPoolId() {
		return timerPoolId;
	}

	/**
	 * タイマープールGRNを設定
	 *
	 * @param timerPoolId タイマープールGRN
	 */
	public void setTimerPoolId(String timerPoolId) {
		this.timerPoolId = timerPoolId;
	}

	/**
	 * コールバックHTTPメソッドを取得
	 *
	 * @return コールバックHTTPメソッド
	 */
	public String getCallbackMethod() {
		return callbackMethod;
	}

	/**
	 * コールバックHTTPメソッドを設定
	 *
	 * @param callbackMethod コールバックHTTPメソッド
	 */
	public void setCallbackMethod(String callbackMethod) {
		this.callbackMethod = callbackMethod;
	}

	/**
	 * コールバックURLを取得
	 *
	 * @return コールバックURL
	 */
	public String getCallbackUrl() {
		return callbackUrl;
	}

	/**
	 * コールバックURLを設定
	 *
	 * @param callbackUrl コールバックURL
	 */
	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	/**
	 * コールバックボディ(PUT/POSTのときのみ有効)を取得
	 *
	 * @return コールバックボディ(PUT/POSTのときのみ有効)
	 */
	public String getCallbackBody() {
		return callbackBody;
	}

	/**
	 * コールバックボディ(PUT/POSTのときのみ有効)を設定
	 *
	 * @param callbackBody コールバックボディ(PUT/POSTのときのみ有効)
	 */
	public void setCallbackBody(String callbackBody) {
		this.callbackBody = callbackBody;
	}

	/**
	 * コールバック時間(エポック秒)を取得
	 *
	 * @return コールバック時間(エポック秒)
	 */
	public Integer getExecuteTime() {
		return executeTime;
	}

	/**
	 * コールバック時間(エポック秒)を設定
	 *
	 * @param executeTime コールバック時間(エポック秒)
	 */
	public void setExecuteTime(Integer executeTime) {
		this.executeTime = executeTime;
	}

	/**
	 * 最大リトライ回数を取得
	 *
	 * @return 最大リトライ回数
	 */
	public Integer getRetryMax() {
		return retryMax;
	}

	/**
	 * 最大リトライ回数を設定
	 *
	 * @param retryMax 最大リトライ回数
	 */
	public void setRetryMax(Integer retryMax) {
		this.retryMax = retryMax;
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

}
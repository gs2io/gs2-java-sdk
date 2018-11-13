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

package io.gs2.timer.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.timer.Gs2Timer;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateTimerRequest extends Gs2BasicRequest<CreateTimerRequest> {

	public static class Constant extends Gs2Timer.Constant {
		public static final String FUNCTION = "CreateTimer";
	}

	/** タイマープールの名前を指定します。 */
	private String timerPoolName;

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


	/**
	 * タイマープールの名前を指定します。を取得
	 *
	 * @return タイマープールの名前を指定します。
	 */
	public String getTimerPoolName() {
		return timerPoolName;
	}

	/**
	 * タイマープールの名前を指定します。を設定
	 *
	 * @param timerPoolName タイマープールの名前を指定します。
	 */
	public void setTimerPoolName(String timerPoolName) {
		this.timerPoolName = timerPoolName;
	}

	/**
	 * タイマープールの名前を指定します。を設定
	 *
	 * @param timerPoolName タイマープールの名前を指定します。
	 * @return this
	 */
	public CreateTimerRequest withTimerPoolName(String timerPoolName) {
		setTimerPoolName(timerPoolName);
		return this;
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
	 * コールバックHTTPメソッドを設定
	 *
	 * @param callbackMethod コールバックHTTPメソッド
	 * @return this
	 */
	public CreateTimerRequest withCallbackMethod(String callbackMethod) {
		setCallbackMethod(callbackMethod);
		return this;
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
	 * コールバックURLを設定
	 *
	 * @param callbackUrl コールバックURL
	 * @return this
	 */
	public CreateTimerRequest withCallbackUrl(String callbackUrl) {
		setCallbackUrl(callbackUrl);
		return this;
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
	 * コールバックボディ(PUT/POSTのときのみ有効)を設定
	 *
	 * @param callbackBody コールバックボディ(PUT/POSTのときのみ有効)
	 * @return this
	 */
	public CreateTimerRequest withCallbackBody(String callbackBody) {
		setCallbackBody(callbackBody);
		return this;
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
	 * コールバック時間(エポック秒)を設定
	 *
	 * @param executeTime コールバック時間(エポック秒)
	 * @return this
	 */
	public CreateTimerRequest withExecuteTime(Integer executeTime) {
		setExecuteTime(executeTime);
		return this;
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
	 * 最大リトライ回数を設定
	 *
	 * @param retryMax 最大リトライ回数
	 * @return this
	 */
	public CreateTimerRequest withRetryMax(Integer retryMax) {
		setRetryMax(retryMax);
		return this;
	}

}
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

package io.gs2.limit.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.limit.Gs2Limit;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class GetCounterRequest extends Gs2BasicRequest<GetCounterRequest> {

	public static class Constant extends Gs2Limit.Constant {
		public static final String FUNCTION = "GetCounter";
	}

	/** 回数制限の名前を指定します。 */
	private String limitName;

	/** ユーザIDを指定します。 */
	private String userId;

	/** カウンター名を指定します。 */
	private String counterName;


	/**
	 * 回数制限の名前を指定します。を取得
	 *
	 * @return 回数制限の名前を指定します。
	 */
	public String getLimitName() {
		return limitName;
	}

	/**
	 * 回数制限の名前を指定します。を設定
	 *
	 * @param limitName 回数制限の名前を指定します。
	 */
	public void setLimitName(String limitName) {
		this.limitName = limitName;
	}

	/**
	 * 回数制限の名前を指定します。を設定
	 *
	 * @param limitName 回数制限の名前を指定します。
	 * @return this
	 */
	public GetCounterRequest withLimitName(String limitName) {
		setLimitName(limitName);
		return this;
	}

	/**
	 * ユーザIDを指定します。を取得
	 *
	 * @return ユーザIDを指定します。
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * ユーザIDを指定します。を設定
	 *
	 * @param userId ユーザIDを指定します。
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * ユーザIDを指定します。を設定
	 *
	 * @param userId ユーザIDを指定します。
	 * @return this
	 */
	public GetCounterRequest withUserId(String userId) {
		setUserId(userId);
		return this;
	}

	/**
	 * カウンター名を指定します。を取得
	 *
	 * @return カウンター名を指定します。
	 */
	public String getCounterName() {
		return counterName;
	}

	/**
	 * カウンター名を指定します。を設定
	 *
	 * @param counterName カウンター名を指定します。
	 */
	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}

	/**
	 * カウンター名を指定します。を設定
	 *
	 * @param counterName カウンター名を指定します。
	 * @return this
	 */
	public GetCounterRequest withCounterName(String counterName) {
		setCounterName(counterName);
		return this;
	}

}
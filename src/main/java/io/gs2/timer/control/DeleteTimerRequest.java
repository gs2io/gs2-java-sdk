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
public class DeleteTimerRequest extends Gs2BasicRequest<DeleteTimerRequest> {

	public static class Constant extends Gs2Timer.Constant {
		public static final String FUNCTION = "DeleteTimer";
	}

	/** タイマープールの名前を指定します。 */
	private String timerPoolName;

	/** タイマーIDを指定します。 */
	private String timerId;


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
	public DeleteTimerRequest withTimerPoolName(String timerPoolName) {
		setTimerPoolName(timerPoolName);
		return this;
	}

	/**
	 * タイマーIDを指定します。を取得
	 *
	 * @return タイマーIDを指定します。
	 */
	public String getTimerId() {
		return timerId;
	}

	/**
	 * タイマーIDを指定します。を設定
	 *
	 * @param timerId タイマーIDを指定します。
	 */
	public void setTimerId(String timerId) {
		this.timerId = timerId;
	}

	/**
	 * タイマーIDを指定します。を設定
	 *
	 * @param timerId タイマーIDを指定します。
	 * @return this
	 */
	public DeleteTimerRequest withTimerId(String timerId) {
		setTimerId(timerId);
		return this;
	}

}
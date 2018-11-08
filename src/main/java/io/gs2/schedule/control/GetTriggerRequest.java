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

package io.gs2.schedule.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.schedule.model.*;
import io.gs2.schedule.Gs2Schedule;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class GetTriggerRequest extends Gs2BasicRequest<GetTriggerRequest> {

	public static class Constant extends Gs2Schedule.Constant {
		public static final String FUNCTION = "GetTrigger";
	}

	/** スケジュールの名前を指定します。 */
	private String scheduleName;

	/** ユーザIDを指定します。 */
	private String userId;

	/** トリガー名を指定します。 */
	private String triggerName;


	/**
	 * スケジュールの名前を指定します。を取得
	 *
	 * @return スケジュールの名前を指定します。
	 */
	public String getScheduleName() {
		return scheduleName;
	}

	/**
	 * スケジュールの名前を指定します。を設定
	 *
	 * @param scheduleName スケジュールの名前を指定します。
	 */
	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}

	/**
	 * スケジュールの名前を指定します。を設定
	 *
	 * @param scheduleName スケジュールの名前を指定します。
	 * @return this
	 */
	public GetTriggerRequest withScheduleName(String scheduleName) {
		setScheduleName(scheduleName);
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
	public GetTriggerRequest withUserId(String userId) {
		setUserId(userId);
		return this;
	}

	/**
	 * トリガー名を指定します。を取得
	 *
	 * @return トリガー名を指定します。
	 */
	public String getTriggerName() {
		return triggerName;
	}

	/**
	 * トリガー名を指定します。を設定
	 *
	 * @param triggerName トリガー名を指定します。
	 */
	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	/**
	 * トリガー名を指定します。を設定
	 *
	 * @param triggerName トリガー名を指定します。
	 * @return this
	 */
	public GetTriggerRequest withTriggerName(String triggerName) {
		setTriggerName(triggerName);
		return this;
	}

}
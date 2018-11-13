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
public class DeleteEventMasterRequest extends Gs2BasicRequest<DeleteEventMasterRequest> {

	public static class Constant extends Gs2Schedule.Constant {
		public static final String FUNCTION = "DeleteEventMaster";
	}

	/** スケジュールの名前を指定します。 */
	private String scheduleName;

	/** イベント名を指定します。 */
	private String eventName;


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
	public DeleteEventMasterRequest withScheduleName(String scheduleName) {
		setScheduleName(scheduleName);
		return this;
	}

	/**
	 * イベント名を指定します。を取得
	 *
	 * @return イベント名を指定します。
	 */
	public String getEventName() {
		return eventName;
	}

	/**
	 * イベント名を指定します。を設定
	 *
	 * @param eventName イベント名を指定します。
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	/**
	 * イベント名を指定します。を設定
	 *
	 * @param eventName イベント名を指定します。
	 * @return this
	 */
	public DeleteEventMasterRequest withEventName(String eventName) {
		setEventName(eventName);
		return this;
	}

}
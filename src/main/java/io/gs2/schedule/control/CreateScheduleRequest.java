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
public class CreateScheduleRequest extends Gs2BasicRequest<CreateScheduleRequest> {

	public static class Constant extends Gs2Schedule.Constant {
		public static final String FUNCTION = "CreateSchedule";
	}

	/** スケジュール名 */
	private String name;

	/** 説明文 */
	private String description;


	/**
	 * スケジュール名を取得
	 *
	 * @return スケジュール名
	 */
	public String getName() {
		return name;
	}

	/**
	 * スケジュール名を設定
	 *
	 * @param name スケジュール名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * スケジュール名を設定
	 *
	 * @param name スケジュール名
	 * @return this
	 */
	public CreateScheduleRequest withName(String name) {
		setName(name);
		return this;
	}

	/**
	 * 説明文を取得
	 *
	 * @return 説明文
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 説明文を設定
	 *
	 * @param description 説明文
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 説明文を設定
	 *
	 * @param description 説明文
	 * @return this
	 */
	public CreateScheduleRequest withDescription(String description) {
		setDescription(description);
		return this;
	}

}
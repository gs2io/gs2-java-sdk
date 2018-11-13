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
public class PullTriggerRequest extends Gs2BasicRequest<PullTriggerRequest> {

	public static class Constant extends Gs2Schedule.Constant {
		public static final String FUNCTION = "PullTrigger";
	}

	/** スケジュールの名前を指定します。 */
	private String scheduleName;

	/** ユーザIDを指定します。 */
	private String userId;

	/** トリガーの名前を指定します。 */
	private String triggerName;

	/** 既にトリガーが引かれていた時の振る舞い */
	private String action;

	/** action に if_expired_pull_again を指定したときに使用するトリガーの有効期間(分) */
	private Integer ttl;


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
	public PullTriggerRequest withScheduleName(String scheduleName) {
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
	public PullTriggerRequest withUserId(String userId) {
		setUserId(userId);
		return this;
	}

	/**
	 * トリガーの名前を指定します。を取得
	 *
	 * @return トリガーの名前を指定します。
	 */
	public String getTriggerName() {
		return triggerName;
	}

	/**
	 * トリガーの名前を指定します。を設定
	 *
	 * @param triggerName トリガーの名前を指定します。
	 */
	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	/**
	 * トリガーの名前を指定します。を設定
	 *
	 * @param triggerName トリガーの名前を指定します。
	 * @return this
	 */
	public PullTriggerRequest withTriggerName(String triggerName) {
		setTriggerName(triggerName);
		return this;
	}

	/**
	 * 既にトリガーが引かれていた時の振る舞いを取得
	 *
	 * @return 既にトリガーが引かれていた時の振る舞い
	 */
	public String getAction() {
		return action;
	}

	/**
	 * 既にトリガーが引かれていた時の振る舞いを設定
	 *
	 * @param action 既にトリガーが引かれていた時の振る舞い
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * 既にトリガーが引かれていた時の振る舞いを設定
	 *
	 * @param action 既にトリガーが引かれていた時の振る舞い
	 * @return this
	 */
	public PullTriggerRequest withAction(String action) {
		setAction(action);
		return this;
	}

	/**
	 * action に if_expired_pull_again を指定したときに使用するトリガーの有効期間(分)を取得
	 *
	 * @return action に if_expired_pull_again を指定したときに使用するトリガーの有効期間(分)
	 */
	public Integer getTtl() {
		return ttl;
	}

	/**
	 * action に if_expired_pull_again を指定したときに使用するトリガーの有効期間(分)を設定
	 *
	 * @param ttl action に if_expired_pull_again を指定したときに使用するトリガーの有効期間(分)
	 */
	public void setTtl(Integer ttl) {
		this.ttl = ttl;
	}

	/**
	 * action に if_expired_pull_again を指定したときに使用するトリガーの有効期間(分)を設定
	 *
	 * @param ttl action に if_expired_pull_again を指定したときに使用するトリガーの有効期間(分)
	 * @return this
	 */
	public PullTriggerRequest withTtl(Integer ttl) {
		setTtl(ttl);
		return this;
	}

}
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

package io.gs2.jobQueue.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.jobQueue.model.*;
import io.gs2.jobQueue.Gs2JobQueue;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class PushRequest extends Gs2BasicRequest<PushRequest> {

	public static class Constant extends Gs2JobQueue.Constant {
		public static final String FUNCTION = "Push";
	}

	/** ジョブキューの名前 */
	private String queueName;

	/** ユーザID */
	private String userId;

	/** 追加するジョブの情報 */
	private List<PushJob> jobs;


	/**
	 * ジョブキューの名前を取得
	 *
	 * @return ジョブキューの名前
	 */
	public String getQueueName() {
		return queueName;
	}

	/**
	 * ジョブキューの名前を設定
	 *
	 * @param queueName ジョブキューの名前
	 */
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	/**
	 * ジョブキューの名前を設定
	 *
	 * @param queueName ジョブキューの名前
	 * @return this
	 */
	public PushRequest withQueueName(String queueName) {
		setQueueName(queueName);
		return this;
	}

	/**
	 * ユーザIDを取得
	 *
	 * @return ユーザID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * ユーザIDを設定
	 *
	 * @param userId ユーザID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * ユーザIDを設定
	 *
	 * @param userId ユーザID
	 * @return this
	 */
	public PushRequest withUserId(String userId) {
		setUserId(userId);
		return this;
	}

	/**
	 * 追加するジョブの情報を取得
	 *
	 * @return 追加するジョブの情報
	 */
	public List<PushJob> getJobs() {
		return jobs;
	}

	/**
	 * 追加するジョブの情報を設定
	 *
	 * @param jobs 追加するジョブの情報
	 */
	public void setJobs(List<PushJob> jobs) {
		this.jobs = jobs;
	}

	/**
	 * 追加するジョブの情報を設定
	 *
	 * @param jobs 追加するジョブの情報
	 * @return this
	 */
	public PushRequest withJobs(List<PushJob> jobs) {
		setJobs(jobs);
		return this;
	}

}
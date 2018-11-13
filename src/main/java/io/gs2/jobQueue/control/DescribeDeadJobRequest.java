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
public class DescribeDeadJobRequest extends Gs2BasicRequest<DescribeDeadJobRequest> {

	public static class Constant extends Gs2JobQueue.Constant {
		public static final String FUNCTION = "DescribeDeadJob";
	}

	/** ジョブキューの名前を指定します。 */
	private String queueName;

	/** データの取得を開始する位置を指定するトークン */
	private String pageToken;

	/** データの取得件数 */
	private Integer limit;


	/**
	 * ジョブキューの名前を指定します。を取得
	 *
	 * @return ジョブキューの名前を指定します。
	 */
	public String getQueueName() {
		return queueName;
	}

	/**
	 * ジョブキューの名前を指定します。を設定
	 *
	 * @param queueName ジョブキューの名前を指定します。
	 */
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	/**
	 * ジョブキューの名前を指定します。を設定
	 *
	 * @param queueName ジョブキューの名前を指定します。
	 * @return this
	 */
	public DescribeDeadJobRequest withQueueName(String queueName) {
		setQueueName(queueName);
		return this;
	}

	/**
	 * データの取得を開始する位置を指定するトークンを取得
	 *
	 * @return データの取得を開始する位置を指定するトークン
	 */
	public String getPageToken() {
		return pageToken;
	}

	/**
	 * データの取得を開始する位置を指定するトークンを設定
	 *
	 * @param pageToken データの取得を開始する位置を指定するトークン
	 */
	public void setPageToken(String pageToken) {
		this.pageToken = pageToken;
	}

	/**
	 * データの取得を開始する位置を指定するトークンを設定
	 *
	 * @param pageToken データの取得を開始する位置を指定するトークン
	 * @return this
	 */
	public DescribeDeadJobRequest withPageToken(String pageToken) {
		setPageToken(pageToken);
		return this;
	}

	/**
	 * データの取得件数を取得
	 *
	 * @return データの取得件数
	 */
	public Integer getLimit() {
		return limit;
	}

	/**
	 * データの取得件数を設定
	 *
	 * @param limit データの取得件数
	 */
	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	/**
	 * データの取得件数を設定
	 *
	 * @param limit データの取得件数
	 * @return this
	 */
	public DescribeDeadJobRequest withLimit(Integer limit) {
		setLimit(limit);
		return this;
	}

}
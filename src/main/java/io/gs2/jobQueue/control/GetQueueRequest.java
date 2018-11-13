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
public class GetQueueRequest extends Gs2BasicRequest<GetQueueRequest> {

	public static class Constant extends Gs2JobQueue.Constant {
		public static final String FUNCTION = "GetQueue";
	}

	/** ジョブキューの名前を指定します。 */
	private String queueName;


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
	public GetQueueRequest withQueueName(String queueName) {
		setQueueName(queueName);
		return this;
	}

}
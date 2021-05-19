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

package io.gs2.jobQueue.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.core.model.*;
import io.gs2.jobQueue.model.*;

/**
 * ジョブを実行 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class RunResult implements IResult, Serializable {
	/** ジョブ */
	private Job item;
	/** ジョブの実行結果 */
	private JobResultBody result;
	/** None */
	private Boolean isLastJob;

	/**
	 * ジョブを取得
	 *
	 * @return ジョブを実行
	 */
	public Job getItem() {
		return item;
	}

	/**
	 * ジョブを設定
	 *
	 * @param item ジョブを実行
	 */
	public void setItem(Job item) {
		this.item = item;
	}

	/**
	 * ジョブの実行結果を取得
	 *
	 * @return ジョブを実行
	 */
	public JobResultBody getResult() {
		return result;
	}

	/**
	 * ジョブの実行結果を設定
	 *
	 * @param result ジョブを実行
	 */
	public void setResult(JobResultBody result) {
		this.result = result;
	}

	/**
	 * Noneを取得
	 *
	 * @return ジョブを実行
	 */
	public Boolean getIsLastJob() {
		return isLastJob;
	}

	/**
	 * Noneを設定
	 *
	 * @param isLastJob ジョブを実行
	 */
	public void setIsLastJob(Boolean isLastJob) {
		this.isLastJob = isLastJob;
	}
}
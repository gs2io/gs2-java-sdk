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

package io.gs2.jobQueue.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.model.IModel;

/**
 * ジョブ実行結果
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class JobResult implements IModel, Serializable, Comparable<JobResult> {
	/** ジョブ実行結果 */
	protected String jobResultId;

	/**
	 * ジョブ実行結果を取得
	 *
	 * @return ジョブ実行結果
	 */
	public String getJobResultId() {
		return jobResultId;
	}

	/**
	 * ジョブ実行結果を設定
	 *
	 * @param jobResultId ジョブ実行結果
	 */
	public void setJobResultId(String jobResultId) {
		this.jobResultId = jobResultId;
	}

	/**
	 * ジョブ実行結果を設定
	 *
	 * @param jobResultId ジョブ実行結果
	 * @return this
	 */
	public JobResult withJobResultId(String jobResultId) {
		this.jobResultId = jobResultId;
		return this;
	}
	/** ジョブ */
	protected String jobId;

	/**
	 * ジョブを取得
	 *
	 * @return ジョブ
	 */
	public String getJobId() {
		return jobId;
	}

	/**
	 * ジョブを設定
	 *
	 * @param jobId ジョブ
	 */
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	/**
	 * ジョブを設定
	 *
	 * @param jobId ジョブ
	 * @return this
	 */
	public JobResult withJobId(String jobId) {
		this.jobId = jobId;
		return this;
	}
	/** None */
	protected Integer tryNumber;

	/**
	 * Noneを取得
	 *
	 * @return None
	 */
	public Integer getTryNumber() {
		return tryNumber;
	}

	/**
	 * Noneを設定
	 *
	 * @param tryNumber None
	 */
	public void setTryNumber(Integer tryNumber) {
		this.tryNumber = tryNumber;
	}

	/**
	 * Noneを設定
	 *
	 * @param tryNumber None
	 * @return this
	 */
	public JobResult withTryNumber(Integer tryNumber) {
		this.tryNumber = tryNumber;
		return this;
	}
	/** None */
	protected Integer statusCode;

	/**
	 * Noneを取得
	 *
	 * @return None
	 */
	public Integer getStatusCode() {
		return statusCode;
	}

	/**
	 * Noneを設定
	 *
	 * @param statusCode None
	 */
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * Noneを設定
	 *
	 * @param statusCode None
	 * @return this
	 */
	public JobResult withStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
		return this;
	}
	/** レスポンスの内容 */
	protected String result;

	/**
	 * レスポンスの内容を取得
	 *
	 * @return レスポンスの内容
	 */
	public String getResult() {
		return result;
	}

	/**
	 * レスポンスの内容を設定
	 *
	 * @param result レスポンスの内容
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * レスポンスの内容を設定
	 *
	 * @param result レスポンスの内容
	 * @return this
	 */
	public JobResult withResult(String result) {
		this.result = result;
		return this;
	}
	/** 作成日時 */
	protected Long tryAt;

	/**
	 * 作成日時を取得
	 *
	 * @return 作成日時
	 */
	public Long getTryAt() {
		return tryAt;
	}

	/**
	 * 作成日時を設定
	 *
	 * @param tryAt 作成日時
	 */
	public void setTryAt(Long tryAt) {
		this.tryAt = tryAt;
	}

	/**
	 * 作成日時を設定
	 *
	 * @param tryAt 作成日時
	 * @return this
	 */
	public JobResult withTryAt(Long tryAt) {
		this.tryAt = tryAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("jobResultId", this.getJobResultId())
            .put("jobId", this.getJobId())
            .put("tryNumber", this.getTryNumber())
            .put("statusCode", this.getStatusCode())
            .put("result", this.getResult())
            .put("tryAt", this.getTryAt());
        return body_;
    }
	@Override
	public int compareTo(JobResult o) {
		return jobResultId.compareTo(o.jobResultId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.jobResultId == null) ? 0 : this.jobResultId.hashCode());
        result = prime * result + ((this.jobId == null) ? 0 : this.jobId.hashCode());
        result = prime * result + ((this.tryNumber == null) ? 0 : this.tryNumber.hashCode());
        result = prime * result + ((this.statusCode == null) ? 0 : this.statusCode.hashCode());
        result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
        result = prime * result + ((this.tryAt == null) ? 0 : this.tryAt.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		JobResult other = (JobResult) o;
		if (jobResultId == null) {
			return other.jobResultId == null;
		} else if (!jobResultId.equals(other.jobResultId)) {
			return false;
		}
		if (jobId == null) {
			return other.jobId == null;
		} else if (!jobId.equals(other.jobId)) {
			return false;
		}
		if (tryNumber == null) {
			return other.tryNumber == null;
		} else if (!tryNumber.equals(other.tryNumber)) {
			return false;
		}
		if (statusCode == null) {
			return other.statusCode == null;
		} else if (!statusCode.equals(other.statusCode)) {
			return false;
		}
		if (result == null) {
			return other.result == null;
		} else if (!result.equals(other.result)) {
			return false;
		}
		if (tryAt == null) {
			return other.tryAt == null;
		} else if (!tryAt.equals(other.tryAt)) {
			return false;
		}
		return true;
	}
}
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
 * ジョブ
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Job implements IModel, Serializable, Comparable<Job> {
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
	public Job withJobId(String jobId) {
		this.jobId = jobId;
		return this;
	}
	/** ジョブの名前 */
	protected String name;

	/**
	 * ジョブの名前を取得
	 *
	 * @return ジョブの名前
	 */
	public String getName() {
		return name;
	}

	/**
	 * ジョブの名前を設定
	 *
	 * @param name ジョブの名前
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ジョブの名前を設定
	 *
	 * @param name ジョブの名前
	 * @return this
	 */
	public Job withName(String name) {
		this.name = name;
		return this;
	}
	/** ユーザーID */
	protected String userId;

	/**
	 * ユーザーIDを取得
	 *
	 * @return ユーザーID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * ユーザーIDを設定
	 *
	 * @param userId ユーザーID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * ユーザーIDを設定
	 *
	 * @param userId ユーザーID
	 * @return this
	 */
	public Job withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	/** ジョブの実行に使用するスクリプト のGRN */
	protected String scriptId;

	/**
	 * ジョブの実行に使用するスクリプト のGRNを取得
	 *
	 * @return ジョブの実行に使用するスクリプト のGRN
	 */
	public String getScriptId() {
		return scriptId;
	}

	/**
	 * ジョブの実行に使用するスクリプト のGRNを設定
	 *
	 * @param scriptId ジョブの実行に使用するスクリプト のGRN
	 */
	public void setScriptId(String scriptId) {
		this.scriptId = scriptId;
	}

	/**
	 * ジョブの実行に使用するスクリプト のGRNを設定
	 *
	 * @param scriptId ジョブの実行に使用するスクリプト のGRN
	 * @return this
	 */
	public Job withScriptId(String scriptId) {
		this.scriptId = scriptId;
		return this;
	}
	/** 引数 */
	protected String args;

	/**
	 * 引数を取得
	 *
	 * @return 引数
	 */
	public String getArgs() {
		return args;
	}

	/**
	 * 引数を設定
	 *
	 * @param args 引数
	 */
	public void setArgs(String args) {
		this.args = args;
	}

	/**
	 * 引数を設定
	 *
	 * @param args 引数
	 * @return this
	 */
	public Job withArgs(String args) {
		this.args = args;
		return this;
	}
	/** 現在のリトライ回数 */
	protected Integer currentRetryCount;

	/**
	 * 現在のリトライ回数を取得
	 *
	 * @return 現在のリトライ回数
	 */
	public Integer getCurrentRetryCount() {
		return currentRetryCount;
	}

	/**
	 * 現在のリトライ回数を設定
	 *
	 * @param currentRetryCount 現在のリトライ回数
	 */
	public void setCurrentRetryCount(Integer currentRetryCount) {
		this.currentRetryCount = currentRetryCount;
	}

	/**
	 * 現在のリトライ回数を設定
	 *
	 * @param currentRetryCount 現在のリトライ回数
	 * @return this
	 */
	public Job withCurrentRetryCount(Integer currentRetryCount) {
		this.currentRetryCount = currentRetryCount;
		return this;
	}
	/** 最大試行回数 */
	protected Integer maxTryCount;

	/**
	 * 最大試行回数を取得
	 *
	 * @return 最大試行回数
	 */
	public Integer getMaxTryCount() {
		return maxTryCount;
	}

	/**
	 * 最大試行回数を設定
	 *
	 * @param maxTryCount 最大試行回数
	 */
	public void setMaxTryCount(Integer maxTryCount) {
		this.maxTryCount = maxTryCount;
	}

	/**
	 * 最大試行回数を設定
	 *
	 * @param maxTryCount 最大試行回数
	 * @return this
	 */
	public Job withMaxTryCount(Integer maxTryCount) {
		this.maxTryCount = maxTryCount;
		return this;
	}
	/** ソート用インデックス(現在時刻(ミリ秒).登録時のインデックス) */
	protected Double index;

	/**
	 * ソート用インデックス(現在時刻(ミリ秒).登録時のインデックス)を取得
	 *
	 * @return ソート用インデックス(現在時刻(ミリ秒).登録時のインデックス)
	 */
	public Double getIndex() {
		return index;
	}

	/**
	 * ソート用インデックス(現在時刻(ミリ秒).登録時のインデックス)を設定
	 *
	 * @param index ソート用インデックス(現在時刻(ミリ秒).登録時のインデックス)
	 */
	public void setIndex(Double index) {
		this.index = index;
	}

	/**
	 * ソート用インデックス(現在時刻(ミリ秒).登録時のインデックス)を設定
	 *
	 * @param index ソート用インデックス(現在時刻(ミリ秒).登録時のインデックス)
	 * @return this
	 */
	public Job withIndex(Double index) {
		this.index = index;
		return this;
	}
	/** 作成日時 */
	protected Long createdAt;

	/**
	 * 作成日時を取得
	 *
	 * @return 作成日時
	 */
	public Long getCreatedAt() {
		return createdAt;
	}

	/**
	 * 作成日時を設定
	 *
	 * @param createdAt 作成日時
	 */
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * 作成日時を設定
	 *
	 * @param createdAt 作成日時
	 * @return this
	 */
	public Job withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	/** 最終更新日時 */
	protected Long updatedAt;

	/**
	 * 最終更新日時を取得
	 *
	 * @return 最終更新日時
	 */
	public Long getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * 最終更新日時を設定
	 *
	 * @param updatedAt 最終更新日時
	 */
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * 最終更新日時を設定
	 *
	 * @param updatedAt 最終更新日時
	 * @return this
	 */
	public Job withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("jobId", this.getJobId())
            .put("name", this.getName())
            .put("userId", this.getUserId())
            .put("scriptId", this.getScriptId())
            .put("args", this.getArgs())
            .put("currentRetryCount", this.getCurrentRetryCount())
            .put("maxTryCount", this.getMaxTryCount())
            .put("index", this.getIndex())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        return body_;
    }
	@Override
	public int compareTo(Job o) {
		return jobId.compareTo(o.jobId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.jobId == null) ? 0 : this.jobId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.scriptId == null) ? 0 : this.scriptId.hashCode());
        result = prime * result + ((this.args == null) ? 0 : this.args.hashCode());
        result = prime * result + ((this.currentRetryCount == null) ? 0 : this.currentRetryCount.hashCode());
        result = prime * result + ((this.maxTryCount == null) ? 0 : this.maxTryCount.hashCode());
        result = prime * result + ((this.index == null) ? 0 : this.index.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
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
		Job other = (Job) o;
		if (jobId == null) {
			return other.jobId == null;
		} else if (!jobId.equals(other.jobId)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (scriptId == null) {
			return other.scriptId == null;
		} else if (!scriptId.equals(other.scriptId)) {
			return false;
		}
		if (args == null) {
			return other.args == null;
		} else if (!args.equals(other.args)) {
			return false;
		}
		if (currentRetryCount == null) {
			return other.currentRetryCount == null;
		} else if (!currentRetryCount.equals(other.currentRetryCount)) {
			return false;
		}
		if (maxTryCount == null) {
			return other.maxTryCount == null;
		} else if (!maxTryCount.equals(other.maxTryCount)) {
			return false;
		}
		if (index == null) {
			return other.index == null;
		} else if (!index.equals(other.index)) {
			return false;
		}
		if (createdAt == null) {
			return other.createdAt == null;
		} else if (!createdAt.equals(other.createdAt)) {
			return false;
		}
		if (updatedAt == null) {
			return other.updatedAt == null;
		} else if (!updatedAt.equals(other.updatedAt)) {
			return false;
		}
		return true;
	}
}
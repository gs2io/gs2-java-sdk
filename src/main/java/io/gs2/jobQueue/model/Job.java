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

import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * ジョブ
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Job implements Serializable {

	/** ジョブID */
	private String jobId;

	/** キューGRN */
	private String queueId;

	/** オーナーID */
	private String userId;

	/** スクリプト名 */
	private String scriptName;

	/** 引数 */
	private String args;

	/** 現在のリトライ回数 */
	private Integer currentRetry;

	/** 最大リトライ回数 */
	private Integer maxRetry;

	/** 作成日時 */
	private Integer createAt;


	/**
	 * ジョブIDを取得
	 *
	 * @return ジョブID
	 */
	public String getJobId() {
		return jobId;
	}

	/**
	 * ジョブIDを設定
	 *
	 * @param jobId ジョブID
	 */
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	/**
	 * ジョブIDを設定
	 *
	 * @param jobId ジョブID
	 * @return this
	 */
	public Job withJobId(String jobId) {
		this.jobId = jobId;
		return this;
	}

	/**
	 * キューGRNを取得
	 *
	 * @return キューGRN
	 */
	public String getQueueId() {
		return queueId;
	}

	/**
	 * キューGRNを設定
	 *
	 * @param queueId キューGRN
	 */
	public void setQueueId(String queueId) {
		this.queueId = queueId;
	}

	/**
	 * キューGRNを設定
	 *
	 * @param queueId キューGRN
	 * @return this
	 */
	public Job withQueueId(String queueId) {
		this.queueId = queueId;
		return this;
	}

	/**
	 * オーナーIDを取得
	 *
	 * @return オーナーID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * オーナーIDを設定
	 *
	 * @param userId オーナーID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * オーナーIDを設定
	 *
	 * @param userId オーナーID
	 * @return this
	 */
	public Job withUserId(String userId) {
		this.userId = userId;
		return this;
	}

	/**
	 * スクリプト名を取得
	 *
	 * @return スクリプト名
	 */
	public String getScriptName() {
		return scriptName;
	}

	/**
	 * スクリプト名を設定
	 *
	 * @param scriptName スクリプト名
	 */
	public void setScriptName(String scriptName) {
		this.scriptName = scriptName;
	}

	/**
	 * スクリプト名を設定
	 *
	 * @param scriptName スクリプト名
	 * @return this
	 */
	public Job withScriptName(String scriptName) {
		this.scriptName = scriptName;
		return this;
	}

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

	/**
	 * 現在のリトライ回数を取得
	 *
	 * @return 現在のリトライ回数
	 */
	public Integer getCurrentRetry() {
		return currentRetry;
	}

	/**
	 * 現在のリトライ回数を設定
	 *
	 * @param currentRetry 現在のリトライ回数
	 */
	public void setCurrentRetry(Integer currentRetry) {
		this.currentRetry = currentRetry;
	}

	/**
	 * 現在のリトライ回数を設定
	 *
	 * @param currentRetry 現在のリトライ回数
	 * @return this
	 */
	public Job withCurrentRetry(Integer currentRetry) {
		this.currentRetry = currentRetry;
		return this;
	}

	/**
	 * 最大リトライ回数を取得
	 *
	 * @return 最大リトライ回数
	 */
	public Integer getMaxRetry() {
		return maxRetry;
	}

	/**
	 * 最大リトライ回数を設定
	 *
	 * @param maxRetry 最大リトライ回数
	 */
	public void setMaxRetry(Integer maxRetry) {
		this.maxRetry = maxRetry;
	}

	/**
	 * 最大リトライ回数を設定
	 *
	 * @param maxRetry 最大リトライ回数
	 * @return this
	 */
	public Job withMaxRetry(Integer maxRetry) {
		this.maxRetry = maxRetry;
		return this;
	}

	/**
	 * 作成日時を取得
	 *
	 * @return 作成日時
	 */
	public Integer getCreateAt() {
		return createAt;
	}

	/**
	 * 作成日時を設定
	 *
	 * @param createAt 作成日時
	 */
	public void setCreateAt(Integer createAt) {
		this.createAt = createAt;
	}

	/**
	 * 作成日時を設定
	 *
	 * @param createAt 作成日時
	 * @return this
	 */
	public Job withCreateAt(Integer createAt) {
		this.createAt = createAt;
		return this;
	}


    public ObjectNode toJson() {
		return JsonNodeFactory.instance.objectNode()

            .put("jobId", this.getJobId())
            .put("queueId", this.getQueueId())
            .put("userId", this.getUserId())
            .put("scriptName", this.getScriptName())
            .put("args", this.getArgs())
            .put("currentRetry", this.getCurrentRetry())
            .put("maxRetry", this.getMaxRetry())
            .put("createAt", this.getCreateAt());
    }
}
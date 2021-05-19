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
import io.gs2.core.model.IModel;

/**
 * ジョブ
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class JobEntry implements IModel, Serializable {
	/** スクリプト のGRN */
	protected String scriptId;

	/**
	 * スクリプト のGRNを取得
	 *
	 * @return スクリプト のGRN
	 */
	public String getScriptId() {
		return scriptId;
	}

	/**
	 * スクリプト のGRNを設定
	 *
	 * @param scriptId スクリプト のGRN
	 */
	public void setScriptId(String scriptId) {
		this.scriptId = scriptId;
	}

	/**
	 * スクリプト のGRNを設定
	 *
	 * @param scriptId スクリプト のGRN
	 * @return this
	 */
	public JobEntry withScriptId(String scriptId) {
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
	public JobEntry withArgs(String args) {
		this.args = args;
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
	public JobEntry withMaxTryCount(Integer maxTryCount) {
		this.maxTryCount = maxTryCount;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("scriptId", this.getScriptId())
            .put("args", this.getArgs())
            .put("maxTryCount", this.getMaxTryCount());
        return body_;
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.scriptId == null) ? 0 : this.scriptId.hashCode());
        result = prime * result + ((this.args == null) ? 0 : this.args.hashCode());
        result = prime * result + ((this.maxTryCount == null) ? 0 : this.maxTryCount.hashCode());
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
		JobEntry other = (JobEntry) o;
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
		if (maxTryCount == null) {
			return other.maxTryCount == null;
		} else if (!maxTryCount.equals(other.maxTryCount)) {
			return false;
		}
		return true;
	}
}
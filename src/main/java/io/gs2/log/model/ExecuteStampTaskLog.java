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

package io.gs2.log.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * スタンプタスク実行ログ
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class ExecuteStampTaskLog implements IModel, Serializable {
	/** 日時 */
	protected Long timestamp;

	/**
	 * 日時を取得
	 *
	 * @return 日時
	 */
	public Long getTimestamp() {
		return timestamp;
	}

	/**
	 * 日時を設定
	 *
	 * @param timestamp 日時
	 */
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * 日時を設定
	 *
	 * @param timestamp 日時
	 * @return this
	 */
	public ExecuteStampTaskLog withTimestamp(Long timestamp) {
		this.timestamp = timestamp;
		return this;
	}
	/** タスクID */
	protected String taskId;

	/**
	 * タスクIDを取得
	 *
	 * @return タスクID
	 */
	public String getTaskId() {
		return taskId;
	}

	/**
	 * タスクIDを設定
	 *
	 * @param taskId タスクID
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	/**
	 * タスクIDを設定
	 *
	 * @param taskId タスクID
	 * @return this
	 */
	public ExecuteStampTaskLog withTaskId(String taskId) {
		this.taskId = taskId;
		return this;
	}
	/** マイクロサービスの種類 */
	protected String service;

	/**
	 * マイクロサービスの種類を取得
	 *
	 * @return マイクロサービスの種類
	 */
	public String getService() {
		return service;
	}

	/**
	 * マイクロサービスの種類を設定
	 *
	 * @param service マイクロサービスの種類
	 */
	public void setService(String service) {
		this.service = service;
	}

	/**
	 * マイクロサービスの種類を設定
	 *
	 * @param service マイクロサービスの種類
	 * @return this
	 */
	public ExecuteStampTaskLog withService(String service) {
		this.service = service;
		return this;
	}
	/** マイクロサービスのメソッド */
	protected String method;

	/**
	 * マイクロサービスのメソッドを取得
	 *
	 * @return マイクロサービスのメソッド
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * マイクロサービスのメソッドを設定
	 *
	 * @param method マイクロサービスのメソッド
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * マイクロサービスのメソッドを設定
	 *
	 * @param method マイクロサービスのメソッド
	 * @return this
	 */
	public ExecuteStampTaskLog withMethod(String method) {
		this.method = method;
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
	public ExecuteStampTaskLog withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	/** 報酬アクション */
	protected String action;

	/**
	 * 報酬アクションを取得
	 *
	 * @return 報酬アクション
	 */
	public String getAction() {
		return action;
	}

	/**
	 * 報酬アクションを設定
	 *
	 * @param action 報酬アクション
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * 報酬アクションを設定
	 *
	 * @param action 報酬アクション
	 * @return this
	 */
	public ExecuteStampTaskLog withAction(String action) {
		this.action = action;
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
	public ExecuteStampTaskLog withArgs(String args) {
		this.args = args;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("timestamp", this.getTimestamp())
            .put("taskId", this.getTaskId())
            .put("service", this.getService())
            .put("method", this.getMethod())
            .put("userId", this.getUserId())
            .put("action", this.getAction())
            .put("args", this.getArgs());
        return body_;
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.timestamp == null) ? 0 : this.timestamp.hashCode());
        result = prime * result + ((this.taskId == null) ? 0 : this.taskId.hashCode());
        result = prime * result + ((this.service == null) ? 0 : this.service.hashCode());
        result = prime * result + ((this.method == null) ? 0 : this.method.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.action == null) ? 0 : this.action.hashCode());
        result = prime * result + ((this.args == null) ? 0 : this.args.hashCode());
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
		ExecuteStampTaskLog other = (ExecuteStampTaskLog) o;
		if (timestamp == null) {
			return other.timestamp == null;
		} else if (!timestamp.equals(other.timestamp)) {
			return false;
		}
		if (taskId == null) {
			return other.taskId == null;
		} else if (!taskId.equals(other.taskId)) {
			return false;
		}
		if (service == null) {
			return other.service == null;
		} else if (!service.equals(other.service)) {
			return false;
		}
		if (method == null) {
			return other.method == null;
		} else if (!method.equals(other.method)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (action == null) {
			return other.action == null;
		} else if (!action.equals(other.action)) {
			return false;
		}
		if (args == null) {
			return other.args == null;
		} else if (!args.equals(other.args)) {
			return false;
		}
		return true;
	}
}
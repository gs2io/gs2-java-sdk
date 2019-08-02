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
 * デッドレタージョブ
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class DeadLetterJob implements IModel, Serializable, Comparable<DeadLetterJob> {
	/** デッドレタージョブ */
	protected String deadLetterJobId;

	/**
	 * デッドレタージョブを取得
	 *
	 * @return デッドレタージョブ
	 */
	public String getDeadLetterJobId() {
		return deadLetterJobId;
	}

	/**
	 * デッドレタージョブを設定
	 *
	 * @param deadLetterJobId デッドレタージョブ
	 */
	public void setDeadLetterJobId(String deadLetterJobId) {
		this.deadLetterJobId = deadLetterJobId;
	}

	/**
	 * デッドレタージョブを設定
	 *
	 * @param deadLetterJobId デッドレタージョブ
	 * @return this
	 */
	public DeadLetterJob withDeadLetterJobId(String deadLetterJobId) {
		this.deadLetterJobId = deadLetterJobId;
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
	public DeadLetterJob withName(String name) {
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
	public DeadLetterJob withUserId(String userId) {
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
	public DeadLetterJob withScriptId(String scriptId) {
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
	public DeadLetterJob withArgs(String args) {
		this.args = args;
		return this;
	}
	/** ジョブ実行結果 */
	protected List<JobResultBody> result;

	/**
	 * ジョブ実行結果を取得
	 *
	 * @return ジョブ実行結果
	 */
	public List<JobResultBody> getResult() {
		return result;
	}

	/**
	 * ジョブ実行結果を設定
	 *
	 * @param result ジョブ実行結果
	 */
	public void setResult(List<JobResultBody> result) {
		this.result = result;
	}

	/**
	 * ジョブ実行結果を設定
	 *
	 * @param result ジョブ実行結果
	 * @return this
	 */
	public DeadLetterJob withResult(List<JobResultBody> result) {
		this.result = result;
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
	public DeadLetterJob withCreatedAt(Long createdAt) {
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
	public DeadLetterJob withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> result = new ArrayList<>();
        if(this.result != null) {
            for(JobResultBody item : this.result) {
                result.add(item.toJson());
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("deadLetterJobId", this.getDeadLetterJobId())
            .put("name", this.getName())
            .put("userId", this.getUserId())
            .put("scriptId", this.getScriptId())
            .put("args", this.getArgs())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        body_.set("result", JsonNodeFactory.instance.arrayNode().addAll(result));
        return body_;
    }
	@Override
	public int compareTo(DeadLetterJob o) {
		return deadLetterJobId.compareTo(o.deadLetterJobId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.deadLetterJobId == null) ? 0 : this.deadLetterJobId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.scriptId == null) ? 0 : this.scriptId.hashCode());
        result = prime * result + ((this.args == null) ? 0 : this.args.hashCode());
        result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
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
		DeadLetterJob other = (DeadLetterJob) o;
		if (deadLetterJobId == null) {
			return other.deadLetterJobId == null;
		} else if (!deadLetterJobId.equals(other.deadLetterJobId)) {
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
		if (result == null) {
			return other.result == null;
		} else if (!result.equals(other.result)) {
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
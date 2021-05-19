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

package io.gs2.deploy.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * 作成中のリソース
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class WorkingResource implements IModel, Serializable, Comparable<WorkingResource> {
	/** 作成中のリソース */
	protected String resourceId;

	/**
	 * 作成中のリソースを取得
	 *
	 * @return 作成中のリソース
	 */
	public String getResourceId() {
		return resourceId;
	}

	/**
	 * 作成中のリソースを設定
	 *
	 * @param resourceId 作成中のリソース
	 */
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	/**
	 * 作成中のリソースを設定
	 *
	 * @param resourceId 作成中のリソース
	 * @return this
	 */
	public WorkingResource withResourceId(String resourceId) {
		this.resourceId = resourceId;
		return this;
	}
	/** 操作の種類 */
	protected String context;

	/**
	 * 操作の種類を取得
	 *
	 * @return 操作の種類
	 */
	public String getContext() {
		return context;
	}

	/**
	 * 操作の種類を設定
	 *
	 * @param context 操作の種類
	 */
	public void setContext(String context) {
		this.context = context;
	}

	/**
	 * 操作の種類を設定
	 *
	 * @param context 操作の種類
	 * @return this
	 */
	public WorkingResource withContext(String context) {
		this.context = context;
		return this;
	}
	/** 操作対象のリソース */
	protected String type;

	/**
	 * 操作対象のリソースを取得
	 *
	 * @return 操作対象のリソース
	 */
	public String getType() {
		return type;
	}

	/**
	 * 操作対象のリソースを設定
	 *
	 * @param type 操作対象のリソース
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 操作対象のリソースを設定
	 *
	 * @param type 操作対象のリソース
	 * @return this
	 */
	public WorkingResource withType(String type) {
		this.type = type;
		return this;
	}
	/** 作成中のリソース名 */
	protected String name;

	/**
	 * 作成中のリソース名を取得
	 *
	 * @return 作成中のリソース名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 作成中のリソース名を設定
	 *
	 * @param name 作成中のリソース名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 作成中のリソース名を設定
	 *
	 * @param name 作成中のリソース名
	 * @return this
	 */
	public WorkingResource withName(String name) {
		this.name = name;
		return this;
	}
	/** リクエストパラメータ */
	protected String request;

	/**
	 * リクエストパラメータを取得
	 *
	 * @return リクエストパラメータ
	 */
	public String getRequest() {
		return request;
	}

	/**
	 * リクエストパラメータを設定
	 *
	 * @param request リクエストパラメータ
	 */
	public void setRequest(String request) {
		this.request = request;
	}

	/**
	 * リクエストパラメータを設定
	 *
	 * @param request リクエストパラメータ
	 * @return this
	 */
	public WorkingResource withRequest(String request) {
		this.request = request;
		return this;
	}
	/** 依存しているリソースの名前 */
	protected List<String> after;

	/**
	 * 依存しているリソースの名前を取得
	 *
	 * @return 依存しているリソースの名前
	 */
	public List<String> getAfter() {
		return after;
	}

	/**
	 * 依存しているリソースの名前を設定
	 *
	 * @param after 依存しているリソースの名前
	 */
	public void setAfter(List<String> after) {
		this.after = after;
	}

	/**
	 * 依存しているリソースの名前を設定
	 *
	 * @param after 依存しているリソースの名前
	 * @return this
	 */
	public WorkingResource withAfter(List<String> after) {
		this.after = after;
		return this;
	}
	/** ロールバック操作の種類 */
	protected String rollbackContext;

	/**
	 * ロールバック操作の種類を取得
	 *
	 * @return ロールバック操作の種類
	 */
	public String getRollbackContext() {
		return rollbackContext;
	}

	/**
	 * ロールバック操作の種類を設定
	 *
	 * @param rollbackContext ロールバック操作の種類
	 */
	public void setRollbackContext(String rollbackContext) {
		this.rollbackContext = rollbackContext;
	}

	/**
	 * ロールバック操作の種類を設定
	 *
	 * @param rollbackContext ロールバック操作の種類
	 * @return this
	 */
	public WorkingResource withRollbackContext(String rollbackContext) {
		this.rollbackContext = rollbackContext;
		return this;
	}
	/** ロールバック用のリクエストパラメータ */
	protected String rollbackRequest;

	/**
	 * ロールバック用のリクエストパラメータを取得
	 *
	 * @return ロールバック用のリクエストパラメータ
	 */
	public String getRollbackRequest() {
		return rollbackRequest;
	}

	/**
	 * ロールバック用のリクエストパラメータを設定
	 *
	 * @param rollbackRequest ロールバック用のリクエストパラメータ
	 */
	public void setRollbackRequest(String rollbackRequest) {
		this.rollbackRequest = rollbackRequest;
	}

	/**
	 * ロールバック用のリクエストパラメータを設定
	 *
	 * @param rollbackRequest ロールバック用のリクエストパラメータ
	 * @return this
	 */
	public WorkingResource withRollbackRequest(String rollbackRequest) {
		this.rollbackRequest = rollbackRequest;
		return this;
	}
	/** ロールバック時に依存しているリソースの名前 */
	protected List<String> rollbackAfter;

	/**
	 * ロールバック時に依存しているリソースの名前を取得
	 *
	 * @return ロールバック時に依存しているリソースの名前
	 */
	public List<String> getRollbackAfter() {
		return rollbackAfter;
	}

	/**
	 * ロールバック時に依存しているリソースの名前を設定
	 *
	 * @param rollbackAfter ロールバック時に依存しているリソースの名前
	 */
	public void setRollbackAfter(List<String> rollbackAfter) {
		this.rollbackAfter = rollbackAfter;
	}

	/**
	 * ロールバック時に依存しているリソースの名前を設定
	 *
	 * @param rollbackAfter ロールバック時に依存しているリソースの名前
	 * @return this
	 */
	public WorkingResource withRollbackAfter(List<String> rollbackAfter) {
		this.rollbackAfter = rollbackAfter;
		return this;
	}
	/** リソースを作成したときに Output に記録するフィールド */
	protected List<OutputField> outputFields;

	/**
	 * リソースを作成したときに Output に記録するフィールドを取得
	 *
	 * @return リソースを作成したときに Output に記録するフィールド
	 */
	public List<OutputField> getOutputFields() {
		return outputFields;
	}

	/**
	 * リソースを作成したときに Output に記録するフィールドを設定
	 *
	 * @param outputFields リソースを作成したときに Output に記録するフィールド
	 */
	public void setOutputFields(List<OutputField> outputFields) {
		this.outputFields = outputFields;
	}

	/**
	 * リソースを作成したときに Output に記録するフィールドを設定
	 *
	 * @param outputFields リソースを作成したときに Output に記録するフィールド
	 * @return this
	 */
	public WorkingResource withOutputFields(List<OutputField> outputFields) {
		this.outputFields = outputFields;
		return this;
	}
	/** 実行に対して割り振られる一意な ID */
	protected String workId;

	/**
	 * 実行に対して割り振られる一意な IDを取得
	 *
	 * @return 実行に対して割り振られる一意な ID
	 */
	public String getWorkId() {
		return workId;
	}

	/**
	 * 実行に対して割り振られる一意な IDを設定
	 *
	 * @param workId 実行に対して割り振られる一意な ID
	 */
	public void setWorkId(String workId) {
		this.workId = workId;
	}

	/**
	 * 実行に対して割り振られる一意な IDを設定
	 *
	 * @param workId 実行に対して割り振られる一意な ID
	 * @return this
	 */
	public WorkingResource withWorkId(String workId) {
		this.workId = workId;
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
	public WorkingResource withCreatedAt(Long createdAt) {
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
	public WorkingResource withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> after = new ArrayList<>();
        if(this.after != null) {
            for(String item : this.after) {
                after.add(JsonNodeFactory.instance.textNode(item));
            }
        }
        List<JsonNode> rollbackAfter = new ArrayList<>();
        if(this.rollbackAfter != null) {
            for(String item : this.rollbackAfter) {
                rollbackAfter.add(JsonNodeFactory.instance.textNode(item));
            }
        }
        List<JsonNode> outputFields = new ArrayList<>();
        if(this.outputFields != null) {
            for(OutputField item : this.outputFields) {
                outputFields.add(item.toJson());
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("resourceId", this.getResourceId())
            .put("context", this.getContext())
            .put("type", this.getType())
            .put("name", this.getName())
            .put("request", this.getRequest())
            .put("rollbackContext", this.getRollbackContext())
            .put("rollbackRequest", this.getRollbackRequest())
            .put("workId", this.getWorkId())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        body_.set("after", JsonNodeFactory.instance.arrayNode().addAll(after));
        body_.set("rollbackAfter", JsonNodeFactory.instance.arrayNode().addAll(rollbackAfter));
        body_.set("outputFields", JsonNodeFactory.instance.arrayNode().addAll(outputFields));
        return body_;
    }
	@Override
	public int compareTo(WorkingResource o) {
		return resourceId.compareTo(o.resourceId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.resourceId == null) ? 0 : this.resourceId.hashCode());
        result = prime * result + ((this.context == null) ? 0 : this.context.hashCode());
        result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.request == null) ? 0 : this.request.hashCode());
        result = prime * result + ((this.after == null) ? 0 : this.after.hashCode());
        result = prime * result + ((this.rollbackContext == null) ? 0 : this.rollbackContext.hashCode());
        result = prime * result + ((this.rollbackRequest == null) ? 0 : this.rollbackRequest.hashCode());
        result = prime * result + ((this.rollbackAfter == null) ? 0 : this.rollbackAfter.hashCode());
        result = prime * result + ((this.outputFields == null) ? 0 : this.outputFields.hashCode());
        result = prime * result + ((this.workId == null) ? 0 : this.workId.hashCode());
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
		WorkingResource other = (WorkingResource) o;
		if (resourceId == null) {
			return other.resourceId == null;
		} else if (!resourceId.equals(other.resourceId)) {
			return false;
		}
		if (context == null) {
			return other.context == null;
		} else if (!context.equals(other.context)) {
			return false;
		}
		if (type == null) {
			return other.type == null;
		} else if (!type.equals(other.type)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (request == null) {
			return other.request == null;
		} else if (!request.equals(other.request)) {
			return false;
		}
		if (after == null) {
			return other.after == null;
		} else if (!after.equals(other.after)) {
			return false;
		}
		if (rollbackContext == null) {
			return other.rollbackContext == null;
		} else if (!rollbackContext.equals(other.rollbackContext)) {
			return false;
		}
		if (rollbackRequest == null) {
			return other.rollbackRequest == null;
		} else if (!rollbackRequest.equals(other.rollbackRequest)) {
			return false;
		}
		if (rollbackAfter == null) {
			return other.rollbackAfter == null;
		} else if (!rollbackAfter.equals(other.rollbackAfter)) {
			return false;
		}
		if (outputFields == null) {
			return other.outputFields == null;
		} else if (!outputFields.equals(other.outputFields)) {
			return false;
		}
		if (workId == null) {
			return other.workId == null;
		} else if (!workId.equals(other.workId)) {
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
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

package io.gs2.datastore.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * データオブジェクト
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class DataObject implements IModel, Serializable, Comparable<DataObject> {
	/** データオブジェクト */
	protected String dataObjectId;

	/**
	 * データオブジェクトを取得
	 *
	 * @return データオブジェクト
	 */
	public String getDataObjectId() {
		return dataObjectId;
	}

	/**
	 * データオブジェクトを設定
	 *
	 * @param dataObjectId データオブジェクト
	 */
	public void setDataObjectId(String dataObjectId) {
		this.dataObjectId = dataObjectId;
	}

	/**
	 * データオブジェクトを設定
	 *
	 * @param dataObjectId データオブジェクト
	 * @return this
	 */
	public DataObject withDataObjectId(String dataObjectId) {
		this.dataObjectId = dataObjectId;
		return this;
	}
	/** データの名前 */
	protected String name;

	/**
	 * データの名前を取得
	 *
	 * @return データの名前
	 */
	public String getName() {
		return name;
	}

	/**
	 * データの名前を設定
	 *
	 * @param name データの名前
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * データの名前を設定
	 *
	 * @param name データの名前
	 * @return this
	 */
	public DataObject withName(String name) {
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
	public DataObject withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	/** ファイルのアクセス権 */
	protected String scope;

	/**
	 * ファイルのアクセス権を取得
	 *
	 * @return ファイルのアクセス権
	 */
	public String getScope() {
		return scope;
	}

	/**
	 * ファイルのアクセス権を設定
	 *
	 * @param scope ファイルのアクセス権
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}

	/**
	 * ファイルのアクセス権を設定
	 *
	 * @param scope ファイルのアクセス権
	 * @return this
	 */
	public DataObject withScope(String scope) {
		this.scope = scope;
		return this;
	}
	/** 公開するユーザIDリスト */
	protected List<String> allowUserIds;

	/**
	 * 公開するユーザIDリストを取得
	 *
	 * @return 公開するユーザIDリスト
	 */
	public List<String> getAllowUserIds() {
		return allowUserIds;
	}

	/**
	 * 公開するユーザIDリストを設定
	 *
	 * @param allowUserIds 公開するユーザIDリスト
	 */
	public void setAllowUserIds(List<String> allowUserIds) {
		this.allowUserIds = allowUserIds;
	}

	/**
	 * 公開するユーザIDリストを設定
	 *
	 * @param allowUserIds 公開するユーザIDリスト
	 * @return this
	 */
	public DataObject withAllowUserIds(List<String> allowUserIds) {
		this.allowUserIds = allowUserIds;
		return this;
	}
	/** プラットフォーム */
	protected String platform;

	/**
	 * プラットフォームを取得
	 *
	 * @return プラットフォーム
	 */
	public String getPlatform() {
		return platform;
	}

	/**
	 * プラットフォームを設定
	 *
	 * @param platform プラットフォーム
	 */
	public void setPlatform(String platform) {
		this.platform = platform;
	}

	/**
	 * プラットフォームを設定
	 *
	 * @param platform プラットフォーム
	 * @return this
	 */
	public DataObject withPlatform(String platform) {
		this.platform = platform;
		return this;
	}
	/** 状態 */
	protected String status;

	/**
	 * 状態を取得
	 *
	 * @return 状態
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 状態を設定
	 *
	 * @param status 状態
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 状態を設定
	 *
	 * @param status 状態
	 * @return this
	 */
	public DataObject withStatus(String status) {
		this.status = status;
		return this;
	}
	/** データの世代 */
	protected String generation;

	/**
	 * データの世代を取得
	 *
	 * @return データの世代
	 */
	public String getGeneration() {
		return generation;
	}

	/**
	 * データの世代を設定
	 *
	 * @param generation データの世代
	 */
	public void setGeneration(String generation) {
		this.generation = generation;
	}

	/**
	 * データの世代を設定
	 *
	 * @param generation データの世代
	 * @return this
	 */
	public DataObject withGeneration(String generation) {
		this.generation = generation;
		return this;
	}
	/** 以前有効だったデータの世代 */
	protected String previousGeneration;

	/**
	 * 以前有効だったデータの世代を取得
	 *
	 * @return 以前有効だったデータの世代
	 */
	public String getPreviousGeneration() {
		return previousGeneration;
	}

	/**
	 * 以前有効だったデータの世代を設定
	 *
	 * @param previousGeneration 以前有効だったデータの世代
	 */
	public void setPreviousGeneration(String previousGeneration) {
		this.previousGeneration = previousGeneration;
	}

	/**
	 * 以前有効だったデータの世代を設定
	 *
	 * @param previousGeneration 以前有効だったデータの世代
	 * @return this
	 */
	public DataObject withPreviousGeneration(String previousGeneration) {
		this.previousGeneration = previousGeneration;
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
	public DataObject withCreatedAt(Long createdAt) {
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
	public DataObject withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> allowUserIds = new ArrayList<>();
        if(this.allowUserIds != null) {
            for(String item : this.allowUserIds) {
                allowUserIds.add(JsonNodeFactory.instance.textNode(item));
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("dataObjectId", this.getDataObjectId())
            .put("name", this.getName())
            .put("userId", this.getUserId())
            .put("scope", this.getScope())
            .put("platform", this.getPlatform())
            .put("status", this.getStatus())
            .put("generation", this.getGeneration())
            .put("previousGeneration", this.getPreviousGeneration())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        body_.set("allowUserIds", JsonNodeFactory.instance.arrayNode().addAll(allowUserIds));
        return body_;
    }
	@Override
	public int compareTo(DataObject o) {
		return dataObjectId.compareTo(o.dataObjectId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.dataObjectId == null) ? 0 : this.dataObjectId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.scope == null) ? 0 : this.scope.hashCode());
        result = prime * result + ((this.allowUserIds == null) ? 0 : this.allowUserIds.hashCode());
        result = prime * result + ((this.platform == null) ? 0 : this.platform.hashCode());
        result = prime * result + ((this.status == null) ? 0 : this.status.hashCode());
        result = prime * result + ((this.generation == null) ? 0 : this.generation.hashCode());
        result = prime * result + ((this.previousGeneration == null) ? 0 : this.previousGeneration.hashCode());
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
		DataObject other = (DataObject) o;
		if (dataObjectId == null) {
			return other.dataObjectId == null;
		} else if (!dataObjectId.equals(other.dataObjectId)) {
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
		if (scope == null) {
			return other.scope == null;
		} else if (!scope.equals(other.scope)) {
			return false;
		}
		if (allowUserIds == null) {
			return other.allowUserIds == null;
		} else if (!allowUserIds.equals(other.allowUserIds)) {
			return false;
		}
		if (platform == null) {
			return other.platform == null;
		} else if (!platform.equals(other.platform)) {
			return false;
		}
		if (status == null) {
			return other.status == null;
		} else if (!status.equals(other.status)) {
			return false;
		}
		if (generation == null) {
			return other.generation == null;
		} else if (!generation.equals(other.generation)) {
			return false;
		}
		if (previousGeneration == null) {
			return other.previousGeneration == null;
		} else if (!previousGeneration.equals(other.previousGeneration)) {
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
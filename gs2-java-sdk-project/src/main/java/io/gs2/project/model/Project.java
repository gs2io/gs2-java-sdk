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

package io.gs2.project.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.model.IModel;

/**
 * プロジェクト
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Project implements IModel, Serializable, Comparable<Project> {
	/** プロジェクト */
	protected String projectId;

	/**
	 * プロジェクトを取得
	 *
	 * @return プロジェクト
	 */
	public String getProjectId() {
		return projectId;
	}

	/**
	 * プロジェクトを設定
	 *
	 * @param projectId プロジェクト
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	/**
	 * プロジェクトを設定
	 *
	 * @param projectId プロジェクト
	 * @return this
	 */
	public Project withProjectId(String projectId) {
		this.projectId = projectId;
		return this;
	}
	/** GS2アカウントの名前 */
	protected String accountName;

	/**
	 * GS2アカウントの名前を取得
	 *
	 * @return GS2アカウントの名前
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * GS2アカウントの名前を設定
	 *
	 * @param accountName GS2アカウントの名前
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	/**
	 * GS2アカウントの名前を設定
	 *
	 * @param accountName GS2アカウントの名前
	 * @return this
	 */
	public Project withAccountName(String accountName) {
		this.accountName = accountName;
		return this;
	}
	/** プロジェクト名 */
	protected String name;

	/**
	 * プロジェクト名を取得
	 *
	 * @return プロジェクト名
	 */
	public String getName() {
		return name;
	}

	/**
	 * プロジェクト名を設定
	 *
	 * @param name プロジェクト名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * プロジェクト名を設定
	 *
	 * @param name プロジェクト名
	 * @return this
	 */
	public Project withName(String name) {
		this.name = name;
		return this;
	}
	/** プロジェクトの説明 */
	protected String description;

	/**
	 * プロジェクトの説明を取得
	 *
	 * @return プロジェクトの説明
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * プロジェクトの説明を設定
	 *
	 * @param description プロジェクトの説明
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * プロジェクトの説明を設定
	 *
	 * @param description プロジェクトの説明
	 * @return this
	 */
	public Project withDescription(String description) {
		this.description = description;
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
	public Project withCreatedAt(Long createdAt) {
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
	public Project withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("projectId", this.getProjectId())
            .put("accountName", this.getAccountName())
            .put("name", this.getName())
            .put("description", this.getDescription())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        return body_;
    }
	@Override
	public int compareTo(Project o) {
		return projectId.compareTo(o.projectId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.projectId == null) ? 0 : this.projectId.hashCode());
        result = prime * result + ((this.accountName == null) ? 0 : this.accountName.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
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
		Project other = (Project) o;
		if (projectId == null) {
			return other.projectId == null;
		} else if (!projectId.equals(other.projectId)) {
			return false;
		}
		if (accountName == null) {
			return other.accountName == null;
		} else if (!accountName.equals(other.accountName)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (description == null) {
			return other.description == null;
		} else if (!description.equals(other.description)) {
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
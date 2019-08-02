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

package io.gs2.quest.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.model.IModel;

/**
 * クエスト進行
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CompletedQuestList implements IModel, Serializable, Comparable<CompletedQuestList> {
	/** クエスト進行 */
	protected String completedQuestListId;

	/**
	 * クエスト進行を取得
	 *
	 * @return クエスト進行
	 */
	public String getCompletedQuestListId() {
		return completedQuestListId;
	}

	/**
	 * クエスト進行を設定
	 *
	 * @param completedQuestListId クエスト進行
	 */
	public void setCompletedQuestListId(String completedQuestListId) {
		this.completedQuestListId = completedQuestListId;
	}

	/**
	 * クエスト進行を設定
	 *
	 * @param completedQuestListId クエスト進行
	 * @return this
	 */
	public CompletedQuestList withCompletedQuestListId(String completedQuestListId) {
		this.completedQuestListId = completedQuestListId;
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
	public CompletedQuestList withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	/** クエストグループ名 */
	protected String questGroupName;

	/**
	 * クエストグループ名を取得
	 *
	 * @return クエストグループ名
	 */
	public String getQuestGroupName() {
		return questGroupName;
	}

	/**
	 * クエストグループ名を設定
	 *
	 * @param questGroupName クエストグループ名
	 */
	public void setQuestGroupName(String questGroupName) {
		this.questGroupName = questGroupName;
	}

	/**
	 * クエストグループ名を設定
	 *
	 * @param questGroupName クエストグループ名
	 * @return this
	 */
	public CompletedQuestList withQuestGroupName(String questGroupName) {
		this.questGroupName = questGroupName;
		return this;
	}
	/** 攻略済みのクエスト名一覧のリスト */
	protected List<String> completeQuestNames;

	/**
	 * 攻略済みのクエスト名一覧のリストを取得
	 *
	 * @return 攻略済みのクエスト名一覧のリスト
	 */
	public List<String> getCompleteQuestNames() {
		return completeQuestNames;
	}

	/**
	 * 攻略済みのクエスト名一覧のリストを設定
	 *
	 * @param completeQuestNames 攻略済みのクエスト名一覧のリスト
	 */
	public void setCompleteQuestNames(List<String> completeQuestNames) {
		this.completeQuestNames = completeQuestNames;
	}

	/**
	 * 攻略済みのクエスト名一覧のリストを設定
	 *
	 * @param completeQuestNames 攻略済みのクエスト名一覧のリスト
	 * @return this
	 */
	public CompletedQuestList withCompleteQuestNames(List<String> completeQuestNames) {
		this.completeQuestNames = completeQuestNames;
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
	public CompletedQuestList withCreatedAt(Long createdAt) {
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
	public CompletedQuestList withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> completeQuestNames = new ArrayList<>();
        if(this.completeQuestNames != null) {
            for(String item : this.completeQuestNames) {
                completeQuestNames.add(JsonNodeFactory.instance.textNode(item));
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("completedQuestListId", this.getCompletedQuestListId())
            .put("userId", this.getUserId())
            .put("questGroupName", this.getQuestGroupName())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        body_.set("completeQuestNames", JsonNodeFactory.instance.arrayNode().addAll(completeQuestNames));
        return body_;
    }
	@Override
	public int compareTo(CompletedQuestList o) {
		return completedQuestListId.compareTo(o.completedQuestListId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.completedQuestListId == null) ? 0 : this.completedQuestListId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.questGroupName == null) ? 0 : this.questGroupName.hashCode());
        result = prime * result + ((this.completeQuestNames == null) ? 0 : this.completeQuestNames.hashCode());
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
		CompletedQuestList other = (CompletedQuestList) o;
		if (completedQuestListId == null) {
			return other.completedQuestListId == null;
		} else if (!completedQuestListId.equals(other.completedQuestListId)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (questGroupName == null) {
			return other.questGroupName == null;
		} else if (!questGroupName.equals(other.questGroupName)) {
			return false;
		}
		if (completeQuestNames == null) {
			return other.completeQuestNames == null;
		} else if (!completeQuestNames.equals(other.completeQuestNames)) {
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
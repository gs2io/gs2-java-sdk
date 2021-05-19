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

package io.gs2.friend.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * フレンドリクエストの受信ボックス
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Inbox implements IModel, Serializable, Comparable<Inbox> {
	/** フレンドリクエストの受信ボックス */
	protected String inboxId;

	/**
	 * フレンドリクエストの受信ボックスを取得
	 *
	 * @return フレンドリクエストの受信ボックス
	 */
	public String getInboxId() {
		return inboxId;
	}

	/**
	 * フレンドリクエストの受信ボックスを設定
	 *
	 * @param inboxId フレンドリクエストの受信ボックス
	 */
	public void setInboxId(String inboxId) {
		this.inboxId = inboxId;
	}

	/**
	 * フレンドリクエストの受信ボックスを設定
	 *
	 * @param inboxId フレンドリクエストの受信ボックス
	 * @return this
	 */
	public Inbox withInboxId(String inboxId) {
		this.inboxId = inboxId;
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
	public Inbox withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	/** フレンドリクエストを送ってきたユーザーIDリスト */
	protected List<String> fromUserIds;

	/**
	 * フレンドリクエストを送ってきたユーザーIDリストを取得
	 *
	 * @return フレンドリクエストを送ってきたユーザーIDリスト
	 */
	public List<String> getFromUserIds() {
		return fromUserIds;
	}

	/**
	 * フレンドリクエストを送ってきたユーザーIDリストを設定
	 *
	 * @param fromUserIds フレンドリクエストを送ってきたユーザーIDリスト
	 */
	public void setFromUserIds(List<String> fromUserIds) {
		this.fromUserIds = fromUserIds;
	}

	/**
	 * フレンドリクエストを送ってきたユーザーIDリストを設定
	 *
	 * @param fromUserIds フレンドリクエストを送ってきたユーザーIDリスト
	 * @return this
	 */
	public Inbox withFromUserIds(List<String> fromUserIds) {
		this.fromUserIds = fromUserIds;
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
	public Inbox withCreatedAt(Long createdAt) {
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
	public Inbox withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> fromUserIds = new ArrayList<>();
        if(this.fromUserIds != null) {
            for(String item : this.fromUserIds) {
                fromUserIds.add(JsonNodeFactory.instance.textNode(item));
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("inboxId", this.getInboxId())
            .put("userId", this.getUserId())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        body_.set("fromUserIds", JsonNodeFactory.instance.arrayNode().addAll(fromUserIds));
        return body_;
    }
	@Override
	public int compareTo(Inbox o) {
		return inboxId.compareTo(o.inboxId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.inboxId == null) ? 0 : this.inboxId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.fromUserIds == null) ? 0 : this.fromUserIds.hashCode());
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
		Inbox other = (Inbox) o;
		if (inboxId == null) {
			return other.inboxId == null;
		} else if (!inboxId.equals(other.inboxId)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (fromUserIds == null) {
			return other.fromUserIds == null;
		} else if (!fromUserIds.equals(other.fromUserIds)) {
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
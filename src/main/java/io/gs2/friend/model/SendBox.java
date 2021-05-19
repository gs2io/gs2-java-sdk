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
public class SendBox implements IModel, Serializable, Comparable<SendBox> {
	/** フレンドリクエストの受信ボックス */
	protected String sendBoxId;

	/**
	 * フレンドリクエストの受信ボックスを取得
	 *
	 * @return フレンドリクエストの受信ボックス
	 */
	public String getSendBoxId() {
		return sendBoxId;
	}

	/**
	 * フレンドリクエストの受信ボックスを設定
	 *
	 * @param sendBoxId フレンドリクエストの受信ボックス
	 */
	public void setSendBoxId(String sendBoxId) {
		this.sendBoxId = sendBoxId;
	}

	/**
	 * フレンドリクエストの受信ボックスを設定
	 *
	 * @param sendBoxId フレンドリクエストの受信ボックス
	 * @return this
	 */
	public SendBox withSendBoxId(String sendBoxId) {
		this.sendBoxId = sendBoxId;
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
	public SendBox withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	/** フレンドリクエストの宛先ユーザーIDリスト */
	protected List<String> targetUserIds;

	/**
	 * フレンドリクエストの宛先ユーザーIDリストを取得
	 *
	 * @return フレンドリクエストの宛先ユーザーIDリスト
	 */
	public List<String> getTargetUserIds() {
		return targetUserIds;
	}

	/**
	 * フレンドリクエストの宛先ユーザーIDリストを設定
	 *
	 * @param targetUserIds フレンドリクエストの宛先ユーザーIDリスト
	 */
	public void setTargetUserIds(List<String> targetUserIds) {
		this.targetUserIds = targetUserIds;
	}

	/**
	 * フレンドリクエストの宛先ユーザーIDリストを設定
	 *
	 * @param targetUserIds フレンドリクエストの宛先ユーザーIDリスト
	 * @return this
	 */
	public SendBox withTargetUserIds(List<String> targetUserIds) {
		this.targetUserIds = targetUserIds;
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
	public SendBox withCreatedAt(Long createdAt) {
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
	public SendBox withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> targetUserIds = new ArrayList<>();
        if(this.targetUserIds != null) {
            for(String item : this.targetUserIds) {
                targetUserIds.add(JsonNodeFactory.instance.textNode(item));
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("sendBoxId", this.getSendBoxId())
            .put("userId", this.getUserId())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        body_.set("targetUserIds", JsonNodeFactory.instance.arrayNode().addAll(targetUserIds));
        return body_;
    }
	@Override
	public int compareTo(SendBox o) {
		return sendBoxId.compareTo(o.sendBoxId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.sendBoxId == null) ? 0 : this.sendBoxId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.targetUserIds == null) ? 0 : this.targetUserIds.hashCode());
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
		SendBox other = (SendBox) o;
		if (sendBoxId == null) {
			return other.sendBoxId == null;
		} else if (!sendBoxId.equals(other.sendBoxId)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (targetUserIds == null) {
			return other.targetUserIds == null;
		} else if (!targetUserIds.equals(other.targetUserIds)) {
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
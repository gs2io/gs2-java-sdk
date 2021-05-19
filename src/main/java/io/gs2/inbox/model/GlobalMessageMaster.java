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

package io.gs2.inbox.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * 全ユーザに向けたメッセージ
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class GlobalMessageMaster implements IModel, Serializable, Comparable<GlobalMessageMaster> {
	/** 全ユーザに向けたメッセージ */
	protected String globalMessageId;

	/**
	 * 全ユーザに向けたメッセージを取得
	 *
	 * @return 全ユーザに向けたメッセージ
	 */
	public String getGlobalMessageId() {
		return globalMessageId;
	}

	/**
	 * 全ユーザに向けたメッセージを設定
	 *
	 * @param globalMessageId 全ユーザに向けたメッセージ
	 */
	public void setGlobalMessageId(String globalMessageId) {
		this.globalMessageId = globalMessageId;
	}

	/**
	 * 全ユーザに向けたメッセージを設定
	 *
	 * @param globalMessageId 全ユーザに向けたメッセージ
	 * @return this
	 */
	public GlobalMessageMaster withGlobalMessageId(String globalMessageId) {
		this.globalMessageId = globalMessageId;
		return this;
	}
	/** 全ユーザに向けたメッセージ名 */
	protected String name;

	/**
	 * 全ユーザに向けたメッセージ名を取得
	 *
	 * @return 全ユーザに向けたメッセージ名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 全ユーザに向けたメッセージ名を設定
	 *
	 * @param name 全ユーザに向けたメッセージ名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 全ユーザに向けたメッセージ名を設定
	 *
	 * @param name 全ユーザに向けたメッセージ名
	 * @return this
	 */
	public GlobalMessageMaster withName(String name) {
		this.name = name;
		return this;
	}
	/** 全ユーザに向けたメッセージの内容に相当するメタデータ */
	protected String metadata;

	/**
	 * 全ユーザに向けたメッセージの内容に相当するメタデータを取得
	 *
	 * @return 全ユーザに向けたメッセージの内容に相当するメタデータ
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * 全ユーザに向けたメッセージの内容に相当するメタデータを設定
	 *
	 * @param metadata 全ユーザに向けたメッセージの内容に相当するメタデータ
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	/**
	 * 全ユーザに向けたメッセージの内容に相当するメタデータを設定
	 *
	 * @param metadata 全ユーザに向けたメッセージの内容に相当するメタデータ
	 * @return this
	 */
	public GlobalMessageMaster withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	/** 開封時に実行する入手アクション */
	protected List<AcquireAction> readAcquireActions;

	/**
	 * 開封時に実行する入手アクションを取得
	 *
	 * @return 開封時に実行する入手アクション
	 */
	public List<AcquireAction> getReadAcquireActions() {
		return readAcquireActions;
	}

	/**
	 * 開封時に実行する入手アクションを設定
	 *
	 * @param readAcquireActions 開封時に実行する入手アクション
	 */
	public void setReadAcquireActions(List<AcquireAction> readAcquireActions) {
		this.readAcquireActions = readAcquireActions;
	}

	/**
	 * 開封時に実行する入手アクションを設定
	 *
	 * @param readAcquireActions 開封時に実行する入手アクション
	 * @return this
	 */
	public GlobalMessageMaster withReadAcquireActions(List<AcquireAction> readAcquireActions) {
		this.readAcquireActions = readAcquireActions;
		return this;
	}
	/** メッセージを受信したあとメッセージが削除されるまでの期間 */
	protected TimeSpan expiresTimeSpan;

	/**
	 * メッセージを受信したあとメッセージが削除されるまでの期間を取得
	 *
	 * @return メッセージを受信したあとメッセージが削除されるまでの期間
	 */
	public TimeSpan getExpiresTimeSpan() {
		return expiresTimeSpan;
	}

	/**
	 * メッセージを受信したあとメッセージが削除されるまでの期間を設定
	 *
	 * @param expiresTimeSpan メッセージを受信したあとメッセージが削除されるまでの期間
	 */
	public void setExpiresTimeSpan(TimeSpan expiresTimeSpan) {
		this.expiresTimeSpan = expiresTimeSpan;
	}

	/**
	 * メッセージを受信したあとメッセージが削除されるまでの期間を設定
	 *
	 * @param expiresTimeSpan メッセージを受信したあとメッセージが削除されるまでの期間
	 * @return this
	 */
	public GlobalMessageMaster withExpiresTimeSpan(TimeSpan expiresTimeSpan) {
		this.expiresTimeSpan = expiresTimeSpan;
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
	public GlobalMessageMaster withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	/** 全ユーザに向けたメッセージの受信期限 */
	protected Long expiresAt;

	/**
	 * 全ユーザに向けたメッセージの受信期限を取得
	 *
	 * @return 全ユーザに向けたメッセージの受信期限
	 */
	public Long getExpiresAt() {
		return expiresAt;
	}

	/**
	 * 全ユーザに向けたメッセージの受信期限を設定
	 *
	 * @param expiresAt 全ユーザに向けたメッセージの受信期限
	 */
	public void setExpiresAt(Long expiresAt) {
		this.expiresAt = expiresAt;
	}

	/**
	 * 全ユーザに向けたメッセージの受信期限を設定
	 *
	 * @param expiresAt 全ユーザに向けたメッセージの受信期限
	 * @return this
	 */
	public GlobalMessageMaster withExpiresAt(Long expiresAt) {
		this.expiresAt = expiresAt;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> readAcquireActions = new ArrayList<>();
        if(this.readAcquireActions != null) {
            for(AcquireAction item : this.readAcquireActions) {
                readAcquireActions.add(item.toJson());
            }
        }
        JsonNode expiresTimeSpan = this.getExpiresTimeSpan().toJson();
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("globalMessageId", this.getGlobalMessageId())
            .put("name", this.getName())
            .put("metadata", this.getMetadata())
            .put("createdAt", this.getCreatedAt())
            .put("expiresAt", this.getExpiresAt());
        body_.set("readAcquireActions", JsonNodeFactory.instance.arrayNode().addAll(readAcquireActions));
        body_.set("expiresTimeSpan", expiresTimeSpan);
        return body_;
    }
	@Override
	public int compareTo(GlobalMessageMaster o) {
		return globalMessageId.compareTo(o.globalMessageId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.globalMessageId == null) ? 0 : this.globalMessageId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.readAcquireActions == null) ? 0 : this.readAcquireActions.hashCode());
        result = prime * result + ((this.expiresTimeSpan == null) ? 0 : this.expiresTimeSpan.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.expiresAt == null) ? 0 : this.expiresAt.hashCode());
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
		GlobalMessageMaster other = (GlobalMessageMaster) o;
		if (globalMessageId == null) {
			return other.globalMessageId == null;
		} else if (!globalMessageId.equals(other.globalMessageId)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (metadata == null) {
			return other.metadata == null;
		} else if (!metadata.equals(other.metadata)) {
			return false;
		}
		if (readAcquireActions == null) {
			return other.readAcquireActions == null;
		} else if (!readAcquireActions.equals(other.readAcquireActions)) {
			return false;
		}
		if (expiresTimeSpan == null) {
			return other.expiresTimeSpan == null;
		} else if (!expiresTimeSpan.equals(other.expiresTimeSpan)) {
			return false;
		}
		if (createdAt == null) {
			return other.createdAt == null;
		} else if (!createdAt.equals(other.createdAt)) {
			return false;
		}
		if (expiresAt == null) {
			return other.expiresAt == null;
		} else if (!expiresAt.equals(other.expiresAt)) {
			return false;
		}
		return true;
	}
}
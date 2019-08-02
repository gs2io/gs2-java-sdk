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

package io.gs2.inventory.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.model.IModel;

/**
 * ネームスペース
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Namespace implements IModel, Serializable, Comparable<Namespace> {
	/** ネームスペース */
	protected String namespaceId;

	/**
	 * ネームスペースを取得
	 *
	 * @return ネームスペース
	 */
	public String getNamespaceId() {
		return namespaceId;
	}

	/**
	 * ネームスペースを設定
	 *
	 * @param namespaceId ネームスペース
	 */
	public void setNamespaceId(String namespaceId) {
		this.namespaceId = namespaceId;
	}

	/**
	 * ネームスペースを設定
	 *
	 * @param namespaceId ネームスペース
	 * @return this
	 */
	public Namespace withNamespaceId(String namespaceId) {
		this.namespaceId = namespaceId;
		return this;
	}
	/** オーナーID */
	protected String ownerId;

	/**
	 * オーナーIDを取得
	 *
	 * @return オーナーID
	 */
	public String getOwnerId() {
		return ownerId;
	}

	/**
	 * オーナーIDを設定
	 *
	 * @param ownerId オーナーID
	 */
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * オーナーIDを設定
	 *
	 * @param ownerId オーナーID
	 * @return this
	 */
	public Namespace withOwnerId(String ownerId) {
		this.ownerId = ownerId;
		return this;
	}
	/** カテゴリー名 */
	protected String name;

	/**
	 * カテゴリー名を取得
	 *
	 * @return カテゴリー名
	 */
	public String getName() {
		return name;
	}

	/**
	 * カテゴリー名を設定
	 *
	 * @param name カテゴリー名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * カテゴリー名を設定
	 *
	 * @param name カテゴリー名
	 * @return this
	 */
	public Namespace withName(String name) {
		this.name = name;
		return this;
	}
	/** ネームスペースの説明 */
	protected String description;

	/**
	 * ネームスペースの説明を取得
	 *
	 * @return ネームスペースの説明
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * ネームスペースの説明を設定
	 *
	 * @param description ネームスペースの説明
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * ネームスペースの説明を設定
	 *
	 * @param description ネームスペースの説明
	 * @return this
	 */
	public Namespace withDescription(String description) {
		this.description = description;
		return this;
	}
	/** アイテム入手時 に実行されるスクリプト のGRN */
	protected String acquireTriggerScriptId;

	/**
	 * アイテム入手時 に実行されるスクリプト のGRNを取得
	 *
	 * @return アイテム入手時 に実行されるスクリプト のGRN
	 */
	public String getAcquireTriggerScriptId() {
		return acquireTriggerScriptId;
	}

	/**
	 * アイテム入手時 に実行されるスクリプト のGRNを設定
	 *
	 * @param acquireTriggerScriptId アイテム入手時 に実行されるスクリプト のGRN
	 */
	public void setAcquireTriggerScriptId(String acquireTriggerScriptId) {
		this.acquireTriggerScriptId = acquireTriggerScriptId;
	}

	/**
	 * アイテム入手時 に実行されるスクリプト のGRNを設定
	 *
	 * @param acquireTriggerScriptId アイテム入手時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withAcquireTriggerScriptId(String acquireTriggerScriptId) {
		this.acquireTriggerScriptId = acquireTriggerScriptId;
		return this;
	}
	/** アイテム入手完了時 に実行されるスクリプト のGRN */
	protected String acquireDoneTriggerScriptId;

	/**
	 * アイテム入手完了時 に実行されるスクリプト のGRNを取得
	 *
	 * @return アイテム入手完了時 に実行されるスクリプト のGRN
	 */
	public String getAcquireDoneTriggerScriptId() {
		return acquireDoneTriggerScriptId;
	}

	/**
	 * アイテム入手完了時 に実行されるスクリプト のGRNを設定
	 *
	 * @param acquireDoneTriggerScriptId アイテム入手完了時 に実行されるスクリプト のGRN
	 */
	public void setAcquireDoneTriggerScriptId(String acquireDoneTriggerScriptId) {
		this.acquireDoneTriggerScriptId = acquireDoneTriggerScriptId;
	}

	/**
	 * アイテム入手完了時 に実行されるスクリプト のGRNを設定
	 *
	 * @param acquireDoneTriggerScriptId アイテム入手完了時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withAcquireDoneTriggerScriptId(String acquireDoneTriggerScriptId) {
		this.acquireDoneTriggerScriptId = acquireDoneTriggerScriptId;
		return this;
	}
	/** アイテム入手完了時 に通知するジョブを追加する ネームスペース のGRN */
	protected String acquireDoneTriggerQueueNamespaceId;

	/**
	 * アイテム入手完了時 に通知するジョブを追加する ネームスペース のGRNを取得
	 *
	 * @return アイテム入手完了時 に通知するジョブを追加する ネームスペース のGRN
	 */
	public String getAcquireDoneTriggerQueueNamespaceId() {
		return acquireDoneTriggerQueueNamespaceId;
	}

	/**
	 * アイテム入手完了時 に通知するジョブを追加する ネームスペース のGRNを設定
	 *
	 * @param acquireDoneTriggerQueueNamespaceId アイテム入手完了時 に通知するジョブを追加する ネームスペース のGRN
	 */
	public void setAcquireDoneTriggerQueueNamespaceId(String acquireDoneTriggerQueueNamespaceId) {
		this.acquireDoneTriggerQueueNamespaceId = acquireDoneTriggerQueueNamespaceId;
	}

	/**
	 * アイテム入手完了時 に通知するジョブを追加する ネームスペース のGRNを設定
	 *
	 * @param acquireDoneTriggerQueueNamespaceId アイテム入手完了時 に通知するジョブを追加する ネームスペース のGRN
	 * @return this
	 */
	public Namespace withAcquireDoneTriggerQueueNamespaceId(String acquireDoneTriggerQueueNamespaceId) {
		this.acquireDoneTriggerQueueNamespaceId = acquireDoneTriggerQueueNamespaceId;
		return this;
	}
	/** 入手上限に当たって入手できなかった数量を通知する スクリプト のGRN */
	protected String overflowTriggerScriptId;

	/**
	 * 入手上限に当たって入手できなかった数量を通知する スクリプト のGRNを取得
	 *
	 * @return 入手上限に当たって入手できなかった数量を通知する スクリプト のGRN
	 */
	public String getOverflowTriggerScriptId() {
		return overflowTriggerScriptId;
	}

	/**
	 * 入手上限に当たって入手できなかった数量を通知する スクリプト のGRNを設定
	 *
	 * @param overflowTriggerScriptId 入手上限に当たって入手できなかった数量を通知する スクリプト のGRN
	 */
	public void setOverflowTriggerScriptId(String overflowTriggerScriptId) {
		this.overflowTriggerScriptId = overflowTriggerScriptId;
	}

	/**
	 * 入手上限に当たって入手できなかった数量を通知する スクリプト のGRNを設定
	 *
	 * @param overflowTriggerScriptId 入手上限に当たって入手できなかった数量を通知する スクリプト のGRN
	 * @return this
	 */
	public Namespace withOverflowTriggerScriptId(String overflowTriggerScriptId) {
		this.overflowTriggerScriptId = overflowTriggerScriptId;
		return this;
	}
	/** 入手上限に当たって入手できなかった数量を通知するジョブを追加する ネームスペース のGRN */
	protected String overflowTriggerQueueNamespaceId;

	/**
	 * 入手上限に当たって入手できなかった数量を通知するジョブを追加する ネームスペース のGRNを取得
	 *
	 * @return 入手上限に当たって入手できなかった数量を通知するジョブを追加する ネームスペース のGRN
	 */
	public String getOverflowTriggerQueueNamespaceId() {
		return overflowTriggerQueueNamespaceId;
	}

	/**
	 * 入手上限に当たって入手できなかった数量を通知するジョブを追加する ネームスペース のGRNを設定
	 *
	 * @param overflowTriggerQueueNamespaceId 入手上限に当たって入手できなかった数量を通知するジョブを追加する ネームスペース のGRN
	 */
	public void setOverflowTriggerQueueNamespaceId(String overflowTriggerQueueNamespaceId) {
		this.overflowTriggerQueueNamespaceId = overflowTriggerQueueNamespaceId;
	}

	/**
	 * 入手上限に当たって入手できなかった数量を通知するジョブを追加する ネームスペース のGRNを設定
	 *
	 * @param overflowTriggerQueueNamespaceId 入手上限に当たって入手できなかった数量を通知するジョブを追加する ネームスペース のGRN
	 * @return this
	 */
	public Namespace withOverflowTriggerQueueNamespaceId(String overflowTriggerQueueNamespaceId) {
		this.overflowTriggerQueueNamespaceId = overflowTriggerQueueNamespaceId;
		return this;
	}
	/** アイテム消費時 に実行されるスクリプト のGRN */
	protected String consumeTriggerScriptId;

	/**
	 * アイテム消費時 に実行されるスクリプト のGRNを取得
	 *
	 * @return アイテム消費時 に実行されるスクリプト のGRN
	 */
	public String getConsumeTriggerScriptId() {
		return consumeTriggerScriptId;
	}

	/**
	 * アイテム消費時 に実行されるスクリプト のGRNを設定
	 *
	 * @param consumeTriggerScriptId アイテム消費時 に実行されるスクリプト のGRN
	 */
	public void setConsumeTriggerScriptId(String consumeTriggerScriptId) {
		this.consumeTriggerScriptId = consumeTriggerScriptId;
	}

	/**
	 * アイテム消費時 に実行されるスクリプト のGRNを設定
	 *
	 * @param consumeTriggerScriptId アイテム消費時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withConsumeTriggerScriptId(String consumeTriggerScriptId) {
		this.consumeTriggerScriptId = consumeTriggerScriptId;
		return this;
	}
	/** アイテム入手完了時 に実行されるスクリプト のGRN */
	protected String consumeDoneTriggerScriptId;

	/**
	 * アイテム入手完了時 に実行されるスクリプト のGRNを取得
	 *
	 * @return アイテム入手完了時 に実行されるスクリプト のGRN
	 */
	public String getConsumeDoneTriggerScriptId() {
		return consumeDoneTriggerScriptId;
	}

	/**
	 * アイテム入手完了時 に実行されるスクリプト のGRNを設定
	 *
	 * @param consumeDoneTriggerScriptId アイテム入手完了時 に実行されるスクリプト のGRN
	 */
	public void setConsumeDoneTriggerScriptId(String consumeDoneTriggerScriptId) {
		this.consumeDoneTriggerScriptId = consumeDoneTriggerScriptId;
	}

	/**
	 * アイテム入手完了時 に実行されるスクリプト のGRNを設定
	 *
	 * @param consumeDoneTriggerScriptId アイテム入手完了時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withConsumeDoneTriggerScriptId(String consumeDoneTriggerScriptId) {
		this.consumeDoneTriggerScriptId = consumeDoneTriggerScriptId;
		return this;
	}
	/** アイテム入手完了時 に通知するジョブを追加する ネームスペース のGRN */
	protected String consumeDoneTriggerQueueNamespaceId;

	/**
	 * アイテム入手完了時 に通知するジョブを追加する ネームスペース のGRNを取得
	 *
	 * @return アイテム入手完了時 に通知するジョブを追加する ネームスペース のGRN
	 */
	public String getConsumeDoneTriggerQueueNamespaceId() {
		return consumeDoneTriggerQueueNamespaceId;
	}

	/**
	 * アイテム入手完了時 に通知するジョブを追加する ネームスペース のGRNを設定
	 *
	 * @param consumeDoneTriggerQueueNamespaceId アイテム入手完了時 に通知するジョブを追加する ネームスペース のGRN
	 */
	public void setConsumeDoneTriggerQueueNamespaceId(String consumeDoneTriggerQueueNamespaceId) {
		this.consumeDoneTriggerQueueNamespaceId = consumeDoneTriggerQueueNamespaceId;
	}

	/**
	 * アイテム入手完了時 に通知するジョブを追加する ネームスペース のGRNを設定
	 *
	 * @param consumeDoneTriggerQueueNamespaceId アイテム入手完了時 に通知するジョブを追加する ネームスペース のGRN
	 * @return this
	 */
	public Namespace withConsumeDoneTriggerQueueNamespaceId(String consumeDoneTriggerQueueNamespaceId) {
		this.consumeDoneTriggerQueueNamespaceId = consumeDoneTriggerQueueNamespaceId;
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
	public Namespace withCreatedAt(Long createdAt) {
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
	public Namespace withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("namespaceId", this.getNamespaceId())
            .put("ownerId", this.getOwnerId())
            .put("name", this.getName())
            .put("description", this.getDescription())
            .put("acquireTriggerScriptId", this.getAcquireTriggerScriptId())
            .put("acquireDoneTriggerScriptId", this.getAcquireDoneTriggerScriptId())
            .put("acquireDoneTriggerQueueNamespaceId", this.getAcquireDoneTriggerQueueNamespaceId())
            .put("overflowTriggerScriptId", this.getOverflowTriggerScriptId())
            .put("overflowTriggerQueueNamespaceId", this.getOverflowTriggerQueueNamespaceId())
            .put("consumeTriggerScriptId", this.getConsumeTriggerScriptId())
            .put("consumeDoneTriggerScriptId", this.getConsumeDoneTriggerScriptId())
            .put("consumeDoneTriggerQueueNamespaceId", this.getConsumeDoneTriggerQueueNamespaceId())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        return body_;
    }
	@Override
	public int compareTo(Namespace o) {
		return namespaceId.compareTo(o.namespaceId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.namespaceId == null) ? 0 : this.namespaceId.hashCode());
        result = prime * result + ((this.ownerId == null) ? 0 : this.ownerId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.acquireTriggerScriptId == null) ? 0 : this.acquireTriggerScriptId.hashCode());
        result = prime * result + ((this.acquireDoneTriggerScriptId == null) ? 0 : this.acquireDoneTriggerScriptId.hashCode());
        result = prime * result + ((this.acquireDoneTriggerQueueNamespaceId == null) ? 0 : this.acquireDoneTriggerQueueNamespaceId.hashCode());
        result = prime * result + ((this.overflowTriggerScriptId == null) ? 0 : this.overflowTriggerScriptId.hashCode());
        result = prime * result + ((this.overflowTriggerQueueNamespaceId == null) ? 0 : this.overflowTriggerQueueNamespaceId.hashCode());
        result = prime * result + ((this.consumeTriggerScriptId == null) ? 0 : this.consumeTriggerScriptId.hashCode());
        result = prime * result + ((this.consumeDoneTriggerScriptId == null) ? 0 : this.consumeDoneTriggerScriptId.hashCode());
        result = prime * result + ((this.consumeDoneTriggerQueueNamespaceId == null) ? 0 : this.consumeDoneTriggerQueueNamespaceId.hashCode());
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
		Namespace other = (Namespace) o;
		if (namespaceId == null) {
			return other.namespaceId == null;
		} else if (!namespaceId.equals(other.namespaceId)) {
			return false;
		}
		if (ownerId == null) {
			return other.ownerId == null;
		} else if (!ownerId.equals(other.ownerId)) {
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
		if (acquireTriggerScriptId == null) {
			return other.acquireTriggerScriptId == null;
		} else if (!acquireTriggerScriptId.equals(other.acquireTriggerScriptId)) {
			return false;
		}
		if (acquireDoneTriggerScriptId == null) {
			return other.acquireDoneTriggerScriptId == null;
		} else if (!acquireDoneTriggerScriptId.equals(other.acquireDoneTriggerScriptId)) {
			return false;
		}
		if (acquireDoneTriggerQueueNamespaceId == null) {
			return other.acquireDoneTriggerQueueNamespaceId == null;
		} else if (!acquireDoneTriggerQueueNamespaceId.equals(other.acquireDoneTriggerQueueNamespaceId)) {
			return false;
		}
		if (overflowTriggerScriptId == null) {
			return other.overflowTriggerScriptId == null;
		} else if (!overflowTriggerScriptId.equals(other.overflowTriggerScriptId)) {
			return false;
		}
		if (overflowTriggerQueueNamespaceId == null) {
			return other.overflowTriggerQueueNamespaceId == null;
		} else if (!overflowTriggerQueueNamespaceId.equals(other.overflowTriggerQueueNamespaceId)) {
			return false;
		}
		if (consumeTriggerScriptId == null) {
			return other.consumeTriggerScriptId == null;
		} else if (!consumeTriggerScriptId.equals(other.consumeTriggerScriptId)) {
			return false;
		}
		if (consumeDoneTriggerScriptId == null) {
			return other.consumeDoneTriggerScriptId == null;
		} else if (!consumeDoneTriggerScriptId.equals(other.consumeDoneTriggerScriptId)) {
			return false;
		}
		if (consumeDoneTriggerQueueNamespaceId == null) {
			return other.consumeDoneTriggerQueueNamespaceId == null;
		} else if (!consumeDoneTriggerQueueNamespaceId.equals(other.consumeDoneTriggerQueueNamespaceId)) {
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
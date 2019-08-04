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

package io.gs2.lottery.model;

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
	/** ネームスペース名 */
	protected String name;

	/**
	 * ネームスペース名を取得
	 *
	 * @return ネームスペース名
	 */
	public String getName() {
		return name;
	}

	/**
	 * ネームスペース名を設定
	 *
	 * @param name ネームスペース名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ネームスペース名を設定
	 *
	 * @param name ネームスペース名
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
	/** 景品付与処理をジョブとして追加するキューのネームスペース のGRN */
	protected String queueNamespaceId;

	/**
	 * 景品付与処理をジョブとして追加するキューのネームスペース のGRNを取得
	 *
	 * @return 景品付与処理をジョブとして追加するキューのネームスペース のGRN
	 */
	public String getQueueNamespaceId() {
		return queueNamespaceId;
	}

	/**
	 * 景品付与処理をジョブとして追加するキューのネームスペース のGRNを設定
	 *
	 * @param queueNamespaceId 景品付与処理をジョブとして追加するキューのネームスペース のGRN
	 */
	public void setQueueNamespaceId(String queueNamespaceId) {
		this.queueNamespaceId = queueNamespaceId;
	}

	/**
	 * 景品付与処理をジョブとして追加するキューのネームスペース のGRNを設定
	 *
	 * @param queueNamespaceId 景品付与処理をジョブとして追加するキューのネームスペース のGRN
	 * @return this
	 */
	public Namespace withQueueNamespaceId(String queueNamespaceId) {
		this.queueNamespaceId = queueNamespaceId;
		return this;
	}
	/** 景品付与処理のスタンプシートで使用する暗号鍵GRN */
	protected String keyId;

	/**
	 * 景品付与処理のスタンプシートで使用する暗号鍵GRNを取得
	 *
	 * @return 景品付与処理のスタンプシートで使用する暗号鍵GRN
	 */
	public String getKeyId() {
		return keyId;
	}

	/**
	 * 景品付与処理のスタンプシートで使用する暗号鍵GRNを設定
	 *
	 * @param keyId 景品付与処理のスタンプシートで使用する暗号鍵GRN
	 */
	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

	/**
	 * 景品付与処理のスタンプシートで使用する暗号鍵GRNを設定
	 *
	 * @param keyId 景品付与処理のスタンプシートで使用する暗号鍵GRN
	 * @return this
	 */
	public Namespace withKeyId(String keyId) {
		this.keyId = keyId;
		return this;
	}
	/** 抽選処理時 に実行されるスクリプト のGRN */
	protected String lotteryTriggerScriptId;

	/**
	 * 抽選処理時 に実行されるスクリプト のGRNを取得
	 *
	 * @return 抽選処理時 に実行されるスクリプト のGRN
	 */
	public String getLotteryTriggerScriptId() {
		return lotteryTriggerScriptId;
	}

	/**
	 * 抽選処理時 に実行されるスクリプト のGRNを設定
	 *
	 * @param lotteryTriggerScriptId 抽選処理時 に実行されるスクリプト のGRN
	 */
	public void setLotteryTriggerScriptId(String lotteryTriggerScriptId) {
		this.lotteryTriggerScriptId = lotteryTriggerScriptId;
	}

	/**
	 * 抽選処理時 に実行されるスクリプト のGRNを設定
	 *
	 * @param lotteryTriggerScriptId 抽選処理時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withLotteryTriggerScriptId(String lotteryTriggerScriptId) {
		this.lotteryTriggerScriptId = lotteryTriggerScriptId;
		return this;
	}
	/** 排出テーブル選択時 に実行されるスクリプト のGRN */
	protected String choicePrizeTableScriptId;

	/**
	 * 排出テーブル選択時 に実行されるスクリプト のGRNを取得
	 *
	 * @return 排出テーブル選択時 に実行されるスクリプト のGRN
	 */
	public String getChoicePrizeTableScriptId() {
		return choicePrizeTableScriptId;
	}

	/**
	 * 排出テーブル選択時 に実行されるスクリプト のGRNを設定
	 *
	 * @param choicePrizeTableScriptId 排出テーブル選択時 に実行されるスクリプト のGRN
	 */
	public void setChoicePrizeTableScriptId(String choicePrizeTableScriptId) {
		this.choicePrizeTableScriptId = choicePrizeTableScriptId;
	}

	/**
	 * 排出テーブル選択時 に実行されるスクリプト のGRNを設定
	 *
	 * @param choicePrizeTableScriptId 排出テーブル選択時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withChoicePrizeTableScriptId(String choicePrizeTableScriptId) {
		this.choicePrizeTableScriptId = choicePrizeTableScriptId;
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
            .put("queueNamespaceId", this.getQueueNamespaceId())
            .put("keyId", this.getKeyId())
            .put("lotteryTriggerScriptId", this.getLotteryTriggerScriptId())
            .put("choicePrizeTableScriptId", this.getChoicePrizeTableScriptId())
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
        result = prime * result + ((this.queueNamespaceId == null) ? 0 : this.queueNamespaceId.hashCode());
        result = prime * result + ((this.keyId == null) ? 0 : this.keyId.hashCode());
        result = prime * result + ((this.lotteryTriggerScriptId == null) ? 0 : this.lotteryTriggerScriptId.hashCode());
        result = prime * result + ((this.choicePrizeTableScriptId == null) ? 0 : this.choicePrizeTableScriptId.hashCode());
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
		if (queueNamespaceId == null) {
			return other.queueNamespaceId == null;
		} else if (!queueNamespaceId.equals(other.queueNamespaceId)) {
			return false;
		}
		if (keyId == null) {
			return other.keyId == null;
		} else if (!keyId.equals(other.keyId)) {
			return false;
		}
		if (lotteryTriggerScriptId == null) {
			return other.lotteryTriggerScriptId == null;
		} else if (!lotteryTriggerScriptId.equals(other.lotteryTriggerScriptId)) {
			return false;
		}
		if (choicePrizeTableScriptId == null) {
			return other.choicePrizeTableScriptId == null;
		} else if (!choicePrizeTableScriptId.equals(other.choicePrizeTableScriptId)) {
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
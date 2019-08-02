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

package io.gs2.experience.model;

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
	/** ランクキャップ取得時 に実行されるスクリプト のGRN */
	protected String experienceCapScriptId;

	/**
	 * ランクキャップ取得時 に実行されるスクリプト のGRNを取得
	 *
	 * @return ランクキャップ取得時 に実行されるスクリプト のGRN
	 */
	public String getExperienceCapScriptId() {
		return experienceCapScriptId;
	}

	/**
	 * ランクキャップ取得時 に実行されるスクリプト のGRNを設定
	 *
	 * @param experienceCapScriptId ランクキャップ取得時 に実行されるスクリプト のGRN
	 */
	public void setExperienceCapScriptId(String experienceCapScriptId) {
		this.experienceCapScriptId = experienceCapScriptId;
	}

	/**
	 * ランクキャップ取得時 に実行されるスクリプト のGRNを設定
	 *
	 * @param experienceCapScriptId ランクキャップ取得時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withExperienceCapScriptId(String experienceCapScriptId) {
		this.experienceCapScriptId = experienceCapScriptId;
		return this;
	}
	/** 経験値変化時 に実行されるスクリプト のGRN */
	protected String changeExperienceTriggerScriptId;

	/**
	 * 経験値変化時 に実行されるスクリプト のGRNを取得
	 *
	 * @return 経験値変化時 に実行されるスクリプト のGRN
	 */
	public String getChangeExperienceTriggerScriptId() {
		return changeExperienceTriggerScriptId;
	}

	/**
	 * 経験値変化時 に実行されるスクリプト のGRNを設定
	 *
	 * @param changeExperienceTriggerScriptId 経験値変化時 に実行されるスクリプト のGRN
	 */
	public void setChangeExperienceTriggerScriptId(String changeExperienceTriggerScriptId) {
		this.changeExperienceTriggerScriptId = changeExperienceTriggerScriptId;
	}

	/**
	 * 経験値変化時 に実行されるスクリプト のGRNを設定
	 *
	 * @param changeExperienceTriggerScriptId 経験値変化時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withChangeExperienceTriggerScriptId(String changeExperienceTriggerScriptId) {
		this.changeExperienceTriggerScriptId = changeExperienceTriggerScriptId;
		return this;
	}
	/** 経験値変化完了時 に実行されるスクリプト のGRN */
	protected String changeExperienceDoneTriggerScriptId;

	/**
	 * 経験値変化完了時 に実行されるスクリプト のGRNを取得
	 *
	 * @return 経験値変化完了時 に実行されるスクリプト のGRN
	 */
	public String getChangeExperienceDoneTriggerScriptId() {
		return changeExperienceDoneTriggerScriptId;
	}

	/**
	 * 経験値変化完了時 に実行されるスクリプト のGRNを設定
	 *
	 * @param changeExperienceDoneTriggerScriptId 経験値変化完了時 に実行されるスクリプト のGRN
	 */
	public void setChangeExperienceDoneTriggerScriptId(String changeExperienceDoneTriggerScriptId) {
		this.changeExperienceDoneTriggerScriptId = changeExperienceDoneTriggerScriptId;
	}

	/**
	 * 経験値変化完了時 に実行されるスクリプト のGRNを設定
	 *
	 * @param changeExperienceDoneTriggerScriptId 経験値変化完了時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withChangeExperienceDoneTriggerScriptId(String changeExperienceDoneTriggerScriptId) {
		this.changeExperienceDoneTriggerScriptId = changeExperienceDoneTriggerScriptId;
		return this;
	}
	/** 経験値変化完了時 にジョブが登録されるネームスペース のGRN */
	protected String changeExperienceDoneTriggerNamespaceId;

	/**
	 * 経験値変化完了時 にジョブが登録されるネームスペース のGRNを取得
	 *
	 * @return 経験値変化完了時 にジョブが登録されるネームスペース のGRN
	 */
	public String getChangeExperienceDoneTriggerNamespaceId() {
		return changeExperienceDoneTriggerNamespaceId;
	}

	/**
	 * 経験値変化完了時 にジョブが登録されるネームスペース のGRNを設定
	 *
	 * @param changeExperienceDoneTriggerNamespaceId 経験値変化完了時 にジョブが登録されるネームスペース のGRN
	 */
	public void setChangeExperienceDoneTriggerNamespaceId(String changeExperienceDoneTriggerNamespaceId) {
		this.changeExperienceDoneTriggerNamespaceId = changeExperienceDoneTriggerNamespaceId;
	}

	/**
	 * 経験値変化完了時 にジョブが登録されるネームスペース のGRNを設定
	 *
	 * @param changeExperienceDoneTriggerNamespaceId 経験値変化完了時 にジョブが登録されるネームスペース のGRN
	 * @return this
	 */
	public Namespace withChangeExperienceDoneTriggerNamespaceId(String changeExperienceDoneTriggerNamespaceId) {
		this.changeExperienceDoneTriggerNamespaceId = changeExperienceDoneTriggerNamespaceId;
		return this;
	}
	/** ランク変化時 に実行されるスクリプト のGRN */
	protected String changeRankTriggerScriptId;

	/**
	 * ランク変化時 に実行されるスクリプト のGRNを取得
	 *
	 * @return ランク変化時 に実行されるスクリプト のGRN
	 */
	public String getChangeRankTriggerScriptId() {
		return changeRankTriggerScriptId;
	}

	/**
	 * ランク変化時 に実行されるスクリプト のGRNを設定
	 *
	 * @param changeRankTriggerScriptId ランク変化時 に実行されるスクリプト のGRN
	 */
	public void setChangeRankTriggerScriptId(String changeRankTriggerScriptId) {
		this.changeRankTriggerScriptId = changeRankTriggerScriptId;
	}

	/**
	 * ランク変化時 に実行されるスクリプト のGRNを設定
	 *
	 * @param changeRankTriggerScriptId ランク変化時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withChangeRankTriggerScriptId(String changeRankTriggerScriptId) {
		this.changeRankTriggerScriptId = changeRankTriggerScriptId;
		return this;
	}
	/** ランク変化時 にジョブが登録されるネームスペース のGRN */
	protected String changeRankTriggerNamespaceId;

	/**
	 * ランク変化時 にジョブが登録されるネームスペース のGRNを取得
	 *
	 * @return ランク変化時 にジョブが登録されるネームスペース のGRN
	 */
	public String getChangeRankTriggerNamespaceId() {
		return changeRankTriggerNamespaceId;
	}

	/**
	 * ランク変化時 にジョブが登録されるネームスペース のGRNを設定
	 *
	 * @param changeRankTriggerNamespaceId ランク変化時 にジョブが登録されるネームスペース のGRN
	 */
	public void setChangeRankTriggerNamespaceId(String changeRankTriggerNamespaceId) {
		this.changeRankTriggerNamespaceId = changeRankTriggerNamespaceId;
	}

	/**
	 * ランク変化時 にジョブが登録されるネームスペース のGRNを設定
	 *
	 * @param changeRankTriggerNamespaceId ランク変化時 にジョブが登録されるネームスペース のGRN
	 * @return this
	 */
	public Namespace withChangeRankTriggerNamespaceId(String changeRankTriggerNamespaceId) {
		this.changeRankTriggerNamespaceId = changeRankTriggerNamespaceId;
		return this;
	}
	/** ランクキャップ変化時 に実行されるスクリプト のGRN */
	protected String changeRankCapTriggerScriptId;

	/**
	 * ランクキャップ変化時 に実行されるスクリプト のGRNを取得
	 *
	 * @return ランクキャップ変化時 に実行されるスクリプト のGRN
	 */
	public String getChangeRankCapTriggerScriptId() {
		return changeRankCapTriggerScriptId;
	}

	/**
	 * ランクキャップ変化時 に実行されるスクリプト のGRNを設定
	 *
	 * @param changeRankCapTriggerScriptId ランクキャップ変化時 に実行されるスクリプト のGRN
	 */
	public void setChangeRankCapTriggerScriptId(String changeRankCapTriggerScriptId) {
		this.changeRankCapTriggerScriptId = changeRankCapTriggerScriptId;
	}

	/**
	 * ランクキャップ変化時 に実行されるスクリプト のGRNを設定
	 *
	 * @param changeRankCapTriggerScriptId ランクキャップ変化時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withChangeRankCapTriggerScriptId(String changeRankCapTriggerScriptId) {
		this.changeRankCapTriggerScriptId = changeRankCapTriggerScriptId;
		return this;
	}
	/** ランクキャップ変化完了時 に実行されるスクリプト のGRN */
	protected String changeRankCapDoneTriggerScriptId;

	/**
	 * ランクキャップ変化完了時 に実行されるスクリプト のGRNを取得
	 *
	 * @return ランクキャップ変化完了時 に実行されるスクリプト のGRN
	 */
	public String getChangeRankCapDoneTriggerScriptId() {
		return changeRankCapDoneTriggerScriptId;
	}

	/**
	 * ランクキャップ変化完了時 に実行されるスクリプト のGRNを設定
	 *
	 * @param changeRankCapDoneTriggerScriptId ランクキャップ変化完了時 に実行されるスクリプト のGRN
	 */
	public void setChangeRankCapDoneTriggerScriptId(String changeRankCapDoneTriggerScriptId) {
		this.changeRankCapDoneTriggerScriptId = changeRankCapDoneTriggerScriptId;
	}

	/**
	 * ランクキャップ変化完了時 に実行されるスクリプト のGRNを設定
	 *
	 * @param changeRankCapDoneTriggerScriptId ランクキャップ変化完了時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withChangeRankCapDoneTriggerScriptId(String changeRankCapDoneTriggerScriptId) {
		this.changeRankCapDoneTriggerScriptId = changeRankCapDoneTriggerScriptId;
		return this;
	}
	/** ランクキャップ変化完了時 にジョブが登録されるネームスペース のGRN */
	protected String changeRankCapDoneTriggerNamespaceId;

	/**
	 * ランクキャップ変化完了時 にジョブが登録されるネームスペース のGRNを取得
	 *
	 * @return ランクキャップ変化完了時 にジョブが登録されるネームスペース のGRN
	 */
	public String getChangeRankCapDoneTriggerNamespaceId() {
		return changeRankCapDoneTriggerNamespaceId;
	}

	/**
	 * ランクキャップ変化完了時 にジョブが登録されるネームスペース のGRNを設定
	 *
	 * @param changeRankCapDoneTriggerNamespaceId ランクキャップ変化完了時 にジョブが登録されるネームスペース のGRN
	 */
	public void setChangeRankCapDoneTriggerNamespaceId(String changeRankCapDoneTriggerNamespaceId) {
		this.changeRankCapDoneTriggerNamespaceId = changeRankCapDoneTriggerNamespaceId;
	}

	/**
	 * ランクキャップ変化完了時 にジョブが登録されるネームスペース のGRNを設定
	 *
	 * @param changeRankCapDoneTriggerNamespaceId ランクキャップ変化完了時 にジョブが登録されるネームスペース のGRN
	 * @return this
	 */
	public Namespace withChangeRankCapDoneTriggerNamespaceId(String changeRankCapDoneTriggerNamespaceId) {
		this.changeRankCapDoneTriggerNamespaceId = changeRankCapDoneTriggerNamespaceId;
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
            .put("experienceCapScriptId", this.getExperienceCapScriptId())
            .put("changeExperienceTriggerScriptId", this.getChangeExperienceTriggerScriptId())
            .put("changeExperienceDoneTriggerScriptId", this.getChangeExperienceDoneTriggerScriptId())
            .put("changeExperienceDoneTriggerNamespaceId", this.getChangeExperienceDoneTriggerNamespaceId())
            .put("changeRankTriggerScriptId", this.getChangeRankTriggerScriptId())
            .put("changeRankTriggerNamespaceId", this.getChangeRankTriggerNamespaceId())
            .put("changeRankCapTriggerScriptId", this.getChangeRankCapTriggerScriptId())
            .put("changeRankCapDoneTriggerScriptId", this.getChangeRankCapDoneTriggerScriptId())
            .put("changeRankCapDoneTriggerNamespaceId", this.getChangeRankCapDoneTriggerNamespaceId())
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
        result = prime * result + ((this.experienceCapScriptId == null) ? 0 : this.experienceCapScriptId.hashCode());
        result = prime * result + ((this.changeExperienceTriggerScriptId == null) ? 0 : this.changeExperienceTriggerScriptId.hashCode());
        result = prime * result + ((this.changeExperienceDoneTriggerScriptId == null) ? 0 : this.changeExperienceDoneTriggerScriptId.hashCode());
        result = prime * result + ((this.changeExperienceDoneTriggerNamespaceId == null) ? 0 : this.changeExperienceDoneTriggerNamespaceId.hashCode());
        result = prime * result + ((this.changeRankTriggerScriptId == null) ? 0 : this.changeRankTriggerScriptId.hashCode());
        result = prime * result + ((this.changeRankTriggerNamespaceId == null) ? 0 : this.changeRankTriggerNamespaceId.hashCode());
        result = prime * result + ((this.changeRankCapTriggerScriptId == null) ? 0 : this.changeRankCapTriggerScriptId.hashCode());
        result = prime * result + ((this.changeRankCapDoneTriggerScriptId == null) ? 0 : this.changeRankCapDoneTriggerScriptId.hashCode());
        result = prime * result + ((this.changeRankCapDoneTriggerNamespaceId == null) ? 0 : this.changeRankCapDoneTriggerNamespaceId.hashCode());
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
		if (experienceCapScriptId == null) {
			return other.experienceCapScriptId == null;
		} else if (!experienceCapScriptId.equals(other.experienceCapScriptId)) {
			return false;
		}
		if (changeExperienceTriggerScriptId == null) {
			return other.changeExperienceTriggerScriptId == null;
		} else if (!changeExperienceTriggerScriptId.equals(other.changeExperienceTriggerScriptId)) {
			return false;
		}
		if (changeExperienceDoneTriggerScriptId == null) {
			return other.changeExperienceDoneTriggerScriptId == null;
		} else if (!changeExperienceDoneTriggerScriptId.equals(other.changeExperienceDoneTriggerScriptId)) {
			return false;
		}
		if (changeExperienceDoneTriggerNamespaceId == null) {
			return other.changeExperienceDoneTriggerNamespaceId == null;
		} else if (!changeExperienceDoneTriggerNamespaceId.equals(other.changeExperienceDoneTriggerNamespaceId)) {
			return false;
		}
		if (changeRankTriggerScriptId == null) {
			return other.changeRankTriggerScriptId == null;
		} else if (!changeRankTriggerScriptId.equals(other.changeRankTriggerScriptId)) {
			return false;
		}
		if (changeRankTriggerNamespaceId == null) {
			return other.changeRankTriggerNamespaceId == null;
		} else if (!changeRankTriggerNamespaceId.equals(other.changeRankTriggerNamespaceId)) {
			return false;
		}
		if (changeRankCapTriggerScriptId == null) {
			return other.changeRankCapTriggerScriptId == null;
		} else if (!changeRankCapTriggerScriptId.equals(other.changeRankCapTriggerScriptId)) {
			return false;
		}
		if (changeRankCapDoneTriggerScriptId == null) {
			return other.changeRankCapDoneTriggerScriptId == null;
		} else if (!changeRankCapDoneTriggerScriptId.equals(other.changeRankCapDoneTriggerScriptId)) {
			return false;
		}
		if (changeRankCapDoneTriggerNamespaceId == null) {
			return other.changeRankCapDoneTriggerNamespaceId == null;
		} else if (!changeRankCapDoneTriggerNamespaceId.equals(other.changeRankCapDoneTriggerNamespaceId)) {
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
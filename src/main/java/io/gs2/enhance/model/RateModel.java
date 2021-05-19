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

package io.gs2.enhance.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * 強化レートモデル
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class RateModel implements IModel, Serializable, Comparable<RateModel> {
	/** 強化レートモデル */
	protected String rateModelId;

	/**
	 * 強化レートモデルを取得
	 *
	 * @return 強化レートモデル
	 */
	public String getRateModelId() {
		return rateModelId;
	}

	/**
	 * 強化レートモデルを設定
	 *
	 * @param rateModelId 強化レートモデル
	 */
	public void setRateModelId(String rateModelId) {
		this.rateModelId = rateModelId;
	}

	/**
	 * 強化レートモデルを設定
	 *
	 * @param rateModelId 強化レートモデル
	 * @return this
	 */
	public RateModel withRateModelId(String rateModelId) {
		this.rateModelId = rateModelId;
		return this;
	}
	/** 強化レート名 */
	protected String name;

	/**
	 * 強化レート名を取得
	 *
	 * @return 強化レート名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 強化レート名を設定
	 *
	 * @param name 強化レート名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 強化レート名を設定
	 *
	 * @param name 強化レート名
	 * @return this
	 */
	public RateModel withName(String name) {
		this.name = name;
		return this;
	}
	/** 強化レートの説明 */
	protected String description;

	/**
	 * 強化レートの説明を取得
	 *
	 * @return 強化レートの説明
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 強化レートの説明を設定
	 *
	 * @param description 強化レートの説明
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 強化レートの説明を設定
	 *
	 * @param description 強化レートの説明
	 * @return this
	 */
	public RateModel withDescription(String description) {
		this.description = description;
		return this;
	}
	/** 強化レートのメタデータ */
	protected String metadata;

	/**
	 * 強化レートのメタデータを取得
	 *
	 * @return 強化レートのメタデータ
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * 強化レートのメタデータを設定
	 *
	 * @param metadata 強化レートのメタデータ
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	/**
	 * 強化レートのメタデータを設定
	 *
	 * @param metadata 強化レートのメタデータ
	 * @return this
	 */
	public RateModel withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	/** 強化対象に使用できるインベントリモデル のGRN */
	protected String targetInventoryModelId;

	/**
	 * 強化対象に使用できるインベントリモデル のGRNを取得
	 *
	 * @return 強化対象に使用できるインベントリモデル のGRN
	 */
	public String getTargetInventoryModelId() {
		return targetInventoryModelId;
	}

	/**
	 * 強化対象に使用できるインベントリモデル のGRNを設定
	 *
	 * @param targetInventoryModelId 強化対象に使用できるインベントリモデル のGRN
	 */
	public void setTargetInventoryModelId(String targetInventoryModelId) {
		this.targetInventoryModelId = targetInventoryModelId;
	}

	/**
	 * 強化対象に使用できるインベントリモデル のGRNを設定
	 *
	 * @param targetInventoryModelId 強化対象に使用できるインベントリモデル のGRN
	 * @return this
	 */
	public RateModel withTargetInventoryModelId(String targetInventoryModelId) {
		this.targetInventoryModelId = targetInventoryModelId;
		return this;
	}
	/** GS2-Experience で入手した経験値を格納する プロパティID に付与するサフィックス */
	protected String acquireExperienceSuffix;

	/**
	 * GS2-Experience で入手した経験値を格納する プロパティID に付与するサフィックスを取得
	 *
	 * @return GS2-Experience で入手した経験値を格納する プロパティID に付与するサフィックス
	 */
	public String getAcquireExperienceSuffix() {
		return acquireExperienceSuffix;
	}

	/**
	 * GS2-Experience で入手した経験値を格納する プロパティID に付与するサフィックスを設定
	 *
	 * @param acquireExperienceSuffix GS2-Experience で入手した経験値を格納する プロパティID に付与するサフィックス
	 */
	public void setAcquireExperienceSuffix(String acquireExperienceSuffix) {
		this.acquireExperienceSuffix = acquireExperienceSuffix;
	}

	/**
	 * GS2-Experience で入手した経験値を格納する プロパティID に付与するサフィックスを設定
	 *
	 * @param acquireExperienceSuffix GS2-Experience で入手した経験値を格納する プロパティID に付与するサフィックス
	 * @return this
	 */
	public RateModel withAcquireExperienceSuffix(String acquireExperienceSuffix) {
		this.acquireExperienceSuffix = acquireExperienceSuffix;
		return this;
	}
	/** 強化素材に使用できるインベントリモデル のGRN */
	protected String materialInventoryModelId;

	/**
	 * 強化素材に使用できるインベントリモデル のGRNを取得
	 *
	 * @return 強化素材に使用できるインベントリモデル のGRN
	 */
	public String getMaterialInventoryModelId() {
		return materialInventoryModelId;
	}

	/**
	 * 強化素材に使用できるインベントリモデル のGRNを設定
	 *
	 * @param materialInventoryModelId 強化素材に使用できるインベントリモデル のGRN
	 */
	public void setMaterialInventoryModelId(String materialInventoryModelId) {
		this.materialInventoryModelId = materialInventoryModelId;
	}

	/**
	 * 強化素材に使用できるインベントリモデル のGRNを設定
	 *
	 * @param materialInventoryModelId 強化素材に使用できるインベントリモデル のGRN
	 * @return this
	 */
	public RateModel withMaterialInventoryModelId(String materialInventoryModelId) {
		this.materialInventoryModelId = materialInventoryModelId;
		return this;
	}
	/** 入手経験値を格納しているメタデータのJSON階層 */
	protected List<String> acquireExperienceHierarchy;

	/**
	 * 入手経験値を格納しているメタデータのJSON階層を取得
	 *
	 * @return 入手経験値を格納しているメタデータのJSON階層
	 */
	public List<String> getAcquireExperienceHierarchy() {
		return acquireExperienceHierarchy;
	}

	/**
	 * 入手経験値を格納しているメタデータのJSON階層を設定
	 *
	 * @param acquireExperienceHierarchy 入手経験値を格納しているメタデータのJSON階層
	 */
	public void setAcquireExperienceHierarchy(List<String> acquireExperienceHierarchy) {
		this.acquireExperienceHierarchy = acquireExperienceHierarchy;
	}

	/**
	 * 入手経験値を格納しているメタデータのJSON階層を設定
	 *
	 * @param acquireExperienceHierarchy 入手経験値を格納しているメタデータのJSON階層
	 * @return this
	 */
	public RateModel withAcquireExperienceHierarchy(List<String> acquireExperienceHierarchy) {
		this.acquireExperienceHierarchy = acquireExperienceHierarchy;
		return this;
	}
	/** 獲得できる経験値の種類マスター のGRN */
	protected String experienceModelId;

	/**
	 * 獲得できる経験値の種類マスター のGRNを取得
	 *
	 * @return 獲得できる経験値の種類マスター のGRN
	 */
	public String getExperienceModelId() {
		return experienceModelId;
	}

	/**
	 * 獲得できる経験値の種類マスター のGRNを設定
	 *
	 * @param experienceModelId 獲得できる経験値の種類マスター のGRN
	 */
	public void setExperienceModelId(String experienceModelId) {
		this.experienceModelId = experienceModelId;
	}

	/**
	 * 獲得できる経験値の種類マスター のGRNを設定
	 *
	 * @param experienceModelId 獲得できる経験値の種類マスター のGRN
	 * @return this
	 */
	public RateModel withExperienceModelId(String experienceModelId) {
		this.experienceModelId = experienceModelId;
		return this;
	}
	/** 経験値獲得量ボーナス */
	protected List<BonusRate> bonusRates;

	/**
	 * 経験値獲得量ボーナスを取得
	 *
	 * @return 経験値獲得量ボーナス
	 */
	public List<BonusRate> getBonusRates() {
		return bonusRates;
	}

	/**
	 * 経験値獲得量ボーナスを設定
	 *
	 * @param bonusRates 経験値獲得量ボーナス
	 */
	public void setBonusRates(List<BonusRate> bonusRates) {
		this.bonusRates = bonusRates;
	}

	/**
	 * 経験値獲得量ボーナスを設定
	 *
	 * @param bonusRates 経験値獲得量ボーナス
	 * @return this
	 */
	public RateModel withBonusRates(List<BonusRate> bonusRates) {
		this.bonusRates = bonusRates;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> acquireExperienceHierarchy = new ArrayList<>();
        if(this.acquireExperienceHierarchy != null) {
            for(String item : this.acquireExperienceHierarchy) {
                acquireExperienceHierarchy.add(JsonNodeFactory.instance.textNode(item));
            }
        }
        List<JsonNode> bonusRates = new ArrayList<>();
        if(this.bonusRates != null) {
            for(BonusRate item : this.bonusRates) {
                bonusRates.add(item.toJson());
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("rateModelId", this.getRateModelId())
            .put("name", this.getName())
            .put("description", this.getDescription())
            .put("metadata", this.getMetadata())
            .put("targetInventoryModelId", this.getTargetInventoryModelId())
            .put("acquireExperienceSuffix", this.getAcquireExperienceSuffix())
            .put("materialInventoryModelId", this.getMaterialInventoryModelId())
            .put("experienceModelId", this.getExperienceModelId());
        body_.set("acquireExperienceHierarchy", JsonNodeFactory.instance.arrayNode().addAll(acquireExperienceHierarchy));
        body_.set("bonusRates", JsonNodeFactory.instance.arrayNode().addAll(bonusRates));
        return body_;
    }
	@Override
	public int compareTo(RateModel o) {
		return rateModelId.compareTo(o.rateModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.rateModelId == null) ? 0 : this.rateModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.targetInventoryModelId == null) ? 0 : this.targetInventoryModelId.hashCode());
        result = prime * result + ((this.acquireExperienceSuffix == null) ? 0 : this.acquireExperienceSuffix.hashCode());
        result = prime * result + ((this.materialInventoryModelId == null) ? 0 : this.materialInventoryModelId.hashCode());
        result = prime * result + ((this.acquireExperienceHierarchy == null) ? 0 : this.acquireExperienceHierarchy.hashCode());
        result = prime * result + ((this.experienceModelId == null) ? 0 : this.experienceModelId.hashCode());
        result = prime * result + ((this.bonusRates == null) ? 0 : this.bonusRates.hashCode());
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
		RateModel other = (RateModel) o;
		if (rateModelId == null) {
			return other.rateModelId == null;
		} else if (!rateModelId.equals(other.rateModelId)) {
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
		if (metadata == null) {
			return other.metadata == null;
		} else if (!metadata.equals(other.metadata)) {
			return false;
		}
		if (targetInventoryModelId == null) {
			return other.targetInventoryModelId == null;
		} else if (!targetInventoryModelId.equals(other.targetInventoryModelId)) {
			return false;
		}
		if (acquireExperienceSuffix == null) {
			return other.acquireExperienceSuffix == null;
		} else if (!acquireExperienceSuffix.equals(other.acquireExperienceSuffix)) {
			return false;
		}
		if (materialInventoryModelId == null) {
			return other.materialInventoryModelId == null;
		} else if (!materialInventoryModelId.equals(other.materialInventoryModelId)) {
			return false;
		}
		if (acquireExperienceHierarchy == null) {
			return other.acquireExperienceHierarchy == null;
		} else if (!acquireExperienceHierarchy.equals(other.acquireExperienceHierarchy)) {
			return false;
		}
		if (experienceModelId == null) {
			return other.experienceModelId == null;
		} else if (!experienceModelId.equals(other.experienceModelId)) {
			return false;
		}
		if (bonusRates == null) {
			return other.bonusRates == null;
		} else if (!bonusRates.equals(other.bonusRates)) {
			return false;
		}
		return true;
	}
}
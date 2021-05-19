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

package io.gs2.matchmaking.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * ギャザリング
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Gathering implements IModel, Serializable, Comparable<Gathering> {
	/** ギャザリング */
	protected String gatheringId;

	/**
	 * ギャザリングを取得
	 *
	 * @return ギャザリング
	 */
	public String getGatheringId() {
		return gatheringId;
	}

	/**
	 * ギャザリングを設定
	 *
	 * @param gatheringId ギャザリング
	 */
	public void setGatheringId(String gatheringId) {
		this.gatheringId = gatheringId;
	}

	/**
	 * ギャザリングを設定
	 *
	 * @param gatheringId ギャザリング
	 * @return this
	 */
	public Gathering withGatheringId(String gatheringId) {
		this.gatheringId = gatheringId;
		return this;
	}
	/** ギャザリング名 */
	protected String name;

	/**
	 * ギャザリング名を取得
	 *
	 * @return ギャザリング名
	 */
	public String getName() {
		return name;
	}

	/**
	 * ギャザリング名を設定
	 *
	 * @param name ギャザリング名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ギャザリング名を設定
	 *
	 * @param name ギャザリング名
	 * @return this
	 */
	public Gathering withName(String name) {
		this.name = name;
		return this;
	}
	/** 募集条件 */
	protected List<AttributeRange> attributeRanges;

	/**
	 * 募集条件を取得
	 *
	 * @return 募集条件
	 */
	public List<AttributeRange> getAttributeRanges() {
		return attributeRanges;
	}

	/**
	 * 募集条件を設定
	 *
	 * @param attributeRanges 募集条件
	 */
	public void setAttributeRanges(List<AttributeRange> attributeRanges) {
		this.attributeRanges = attributeRanges;
	}

	/**
	 * 募集条件を設定
	 *
	 * @param attributeRanges 募集条件
	 * @return this
	 */
	public Gathering withAttributeRanges(List<AttributeRange> attributeRanges) {
		this.attributeRanges = attributeRanges;
		return this;
	}
	/** 参加者 */
	protected List<CapacityOfRole> capacityOfRoles;

	/**
	 * 参加者を取得
	 *
	 * @return 参加者
	 */
	public List<CapacityOfRole> getCapacityOfRoles() {
		return capacityOfRoles;
	}

	/**
	 * 参加者を設定
	 *
	 * @param capacityOfRoles 参加者
	 */
	public void setCapacityOfRoles(List<CapacityOfRole> capacityOfRoles) {
		this.capacityOfRoles = capacityOfRoles;
	}

	/**
	 * 参加者を設定
	 *
	 * @param capacityOfRoles 参加者
	 * @return this
	 */
	public Gathering withCapacityOfRoles(List<CapacityOfRole> capacityOfRoles) {
		this.capacityOfRoles = capacityOfRoles;
		return this;
	}
	/** 参加を許可するユーザIDリスト */
	protected List<String> allowUserIds;

	/**
	 * 参加を許可するユーザIDリストを取得
	 *
	 * @return 参加を許可するユーザIDリスト
	 */
	public List<String> getAllowUserIds() {
		return allowUserIds;
	}

	/**
	 * 参加を許可するユーザIDリストを設定
	 *
	 * @param allowUserIds 参加を許可するユーザIDリスト
	 */
	public void setAllowUserIds(List<String> allowUserIds) {
		this.allowUserIds = allowUserIds;
	}

	/**
	 * 参加を許可するユーザIDリストを設定
	 *
	 * @param allowUserIds 参加を許可するユーザIDリスト
	 * @return this
	 */
	public Gathering withAllowUserIds(List<String> allowUserIds) {
		this.allowUserIds = allowUserIds;
		return this;
	}
	/** メタデータ */
	protected String metadata;

	/**
	 * メタデータを取得
	 *
	 * @return メタデータ
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * メタデータを設定
	 *
	 * @param metadata メタデータ
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	/**
	 * メタデータを設定
	 *
	 * @param metadata メタデータ
	 * @return this
	 */
	public Gathering withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	/** ギャザリングの有効期限 */
	protected Long expiresAt;

	/**
	 * ギャザリングの有効期限を取得
	 *
	 * @return ギャザリングの有効期限
	 */
	public Long getExpiresAt() {
		return expiresAt;
	}

	/**
	 * ギャザリングの有効期限を設定
	 *
	 * @param expiresAt ギャザリングの有効期限
	 */
	public void setExpiresAt(Long expiresAt) {
		this.expiresAt = expiresAt;
	}

	/**
	 * ギャザリングの有効期限を設定
	 *
	 * @param expiresAt ギャザリングの有効期限
	 * @return this
	 */
	public Gathering withExpiresAt(Long expiresAt) {
		this.expiresAt = expiresAt;
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
	public Gathering withCreatedAt(Long createdAt) {
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
	public Gathering withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> attributeRanges = new ArrayList<>();
        if(this.attributeRanges != null) {
            for(AttributeRange item : this.attributeRanges) {
                attributeRanges.add(item.toJson());
            }
        }
        List<JsonNode> capacityOfRoles = new ArrayList<>();
        if(this.capacityOfRoles != null) {
            for(CapacityOfRole item : this.capacityOfRoles) {
                capacityOfRoles.add(item.toJson());
            }
        }
        List<JsonNode> allowUserIds = new ArrayList<>();
        if(this.allowUserIds != null) {
            for(String item : this.allowUserIds) {
                allowUserIds.add(JsonNodeFactory.instance.textNode(item));
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("gatheringId", this.getGatheringId())
            .put("name", this.getName())
            .put("metadata", this.getMetadata())
            .put("expiresAt", this.getExpiresAt())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        body_.set("attributeRanges", JsonNodeFactory.instance.arrayNode().addAll(attributeRanges));
        body_.set("capacityOfRoles", JsonNodeFactory.instance.arrayNode().addAll(capacityOfRoles));
        body_.set("allowUserIds", JsonNodeFactory.instance.arrayNode().addAll(allowUserIds));
        return body_;
    }
	@Override
	public int compareTo(Gathering o) {
		return gatheringId.compareTo(o.gatheringId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.gatheringId == null) ? 0 : this.gatheringId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.attributeRanges == null) ? 0 : this.attributeRanges.hashCode());
        result = prime * result + ((this.capacityOfRoles == null) ? 0 : this.capacityOfRoles.hashCode());
        result = prime * result + ((this.allowUserIds == null) ? 0 : this.allowUserIds.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.expiresAt == null) ? 0 : this.expiresAt.hashCode());
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
		Gathering other = (Gathering) o;
		if (gatheringId == null) {
			return other.gatheringId == null;
		} else if (!gatheringId.equals(other.gatheringId)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (attributeRanges == null) {
			return other.attributeRanges == null;
		} else if (!attributeRanges.equals(other.attributeRanges)) {
			return false;
		}
		if (capacityOfRoles == null) {
			return other.capacityOfRoles == null;
		} else if (!capacityOfRoles.equals(other.capacityOfRoles)) {
			return false;
		}
		if (allowUserIds == null) {
			return other.allowUserIds == null;
		} else if (!allowUserIds.equals(other.allowUserIds)) {
			return false;
		}
		if (metadata == null) {
			return other.metadata == null;
		} else if (!metadata.equals(other.metadata)) {
			return false;
		}
		if (expiresAt == null) {
			return other.expiresAt == null;
		} else if (!expiresAt.equals(other.expiresAt)) {
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
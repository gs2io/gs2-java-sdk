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

package io.gs2.distributor.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * 配信設定マスター
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class DistributorModelMaster implements IModel, Serializable, Comparable<DistributorModelMaster> {
	/** 配信設定マスター */
	protected String distributorModelId;

	/**
	 * 配信設定マスターを取得
	 *
	 * @return 配信設定マスター
	 */
	public String getDistributorModelId() {
		return distributorModelId;
	}

	/**
	 * 配信設定マスターを設定
	 *
	 * @param distributorModelId 配信設定マスター
	 */
	public void setDistributorModelId(String distributorModelId) {
		this.distributorModelId = distributorModelId;
	}

	/**
	 * 配信設定マスターを設定
	 *
	 * @param distributorModelId 配信設定マスター
	 * @return this
	 */
	public DistributorModelMaster withDistributorModelId(String distributorModelId) {
		this.distributorModelId = distributorModelId;
		return this;
	}
	/** 配信設定名 */
	protected String name;

	/**
	 * 配信設定名を取得
	 *
	 * @return 配信設定名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 配信設定名を設定
	 *
	 * @param name 配信設定名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 配信設定名を設定
	 *
	 * @param name 配信設定名
	 * @return this
	 */
	public DistributorModelMaster withName(String name) {
		this.name = name;
		return this;
	}
	/** 配信設定マスターの説明 */
	protected String description;

	/**
	 * 配信設定マスターの説明を取得
	 *
	 * @return 配信設定マスターの説明
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 配信設定マスターの説明を設定
	 *
	 * @param description 配信設定マスターの説明
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 配信設定マスターの説明を設定
	 *
	 * @param description 配信設定マスターの説明
	 * @return this
	 */
	public DistributorModelMaster withDescription(String description) {
		this.description = description;
		return this;
	}
	/** 配信設定のメタデータ */
	protected String metadata;

	/**
	 * 配信設定のメタデータを取得
	 *
	 * @return 配信設定のメタデータ
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * 配信設定のメタデータを設定
	 *
	 * @param metadata 配信設定のメタデータ
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	/**
	 * 配信設定のメタデータを設定
	 *
	 * @param metadata 配信設定のメタデータ
	 * @return this
	 */
	public DistributorModelMaster withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	/** 所持品がキャパシティをオーバーしたときに転送するプレゼントボックスのネームスペース のGRN */
	protected String inboxNamespaceId;

	/**
	 * 所持品がキャパシティをオーバーしたときに転送するプレゼントボックスのネームスペース のGRNを取得
	 *
	 * @return 所持品がキャパシティをオーバーしたときに転送するプレゼントボックスのネームスペース のGRN
	 */
	public String getInboxNamespaceId() {
		return inboxNamespaceId;
	}

	/**
	 * 所持品がキャパシティをオーバーしたときに転送するプレゼントボックスのネームスペース のGRNを設定
	 *
	 * @param inboxNamespaceId 所持品がキャパシティをオーバーしたときに転送するプレゼントボックスのネームスペース のGRN
	 */
	public void setInboxNamespaceId(String inboxNamespaceId) {
		this.inboxNamespaceId = inboxNamespaceId;
	}

	/**
	 * 所持品がキャパシティをオーバーしたときに転送するプレゼントボックスのネームスペース のGRNを設定
	 *
	 * @param inboxNamespaceId 所持品がキャパシティをオーバーしたときに転送するプレゼントボックスのネームスペース のGRN
	 * @return this
	 */
	public DistributorModelMaster withInboxNamespaceId(String inboxNamespaceId) {
		this.inboxNamespaceId = inboxNamespaceId;
		return this;
	}
	/** ディストリビューターを通して処理出来る対象のリソースGRNのホワイトリスト */
	protected List<String> whiteListTargetIds;

	/**
	 * ディストリビューターを通して処理出来る対象のリソースGRNのホワイトリストを取得
	 *
	 * @return ディストリビューターを通して処理出来る対象のリソースGRNのホワイトリスト
	 */
	public List<String> getWhiteListTargetIds() {
		return whiteListTargetIds;
	}

	/**
	 * ディストリビューターを通して処理出来る対象のリソースGRNのホワイトリストを設定
	 *
	 * @param whiteListTargetIds ディストリビューターを通して処理出来る対象のリソースGRNのホワイトリスト
	 */
	public void setWhiteListTargetIds(List<String> whiteListTargetIds) {
		this.whiteListTargetIds = whiteListTargetIds;
	}

	/**
	 * ディストリビューターを通して処理出来る対象のリソースGRNのホワイトリストを設定
	 *
	 * @param whiteListTargetIds ディストリビューターを通して処理出来る対象のリソースGRNのホワイトリスト
	 * @return this
	 */
	public DistributorModelMaster withWhiteListTargetIds(List<String> whiteListTargetIds) {
		this.whiteListTargetIds = whiteListTargetIds;
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
	public DistributorModelMaster withCreatedAt(Long createdAt) {
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
	public DistributorModelMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> whiteListTargetIds = new ArrayList<>();
        if(this.whiteListTargetIds != null) {
            for(String item : this.whiteListTargetIds) {
                whiteListTargetIds.add(JsonNodeFactory.instance.textNode(item));
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("distributorModelId", this.getDistributorModelId())
            .put("name", this.getName())
            .put("description", this.getDescription())
            .put("metadata", this.getMetadata())
            .put("inboxNamespaceId", this.getInboxNamespaceId())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        body_.set("whiteListTargetIds", JsonNodeFactory.instance.arrayNode().addAll(whiteListTargetIds));
        return body_;
    }
	@Override
	public int compareTo(DistributorModelMaster o) {
		return distributorModelId.compareTo(o.distributorModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.distributorModelId == null) ? 0 : this.distributorModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.inboxNamespaceId == null) ? 0 : this.inboxNamespaceId.hashCode());
        result = prime * result + ((this.whiteListTargetIds == null) ? 0 : this.whiteListTargetIds.hashCode());
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
		DistributorModelMaster other = (DistributorModelMaster) o;
		if (distributorModelId == null) {
			return other.distributorModelId == null;
		} else if (!distributorModelId.equals(other.distributorModelId)) {
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
		if (inboxNamespaceId == null) {
			return other.inboxNamespaceId == null;
		} else if (!inboxNamespaceId.equals(other.inboxNamespaceId)) {
			return false;
		}
		if (whiteListTargetIds == null) {
			return other.whiteListTargetIds == null;
		} else if (!whiteListTargetIds.equals(other.whiteListTargetIds)) {
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
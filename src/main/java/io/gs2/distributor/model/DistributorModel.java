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
 * 配信設定
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class DistributorModel implements IModel, Serializable, Comparable<DistributorModel> {
	/** 配信設定 */
	protected String distributorModelId;

	/**
	 * 配信設定を取得
	 *
	 * @return 配信設定
	 */
	public String getDistributorModelId() {
		return distributorModelId;
	}

	/**
	 * 配信設定を設定
	 *
	 * @param distributorModelId 配信設定
	 */
	public void setDistributorModelId(String distributorModelId) {
		this.distributorModelId = distributorModelId;
	}

	/**
	 * 配信設定を設定
	 *
	 * @param distributorModelId 配信設定
	 * @return this
	 */
	public DistributorModel withDistributorModelId(String distributorModelId) {
		this.distributorModelId = distributorModelId;
		return this;
	}
	/** ディストリビューターの種類名 */
	protected String name;

	/**
	 * ディストリビューターの種類名を取得
	 *
	 * @return ディストリビューターの種類名
	 */
	public String getName() {
		return name;
	}

	/**
	 * ディストリビューターの種類名を設定
	 *
	 * @param name ディストリビューターの種類名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ディストリビューターの種類名を設定
	 *
	 * @param name ディストリビューターの種類名
	 * @return this
	 */
	public DistributorModel withName(String name) {
		this.name = name;
		return this;
	}
	/** ディストリビューターの種類のメタデータ */
	protected String metadata;

	/**
	 * ディストリビューターの種類のメタデータを取得
	 *
	 * @return ディストリビューターの種類のメタデータ
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * ディストリビューターの種類のメタデータを設定
	 *
	 * @param metadata ディストリビューターの種類のメタデータ
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	/**
	 * ディストリビューターの種類のメタデータを設定
	 *
	 * @param metadata ディストリビューターの種類のメタデータ
	 * @return this
	 */
	public DistributorModel withMetadata(String metadata) {
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
	public DistributorModel withInboxNamespaceId(String inboxNamespaceId) {
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
	public DistributorModel withWhiteListTargetIds(List<String> whiteListTargetIds) {
		this.whiteListTargetIds = whiteListTargetIds;
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
            .put("metadata", this.getMetadata())
            .put("inboxNamespaceId", this.getInboxNamespaceId());
        body_.set("whiteListTargetIds", JsonNodeFactory.instance.arrayNode().addAll(whiteListTargetIds));
        return body_;
    }
	@Override
	public int compareTo(DistributorModel o) {
		return distributorModelId.compareTo(o.distributorModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.distributorModelId == null) ? 0 : this.distributorModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.inboxNamespaceId == null) ? 0 : this.inboxNamespaceId.hashCode());
        result = prime * result + ((this.whiteListTargetIds == null) ? 0 : this.whiteListTargetIds.hashCode());
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
		DistributorModel other = (DistributorModel) o;
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
		return true;
	}
}
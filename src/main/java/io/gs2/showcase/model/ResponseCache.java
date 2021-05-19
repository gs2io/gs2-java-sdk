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

package io.gs2.showcase.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * レスポンスキャッシュ
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class ResponseCache implements IModel, Serializable, Comparable<ResponseCache> {
	/** None */
	protected String region;

	/**
	 * Noneを取得
	 *
	 * @return None
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * Noneを設定
	 *
	 * @param region None
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * Noneを設定
	 *
	 * @param region None
	 * @return this
	 */
	public ResponseCache withRegion(String region) {
		this.region = region;
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
	public ResponseCache withOwnerId(String ownerId) {
		this.ownerId = ownerId;
		return this;
	}
	/** レスポンスキャッシュ のGRN */
	protected String responseCacheId;

	/**
	 * レスポンスキャッシュ のGRNを取得
	 *
	 * @return レスポンスキャッシュ のGRN
	 */
	public String getResponseCacheId() {
		return responseCacheId;
	}

	/**
	 * レスポンスキャッシュ のGRNを設定
	 *
	 * @param responseCacheId レスポンスキャッシュ のGRN
	 */
	public void setResponseCacheId(String responseCacheId) {
		this.responseCacheId = responseCacheId;
	}

	/**
	 * レスポンスキャッシュ のGRNを設定
	 *
	 * @param responseCacheId レスポンスキャッシュ のGRN
	 * @return this
	 */
	public ResponseCache withResponseCacheId(String responseCacheId) {
		this.responseCacheId = responseCacheId;
		return this;
	}
	/** None */
	protected String requestHash;

	/**
	 * Noneを取得
	 *
	 * @return None
	 */
	public String getRequestHash() {
		return requestHash;
	}

	/**
	 * Noneを設定
	 *
	 * @param requestHash None
	 */
	public void setRequestHash(String requestHash) {
		this.requestHash = requestHash;
	}

	/**
	 * Noneを設定
	 *
	 * @param requestHash None
	 * @return this
	 */
	public ResponseCache withRequestHash(String requestHash) {
		this.requestHash = requestHash;
		return this;
	}
	/** APIの応答内容 */
	protected String result;

	/**
	 * APIの応答内容を取得
	 *
	 * @return APIの応答内容
	 */
	public String getResult() {
		return result;
	}

	/**
	 * APIの応答内容を設定
	 *
	 * @param result APIの応答内容
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * APIの応答内容を設定
	 *
	 * @param result APIの応答内容
	 * @return this
	 */
	public ResponseCache withResult(String result) {
		this.result = result;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("region", this.getRegion())
            .put("ownerId", this.getOwnerId())
            .put("responseCacheId", this.getResponseCacheId())
            .put("requestHash", this.getRequestHash())
            .put("result", this.getResult());
        return body_;
    }
	@Override
	public int compareTo(ResponseCache o) {
		return responseCacheId.compareTo(o.responseCacheId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.region == null) ? 0 : this.region.hashCode());
        result = prime * result + ((this.ownerId == null) ? 0 : this.ownerId.hashCode());
        result = prime * result + ((this.responseCacheId == null) ? 0 : this.responseCacheId.hashCode());
        result = prime * result + ((this.requestHash == null) ? 0 : this.requestHash.hashCode());
        result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
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
		ResponseCache other = (ResponseCache) o;
		if (region == null) {
			return other.region == null;
		} else if (!region.equals(other.region)) {
			return false;
		}
		if (ownerId == null) {
			return other.ownerId == null;
		} else if (!ownerId.equals(other.ownerId)) {
			return false;
		}
		if (responseCacheId == null) {
			return other.responseCacheId == null;
		} else if (!responseCacheId.equals(other.responseCacheId)) {
			return false;
		}
		if (requestHash == null) {
			return other.requestHash == null;
		} else if (!requestHash.equals(other.requestHash)) {
			return false;
		}
		if (result == null) {
			return other.result == null;
		} else if (!result.equals(other.result)) {
			return false;
		}
		return true;
	}
}
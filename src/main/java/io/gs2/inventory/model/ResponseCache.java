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

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.IModel;


@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class ResponseCache implements IModel, Serializable, Comparable<ResponseCache> {
	private String region;
	private String ownerId;
	private String responseCacheId;
	private String requestHash;
	private String result;

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public ResponseCache withRegion(String region) {
		this.region = region;
		return this;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public ResponseCache withOwnerId(String ownerId) {
		this.ownerId = ownerId;
		return this;
	}

	public String getResponseCacheId() {
		return responseCacheId;
	}

	public void setResponseCacheId(String responseCacheId) {
		this.responseCacheId = responseCacheId;
	}

	public ResponseCache withResponseCacheId(String responseCacheId) {
		this.responseCacheId = responseCacheId;
		return this;
	}

	public String getRequestHash() {
		return requestHash;
	}

	public void setRequestHash(String requestHash) {
		this.requestHash = requestHash;
	}

	public ResponseCache withRequestHash(String requestHash) {
		this.requestHash = requestHash;
		return this;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public ResponseCache withResult(String result) {
		this.result = result;
		return this;
	}

    public static ResponseCache fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new ResponseCache()
            .withRegion(data.get("region") == null || data.get("region").isNull() ? null : data.get("region").asText())
            .withOwnerId(data.get("ownerId") == null || data.get("ownerId").isNull() ? null : data.get("ownerId").asText())
            .withResponseCacheId(data.get("responseCacheId") == null || data.get("responseCacheId").isNull() ? null : data.get("responseCacheId").asText())
            .withRequestHash(data.get("requestHash") == null || data.get("requestHash").isNull() ? null : data.get("requestHash").asText())
            .withResult(data.get("result") == null || data.get("result").isNull() ? null : data.get("result").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("region", getRegion());
                put("ownerId", getOwnerId());
                put("responseCacheId", getResponseCacheId());
                put("requestHash", getRequestHash());
                put("result", getResult());
            }}
        );
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
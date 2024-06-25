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

package io.gs2.money2.model;

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
public class StoreContentModel implements IModel, Serializable, Comparable<StoreContentModel> {
	private String storeContentModelId;
	private String name;
	private String metadata;
	private AppleAppStoreContent appleAppStore;
	private GooglePlayContent googlePlay;
	public String getStoreContentModelId() {
		return storeContentModelId;
	}
	public void setStoreContentModelId(String storeContentModelId) {
		this.storeContentModelId = storeContentModelId;
	}
	public StoreContentModel withStoreContentModelId(String storeContentModelId) {
		this.storeContentModelId = storeContentModelId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public StoreContentModel withName(String name) {
		this.name = name;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public StoreContentModel withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public AppleAppStoreContent getAppleAppStore() {
		return appleAppStore;
	}
	public void setAppleAppStore(AppleAppStoreContent appleAppStore) {
		this.appleAppStore = appleAppStore;
	}
	public StoreContentModel withAppleAppStore(AppleAppStoreContent appleAppStore) {
		this.appleAppStore = appleAppStore;
		return this;
	}
	public GooglePlayContent getGooglePlay() {
		return googlePlay;
	}
	public void setGooglePlay(GooglePlayContent googlePlay) {
		this.googlePlay = googlePlay;
	}
	public StoreContentModel withGooglePlay(GooglePlayContent googlePlay) {
		this.googlePlay = googlePlay;
		return this;
	}

    public static StoreContentModel fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new StoreContentModel()
            .withStoreContentModelId(data.get("storeContentModelId") == null || data.get("storeContentModelId").isNull() ? null : data.get("storeContentModelId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withAppleAppStore(data.get("appleAppStore") == null || data.get("appleAppStore").isNull() ? null : AppleAppStoreContent.fromJson(data.get("appleAppStore")))
            .withGooglePlay(data.get("googlePlay") == null || data.get("googlePlay").isNull() ? null : GooglePlayContent.fromJson(data.get("googlePlay")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("storeContentModelId", getStoreContentModelId());
                put("name", getName());
                put("metadata", getMetadata());
                put("appleAppStore", getAppleAppStore() != null ? getAppleAppStore().toJson() : null);
                put("googlePlay", getGooglePlay() != null ? getGooglePlay().toJson() : null);
            }}
        );
    }

	@Override
	public int compareTo(StoreContentModel o) {
		return storeContentModelId.compareTo(o.storeContentModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.storeContentModelId == null) ? 0 : this.storeContentModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.appleAppStore == null) ? 0 : this.appleAppStore.hashCode());
        result = prime * result + ((this.googlePlay == null) ? 0 : this.googlePlay.hashCode());
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
		StoreContentModel other = (StoreContentModel) o;
		if (storeContentModelId == null) {
			return other.storeContentModelId == null;
		} else if (!storeContentModelId.equals(other.storeContentModelId)) {
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
		if (appleAppStore == null) {
			return other.appleAppStore == null;
		} else if (!appleAppStore.equals(other.appleAppStore)) {
			return false;
		}
		if (googlePlay == null) {
			return other.googlePlay == null;
		} else if (!googlePlay.equals(other.googlePlay)) {
			return false;
		}
		return true;
	}
}
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
public class StoreSubscriptionContentModelMaster implements IModel, Serializable, Comparable<StoreSubscriptionContentModelMaster> {
	private String storeSubscriptionContentModelId;
	private String name;
	private String description;
	private String metadata;
	private String scheduleNamespaceId;
	private String triggerName;
	private Integer reallocateSpanDays;
	private AppleAppStoreSubscriptionContent appleAppStore;
	private GooglePlaySubscriptionContent googlePlay;
	private Long createdAt;
	private Long updatedAt;
	private Long revision;
	public String getStoreSubscriptionContentModelId() {
		return storeSubscriptionContentModelId;
	}
	public void setStoreSubscriptionContentModelId(String storeSubscriptionContentModelId) {
		this.storeSubscriptionContentModelId = storeSubscriptionContentModelId;
	}
	public StoreSubscriptionContentModelMaster withStoreSubscriptionContentModelId(String storeSubscriptionContentModelId) {
		this.storeSubscriptionContentModelId = storeSubscriptionContentModelId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public StoreSubscriptionContentModelMaster withName(String name) {
		this.name = name;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public StoreSubscriptionContentModelMaster withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public StoreSubscriptionContentModelMaster withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public String getScheduleNamespaceId() {
		return scheduleNamespaceId;
	}
	public void setScheduleNamespaceId(String scheduleNamespaceId) {
		this.scheduleNamespaceId = scheduleNamespaceId;
	}
	public StoreSubscriptionContentModelMaster withScheduleNamespaceId(String scheduleNamespaceId) {
		this.scheduleNamespaceId = scheduleNamespaceId;
		return this;
	}
	public String getTriggerName() {
		return triggerName;
	}
	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}
	public StoreSubscriptionContentModelMaster withTriggerName(String triggerName) {
		this.triggerName = triggerName;
		return this;
	}
	public Integer getReallocateSpanDays() {
		return reallocateSpanDays;
	}
	public void setReallocateSpanDays(Integer reallocateSpanDays) {
		this.reallocateSpanDays = reallocateSpanDays;
	}
	public StoreSubscriptionContentModelMaster withReallocateSpanDays(Integer reallocateSpanDays) {
		this.reallocateSpanDays = reallocateSpanDays;
		return this;
	}
	public AppleAppStoreSubscriptionContent getAppleAppStore() {
		return appleAppStore;
	}
	public void setAppleAppStore(AppleAppStoreSubscriptionContent appleAppStore) {
		this.appleAppStore = appleAppStore;
	}
	public StoreSubscriptionContentModelMaster withAppleAppStore(AppleAppStoreSubscriptionContent appleAppStore) {
		this.appleAppStore = appleAppStore;
		return this;
	}
	public GooglePlaySubscriptionContent getGooglePlay() {
		return googlePlay;
	}
	public void setGooglePlay(GooglePlaySubscriptionContent googlePlay) {
		this.googlePlay = googlePlay;
	}
	public StoreSubscriptionContentModelMaster withGooglePlay(GooglePlaySubscriptionContent googlePlay) {
		this.googlePlay = googlePlay;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public StoreSubscriptionContentModelMaster withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public StoreSubscriptionContentModelMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public StoreSubscriptionContentModelMaster withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static StoreSubscriptionContentModelMaster fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new StoreSubscriptionContentModelMaster()
            .withStoreSubscriptionContentModelId(data.get("storeSubscriptionContentModelId") == null || data.get("storeSubscriptionContentModelId").isNull() ? null : data.get("storeSubscriptionContentModelId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withScheduleNamespaceId(data.get("scheduleNamespaceId") == null || data.get("scheduleNamespaceId").isNull() ? null : data.get("scheduleNamespaceId").asText())
            .withTriggerName(data.get("triggerName") == null || data.get("triggerName").isNull() ? null : data.get("triggerName").asText())
            .withReallocateSpanDays(data.get("reallocateSpanDays") == null || data.get("reallocateSpanDays").isNull() ? null : data.get("reallocateSpanDays").intValue())
            .withAppleAppStore(data.get("appleAppStore") == null || data.get("appleAppStore").isNull() ? null : AppleAppStoreSubscriptionContent.fromJson(data.get("appleAppStore")))
            .withGooglePlay(data.get("googlePlay") == null || data.get("googlePlay").isNull() ? null : GooglePlaySubscriptionContent.fromJson(data.get("googlePlay")))
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("storeSubscriptionContentModelId", getStoreSubscriptionContentModelId());
                put("name", getName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("scheduleNamespaceId", getScheduleNamespaceId());
                put("triggerName", getTriggerName());
                put("reallocateSpanDays", getReallocateSpanDays());
                put("appleAppStore", getAppleAppStore() != null ? getAppleAppStore().toJson() : null);
                put("googlePlay", getGooglePlay() != null ? getGooglePlay().toJson() : null);
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(StoreSubscriptionContentModelMaster o) {
		return storeSubscriptionContentModelId.compareTo(o.storeSubscriptionContentModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.storeSubscriptionContentModelId == null) ? 0 : this.storeSubscriptionContentModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.scheduleNamespaceId == null) ? 0 : this.scheduleNamespaceId.hashCode());
        result = prime * result + ((this.triggerName == null) ? 0 : this.triggerName.hashCode());
        result = prime * result + ((this.reallocateSpanDays == null) ? 0 : this.reallocateSpanDays.hashCode());
        result = prime * result + ((this.appleAppStore == null) ? 0 : this.appleAppStore.hashCode());
        result = prime * result + ((this.googlePlay == null) ? 0 : this.googlePlay.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
        result = prime * result + ((this.revision == null) ? 0 : this.revision.hashCode());
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
		StoreSubscriptionContentModelMaster other = (StoreSubscriptionContentModelMaster) o;
		if (storeSubscriptionContentModelId == null) {
			return other.storeSubscriptionContentModelId == null;
		} else if (!storeSubscriptionContentModelId.equals(other.storeSubscriptionContentModelId)) {
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
		if (scheduleNamespaceId == null) {
			return other.scheduleNamespaceId == null;
		} else if (!scheduleNamespaceId.equals(other.scheduleNamespaceId)) {
			return false;
		}
		if (triggerName == null) {
			return other.triggerName == null;
		} else if (!triggerName.equals(other.triggerName)) {
			return false;
		}
		if (reallocateSpanDays == null) {
			return other.reallocateSpanDays == null;
		} else if (!reallocateSpanDays.equals(other.reallocateSpanDays)) {
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
		if (revision == null) {
			return other.revision == null;
		} else if (!revision.equals(other.revision)) {
			return false;
		}
		return true;
	}
}
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

package io.gs2.money2.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.money2.model.AppleAppStoreSubscriptionContent;
import io.gs2.money2.model.GooglePlaySubscriptionContent;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class UpdateStoreSubscriptionContentModelMasterRequest extends Gs2BasicRequest<UpdateStoreSubscriptionContentModelMasterRequest> {
    private String namespaceName;
    private String contentName;
    private String description;
    private String metadata;
    private String scheduleNamespaceId;
    private String triggerName;
    private Integer reallocateSpanDays;
    private AppleAppStoreSubscriptionContent appleAppStore;
    private GooglePlaySubscriptionContent googlePlay;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public UpdateStoreSubscriptionContentModelMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getContentName() {
		return contentName;
	}
	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
	public UpdateStoreSubscriptionContentModelMasterRequest withContentName(String contentName) {
		this.contentName = contentName;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UpdateStoreSubscriptionContentModelMasterRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public UpdateStoreSubscriptionContentModelMasterRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public String getScheduleNamespaceId() {
		return scheduleNamespaceId;
	}
	public void setScheduleNamespaceId(String scheduleNamespaceId) {
		this.scheduleNamespaceId = scheduleNamespaceId;
	}
	public UpdateStoreSubscriptionContentModelMasterRequest withScheduleNamespaceId(String scheduleNamespaceId) {
		this.scheduleNamespaceId = scheduleNamespaceId;
		return this;
	}
	public String getTriggerName() {
		return triggerName;
	}
	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}
	public UpdateStoreSubscriptionContentModelMasterRequest withTriggerName(String triggerName) {
		this.triggerName = triggerName;
		return this;
	}
	public Integer getReallocateSpanDays() {
		return reallocateSpanDays;
	}
	public void setReallocateSpanDays(Integer reallocateSpanDays) {
		this.reallocateSpanDays = reallocateSpanDays;
	}
	public UpdateStoreSubscriptionContentModelMasterRequest withReallocateSpanDays(Integer reallocateSpanDays) {
		this.reallocateSpanDays = reallocateSpanDays;
		return this;
	}
	public AppleAppStoreSubscriptionContent getAppleAppStore() {
		return appleAppStore;
	}
	public void setAppleAppStore(AppleAppStoreSubscriptionContent appleAppStore) {
		this.appleAppStore = appleAppStore;
	}
	public UpdateStoreSubscriptionContentModelMasterRequest withAppleAppStore(AppleAppStoreSubscriptionContent appleAppStore) {
		this.appleAppStore = appleAppStore;
		return this;
	}
	public GooglePlaySubscriptionContent getGooglePlay() {
		return googlePlay;
	}
	public void setGooglePlay(GooglePlaySubscriptionContent googlePlay) {
		this.googlePlay = googlePlay;
	}
	public UpdateStoreSubscriptionContentModelMasterRequest withGooglePlay(GooglePlaySubscriptionContent googlePlay) {
		this.googlePlay = googlePlay;
		return this;
	}

    public static UpdateStoreSubscriptionContentModelMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateStoreSubscriptionContentModelMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withContentName(data.get("contentName") == null || data.get("contentName").isNull() ? null : data.get("contentName").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withScheduleNamespaceId(data.get("scheduleNamespaceId") == null || data.get("scheduleNamespaceId").isNull() ? null : data.get("scheduleNamespaceId").asText())
            .withTriggerName(data.get("triggerName") == null || data.get("triggerName").isNull() ? null : data.get("triggerName").asText())
            .withReallocateSpanDays(data.get("reallocateSpanDays") == null || data.get("reallocateSpanDays").isNull() ? null : data.get("reallocateSpanDays").intValue())
            .withAppleAppStore(data.get("appleAppStore") == null || data.get("appleAppStore").isNull() ? null : AppleAppStoreSubscriptionContent.fromJson(data.get("appleAppStore")))
            .withGooglePlay(data.get("googlePlay") == null || data.get("googlePlay").isNull() ? null : GooglePlaySubscriptionContent.fromJson(data.get("googlePlay")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("contentName", getContentName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("scheduleNamespaceId", getScheduleNamespaceId());
                put("triggerName", getTriggerName());
                put("reallocateSpanDays", getReallocateSpanDays());
                put("appleAppStore", getAppleAppStore() != null ? getAppleAppStore().toJson() : null);
                put("googlePlay", getGooglePlay() != null ? getGooglePlay().toJson() : null);
            }}
        );
    }
}
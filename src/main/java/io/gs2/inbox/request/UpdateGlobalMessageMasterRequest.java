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

package io.gs2.inbox.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.inbox.model.AcquireAction;
import io.gs2.inbox.model.TimeSpan;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class UpdateGlobalMessageMasterRequest extends Gs2BasicRequest<UpdateGlobalMessageMasterRequest> {
    private String namespaceName;
    private String globalMessageName;
    private String metadata;
    private List<AcquireAction> readAcquireActions;
    private TimeSpan expiresTimeSpan;
    private Long expiresAt;
    private String messageReceptionPeriodEventId;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public UpdateGlobalMessageMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getGlobalMessageName() {
		return globalMessageName;
	}
	public void setGlobalMessageName(String globalMessageName) {
		this.globalMessageName = globalMessageName;
	}
	public UpdateGlobalMessageMasterRequest withGlobalMessageName(String globalMessageName) {
		this.globalMessageName = globalMessageName;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public UpdateGlobalMessageMasterRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public List<AcquireAction> getReadAcquireActions() {
		return readAcquireActions;
	}
	public void setReadAcquireActions(List<AcquireAction> readAcquireActions) {
		this.readAcquireActions = readAcquireActions;
	}
	public UpdateGlobalMessageMasterRequest withReadAcquireActions(List<AcquireAction> readAcquireActions) {
		this.readAcquireActions = readAcquireActions;
		return this;
	}
	public TimeSpan getExpiresTimeSpan() {
		return expiresTimeSpan;
	}
	public void setExpiresTimeSpan(TimeSpan expiresTimeSpan) {
		this.expiresTimeSpan = expiresTimeSpan;
	}
	public UpdateGlobalMessageMasterRequest withExpiresTimeSpan(TimeSpan expiresTimeSpan) {
		this.expiresTimeSpan = expiresTimeSpan;
		return this;
	}
	public Long getExpiresAt() {
		return expiresAt;
	}
	public void setExpiresAt(Long expiresAt) {
		this.expiresAt = expiresAt;
	}
	public UpdateGlobalMessageMasterRequest withExpiresAt(Long expiresAt) {
		this.expiresAt = expiresAt;
		return this;
	}
	public String getMessageReceptionPeriodEventId() {
		return messageReceptionPeriodEventId;
	}
	public void setMessageReceptionPeriodEventId(String messageReceptionPeriodEventId) {
		this.messageReceptionPeriodEventId = messageReceptionPeriodEventId;
	}
	public UpdateGlobalMessageMasterRequest withMessageReceptionPeriodEventId(String messageReceptionPeriodEventId) {
		this.messageReceptionPeriodEventId = messageReceptionPeriodEventId;
		return this;
	}

    public static UpdateGlobalMessageMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateGlobalMessageMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withGlobalMessageName(data.get("globalMessageName") == null || data.get("globalMessageName").isNull() ? null : data.get("globalMessageName").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withReadAcquireActions(data.get("readAcquireActions") == null || data.get("readAcquireActions").isNull() ? new ArrayList<AcquireAction>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("readAcquireActions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return AcquireAction.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withExpiresTimeSpan(data.get("expiresTimeSpan") == null || data.get("expiresTimeSpan").isNull() ? null : TimeSpan.fromJson(data.get("expiresTimeSpan")))
            .withExpiresAt(data.get("expiresAt") == null || data.get("expiresAt").isNull() ? null : data.get("expiresAt").longValue())
            .withMessageReceptionPeriodEventId(data.get("messageReceptionPeriodEventId") == null || data.get("messageReceptionPeriodEventId").isNull() ? null : data.get("messageReceptionPeriodEventId").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("globalMessageName", getGlobalMessageName());
                put("metadata", getMetadata());
                put("readAcquireActions", getReadAcquireActions() == null ? new ArrayList<AcquireAction>() :
                    getReadAcquireActions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("expiresTimeSpan", getExpiresTimeSpan() != null ? getExpiresTimeSpan().toJson() : null);
                put("expiresAt", getExpiresAt());
                put("messageReceptionPeriodEventId", getMessageReceptionPeriodEventId());
            }}
        );
    }
}
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

package io.gs2.formation.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.formation.model.AcquireAction;
import io.gs2.formation.model.Config;
import io.gs2.formation.model.AcquireActionConfig;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class AcquireActionsToFormPropertiesRequest extends Gs2BasicRequest<AcquireActionsToFormPropertiesRequest> {
    private String namespaceName;
    private String userId;
    private String moldName;
    private Integer index;
    private AcquireAction acquireAction;
    private String queueNamespaceId;
    private String keyId;
    private List<AcquireActionConfig> config;
    private String duplicationAvoider;

	public String getNamespaceName() {
		return namespaceName;
	}

	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}

	public AcquireActionsToFormPropertiesRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public AcquireActionsToFormPropertiesRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public String getMoldName() {
		return moldName;
	}

	public void setMoldName(String moldName) {
		this.moldName = moldName;
	}

	public AcquireActionsToFormPropertiesRequest withMoldName(String moldName) {
		this.moldName = moldName;
		return this;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public AcquireActionsToFormPropertiesRequest withIndex(Integer index) {
		this.index = index;
		return this;
	}

	public AcquireAction getAcquireAction() {
		return acquireAction;
	}

	public void setAcquireAction(AcquireAction acquireAction) {
		this.acquireAction = acquireAction;
	}

	public AcquireActionsToFormPropertiesRequest withAcquireAction(AcquireAction acquireAction) {
		this.acquireAction = acquireAction;
		return this;
	}

	public String getQueueNamespaceId() {
		return queueNamespaceId;
	}

	public void setQueueNamespaceId(String queueNamespaceId) {
		this.queueNamespaceId = queueNamespaceId;
	}

	public AcquireActionsToFormPropertiesRequest withQueueNamespaceId(String queueNamespaceId) {
		this.queueNamespaceId = queueNamespaceId;
		return this;
	}

	public String getKeyId() {
		return keyId;
	}

	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

	public AcquireActionsToFormPropertiesRequest withKeyId(String keyId) {
		this.keyId = keyId;
		return this;
	}

	public List<AcquireActionConfig> getConfig() {
		return config;
	}

	public void setConfig(List<AcquireActionConfig> config) {
		this.config = config;
	}

	public AcquireActionsToFormPropertiesRequest withConfig(List<AcquireActionConfig> config) {
		this.config = config;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public AcquireActionsToFormPropertiesRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static AcquireActionsToFormPropertiesRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new AcquireActionsToFormPropertiesRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withMoldName(data.get("moldName") == null || data.get("moldName").isNull() ? null : data.get("moldName").asText())
            .withIndex(data.get("index") == null || data.get("index").isNull() ? null : data.get("index").intValue())
            .withAcquireAction(data.get("acquireAction") == null || data.get("acquireAction").isNull() ? null : AcquireAction.fromJson(data.get("acquireAction")))
            .withQueueNamespaceId(data.get("queueNamespaceId") == null || data.get("queueNamespaceId").isNull() ? null : data.get("queueNamespaceId").asText())
            .withKeyId(data.get("keyId") == null || data.get("keyId").isNull() ? null : data.get("keyId").asText())
            .withConfig(data.get("config") == null || data.get("config").isNull() ? new ArrayList<AcquireActionConfig>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("config").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return AcquireActionConfig.fromJson(item);
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("userId", getUserId());
                put("moldName", getMoldName());
                put("index", getIndex());
                put("acquireAction", getAcquireAction() != null ? getAcquireAction().toJson() : null);
                put("queueNamespaceId", getQueueNamespaceId());
                put("keyId", getKeyId());
                put("config", getConfig() == null ? new ArrayList<AcquireActionConfig>() :
                    getConfig().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }
}
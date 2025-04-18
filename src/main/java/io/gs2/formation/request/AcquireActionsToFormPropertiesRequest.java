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

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class AcquireActionsToFormPropertiesRequest extends Gs2BasicRequest<AcquireActionsToFormPropertiesRequest> {
    private String namespaceName;
    private String userId;
    private String moldModelName;
    private Integer index;
    private AcquireAction acquireAction;
    private List<Config> config;
    private String timeOffsetToken;
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
	public String getMoldModelName() {
		return moldModelName;
	}
	public void setMoldModelName(String moldModelName) {
		this.moldModelName = moldModelName;
	}
	public AcquireActionsToFormPropertiesRequest withMoldModelName(String moldModelName) {
		this.moldModelName = moldModelName;
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
	public List<Config> getConfig() {
		return config;
	}
	public void setConfig(List<Config> config) {
		this.config = config;
	}
	public AcquireActionsToFormPropertiesRequest withConfig(List<Config> config) {
		this.config = config;
		return this;
	}
	public String getTimeOffsetToken() {
		return timeOffsetToken;
	}
	public void setTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
	}
	public AcquireActionsToFormPropertiesRequest withTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
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
            .withMoldModelName(data.get("moldModelName") == null || data.get("moldModelName").isNull() ? null : data.get("moldModelName").asText())
            .withIndex(data.get("index") == null || data.get("index").isNull() ? null : data.get("index").intValue())
            .withAcquireAction(data.get("acquireAction") == null || data.get("acquireAction").isNull() ? null : AcquireAction.fromJson(data.get("acquireAction")))
            .withConfig(data.get("config") == null || data.get("config").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("config").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return Config.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withTimeOffsetToken(data.get("timeOffsetToken") == null || data.get("timeOffsetToken").isNull() ? null : data.get("timeOffsetToken").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("userId", getUserId());
                put("moldModelName", getMoldModelName());
                put("index", getIndex());
                put("acquireAction", getAcquireAction() != null ? getAcquireAction().toJson() : null);
                put("config", getConfig() == null ? null :
                    getConfig().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("timeOffsetToken", getTimeOffsetToken());
            }}
        );
    }
}
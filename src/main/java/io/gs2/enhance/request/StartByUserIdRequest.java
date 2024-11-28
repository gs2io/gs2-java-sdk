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

package io.gs2.enhance.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.enhance.model.Material;
import io.gs2.enhance.model.Config;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class StartByUserIdRequest extends Gs2BasicRequest<StartByUserIdRequest> {
    private String namespaceName;
    private String rateName;
    private String targetItemSetId;
    private List<Material> materials;
    private String userId;
    private Boolean force;
    private List<Config> config;
    private String timeOffsetToken;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public StartByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getRateName() {
		return rateName;
	}
	public void setRateName(String rateName) {
		this.rateName = rateName;
	}
	public StartByUserIdRequest withRateName(String rateName) {
		this.rateName = rateName;
		return this;
	}
	public String getTargetItemSetId() {
		return targetItemSetId;
	}
	public void setTargetItemSetId(String targetItemSetId) {
		this.targetItemSetId = targetItemSetId;
	}
	public StartByUserIdRequest withTargetItemSetId(String targetItemSetId) {
		this.targetItemSetId = targetItemSetId;
		return this;
	}
	public List<Material> getMaterials() {
		return materials;
	}
	public void setMaterials(List<Material> materials) {
		this.materials = materials;
	}
	public StartByUserIdRequest withMaterials(List<Material> materials) {
		this.materials = materials;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public StartByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public Boolean getForce() {
		return force;
	}
	public void setForce(Boolean force) {
		this.force = force;
	}
	public StartByUserIdRequest withForce(Boolean force) {
		this.force = force;
		return this;
	}
	public List<Config> getConfig() {
		return config;
	}
	public void setConfig(List<Config> config) {
		this.config = config;
	}
	public StartByUserIdRequest withConfig(List<Config> config) {
		this.config = config;
		return this;
	}
	public String getTimeOffsetToken() {
		return timeOffsetToken;
	}
	public void setTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
	}
	public StartByUserIdRequest withTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public StartByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static StartByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new StartByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withRateName(data.get("rateName") == null || data.get("rateName").isNull() ? null : data.get("rateName").asText())
            .withTargetItemSetId(data.get("targetItemSetId") == null || data.get("targetItemSetId").isNull() ? null : data.get("targetItemSetId").asText())
            .withMaterials(data.get("materials") == null || data.get("materials").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("materials").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return Material.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withForce(data.get("force") == null || data.get("force").isNull() ? null : data.get("force").booleanValue())
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
                put("rateName", getRateName());
                put("targetItemSetId", getTargetItemSetId());
                put("materials", getMaterials() == null ? null :
                    getMaterials().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("userId", getUserId());
                put("force", getForce());
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
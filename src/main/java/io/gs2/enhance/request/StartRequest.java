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
public class StartRequest extends Gs2BasicRequest<StartRequest> {
    private String namespaceName;
    private String rateName;
    private String targetItemSetId;
    private List<Material> materials;
    private String accessToken;
    private Boolean force;
    private List<Config> config;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public StartRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getRateName() {
		return rateName;
	}
	public void setRateName(String rateName) {
		this.rateName = rateName;
	}
	public StartRequest withRateName(String rateName) {
		this.rateName = rateName;
		return this;
	}
	public String getTargetItemSetId() {
		return targetItemSetId;
	}
	public void setTargetItemSetId(String targetItemSetId) {
		this.targetItemSetId = targetItemSetId;
	}
	public StartRequest withTargetItemSetId(String targetItemSetId) {
		this.targetItemSetId = targetItemSetId;
		return this;
	}
	public List<Material> getMaterials() {
		return materials;
	}
	public void setMaterials(List<Material> materials) {
		this.materials = materials;
	}
	public StartRequest withMaterials(List<Material> materials) {
		this.materials = materials;
		return this;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public StartRequest withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}
	public Boolean getForce() {
		return force;
	}
	public void setForce(Boolean force) {
		this.force = force;
	}
	public StartRequest withForce(Boolean force) {
		this.force = force;
		return this;
	}
	public List<Config> getConfig() {
		return config;
	}
	public void setConfig(List<Config> config) {
		this.config = config;
	}
	public StartRequest withConfig(List<Config> config) {
		this.config = config;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public StartRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static StartRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new StartRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withRateName(data.get("rateName") == null || data.get("rateName").isNull() ? null : data.get("rateName").asText())
            .withTargetItemSetId(data.get("targetItemSetId") == null || data.get("targetItemSetId").isNull() ? null : data.get("targetItemSetId").asText())
            .withMaterials(data.get("materials") == null || data.get("materials").isNull() ? new ArrayList<Material>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("materials").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return Material.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withAccessToken(data.get("accessToken") == null || data.get("accessToken").isNull() ? null : data.get("accessToken").asText())
            .withForce(data.get("force") == null || data.get("force").isNull() ? null : data.get("force").booleanValue())
            .withConfig(data.get("config") == null || data.get("config").isNull() ? new ArrayList<Config>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("config").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return Config.fromJson(item);
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("rateName", getRateName());
                put("targetItemSetId", getTargetItemSetId());
                put("materials", getMaterials() == null ? new ArrayList<Material>() :
                    getMaterials().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("accessToken", getAccessToken());
                put("force", getForce());
                put("config", getConfig() == null ? new ArrayList<Config>() :
                    getConfig().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }
}
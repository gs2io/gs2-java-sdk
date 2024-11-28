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
import io.gs2.formation.model.SlotWithSignature;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class SetPropertyFormWithSignatureRequest extends Gs2BasicRequest<SetPropertyFormWithSignatureRequest> {
    private String namespaceName;
    private String accessToken;
    private String propertyFormModelName;
    private String propertyId;
    private List<SlotWithSignature> slots;
    private String keyId;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public SetPropertyFormWithSignatureRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public SetPropertyFormWithSignatureRequest withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}
	public String getPropertyFormModelName() {
		return propertyFormModelName;
	}
	public void setPropertyFormModelName(String propertyFormModelName) {
		this.propertyFormModelName = propertyFormModelName;
	}
	public SetPropertyFormWithSignatureRequest withPropertyFormModelName(String propertyFormModelName) {
		this.propertyFormModelName = propertyFormModelName;
		return this;
	}
	public String getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	public SetPropertyFormWithSignatureRequest withPropertyId(String propertyId) {
		this.propertyId = propertyId;
		return this;
	}
	public List<SlotWithSignature> getSlots() {
		return slots;
	}
	public void setSlots(List<SlotWithSignature> slots) {
		this.slots = slots;
	}
	public SetPropertyFormWithSignatureRequest withSlots(List<SlotWithSignature> slots) {
		this.slots = slots;
		return this;
	}
	public String getKeyId() {
		return keyId;
	}
	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}
	public SetPropertyFormWithSignatureRequest withKeyId(String keyId) {
		this.keyId = keyId;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public SetPropertyFormWithSignatureRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static SetPropertyFormWithSignatureRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new SetPropertyFormWithSignatureRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withAccessToken(data.get("accessToken") == null || data.get("accessToken").isNull() ? null : data.get("accessToken").asText())
            .withPropertyFormModelName(data.get("propertyFormModelName") == null || data.get("propertyFormModelName").isNull() ? null : data.get("propertyFormModelName").asText())
            .withPropertyId(data.get("propertyId") == null || data.get("propertyId").isNull() ? null : data.get("propertyId").asText())
            .withSlots(data.get("slots") == null || data.get("slots").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("slots").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return SlotWithSignature.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withKeyId(data.get("keyId") == null || data.get("keyId").isNull() ? null : data.get("keyId").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("accessToken", getAccessToken());
                put("propertyFormModelName", getPropertyFormModelName());
                put("propertyId", getPropertyId());
                put("slots", getSlots() == null ? null :
                    getSlots().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("keyId", getKeyId());
            }}
        );
    }
}
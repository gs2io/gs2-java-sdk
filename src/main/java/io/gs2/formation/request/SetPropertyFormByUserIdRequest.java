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
import io.gs2.formation.model.Slot;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class SetPropertyFormByUserIdRequest extends Gs2BasicRequest<SetPropertyFormByUserIdRequest> {
    private String namespaceName;
    private String userId;
    private String formModelName;
    private String propertyId;
    private List<Slot> slots;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public SetPropertyFormByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public SetPropertyFormByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getFormModelName() {
		return formModelName;
	}
	public void setFormModelName(String formModelName) {
		this.formModelName = formModelName;
	}
	public SetPropertyFormByUserIdRequest withFormModelName(String formModelName) {
		this.formModelName = formModelName;
		return this;
	}
	public String getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	public SetPropertyFormByUserIdRequest withPropertyId(String propertyId) {
		this.propertyId = propertyId;
		return this;
	}
	public List<Slot> getSlots() {
		return slots;
	}
	public void setSlots(List<Slot> slots) {
		this.slots = slots;
	}
	public SetPropertyFormByUserIdRequest withSlots(List<Slot> slots) {
		this.slots = slots;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public SetPropertyFormByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static SetPropertyFormByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new SetPropertyFormByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withFormModelName(data.get("formModelName") == null || data.get("formModelName").isNull() ? null : data.get("formModelName").asText())
            .withPropertyId(data.get("propertyId") == null || data.get("propertyId").isNull() ? null : data.get("propertyId").asText())
            .withSlots(data.get("slots") == null || data.get("slots").isNull() ? new ArrayList<Slot>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("slots").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return Slot.fromJson(item);
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("userId", getUserId());
                put("formModelName", getFormModelName());
                put("propertyId", getPropertyId());
                put("slots", getSlots() == null ? new ArrayList<Slot>() :
                    getSlots().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }
}
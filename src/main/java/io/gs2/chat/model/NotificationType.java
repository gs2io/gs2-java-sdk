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

package io.gs2.chat.model;

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
public class NotificationType implements IModel, Serializable {
	private Integer category;
	private Boolean enableTransferMobilePushNotification;
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public NotificationType withCategory(Integer category) {
		this.category = category;
		return this;
	}
	public Boolean getEnableTransferMobilePushNotification() {
		return enableTransferMobilePushNotification;
	}
	public void setEnableTransferMobilePushNotification(Boolean enableTransferMobilePushNotification) {
		this.enableTransferMobilePushNotification = enableTransferMobilePushNotification;
	}
	public NotificationType withEnableTransferMobilePushNotification(Boolean enableTransferMobilePushNotification) {
		this.enableTransferMobilePushNotification = enableTransferMobilePushNotification;
		return this;
	}

    public static NotificationType fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new NotificationType()
            .withCategory(data.get("category") == null || data.get("category").isNull() ? null : data.get("category").intValue())
            .withEnableTransferMobilePushNotification(data.get("enableTransferMobilePushNotification") == null || data.get("enableTransferMobilePushNotification").isNull() ? null : data.get("enableTransferMobilePushNotification").booleanValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("category", getCategory());
                put("enableTransferMobilePushNotification", getEnableTransferMobilePushNotification());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.category == null) ? 0 : this.category.hashCode());
        result = prime * result + ((this.enableTransferMobilePushNotification == null) ? 0 : this.enableTransferMobilePushNotification.hashCode());
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
		NotificationType other = (NotificationType) o;
		if (category == null) {
			return other.category == null;
		} else if (!category.equals(other.category)) {
			return false;
		}
		if (enableTransferMobilePushNotification == null) {
			return other.enableTransferMobilePushNotification == null;
		} else if (!enableTransferMobilePushNotification.equals(other.enableTransferMobilePushNotification)) {
			return false;
		}
		return true;
	}
}
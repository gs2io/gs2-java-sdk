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
public class GooglePlayRealtimeNotificationMessage implements IModel, Serializable {
	private String data;
	private String messageId;
	private String publishTime;
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public GooglePlayRealtimeNotificationMessage withData(String data) {
		this.data = data;
		return this;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public GooglePlayRealtimeNotificationMessage withMessageId(String messageId) {
		this.messageId = messageId;
		return this;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	public GooglePlayRealtimeNotificationMessage withPublishTime(String publishTime) {
		this.publishTime = publishTime;
		return this;
	}

    public static GooglePlayRealtimeNotificationMessage fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GooglePlayRealtimeNotificationMessage()
            .withData(data.get("data") == null || data.get("data").isNull() ? null : data.get("data").asText())
            .withMessageId(data.get("messageId") == null || data.get("messageId").isNull() ? null : data.get("messageId").asText())
            .withPublishTime(data.get("publishTime") == null || data.get("publishTime").isNull() ? null : data.get("publishTime").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("data", getData());
                put("messageId", getMessageId());
                put("publishTime", getPublishTime());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.data == null) ? 0 : this.data.hashCode());
        result = prime * result + ((this.messageId == null) ? 0 : this.messageId.hashCode());
        result = prime * result + ((this.publishTime == null) ? 0 : this.publishTime.hashCode());
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
		GooglePlayRealtimeNotificationMessage other = (GooglePlayRealtimeNotificationMessage) o;
		if (data == null) {
			return other.data == null;
		} else if (!data.equals(other.data)) {
			return false;
		}
		if (messageId == null) {
			return other.messageId == null;
		} else if (!messageId.equals(other.messageId)) {
			return false;
		}
		if (publishTime == null) {
			return other.publishTime == null;
		} else if (!publishTime.equals(other.publishTime)) {
			return false;
		}
		return true;
	}
}